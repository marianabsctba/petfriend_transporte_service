package infnet.edu.petfriends_transporte_service.domain.vo;

import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class Endereco {
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

    public Endereco() {}

    public Endereco(String rua, String numero, String bairro, String cidade, String estado, String cep) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return rua + ", " + numero + " - " + bairro + ", " + cidade + " - " + estado + ", " + cep;
    }
    public boolean isComplete() {
        return rua != null && !rua.isEmpty() &&
                numero != null && !numero.isEmpty() &&
                bairro != null && !bairro.isEmpty() &&
                cidade != null && !cidade.isEmpty() &&
                estado != null && !estado.isEmpty() &&
                cep != null && !cep.isEmpty();
    }
}
