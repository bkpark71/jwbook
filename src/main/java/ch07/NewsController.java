package ch07;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/news.nhn")
@MultipartConfig(maxFileSize = 1024*1024*2 , location = "C:\\spring_study\\jwbook\\src\\main\\webapp\\img")
public class NewsController extends HttpServlet {
    private NewsDAO newsDAO;
    private ServletContext ctx;
    private final String START_PAGE = "/ch07/newsList.jsp"; // ?

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        newsDAO = new NewsDAO();
        ctx = getServletContext();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String action = req.getParameter("action");
        String view= null;
        Method m;
        System.out.println("action == " + action);
        if(action == null) {
            ctx.getRequestDispatcher("/news.nhn?action=list").forward(req,resp);
        } else {
            try {
                m = this.getClass().getMethod(action, HttpServletRequest.class);
                view = (String)m.invoke(this, req);
            } catch (Exception e){
                e.printStackTrace();
            }
            if(view.startsWith("redirect:/")){
                view = view.substring("redirect:/".length());
                resp.sendRedirect(view);
            } else {
                ctx.getRequestDispatcher("/ch07/"+ view).forward(req,resp);
            }
        }
    }

    public String addNews(HttpServletRequest req){
        News news = new News();
        try {
            Part part = req.getPart("img");
            System.out.println("part == " + part);
            String fileName = part.getSubmittedFileName();
            System.out.println("fileName == " + fileName);
            if(fileName != null && !fileName.isEmpty()){
                part.write(fileName);
            }
            BeanUtils.populate(news, req.getParameterMap());
            news.setImg("/img/" + fileName);
            newsDAO.addNews(news);
        } catch (Exception e) {
            e.printStackTrace();
            ctx.log("뉴스 등록 과정에서 문제 발생!");
            req.setAttribute("error", "뉴스가 정상적으로 등록되지 않았습니다.");
            return list(req);
        } 
        return "redirect:/news.nhn?action=list";
    }

    public String list(HttpServletRequest req){
        String err = (String)req.getAttribute("error");
        System.out.println("err == " + err);
        try {
            List<News> list = newsDAO.getAll();
            System.out.println("list ==" + list);
            req.setAttribute("newsList", list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "newsList.jsp";
    }

    public String delNews(HttpServletRequest req){
        try {
            newsDAO.delNews(Integer.parseInt(req.getParameter("aid")));
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.log("뉴스를 삭제하는 과정에서 문제 발생!");
            req.setAttribute("error", "뉴스를 정상적으로 삭제하지 못했습니다.");
            return list(req);
        }
        return "redirect:/news.nhn?action=list";
    }

    public String getNews(HttpServletRequest req){
        try {
            News news = newsDAO.getNews(Integer.parseInt(req.getParameter("aid")));
            req.setAttribute("news", news);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.log("뉴스를 가져오는 과정에서 문제 발생!");
            req.setAttribute("error", "뉴스를 정상적으로 가져오지 못했습니다.");
        }
        return "newsView.jsp";
    }
}