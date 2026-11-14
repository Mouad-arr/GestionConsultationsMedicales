<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<c:forEach var="m" items="${medecins}">
    <tr>
        <td class="fw-semibold">#${m.id}</td>
        <td>
            <div class="d-flex align-items-center gap-2">
                <div class="patient-avatar">
                    <i class="fa-solid fa-user-doctor"></i>
                </div>
                <span class="fw-semibold">
                    <c:out value="${m.getNom()} ${m.getPrenom()}"/>
                </span>
            </div>
        </td>
        <td>
            <span class="badge bg-primary-subtle text-primary">
                <c:out value="${m.getSpecialite()}"/>
            </span>
        </td>
        <td><c:out value="${m.getNumeroOrdre()}"/></td>
        <td><c:out value="${m.getEmail()}"/></td>
        <td><c:out value="${m.getTelephone()}"/></td>
        <td>
            <div class="d-flex gap-2">
                <a href="${ctx}/admin/medecins?action=edit&id=${m.id}"
                   class="btn btn-sm btn-outline-primary">
                    <i class="fa-solid fa-pen-to-square me-1"></i>
                    Modifier
                </a>
                <a href="${ctx}/admin/medecins?action=delete&id=${m.id}"
                   class="btn btn-sm btn-outline-danger"
                   onclick="return confirm('Voulez-vous vraiment supprimer ce médecin ?');">
                    <i class="fa-solid fa-trash-can me-1"></i>
                    Supprimer
                </a>
            </div>
        </td>
    </tr>
</c:forEach>

<c:if test="${empty medecins}">
    <tr>
        <td colspan="7" class="text-center py-5">
            <div class="text-muted">
                <i class="fa-solid fa-user-doctor fs-1 mb-3"></i>
                <p class="fw-semibold mb-1">Aucun médecin enregistré</p>
                <small>Les médecins apparaîtront ici.</small>
            </div>
        </td>
    </tr>
</c:if>
