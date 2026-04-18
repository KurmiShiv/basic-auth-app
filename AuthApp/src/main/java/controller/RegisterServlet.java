package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.DBConnection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
                          throws ServletException, IOException {

        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();

        String fullname = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Connection con = null;
        PreparedStatement ps = null;
        PreparedStatement checkPs = null;
        ResultSet rs = null;

        try {

            con = DBConnection.getConnection();

            // Check if email already exists
            String checkSql = "SELECT email FROM EOUser WHERE email = ?";
            checkPs = con.prepareStatement(checkSql);
            checkPs.setString(1, email);

            rs = checkPs.executeQuery();

            if (rs.next()) {
                out.print("Email already registered");
                return;
            }

            // Insert user
            String insertSql =
                "INSERT INTO EOUser(fullname, email, password) VALUES (?, ?, ?)";

            ps = con.prepareStatement(insertSql);
            ps.setString(1, fullname);
            ps.setString(2, email);
            ps.setString(3, password);

            int row = ps.executeUpdate();

            if (row > 0) {
                out.print("success");
            } else {
                out.print("fail");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.print("error");
        } finally {

            try { if (rs != null) rs.close(); } catch(Exception e) {}
            try { if (checkPs != null) checkPs.close(); } catch(Exception e) {}
            try { if (ps != null) ps.close(); } catch(Exception e) {}
            try { if (con != null) con.close(); } catch(Exception e) {}

        }
    }
}