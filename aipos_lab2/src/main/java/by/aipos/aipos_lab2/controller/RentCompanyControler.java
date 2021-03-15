package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.RentCompany;
import by.aipos.aipos_lab2.service.RentCompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RentCompanyControler {
    @Autowired
    RentCompanyServiceImpl rentCompanyService;

    @GetMapping(value = "/rentCompanies")
    public ResponseEntity<List<RentCompany>> allRentCompanies(){
        return new ResponseEntity(rentCompanyService.getAllRentCompanies(), HttpStatus.OK);
    }

    @PostMapping(value = "/rentCompany")
    public ResponseEntity<?> addRentCompany(@RequestBody RentCompany rentCompany){
        rentCompanyService.addRentCompany(rentCompany);
        return new ResponseEntity(rentCompanyService.getAllRentCompanies(), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/rentCompany/{id}")
    public ResponseEntity<?> dropRentCompanyById(@PathVariable(name = "id") int id){
        rentCompanyService.dropRentCompanyById(id);
        return new ResponseEntity(rentCompanyService.getAllRentCompanies(), HttpStatus.OK);
    }
}
