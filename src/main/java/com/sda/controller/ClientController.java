package com.sda.controller;

import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import com.sda.model.Message;
import com.sda.repository.ClientRepository;
import com.sda.service.ClientService;
import com.sda.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by Błażej on 2017-07-26.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {


   /* @Autowired
    public void setClientService(ClientService clientService){this.clientService = clientService;} */

    @Autowired
    private ClientService clientService;

   // @PutMapping("/register")
    @RequestMapping(value = "/register", method = POST,headers = "content-type=application/x-www-form-urlencoded")
    public ClientDTO create(ClientDTO clientDTO){
        return clientService.create(clientDTO);
    }

    @RequestMapping(value = "/findOne", method = GET)
    public List<ClientDTO> findOne(@RequestBody String name){
        return clientService.findOne(name);
    }

    @RequestMapping(value ="/all", method = GET)
    public List<ClientDTO> findAll(){
        return clientService.findAll();
    }

    @RequestMapping(value ="/profil", method = GET)
    public ClientDTO findMyProfil(){
        return clientService.getProfil();
    }



    @GetMapping("/me")
    public String getLoggedUserDetails() {
        return clientService.getLoggedUserDetails();
    }









}
