<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${empty patient.id ? 'Ajouter' : 'Modifier'} un patient | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="patients"/>
        </jsp:include>

        <main class="main-content">

            <div class="mb-4">
                <h1 class="page-title">
                    ${empty patient.id ? 'Ajouter un patient' : 'Modifier un patient'}
                </h1>
                <p class="subtitle">
                    Renseignez les informations du dossier patient.
                </p>
            </div>

            <div class="content-card">

                <form action="${ctx}/admin/patients" method="post">

                    <input type="hidden" name="id" value="${patient.id}"/>

                    <div class="row g-3">

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Nom <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="nom" class="form-control"
                                   value="${patient.nom}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Prénom <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="prenom" class="form-control"
                                   value="${patient.prenom}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                CIN <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="cin" class="form-control"
                                   value="${patient.cin}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Email <span class="text-danger">*</span>
                            </label>
                            <input type="email" name="email" class="form-control"
                                   value="${patient.email}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Téléphone</label>
                            <input type="text" name="telephone" class="form-control"
                                   value="${patient.telephone}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Date de naissance</label>
                            <input type="date" name="dateNaissance" class="form-control"
                                   value="${patient.dateNaissance}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">N° dossier</label>
                            <input type="text" name="numeroDossier" class="form-control"
                                   value="${patient.numeroDossier}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Groupe sanguin</label>
                            <input type="text" name="groupeSanguin" class="form-control"
                                   value="${patient.groupeSanguin}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Sexe <span class="text-danger">*</span>
                            </label>
                            <select name="sexe" class="form-select" required>
                                <option value="">-- Choisir --</option>
                                <option value="HOMME" ${patient.sexe.name() == 'HOMME' ? 'selected' : ''}>Homme</option>
                                <option value="FEMME" ${patient.sexe.name() == 'FEMME' ? 'selected' : ''}>Femme</option>
                            </select>
                        </div>

                        <div class="col-12">
                            <label class="form-label fw-semibold">Adresse</label>
                            <input type="text" name="adresse" class="form-control"
                                   value="${patient.adresse}">
                        </div>

                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4 flex-wrap">
                        <a href="${ctx}/admin/patients" class="btn btn-outline-secondary">
                            <i class="fa-solid fa-xmark me-1"></i>
                            Annuler
                        </a>
                        <button type="submit" class="btn btn-primary px-4">
                            <i class="fa-solid fa-check me-2"></i>
                            Enregistrer
                        </button>
                    </div>

                </form>

            </div>

        </main>

    </body>
</html>
