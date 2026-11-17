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
        <small class="menu-label">ADMINISTRATION</small>

        <a href="${ctx}/admin"
           class="sidebar-link ${param.active == 'dashboard' ? 'active' : ''}">
            <i class="fa-solid fa-house"></i>
            <span>Accueil</span>
        </a>

        <a href="${ctx}/admin/medecins"
           class="sidebar-link ${param.active == 'medecins' ? 'active' : ''}">
            <i class="fa-solid fa-user-doctor"></i>
            <span>Médecins</span>
        </a>

        <a href="${ctx}/admin/patients"
           class="sidebar-link ${param.active == 'patients' ? 'active' : ''}">
            <i class="fa-solid fa-hospital-user"></i>
            <span>Patients</span>
        </a>

        <a href="${ctx}/admin/consultations"
           class="sidebar-link ${param.active == 'consultations' ? 'active' : ''}">
            <i class="fa-solid fa-calendar-days"></i>
            <span>Consultations</span>
        </a>
    </div>

    <div class="sidebar-bottom">
        <a onclick="return confirm('Voulez-vous vraiment vous déconnecter ?')" href="${ctx}/admin/logout" class="sidebar-link logout">
            <i class="fa-solid fa-right-from-bracket"></i>
            <span>Déconnexion</span>
        </a>
    </div>
</div>
