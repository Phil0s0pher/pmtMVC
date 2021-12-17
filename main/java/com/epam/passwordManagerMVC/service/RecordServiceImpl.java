package com.epam.passwordManagerMVC.service;

import com.epam.passwordManagerMVC.dao.RecordDaoImpl;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RecordDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Records;
import com.epam.passwordManagerMVC.exception.NoRecordFoundForAccount;
import com.epam.passwordManagerMVC.exception.NoRecordFoundForAccountBasedOnUrl;
import com.epam.passwordManagerMVC.exception.RecordAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    RecordDaoImpl recordDaoImpl;

    @Override
    public Records addRecord(RecordDTO recordDTO) throws RecordAlreadyExistsException {
        return recordDaoImpl.addRecord(recordDTO);
    }

    @Override
    public Records findRecordBasedOnUrl(String url) throws NoRecordFoundForAccountBasedOnUrl {
        return recordDaoImpl.findRecordBasedOnUrl(url);
    }

    @Override
    public List<Records> findAllRecords() throws NoRecordFoundForAccount {
        return recordDaoImpl.findAllRecords();
    }

    @Override
    public Records updateRecord(RecordDTO recordDTO) throws NoRecordFoundForAccountBasedOnUrl {
        return recordDaoImpl.updateRecord(recordDTO);
    }

    @Override
    public Records deleteRecordBasedOnUrl(String url) throws NoRecordFoundForAccountBasedOnUrl {
        return recordDaoImpl.deleteRecordBasedOnUrl(url);
    }

    @Override
    public void setAccount(LoginDTO loginDTO) {
        recordDaoImpl.setAccount(loginDTO);
    }

    @Override
    public Account getAccount() {
        return recordDaoImpl.getAccount();
    }
}

