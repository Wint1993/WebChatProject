package com.sda.service.impl;

import com.sda.dto.MessageDTO;
import com.sda.mapper.MessageMapper;
import com.sda.model.Client;
import com.sda.model.Message;
import com.sda.repository.ClientRepository;
import com.sda.repository.MessageRepository;
import com.sda.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {


    @Autowired
    private MessageMapper messageMapper;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public MessageDTO create(MessageDTO messageDTO){

        Client from = clientRepository.findOneByUuid(messageDTO.getFrom().getUuid());
        Client to = clientRepository.findOneByUuid(messageDTO.getTo().getUuid());
        Message message = messageMapper.toMessage(messageDTO);
        message.setClientFrom(from);
        message.setClientTo(to);
        return messageMapper.toMessageDTO(messageRepository.save(message));
    }

    @Override
    public List<MessageDTO> findAll() {
        return (List)messageMapper.toMessageDTOList(messageRepository.findAll());
    }

   /* @Override
    public List<MessageDTO> findAllMessagesForUser(){
        return messageMapper.toMessageDTOList(messageRepository.findByClientUuid(LoggedUserUtils.getLoggedUser().getUuid()))

    }*/

    /*@Override
    public Page<Message> findAll(Pageable pageable) {
        //return new PageImpl<MessageDTO>(messageMapper.toMessageDTOList(messageRepository.findAll()));
        return messageRepository.findAll(pageable);
    }*/

}
