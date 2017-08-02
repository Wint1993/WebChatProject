package com.sda.mapper;

import com.sda.dto.MessageDTO;
import com.sda.model.Message;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class})
public interface MessageMapper {

    Message toMessage(MessageDTO messageDTO);

    MessageDTO toMessageDTO(Message message);

    List<Message> toMessageList(List<MessageDTO> list);
    List<MessageDTO> toMessageDTOList(List<Message> list);
}
