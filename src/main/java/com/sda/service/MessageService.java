package com.sda.service;

import com.sda.dto.MessageDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService {

    MessageDTO create(MessageDTO messageDTO);
    List<MessageDTO> findAll();
}
