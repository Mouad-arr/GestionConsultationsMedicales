package our.ensaa.projet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import our.ensaa.projet.entity.Consultation;
import our.ensaa.projet.entity.Medecin;
import our.ensaa.projet.entity.Patient;
import our.ensaa.projet.entity.Sexe;
import our.ensaa.projet.entity.StatusConsultation;
import our.ensaa.projet.service.AdminInterface;
import our.ensaa.projet.service.AdminService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet({"/admin", "/admin/medecins", "/admin/patients", "/admin/consultations", "/admin/logout"})
public class AdminServlet extends HttpServlet {

    private AdminInterface adminService;

    @Override
    public void init() {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/admin" -> showDashboard(request, response);
            case "/admin/medecins" -> showMedecins(request, response);
            case "/admin/patients" -> showPatients(request, response);
            case "/admin/consultations" -> showConsultations(request, response);
            case "/admin/logout" -> response.sendRedirect(request.getContextPath() + "/login");
            default -> response.sendRedirect(request.getContextPath() + "/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/admin/medecins" -> saveMedecin(request, response);
            case "/admin/patients" -> savePatient(request, response);
            default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    // ---------------------------------------------------------------------
    //  Dashboard
    // ---------------------------------------------------------------------

    private void showDashboard(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("medecins", adminService.getAllMedecins());
        request.setAttribute("patients", adminService.getAllPatients());
        request.setAttribute("consultations", adminService.getAllConsultations());
        request.setAttribute("consultationsDuJour", adminService.getConsultationsDuJour());

        request.getRequestDispatcher("/adminView/index.jsp").forward(request, response);
    }

    // ---------------------------------------------------------------------
    //  Médecins
    // ---------------------------------------------------------------------

    private void showMedecins(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deleteMedecin(request, response);
            return;
        }

        if ("new".equals(action)) {
            request.setAttribute("medecin", new Medecin());
            request.setAttribute("specialites", adminService.getAllSpecialites());
            request.getRequestDispatcher("/adminView/medecinForm.jsp").forward(request, response);
            return;
        }

        if ("edit".equals(action)) {
            Long id = parseLong(request.getParameter("id"));
            request.setAttribute("medecin", id == null ? null : adminService.getMedecinById(id));
            request.setAttribute("specialites", adminService.getAllSpecialites());
            request.getRequestDispatcher("/adminView/medecinForm.jsp").forward(request, response);
            return;
        }

        String search = request.getParameter("search");
        String specialite = request.getParameter("specialite");

        request.setAttribute("medecins", adminService.getMedecinsFiltered(search, specialite));
        request.setAttribute("specialites", adminService.getAllSpecialites());

        request.getRequestDispatcher("/adminView/medecins.jsp").forward(request, response);
    }

    private void deleteMedecin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = parseLong(request.getParameter("id"));
        try {
            if (id != null) {
                adminService.deleteMedecin(id);
            }
            response.sendRedirect(request.getContextPath() + "/admin/medecins");
        } catch (Exception e) {
            flashErrorAndRedirect(request, response, "/admin/medecins",
                    "Impossible de supprimer ce médecin.");
        }
    }

    private void saveMedecin(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Medecin medecin = buildMedecinFromRequest(request);

        Long id = parseLong(request.getParameter("id"));
        try {
            if (id == null) {
                adminService.saveMedecin(medecin);
            } else {
                medecin.setId(id);
                adminService.updateMedecin(medecin);
            }
            response.sendRedirect(request.getContextPath() + "/admin/medecins");
        } catch (Exception e) {
            flashErrorAndRedirect(request, response, "/admin/medecins",
                    "Erreur d'enregistrement : email ou numéro d'ordre déjà utilisé.");
        }
    }

    private Medecin buildMedecinFromRequest(HttpServletRequest request) {
        Medecin m = new Medecin();
        m.setNom(request.getParameter("nom"));
        m.setPrenom(request.getParameter("prenom"));
        m.setEmail(request.getParameter("email"));
        m.setTelephone(request.getParameter("telephone"));
        m.setDateNaissance(parseDate(request.getParameter("dateNaissance")));
        m.setSpecialite(request.getParameter("specialite"));
        m.setNumeroOrdre(request.getParameter("numeroOrdre"));
        m.setMotDePasse(request.getParameter("motDePasse"));
        return m;
    }

