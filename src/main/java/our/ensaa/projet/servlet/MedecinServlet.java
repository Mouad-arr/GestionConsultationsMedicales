package our.ensaa.projet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.service.AdminService;
import our.ensaa.projet.service.MedcinTraitement;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MedecinServlet", value = "/medecin")
public class MedecinServlet extends HttpServlet {

    private  MedcinTraitement medcinTraitement;

    @Override
    public void init() throws ServletException {
        medcinTraitement = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        List<Consultation> consultations = medcinTraitement.getHistoriqueConsultationsByMedcinId(1L);
        List<Consultation> upcomingConsultations = medcinTraitement.getUpcomingConsultationsByMedcinId(1L);
        Medecin medecin = medcinTraitement.getInfoMedicinById(1L);
        List<Consultation> first3UpcomingConsultations = medcinTraitement.getLesTroisPremiersUpcomingConsultationsByMedecinId(1L);
        List<Consultation> lastFinishedConsultations = medcinTraitement.getLesTroisDerniersConsultationsTerminerByMedecinId(1L);

        request.setAttribute("first3UpcomingConsultations", first3UpcomingConsultations);
        request.setAttribute("lastFinishedConsultations", lastFinishedConsultations);
        request.setAttribute("consultations", consultations);
        request.setAttribute("upcomingConsultations", upcomingConsultations);
        request.setAttribute("medicine", medecin);
        request.getRequestDispatcher("/medecinView/index.jsp").forward(request, response);
    }
}
