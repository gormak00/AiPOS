package by.aipos.aipos_lab2.repository;

import by.aipos.aipos_lab2.model.RentCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentCompanyRepository extends JpaRepository<RentCompany, Integer> {
    Optional<RentCompany> findByName(String name);
}
