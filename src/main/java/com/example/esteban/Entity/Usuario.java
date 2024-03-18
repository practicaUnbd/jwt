package com.example.esteban.Entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="usuario")
public class Usuario implements Serializable, UserDetails {

	private final static long serialVersionUID = 1l;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	

	@Column(name = "nombre", length = 50, nullable = false)
	private String Nombre;
	
	@Column(name = "apellido", length = 50, nullable = false)
	private String Apellido;
	
	@Column(name = "tipo_documento", length = 20, nullable = false)
	private String TipoDocumento;
	
	@Column(name = "numero_documento", unique = true, length = 20, nullable = false)
	private String numeroDocumento;
	
	@Column(name = "edad", length = 3, nullable = false)
	private String Edad;
		
	@Column(name = "telefono", length = 20, nullable = false)
	private String Telefono;
	
	@Column(name = "email", nullable = false, unique = true,  length = 50)
	private String email;
	
	@Column(name ="password", nullable = false, length = 128) 
	private String Password;
	
	@Enumerated(EnumType.STRING)
    @Column(name ="role", length = 20, nullable = false)
    private Role role;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority((role.name())));
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return this.email;
	}
	
	
}
