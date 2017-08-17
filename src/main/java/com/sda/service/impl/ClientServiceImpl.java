package com.sda.service.impl;

import com.sda.dto.ClientDTO;
import com.sda.dto.MessageDTO;
import com.sda.mapper.ClientMapper;
import com.sda.model.Client;
import com.sda.repository.ClientRepository;
import com.sda.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public List<ClientDTO> findAll() {
        return clientMapper.toClientDTOList(clientRepository.findAll()); }

    @Override
    public List<ClientDTO> findOne(String name){
        List<ClientDTO> list = clientMapper.toClientDTOList(clientRepository.findAll());
        return list.stream().filter(x->x.getFirstName().equals(name)).collect(Collectors.toList());
        //return list.stream().filter(c-> name.equals(c.getFirstName())).collect(Collectors.toList());
    }

    @Override
    public ClientDTO create(ClientDTO clientDTO){

        Client newClient = clientMapper.toClient(clientDTO);
        newClient.setUuid(UUID.randomUUID().toString());
        return clientMapper.toClientDTO(clientRepository.save(newClient));
    }

    @Override
    public Long countMembers(){
        return clientRepository.count();
    }

    @Override
    public Page<Client> listAllByPage(Pageable pageable){
        return clientRepository.findAll(pageable);
    }

 /*   @Override
    public List<ClientDTO> findAll(int firstResult, int maxResult){
        TypedQuery<ClientDTO> query = ;
    }*/

  /*  @Override
    public Page<ClientDTO> clientDTOPageable(Pageable pageable){
        return clientMapper.toClientDTOList(clientRepository.findAll(pageable));
    }*/


  /*  @Override
    public List<ClientDTO> findBySearchName(String searchTerm, Pageable pageRequest){
        Page<Client> search = clientRepository.findAllByName(searchTerm,pageRequest);
        return search.getContent().stream().map(c -> clientMapper.toClientDTO(c)).collect(Collectors.toList());
    }*/

}
