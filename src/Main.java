import java.util.Scanner;

import service.impl.AccountManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            AccountManager accountManager = new AccountManager();
            System.out.println("1. Sig In");
            System.out.println("2. Sig Up");
            System.out.println("3. delete account");
            System.out.println("0. Exit");
            System.out.println("you choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1-> accountManager.displayTest(accountManager.login(accountManager.returnAccount()));
                case 2 -> accountManager.add(accountManager.returnAccount());
                case 3 -> accountManager.delete(accountManager.returnAccount());
                case 0 -> System.exit(0);
            }
        }
    }
}
