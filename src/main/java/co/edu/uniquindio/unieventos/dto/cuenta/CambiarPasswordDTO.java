package co.edu.uniquindio.unieventos.dto.cuenta;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CambiarPasswordDTO(
        @NotBlank @Email String email,
        @NotBlank String codigoVerificacion,
        @NotBlank String passwordNueva
) {
}