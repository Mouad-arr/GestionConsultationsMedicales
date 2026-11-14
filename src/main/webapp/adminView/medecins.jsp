<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestion des médecins | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="medecins"/>
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
                    <h1 class="page-title">Gestion des médecins</h1>
                    <p class="subtitle mb-0">
                        Consultez et gérez les comptes des médecins.
                    </p>
                </div>

                <a href="${ctx}/admin/medecins?action=new"
                   class="btn btn-primary">
                    <i class="fa-solid fa-plus me-2"></i>
                    Ajouter un médecin
                </a>
            </div>

            <!-- Statistics -->
            <div class="row g-3">
                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Total des médecins</div>
                                <div class="stat-value">
                                    ${medecins.size()}
                                </div>
                            </div>
                            <div class="stat-icon icon-blue">
                                <i class="fa-solid fa-user-doctor"></i>
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
                            <i class="fa-solid fa-user-doctor text-primary me-2"></i>
                            Liste des médecins
                        </h2>
                        <p class="subtitle mb-0">
                            Tous les médecins enregistrés dans le système.
                        </p>
                    </div>

                    <span class="badge bg-primary-subtle text-primary px-3 py-2">
                        ${medecins.size()} médecins
                    </span>
                </div>

                <!-- Search / filter -->
                <form class="row g-2 align-items-end mb-4" action="${ctx}/admin/medecins" method="get">
                    <div class="col-md-4">
                        <label class="form-label small text-muted mb-1">Rechercher</label>
                        <input type="text" id="searchInput" name="search" value="${param.search}"
                               class="form-control" placeholder="Nom du médecin...">
                    </div>

                    <div class="col-md-3">
                        <label class="form-label small text-muted mb-1">Spécialité</label>
                        <select name="specialite" id="specialiteSelect" class="form-select">
                            <option value="">Toutes les spécialités</option>
                            <c:forEach var="s" items="${specialites}">
                                <option value="${s}" ${param.specialite == s ? 'selected' : ''}>${s}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="col-auto">
                        <button type="submit" class="btn btn-primary">
                            <i class="fa-solid fa-magnifying-glass me-1"></i>
                            Filtrer
                        </button>
                    </div>

                    <div class="col-auto">
                        <a href="${ctx}/admin/medecins" class="btn btn-outline-secondary">
                            Réinitialiser
                        </a>
                    </div>
                </form>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Médecin</th>
                            <th>Spécialité</th>
                            <th>N° ordre</th>
                            <th>Email</th>
                            <th>Téléphone</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody id="medecinsBody">
                            <jsp:include page="partials/medecinsRows.jsp"/>
                        </tbody>
                    </table>
                </div>

            </div>

        </main>

        <script src="${ctx}/js/adminSearch.js"></script>
        <script>
            liveSearch({
                endpoint: '${ctx}/admin/medecins/search',
                tbodyId: 'medecinsBody',
                inputs: [
                    { id: 'searchInput', param: 'search' },
                    { id: 'specialiteSelect', param: 'specialite' }
                ]
            });
        </script>

    </body>
</html>
