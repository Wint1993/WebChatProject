package com.sda.service.impl;

import com.sda.dto.MessageDTO;
import com.sda.mapper.MessageMapper;
import com.sda.model.Client;
import com.sda.model.Message;
import com.sda.repository.ClientRepository;
import com.sda.repository.MessageRepository;
import com.sda.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
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



    @Override
    public List<MessageDTO> findAllPaginated(Pageable pageable){
        List<Message> content = messageRepository.findAll(pageable).getContent();
        return messageMapper.toMessageDTOList(content);
    }


    //   @Transactional
//    @Override
   /// public Page<MessageDTO> findAllPaginated(Pageable pageable) {
       // List<Message> messages = messageRepository.findAll(new PageRequest(page, size)).getContent();
       // return (List)messageMapper.toMessageDTOList(messages);
       // return messageMapper.toMessageDTOList(messageRepository.findAll(pageable));
   // }





}
