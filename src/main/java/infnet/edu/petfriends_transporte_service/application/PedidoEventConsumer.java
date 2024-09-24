package infnet.edu.petfriends_transporte_service.application;

import infnet.edu.petfriends_transporte_service.domain.events.PedidoCriadoEvent;
import infnet.edu.petfriends_transporte_service.domain.model.Transporte;
import infnet.edu.petfriends_transporte_service.infrastructure.clients.ClienteClient;
import infnet.edu.petfriends_transporte_service.infrastructure.dto.EnderecoDTO;
import infnet.edu.petfriends_transporte_service.infrastructure.repository.TransporteRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoEventConsumer {

    @Autowired
    private TransporteRepository transporteRepository;

    @Autowired
    private ClienteClient clienteClient;

    @RabbitListener(queues = "transporteQueue")
    public void iniciarTransporte(PedidoCriadoEvent event) {
        EnderecoDTO enderecoDTO = clienteClient.getEnderecoCliente(event.getClienteId());
        Transporte transporte = Transporte.builder()
                .pedidoId(event.getPedidoId())
                .status("EM TRANSITO")
                .rua(enderecoDTO.getRua())
                .cidade(enderecoDTO.getCidade())
                .estado(enderecoDTO.getEstado())
                .cep(enderecoDTO.getCep())
                .build();

        transporteRepository.save(transporte);

        System.out.println("Transporte iniciado para o pedido: " + event.getPedidoId() + ", Endereço: " + enderecoDTO);
    }
}
