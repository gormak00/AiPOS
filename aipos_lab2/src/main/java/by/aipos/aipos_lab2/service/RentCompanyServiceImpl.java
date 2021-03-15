package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.RentCompany;
import by.aipos.aipos_lab2.repository.RentCompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentCompanyServiceImpl implements RentCompanyService {

    @Autowired
    private RentCompanyRepository rentCompanyRepository;

    @Override
    public void addRentCompany(RentCompany rentCompany) {
        int count = rentCompanyRepository.findAll().size();
        if(count==0) rentCompany.setId(1);
            else rentCompany.setId(count +1);
        rentCompanyRepository.save(rentCompany);
    }

    @Override
    public List<RentCompany> getAllRentCompanies() {
        return rentCompanyRepository.findAll();
    }

    @Override
    public void dropRentCompanyById(int id) {
        rentCompanyRepository.deleteById(id);
    }
}
