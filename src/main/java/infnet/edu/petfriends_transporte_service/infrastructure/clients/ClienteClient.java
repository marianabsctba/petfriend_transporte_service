package infnet.edu.petfriends_transporte_service.infrastructure.clients;

import infnet.edu.petfriends_transporte_service.infrastructure.dto.EnderecoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "petfriends-clientes", url = "http://localhost:8080/api/clientes")
public interface ClienteClient {

    @GetMapping("/{id}/endereco")
    EnderecoDTO getEnderecoCliente(@PathVariable("id") Long clienteId);
}
