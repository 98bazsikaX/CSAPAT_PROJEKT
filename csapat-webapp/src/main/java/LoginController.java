import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login_backend")
public class LoginController extends HttpServlet {

    private UserDAO dao = UserDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameter("username"));
        System.out.println(req.getParameter("password"));

        if( dao.hasPasswordAndEmail(req.getParameter("username"))){
           User log =  dao.login(req.getParameter("username"),req.getParameter("password"));

            HttpSession session = req.getSession();

            session.setAttribute("user",log);
            session.setAttribute("username",log.getUsername());
            resp.sendRedirect("/checksession");
        }else{
            int id = dao.getIDbyUsername(req.getParameter("username"));
            if(id==0){
                resp.sendRedirect("/index.jsp");
            }else{
                resp.sendRedirect("/setEmailPassword.jsp?id=" + id );
            }

        }
    }
}
