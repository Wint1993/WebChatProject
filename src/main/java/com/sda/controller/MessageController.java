package com.sda.controller;

import com.sda.dto.MessageDTO;
import com.sda.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/message")
public class MessageController {

    private final Logger LOG = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private MessageService messageService;

    @RequestMapping(value = "/create", method = POST)
    public MessageDTO create(@RequestBody MessageDTO messageDTO) {
        return messageService.create(messageDTO);
    }

    @RequestMapping(value = "/all", method = GET)
    public List<MessageDTO> findAll() {
        LOG.info("Received request to all messages.");
        return messageService.findAll();
    }

    //@GetMapping
  //  @ResponseBody
   /* @RequestMapping(value = "/page/{page}/size/{size}", method = GET)
    public Page<MessageDTO> findPaginated(@RequestParam Integer page, @RequestParam Integer size){
        return messageService.findAllPaginated(page,size);
    }*/
  /*  @RequestMapping(value = "/page/{page}/size/{size}", method = GET)
    public Page<MessageDTO> findPaginated(@PathVariable("page") int page, @PathVariable("size") int size){
        return messageService.findAllPaginated(new PageRequest(page - 1, size));
    }*/

   // @Path("/page")
    //@GetMapping
   @RequestMapping(value = "/page", method = GET)
   public List<MessageDTO> getAllPaginated(
            @QueryParam("sort") String sort,
            @QueryParam("page") Integer page,
            @QueryParam("size") Integer size) {
        Pageable pageRequest = new PageRequest(
                ((page == null) ? 0 : (page - 1)),
                ((size == null) ? 10 : size));
        return messageService.findAllPaginated(pageRequest);
    }
}
