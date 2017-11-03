package com.sda.mapper;

import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {

    @Mappings({
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email1", target = "email1"),
            @Mapping(source = "login", target = "login"),

    })
    ClientDTO toClientDTO(Client client);

    @Mappings({
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "email1", target = "email1"),
            @Mapping(source = "login", target = "login"),

    })
    Client toClient(ClientDTO clientDTO);

    List<ClientDTO> toClientDTOList(List<Client> clients);
    List<Client> toClientList(List<ClientDTO> clientsDTOS);
}
