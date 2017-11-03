package com.sda.service;

import com.sda.dto.ClientDTO;
import com.sda.dto.MessageDTO;
import com.sda.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    MessageDTO create(MessageDTO messageDTO);
    List<MessageDTO> findAll();
    List<MessageDTO> findAllTo();
    List<MessageDTO> findAllFrom();


}
