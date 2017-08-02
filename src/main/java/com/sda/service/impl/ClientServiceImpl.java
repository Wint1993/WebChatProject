package com.sda.service.impl;

import com.sda.dto.ClientDTO;
import com.sda.mapper.ClientMapper;
import com.sda.model.Client;
import com.sda.repository.ClientRepository;
import com.sda.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() {
        return clientMapper.toClientDTOList(clientRepository.findAll()); }

    @Override
    public ClientDTO findOne(String uuid){
        return clientMapper.toClientDTO(clientRepository.findOneByUuid(uuid));
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO){

        Client newClient = clientMapper.toClient(clientDTO);
        newClient.setUuid(UUID.randomUUID().toString());
        return clientMapper.toClientDTO(clientRepository.save(newClient));

    }

}
