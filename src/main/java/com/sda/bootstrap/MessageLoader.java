package com.sda.bootstrap;

import com.sda.model.Client;
import com.sda.model.Message;
import com.sda.repository.ClientRepository;
import com.sda.repository.MessageRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class MessageLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ClientRepository clientRepository;

    private Logger log = Logger.getLogger(MessageLoader.class);

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event){


        Client client = new Client();
        client.setFirstName("Blazej");
        clientRepository.save(client);

        Client client1 = new Client();
        client1.setFirstName("Piotr");
        clientRepository.save(client1);

        Message message = new Message();
        message.setId(1l);
        message.setMessage("Siema");
        message.setClientTo(client);
        message.setClientFrom(client1);
        messageRepository.save(message);


        log.info("Saved message - id" + message.getId());


    }
}
