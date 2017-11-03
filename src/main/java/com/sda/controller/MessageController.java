package com.sda.controller;

import com.sda.dto.MessageDTO;
import com.sda.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

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

    @RequestMapping(value = "/allToMe", method = GET)
    public List<MessageDTO> findAllToMe() {
        LOG.info("Received request to all messages.");
        return messageService.findAllTo();
    }

    @RequestMapping(value = "/allFromMe", method = GET)
    public List<MessageDTO> findAllFromMe() {
        LOG.info("Received request to all messages.");
        return messageService.findAllFrom();
    }

}
