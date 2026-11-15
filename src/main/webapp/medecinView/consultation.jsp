<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Détails de la consultation | MediCare</title>
    </head>
    <body>

    <jsp:include page="navbar.jsp"/>

    <main class="main-content">

        <!-- Header -->
        <div class="d-flex justify-content-between align-items-center mb-4 flex-wrap gap-3">

            <div>
                <h1 class="page-title">Détails de la consultation</h1>
                <p class="subtitle mb-0">
                    Consultez les informations détaillées de cette consultation.
                </p>
            </div>

            <a href="${ctx}/medecin/consultations"
               class="btn btn-outline-secondary">
                <i class="fa-solid fa-arrow-left me-2"></i>
                Retour à l'historique
            </a>

        </div>

        <!-- Consultation information -->
        <div class="content-card mt-0">

            <div class="d-flex justify-content-between align-items-center mb-4">

                <h2 class="section-title mb-0">
                    <i class="fa-solid fa-file-medical text-primary me-2"></i>
                    Informations générales
                </h2>

                <c:if test="${consultation.status=='TERMINEE'}">
                    <i class="fa-solid fa-check-circle me-1"></i>
                    <span class="badge bg-success-subtle text-success">
                                        <c:out value="${consultation.status}"/>
                                    </span>
                </c:if>
                <c:if test="${consultation.status=='ANNULEE'}">
                                    <span class="badge bg-danger-subtle text-danger">
                                        <i class="fa-solid fa-ban me-1"></i>
                                        <c:out value="${consultation.status}"/>
                                    </span>
                </c:if>

            </div>

            <div class="row g-4">

                <div class="col-md-4">
                    <div class="detail-item">
                        <div class="detail-icon icon-blue">
                            <i class="fa-solid fa-hashtag"></i>
                        </div>

                        <div>
                            <small>ID consultation</small>
                            <p>#${consultation.getId()}</p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="detail-item">
                        <div class="detail-icon icon-green">
                            <i class="fa-solid fa-calendar-days"></i>
                        </div>

                        <div>
                            <small>Date de consultation</small>
                            <p>
                                ${consultation.getDateConsultation().toLocalDate()}
                            </p>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="detail-item">
                        <div class="detail-icon icon-orange">
                            <i class="fa-solid fa-user"></i>
                        </div>

                        <div>
                            <small>Patient</small>
                            <p>
                                <c:out value="${consultation.getPatient().getNom()} ${consultation.getPatient().getPrenom()}"/>
                            </p>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <!-- Observations -->
        <div class="content-card">

            <h2 class="section-title mb-4">
                <i class="fa-solid fa-notes-medical text-primary me-2"></i>
                Observations médicales
            </h2>

            <div class="medical-description">

                <c:choose>
                    <c:when test="${not empty consultation.getObservations()}">
                        <p class="mb-0">
                            <c:out value="${consultation.getObservations()}"/>
                        </p>
                    </c:when>

                    <c:otherwise>
                        <p class="text-muted mb-0">
                            Aucune observation disponible.
                        </p>
                    </c:otherwise>
                </c:choose>

            </div>

        </div>

        <!-- Diagnostic and prescription -->
        <div class="content-card">

            <h2 class="section-title mb-4">
                <i class="fa-solid fa-stethoscope text-primary me-2"></i>
                Diagnostic et prescription
            </h2>


            <c:choose>
                <%-- Diagnostic disponible --%>
                <c:when test="${consultation.diagnostic != null}">

                    <!-- Diagnostic -->
                    <div class="medical-section mb-4">

                        <div class="d-flex align-items-center gap-2 mb-3">
                            <div class="detail-icon icon-blue">
                                <i class="fa-solid fa-microscope"></i>
                            </div>
                            <h3 class="section-title mb-0">Diagnostic</h3>
                        </div>

                        <div class="medical-description">
                            <p class="mb-0">
                                <c:out value="${consultation.diagnostic.description}"/>
                            </p>
                        </div>

                    </div>

                    <!-- Prescriptions -->
                    <div class="medical-section">

                        <div class="d-flex align-items-center gap-2 mb-3">
                            <div class="detail-icon icon-green">
                                <i class="fa-solid fa-prescription"></i>
                            </div>
                            <h3 class="section-title mb-0">Prescriptions</h3>
                        </div>

                        <c:choose>
                            <c:when test="${not empty consultation.diagnostic.prescriptions}">

                                <div class="row g-3">
                                    <c:forEach var="prescription"
                                               items="${consultation.diagnostic.prescriptions}"
                                               varStatus="status">

                                        <div class="col-12">
                                            <div class="prescription-card">

                                                <div class="d-flex align-items-center justify-content-between mb-3">
                                        <span class="badge bg-success-subtle text-success">
                                            <i class="fa-solid fa-pills me-1"></i>
                                            Prescription ${status.index + 1}
                                        </span>
                                                </div>

                                                <h5 class="prescription-medicine mb-3">
                                                    <i class="fa-solid fa-capsules text-success me-2"></i>
                                                    <c:out value="${prescription.medicament}"/>
                                                </h5>

                                                <div class="row g-3">

                                                    <div class="col-md-4">
                                                        <div class="prescription-detail">
                                                            <small>Posologie</small>
                                                            <p class="mb-0">
                                                                <c:out value="${prescription.posologie}"/>
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <div class="prescription-detail">
                                                            <small>Durée</small>
                                                            <p class="mb-0">
                                                                <c:out value="${prescription.dureeJours}"/> jours
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="col-md-4">
                                                        <div class="prescription-detail">
                                                            <small>Instructions</small>
                                                            <p class="mb-0">
                                                                <c:out value="${prescription.instructions}"/>
                                                            </p>
                                                        </div>
                                                    </div>

                                                </div>

                                            </div>
                                        </div>

                                    </c:forEach>
                                </div>

                            </c:when>

                            <c:otherwise>
                                <div class="alert alert-light border mb-0">
                                    <i class="fa-solid fa-info-circle text-primary me-2"></i>
                                    Aucune prescription disponible pour ce diagnostic.
                                </div>
                            </c:otherwise>
                        </c:choose>

                    </div>

                </c:when>

                <%-- Aucun diagnostic --%>
                <c:otherwise>

                    <div class="empty-medical-state text-center py-5">

                        <div class="empty-icon mb-3">
                            <i class="fa-solid fa-file-circle-question"></i>
                        </div>

                        <h5 class="fw-semibold">Aucun diagnostic disponible</h5>

                        <p class="text-muted mb-0">
                            Aucun diagnostic n'a encore été établi pour cette consultation.
                        </p>

                    </div>

                </c:otherwise>
            </c:choose>


        </div>

    </main>

    </body>
</html>
