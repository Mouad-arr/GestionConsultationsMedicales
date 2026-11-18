<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="fr">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Nouvelle consultation | MediCare</title>
    </head>

    <body>

        <jsp:include page="navbar.jsp">
            <jsp:param name="active" value="consultations"/>
        </jsp:include>

        <main class="main-content">

            <div class="mb-4">
                <h1 class="page-title">Nouvelle consultation</h1>
                <p class="subtitle">
                    Planifiez une consultation en associant un patient à un médecin.
                </p>
            </div>

            <div class="content-card">

                <form action="${ctx}/admin/consultations" method="post">

                    <div class="row g-3">

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Patient <span class="text-danger">*</span>
                            </label>
                            <select name="patientId" class="form-select" required>
                                <option value="">-- Choisir un patient --</option>
                                <c:forEach var="p" items="${patients}">
                                    <option value="${p.id}">
                                        ${p.nom} ${p.prenom} (${p.cin})
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Médecin <span class="text-danger">*</span>
                            </label>
                            <select name="medecinId" class="form-select" required>
                                <option value="">-- Choisir un médecin --</option>
                                <c:forEach var="m" items="${medecins}">
                                    <option value="${m.id}">
                                        ${m.nom} ${m.prenom}
                                        <c:if test="${not empty m.specialite}"> (${m.specialite})</c:if>
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">
                                Date <span class="text-danger">*</span>
                            </label>
                            <input type="date" name="date" id="dateInput" class="form-control" required>
                        </div>

                        <div class="col-md-6">
                            <label class="form-label fw-semibold">Heure</label>
                            <input type="time" name="heure" class="form-control" value="09:00">
                        </div>

                        <div class="col-12">
                            <label class="form-label fw-semibold">Motif</label>
                            <input type="text" name="motif" class="form-control"
                                   placeholder="Motif de la consultation (raison du rendez-vous)">
                        </div>

                    </div>

                    <div class="d-flex justify-content-end gap-2 mt-4 flex-wrap">
                        <a href="${ctx}/admin/consultations" class="btn btn-outline-secondary">
                            <i class="fa-solid fa-xmark me-1"></i>
                            Annuler
                        </a>
                        <button type="submit" class="btn btn-primary px-4">
                            <i class="fa-solid fa-check me-2"></i>
                            Planifier
                        </button>
                    </div>

                </form>

            </div>

        </main>

        <script>
            // Bloque la sélection d'une date passée côté navigateur.
            document.getElementById("dateInput").min = new Date().toISOString().split("T")[0];
        </script>
    </body>
</html>
