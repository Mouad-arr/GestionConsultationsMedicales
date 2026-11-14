<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:forEach var="p" items="${patients}">
    <tr>
        <td class="fw-semibold">#${p.id}</td>
        <td>
            <div class="d-flex align-items-center gap-2">
                <div class="patient-avatar">
                    <i class="fa-solid fa-user"></i>
                </div>
                <span class="fw-semibold">
                    <c:out value="${p.getNom()} ${p.getPrenom()}"/>
                </span>
            </div>
        </td>
        <td><c:out value="${p.getCin()}"/></td>
        <td><c:out value="${p.getSexe().getLibelle()}"/></td>
        <td><c:out value="${p.getEmail()}"/></td>
        <td><c:out value="${p.getTelephone()}"/></td>
        <td>
            <div class="d-flex gap-2">
                <a href="${ctx}/admin/patients?action=edit&id=${p.id}"
                   class="btn btn-sm btn-outline-primary">
                    <i class="fa-solid fa-pen-to-square me-1"></i>
                    Modifier
                </a>
                <a href="${ctx}/admin/patients?action=delete&id=${p.id}"
                   class="btn btn-sm btn-outline-danger"
                   onclick="return confirm('Voulez-vous vraiment supprimer ce patient ?');">
                    <i class="fa-solid fa-trash-can me-1"></i>
                    Supprimer
                </a>
            </div>
        </td>
    </tr>
</c:forEach>

<c:if test="${empty patients}">
    <tr>
        <td colspan="7" class="text-center py-5">
            <div class="text-muted">
                <i class="fa-solid fa-hospital-user fs-1 mb-3"></i>
                <p class="fw-semibold mb-1">Aucun patient enregistré</p>
                <small>Les patients apparaîtront ici.</small>
            </div>
        </td>
    </tr>
</c:if>
