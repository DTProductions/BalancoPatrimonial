package comandos;

import demonstrativos.BalancoPatrimonial;
import helpers.ConversaoSegura;
import helpers.LeituraInput;
import java.util.Scanner;

public class Alterar implements Comando{

    private String mensagem;
    private Scanner terminal;
    private BalancoPatrimonial balanco;

    public Alterar(Scanner terminal, BalancoPatrimonial balanco){
        this.terminal = terminal;
        this.balanco = balanco;
        this.mensagem = null;
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
        Integer selecionado = ConversaoSegura.lerInt(terminal.nextLine());
        if(selecionado == null){
            mensagem = "Valor inválido, operação abortada";
            return false;
        }

        if(!balanco.indiceValido(categoria, selecionado)){
            mensagem = "Registro selecionado não existe, operação abortada";
            return false;
        }

        balanco.imprimirRegistro(categoria, selecionado);

        boolean resposta = LeituraInput.simOuNao("Alterar nome (S/N)? ", terminal);
        if (resposta) {
            System.out.print("Digite o novo nome: ");
            String nome = terminal.nextLine();
            balanco.alterarRegistro(categoria, nome, selecionado);
        }

        resposta = LeituraInput.simOuNao("Alterar valor (S/N)? ", terminal);
        if (resposta) {
            System.out.print("Digite o novo valor: ");

            Integer valor = ConversaoSegura.lerInt(terminal.nextLine());
            if(valor == null){
                mensagem = "Valor inválido, operação abortada";
                return false;
            }
            if(valor < 0){
                mensagem = "Somente valores positivos são aceitos, operação abortada";
                return false;
            }

            balanco.alterarRegistro(categoria, valor, selecionado);
        }

        return true;
    }

    @Override
    public String mensagemRetorno(){
        return mensagem;
    }
}
