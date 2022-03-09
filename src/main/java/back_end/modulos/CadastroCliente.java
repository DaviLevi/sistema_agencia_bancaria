package back_end.modulos;

import back_end.dominio.Cliente;
import back_end.repositorio.ClienteRepositorio;

public class CadastroCliente {
    private ClienteRepositorio clienteRepositorio;


    public CadastroCliente(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }


    public void cadastra(Cliente cliente){

    }
}