    // ---------------------------------------------------------------------
    //  Patients
    // ---------------------------------------------------------------------

    private void showPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            deletePatient(request, response);
            return;
        }

        if ("new".equals(action)) {
            request.setAttribute("patient", new Patient());
            request.getRequestDispatcher("/adminView/patientForm.jsp").forward(request, response);
            return;
        }

        if ("edit".equals(action)) {
            Long id = parseLong(request.getParameter("id"));
            request.setAttribute("patient", id == null ? null : adminService.getPatientById(id));
            request.getRequestDispatcher("/adminView/patientForm.jsp").forward(request, response);
            return;
        }

        String search = request.getParameter("search");
        List<Patient> patients = (search == null || search.isBlank())
                ? adminService.getAllPatients()
                : adminService.getPatientsFiltered(search);

        request.setAttribute("patients", patients);
        request.getRequestDispatcher("/adminView/patients.jsp").forward(request, response);
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Long id = parseLong(request.getParameter("id"));
        try {
            if (id != null) {
                adminService.deletePatient(id);
            }
            response.sendRedirect(request.getContextPath() + "/admin/patients");
        } catch (Exception e) {
            flashErrorAndRedirect(request, response, "/admin/patients",
                    "Impossible de supprimer ce patient.");
        }
    }

    private void savePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Patient patient = buildPatientFromRequest(request);

        Long id = parseLong(request.getParameter("id"));
        try {
            if (id == null) {
                adminService.savePatient(patient);
            } else {
                patient.setId(id);
                adminService.updatePatient(patient);
            }
            response.sendRedirect(request.getContextPath() + "/admin/patients");
        } catch (Exception e) {
            flashErrorAndRedirect(request, response, "/admin/patients",
                    "Erreur d'enregistrement : email, CIN ou numéro de dossier déjà utilisé.");
        }
    }

    private Patient buildPatientFromRequest(HttpServletRequest request) {
        Patient p = new Patient();
        p.setNom(request.getParameter("nom"));
        p.setPrenom(request.getParameter("prenom"));
        p.setEmail(request.getParameter("email"));
        p.setTelephone(request.getParameter("telephone"));
        p.setDateNaissance(parseDate(request.getParameter("dateNaissance")));
        p.setNumeroDossier(request.getParameter("numeroDossier"));
        p.setGroupeSanguin(request.getParameter("groupeSanguin"));
        p.setCin(request.getParameter("cin"));
        p.setAdresse(request.getParameter("adresse"));

        String sexe = request.getParameter("sexe");
        if (sexe != null && !sexe.isBlank()) {
            try {
                p.setSexe(Sexe.valueOf(sexe));
            } catch (IllegalArgumentException ignored) {
                // leave null; validation should happen upstream
            }
        }
        return p;
    }

    // ---------------------------------------------------------------------
    //  Consultations (read-only + filters)
    // ---------------------------------------------------------------------

    private void showConsultations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        StatusConsultation statut = parseStatus(request.getParameter("statut"));
        LocalDate date = parseDate(request.getParameter("date"));
        String medecin = request.getParameter("medecin");

        List<Consultation> consultations = adminService.getConsultationsFiltered(statut, date, medecin);

        request.setAttribute("consultations", consultations);
        request.getRequestDispatcher("/adminView/consultations.jsp").forward(request, response);
    }

    // ---------------------------------------------------------------------
    //  Helpers
    // ---------------------------------------------------------------------

    private Long parseLong(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return Long.parseLong(value.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private LocalDate parseDate(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(value.trim());
        } catch (Exception e) {
            return null;
        }
    }

    private StatusConsultation parseStatus(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return StatusConsultation.valueOf(value.trim());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    private void flashErrorAndRedirect(HttpServletRequest request, HttpServletResponse response,
                                       String path, String message) throws IOException {
        request.getSession().setAttribute("flashError", message);
        response.sendRedirect(request.getContextPath() + path);
    }

}
