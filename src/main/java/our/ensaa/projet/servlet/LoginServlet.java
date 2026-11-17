package our.ensaa.projet.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import our.ensaa.projet.entity.Admin;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.service.AuthService;
import our.ensaa.projet.service.AuthServiceImpl;


@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {

    private AuthService authService;


    @Override
    public void init() {
        this.authService = new AuthServiceImpl();
    }

    @Override
    protected void doGet(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(jakarta.servlet.http.HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response) throws jakarta.servlet.ServletException, java.io.IOException {

        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String role = request.getParameter("role");

        switch (role) {
            case "admin":
                Admin admin = authService.getAdmin(email, password);
                if(admin !=null){
                    request.getSession().setAttribute("admin", admin);
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else {
                    request.setAttribute("errorMessage", "Invalid email or password");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            case "medecin":
                Medecin medecin = authService.getMedecin(email, password);
                if(medecin!=null){
                    request.getSession().setAttribute("medecin", medecin);
                    response.sendRedirect(request.getContextPath() + "/medecin");
                } else {
                    request.setAttribute("errorMessage", "Invalid email or password");
                    request.getRequestDispatcher("/login.jsp").forward(request, response);
                }
                break;
            default:
                request.setAttribute("errorMessage", "Invalid role");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
