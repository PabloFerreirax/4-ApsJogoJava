<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="usuarioBean" scope="page" class="br.com.aps.quarto.semestre.bean.UsuarioBean"/>

<jsp:setProperty property="usuario" name="usuarioBean" value="${sessionScope.usuario}"/>
<jsp:setProperty property="senha" name="usuarioBean" value="${sessionScope.senha}"/>

<c:if test="${!usuarioBean.logar()}">
	<c:redirect url = "http://www.photofuntoos.com"/>
</c:if>

<nav class="navbar-expand-lg navbar navbar-dark bg-primary">
  <a class="navbar-brand" href="#">Fase</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Alterna navegação">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNavDropdown">
    <ul class="navbar-nav">
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <c:out value="${sessionScope.usuario}"></c:out>
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
          <a class="dropdown-item" href="${pageContext.request.contextPath}/index.jsp">sair</a>
          <a class="dropdown-item" href="#">Outra ação</a>
        </div>
      </li>
    </ul>
  </div>
</nav>