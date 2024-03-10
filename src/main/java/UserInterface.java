import demonstrativos.BalancoPatrimonial;
import comandos.*;
import java.util.Scanner;

public class UserInterface {

    private Scanner terminal;
    private BalancoPatrimonial balanco;

    public UserInterface(Scanner terminal, BalancoPatrimonial balanco){
        this.terminal = terminal;
        this.balanco = balanco;
    }

    public void iniciar(){
        if(!inicializarBalanco()){
            System.out.println("Não foi possível ler o arquivo, fechando o programa...");
            return;
        }

        while(true){
            imprimirOpcoes();

            String input = terminal.nextLine();
            if (input.equals("5")) {
                break;
            }

            executarComando(input);
        }
    }

    private boolean inicializarBalanco(){
        System.out.println("=".repeat(20));
        System.out.println("1: Novo Balanço");
        System.out.println("2: Carregar Balanço");
        System.out.println("=".repeat(20));

        String input;
        do {
            input = terminal.nextLine();
        } while (!(input.equals("1") || input.equals("2")));

        if (input.equals("2")) {
            System.out.print("Digite o local do arquivo: ");
            input = terminal.nextLine();
            return balanco.lerArquivo(input);
        }

        return true;
    }

    private void imprimirOpcoes(){
        System.out.println("#".repeat(20));
        System.out.println("1: Inserir Item");
        System.out.println("2: Alterar Item");
        System.out.println("3: Remover Item");
        System.out.println("4: Salvar Arquivo");
        System.out.println("5: Fechar");
        System.out.println("#".repeat(20));
    }

    private void executarComando(String input){
        Comando comando = null;
        switch (input) {
            case "1":
                comando = new Inserir(terminal, balanco);
                break;
            case "2":
                comando = new Alterar(terminal, balanco);
                break;
            case "3":
                comando = new Remover(terminal, balanco);
                break;
            case "4":
                comando = new SalvarArquivo(terminal, balanco);
                break;
        }

        if(comando == null){
            return;
        }

        if(!comando.executar()){
            System.out.println(comando.mensagemRetorno());
        }
    }
}
