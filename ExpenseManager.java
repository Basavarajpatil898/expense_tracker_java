import java.io.*;
import java.util.*;

public class ExpenseManager {
    ArrayList<Expense> expenses = new ArrayList<>();
    String fileName = "expenses.txt";

    public void addExpense(double amount, String category) {
        expenses.add(new Expense(amount, category));
        saveToFile();
    }

    public void viewExpenses() {
        for (Expense e : expenses) {
            e.display();
        }
    }

    public void showTotal() {
        double total = 0;
        for (Expense e : expenses) {
            total += e.amount;
        }
        System.out.println("Total Spending: " + total);
    }

    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (Expense e : expenses) {
                pw.println(e.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    public void loadFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                expenses.add(Expense.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No previous data found.");
        }
    }
}