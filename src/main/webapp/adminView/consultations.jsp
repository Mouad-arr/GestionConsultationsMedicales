<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestion des consultations | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="consultations"/>
        </jsp:include>

        <main class="main-content">

            <c:if test="${not empty sessionScope.flashError}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    <i class="fa-solid fa-triangle-exclamation me-2"></i>
                    <c:out value="${sessionScope.flashError}"/>
                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Fermer"></button>
                </div>
                <c:remove var="flashError" scope="session"/>
            </c:if>

            <!-- Header -->
            <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-2">
                <div>
                    <h1 class="page-title">Gestion des consultations</h1>
                    <p class="subtitle mb-0">
                        Vue globale de toutes les consultations de la clinique.
                    </p>
                </div>

                <a href="${ctx}/admin/consultations?action=new"
                   class="btn btn-primary">
                    <i class="fa-solid fa-plus me-2"></i>
                    Nouvelle consultation
                </a>
            </div>

            <!-- Statistics -->
            <div class="row g-3">
                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Total des consultations</div>
                                <div class="stat-value">
                                    ${consultations.size()}
                                </div>
                            </div>
                            <div class="stat-icon icon-orange">
                                <i class="fa-solid fa-calendar-check"></i>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Table -->
            <div class="content-card">

                <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-2">
                    <div>
                        <h2 class="section-title mb-1">
                            <i class="fa-solid fa-calendar-days text-primary me-2"></i>
                            Toutes les consultations
                        </h2>
                        <p class="subtitle mb-0">
                            L'ensemble des consultations, tous médecins confondus.
                        </p>
                    </div>

                    <span class="badge bg-primary-subtle text-primary px-3 py-2">
                        ${consultations.size()} consultations
                    </span>
                </div>

                <!-- Filters -->
                <form class="row g-2 align-items-end mb-4" action="${ctx}/admin/consultations" method="get">
                    <div class="col-md-3">
                        <label class="form-label small text-muted mb-1">Statut</label>
                        <select name="statut" id="statutSelect" class="form-select">
                            <option value="">Tous les statuts</option>
                            <option value="Planifie" ${param.statut == 'Planifie' ? 'selected' : ''}>Planifiée</option>
                            <option value="TERMINEE" ${param.statut == 'TERMINEE' ? 'selected' : ''}>Terminée</option>
                            <option value="ANNULEE" ${param.statut == 'ANNULEE' ? 'selected' : ''}>Annulée</option>
                        </select>
                    </div>

                    <div class="col-md-3">
                        <label class="form-label small text-muted mb-1">Date</label>
                        <input type="date" id="dateInput" name="date" value="${param.date}" class="form-control">
                    </div>

                    <div class="col-md-3">
                        <label class="form-label small text-muted mb-1">Médecin</label>
                        <input type="text" id="medecinInput" name="medecin" value="${param.medecin}"
                               class="form-control" placeholder="Nom du médecin...">
                    </div>

                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">
                            <i class="fa-solid fa-magnifying-glass me-1"></i>
                            Filtrer
                        </button>
                    </div>

                    <div class="col-auto">
                        <a href="${ctx}/admin/consultations" class="btn btn-outline-secondary">
                            Réinitialiser
                        </a>
                    </div>
                </form>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Patient</th>
                            <th>Médecin</th>
                            <th>Date</th>
                            <th>Heure</th>
                            <th>Motif</th>
                            <th>Statut</th>
                        </tr>
                        </thead>

                        <tbody id="consultationsBody">
                            <jsp:include page="partials/consultationsRows.jsp"/>
                        </tbody>
                    </table>
                </div>

            </div>

        </main>

        <script src="${ctx}/js/adminSearch.js"></script>
        <script>
            liveSearch({
                endpoint: '${ctx}/admin/consultations/search',
                tbodyId: 'consultationsBody',
                inputs: [
                    { id: 'statutSelect', param: 'statut' },
                    { id: 'dateInput', param: 'date' },
                    { id: 'medecinInput', param: 'medecin' }
                ]
            });
        </script>

    </body>
</html>
