import dao.UserDAO;
import dao.UserDAOImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/setemailpassword")
public class SetEmailPassword extends HttpServlet {
    private UserDAO dao = UserDAOImpl.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //System.out.println(req.getParameter("email") + req.getParameter("password") + req.getParameter("azon"));

        dao.EditUser(req.getParameter("email"),req.getParameter("password"), Integer.parseInt(req.getParameter("azon")));
        resp.sendRedirect("/login.jsp");
    }
}
