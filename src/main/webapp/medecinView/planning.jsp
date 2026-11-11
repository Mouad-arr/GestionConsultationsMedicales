<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Planning Des Prochaines Consultations</title>
    </head>
    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="planning"/>
        </jsp:include>

        <h1>Planning des prochaines consultations</h1>
        <p>Bienvenue, <c:out value="${medicine.getNom()} ${medicine.getPrenom()}"/></p>

        <div>
            <p>À venir</p>
            <p>${upcomingConsultations.size()}</p>
        </div>
        <div>
            <p>Prochaine consultation</p>
            <c:choose>
                <c:when test="${not empty first3UpcomingConsultations}">
                    <p>${first3UpcomingConsultations[0].getDateConsultation().toLocalDate()}
                        à ${first3UpcomingConsultations[0].getDateConsultation().toLocalTime()}</p>
                </c:when>
                <c:otherwise>
                    <p>Aucune consultation à venir</p>
                </c:otherwise>
            </c:choose>
        </div>

        <h2>Consultations à venir</h2>
        <table>
            <thead>
            <tr><th>ID</th><th>Patient</th><th>Date</th><th>Heure</th><th>Statut</th><th>Action</th></tr>
            </thead>
            <tbody>
            <c:forEach var="c" items="${upcomingConsultations}">
                <tr>
                    <td>${c.id}</td>
                    <td><c:out value="${c.getPatient().getNom()} ${c.getPatient().getPrenom()}"/></td>
                    <td>${c.getDateConsultation().toLocalDate()}</td>
                    <td>${c.getDateConsultation().toLocalTime()}</td>
                    <td>${c.getStatut().getLibelle()}</td>
                    <td><a href="${pageContext.request.contextPath}/diagnostic?ConsultationId=${c.id}">Etablir le diagnostic</a></td>
                </tr>
            </c:forEach>
            <c:if test="${empty first3UpcomingConsultations}">
                <tr><td colspan="6">Aucune consultation à venir</td></tr>
            </c:if>
            </tbody>
        </table>

    </body>
</html>
