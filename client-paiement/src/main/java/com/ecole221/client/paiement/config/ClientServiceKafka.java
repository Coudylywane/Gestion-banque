package com.ecole221.client.paiement.config;


import com.ecole221.client.paiement.service.ClientService;
import com.ecole221.common.service.event.CompteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientServiceKafka {
    private final ClientService clientService;

    @KafkaListener(topics = "compte-event-topic", groupId = "default", containerFactory = "EventListner")
    public void getResponse(CompteEvent compteEvent) {
        clientService.changeClient(compteEvent);
    }
}
