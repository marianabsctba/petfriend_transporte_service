package infnet.edu.petfriends_transporte_service.interfaces.controller;

import infnet.edu.petfriends_transporte_service.application.EntregaService;
import infnet.edu.petfriends_transporte_service.domain.vo.StatusEntrega;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

     @PutMapping("/{id}/status")
    public ResponseEntity<String> atualizarStatusEntrega(@PathVariable Long id, @RequestParam String status) {
        try {
            StatusEntrega novoStatus = StatusEntrega.valueOf(status.toUpperCase());
            entregaService.atualizarStatusEntrega(id, novoStatus);
            return ResponseEntity.ok("Status da entrega atualizado para: " + novoStatus);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Status inválido");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(409).body("A entrega já foi concluída e não pode ser alterada.");
        }
    }
}
