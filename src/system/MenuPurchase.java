package system;
import service.PurchaseManager;
import java.util.Scanner;
public class MenuPurchase {
    public static void menuPurchase(String str){
        //String account = str;
        Scanner scanner = new Scanner(System.in);
        PurchaseManager purchaseManager = new PurchaseManager();
        boolean check = true;
        while (check) {
            try {
                System.out.println("|------Menu------|");
                System.out.println("|  1. Purchase   |");
                System.out.println("|  2. Show Cart  |");
                System.out.println("|  0. Back       |");
                System.out.println("|----------------|");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> purchaseManager.choice();
                    case 2 -> purchaseManager.showCart(str);
                    case 0 -> check = false;
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
