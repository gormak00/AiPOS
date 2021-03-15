package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.RentCompany;

import java.util.List;

public interface RentCompanyService {
    void addRentCompany(RentCompany rentCompany);

    List<RentCompany> getAllRentCompanies();

    void dropRentCompanyById(int id);
}
