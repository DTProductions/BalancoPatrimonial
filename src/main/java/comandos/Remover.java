package comandos;

import demonstrativos.BalancoPatrimonial;
import helpers.ConversaoSegura;
import helpers.LeituraInput;

import java.util.Scanner;

public class Remover implements Comando{

    private String mensagem;
    private Scanner terminal;
    private BalancoPatrimonial balanco;

    public Remover(Scanner terminal, BalancoPatrimonial balanco){
        this.terminal = terminal;
        this.balanco = balanco;
        mensagem = null;
    }

    @Override
    public boolean executar(){
        String categoria = LeituraInput.selecionarCategoria(terminal);
        if(balanco.estaVazio(categoria)){
            mensagem = "Não existem registros nesta categoria.";
            return false;
        }

        balanco.imprimirRegistros(categoria);

        System.out.print("Numero do registro: ");

        Integer numero = ConversaoSegura.lerInt(terminal.nextLine());
        if(numero == null){
            mensagem = "Valor inválido, operação abortada";
            return false;
        }

        if(!balanco.removerRegistro(categoria, numero)){
            mensagem = "O registro selecionado não existe";
            return false;
        }

        return true;
    }

    @Override
    public String mensagemRetorno(){
        return mensagem;
    }
}
