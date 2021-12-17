package com.epam.passwordManagerMVC.repository;

import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByNameAndAccount(String name, Account account);
    List<Group> findByAccount(Account account);
}