package infnet.edu.petfriends_transporte_service.interfaces;

import infnet.edu.petfriends_transporte_service.application.EntregaService;
import infnet.edu.petfriends_transporte_service.domain.model.Entrega;
import infnet.edu.petfriends_transporte_service.domain.vo.StatusEntrega;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @GetMapping
    public ResponseEntity<List<Entrega>> getAllEntregas() {
        List<Entrega> entregas = entregaService.getAll();
        if (entregas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entregas);
    }

    @GetMapping("/pedido/{id}")
    public ResponseEntity<List<Entrega>> getEntregasPorPedidoId(@PathVariable Long id) {
        List<Entrega> entregas = entregaService.findByPedidoId(id);
        if (entregas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(entregas);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Entrega>> getEntregasPorStatus(@PathVariable String status) {
        // Converter a string para a enum StatusEntrega
        try {
            StatusEntrega statusEnum = StatusEntrega.valueOf(status.toUpperCase());
            List<Entrega> entregas = entregaService.findByStatus(statusEnum);
            if (entregas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(entregas);
        } catch (IllegalArgumentException e) {
            // Retornar um erro 400 caso o status não corresponda à enumeração
            return ResponseEntity.badRequest().body(null);
        }
    }

}
