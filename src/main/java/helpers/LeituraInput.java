package helpers;

import java.util.Scanner;

public class LeituraInput {

    public static boolean simOuNao(String mensagem, Scanner terminal) {
        String input;
        do {
            System.out.print(mensagem);
            input = terminal.nextLine();
        } while (!(input.toLowerCase().equals("s") || input.toLowerCase().equals("n")));

        return input.toLowerCase().equals("s");
    }

    public static String selecionarCategoria(Scanner terminal) {
        System.out.println("Selecione a categoria desejada: ");
        System.out.println("1: Ativo Circulante");
        System.out.println("2: Ativo Não Circulante");
        System.out.println("3: Passivo Circulante");
        System.out.println("4: Passivo Não Circulante");
        System.out.println("5: Patrimônio Líquido");

        Integer input;
        do {
            input = ConversaoSegura.lerInt(terminal.nextLine());
            if(input == null){
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
}
