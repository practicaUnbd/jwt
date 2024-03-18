package com.example.esteban.IService;

import java.util.List;
import java.util.Optional;

import com.example.esteban.Entity.Usuario;

public interface AssingRoleIService {

	public List<Usuario> all();
	
	
	Optional<Usuario> findByOptional(String NumeroDocumento);
	
	public Usuario save(Usuario usuario);

	
}
