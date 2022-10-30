import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Casino casino = new Casino(1000000);

        System.out.println("\nWELCOME TO THE CASINO!!!\n");
        casino.playRoulette();
    }
}