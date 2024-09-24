package infnet.edu.petfriends_transporte_service.domain.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
public class IniciarTransporteCommand {

    @TargetAggregateIdentifier
    private Long transporteId;
    private Long pedidoId;
}
