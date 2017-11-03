package com.sda.service.impl;

import com.sda.dto.ClientDTO;
import com.sda.dto.MessageDTO;
import com.sda.mapper.MessageMapper;
import com.sda.model.Client;
import com.sda.model.Message;
import com.sda.repository.ClientRepository;
import com.sda.repository.MessageRepository;
import com.sda.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
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

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public static void main(String[] args) {

        String yy = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(yy, formatter);
        String g = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime);
        System.out.println(yy);
    }

    @Override
    public MessageDTO create(MessageDTO messageDTO){

        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        Client from = clientRepository.findOneByUuid(uuid);
        Client to = clientRepository.findOneByUuid(messageDTO.getTo().getUuid());
        Message message = messageMapper.toMessage(messageDTO);
        message.setClientFrom(from);
        message.setClientTo(to);
        String date = LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
        message.setTime(dateTime);
        message.setTimeString(LocalDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
        return messageMapper.toMessageDTO(messageRepository.save(message));
    }

    @Override
    public List<MessageDTO> findAll() {

        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> listFrom = messageRepository.findByClientFrom_Uuid(uuid);
        List<Message> listTo = messageRepository.findByClientTo_Uuid(uuid);
        List<Message> newMessageList = new ArrayList<>();
        newMessageList.addAll(listFrom);
        newMessageList.addAll(listTo);
        return (List)messageMapper.toMessageDTOList(newMessageList);

    }

    @Override
    public List<MessageDTO> findAllTo(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> listTo = messageRepository.findByClientTo_Uuid(uuid);
        return (List)messageMapper.toMessageDTOList(listTo);
    }

    @Override
    public List<MessageDTO> findAllFrom(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> listFrom= messageRepository.findByClientFrom_Uuid(uuid);
        return (List)messageMapper.toMessageDTOList(listFrom);
    }











}
