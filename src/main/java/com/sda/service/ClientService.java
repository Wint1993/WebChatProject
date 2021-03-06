package com.sda.service;


import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<ClientDTO> findAll();
    List<ClientDTO> findOne(String name);
    ClientDTO create(ClientDTO clientDTO);
    String getLoggedUserDetails();

    ClientDTO getProfil();
}
