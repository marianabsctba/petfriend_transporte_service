package infnet.edu.petfriends_transporte_service.application;

import infnet.edu.petfriends_transporte_service.infrastructure.dto.PedidoEventoDto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class EntregaConsumer {

    private final EntregaService entregaService;

    public EntregaConsumer(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @RabbitListener(queues = "${broker.queue.pedido.name}")
    public void processarEventoPedido(PedidoEventoDto pedidoEvento) {
        entregaService.processarEntrega(pedidoEvento);
    }
}
