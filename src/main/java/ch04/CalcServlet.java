package ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/calc")
public class CalcServlet extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    int n1 = Integer.parseInt(req.getParameter("n1"));
    int n2 = Integer.parseInt(req.getParameter("n2"));
    String op = req.getParameter("op");
    int result = 0;
    switch (op) {
      case "+" : result = n1 + n2 ; break;
      case "-" : result = n1 - n2 ; break;
      case "*" : result = n1 * n2 ; break;
      case "/" : result = n1 / n2 ; break;
    }

    resp.setContentType("text/html; charset=utf-8");
    resp.getWriter().append("<html><head></head><body>")
        .append("<h2>계산기 서블릿</h2><hr>")
        .append("계산결과 " + n1 + op + n2)
        .append("= " + result)
        .append("</body></html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}