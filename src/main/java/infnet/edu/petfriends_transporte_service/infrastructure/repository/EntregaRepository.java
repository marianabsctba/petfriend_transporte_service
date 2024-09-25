package infnet.edu.petfriends_transporte_service.infrastructure.repository;


import infnet.edu.petfriends_transporte_service.domain.model.Entrega;
import infnet.edu.petfriends_transporte_service.domain.vo.StatusEntrega;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EntregaRepository extends JpaRepository<Entrega, Long> {
    List<Entrega> findByPedidoId(Long pedidoId);
    List<Entrega> findByStatus(StatusEntrega status);
}
