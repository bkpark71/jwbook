package ch06;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
            getServletContext().getRequestDispatcher("/student?action=list").forward(req,resp);
        } else {
            switch(action) {
                case "list" :
                    view=list(req,resp);
                    getServletContext().getRequestDispatcher("/ch06/" + view).forward(req,resp);
                    break;
                case "update" :
                    view = update(req,resp);
                    if(method.equals("GET"))
                        getServletContext().getRequestDispatcher("/ch06/" + view).forward(req,resp);
                    else if(method.equals("POST"))
                        resp.sendRedirect(view);
                    break;
                case "delete" :
                    view = delete(req,resp);
                    resp.sendRedirect(view);
                    break;
                case "insert" :
                    try {
                        view = insert(req,resp);
                        resp.sendRedirect(view);
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
            req.setAttribute("s", service.findById(Integer.parseInt(req.getParameter("id"))));
            return "studentForm.jsp";
        } else if (method.equals("POST")){
            service.update(Integer.parseInt(req.getParameter("id")), req.getParameter("email"));
            req.setAttribute("s", service.findById(Integer.parseInt(req.getParameter("id"))));
            return "/student?action=list";
        } else {
            return null;
        }
    }

    private String insert(HttpServletRequest req, HttpServletResponse resp) throws InvocationTargetException, IllegalAccessException {
        String method = req.getMethod();
        if (method.equals("POST")) {
            Student s = new Student();
            BeanUtils.populate(s, req.getParameterMap());
            service.insert(s);
            return "/student?action=list";
        } else {
            return null;
        }
    }

    private String delete(HttpServletRequest req, HttpServletResponse resp){
        service.delete(Integer.parseInt(req.getParameter("id")));
        return "/student?action=list";
    }
}