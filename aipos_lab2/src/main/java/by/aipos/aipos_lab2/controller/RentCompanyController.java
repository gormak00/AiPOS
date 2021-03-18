package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.RentCompanyDto;
import by.aipos.aipos_lab2.dto.RentCompanyMapper;
import by.aipos.aipos_lab2.model.RentCompany;
import by.aipos.aipos_lab2.service.RentCompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentCompanyController {
    @Autowired
    RentCompanyServiceImpl rentCompanyService;

    @GetMapping(value = "/rentCompanies")
    public ResponseEntity<List<RentCompany>> allRentCompanies() {
        return new ResponseEntity(rentCompanyService.getAllRentCompanies(), HttpStatus.OK);
    }

    @GetMapping(value = "/rentCompany/{id}")
    public ResponseEntity<List<RentCompany>> getRentCompanyById(@PathVariable(name = "id") int id) {
        return new ResponseEntity(rentCompanyService.getRentCompanyById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/rentCompany")
    public String addRentCompany(@RequestBody RentCompanyDto rentCompanyDto, Model model) {
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        model.addAttribute("rentCompany", rentCompanyService.addRentCompany(rentCompany));
        return "rentCompanyAddPage";
    }

    @DeleteMapping(value = "/rentCompany/{id}")
    public ResponseEntity<?> dropRentCompanyById(@PathVariable(name = "id") int id) {
        rentCompanyService.dropRentCompanyById(id);
        return new ResponseEntity(rentCompanyService.getAllRentCompanies(), HttpStatus.OK);
    }
}
