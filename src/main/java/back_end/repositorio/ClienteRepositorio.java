package back_end.repositorio;

import back_end.dominio.Cliente;

import java.util.List;

public interface ClienteRepositorio {
    Cliente salva(Cliente cliente);
    Cliente atualiza(Cliente cliente);
    List<Cliente> listar();
}
