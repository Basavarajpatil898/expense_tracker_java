package com.fintrack.servlet;

import com.fintrack.dao.ExpenseFileDAO;
import com.fintrack.model.Expense;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

public class AddExpenseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String dateStr = req.getParameter("date");
        String category = req.getParameter("category");
        double amount = Double.parseDouble(req.getParameter("amount"));
        String description = req.getParameter("description");

        Expense e = new Expense();
        e.setDate(LocalDate.parse(dateStr));
        e.setCategory(category);
        e.setAmount(amount);
        e.setDescription(description);

        try {
            ExpenseFileDAO dao = new ExpenseFileDAO();
            dao.addExpense(e);
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
        resp.sendRedirect("view");
    }
}