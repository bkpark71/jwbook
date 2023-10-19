package ch04;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class Login extends HttpServlet {
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    //
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    // redirect.jsp 페이지로 새로운 요청을 하는 방식
    // req 를 통해 데이터를 가지고 갈 수 없다.
    String id = req.getParameter("id");
    String pwd = req.getParameter("pwd");
    HttpSession session = req.getSession();
    session.setAttribute("id",id);
    String id1 = (String)session.getAttribute("id");
    resp.sendRedirect("/ch04/loginOK.jsp");
  }
}