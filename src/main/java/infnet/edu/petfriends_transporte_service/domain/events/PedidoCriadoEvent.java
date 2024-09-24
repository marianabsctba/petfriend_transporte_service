package infnet.edu.petfriends_transporte_service.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCriadoEvent {

    private Long pedidoId;
    private Long clienteId;
    private Long produtoId;
    private String enderecoEntrega;
}
