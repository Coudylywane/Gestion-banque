package com.ecole221.client.paiement.mapper;

import com.ecole221.client.paiement.model.Client;
import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.CompteStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientMapper {

    public ClientDTO clientToClientDTO(Client client) {
        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setNomComplet(client.getNomComplet());
        dto.setTelephone(client.getTelephone());
        dto.setClientStatus(client.getClientStatus().name());
        dto.setCompteStatus(client.getCompteStatus().name());
        return dto;
    }

    public Client clientDTOToClient(ClientDTO clientDTO) {
        return Client.builder()
                .nomComplet(clientDTO.getNomComplet())
                .telephone(clientDTO.getTelephone())
                .clientStatus(ClientStatus.valueOf(clientDTO.getClientStatus()))
                .compteStatus(CompteStatus.valueOf(clientDTO.getCompteStatus()))
                .build();
    }


}
