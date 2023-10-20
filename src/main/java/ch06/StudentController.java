package ch06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/student")
public class StudentController extends HttpServlet {
    StudentService service;
    @Override
    public void init() throws ServletException {
        service = new StudentService();
        service.open();
    }

    @Override
    public void destroy() {
        service.close();
    }

    public StudentController() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String method = req.getMethod();
        String view="";

        if(action == null) {
            getServletContext().getRequestDispatcher("/student?action=list")
                    .forward(req,resp);
        } else {
            switch(action) {
                case "list" : view=list(req,resp);
                    getServletContext().getRequestDispatcher("/ch06/studentList.jsp")
                            .forward(req,resp);
                    break;
                case "update" : view = update(req,resp); break;
                case "delete" : view = delete(req,resp); break;
                case "insert" : view = insert(req,resp); break;
            }
        }
    }

    private String list(HttpServletRequest req, HttpServletResponse resp){
        List<Student> students = service.findAll();
        req.setAttribute("students", students);
        return "studentList.jsp";

    }

    private String update(HttpServletRequest req, HttpServletResponse resp){
        String method = req.getMethod();
        if (method.equals("GET")) {
            req.setAttribute("p", service.findById(req.getParameter("id")));
            return "productForm.jsp";
        } else if (method.equals("POST")){
            service.update(req.getParameter("id"), Integer.parseInt(req.getParameter("price")));
            req.setAttribute("p", service.findById(req.getParameter("id")));
            return "productInfo.jsp";
        } else {
            return null;
        }
    }

    private String insert(HttpServletRequest req, HttpServletResponse resp){
        String method = req.getMethod();
        if (method.equals("POST")) {
            Product p = new Product();
            p.setId(req.getParameter("id"));
            p.setName(req.getParameter("name"));
            p.setMaker(req.getParameter("maker"));
            p.setPrice(Integer.parseInt(req.getParameter("price")));
            p.setDate(req.getParameter("date"));
            service.insert(p);
            return "/student?action=list";
        } else {
            return null;
        }
    }

    private String delete(HttpServletRequest req, HttpServletResponse resp){
        service.delete(req.getParameter("id"));
        return "/student?action=list";
    }
}