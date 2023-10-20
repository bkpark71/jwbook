package ch05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
    ProductService service;
    @Override
    public void init() throws ServletException {
        System.out.println("init이 수행됩니다.");
        service = new ProductService();
    }

    public ProductController() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String view="";
        System.out.println(action);
        if(action == null) {
            getServletContext().getRequestDispatcher("/pcontrol?action=list")
                    .forward(req,resp);
        } else {
            if (action.equals("list")) {
                view = list(req,resp);
            } else if(action.equals("info")) {
                view = info(req,resp);
            } else if(action.equals("update")) {
                view = update(req,resp);
            } else if(action.equals("insert")){
                System.out.println("insert가 실행됩니다.");
                view = insert(req,resp);
            }
            getServletContext().getRequestDispatcher("/ch05/"+view)
                    .forward(req,resp);
        }
    }

    private String list(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("products", service.findAll());
        return "productList.jsp";
    }

    private String info(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("p", service.findById(req.getParameter("id")));
        return "productInfo.jsp";
    }

    private String update(HttpServletRequest req, HttpServletResponse resp){
        String method = req.getMethod();
        System.out.println(method);
        if (method.equals("GET")) {
            req.setAttribute("p", service.findById(req.getParameter("id")));
            return "productForm.html";
        } else if (method.equals("POST")){
            System.out.println(req.getParameter("id") + ":" + Integer.parseInt(req.getParameter("price")));
            service.update(req.getParameter("id"), Integer.parseInt(req.getParameter("price")));
            return "productInfo.jsp";
        } else {
            return null;
        }
    }

    private String insert(HttpServletRequest req, HttpServletResponse resp){

        Product p = new Product(
                req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("maker"),
                Integer.parseInt(req.getParameter("price")),
                req.getParameter("date")
        );
        service.insert(p);
        return "productList.jsp";
    }
}