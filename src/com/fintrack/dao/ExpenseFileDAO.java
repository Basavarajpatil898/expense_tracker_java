package com.fintrack.dao;

import com.fintrack.model.Expense;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ExpenseFileDAO {
    private static final String FILE_PATH = "D:/Tomcat 9.0_Tomcat9_/data_expense_tracker/expenses.txt";

    public ExpenseFileDAO() throws IOException {
        Path path = Paths.get(FILE_PATH);
        Files.createDirectories(path.getParent());
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public synchronized void addExpense(Expense e) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            bw.write(String.format("%s|%s|%.2f|%s",
                    e.getDate(), e.getCategory(), e.getAmount(), e.getDescription()));
            bw.newLine();
        }
    }

    public synchronized List<Expense> getAllExpenses() throws IOException {
        List<Expense> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    Expense e = new Expense();
                    e.setDate(LocalDate.parse(parts[0]));
                    e.setCategory(parts[1]);
                    e.setAmount(Double.parseDouble(parts[2]));
                    e.setDescription(parts[3]);
                    list.add(e);
                }
            }
        }
        return list;
    }

    public Map<String, Double> getCategoryTotals() throws IOException {
        return getAllExpenses().stream()
                .collect(Collectors.groupingBy(Expense::getCategory,
                         Collectors.summingDouble(Expense::getAmount)));
    }
}
