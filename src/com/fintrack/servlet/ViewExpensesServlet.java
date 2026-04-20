package com.fintrack.servlet;

import com.fintrack.dao.ExpenseFileDAO;
import com.fintrack.model.Expense;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class ViewExpensesServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            ExpenseFileDAO dao = new ExpenseFileDAO();
            List<Expense> expenses = dao.getAllExpenses();
            req.setAttribute("expenses", expenses);
            RequestDispatcher rd = req.getRequestDispatcher("/viewExpenses.jsp");
            rd.forward(req, resp);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
