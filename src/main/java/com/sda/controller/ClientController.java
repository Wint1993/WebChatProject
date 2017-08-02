package com.sda.controller;

import com.sda.dto.ClientDTO;
import com.sda.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Błażej on 2017-07-26.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/create", method = POST)
    public ClientDTO create(@RequestBody ClientDTO clientDTO){
        return clientService.create(clientDTO);
    }

    @RequestMapping(value ="/all", method = GET)
    public List<ClientDTO> findAll(){
        return clientService.findAll();
    }
}
