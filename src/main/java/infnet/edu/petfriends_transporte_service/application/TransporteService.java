package infnet.edu.petfriends_transporte_service.application;

import infnet.edu.petfriends_transporte_service.domain.model.Transporte;
import infnet.edu.petfriends_transporte_service.infrastructure.repository.TransporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransporteService {

    @Autowired
    private TransporteRepository transporteRepository;

    public Transporte iniciarTransporte(Long pedidoId, String status, String rua, String cidade, String estado, String cep) {
        Transporte transporte = Transporte.builder()
                .pedidoId(pedidoId)
                .status(status)
                .rua(rua)
                .cidade(cidade)
                .estado(estado)
                .cep(cep)
                .build();

        return transporteRepository.save(transporte);
    }

    public Optional<Transporte> buscarPorPedidoId(Long pedidoId) {
        return transporteRepository.findByPedidoId(pedidoId);
    }

    public Transporte atualizarStatusTransporte(Long pedidoId, String status) {
        Optional<Transporte> optionalTransporte = transporteRepository.findByPedidoId(pedidoId);

        if (optionalTransporte.isPresent()) {
            Transporte transporte = optionalTransporte.get();
            transporte.setStatus(status);
            return transporteRepository.save(transporte);
        } else {
            throw new RuntimeException("Transporte não encontrado para o pedidoId: " + pedidoId);
        }
    }
}
