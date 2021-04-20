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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class ClientController {

    @GetMapping(value = "/clients")
    public String allUsers(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Client>> response = restTemplate.exchange("http://server:8080/clients",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Client>>() {});
        List<Client> allClients = response.getBody();
        model.addAttribute("clients", allClients);
        model.addAttribute("clientDto", new ClientDto());
        return "start/clientListPage";
    }

    @GetMapping(value = "/client")
    public String getClientById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> response = restTemplate.exchange("http://server:8080/client?id=" + id,
                HttpMethod.GET, null, Client.class);
        Client client = response.getBody();
        model.addAttribute("client", client);
        return "final/clientGetPageFinal";
    }

    @GetMapping(value = "/addClient")
    public String showAddPersonPage(Model model) {
        model.addAttribute("clientDto", new ClientDto());
        return "start/clientAddPage";
    }

    @PostMapping(value = "/client")
    public String addClient(ClientDto clientDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> response = restTemplate.exchange("http://server:8080/client",
                HttpMethod.POST, new HttpEntity<ClientDto>(clientDto), Client.class);
        Client client = response.getBody();
        model.addAttribute("client", client);
        return "final/clientAddPageFinal";
    }

    @PostMapping(value = "/deleteClient")
    public String dropClientById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> response = restTemplate.exchange("http://server:8080/deleteClient?id=" + id,
                HttpMethod.POST, null, Client.class);
        Client client = response.getBody();
        model.addAttribute("client", client);
        return "final/clientDropByIdPageFinal";
    }

    @PostMapping(value = "/updateClient")
    public String updateClientById(@RequestParam(value = "id", required = true) int id, ClientDto clientDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Client> response = restTemplate.exchange("http://server:8080/updateClient?id=" + id,
                HttpMethod.POST, new HttpEntity<ClientDto>(clientDto), Client.class);
        Client client = response.getBody();
        model.addAttribute("client", client);
        return "final/clientUpdatePageFinal";
    }
}
