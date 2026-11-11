
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<nav class="sidebar">
    <a href="${pageContext.request.contextPath}/medecin"
       class="${param.active == 'dashboard' ? 'active' : ''}">
        Accueil
    </a>

    <a href="${pageContext.request.contextPath}/medecin/planning"
       class="${param.active == 'planning' ? 'active' : ''}">
        Mon planning
    </a>

    <a href="${pageContext.request.contextPath}/medecin/consultations"
       class="${param.active == 'historique' ? 'active' : ''}">
        Historique
    </a>

    <a href="${pageContext.request.contextPath}/medecin/logout">
        Déconnexion
    </a>
</nav>
