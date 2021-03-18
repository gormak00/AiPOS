package by.aipos.aipos_lab2.service;

import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client addClient(Client client) {
        int count = clientRepository.findAll().size();
        if (count == 0) client.setId(1);
        else client.setId(count + 1);

        return clientRepository.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public void dropClientById(int id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Client getClientById(int id) {
        return clientRepository.findById(id).get();
    }
}
