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
   // List<MessageDTO> findAllMessagesForUser();
   // Page<MessageDTO> findAllPaginated(Pageable pageable);
    //Page<Message> findAll(Pageable pageable);
   // Page<ClientDTO> findBySearchName(String searchTerm);

    // Page<MessageDTO> findAllPaginated(Pageable pageable);
    //Page<MessageDTO> findPaginated(int page, int size);

     List<MessageDTO> findAllPaginated(Pageable pageable);

}
