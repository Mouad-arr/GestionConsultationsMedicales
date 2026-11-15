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
                        Consultation #${ConsultationId}
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
                       value="${ConsultationId}"/>

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
                        Ajoutez un ou plusieurs médicaments si une prescription est nécessaire.
                    </p>
                </div>

                <!-- Liste des prescriptions -->
                <div id="prescriptionsContainer" class="d-flex flex-column gap-3">
                </div>

                <!-- Bouton ajouter -->
                <div class="mt-3">
                    <button type="button"
                            class="btn btn-outline-success"
                            onclick="ajouterPrescription()">
                        <i class="fa-solid fa-plus me-2"></i>
                        Ajouter une prescription
                    </button>
                </div>

                <!-- Template d'une prescription -->
                <template id="prescriptionTemplate">
                    <div class="card border-0 shadow-sm prescription-item">
                        <div class="card-header bg-light d-flex justify-content-between align-items-center py-3">
                            <span class="fw-semibold text-success">
                                <i class="fa-solid fa-pills me-2"></i>
                                Prescription <span class="prescription-number"></span>
                            </span>

                            <button type="button"
                                    class="btn btn-sm btn-outline-danger"
                                    onclick="supprimerPrescription(this)">
                                <i class="fa-solid fa-trash me-1"></i>
                                Supprimer
                            </button>
                        </div>

                        <div class="card-body">
                            <div class="row g-3">

                                <!-- Médicament -->
                                <div class="col-md-6">
                                    <label class="form-label fw-semibold">
                                        <i class="fa-solid fa-capsules text-muted me-1"></i>
                                        Médicament
                                    </label>

                                    <input type="text"
                                           class="form-control medical-input"
                                           data-field="medicament"
                                           placeholder="Nom du médicament">
                                </div>

                                <!-- Posologie -->
                                <div class="col-md-6">
                                    <label class="form-label fw-semibold">
                                        <i class="fa-solid fa-prescription-bottle-medical text-muted me-1"></i>
                                        Posologie
                                    </label>

                                    <input type="text"
                                           class="form-control medical-input"
                                           data-field="posologie"
                                           placeholder="Ex : 1 comprimé, 2 fois par jour">
                                </div>

                                <!-- Durée -->
                                <div class="col-md-6">
                                    <label class="form-label fw-semibold">
                                        <i class="fa-solid fa-calendar-days text-muted me-1"></i>
                                        Durée du traitement
                                    </label>

                                    <input type="number"
                                           class="form-control medical-input"
                                           data-field="duree"
                                           placeholder="Ex : 7 jours">
                                </div>

                                <!-- Instructions -->
                                <div class="col-md-6">
                                    <label class="form-label fw-semibold">
                                        <i class="fa-solid fa-clipboard-list text-muted me-1"></i>
                                        Instructions
                                    </label>

                                    <input type="text"
                                           class="form-control medical-input"
                                           data-field="instructions"
                                           placeholder="Instructions complémentaires">
                                </div>

                            </div>
                        </div>
                    </div>
                </template>

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
<script>
    let prescriptionIndex = 0;

    function ajouterPrescription() {

        const template = document.getElementById("prescriptionTemplate");
        const container = document.getElementById("prescriptionsContainer");

        const clone = template.content.cloneNode(true);

        const item = clone.querySelector(".prescription-item");


        item.querySelector(".prescription-number").textContent =
            container.children.length + 1;

        item.querySelectorAll("[data-field]").forEach(input => {
            const field = input.dataset.field;

            const fieldName = "prescription[" + prescriptionIndex + "]." + field;

            input.setAttribute("name", fieldName);

            console.log("Name:", input.getAttribute("name"));
        });

        prescriptionIndex++;

        container.appendChild(clone);
    }

    function supprimerPrescription(button) {

        button.closest(".prescription-item").remove();

        document.querySelectorAll(".prescription-item").forEach((item, index) => {

            item.querySelector(".prescription-number").textContent = index + 1;

            item.querySelectorAll("[data-field]").forEach(input => {
                input.setAttribute("name","prescription["+index+"]."+input.dataset.field);
            });
        });
    }

    document.addEventListener("DOMContentLoaded", () => {
        ajouterPrescription();
    });
</script>