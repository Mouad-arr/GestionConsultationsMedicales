<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Mon planning | MediCare</title>
    </head>

    <body>

    <jsp:include page="navbar.jsp">
        <jsp:param name="active" value="planning"/>
    </jsp:include>

    <main class="main-content">

        <!-- Header -->
        <div class="mb-4">
            <h1 class="page-title">Mon planning</h1>
            <p class="subtitle">
                Bonjour Dr
                <c:out value="${medicine.getNom()} ${medicine.getPrenom()}"/>.
                Retrouvez ici vos prochaines consultations.
            </p>
        </div>

        <!-- Statistics -->
        <div class="row g-3">

            <!-- Upcoming consultations -->
            <div class="col-md-6">
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

            <!-- Next consultation -->
            <div class="col-md-6">
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

        <!-- Consultations table -->
        <div class="content-card">

            <div class="d-flex justify-content-between align-items-center mb-4">
                <div>
                    <h2 class="section-title mb-1">
                        <i class="fa-solid fa-calendar-days text-primary me-2"></i>
                        Consultations à venir
                    </h2>

                    <p class="subtitle mb-0">
                        Liste de vos prochaines consultations.
                    </p>
                </div>

                <span class="badge bg-primary-subtle text-primary px-3 py-2">
                    ${upcomingConsultations.size()} consultations
                </span>
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
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>

                    <c:forEach var="c" items="${upcomingConsultations}">
                        <tr>

                            <td class="fw-semibold">
                                #${c.id}
                            </td>

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
                                <i class="fa-regular fa-calendar text-muted me-1"></i>
                                    ${c.getDateConsultation().toLocalDate()}
                            </td>

                            <td>
                                <i class="fa-regular fa-clock text-muted me-1"></i>
                                    ${c.getDateConsultation().toLocalTime()}
                            </td>

                            <td>
                                <span class="badge bg-primary-subtle text-primary">
                                    <c:out value="${c.getStatut().getLibelle()}"/>
                                </span>
                            </td>

                            <td>
                                <a href="${ctx}/diagnostic?ConsultationId=${c.id}"
                                   class="btn btn-sm btn-primary">
                                    <i class="fa-solid fa-file-medical me-1"></i>
                                    Établir le diagnostic
                                </a>
                            </td>

                        </tr>
                    </c:forEach>

                    <c:if test="${empty upcomingConsultations}">
                        <tr>
                            <td colspan="6" class="text-center py-5">

                                <div class="text-muted">
                                    <i class="fa-regular fa-calendar-xmark fs-1 mb-3"></i>

                                    <p class="fw-semibold mb-1">
                                        Aucun rendez-vous à venir
                                    </p>

                                    <small>
                                        Vos prochaines consultations apparaîtront ici.
                                    </small>
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