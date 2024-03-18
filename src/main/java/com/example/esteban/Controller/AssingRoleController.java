package com.example.esteban.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.esteban.Entity.Usuario;
import com.example.esteban.IService.AssingRoleIService;


@RestController
@RequestMapping("/api/security/assingrole")
@CrossOrigin(origins = {"*"})
public class AssingRoleController {

	@Autowired
    private AssingRoleIService service;

	@GetMapping
	public List<Usuario> all() {
		return service.all();
	}
	
    @GetMapping("{NumeroDocumento}")
    public ResponseEntity<?> findByCedula(@PathVariable String NumeroDocumento) {
    	Optional<Usuario> optionalUsuario = service.findByOptional(NumeroDocumento);
    	if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            Map<String, Object> response = new HashMap<>();
            response.put("id", usuario.getId());
            response.put("nombre", usuario.getNombre());
            response.put("apellido", usuario.getApellido());
            response.put("Numero Documento", usuario.getNumeroDocumento());
            response.put("role", usuario.getRole());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("{NumeroDocumento}")
	@ResponseStatus(code = HttpStatus.CREATED)
	public Usuario update(@PathVariable String NumeroDocumento, @RequestBody Usuario usuario) {
    	Optional<Usuario> op = service.findByOptional(NumeroDocumento);
		
		if (!op.isEmpty()) {
			Usuario roleUpdate = op.get();
			roleUpdate.setRole(usuario.getRole());
			return service.save(roleUpdate);
		}
		
		return usuario;
	}

    

}
