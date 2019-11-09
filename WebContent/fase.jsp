<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">



<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/cabecalho.css" />
	<link rel="stylesheet" href="${pageContext.request.contextPath}/text_slide.css" />
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<title>Fase</title>
</head>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/sweetalert2.js"></script>
	
	
	<jsp:useBean id="usuarioBean" scope="page" class="br.com.aps.quarto.semestre.bean.UsuarioBean"/>
	<jsp:setProperty property="usuario" name="usuarioBean" value="${sessionScope.usuario}"/>
	<jsp:setProperty property="senha" name="usuarioBean" value="${sessionScope.senha}"/>
	
	<%-- se o usuário é válido --%>
	<c:if test="${!usuarioBean.logar()}">
		<c:redirect url = "/index.jsp"/>
	</c:if>
	
	
	<jsp:useBean id="faseBeans" class="br.com.aps.quarto.semestre.bean.FaseBean" scope="page"/>
	<c:if test="${faseBeans.progresso==null}">
		<jsp:setProperty property="usuario" name="faseBeans" value="${sessionScope.usuario}"/>
	</c:if>
	
	
	<%-- VERIFICAAR SE TEM RESPOTA --%>
	<c:if test="${param.idPerg!=null}">
		<jsp:setProperty property="idResposta" name="faseBeans" value="${param.idPerg}"/>

		<script type="text/javascript">
			var msg = "${faseBeans.msg}";
			swal(msg);
		</script>
	</c:if>
	
	<%-- VERIFICAR SE TEM COMBATE --%>
	<c:if test="${param.vlrCombt!=null}">
		${faseBeans.batalhar()}
		
		<script type="text/javascript">
			var msg = "${faseBeans.msg}";
			swal(msg);
		</script>
	</c:if>
	
	<%-- VERIFICAR SE É TEM COMPLETAR --%>
	<c:if test="${param.btnRespComplt!=null}">
		<jsp:setProperty property="respCompletar" name="faseBeans" value="${param.respComplt}"/>
		
		<script type="text/javascript">
			var msg = "${faseBeans.msg}";
			swal(msg);
		</script>
	</c:if>
	
	
	
	
	<nav class="navbar-expand-lg navbar navbar-dark bg-success">
	  <a class="navbar-brand" href="#">${faseBeans.getFase().dsFase}</a>
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
        	  <a class="dropdown-item" href="#">Você tem ${faseBeans.progresso.qtdPontos} pontos</a>
	          <a class="dropdown-item" href="${pageContext.request.contextPath}/index.jsp">Sair</a>
	        </div>
	      </li>
	    </ul>
	  </div>
	</nav>
	
	
	<c:catch var ="catchException">
        <div class="text-center">
			<img class="img-fluid" style="width: 40%" src="data:image/png;base64,${faseBeans.getImg()}"/>
		</div>	
    </c:catch>
	
	
	<c:set var="dialogos" scope="page" value="${faseBeans.getDialogos()}"/>
	<c:set var="count" scope="page" value="1"/>
	
	<div class="slideshow-container">
	
		<c:forEach items="${dialogos}" var="dialogo">
		
			<div class="mySlides">
				<q>${dialogo.textDialogo}</q>
				<c:if test="${dialogos.size()==count}">
					
					<%-- FASE TIPO PERGUNTA --%>
					<c:if test="${faseBeans.getFase().tipoFase.toInt() == 0}">
						<form method="post">
							<c:forEach var="resposta" items="${faseBeans.getRespostas()}">
								<button type="submit" style="margin: 10px" class="btn bg-success text-light" name="idPerg" value="${resposta.idResposta}">${resposta.textResposta}</button>
							</c:forEach>
						</form>
					</c:if>
					<%-- FASE TIPO COMBATE--%>
					<c:if test="${faseBeans.getFase().tipoFase.toInt() == 1}">
						<form method="post">
							<button type="submit" class="btn bg-success text-light" name="vlrCombt">Batalhar</button>
						</form>
					</c:if>
					<%-- FASE TIPO COMPLETAR --%>
					<c:if test="${faseBeans.getFase().tipoFase.toInt() == 2}">
						<form method="post" class="container" style="width: 50%">
							<input type="text" name="respComplt" class="form-control"/>
							<br/>
							<button type="submit" class="btn bg-success text-light" name="btnRespComplt">Responder</button>
						</form>
					</c:if>
				</c:if>
			</div>
			
			<c:set var="count" value="${count + 1}" />
		</c:forEach>
		
		<a class="next" onclick="plusSlides(1)">Próximo</a>
	
	</div>
	
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/sweetalert2.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/text_slide.js"></script>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>