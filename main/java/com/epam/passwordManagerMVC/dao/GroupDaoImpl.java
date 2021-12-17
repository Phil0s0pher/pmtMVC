package com.epam.passwordManagerMVC.dao;

import com.epam.passwordManagerMVC.converter.Convert;
import com.epam.passwordManagerMVC.dto.GroupDTO;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import com.epam.passwordManagerMVC.entity.Records;
import com.epam.passwordManagerMVC.exception.GroupAlreadyExistsException;
import com.epam.passwordManagerMVC.exception.GroupShouldNotContainsRecords;
import com.epam.passwordManagerMVC.exception.NoGroupFoundForAccount;
import com.epam.passwordManagerMVC.exception.NoRecordFoundForGroup;
import com.epam.passwordManagerMVC.repository.GroupRepository;
import com.epam.passwordManagerMVC.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupDaoImpl implements GroupDao {
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    RecordRepository recordRepository;

    private Account account;

    @Override
    public Group addGroup(GroupDTO groupDTO) throws GroupAlreadyExistsException {
        Group group = Convert.convertToEntity(groupDTO);
        group.setAccount(account);
        Group existGroup = groupRepository.findByNameAndAccount(group.getName(), group.getAccount());
        if (existGroup != null) {
            throw new GroupAlreadyExistsException();
        }
        return groupRepository.save(group);
    }

    @Override
    public List<Records> findAllRecordByGroupName(String name) throws NoRecordFoundForGroup, NoGroupFoundForAccount {
        Group existGroup = findGroupByName(name);
        List<Records> recordList = recordRepository.findByGroupAndAccount(existGroup, account);
        if (recordList.isEmpty()) {
            throw new NoRecordFoundForGroup();
        }
        return recordList;
    }

    @Override
    public Group updateGroup(GroupDTO groupDTO) throws NoGroupFoundForAccount {
        Group group = Convert.convertToEntity(groupDTO);
        group.setAccount(account);
        Optional<Group> fetchGroup = groupRepository.findById(group.getId());
        if (fetchGroup.isEmpty()) {
            throw new NoGroupFoundForAccount();
        }
        Group existGroup = fetchGroup.get();
        existGroup.setName(group.getName());
        existGroup.setDescription(group.getDescription());
        return groupRepository.save(existGroup);
    }

    @Override
    public List<Group> findAllGroups() throws NoGroupFoundForAccount {
        List<Group> groupList = groupRepository.findByAccount(account);
        if (groupList.isEmpty()) {
            throw new NoGroupFoundForAccount();
        }
        return groupList;
    }

    @Override
    public boolean deleteGroup(String name) throws NoGroupFoundForAccount, GroupShouldNotContainsRecords {
        Group existGroup = groupRepository.findByNameAndAccount(name, account);
        if (existGroup == null) {
            throw new NoGroupFoundForAccount();
        }
        List<Records> recordList = recordRepository.findByGroupAndAccount(existGroup, account);
        if (recordList.isEmpty()) {
            groupRepository.deleteById(existGroup.getId());
            return true;
        } else {
            throw new GroupShouldNotContainsRecords();
        }
    }

    @Override
    public Group findGroupByName(String name) throws NoGroupFoundForAccount {
        Group fetchGroup = groupRepository.findByNameAndAccount(name, account);
        if (fetchGroup == null) {
            throw new NoGroupFoundForAccount();
        }
        return fetchGroup;
    }

    @Override
    public void setAccount(LoginDTO loginDTO) {
        this.account = Convert.convertToEntity(loginDTO);
    }

    @Override
    public Account getAccount() {
        return this.account;
    }
}
