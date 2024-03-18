package com.example.esteban.IRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.esteban.Entity.Usuario;

public interface AssingRoleIRepository extends JpaRepository<Usuario,Integer>{
	Optional<Usuario> findByNumeroDocumento(String numeroDocumento);
}

