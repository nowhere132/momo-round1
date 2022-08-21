import models.Coin;
import models.Product;
import services.impl.CoinFactory;
import services.impl.ProductFactory;
import services.VendingMachine;
import services.impl.VendingMachineImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {
    static VendingMachine machine = new VendingMachineImpl();

    public static void main(String[] args) throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (; ; ) {
            System.out.print("$ ");
            String line = reader.readLine().replace("$ ", "");
            if (line.equals("")) break;
            try {
                run(line);
            } catch (Exception ex) {
                System.err.println(ex);
                break;
            }
        }
    }

    private static void run(String command) throws Exception {
        var parts = command.toLowerCase().trim().split(" ");
        var type = parts[0];
        switch (type) {
            case "a" -> {
                if (parts.length != 2) {
                    throw new Exception("invalid command: " + command);
                }
                Coin c = CoinFactory.getCoin(parts[1]);
                machine.addCoin(c);
            }
            case "p" -> {
                if (parts.length != 2) {
                    throw new Exception("invalid command: " + command);
                }
                Product p = ProductFactory.getProduct(parts[1]);
                machine.pickProduct(p);
            }
            case "c" -> {
                if (parts.length != 1) {
                    throw new Exception("invalid command: " + command);
                }
                machine.clearCart();
            }
            case "rf" -> {
                if (parts.length != 1) {
                    throw new Exception("invalid command: " + command);
                }
                machine.refund();
            }
            case "rp" -> {
                if (parts.length != 1) {
                    throw new Exception("invalid command: " + command);
                }
                machine.releaseProducts();
            }
            default -> throw new Exception("invalid command: " + type);
        }
    }
}
