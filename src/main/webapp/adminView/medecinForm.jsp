<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>${empty medecin.id ? 'Ajouter' : 'Modifier'} un médecin | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="medecins"/>
        </jsp:include>

        <main class="main-content">

            <div class="mb-4">
                <h1 class="page-title">
                    ${empty medecin.id ? 'Ajouter un médecin' : 'Modifier un médecin'}
                </h1>
                <p class="subtitle">
                    Renseignez les informations du compte médecin.
                </p>
            </div>

            <div class="content-card">

                <form action="${ctx}/admin/medecins" method="post">

                    <input type="hidden" name="id" value="${medecin.id}"/>

                    <div class="row g-3">

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Nom <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="nom" class="form-control"
                                   value="${medecin.nom}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Prénom <span class="text-danger">*</span>
                            </label>
                            <input type="text" name="prenom" class="form-control"
                                   value="${medecin.prenom}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Email <span class="text-danger">*</span>
                            </label>
                            <input type="email" name="email" class="form-control"
                                   value="${medecin.email}" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Téléphone</label>
                            <input type="text" name="telephone" class="form-control"
                                   value="${medecin.telephone}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Date de naissance</label>
                            <input type="date" name="dateNaissance" class="form-control"
                                   value="${medecin.dateNaissance}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Spécialité</label>
                            <input type="text" name="specialite" class="form-control"
                                   value="${medecin.specialite}" list="specialitesList">
                            <datalist id="specialitesList">
                                <c:forEach var="s" items="${specialites}">
                                    <option value="${s}"></option>
                                </c:forEach>
                            </datalist>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">N° ordre</label>
                            <input type="text" name="numeroOrdre" class="form-control"
                                   value="${medecin.numeroOrdre}">
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Mot de passe <span class="text-danger">*</span>
                            </label>
                            <input type="password" name="motDePasse" class="form-control"
                                   value="${medecin.motDePasse}" required>
                        </div>

                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4 flex-wrap">
                        <a href="${ctx}/admin/medecins" class="btn btn-outline-secondary">
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
