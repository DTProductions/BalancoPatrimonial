
import demonstrativos.BalancoPatrimonial;
import java.util.Scanner;
public class Program {

    public static void main(String[] args) {

        Scanner terminal = new Scanner(System.in);
        BalancoPatrimonial balanco = new BalancoPatrimonial();
        UserInterface programa = new UserInterface(terminal, balanco);
        programa.iniciar();
    }
}