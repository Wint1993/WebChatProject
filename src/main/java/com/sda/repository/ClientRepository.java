package com.sda.repository;

/**
 * Created by Błażej on 2017-07-26.
 */

import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findByLogin(String login);
    Client findByEmail1( String email);
    Client findOneByUuid(String uuid);
    List<ClientDTO> findAllAddedFriendsByUuid(String uuid);

}
