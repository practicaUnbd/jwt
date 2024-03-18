package com.example.esteban.Auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.esteban.Entity.Role;
import com.example.esteban.Entity.Usuario;
import com.example.esteban.IRepository.IRepositoryUsuario;
import com.example.esteban.Jwt.JwtService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final IRepositoryUsuario iRepositoryUsuario;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Usuario email=iRepositoryUsuario.findByEmail(request.getEmail()).orElseThrow();
        String token=jwtService.getToken(email);
        return AuthResponse.builder()
            .token(token)
            .build();

    }
    
    public AuthResponse register(RegisterRequest request) {
        Usuario user = Usuario.builder()
            .Nombre(request.getNombre())
            .Apellido(request.getApellido())
            .TipoDocumento(request.getTipo_documento())
            .numeroDocumento(request.getNumero_documento())
            .Edad(request.getEdad())
            .Telefono(request.getTelefono())           
            .email(request.getEmail())
            .Password(passwordEncoder.encode( request.getPassword()))
            .role(Role.USER)
            .build();

        iRepositoryUsuario.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }

}
