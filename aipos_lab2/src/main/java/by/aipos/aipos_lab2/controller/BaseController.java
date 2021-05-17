package by.aipos.aipos_lab2.controller;

import by.aipos.aipos_lab2.model.Client;
import by.aipos.aipos_lab2.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

public abstract class BaseController {
    @Autowired
    ClientServiceImpl clientService;
    public Client getUserNameFromToken() {
        String id = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        if (!id.isEmpty()) {
            return clientService.getClientById(Integer.parseInt(id));
        }
        return null;
    }
}
