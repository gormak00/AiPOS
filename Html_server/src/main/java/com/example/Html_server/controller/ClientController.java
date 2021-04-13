package com.example.Html_server.controller;


import com.example.Html_server.dto.ClientDto;
import com.example.Html_server.model.Client;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class ClientController {

    /*@GetMapping(value = "/clients")
    public String allUsers(Model model) {
        model.addAttribute("clients", clientService.getAllClients());
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "start/clientListPage";
    }*/

    /*@GetMapping(value = "/client")
    public String getClientById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        return "final/clientGetPageFinal";
    }*/

    @GetMapping(value = "/addClient")
    public String showAddPersonPage(Model model) {
        ClientDto clientDto = new ClientDto();
        model.addAttribute("clientDto", clientDto);
        return "start/clientAddPage";
        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDto> response = restTemplate.exchange("http://localhost:8080/addClient",
                HttpMethod.GET, null, new ParameterizedTypeReference<ClientDto>() {});
        ClientDto clientDto = response.getBody();
        model.addAttribute("clientDto", clientDto);
        return "start/clientAddPage";*/
    }

    @PostMapping(value = "/client")
    public String addClient(ClientDto clientDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ClientDto> response = restTemplate.exchange("http://localhost:8080/client",
                HttpMethod.POST, new HttpEntity<ClientDto>(clientDto), ClientDto.class/*new ParameterizedTypeReference<ClientDto>() {}*/);
        //Client client = ClientMapper.toClient(clientDto);
        ClientDto clientDto1 = response.getBody();
        Client client = new Client(0, clientDto.getName(), clientDto.getPhoneNumber());
        //Client client = response.getBody();
        model.addAttribute("client", client);
        //model.addAttribute("errorMessage", errorMessage);
        return "final/clientAddPageFinal";
    }

    /*@PostMapping(value = "/deleteClient")
    public String dropClientById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("client", clientService.getClientById(id));
        clientService.dropClientById(id);
        return "final/clientDropByIdPageFinal";
    }

    @PostMapping(value = "/updateClient")
    public String updateClientById(@RequestParam(value = "id", required = true) int id, @Valid @ModelAttribute("clientDto") ClientDto clientDto, Model model) {
        Client client = ClientMapper.toClient(clientDto);
        model.addAttribute("client", clientService.updateClientById(client, id));
        return "final/clientUpdatePageFinal";
    }*/
}
