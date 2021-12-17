package com.epam.passwordManagerMVC.dao;

import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RecordDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Records;
import com.epam.passwordManagerMVC.exception.*;

import java.util.List;

public interface RecordDao {
    Records addRecord(RecordDTO recordDTO) throws UnableToAddRecord, RecordAlreadyExistsException;

    Records findRecordBasedOnUrl(String url) throws NoRecordFoundForAccountBasedOnUrl;

    List<Records> findAllRecords() throws NoRecordFoundForAccount;

    Records updateRecord(RecordDTO recordDTO) throws UnableToUpdateRecord, NoRecordFoundForAccountBasedOnUrl;

    Records deleteRecordBasedOnUrl(String url) throws NoRecordFoundForAccountBasedOnUrl;

    void setAccount(LoginDTO loginDTO);

    Account getAccount();
}
