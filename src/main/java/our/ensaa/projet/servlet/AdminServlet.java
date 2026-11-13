package our.ensaa.projet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.Patient;
import our.ensaa.projet.service.AdminService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/admin", "/admin/medecins", "/admin/patients", "/admin/consultations", "/admin/logout"})
public class AdminServlet extends HttpServlet {

    private AdminService adminService;

    @Override
    public void init() throws ServletException {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        // TODO: handle search/filter params once services are implemented:
        //   - search     (patients par nom/prénom/CIN, médecins par nom)
        //   - specialite (médecins)
        //   - statut / date / medecin (consultations)
        List<Medecin> medecins = adminService.getAllMedecins();
        List<Patient> patients = adminService.getAllPatients();
        List<Consultation> consultations = adminService.getAllConsultations();
        List<Consultation> consultationsDuJour = adminService.getConsultationsDuJour();

        // Guard against null (services return null until implemented).
        if (medecins == null) medecins = new ArrayList<>();
        if (patients == null) patients = new ArrayList<>();
        if (consultations == null) consultations = new ArrayList<>();
        if (consultationsDuJour == null) consultationsDuJour = new ArrayList<>();

        request.setAttribute("medecins", medecins);
        request.setAttribute("patients", patients);
        request.setAttribute("consultations", consultations);
        request.setAttribute("consultationsDuJour", consultationsDuJour);

        switch (path) {
            case "/admin" -> request.getRequestDispatcher("/adminView/index.jsp").forward(request, response);
            case "/admin/medecins" -> request.getRequestDispatcher("/adminView/medecins.jsp").forward(request, response);
            case "/admin/patients" -> request.getRequestDispatcher("/adminView/patients.jsp").forward(request, response);
            case "/admin/consultations" -> request.getRequestDispatcher("/adminView/consultations.jsp").forward(request, response);
            default -> request.getRequestDispatcher("/adminView/index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // TODO: handle add / edit / delete actions (medecins, patients).
    }
}
