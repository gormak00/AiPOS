package com.example.Html_server.controller;


import com.example.Html_server.dto.RentCompanyDto;
import com.example.Html_server.model.RentCompany;
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
public class RentCompanyController {

    @GetMapping(value = "/rentCompanies")
    public String allRentCompanies(Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<RentCompany>> response = restTemplate.exchange("http://localhost:8080/rentCompanies",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<RentCompany>>() {});
        List<RentCompany> allRentCompanies = response.getBody();
        model.addAttribute("rentCompanies", allRentCompanies);
        model.addAttribute("rentCompanyDto", new RentCompanyDto());
        return "start/rentCompanyListPage";
    }

    @GetMapping(value = "/rentCompany")
    public String getRentCompanyById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RentCompany> response = restTemplate.exchange("http://localhost:8080/rentCompany?id=" + id,
                HttpMethod.GET, null, RentCompany.class);
        RentCompany rentCompany = response.getBody();
        model.addAttribute("rentCompany", rentCompany);
        return "final/rentCompanyGetPageFinal";
    }

    @GetMapping(value = "/addRentCompany")
    public String showAddRentCompanyPage(Model model){
        model.addAttribute("rentCompanyDto", new RentCompanyDto());
        return "start/rentCompanyAddPage";
    }

    @PostMapping(value = "/rentCompany")
    public String addRentCompany(RentCompanyDto rentCompanyDto, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RentCompany> response = restTemplate.exchange("http://localhost:8080/rentCompany",
                HttpMethod.POST, new HttpEntity<RentCompanyDto>(rentCompanyDto), RentCompany.class);
        RentCompany rentCompany = response.getBody();
        model.addAttribute("rentCompany", rentCompany);
        return "final/rentCompanyAddPageFinal";
    }

    @PostMapping(value = "/deleteRentCompany")
    public String dropRentCompanyById(@RequestParam(value = "id", required = true) int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RentCompany> response = restTemplate.exchange("http://localhost:8080/deleteRentCompany?id=" + id,
                HttpMethod.POST, null, RentCompany.class);
        RentCompany rentCompany = response.getBody();
        model.addAttribute("rentCompany", rentCompany);
        return "final/rentCompanyDropByIdPageFinal";
    }

    @PostMapping(value = "/updateRentCompany")
    public String updateRentCompanyById(@RequestParam(value = "id", required = true) int id, RentCompanyDto rentCompanyDto, Model model){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RentCompany> response = restTemplate.exchange("http://localhost:8080/updateRentCompany?id=" + id,
                HttpMethod.POST, new HttpEntity<RentCompanyDto>(rentCompanyDto), RentCompany.class);
        RentCompany rentCompany = response.getBody();
        model.addAttribute("rentCompany", rentCompany);
        return "final/rentCompanyUpdatePageFinal";
    }
}
