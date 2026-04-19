public class Expense {
    double amount;
    String category;

    public Expense(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }

    public String toFileString() {
        return amount + "," + category;
    }

    public static Expense fromFileString(String line) {
        String[] parts = line.split(",");
        return new Expense(Double.parseDouble(parts[0]), parts[1]);
    }

    public void display() {
        System.out.println("Amount: " + amount + " | Category: " + category);
    }
}