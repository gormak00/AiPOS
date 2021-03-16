package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> allUsers() {
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }

    @GetMapping(value = "/client/{id}")
    public String getClientById(@PathVariable(name = "id") Integer id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "clientWelcomePage";
    }

    @PostMapping(value = "/client")
    public String addClient(@RequestBody Client client, Model model) {
        //clientService.addClient(client);
        model.addAttribute("client", clientService.addClient(client));
        return "clientWelcomePage";
        //return new ResponseEntity(clientService.getAllClients(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<?> dropClientById(@PathVariable(name = "id") int id){
        clientService.dropClientById(id);
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }
}
