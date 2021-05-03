import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/checksession")
public class CheckSession extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


       HttpSession  session= req.getSession();
       if(session.getAttribute("user").getClass() == User.class){
           resp.sendRedirect("/index.jsp");
       }else{
           resp.sendRedirect("/login.jsp");
       }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession  session= req.getSession();
        if(session.getAttribute("user").getClass() == User.class){
            resp.sendRedirect("/index.jsp");
        }else{
            resp.sendRedirect("/login.jsp");
        }

    }
}
