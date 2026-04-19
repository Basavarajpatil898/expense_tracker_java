import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        manager.loadFromFile();

        while (true) {
            System.out.println("\n1.Add 2.View 3.Total 4.Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount: ");
                    double amt = sc.nextDouble();
                    System.out.print("Enter category: ");
                    String cat = sc.next();
                    manager.addExpense(amt, cat);
                    break;

                case 2:
                    manager.viewExpenses();
                    break;

                case 3:
                    manager.showTotal();
                    break;

                case 4:
                    return;
            }
        }
    }
}