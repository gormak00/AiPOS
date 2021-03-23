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
    public RentCompany addRentCompany(RentCompany rentCompany) {
        List<RentCompany> rentCompaniesFromRepository = rentCompanyRepository.findAll();
        if (rentCompaniesFromRepository.size() == 0) rentCompany.setId(1);
        else rentCompany.setId(rentCompaniesFromRepository.get(rentCompaniesFromRepository.size() - 1).getId() + 1);

        return rentCompanyRepository.save(rentCompany);
    }

    @Override
    public RentCompany getRentCompanyById(int id) {
        return rentCompanyRepository.findById(id).get();
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
