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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class ClientController {

    @Autowired
    ClientServiceImpl clientService;

    @GetMapping(value = "/clients")
    public String allUsers(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "start/clientListPage";
    }

    @GetMapping(value = "/client")
    public String getClientById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "final/clientGetPageFinal";
    }

    @GetMapping(value = "/addClient")
    public String showAddPersonPage(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "start/clientAddPage";
    }

    @PostMapping(value = "/client")
    public String addClient(@Valid @ModelAttribute("clientDto") ClientDto clientDto, Model model) {
        Client client = ClientMapper.toClient(clientDto);
        model.addAttribute("client", clientService.addClient(client));
        //model.addAttribute("errorMessage", errorMessage);
        return "final/clientAddPageFinal";
    }

    @PostMapping(value = "/deleteClient")
    public ResponseEntity<?> dropClientById(@RequestParam(value = "id", required = true) int id) {
        clientService.dropClientById(id);
        return new ResponseEntity(clientService.getAllClients(), HttpStatus.OK);
    }

    @PostMapping(value = "/updateClient")
    public String updateClientById(@RequestParam(value = "id", required = true) int id, @Valid @ModelAttribute("clientDto") ClientDto clientDto, Model model) {
        Client client = ClientMapper.toClient(clientDto);
        model.addAttribute("client", clientService.updateClientById(client, id));
        //return new ResponseEntity(clientService.updateClientById(client, id), HttpStatus.OK);
        return "final/clientUpdatePageFinal";
    }
}
