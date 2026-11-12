<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Établir un diagnostic | MediCare</title>
    </head>
    <body>

    <jsp:include page="navbar.jsp"/>

    <main class="main-content">

        <!-- Header -->
        <div class="mb-4">

            <h1 class="page-title">Établir un diagnostic</h1>

            <p class="subtitle">
                Renseignez les informations médicales et la prescription du patient.
            </p>

        </div>

        <!-- Consultation information -->
        <div class="content-card mt-0 mb-4">

            <div class="d-flex align-items-center gap-3">

                <div class="detail-icon icon-blue">
                    <i class="fa-solid fa-file-medical"></i>
                </div>

                <div>
                    <small class="text-muted">Consultation concernée</small>

                    <h5 class="fw-bold mb-0">
                        Consultation #${consultation.getId()}
                    </h5>
                </div>

            </div>

        </div>

        <!-- Diagnostic form -->
        <div class="content-card">

            <div class="mb-4">
                <h2 class="section-title">
                    <i class="fa-solid fa-stethoscope text-primary me-2"></i>
                    Informations du diagnostic
                </h2>

                <p class="subtitle">
                    Décrivez le diagnostic établi après la consultation.
                </p>
            </div>

            <form action="${ctx}/medecin/diagnostic" method="post">

                <input type="hidden"
                       name="consultationId"
                       value="${consultation.getId()}"/>

                <!-- Diagnostic description -->
                <div class="mb-4">

                    <label for="description" class="form-label fw-semibold">
                        Description du diagnostic
                        <span class="text-danger">*</span>
                    </label>

                    <textarea
                            class="form-control medical-input"
                            id="description"
                            name="description"
                            rows="5"
                            placeholder="Saisissez la description du diagnostic..."
                            required></textarea>

                    <div class="form-text">
                        Décrivez les conclusions médicales de la consultation.
                    </div>

                </div>

                <hr class="my-4">

                <!-- Prescription -->
                <div class="mb-4">

                    <h3 class="section-title">
                        <i class="fa-solid fa-prescription text-success me-2"></i>
                        Prescription médicale
                        <span class="text-muted fs-6 fw-normal">(Facultative)</span>
                    </h3>

                    <p class="subtitle">
                        Complétez les informations suivantes si une prescription est nécessaire.
                    </p>

                </div>

                <div class="row g-3">

                    <!-- Medicament -->
                    <div class="col-md-6">

                        <label for="medicament" class="form-label fw-semibold">
                            <i class="fa-solid fa-pills text-muted me-1"></i>
                            Médicament
                        </label>

                        <input
                                type="text"
                                class="form-control medical-input"
                                id="medicament"
                                name="medicament"
                                placeholder="Nom du médicament">

                    </div>

                    <!-- Posologie -->
                    <div class="col-md-6">

                        <label for="posologie" class="form-label fw-semibold">
                            <i class="fa-solid fa-prescription-bottle-medical text-muted me-1"></i>
                            Posologie
                        </label>

                        <input
                                type="text"
                                class="form-control medical-input"
                                id="posologie"
                                name="posologie"
                                placeholder="Ex : 1 comprimé, 2 fois par jour">

                    </div>

                    <!-- Duree -->
                    <div class="col-md-6">

                        <label for="duree" class="form-label fw-semibold">
                            <i class="fa-solid fa-calendar-days text-muted me-1"></i>
                            Durée du traitement
                        </label>

                        <input
                                type="text"
                                class="form-control medical-input"
                                id="duree"
                                name="duree"
                                placeholder="Ex : 7 jours">

                    </div>

                    <!-- Instructions -->
                    <div class="col-md-6">

                        <label for="instructions" class="form-label fw-semibold">
                            <i class="fa-solid fa-clipboard-list text-muted me-1"></i>
                            Instructions
                        </label>

                        <input
                                type="text"
                                class="form-control medical-input"
                                id="instructions"
                                name="instructions"
                                placeholder="Instructions complémentaires">

                    </div>

                </div>

                <!-- Actions -->
                <div class="d-flex justify-content-end gap-2 mt-5 flex-wrap">

                    <a href="${ctx}/medecin/planning"
                       class="btn btn-outline-secondary">

                        <i class="fa-solid fa-xmark me-1"></i>
                        Annuler

                    </a>

                    <button type="submit" class="btn btn-primary px-4">

                        <i class="fa-solid fa-check me-2"></i>
                        Soumettre le diagnostic

                    </button>

                </div>

            </form>

        </div>

    </main>

    </body>
</html>