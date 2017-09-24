package com.sda.repository;


import com.sda.dto.MessageDTO;
import com.sda.model.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {




   // List<MessageDTO> findByClientUuid(String uuid);
}
