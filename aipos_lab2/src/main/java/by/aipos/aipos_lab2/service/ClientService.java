package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Client;

import java.util.List;

public interface ClientService {
    Client addClient(Client client);

    Client getClientById(int id);

    List<Client> getAllClients();

    void dropClientById(int id);

    Client updateClientById(Client client, int id);
}
