import java.util.Scanner;
import service.impl.AccountManager;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                AccountManager accountManager = new AccountManager();
                System.out.println("|----------Menu----------|");
                System.out.println("|       1. Sig In        |");
                System.out.println("|       2. Sig Up        |");
                System.out.println("|       3. Delete Acc    |");
                System.out.println("|       0. Exit          |");
                System.out.println("|------------------------|");
                System.out.println("         your choice:     ");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> accountManager.logIn(accountManager.login(accountManager.returnAccount()));
                    case 2 -> accountManager.add(accountManager.returnAccount());
                    case 3 -> accountManager.delete(accountManager.returnAccount());
                    case 0 -> System.exit(0);
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
