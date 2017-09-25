package com.sda.mapper;

import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-09-25T14:50:35+0200",
    comments = "version: 1.2.0.Beta3, compiler: javac, environment: Java 1.8.0_112 (Oracle Corporation)"
)
@Component
public class ClientMapperImpl implements ClientMapper {

    @Override
    public ClientDTO toClientDTO(Client client) {
        if ( client == null ) {
            return null;
        }

        ClientDTO clientDTO = new ClientDTO();

        clientDTO.setPassword( client.getPassword() );
        clientDTO.setLogin( client.getLogin() );
        clientDTO.setEmail( client.getEmail() );
        clientDTO.setUuid( client.getUuid() );
        clientDTO.setFirstName( client.getFirstName() );
        clientDTO.setLastName( client.getLastName() );

        return clientDTO;
    }

    @Override
    public Client toClient(ClientDTO clientDTO) {
        if ( clientDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setPassword( clientDTO.getPassword() );
        client.setLogin( clientDTO.getLogin() );
        client.setEmail( clientDTO.getEmail() );
        client.setFirstName( clientDTO.getFirstName() );
        client.setLastName( clientDTO.getLastName() );
        client.setUuid( clientDTO.getUuid() );

        return client;
    }

    @Override
    public List<ClientDTO> toClientDTOList(List<Client> clients) {
        if ( clients == null ) {
            return null;
        }

        List<ClientDTO> list = new ArrayList<ClientDTO>( clients.size() );
        for ( Client client : clients ) {
            list.add( toClientDTO( client ) );
        }

        return list;
    }

    @Override
    public List<Client> toClientList(List<ClientDTO> clientsDTOS) {
        if ( clientsDTOS == null ) {
            return null;
        }

        List<Client> list = new ArrayList<Client>( clientsDTOS.size() );
        for ( ClientDTO clientDTO : clientsDTOS ) {
            list.add( toClient( clientDTO ) );
        }

        return list;
    }
}
