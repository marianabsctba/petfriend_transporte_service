package infnet.edu.petfriends_transporte_service.infrastructure.clients;


import infnet.edu.petfriends_transporte_service.infrastructure.dto.PedidoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "pedido-service")
public interface PedidoClient {

    @GetMapping("/api/pedidos/{pedidoId}")
    PedidoDTO buscarPedido(@PathVariable("pedidoId") Long pedidoId);
}
