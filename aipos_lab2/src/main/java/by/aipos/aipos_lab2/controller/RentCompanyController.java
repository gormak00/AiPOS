package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.dto.RentCompanyDto;
import by.aipos.aipos_lab2.dto.RentCompanyMapper;
import by.aipos.aipos_lab2.model.RentCompany;
import by.aipos.aipos_lab2.service.RentCompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class RentCompanyController {
    @Autowired
    RentCompanyServiceImpl rentCompanyService;

    @GetMapping(value = "/rentCompanies")
    public List<RentCompany> allRentCompanies() {
        return rentCompanyService.getAllRentCompanies();
    }

    @GetMapping(value = "/rentCompany/{id}")
    public RentCompany getRentCompanyById(@PathVariable (value = "id") int id) {
        return rentCompanyService.getRentCompanyById(id);
    }

    @PostMapping(value = "/rentCompany")
    public RentCompany addRentCompany(@RequestBody RentCompanyDto rentCompanyDto) {
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        RentCompany rentCompany1 = rentCompanyService.addRentCompany(rentCompany);
        return rentCompanyService.getRentCompanyById(rentCompany1.getId());
    }

    @DeleteMapping(value = "/deleteRentCompany/{id}")
    public RentCompany dropRentCompanyById(@PathVariable (value = "id") int id) {
        RentCompany rentCompany = rentCompanyService.getRentCompanyById(id);
        rentCompanyService.dropRentCompanyById(id);
        return rentCompany;
    }

    @PutMapping(value = "/updateRentCompany/{id}")
    public RentCompany updateRentCompanyById(@PathVariable (value = "id") int id, @RequestBody RentCompanyDto rentCompanyDto){
        RentCompany rentCompany = RentCompanyMapper.toRentCompany(rentCompanyDto);
        return rentCompanyService.updateRentCompanyById(rentCompany, id);
    }
}
