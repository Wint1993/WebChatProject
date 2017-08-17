package com.sda.controller;

import com.sda.dto.ClientDTO;
import com.sda.dto.MessageDTO;
import com.sda.model.Message;
import com.sda.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.print.Pageable;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/create", method = POST)
    public MessageDTO create(@RequestBody MessageDTO messageDTO) {
        return messageService.create(messageDTO);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<MessageDTO> findAll() {
        LOG.info("Received request to all messages.");
        return messageService.findAll();
    }





}
