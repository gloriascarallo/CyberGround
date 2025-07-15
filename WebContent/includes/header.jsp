<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<header id="main-header">
  <div class="header-left">
    <a href="${pageContext.request.contextPath}/guest/view//index.jsp">
      <img src="${pageContext.request.contextPath}/images/logo.png" alt="Logo sito" class="logo">
    </a>
    <h1>CyberGround</h1>
  </div>
  <div class="header-right">
    <a href="${pageContext.request.contextPath}/guest/view/login.jsp" title="Login">
    <i class="fas fa-sign-in-alt"></i>Login</a>
    <a href="${pageContext.request.contextPath}/User" title="Profilo">
    <i class="fas fa-user-circle"></i>Profilo</a>
    <a href="${pageContext.request.contextPath}/guest/view/cart.jsp" title="Carrello">
    <i class="fas fa-shopping-cart"></i>Carrello</a>
  </div>
</header>
