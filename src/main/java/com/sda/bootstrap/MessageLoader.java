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
        client.setUuid("1");
        client.setLastName("Rejnowski");
        clientRepository.save(client);

        Client client1 = new Client();
        client1.setFirstName("Piotr");
        client1.setUuid("2");
        client1.setLastName("Rejnowski");
        clientRepository.save(client1);

        Message message = new Message();
        message.setId(1l);
        message.setMessage("Siema");
        message.setClientTo(client);
        message.setClientFrom(client1);
        messageRepository.save(message);

        Message message1 = new Message();
        message1.setId(2l);
        message1.setMessage("Elo");
        message1.setClientFrom(client);
        message1.setClientTo(client1);
        messageRepository.save(message1);


        Message message2 = new Message();
        message2.setId(3l);
        message2.setMessage("Czesc");
        message2.setClientFrom(client);
        message2.setClientTo(client1);
        messageRepository.save(message2);


        Message message3 = new Message();
        message3.setId(4l);
        message3.setMessage("Siemandero kuzyn");
        message3.setClientFrom(client);
        message3.setClientTo(client1);
        messageRepository.save(message3);


        Message message4 = new Message();
        message4.setId(5l);
        message4.setMessage("Co tam?");
        message4.setClientFrom(client);
        message4.setClientTo(client1);
        messageRepository.save(message4);


        Message message5 = new Message();
        message5.setId(6l);
        message5.setMessage("Witaj");
        message5.setClientFrom(client);
        message5.setClientTo(client1);
        messageRepository.save(message1);


        Message message6 = new Message();
        message6.setId(7l);
        message6.setMessage("Czesc");
        message6.setClientFrom(client);
        message6.setClientTo(client1);
        messageRepository.save(message6);



        log.info("Saved message - id" + message.getId());


    }
}
