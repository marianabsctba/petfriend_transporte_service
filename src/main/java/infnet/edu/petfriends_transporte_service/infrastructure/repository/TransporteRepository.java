package infnet.edu.petfriends_transporte_service.infrastructure.repository;

import infnet.edu.petfriends_transporte_service.domain.model.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, Long> {
    Optional<Transporte> findByPedidoId(Long pedidoId);
}
