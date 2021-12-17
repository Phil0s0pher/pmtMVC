package com.epam.passwordManagerMVC.dao;

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
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecordDaoTest {
    @InjectMocks
    RecordDaoImpl recordDaoImpl;

    @Mock
    RecordRepository recordRepository;

    @Mock
    GroupRepository groupRepository;

    @Test
    void testAddRecordThrowExceptionWhenRecordAlreadyExists() {
        RecordDTO recordDTO = new RecordDTO("KGR009517", "Tiger1234", "http://www.epam.com", "first record");
        when(recordRepository.existsByUrlAndAccount(any(), any())).thenReturn(true);
        Assertions.assertThrows(RecordAlreadyExistsException.class, () -> recordDaoImpl.addRecord(recordDTO));
    }

    @Test
    void testAddRecordDoesNotThrowExceptionWhileNewRecordInsertion() {
        RecordDTO recordDTO = new RecordDTO("KGR009517", "Vishi834019", "http://www.epam.com", "first record");
        Records record = new Records();
        Group existGroup = new Group();
        when(recordRepository.save(any())).thenReturn(record);
        when(groupRepository.findByNameAndAccount(any(), any())).thenReturn(existGroup);
        when(recordRepository.existsByUrlAndAccount(any(), any())).thenReturn(false);
        Assertions.assertDoesNotThrow(() -> recordDaoImpl.addRecord(recordDTO));
    }

    @Test
    void findRecordBasedOnUrlThrowExceptionWhenNoRecordFoundForProvidedUrl() {
        String url = "http://www.master.com";
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(null);
        Assertions.assertThrows(NoRecordFoundForAccountBasedOnUrl.class, () -> recordDaoImpl.findRecordBasedOnUrl(url));
    }

    @Test
    void findRecordBasedOnUrlDoesNotThrowExceptionWhileRecordFoundForProvidedUrl() {
        String url = "http://www.master.com";
        Records record = new Records();
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(record);
        Assertions.assertDoesNotThrow(() -> recordDaoImpl.findRecordBasedOnUrl(url));
    }

    @Test
    void findRecordBasedOnUrlReturnRecordWhileProvidedUrlExistsWithAccount() throws NoRecordFoundForAccountBasedOnUrl {
        String url = "http://www.master.com";
        Records record = new Records();
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(record);
        Assertions.assertEquals(record, recordDaoImpl.findRecordBasedOnUrl(url));
    }

    @Test
    void findAllRecordsThrowExceptionWhenNoRecordFoundForAccount() {
        when(recordRepository.findByAccount(any())).thenReturn(null);
        Assertions.assertThrows(NoRecordFoundForAccount.class, () -> recordDaoImpl.findAllRecords());
    }

    @Test
    void findAllRecordsDoesNotThrowExceptionWhileRecordFoundForAccount() {
        List<Records> recordList = new ArrayList<>();
        when(recordRepository.findByAccount(any())).thenReturn(recordList);
        Assertions.assertDoesNotThrow(() -> recordDaoImpl.findAllRecords());
    }

    @Test
    void testUpdateRecordThrowExceptionWhenNoRecordFoundForAccountBasedOnUrl() {
        RecordDTO recordDTO = new RecordDTO("KGR009517", "Vishi834019", "http://www.epam.com", "first record");
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(null);
        Assertions.assertThrows(NoRecordFoundForAccountBasedOnUrl.class, () -> recordDaoImpl.updateRecord(recordDTO));
    }

    @Test
    void updateRecordDoesNotThrowExceptionWhileRecordFoundForAccountBasedOnUrl() {
        RecordDTO recordDTO = new RecordDTO("KGR009517", "Vishi834019", "http://www.epam.com", "first record");
        Records record = new Records();
        when(recordRepository.save(any())).thenReturn(record);
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(record);
        Assertions.assertDoesNotThrow(() -> recordDaoImpl.updateRecord(recordDTO));
    }

    @Test
    void deleteRecordBasedOnUrlThrowExceptionWhenNoRecordFoundForAccountBasedOnUrl() {
        String url = "http://www.master.com";
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(null);
        Assertions.assertThrows(NoRecordFoundForAccountBasedOnUrl.class, () -> recordDaoImpl.deleteRecordBasedOnUrl(url));
    }

    @Test
    void deleteRecordBasedOnUrlDoesNotThrowExceptionWhileRecordFoundForAccountBasedOnUrl() {
        String url = "http://www.master.com";
        Records record = new Records();
        when(recordRepository.findByUrlAndAccount(any(), any())).thenReturn(record);
        Assertions.assertDoesNotThrow(() -> recordDaoImpl.deleteRecordBasedOnUrl(url));
    }

    @Test
    void testAccount() {
        LoginDTO loginDTO = new LoginDTO("KGR009517", "Tiger1234");
        recordDaoImpl.setAccount(loginDTO);
        Account account = new Account("KGR009517", "Tiger1234");
        Assertions.assertEquals(account.getUserName(), recordDaoImpl.getAccount().getUserName());
    }
}
