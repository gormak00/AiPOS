package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.RentCompanyDto;
import by.aipos.aipos_lab2.dto.RentCompanyMapper;
import by.aipos.aipos_lab2.model.RentCompany;
import by.aipos.aipos_lab2.service.RentCompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RentCompanyController {
    @Autowired
    RentCompanyServiceImpl rentCompanyService;

    @GetMapping(value = "/rentCompanies")
    public List<RentCompany> allRentCompanies() {
        return rentCompanyService.getAllRentCompanies();
    }

    @GetMapping(value = "/rentCompany")
    public RentCompany getRentCompanyById(@RequestParam(value = "id", required = true) int id) {
        return rentCompanyService.getRentCompanyById(id);
    }

    @PostMapping(value = "/rentCompany")
    public RentCompany addRentCompany(@RequestBody RentCompanyDto rentCompanyDto) {
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        RentCompany rentCompany1 = rentCompanyService.addRentCompany(rentCompany);
        return rentCompanyService.getRentCompanyById(rentCompany1.getId());
    }

    @PostMapping(value = "/deleteRentCompany")
    public RentCompany dropRentCompanyById(@RequestParam(value = "id", required = true) int id) {
        RentCompany rentCompany = rentCompanyService.getRentCompanyById(id);
        rentCompanyService.dropRentCompanyById(id);
        return rentCompany;
    }

    @PostMapping(value = "/updateRentCompany")
    public RentCompany updateRentCompanyById(@RequestParam(value = "id", required = true) int id, @RequestBody RentCompanyDto rentCompanyDto){
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        return rentCompanyService.updateRentCompanyById(rentCompany, id);
    }
}
