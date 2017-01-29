package io.swagger.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.swagger.model.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, String>{

	public Usuario findById(String id);
}
