package our.ensaa.projet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.tool.schema.spi.ScriptTargetOutput;
import our.ensaa.projet.entity.*;
import our.ensaa.projet.service.MedcinTraitement;
import our.ensaa.projet.service.MedecinService;

import java.io.IOException;
import java.util.ArrayList;
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

        Medecin medecin = (Medecin) request.getSession(false).getAttribute("medecin");
        if(medecin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String path = request.getServletPath();
        Long medecinId = medecin.getId();


        if ("annuler".equals(request.getParameter("action"))) {
            annulerConsultation(request, response);
            return ;
        }

        switch (path) {

            case "/medecin" -> dashboardMedecin(request,response,medecinId);

            case "/medecin/planning" -> getPlanning(request,response,medecinId);

            case "/medecin/consultations" -> getConsultations(request,response,medecinId);

            case "/medecin/consultation" -> getConsultation(request,response,medecinId);

            case "/medecin/diagnostic" -> getDiagnosticForm(request,response);

            case "/medecin/logout" -> {
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }

            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Medecin medecin = (Medecin) request.getSession(false).getAttribute("medecin");
        if(medecin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String path = request.getServletPath();
        switch(path){
            case "/medecin/diagnostic" -> updateDiagnostic(request,response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void annulerConsultation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
    }

    private void dashboardMedecin(HttpServletRequest request, HttpServletResponse response , Long medecinId) throws ServletException, IOException {
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

    private void getPlanning(HttpServletRequest request, HttpServletResponse response ,Long medecinId) throws ServletException, IOException {
        request.setAttribute("medecin",
                medcinTraitement.getInfoMedicinById(medecinId));
        request.setAttribute("upcomingConsultations",
                medcinTraitement.getUpcomingConsultationsByMedcinId(medecinId));
        request.setAttribute("first3UpcomingConsultations",
                medcinTraitement.getLesTroisPremiersUpcomingConsultationsByMedecinId(medecinId));

        request.getRequestDispatcher(
                "/medecinView/planning.jsp"
        ).forward(request, response);
    }

    private void getConsultations(HttpServletRequest request, HttpServletResponse response , Long medecinId) throws ServletException, IOException {
        request.setAttribute("medecin",
                medcinTraitement.getInfoMedicinById(medecinId));
        request.setAttribute("consultations",
                medcinTraitement.getHistoriqueConsultationsByMedcinId(medecinId));

        request.getRequestDispatcher(
                "/medecinView/consultations.jsp"
        ).forward(request, response);
    }

    private void getConsultation(HttpServletRequest request, HttpServletResponse response , Long medecinId) throws ServletException, IOException {
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

    public void getDiagnosticForm(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

        request.setAttribute("ConsultationId" , request.getParameter("ConsultationId"));
        request.getRequestDispatcher("/medecinView/formDiagnostic.jsp").forward(request, response);
    }

    private void updateDiagnostic(HttpServletRequest request , HttpServletResponse response) throws ServletException, IOException {

        try {
            Long consultationId = Long.valueOf(
                    request.getParameter("consultationId")
            );

            String description = request.getParameter("description");

            List<Prescription> prescriptions = new ArrayList<>();

            int i = 0;

            while (request.getParameter("prescription[" + i + "].medicament") != null) {

                String medicament = request.getParameter("prescription[" + i + "].medicament");
                String posologie = request.getParameter("prescription[" + i + "].posologie");
                String duree = request.getParameter("prescription[" + i + "].duree");
                String instructions = request.getParameter("prescription[" + i + "].instructions");

                if (medicament != null && !medicament.isBlank()) {
                    Prescription prescription = new Prescription();
                    prescription.setMedicament(medicament);
                    prescription.setPosologie(posologie);
                    if (duree != null && !duree.isBlank()) {
                        prescription.setDureeJours(Integer.parseInt(duree));
                    }
                    prescription.setInstructions(instructions);

                    prescriptions.add(prescription);
                }

                i++;
            }
            Diagnostic diagnostic = new Diagnostic();
            diagnostic.setDescription(description);
            diagnostic.setPrescriptions(prescriptions);

            medcinTraitement.updateDiagnostic(consultationId, diagnostic);

            response.sendRedirect(
                    request.getContextPath() + "/medecin/consultations"
            );

        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        }
    }
}
