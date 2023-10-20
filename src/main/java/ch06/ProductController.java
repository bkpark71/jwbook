package ch06;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/pcontrol")
public class ProductController extends HttpServlet {
    ProductServiceDAO service;
    @Override
    public void init() throws ServletException {
        service = new ProductServiceDAO();
        service.open();
    }

    @Override
    public void destroy() {
        service.open();
    }

    public ProductController() {
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String method = req.getMethod();
        String view="";
        System.out.println(action);
        if(action == null) {
            getServletContext().getRequestDispatcher("/pcontrol?action=list")
                    .forward(req,resp);
        } else {
            if (action.equals("list")) {
                view = list(req,resp);
                getServletContext().getRequestDispatcher("/ch06/"+view)
                        .forward(req,resp);
            } else if(action.equals("info")) {
                view = info(req, resp);
                getServletContext().getRequestDispatcher("/ch06/"+view)
                        .forward(req,resp);
            } else if(action.equals("insert")) {
                view = insert(req, resp);
                if(method.equals("GET")){
                    getServletContext().getRequestDispatcher("/ch06/"+view)
                            .forward(req,resp);
                } else if(method.equals("POST")) {
                    resp.sendRedirect(view);
                }
            } else if(action.equals("update")) {
                view = update(req,resp);
                getServletContext().getRequestDispatcher("/ch06/" + view)
                            .forward(req, resp);
            } else if(action.equals("delete")) {
                view = delete(req, resp);
                resp.sendRedirect(view);
            }
        }
    }

    private String list(HttpServletRequest req, HttpServletResponse resp){
        List<Product> products = service.findAll();
        req.setAttribute("products", products);
        return "productList.jsp";
    }

    private String info(HttpServletRequest req, HttpServletResponse resp){
        req.setAttribute("p", service.findById(req.getParameter("id")));
        return "productInfo.jsp";
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
        if (method.equals("GET")) {
            return "newProduct.jsp";
        } else if (method.equals("POST")) {
            Product p = new Product();
            p.setId(req.getParameter("id"));
            p.setName(req.getParameter("name"));
            p.setMaker(req.getParameter("maker"));
            p.setPrice(Integer.parseInt(req.getParameter("price")));
            p.setDate(req.getParameter("date"));
            service.insert(p);
            return "/pcontrol?action=list";
        } else {
            return null;
        }
    }

    private String delete(HttpServletRequest req, HttpServletResponse resp){
        service.delete(req.getParameter("id"));
        return "/pcontrol?action=list";
    }
}