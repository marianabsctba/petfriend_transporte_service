package infnet.edu.petfriends_transporte_service.domain.model;

import infnet.edu.petfriends_transporte_service.domain.vo.Endereco;
import infnet.edu.petfriends_transporte_service.domain.vo.StatusEntrega;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Entity
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pedidoId;

    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    private LocalDateTime dataCriacao;
    private LocalDateTime dataEntrega;

    @Embedded
    private Endereco enderecoDestino;

    public Entrega() {}

    public Entrega(Long pedidoId, StatusEntrega status, LocalDateTime dataCriacao, Endereco enderecoDestino) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.enderecoDestino = enderecoDestino;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entrega entrega)) return false;
        return Objects.equals(id, entrega.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public boolean isCompleted() {
        return this.dataEntrega != null && this.status == StatusEntrega.ENTREGUE;
    }

    public void updateStatus(StatusEntrega newStatus) {
        this.status = newStatus;
    }
}
