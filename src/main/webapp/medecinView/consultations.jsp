<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Consultations</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="historique"/>
        </jsp:include>
        <h1>Historique des consultations</h1>
        <p>Bienvenue, <c:out value="${medicine.getNom()} ${medicine.getPrenom()}"/></p>
        <div>
            <p>Total</p>
            <p>${consultations.size()}</p>
        </div>
        <h2>Consultations passées</h2>
        <table>
            <thead>
            <tr><th>ID</th><th>Patient</th><th>Date</th><th>Heure</th><th>Statut</th><th>Action</th></tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${consultations}">
                <tr>
                    <td>${c.id}</td>
                    <td><c:out value="${c.getPatient().getNom()} ${c.getPatient().getPrenom()}"/></td>
                    <td>${c.getDateConsultation().toLocalDate()}</td>
                    <td>${c.getDateConsultation().toLocalTime()}</td>
                    <td>${c.getStatus().getLibelle()}</td>
                    <td><a href="${pageContext.request.contextPath}/medecin/consultation?Id=${c.id}">Voir les details</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty lastFinishedConsultations}">
                <tr><td colspan="6">Aucune consultation terminée</td></tr>
            </c:if>
            </tbody>
        </table>
    </body>
</html>
