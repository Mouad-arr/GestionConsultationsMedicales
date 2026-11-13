package our.ensaa.projet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.StatusConsultation;
import our.ensaa.projet.service.MedcinTraitement;
import our.ensaa.projet.service.MedecinService;

import java.io.IOException;
import java.util.List;

@WebServlet({"/medecin" , "/medecin/planning" , "/medecin/consultations" , "/medecin/consultation" ,"/medecin/diagnostic", "/medecin/logout"})
public class MedecinServlet extends HttpServlet {

    private  MedcinTraitement medcinTraitement;

    @Override
    public void init() throws ServletException {
        medcinTraitement = new MedecinService();
    }
    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws ServletException, IOException {

        String path = request.getServletPath();
        Long medecinId = 1L;


        if (request.getParameter("annuler") != null) {

            Long consultationId = Long.parseLong(
                    request.getParameter("ConsultationId")
            );

            medcinTraitement.updateConsultation(
                    consultationId,
                    StatusConsultation.ANNULEE
            );

            response.sendRedirect(
                    request.getContextPath() + "/medecin/consultations"
            );
            return;
        }

        switch (path) {

            case "/medecin" -> {

                Medecin medecin =
                        medcinTraitement.getInfoMedicinById(medecinId);

                request.setAttribute("upcomingConsultations",
                        medcinTraitement.getUpcomingConsultationsByMedcinId(medecinId));

                request.setAttribute("consultations",
                        medcinTraitement.getHistoriqueConsultationsByMedcinId(medecinId));

                List<Consultation> first3UpcomingConsultations =
                        medcinTraitement.getLesTroisPremiersUpcomingConsultationsByMedecinId(medecinId);

                List<Consultation> lastFinishedConsultations =
                        medcinTraitement.getLesTroisDerniersConsultationsTerminerByMedecinId(medecinId);

                request.setAttribute("medicine", medecin);
                request.setAttribute("first3UpcomingConsultations", first3UpcomingConsultations);
                request.setAttribute("lastFinishedConsultations", lastFinishedConsultations);

                request.getRequestDispatcher(
                        "/medecinView/index.jsp"
                ).forward(request, response);
            }

            case "/medecin/planning" -> {

                request.setAttribute("upcomingConsultations",
                        medcinTraitement.getUpcomingConsultationsByMedcinId(medecinId));

                request.getRequestDispatcher(
                        "/medecinView/planning.jsp"
                ).forward(request, response);
            }

            case "/medecin/consultations" -> {

                request.setAttribute("consultations",
                        medcinTraitement.getHistoriqueConsultationsByMedcinId(medecinId));

                request.getRequestDispatcher(
                        "/medecinView/consultations.jsp"
                ).forward(request, response);
            }

            case "/medecin/consultation" -> {

                String id = request.getParameter("ConsultationId");

                if (id != null) {
                    Consultation consultation =
                            medcinTraitement.getConsultationById(
                                    Long.parseLong(id), medecinId
                            );

                    request.setAttribute("consultation", consultation);
                }

                request.getRequestDispatcher(
                        "/medecinView/consultation.jsp"
                ).forward(request, response);
            }

            case "/medecin/diagnostic" -> {

                request.getRequestDispatcher(
                        "/medecinView/formDiagnostic.jsp"
                ).forward(request, response);
            }

            default -> response.sendError(
                    HttpServletResponse.SC_NOT_FOUND
            );
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch(path){
            case "/medecin/diagnostic" -> {
                try {
                    Long consultationId = Long.valueOf(
                            request.getParameter("consultationId")
                    );

                    String description = request.getParameter("description");

                    medcinTraitement.updateDiagnostic(consultationId, description);

                    response.sendRedirect(
                            request.getContextPath() + "/medecin/consultations"
                    );

                } catch (IllegalArgumentException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
                }
            }
        }
    }
}
