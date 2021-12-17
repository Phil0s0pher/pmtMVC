package com.epam.passwordManagerMVC.dao;

import com.epam.passwordManagerMVC.converter.Convert;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RecordDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import com.epam.passwordManagerMVC.entity.Records;
import com.epam.passwordManagerMVC.exception.NoRecordFoundForAccount;
import com.epam.passwordManagerMVC.exception.NoRecordFoundForAccountBasedOnUrl;
import com.epam.passwordManagerMVC.exception.RecordAlreadyExistsException;
import com.epam.passwordManagerMVC.repository.GroupRepository;
import com.epam.passwordManagerMVC.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RecordDaoImpl implements RecordDao {
        @Autowired
        RecordRepository recordRepository;
        @Autowired
        GroupRepository groupRepository;

        private Account account;

    @Override
    public Records addRecord(RecordDTO recordDTO) throws RecordAlreadyExistsException {
        Records records = Convert.convertToEntity(recordDTO);
        records.setAccount(account);
        boolean existRecord = recordRepository.existsByUrlAndAccount(records.getUrl(), account);
        if (existRecord) {
            throw new RecordAlreadyExistsException();
        }
        Group existGroup = groupRepository.findByNameAndAccount(recordDTO.getGroup_name(), account);
        records.setGroup(existGroup);
        return recordRepository.save(records);
    }

    @Override
    public Records findRecordBasedOnUrl(String url) throws NoRecordFoundForAccountBasedOnUrl {
        Records fetchRecords = recordRepository.findByUrlAndAccount(url, account);
        if (fetchRecords == null) {
            throw new NoRecordFoundForAccountBasedOnUrl();
        } else {
            return fetchRecords;
        }
    }

    @Override
    public List<Records> findAllRecords() throws NoRecordFoundForAccount {
        List<Records> recordsList = recordRepository.findByAccount(account);
        if (recordsList == null) {
            throw new NoRecordFoundForAccount();
        }
        return recordsList;
    }

    @Override
    public Records updateRecord(RecordDTO recordDTO) throws NoRecordFoundForAccountBasedOnUrl {
        Records records = Convert.convertToEntity(recordDTO);
        records.setAccount(account);
        Records foundRecords = recordRepository.findByUrlAndAccount(records.getUrl(), account);
        if (foundRecords == null) {
            throw new NoRecordFoundForAccountBasedOnUrl();
        }
        foundRecords.setUserName(records.getUserName());
        foundRecords.setPassword(records.getPassword());
        foundRecords.setNotes(records.getNotes());
        return recordRepository.save(foundRecords);
    }

    @Override
    public Records deleteRecordBasedOnUrl(String url) throws NoRecordFoundForAccountBasedOnUrl {
        Records fetchRecords = findRecordBasedOnUrl(url);
        recordRepository.deleteByUrlAndAccount(url, account);
        return fetchRecords;
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
