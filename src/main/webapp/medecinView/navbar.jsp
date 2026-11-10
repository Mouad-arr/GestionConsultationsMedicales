<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<nav class="sidebar">
    <a href="${pageContext.request.contextPath}/dashboard"
       class="${param.active == 'dashboard' ? 'active' : ''}">Accueil</a>

    <a href="${pageContext.request.contextPath}/planning">Mon planning</a>
    <a href="${pageContext.request.contextPath}/planning">A venir</a>
    <a href="${pageContext.request.contextPath}/historique">Historique</a>


    <a href="${pageContext.request.contextPath}/logout">Déconnexion</a>
</nav>