<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tableau de bord | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="dashboard"/>
        </jsp:include>

        <main class="main-content">

            <div class="mb-4">
                <h1 class="page-title">Tableau de bord</h1>
                <p class="subtitle">
                    Vue d'ensemble de l'activité de la clinique.
                </p>
            </div>

            <div class="row g-3">

                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Médecins</div>
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

                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Patients</div>
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

                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Consultations</div>
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

            <!-- Dernières consultations -->
            <div class="content-card">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2 class="section-title mb-0">
                        Dernières consultations
                    </h2>

                    <a href="${ctx}/admin/consultations"
                       class="btn btn-sm btn-outline-primary">
                        Voir tout <i class="fa-solid fa-arrow-right ms-1"></i>
                    </a>
                </div>

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Patient</th>
                            <th>Médecin</th>
                            <th>Date</th>
                            <th>Statut</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="c" items="${consultations}">
                            <tr>
                                <td class="fw-semibold">#${c.id}</td>

                                <td>
                                    <div class="d-flex align-items-center gap-2">
                                        <div class="patient-avatar">
                                            <i class="fa-solid fa-user"></i>
                                        </div>
                                        <span class="fw-semibold">
                                            <c:out value="${c.getPatient().getNom()} ${c.getPatient().getPrenom()}"/>
                                        </span>
                                    </div>
                                </td>

                                <td>
                                    <c:out value="${c.getMedecin().getNom()} ${c.getMedecin().getPrenom()}"/>
                                </td>

                                <td>
                                    <i class="fa-regular fa-calendar text-muted me-1"></i>
                                    ${c.getDateConsultation().toLocalDate()}
                                </td>

                                <td>
                                    <span class="badge bg-primary-subtle text-primary">
                                        <c:out value="${c.getStatus().getLibelle()}"/>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty consultations}">
                            <tr>
                                <td colspan="5" class="text-center py-5">
                                    <div class="text-muted">
                                        <i class="fa-regular fa-calendar-xmark fs-1 mb-3"></i>
                                        <p class="fw-semibold mb-1">Aucune consultation</p>
                                        <small>Les consultations apparaîtront ici.</small>
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
