package infnet.edu.petfriends_transporte_service.domain.aggregate;

import infnet.edu.petfriends_transporte_service.domain.commands.IniciarTransporteCommand;
import infnet.edu.petfriends_transporte_service.domain.events.TransporteIniciadoEvent;
import infnet.edu.petfriends_transporte_service.infrastructure.clients.PedidoClient;
import infnet.edu.petfriends_transporte_service.infrastructure.dto.PedidoDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class TransporteAggregate {

    @AggregateIdentifier
    private Long id;
    private Long pedidoId;

    @CommandHandler
    public TransporteAggregate(IniciarTransporteCommand command, PedidoClient pedidoClient) {
        PedidoDTO pedidoDTO = pedidoClient.buscarPedido(command.getPedidoId());

        apply(new TransporteIniciadoEvent(command.getTransporteId(), pedidoDTO.getPedidoId()));
    }

    @EventSourcingHandler
    public void on(TransporteIniciadoEvent event) {
        this.id = event.getTransporteId();
        this.pedidoId = event.getPedidoId();
    }
}
