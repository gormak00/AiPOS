package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.ClientDto;
import by.aipos.aipos_lab2.dto.ClientMapper;
import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String addClient(@Valid @RequestBody ClientDto clientDto, Model model) {
        Client client = ClientMapper.toClient(clientDto);
        model.addAttribute("client", clientService.addClient(client));
        return "clientWelcomePage";
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<?> dropClientById(@PathVariable(name = "id") int id) {
        clientService.dropClientById(id);
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }
}
