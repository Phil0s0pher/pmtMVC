package com.epam.passwordManagerMVC.dao;

import com.epam.passwordManagerMVC.dto.GroupDTO;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import com.epam.passwordManagerMVC.entity.Records;
import com.epam.passwordManagerMVC.exception.*;

import java.util.List;

public interface GroupDao {
    Group addGroup(GroupDTO groupDTO) throws UnableToAddGroup, GroupAlreadyExistsException;
    List<Records> findAllRecordByGroupName(String name) throws NoRecordFoundForGroup, NoGroupFoundForAccount;
    Group updateGroup(GroupDTO groupDTO) throws UnableToUpdateGroup, NoGroupFoundForAccount;
    List<Group> findAllGroups() throws NoGroupFoundForAccount;
    boolean deleteGroup(String name) throws NoGroupFoundForAccount, GroupShouldNotContainsRecords;
    void setAccount(LoginDTO LoginDTO);
    Group findGroupByName(String name) throws NoGroupFoundForAccount;
    Account getAccount();
}