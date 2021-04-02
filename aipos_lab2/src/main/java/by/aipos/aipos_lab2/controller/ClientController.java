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

    @GetMapping(value = "/client")
    public String showAddPersonPage(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "start/clientAddPage";
    }

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
    public String addClient(@Valid @ModelAttribute("clientDto") ClientDto clientDto, Model model) {
        Client client = ClientMapper.toClient(clientDto);
        model.addAttribute("client", clientService.addClient(client));
        //model.addAttribute("errorMessage", errorMessage);
        return "final/clientAddPageFinal";
    }

    @DeleteMapping(value = "/client/{id}")
    public ResponseEntity<?> dropClientById(@PathVariable(name = "id") int id) {
        clientService.dropClientById(id);
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }

    @PutMapping(value = "/client/{id}")
    public String updateClientById(@PathVariable(name = "id") int id, @Valid @RequestBody ClientDto clientDto, Model model) {
        Client client = ClientMapper.toClient(clientDto);
        model.addAttribute("client", clientService.updateClientById(client, id));
        //return new ResponseEntity(clientService.updateClientById(client, id), HttpStatus.OK);
        return "clientUpdatePage";
    }
}
