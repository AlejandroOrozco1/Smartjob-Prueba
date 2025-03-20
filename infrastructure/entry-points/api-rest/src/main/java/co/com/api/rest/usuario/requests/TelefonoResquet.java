package co.com.api.rest.usuario.requests;

import lombok.Data;

@Data
public class TelefonoResquet {
    private String numero;
    private String codigoCiudad;
    private String coidgoPais;
}
