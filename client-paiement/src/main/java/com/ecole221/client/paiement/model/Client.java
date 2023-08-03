package com.ecole221.client.paiement.model;


import com.ecole221.common.service.event.ClientStatus;
import com.ecole221.common.service.event.CompteStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Client {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private UUID id;
    private String nomComplet;
    private String telephone ;
    private ClientStatus clientStatus;
    private CompteStatus compteStatus;
}
