
import java.util.Scanner;
public class BP {

    public static void main(String[] args) {

        Scanner terminal = new Scanner(System.in);

        System.out.println("=".repeat(20));
        System.out.println("1: Novo Balanço");
        System.out.println("2: Carregar Balanço");
        System.out.println("=".repeat(20));
        //Runtime.getRuntime().exec("clear");

        String input;
        do {
            input = terminal.nextLine();
        } while (!(input.equals("1") || input.equals("2")));

        BalancoPatrimonial balanco = new BalancoPatrimonial();

        if (input.equals("2")) {
            System.out.print("Digite o local do arquivo: ");
            input = terminal.nextLine();
            balanco.lerArquivo(input);
        }

        while (true) {
            System.out.println("#".repeat(20));
            System.out.println("1: Inserir Item");
            System.out.println("2: Alterar Item");
            System.out.println("3: Remover Item");
            System.out.println("4: Salvar Arquivo");
            System.out.println("5: Fechar");
            System.out.println("#".repeat(20));

            input = terminal.nextLine();
            if (input.equals("5")) {
                break;
            }

            switch (input) {
                case "1": {
                    String categoria = selecionarCategoria(terminal);

                    System.out.print("Nome: ");
                    String nome = terminal.nextLine();

                    System.out.print("Valor: ");
                    double valor;
                    try {
                        valor = Double.parseDouble(terminal.nextLine());

                    } catch (Exception e) {
                        System.out.println("Valor inválido, operação abortada");
                        break;
                    }

                    if (!balanco.adicionarRegistro(categoria, nome, valor)) {
                        System.out.println("Categoria Inválida, operação abortada");
                    }
                    break;
                }
                case "2": {
                    String categoria = selecionarCategoria(terminal);
                    if(balanco.estaVazio(categoria)){
                        System.out.println("Não existem registros nesta categoria.");
                        break;
                    }
                    
                    balanco.imprimirRegistros(categoria);

                    System.out.print("Numero do registro: ");
                    int selecionado;
                    try {
                        selecionado = Integer.parseInt(terminal.nextLine());
                    } catch (Exception e) {
                        System.out.println("Não foi possivel ler o número, operação abortada");
                        break;
                    }

                    if(!balanco.indiceValido(categoria, selecionado)){
                        System.out.println("Numero inválido, operação abortada");
                        break;
                    }
                    
                    balanco.imprimirRegistro(categoria, selecionado);

                    boolean resposta = simOuNao("Alterar nome (S/N)? ", terminal);
                    if (resposta) {
                        System.out.print("Digite o novo nome: ");
                        String nome = terminal.nextLine();
                        balanco.alterarRegistro(categoria, nome, selecionado);
                    }

                    resposta = simOuNao("Alterar valor (S/N)? ", terminal);
                    if (resposta) {
                        System.out.print("Digite o novo valor: ");

                        int valor;
                        try {
                            valor = Integer.parseInt(terminal.nextLine());
                        } catch (Exception e) {
                            System.out.println("Não foi possível ler o número, operação abortada");
                            break;
                        }

                        balanco.alterarRegistro(categoria, valor, selecionado);
                    }
                    break;
                }
                case "3": {
                    String categoria = selecionarCategoria(terminal);
                    if(balanco.estaVazio(categoria)){
                        System.out.println("Não existem registros nesta categoria.");
                        break;
                    }
                    
                    balanco.imprimirRegistros(categoria);

                    System.out.print("Numero do registro: ");

                    int numero;
                    try {
                        numero = Integer.parseInt(terminal.nextLine());
                    } catch (Exception e) {
                        System.out.println("Não foi possível ler o número, operação abortada");
                        break;
                    }

                    if(!balanco.removerRegistro(categoria, numero)){
                        System.out.println("Registro não existe");
                        break;
                    }
                    break;
                }
                case "4":{
                    System.out.print("Digite o nome do arquivo: ");
                    String nomeArquivo = terminal.nextLine();
                    balanco.gerarArquivo(nomeArquivo);
                    break;
                }
            }
        }
    }

    public static String selecionarCategoria(Scanner terminal) {
        System.out.println("Selecione a categoria desejada: ");
        System.out.println("1: Ativo Circulante");
        System.out.println("2: Ativo Não Circulante");
        System.out.println("3: Passivo Circulante");
        System.out.println("4: Passivo Não Circulante");
        System.out.println("5: Patrimônio Líquido");

        int input = 0;
        do {
            try {
                input = Integer.parseInt(terminal.nextLine());
            } catch (Exception e) {
                System.out.println("Digite um número");
            }
        } while (input < 1 || input > 5);

        switch (input) {
            case 1:
                return "AC";
            case 2:
                return "ANC";
            case 3:
                return "PC";
            case 4:
                return "PNC";
            default:
                return "PL";
        }
    }

    public static boolean simOuNao(String mensagem, Scanner terminal) {
        String input;
        do {
            System.out.print(mensagem);
            input = terminal.nextLine();
        } while (!(input.toLowerCase().equals("s") || input.toLowerCase().equals("n")));

        return input.toLowerCase().equals("s");
    }
}
