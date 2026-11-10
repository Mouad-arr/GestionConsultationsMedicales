<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>Dashboard médecin</title>
</head>
<body>
<jsp:include page="navbar.jsp">
    <jsp:param name="active" value="dashboard"/>
</jsp:include>

<h1>Dashboard médecin</h1>
<p>Bienvenue, <c:out value="${medecin.nom} ${medecin.prenom}"/></p>

<div>
    <p>À venir</p>
    <p>${upcomingConsultations.size()}</p>
</div>
<div>
    <p>Total</p>
    <p>${consultations.size()}</p>
</div>
<div>
    <p>Prochaine consultation</p>
    <c:ch   oose>
        <c:when test="${not empty first3UpcomingConsultations}">
            <p>${first3UpcomingConsultations[0].dateConsultation}
                à ${first3UpcomingConsultations[0].heureConsultation}</p>
        </c:when>
        <c:otherwise>
            <p>Aucune consultation à venir</p>
        </c:otherwise>
    </c:choose>
</div>

<h2>Consultations à venir</h2>
<table>
    <thead>
    <tr><th>ID</th><th>Patient</th><th>Date</th><th>Heure</th><th>Statut</th></tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${first3UpcomingConsultations}">
        <tr>
            <td>${c.id}</td>
            <td><c:out value="${c.patient.nom} ${c.patient.prenom}"/></td>
            <td>${c.dateConsultation}</td>
            <td>${c.heureConsultation}</td>
            <td>${c.statut}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty first3UpcomingConsultations}">
        <tr><td colspan="5">Aucune consultation à venir</td></tr>
    </c:if>
    </tbody>
</table>
<a href="${ctx}/planning">Voir tout le planning</a>

<h2>Historique</h2>
<table>
    <thead>
    <tr><th>ID</th><th>Patient</th><th>Date</th><th>Heure</th><th>Statut</th></tr>
    </thead>
    <tbody>
    <c:forEach var="c" items="${lastFinishedConsultations}">
        <tr>
            <td>${c.id}</td>
            <td><c:out value="${c.patient.nom} ${c.patient.prenom}"/></td>
            <td>${c.dateConsultation}</td>
            <td>${c.heureConsultation}</td>
            <td>${c.statut}</td>
        </tr>
    </c:forEach>
    <c:if test="${empty lastFinishedConsultations}">
        <tr><td colspan="5">Aucune consultation terminée</td></tr>
    </c:if>
    </tbody>
</table>
<a href="${ctx}/historique">Voir tout l'historique</a>
</body>
</html>