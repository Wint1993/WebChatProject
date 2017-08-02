package com.sda.mapper;

import com.sda.dto.MessageDTO;
import com.sda.model.Message;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2017-08-01T18:12:38+0200",
    comments = "version: 1.2.0.Beta3, compiler: javac, environment: Java 1.8.0_112 (Oracle Corporation)"
)
@Component
public class MessageMapperImpl implements MessageMapper {

    @Override
    public Message toMessage(MessageDTO messageDTO) {
        if ( messageDTO == null ) {
            return null;
        }

        Message message = new Message();

        message.setId( messageDTO.getId() );
        message.setMessage( messageDTO.getMessage() );

        return message;
    }

    @Override
    public MessageDTO toMessageDTO(Message message) {
        if ( message == null ) {
            return null;
        }

        MessageDTO messageDTO = new MessageDTO();

        messageDTO.setId( message.getId() );
        messageDTO.setMessage( message.getMessage() );

        return messageDTO;
    }

    @Override
    public List<Message> toMessageList(List<MessageDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<Message> list1 = new ArrayList<Message>( list.size() );
        for ( MessageDTO messageDTO : list ) {
            list1.add( toMessage( messageDTO ) );
        }

        return list1;
    }

    @Override
    public List<MessageDTO> toMessageDTOList(List<Message> list) {
        if ( list == null ) {
            return null;
        }

        List<MessageDTO> list1 = new ArrayList<MessageDTO>( list.size() );
        for ( Message message : list ) {
            list1.add( toMessageDTO( message ) );
        }

        return list1;
    }
}
