package infnet.edu.petfriends_transporte_service.interfaces;

import infnet.edu.petfriends_transporte_service.application.TransporteService;
import infnet.edu.petfriends_transporte_service.domain.model.Transporte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/transportes")
public class TransporteController {

    @Autowired
    private TransporteService transporteService;

    @PostMapping("/iniciar")
    public ResponseEntity<Transporte> iniciarTransporte(@RequestParam Long pedidoId,
                                                        @RequestParam String rua,
                                                        @RequestParam String cidade,
                                                        @RequestParam String estado,
                                                        @RequestParam String cep) {
        Transporte transporte = transporteService.iniciarTransporte(pedidoId, "EM TRANSITO", rua, cidade, estado, cep);
        return ResponseEntity.status(201).body(transporte);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<Transporte> buscarPorPedidoId(@PathVariable Long pedidoId) {
        Optional<Transporte> transporte = transporteService.buscarPorPedidoId(pedidoId);
        return transporte.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizarStatus/{pedidoId}")
    public ResponseEntity<Transporte> atualizarStatusTransporte(@PathVariable Long pedidoId, @RequestParam String status) {
        Transporte transporte = transporteService.atualizarStatusTransporte(pedidoId, status);
        return ResponseEntity.ok(transporte);
    }
}