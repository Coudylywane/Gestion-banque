package com.ecole221.compte.transaction.service;

import com.ecole221.common.service.dto.ClientDTO;
import com.ecole221.common.service.dto.CompteDTO;
import com.ecole221.common.service.event.ClientEvent;
import com.ecole221.common.service.event.CompteEvent;
import com.ecole221.common.service.event.CompteStatus;
import com.ecole221.compte.transaction.mapper.CompteTransactionMapper;
import com.ecole221.compte.transaction.model.Compte;
import com.ecole221.compte.transaction.repository.CompteRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class CompteService {
    private final CompteRepository compteRepository;
    private final CompteTransactionMapper mapper;
    private final Utils utils;


    public List<CompteDTO> getAllComptes() {
        return compteRepository.findAll()
                .stream()
                .map(mapper::compteToCompteDTO)
                .toList();
    }

    @Transactional
    public CompteEvent saveCompte(ClientEvent clientEvent){
        ClientDTO clientDTO = clientEvent.getClientDTO();
        CompteEvent compteEvent = null;
        CompteDTO compteDTO = new CompteDTO(clientDTO.getId(), clientDTO.getSolde());
        try{
            utils.checkSoldeIsValid(clientDTO.getSolde());
            compteRepository.save(new Compte(clientDTO.getId(), clientDTO.getSolde()));
            compteEvent = new CompteEvent(CompteStatus.UPDATED , compteDTO);
        }catch(Exception ex){
            compteEvent = new CompteEvent(CompteStatus.ERREUR_SOLDE , compteDTO);
        }
        return compteEvent;
    }

}
