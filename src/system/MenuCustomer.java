package system;
import service.ManagerCustomer;
import java.util.Scanner;
public class MenuCustomer {
    public static void menuCustomer(){
        Scanner scanner = new Scanner(System.in);
        ManagerCustomer managerCustomer = new ManagerCustomer();
        boolean check = true;
        while (check){
            try {
                System.out.println("|-----------Menu---------|");
                System.out.println("|  1. Add Customer       |");
                System.out.println("|  2. Edit Customer      |");
                System.out.println("|  3. Delete Customer    |");
                System.out.println("|  4. Search by name     |");
                System.out.println("|  5. show all customer  |");
                System.out.println("|  0. Back               |");
                System.out.println("|------------------------|");

                System.out.println("0. Back");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> managerCustomer.add(managerCustomer.returnCustomer(managerCustomer.returnSize()));
                    case 2 -> managerCustomer.edit(managerCustomer.returnCustomer(managerCustomer.returnSize()));
                    case 3 -> managerCustomer.delete();
                    case 4 -> managerCustomer.searchByName();
                    case 5 -> managerCustomer.displayCustomer();
                    case 0 -> check = false;
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
