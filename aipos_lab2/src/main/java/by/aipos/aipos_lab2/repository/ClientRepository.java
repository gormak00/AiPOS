package by.aipos.aipos_lab2.repository;

import by.aipos.aipos_lab2.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    //public Client save(Client client);
}
