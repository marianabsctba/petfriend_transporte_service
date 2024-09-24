package infnet.edu.petfriends_transporte_service.domain.events;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransporteIniciadoEvent {

    private Long transporteId;
    private Long pedidoId;
}
