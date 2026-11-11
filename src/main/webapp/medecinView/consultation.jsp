<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Details de la consultation</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp">
        </jsp:include>
        <h1>Details de la consultation</h1>
        <p>ID: ${consultation.getId()}</p>
        <p>Patient: ${consultation.getPatient().getNom()} ${consultation.getPatient().getPrenom()}</p>
        <p>Date: ${consultation.getDateConsultation().toLocalDate()}</p>
        <p>Observations: ${consultation.getObservations()}</p>
        <p>Statut: ${consultation.getStatus().getLibelle()}</p>
        <c:choose>
            <c:when test="${consultation.getDiagnostic() != null}">
                <p>Diagnostic: ${consultation.getDiagnostic().getDescription()}</p>
                <c:if test="${consultation.getDiagnostic().getPrescription() != null}">
                    <p>Prescription: ${consultation.getDiagnostic().getPrescription().getDescription()}</p>
                </c:if>
            </c:when>
            <c:otherwise>
                <p>Aucun diagnostic disponible.</p>
            </c:otherwise>
        </c:choose>
    </body>
</html>
