/*
package com.example.Html_server.controller;

import by.aipos.aipos_lab2.dto.RentCompanyDto;
import by.aipos.aipos_lab2.dto.RentCompanyMapper;
import by.aipos.aipos_lab2.model.RentCompany;
import by.aipos.aipos_lab2.service.RentCompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class RentCompanyController {

    @GetMapping(value = "/rentCompanies")
    public String allRentCompanies(Model model) {
        model.addAttribute("rentCompanies", rentCompanyService.getAllRentCompanies());
        RentCompanyDto rentCompanyDto = new RentCompanyDto();
        model.addAttribute("rentCompanyDto", rentCompanyDto);
        return "start/rentCompanyListPage";
    }

    @GetMapping(value = "/rentCompany")
    public String getRentCompanyById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("rentCompany", rentCompanyService.getRentCompanyById(id));
        return "final/rentCompanyGetPageFinal";
    }

    @GetMapping(value = "/addRentCompany")
    public String showAddRentCompanyPage(Model model){
        RentCompanyDto rentCompanyDto = new RentCompanyDto();
        model.addAttribute("rentCompanyDto", rentCompanyDto);
        return "start/rentCompanyAddPage";
    }

    @PostMapping(value = "/rentCompany")
    public String addRentCompany(@Valid @ModelAttribute("rentCompanyDto") RentCompanyDto rentCompanyDto, Model model) {
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        model.addAttribute("rentCompany", rentCompanyService.addRentCompany(rentCompany));
        return "final/rentCompanyAddPageFinal";
    }

    @PostMapping(value = "/deleteRentCompany")
    public String dropRentCompanyById(@RequestParam(value = "id", required = true) int id, Model model) {
        model.addAttribute("rentCompany", rentCompanyService.getRentCompanyById(id));
        rentCompanyService.dropRentCompanyById(id);
        return "final/rentCompanyDropByIdPageFinal";
    }

    @PostMapping(value = "/updateRentCompany")
    public String updateRentCompaneById(@RequestParam(value = "id", required = true) int id, @Valid @ModelAttribute RentCompanyDto rentCompanyDto, Model model){
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        model.addAttribute("rentCompany", rentCompanyService.updateRentCompanyById(rentCompany, id));
        return "final/rentCompanyUpdatePageFinal";
    }
}
*/
