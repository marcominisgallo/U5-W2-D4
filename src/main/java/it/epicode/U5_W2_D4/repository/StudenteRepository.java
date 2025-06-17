package it.epicode.U5_W2_D4.repository;

import it.epicode.U5_W2_D4.model.Studente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudenteRepository extends JpaRepository<Studente, Integer> {
}
