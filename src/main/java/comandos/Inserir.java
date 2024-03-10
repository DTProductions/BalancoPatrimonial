package comandos;

import java.util.Scanner;
import helpers.ConversaoSegura;
import helpers.LeituraInput;
import demonstrativos.BalancoPatrimonial;

public class Inserir implements Comando {

    private String mensagem;
    private Scanner terminal;
    private BalancoPatrimonial balanco;

    public Inserir(Scanner terminal, BalancoPatrimonial balanco){
        this.terminal = terminal;
        this.balanco = balanco;
        mensagem = null;
    }

    @Override
    public boolean executar(){
        String categoria = LeituraInput.selecionarCategoria(terminal);

        System.out.print("Nome: ");
        String nome = terminal.nextLine();

        System.out.print("Valor: ");
        Double valor = ConversaoSegura.lerDouble(terminal.nextLine());
        if(valor == null){
            mensagem = "Valor inválido, operação abortada";
            return false;
        }
        if(valor < 0){
            mensagem = "Somente valores positivos são aceitos, operação abortada";
            return false;
        }

        balanco.adicionarRegistro(categoria, nome, valor);
        return true;
    }

    @Override
    public String mensagemRetorno(){
        return mensagem;
    }
}
