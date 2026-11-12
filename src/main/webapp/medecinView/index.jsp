<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Dashboard médecin | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="dashboard"/>
        </jsp:include>

        <main class="main-content">

            <div class="mb-4">
                <h1 class="page-title">Bonjour Dr
                    <c:out value="${medicine.getNom()} ${medicine.getPrenom()}"/>
                </h1>
                <p class="subtitle">
                    Voici un aperçu de votre activité médicale.
                </p>
            </div>

            <div class="row g-3">

                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Consultations à venir</div>
                                <div class="stat-value">
                                    ${upcomingConsultations.size()}
                                </div>
                            </div>
                            <div class="stat-icon icon-blue">
                                <i class="fa-solid fa-calendar-check"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Total consultations</div>
                                <div class="stat-value">
                                    ${consultations.size()}
                                </div>
                            </div>
                            <div class="stat-icon icon-green">
                                <i class="fa-solid fa-stethoscope"></i>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="stat-card">
                        <div class="d-flex justify-content-between align-items-center">
                            <div>
                                <div class="stat-label">Prochaine consultation</div>

                                <c:choose>
                                    <c:when test="${not empty first3UpcomingConsultations}">
                                        <div class="stat-value fs-5">
                                                ${first3UpcomingConsultations[0].getDateConsultation().toLocalDate()}
                                        </div>
                                        <small class="text-muted">
                                            À ${first3UpcomingConsultations[0].getDateConsultation().toLocalTime()}
                                        </small>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="stat-value fs-6">
                                            Aucune prévue
                                        </div>
                                    </c:otherwise>
                                </c:choose>
                            </div>

                            <div class="stat-icon icon-orange">
                                <i class="fa-solid fa-clock"></i>
                            </div>
                        </div>
                    </div>
                </div>

            </div>

            <!-- Upcoming consultations -->
            <div class="content-card">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2 class="section-title mb-0">Consultations à venir</h2>

                    <a href="${ctx}/medecin/planning"
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
                            <th>Date</th>
                            <th>Heure</th>
                            <th>Statut</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="c" items="${first3UpcomingConsultations}">
                            <tr>
                                <td>#${c.id}</td>

                                <td class="fw-semibold">
                                    <c:out value="${c.getPatient().getNom()} ${c.getPatient().getPrenom()}"/>
                                </td>

                                <td>${c.getDateConsultation().toLocalDate()}</td>

                                <td>${c.getDateConsultation().toLocalTime()}</td>

                                <td>
                                    <span class="badge bg-primary-subtle text-primary">
                                        <c:out value="${c.getStatut().getLibelle()}"/>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty first3UpcomingConsultations}">
                            <tr>
                                <td colspan="5" class="text-center text-muted py-4">
                                    <i class="fa-regular fa-calendar-xmark me-2"></i>
                                    Aucune consultation à venir.
                                </td>
                            </tr>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>

            <!-- History -->
            <div class="content-card">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <h2 class="section-title mb-0">Historique récent</h2>

                    <a href="${ctx}/medecin/consultations"
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
                            <th>Date</th>
                            <th>Heure</th>
                            <th>Statut</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="c" items="${lastFinishedConsultations}">
                            <tr>
                                <td>#${c.id}</td>

                                <td class="fw-semibold">
                                    <c:out value="${c.getPatient().getNom()} ${c.getPatient().getPrenom()}"/>
                                </td>

                                <td>${c.getDateConsultation().toLocalDate()}</td>

                                <td>${c.getDateConsultation().toLocalTime()}</td>

                                <td>
                                    <span class="badge bg-success-subtle text-success">
                                        <c:out value="${c.getStatus().getLibelle()}"/>
                                    </span>
                                </td>
                            </tr>
                        </c:forEach>

                        <c:if test="${empty lastFinishedConsultations}">
                            <tr>
                                <td colspan="5" class="text-center text-muted py-4">
                                    Aucune consultation terminée.
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