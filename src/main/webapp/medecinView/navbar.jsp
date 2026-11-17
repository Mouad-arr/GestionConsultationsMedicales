<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<link rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

<link rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

<link rel="stylesheet" href="${ctx}/css/style.css">

<div class="sidebar">
    <div class="sidebar-brand">
        <i class="fa-solid fa-heart-pulse"></i>
        <span>MediCare</span>
    </div>

    <div class="sidebar-menu">
        <small class="menu-label">MENU PRINCIPAL</small>

        <a href="${ctx}/medecin"
           class="sidebar-link ${param.active == 'dashboard' ? 'active' : ''}">
            <i class="fa-solid fa-house"></i>
            <span>Accueil</span>
        </a>

        <a href="${ctx}/medecin/planning"
           class="sidebar-link ${param.active == 'planning' ? 'active' : ''}">
            <i class="fa-solid fa-calendar-days"></i>
            <span>Mon planning</span>
        </a>

        <a href="${ctx}/medecin/consultations"
           class="sidebar-link ${param.active == 'historique' ? 'active' : ''}">
            <i class="fa-solid fa-clock-rotate-left"></i>
            <span>Historique</span>
        </a>
    </div>

    <div class="sidebar-bottom">
        <a onclick="return confirm('Voulez-vous vraiment vous déconnecter ?')" href="${ctx}/medecin/logout" class="sidebar-link logout">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Déconnexion</span>
        </a>
    </div>
</div>
