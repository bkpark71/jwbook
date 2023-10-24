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

@WebServlet("/news.nhn")
@MultipartConfig(maxFileSize = 1024*1024*2 , location = "c:/Temp/img")
public class NewsController extends HttpServlet {
    private NewsDAO newsDAO;
    private ServletContext ctx;
    private final String START_PAAGE = "/ch07/newsList.jsp";

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
        if(action == null) {
            action = "list";
        } else {
            try {
                m = this.getClass().getMethod(action, HttpServletRequest.class);
                view = (String)m.invoke(this, req, resp);
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

    public String addNews(HttpServletRequest req, HttpServletResponse resp){
        News news = new News();
        try {
            Part part = req.getPart("img");
            String fileName = part.getSubmittedFileName();
            if(fileName != null && !fileName.isEmpty()){
                part.write(fileName);
            }
            BeanUtils.populate(news, req.getParameterMap());
            news.setImg("/img/" + fileName);
            newsDAO.addNews(news);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return "redirect:/news.nhn?action=list";
    }

    public String list(HttpServletRequest req, HttpServletResponse resp){
        return null;
    }

    public String delNews(HttpServletRequest req, HttpServletResponse resp){
        return null;
    }

    public String getNews(HttpServletRequest req, HttpServletResponse resp){
        return null;
    }
}