package com.sda.mapper;

import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    ClientDTO toClientDTO(Client client);
    Client toClient(ClientDTO clientDTO);

    List<ClientDTO> toClientDTOList(List<Client> clients);
    List<Client> toClientList(List<ClientDTO> clientsDTOS);
}
