package comandos;

import demonstrativos.BalancoPatrimonial;

import java.util.Scanner;

public class SalvarArquivo implements Comando{

    private String mensagem;
    private Scanner terminal;
    private BalancoPatrimonial balanco;

    public SalvarArquivo(Scanner terminal, BalancoPatrimonial balanco){
        this.terminal = terminal;
        this.balanco = balanco;
        mensagem = null;
    }

    @Override
    public boolean executar(){
        System.out.print("Digite o nome do arquivo: ");
        String nomeArquivo = terminal.nextLine();
        if(!balanco.gerarArquivo(nomeArquivo)){
            mensagem = "Ocorreu um erro durante a gravação, operação abortada";
            return false;
        }

        return true;
    }

    @Override
    public String mensagemRetorno(){
        return mensagem;
    }
}
