package com.sda.service;


import com.sda.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientDTO> findAll();
    ClientDTO findOne(String uuid);
    ClientDTO create(ClientDTO clientDTO);
}
