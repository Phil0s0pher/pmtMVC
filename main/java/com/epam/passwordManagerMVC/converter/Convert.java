package com.epam.passwordManagerMVC.converter;

import com.epam.passwordManagerMVC.dto.GroupDTO;
import com.epam.passwordManagerMVC.dto.LoginDTO;
import com.epam.passwordManagerMVC.dto.RecordDTO;
import com.epam.passwordManagerMVC.dto.RegisterDTO;
import com.epam.passwordManagerMVC.entity.Account;
import com.epam.passwordManagerMVC.entity.Group;
import com.epam.passwordManagerMVC.entity.Records;
import org.springframework.stereotype.Component;

@Component
public class  Convert {
    public static Account convertToEntity(RegisterDTO registerDTO) {
        return new Account(registerDTO.getAccountName(), registerDTO.getUserName(), registerDTO.getPassword());
    }

    public static Account convertToEntity(LoginDTO loginDTO) {
        return new Account(loginDTO.getUserName(), loginDTO.getPassword());
    }

    public static Records convertToEntity(RecordDTO recordDTO) {
        return new Records(recordDTO.getUserName(), recordDTO.getPassword(), recordDTO.getUrl(), recordDTO.getNotes());
    }

    public static Group convertToEntity(GroupDTO groupDTO) {
        return new Group(groupDTO.getId(), groupDTO.getName(), groupDTO.getDescription());
    }
}

