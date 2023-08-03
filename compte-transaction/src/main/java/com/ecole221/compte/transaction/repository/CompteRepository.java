package com.ecole221.compte.transaction.repository;

import com.ecole221.compte.transaction.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompteRepository  extends JpaRepository<Compte, UUID> {
}
