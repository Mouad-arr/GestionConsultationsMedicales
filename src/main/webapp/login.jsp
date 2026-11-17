<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html lang="fr">

  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Connexion | MediCare</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">

    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/login.css">
  </head>

  <body>

    <div class="login-container">

      <div class="login-card">

        <!-- Logo -->
        <div class="login-brand">
          <div class="brand-icon">
            <i class="fa-solid fa-heart-pulse"></i>
          </div>

          <h1>MediCare</h1>

          <p>Gestion médicale simplifiée</p>
        </div>

        <!-- Header -->
        <div class="login-header">
          <h2>Bienvenue !</h2>

          <p>
            Connectez-vous à votre espace de gestion.
          </p>
        </div>

        <!-- Login form -->
        <form method="POST">

          <!-- Email -->
          <div class="mb-3">

            <label for="email" class="form-label">
              Adresse email
            </label>

            <div class="input-group">
                        <span class="input-group-text">
                            <i class="fa-regular fa-envelope"></i>
                        </span>

              <input
                      type="email"
                      class="form-control"
                      id="email"
                      name="email"
                      placeholder="exemple@email.com"
                      autocomplete="username"
                      required>
            </div>

          </div>

          <!-- Password -->
          <div class="mb-4">

            <label for="password" class="form-label">
              Mot de passe
            </label>

            <div class="input-group">
                        <span class="input-group-text">
                            <i class="fa-solid fa-lock"></i>
                        </span>

              <input
                      type="password"
                      class="form-control"
                      id="password"
                      name="password"
                      placeholder="Votre mot de passe"
                      autocomplete="current-password"
                      required>
            </div>

          </div>

          <!-- Role -->
          <div class="mb-4">

            <label class="form-label">
              Se connecter en tant que
            </label>

            <div class="row g-3">

              <div class="col-6">
                <input
                        type="radio"
                        class="btn-check"
                        name="role"
                        id="admin"
                        value="admin"
                        required>

                <label class="role-card" for="admin">

                  <i class="fa-solid fa-user-shield"></i>

                  <span>Admin</span>

                </label>
              </div>

              <div class="col-6">
                <input
                        type="radio"
                        class="btn-check"
                        name="role"
                        id="medecin"
                        value="medecin"
                        required>

                <label class="role-card" for="medecin">

                  <i class="fa-solid fa-user-doctor"></i>

                  <span>Médecin</span>

                </label>
              </div>

            </div>

          </div>

          <!-- Submit -->
          <button type="submit" class="btn btn-login w-100">

            Se connecter

            <i class="fa-solid fa-arrow-right ms-2"></i>

          </button>

        </form>
        <c:if test="${not empty errorMessage}">
          <div class="alert alert-danger d-flex align-items-center gap-2 mb-3" role="alert">
            <i class="fa-solid fa-circle-exclamation"></i>
            <span><c:out value="${errorMessage}" /></span>
          </div>
        </c:if>
        <!-- Footer -->
        <div class="login-footer">
          <i class="fa-solid fa-shield-halved me-1"></i>
          Votre espace médical sécurisé
        </div>

      </div>

    </div>

  </body>
</html>
