package com.sda.service.impl;

import com.sda.dto.ClientDTO;

import com.sda.dto.MessageDTO;
import com.sda.mapper.ClientMapper;
import com.sda.model.Client;
import com.sda.model.Message;
import com.sda.repository.ClientRepository;
import com.sda.service.ClientService;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {


    private HibernateTemplate hibernateTemplate;

   /* public void setSessionFactory(SessionFactory sessionFactory){
        this.hibernateTemplate = new HibernateTemplate(sessionFactory);
    }*/



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

    }

    @Override
    public ClientDTO create(ClientDTO clientDTO){

        Client matchingLogin = clientRepository.findByLogin(clientDTO.getLogin());
        if(matchingLogin != null){
            throw new RuntimeException("Login already in use");
        }
        Client matchingEmail = clientRepository.findByEmail1(clientDTO.getEmail1());
        if(matchingEmail != null){
            throw new RuntimeException("Email already in use");
        }

        Client client = clientMapper.toClient(clientDTO);
        client.setUuid(UUID.randomUUID().toString());
        return clientMapper.toClientDTO(clientRepository.save(client));
    }

    @Override
    public String getLoggedUserDetails() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();

    }

    @Override
    public ClientDTO getProfil(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        return clientMapper.toClientDTO(clientRepository.findOneByUuid(uuid));
    }

/*    public void addFriend (ClientDTO clientDTO){

        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        Client logged = clientRepository.findOneByUuid(uuid);
        List<ClientDTO> friends = logged.getAddedFriends();
        friends.add(clientDTO);

    }*/

    public List<ClientDTO> findAllFriends(){
        String uuid = SecurityContextHolder.getContext().getAuthentication().getName();
        return clientRepository.findAllAddedFriendsByUuid(uuid);
    }

}


    /*@Override
    public void saveClient(Client client) {
        hibernateTemplate.saveOrUpdate(client);
    }
*/


