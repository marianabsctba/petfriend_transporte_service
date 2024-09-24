package infnet.edu.petfriends_transporte_service.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTO {
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
}
