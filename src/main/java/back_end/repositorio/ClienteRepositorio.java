package back_end.repositorio;

import back_end.dominio.Cliente;

public interface ClienteRepositorio {
    Cliente salva(Cliente cliente);
    Cliente atualiza(Cliente cliente);
}
