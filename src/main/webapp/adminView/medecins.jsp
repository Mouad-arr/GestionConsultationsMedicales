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
                        <input type="text" name="search" value="${param.search}"
                               class="form-control" placeholder="Nom du médecin...">
                    </div>

                    <div class="col-md-3">
                        <label class="form-label small text-muted mb-1">Spécialité</label>
                        <select name="specialite" class="form-select">
                            <option value="">Toutes les spécialités</option>
                            <!-- TODO: populate dynamically from the DB -->
                            <option value="Cardiologie" ${param.specialite == 'Cardiologie' ? 'selected' : ''}>Cardiologie</option>
                            <option value="Pédiatrie" ${param.specialite == 'Pédiatrie' ? 'selected' : ''}>Pédiatrie</option>
                            <option value="Dermatologie" ${param.specialite == 'Dermatologie' ? 'selected' : ''}>Dermatologie</option>
                            <option value="Généraliste" ${param.specialite == 'Généraliste' ? 'selected' : ''}>Généraliste</option>
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

                        <tbody>
                        <c:forEach var="m" items="${medecins}">
                            <tr>
                                <td class="fw-semibold">#${m.id}</td>

                                <td>
                                    <div class="d-flex align-items-center gap-2">
                                        <div class="patient-avatar">
                                            <i class="fa-solid fa-user-doctor"></i>
                                        </div>
                                        <span class="fw-semibold">
                                            <c:out value="${m.getNom()} ${m.getPrenom()}"/>
                                        </span>
                                    </div>
                                </td>

                                <td>
                                    <span class="badge bg-primary-subtle text-primary">
                                        <c:out value="${m.getSpecialite()}"/>
                                    </span>
                                </td>

                                <td>
                                    <c:out value="${m.getNumeroOrdre()}"/>
                                </td>

                                <td>
                                    <c:out value="${m.getEmail()}"/>
                                </td>

                                <td>
                                    <c:out value="${m.getTelephone()}"/>
                                </td>

                                <td>
                                    <div class="d-flex gap-2">
                                        <a href="${ctx}/admin/medecins?action=edit&id=${m.id}"
                                           class="btn btn-sm btn-outline-primary">
                                            <i class="fa-solid fa-pen-to-square me-1"></i>
                                            Modifier
                                        </a>

                                        <a href="${ctx}/admin/medecins?action=delete&id=${m.id}"
                                           class="btn btn-sm btn-outline-danger"
                                           onclick="return confirm('Voulez-vous vraiment supprimer ce médecin ?');">
                                            <i class="fa-solid fa-trash-can me-1"></i>
                                            Supprimer
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty medecins}">
                            <tr>
                                <td colspan="7" class="text-center py-5">
                                    <div class="text-muted">
                                        <i class="fa-solid fa-user-doctor fs-1 mb-3"></i>
                                        <p class="fw-semibold mb-1">Aucun médecin enregistré</p>
                                        <small>Les médecins apparaîtront ici.</small>
                                    </div>
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>

            </div>

        </main>

    </body>
</html>
