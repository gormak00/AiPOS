package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.ClientDto;
import by.aipos.aipos_lab2.dto.ClientMapper;
import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @GetMapping(value = "/clients")
    public List<Client> allUsers() {
        return clientService.getAllClients();
    }

    @GetMapping(value = "/client/{id}")
    public Client getClientById(@PathVariable (value = "id") int id) {
        return clientService.getClientById(id);
    }

    @PostMapping(value = "/client")
    public Client addClient(@RequestBody ClientDto clientDto) {
        Client client = ClientMapper.toClient(clientDto);
        Client client1 = clientService.addClient(client);
        return clientService.getClientById(client1.getId());
    }

    @DeleteMapping(value = "/deleteClient/{id}")
    public Client dropClientById(@PathVariable (value = "id") int id) {
        Client client = clientService.getClientById(id);
        clientService.dropClientById(id);
        return client;
    }

    @PutMapping(value = "/updateClient/{id}")
    public Client updateClientById(@PathVariable (value = "id") int id, @RequestBody ClientDto clientDto) {
        Client client = ClientMapper.toClient(clientDto);
        return clientService.updateClientById(client, id);
    }
}
