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

            <!-- Header -->
            <div class="mb-4">
                <h1 class="page-title">Gestion des consultations</h1>
                <p class="subtitle mb-0">
                    Vue globale de toutes les consultations de la clinique.
                </p>
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

                <div class="table-responsive">
                    <table class="table">
                        <thead>
                        <tr>
                            <th>ID</th>
                            <th>Patient</th>
                            <th>Médecin</th>
                            <th>Date</th>
                            <th>Heure</th>
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
                                    <i class="fa-regular fa-clock text-muted me-1"></i>
                                    ${c.getDateConsultation().toLocalTime()}
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
                                <td colspan="6" class="text-center py-5">
                                    <div class="text-muted">
                                        <i class="fa-regular fa-folder-open fs-1 mb-3"></i>
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
