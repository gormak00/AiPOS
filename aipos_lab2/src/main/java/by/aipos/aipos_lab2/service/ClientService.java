package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Client;

import java.util.List;

public interface ClientService {
    void addClient(Client client);
    List<Client> getAllClients();
}
