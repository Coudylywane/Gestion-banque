package com.ecole221.compte.transaction.mapper;

import com.ecole221.common.service.dto.CompteDTO;
import com.ecole221.compte.transaction.model.Compte;
import org.springframework.stereotype.Component;

@Component
public class CompteTransactionMapper {

    public CompteDTO compteToCompteDTO(Compte compte){
        return CompteDTO.builder()
                .clientId(compte.getClient_id())
                .solde(compte.getSolde())
                .build();
    }

}
