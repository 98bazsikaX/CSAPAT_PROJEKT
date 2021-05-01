import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/players")
public class PlayerServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println(this.getServletName() + " initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        resp.getWriter().append("Hellocskahh <3");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
        System.out.println(this.getServletName() + " serviceban vagyunk");
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println(this.getServletName() + "  Destroyed");
    }
}
