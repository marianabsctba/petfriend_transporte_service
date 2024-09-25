package infnet.edu.petfriends_transporte_service.application;

import infnet.edu.petfriends_transporte_service.domain.model.Entrega;
import infnet.edu.petfriends_transporte_service.domain.vo.StatusEntrega;
import infnet.edu.petfriends_transporte_service.infrastructure.dto.PedidoEventoDto;
import infnet.edu.petfriends_transporte_service.infrastructure.repository.EntregaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public void processarEntrega(PedidoEventoDto pedidoEventoDto) {
        if (pedidoEventoDto == null || pedidoEventoDto.getPedidoId() == null || pedidoEventoDto.getEndereco() == null) {
            throw new IllegalArgumentException("PedidoEventoDto, pedidoId, ou endereco não podem ser nulos");
        }

        Entrega novaEntrega = new Entrega(
                pedidoEventoDto.getPedidoId(),
                StatusEntrega.NOVA,
                LocalDateTime.now(),
                pedidoEventoDto.getEndereco()
        );

        entregaRepository.save(novaEntrega);

        System.out.println("Criada nova entrega com status NOVA para o pedido: " + pedidoEventoDto.getPedidoId());
    }

    public void atualizarStatusEntrega(Long entregaId, StatusEntrega novoStatus) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new IllegalArgumentException("Entrega não encontrada"));

        if (entrega.isCompleted()) {
            throw new IllegalStateException("A entrega já foi concluída e o status não pode ser alterado.");
        }

        entrega.updateStatus(novoStatus);

        if (novoStatus == StatusEntrega.ENTREGUE) {
            entrega.setDataEntrega(LocalDateTime.now());
        }

        entregaRepository.save(entrega);
    }

    public List<Entrega> getAll() {
        return entregaRepository.findAll();
    }

    public List<Entrega> findByPedidoId(Long id) {
        return entregaRepository.findByPedidoId(id);
    }

    public List<Entrega> findByStatus(StatusEntrega status) {
        return entregaRepository.findByStatus(status);
    }
}
