import java.util.*;
import ohtu.Multiplier;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        final int INPUT = 3;
        Multiplier kolme = new Multiplier(INPUT);
        System.out.println("anna luku ");
        int luku = scanner.nextInt();

        System.out.println("luku kertaa kolme on "+kolme.multipliedBy(luku) );
    }
}
