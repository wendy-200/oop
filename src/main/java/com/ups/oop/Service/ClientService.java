package com.ups.oop.Service;


import com.ups.oop.dto.ClientDTO;
import com.ups.oop.entity.Client;
import com.ups.oop.repository.ClientRepository;
import com.ups.oop.repository.PersonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity getClients() {
        Iterable<Client> clientIterable = clientRepository.findAll();
        List<ClientDTO> clientList = new ArrayList<>();
        for (Client client : clientIterable) {
            ClientDTO clientDTO = new ClientDTO(
                    client.getPersonId(),
                    (client.getName() + "-" + client.getLastname()),
                    client.getAGE(),
                    client.getClientCode()
            );
            clientList.add(clientDTO);
        }
    return clientList;

}

}