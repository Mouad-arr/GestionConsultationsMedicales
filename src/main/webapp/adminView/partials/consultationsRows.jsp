<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:forEach var="c" items="${consultations}">
    <tr>
        <td class="fw-semibold">#${c.id}</td>
        <td>
            <div class="d-flex align-items-center gap-2">
                <div class="patient-avatar">
                    <i class="fa-solid fa-user"></i>
                </div>
                <span class="fw-semibold">
                    <c:out value="${c.getPatient().getNom()} ${c.getPatient().getPrenom()}"/>
                </span>
            </div>
        </td>
        <td><c:out value="${c.getMedecin().getNom()} ${c.getMedecin().getPrenom()}"/></td>
        <td>
            <i class="fa-regular fa-calendar text-muted me-1"></i>
            ${c.getDateConsultation().toLocalDate()}
        </td>
        <td>
            <i class="fa-regular fa-clock text-muted me-1"></i>
            ${c.getDateConsultation().toLocalTime()}
        </td>
        <td><c:out value="${c.getMotif()}"/></td>
        <td>
            <span class="badge bg-primary-subtle text-primary">
                <c:out value="${c.getStatus().getLibelle()}"/>
            </span>
        </td>
    </tr>
</c:forEach>

<c:if test="${empty consultations}">
    <tr>
        <td colspan="7" class="text-center py-5">
            <div class="text-muted">
                <i class="fa-regular fa-folder-open fs-1 mb-3"></i>
                <p class="fw-semibold mb-1">Aucune consultation</p>
                <small>Les consultations apparaîtront ici.</small>
            </div>
        </td>
    </tr>
</c:if>
