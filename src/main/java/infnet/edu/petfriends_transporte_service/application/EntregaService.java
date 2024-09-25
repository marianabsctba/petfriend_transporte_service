package infnet.edu.petfriends_transporte_service.application;

import infnet.edu.petfriends_transporte_service.domain.model.Entrega;
import infnet.edu.petfriends_transporte_service.domain.vo.StatusEntrega;
import infnet.edu.petfriends_transporte_service.interfaces.dto.PedidoEventoDto;
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

    public void updateDaEntrega(PedidoEventoDto pedidoEventoDto) {
        if (pedidoEventoDto == null || pedidoEventoDto.getPedidoId() == null || pedidoEventoDto.getEndereco() == null) {
            throw new IllegalArgumentException("PedidoEventoDto, pedidoId, ou endereco não podem ser nulos");
        }

        Entrega novaEntrega = new Entrega(
                pedidoEventoDto.getPedidoId(),
                StatusEntrega.NOVA, // toda entrega começa com o status NOVA
                LocalDateTime.now(),
                pedidoEventoDto.getEndereco()
        );

        entregaRepository.save(novaEntrega);

        System.out.println("Nova entrega: " + pedidoEventoDto.getPedidoId());
    }

    public void atualizarStatusEntrega(Long entregaId, StatusEntrega novoStatus) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new IllegalArgumentException("Entrega não encontrada"));

        if (entrega.isCompleted() && (novoStatus != StatusEntrega.DEVOLVIDO && novoStatus != StatusEntrega.EXTRAVIADO)) {
            throw new IllegalStateException("A entrega já foi concluída e o status não pode ser alterado.");
        }

        entrega.updateStatus(novoStatus);

        switch (novoStatus) {
            case NOVA:
                System.out.println("A entrega foi marcada como NOVA.");
                break;
            case FECHADO:
                System.out.println("A entrega foi FECHADA.");
                break;
            case CANCELADO:
                System.out.println("A entrega foi CANCELADA.");
                break;
            case EM_PREPARACAO:
                System.out.println("A entrega está EM PREPARAÇÃO.");
                break;
            case EM_TRANSITO:
                System.out.println("A entrega está EM TRÂNSITO.");
                break;
            case ENTREGUE:
                entrega.setDataEntrega(LocalDateTime.now());
                System.out.println("A entrega foi ENTREGUE.");
                break;
            case DEVOLVIDO:
                System.out.println("A entrega foi DEVOLVIDA.");
                break;
            case EXTRAVIADO:
                System.out.println("A entrega foi EXTRAVIADA.");
                break;
            default:
                throw new IllegalArgumentException("Status de entrega inválido: " + novoStatus);
        }

        entregaRepository.save(entrega);
    }


    public List<Entrega> findByStatus(StatusEntrega status) {
        return entregaRepository.findByStatus(status);
    }
}
