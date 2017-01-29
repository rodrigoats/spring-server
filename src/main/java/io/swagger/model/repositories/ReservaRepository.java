package io.swagger.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swagger.model.entities.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, String>{

}
