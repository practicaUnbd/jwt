package com.example.esteban.Service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.esteban.Entity.Usuario;
import com.example.esteban.IRepository.AssingRoleIRepository;
import com.example.esteban.IService.AssingRoleIService;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class AssingRoleService implements AssingRoleIService{

	@Autowired
    private AssingRoleIRepository assingRoleIRepository;

	@Override
	public List<Usuario> all() {
		return this.assingRoleIRepository.findAll();
	}
	
	@Override
	public Optional<Usuario> findByOptional(String NumeroDocumento) {
		return this.assingRoleIRepository.findByNumeroDocumento(NumeroDocumento);
	}

	@Override
	public Usuario save(Usuario usuario) {
		return this.assingRoleIRepository.save(usuario);
	}
	
}
