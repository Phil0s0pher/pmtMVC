package com.epam.passwordManagerMVC.repository;

import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import com.epam.passwordManagerMVC.entity.Records;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface RecordRepository extends JpaRepository<Records, Integer> {
    List<Records> findByAccount(Account account);
    boolean existsByUrlAndAccount(String url, Account account);

    void deleteByUrlAndAccount(String url, Account account);

    Records findByUrlAndAccount(String url, Account account);

    List<Records> findByGroupAndAccount(Group existGroup, Account account);
}