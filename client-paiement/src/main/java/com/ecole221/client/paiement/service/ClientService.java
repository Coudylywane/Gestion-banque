package com.ecole221.client.paiement.service;

import com.ecole221.client.paiement.mapper.ClientMapper;
import com.ecole221.client.paiement.model.Client;
import com.ecole221.client.paiement.repository.ClientRepository;
import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.event.ClientEvent;
import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.CompteEvent;
import com.ecole221.common.service.event.CompteStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Data
@Slf4j
public class ClientService {
    private static final String CLIENT_TOPIC = "client-event-topic";
    private final ClientRepository clientRepository;
    private final ClientMapper mapper;
    private final KafkaTemplate<String, ClientEvent> kafkaTemplate;

    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(mapper::clientToClientDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public Client saveClient(ClientDTO clientDTO) {
        clientDTO.setClientStatus("CREATED");
        clientDTO.setCompteStatus("INIT");
        Client client = clientRepository.save(mapper.clientDTOToClient(clientDTO));
        clientDTO.setId(client.getId());
        ClientEvent clientEvent = new ClientEvent(clientDTO , ClientStatus.CREATED);
        kafkaTemplate.send(CLIENT_TOPIC, clientEvent);
        return client;
    }

    @Transactional
    public void changeClient(CompteEvent event) {
        UUID clientId = event.getCompteDTO().getClientId();

        clientRepository.findById(clientId).ifPresent(client -> {
            boolean isSaved = CompteStatus.UPDATED.equals(event.getCompteStatus());
            ClientStatus clientStatus = isSaved ? ClientStatus.COMPLETE : ClientStatus.ERREUR;
            client.setClientStatus(clientStatus);
            client.setCompteStatus(event.getCompteStatus());
        });

    }
}
