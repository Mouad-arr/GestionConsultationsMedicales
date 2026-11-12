<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Gestion des patients | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="patients"/>
        </jsp:include>

        <main class="main-content">

            <!-- Header -->
            <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-2">
                <div>
                    <h1 class="page-title">Gestion des patients</h1>
                    <p class="subtitle mb-0">
                        Consultez et gérez les dossiers des patients.
                    </p>
                </div>

                <a href="${ctx}/admin/patients?action=new"
                   class="btn btn-primary">
                    <i class="fa-solid fa-plus me-2"></i>
                    Ajouter un patient
                </a>
            </div>

            <!-- Statistics -->
            <div class="row g-3">
                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Total des patients</div>
                                <div class="stat-value">
                                    ${patients.size()}
                                </div>
                            </div>
                            <div class="stat-icon icon-green">
                                <i class="fa-solid fa-hospital-user"></i>
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
                            <i class="fa-solid fa-hospital-user text-primary me-2"></i>
                            Liste des patients
                        </h2>
                        <p class="subtitle mb-0">
                            Tous les patients enregistrés dans le système.
                        </p>
                    </div>

                    <span class="badge bg-primary-subtle text-primary px-3 py-2">
                        ${patients.size()} patients
                    </span>
                </div>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Patient</th>
                            <th>Email</th>
                            <th>N° dossier</th>
                            <th>Groupe sanguin</th>
                            <th>Actions</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="p" items="${patients}">
                            <tr>
                                <td class="fw-semibold">#${p.id}</td>

                                <td>
                                    <div class="d-flex align-items-center gap-2">
                                        <div class="patient-avatar">
                                            <i class="fa-solid fa-user"></i>
                                        </div>
                                        <span class="fw-semibold">
                                            <c:out value="${p.getNom()} ${p.getPrenom()}"/>
                                        </span>
                                    </div>
                                </td>

                                <td>
                                    <c:out value="${p.getEmail()}"/>
                                </td>

                                <td>
                                    <span class="badge bg-secondary-subtle text-secondary">
                                        <c:out value="${p.getNumeroDossier()}"/>
                                    </span>
                                </td>

                                <td>
                                    <c:out value="${p.getGroupeSanguin()}"/>
                                </td>

                                <td>
                                    <div class="d-flex gap-2">
                                        <a href="${ctx}/admin/patients?action=edit&id=${p.id}"
                                           class="btn btn-sm btn-outline-primary">
                                            <i class="fa-solid fa-pen-to-square me-1"></i>
                                            Modifier
                                        </a>

                                        <a href="${ctx}/admin/patients?action=delete&id=${p.id}"
                                           class="btn btn-sm btn-outline-danger"
                                           onclick="return confirm('Voulez-vous vraiment supprimer ce patient ?');">
                                            <i class="fa-solid fa-trash-can me-1"></i>
                                            Supprimer
                                        </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty patients}">
                            <tr>
                                <td colspan="6" class="text-center py-5">
                                    <div class="text-muted">
                                        <i class="fa-solid fa-hospital-user fs-1 mb-3"></i>
                                        <p class="fw-semibold mb-1">Aucun patient enregistré</p>
                                        <small>Les patients apparaîtront ici.</small>
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
