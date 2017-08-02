package com.sda.repository;

/**
 * Created by Błażej on 2017-07-26.
 */

import com.sda.dto.ClientDTO;
import com.sda.model.Client;
import com.sun.xml.internal.bind.v2.TODO;
import org.h2.store.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    Client findOneByUuid(String uuid);
    List<Client> findBySearch(@Param("searchTerm") String searchTerm, Pageable pageRequest);

}
