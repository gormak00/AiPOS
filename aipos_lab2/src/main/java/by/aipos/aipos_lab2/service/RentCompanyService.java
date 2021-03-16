package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.RentCompany;

import java.util.List;

public interface RentCompanyService {
    RentCompany addRentCompany(RentCompany rentCompany);

    RentCompany getRentCompanyById(int id);

    List<RentCompany> getAllRentCompanies();

    void dropRentCompanyById(int id);
}
