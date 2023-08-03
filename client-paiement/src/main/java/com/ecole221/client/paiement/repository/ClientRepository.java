package com.ecole221.client.paiement.repository;

import com.ecole221.client.paiement.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
