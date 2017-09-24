import com.sda.controller.ClientController;
import com.sda.dto.ClientDTO;
import com.sda.mapper.ClientMapper;
import com.sda.model.Client;
import com.sda.service.ClientService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class TestA {


    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private ClientController clientController;

    @Test
    public void finByName(){

    }

    public static void main(String[] args) {

        List<String> lista = new ArrayList<String>();
        lista.add("Asia");
        lista.add("Basia");
        lista.add("Krzysiek");
        lista.add("Wojtek");

        String name = "ania";
        lista.stream().filter(c-> c.equals(name))
                .peek(System.out::println)
                .collect(Collectors.toList());
    }



}
