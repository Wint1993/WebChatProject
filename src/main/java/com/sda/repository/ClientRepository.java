package com.sda.repository;

/**
 * Created by Błażej on 2017-07-26.
 */

import com.sda.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findOneByUuid(String uuid);
    //Client findAllByName(String name);
    //Page<Client> findAllByName(String name, Pageable pageRequest);

}
