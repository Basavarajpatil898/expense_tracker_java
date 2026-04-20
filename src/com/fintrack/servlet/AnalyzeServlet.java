package com.fintrack.servlet;

import com.fintrack.dao.ExpenseFileDAO;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class AnalyzeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        try {
            ExpenseFileDAO dao = new ExpenseFileDAO();
            Map<String, Double> totals = dao.getCategoryTotals();

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print("{");
            int i = 0;
            for (Map.Entry<String, Double> entry : totals.entrySet()) {
                out.printf("\"%s\": %.2f", entry.getKey(), entry.getValue());
                if (++i < totals.size()) out.print(",");
            }
            out.print("}");
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}