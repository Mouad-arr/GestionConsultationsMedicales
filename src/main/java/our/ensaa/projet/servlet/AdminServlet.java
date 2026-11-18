package our.ensaa.projet.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import our.ensaa.projet.entity.*;
import our.ensaa.projet.service.AdminInterface;
import our.ensaa.projet.service.AdminService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@WebServlet({"/admin", "/admin/medecins", "/admin/patients", "/admin/consultations", "/admin/logout",
             "/admin/medecins/search", "/admin/patients/search", "/admin/consultations/search"})
public class AdminServlet extends HttpServlet {

    private AdminInterface adminService;

    @Override
    public void init() {
        adminService = new AdminService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Admin admin = (Admin) request.getSession(false).getAttribute("admin");
        if(admin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String path = request.getServletPath();

        switch (path) {
            case "/admin" -> showDashboard(request, response);
            case "/admin/medecins" -> showMedecins(request, response);
            case "/admin/patients" -> showPatients(request, response);
            case "/admin/consultations" -> showConsultations(request, response);
            case "/admin/medecins/search" -> searchMedecins(request, response);
            case "/admin/patients/search" -> searchPatients(request, response);
            case "/admin/consultations/search" -> searchConsultations(request, response);
            case "/admin/logout" -> {
                request.getSession().invalidate();
                response.sendRedirect(request.getContextPath() + "/login");
            }
            default -> response.sendRedirect(request.getContextPath() + "/admin");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Admin admin = (Admin) request.getSession(false).getAttribute("admin");
        if(admin == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        String path = request.getServletPath();

        switch (path) {
            case "/admin/medecins" -> saveMedecin(request, response);
            case "/admin/patients" -> savePatient(request, response);
            case "/admin/consultations" -> saveConsultation(request, response);
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

        if ("new".equals(request.getParameter("action"))) {
            request.setAttribute("patients", adminService.getAllPatients());
            request.setAttribute("medecins", adminService.getAllMedecins());
            request.getRequestDispatcher("/adminView/consultationForm.jsp").forward(request, response);
            return;
        }

        StatusConsultation statut = parseStatus(request.getParameter("statut"));
        LocalDate date = parseDate(request.getParameter("date"));
        String medecin = request.getParameter("medecin");

        List<Consultation> consultations = adminService.getConsultationsFiltered(statut, date, medecin);

        request.setAttribute("consultations", consultations);
        request.getRequestDispatcher("/adminView/consultations.jsp").forward(request, response);
    }

    // ---------------------------------------------------------------------
    //  Dynamic search (AJAX — returns the <tbody> fragment)
    // ---------------------------------------------------------------------

    private void searchMedecins(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        String specialite = request.getParameter("specialite");
        request.setAttribute("medecins", adminService.getMedecinsFiltered(search, specialite));
        request.getRequestDispatcher("/adminView/partials/medecinsRows.jsp").forward(request, response);
    }

    private void searchPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        List<Patient> patients = (search == null || search.isBlank())
                ? adminService.getAllPatients()
                : adminService.getPatientsFiltered(search);
        request.setAttribute("patients", patients);
        request.getRequestDispatcher("/adminView/partials/patientsRows.jsp").forward(request, response);
    }

    private void searchConsultations(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        StatusConsultation statut = parseStatus(request.getParameter("statut"));
        LocalDate date = parseDate(request.getParameter("date"));
        String medecin = request.getParameter("medecin");
        request.setAttribute("consultations", adminService.getConsultationsFiltered(statut, date, medecin));
        request.getRequestDispatcher("/adminView/partials/consultationsRows.jsp").forward(request, response);
    }

    private void saveConsultation(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        try {
            Long patientId = parseLong(request.getParameter("patientId"));
            Long medecinId = parseLong(request.getParameter("medecinId"));

            if (patientId == null || medecinId == null) {
                flashErrorAndRedirect(request, response, "/admin/consultations",
                        "Veuillez sélectionner un patient et un médecin.");
                return;
            }

            LocalDate date = parseDate(request.getParameter("date"));
            if (date == null) {
                flashErrorAndRedirect(request, response, "/admin/consultations",
                        "Veuillez renseigner une date valide.");
                return;
            }

            LocalTime heure = parseTime(request.getParameter("heure"));
            LocalDateTime dateConsultation = LocalDateTime.of(date, heure);

            String motif = request.getParameter("motif");

            adminService.createConsultation(patientId, medecinId, dateConsultation, motif);
            response.sendRedirect(request.getContextPath() + "/admin/consultations");
        } catch (IllegalArgumentException e) {
            flashErrorAndRedirect(request, response, "/admin/consultations", e.getMessage());
        } catch (Exception e) {
            flashErrorAndRedirect(request, response, "/admin/consultations",
                    "Erreur lors de la création de la consultation.");
        }
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

    private LocalTime parseTime(String value) {
        if (value == null || value.isBlank()) {
            return LocalTime.of(9, 0);
        }
        try {
            return LocalTime.parse(value.trim());
        } catch (Exception e) {
            return LocalTime.of(9, 0);
        }
    }

    private void flashErrorAndRedirect(HttpServletRequest request, HttpServletResponse response,
                                       String path, String message) throws IOException {
        request.getSession().setAttribute("flashError", message);
        response.sendRedirect(request.getContextPath() + path);
    }

}
