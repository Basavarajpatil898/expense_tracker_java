 
<%@ page import="java.util.List, com.fintrack.model.Expense" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Expense History - Expense Tracker</title>
</head>
<body>
<div class="container">
    <h2>Transaction History</h2>
    <table border="1">
        <tr><th>Date</th><th>Category</th><th>Amount</th><th>Description</th></tr>
        <% 
            List<Expense> expenses = (List<Expense>) request.getAttribute("expenses");
            if (expenses != null) {
                for (Expense e : expenses) {
        %>
        <tr>
            <td><%= e.getDate() %></td>
            <td><%= e.getCategory() %></td>
            <td><%= e.getAmount() %></td>
            <td><%= e.getDescription() %></td>
        </tr>
        <%      }
            } else { %>
        <tr><td colspan="4">No expenses yet. Add some!</td></tr>
        <% } %>
    </table>
    <br><a href="index.html"> Add new expense</a> | <a href="analyze.html">View analysis</a>
</div>
</body>
</html>