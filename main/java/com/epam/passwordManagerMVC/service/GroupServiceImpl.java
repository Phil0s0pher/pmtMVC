package com.epam.passwordManagerMVC.service;

import com.epam.passwordManagerMVC.dao.GroupDaoImpl;
import com.epam.passwordManagerMVC.dto.GroupDTO;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import com.epam.passwordManagerMVC.entity.Records;
import com.epam.passwordManagerMVC.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService{
    @Autowired
    GroupDaoImpl groupDaoImpl;

    @Override
    public Group addGroup(GroupDTO groupDTO) throws UnableToAddGroup, GroupAlreadyExistsException {
        return groupDaoImpl.addGroup(groupDTO);
    }

    @Override
    public List<Records> findAllRecordByGroupName(String name) throws NoRecordFoundForGroup, NoGroupFoundForAccount {
        return groupDaoImpl.findAllRecordByGroupName(name);
    }

    @Override
    public Group updateGroup(GroupDTO groupDTO) throws UnableToUpdateGroup, NoGroupFoundForAccount {
        return groupDaoImpl.updateGroup(groupDTO);
    }

    @Override
    public List<Group> findAllGroups() throws NoGroupFoundForAccount {
        return groupDaoImpl.findAllGroups();
    }

    @Override
    public Group findGroupByName(String name) throws NoGroupFoundForAccount {
        return groupDaoImpl.findGroupByName(name);
    }

    @Override
    public boolean deleteGroup(String name) throws NoGroupFoundForAccount, GroupShouldNotContainsRecords {
        return groupDaoImpl.deleteGroup(name);
    }

    @Override
    public void setAccount(LoginDTO loginDTO) {
        groupDaoImpl.setAccount(loginDTO);
    }

    @Override
    public Account getAccount() {
        return groupDaoImpl.getAccount();
    }
}
