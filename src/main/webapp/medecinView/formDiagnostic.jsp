<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Etablir le diagnostic</title>
    </head>
    <body>
        <jsp:include page="navbar.jsp">
        </jsp:include>
        <h1>Etablir le diagnostic</h1>
        <form action="${pageContext.request.contextPath}/medecin/diagnostic" method="post">
            <input type="hidden" name="consultationId" value="${consultation.getId()}"/>
            <input type="hidden" name="dateDiagnostic" value="${pageContext.request.time}"/>
            <label for="description">Description du diagnostic:</label><br/>
            <textarea id="description" name="description" rows="4" cols="50"></textarea><br/><br/>
            <label for="prescription">Prescription (optionnel):</label><br/>
                <label for="medicament">Médicament:</label>
                <input type="text" id="medicament" name="medicament"/><br>
                <label for="posologie">Posologie:</label>
                <input type="text" id="posologie" name="posologie"/><br>
                <label for="duree">Durée:</label>
                <input type="text" id="duree" name="duree"/><br>
                <label for="instructions">Instructions:</label>
                <input type="text" id="instructions" name="instructions"/><br><br>
            <input type="submit" value="Soumettre le diagnostic"/>
        </form>
    </body>
</html>
