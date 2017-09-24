package com.sda.mapper;

import com.sda.dto.MessageDTO;
import com.sda.model.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring", uses = {ClientMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MessageMapper {

    @Mappings({
            @Mapping(source = "from", target = "clientFrom"),
            @Mapping(source = "to", target = "clientTo")
    })
    Message toMessage(MessageDTO messageDTO);

    @Mappings({
            @Mapping(source = "clientFrom", target = "from"),
            @Mapping(source = "clientTo", target = "to")
    })
    MessageDTO toMessageDTO(Message message);

    List<Message> toMessageList(List<MessageDTO> list);
    List<MessageDTO> toMessageDTOList(List<Message> list);

}
