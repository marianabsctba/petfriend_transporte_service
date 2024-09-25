package infnet.edu.petfriends_transporte_service.infrastructure.dto;

import infnet.edu.petfriends_transporte_service.domain.vo.Endereco;
import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class PedidoEventoDto {
    private Long pedidoId;
    private Endereco endereco;
}
