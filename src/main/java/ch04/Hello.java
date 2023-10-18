package ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(description = "hello servlet", urlPatterns = "/hello")
public class Hello extends HttpServlet {
  public Hello() {
    super();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html; charset=UTF-8");
    resp.getWriter().append("<html><head></head><body>")
        .append("<h2>Hello Servlet</h2><hr>")
        .append("현재 날짜와 시간은 " + java.time.LocalDateTime.now())
        .append("</body></html>");
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    doGet(req, resp);
  }
}