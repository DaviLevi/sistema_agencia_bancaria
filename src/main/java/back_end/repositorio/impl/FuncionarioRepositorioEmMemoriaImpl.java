package back_end.repositorio.impl;

import back_end.dominio.Agencia;
import back_end.dominio.Funcionario;
import back_end.repositorio.AgenciaRepositorio;
import back_end.repositorio.FuncionarioRepositorio;

import java.util.Hashtable;
import java.util.Map;
import java.util.Optional;

public class FuncionarioRepositorioEmMemoriaImpl implements FuncionarioRepositorio {
    private Map<Integer, Funcionario> funcionarios;
    private AgenciaRepositorio agenciaRepositorio;

    public FuncionarioRepositorioEmMemoriaImpl(AgenciaRepositorio agenciaRepositorio){
        this.funcionarios = new Hashtable<>();
        this.agenciaRepositorio = agenciaRepositorio;
    }

    @Override
    public Funcionario salva(Funcionario novoFuncionario) {
        Agencia agencia = agenciaRepositorio.consultaAgenciaPeloNumeroAgencia(novoFuncionario.getNumeroAgencia()).orElseThrow(() -> new IllegalStateException("Agencia vinculada ao funcionario nao existe"));
        if(!novoFuncionario.ehGerente()){
            boolean naoExisteSupervisor = ! funcionarios.containsKey(novoFuncionario.getNumeroFuncionalDoSupervisor());
            if(naoExisteSupervisor) throw new IllegalStateException("Supervisor inexistente");
        }
        boolean existeFuncionarioComMesmoNumeroFuncionalNoBd = funcionarios.containsKey(novoFuncionario.getNumeroFuncional());
        if(existeFuncionarioComMesmoNumeroFuncionalNoBd) throw new IllegalStateException("Ja Existe um funcionario com mesmo numero funcional no BD");
        novoFuncionario.atualizaAgencia(agencia);
        funcionarios.put(novoFuncionario.getNumeroFuncional(), novoFuncionario);
        return novoFuncionario;
    }

    @Override
    public Optional<Funcionario> buscaFuncionarioPorNumeroFuncional(Integer numeroFuncional) {
        return funcionarios.keySet().stream().filter(nf -> nf.equals(numeroFuncional)).findFirst().map(nf -> funcionarios.get(nf));
    }
}
