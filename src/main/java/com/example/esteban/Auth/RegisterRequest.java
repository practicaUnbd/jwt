package com.example.esteban.Auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
	String nombre;
    String apellido;
    String tipo_documento;
    String numero_documento;
    String edad;
    String telefono;
    String email;
    String password;
}
