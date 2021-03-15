package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @GetMapping(value = "/clients")
    public ResponseEntity<List<Client>> allUsers() {
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping(value = "/client")
    public ResponseEntity<?> addClient(@RequestBody Client client) {
        clientService.addClient(client);
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.CREATED);
    }

    @PostMapping(value = "/client/{id}")
    public ResponseEntity<?> dropClientById(@PathVariable(name = "id") int id){
        clientService.dropClientById(id);
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }
}
