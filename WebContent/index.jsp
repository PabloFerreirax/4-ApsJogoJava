<%-- 
    Document   : teste
    Created on : 23/10/2019, 11:30:08
    Author     : tsdev04
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>     
    	<script type="text/javascript" src="${pageContext.request.contextPath}/sweetalert2.js"></script>
       
        <jsp:useBean id="service" scope="page" class="br.com.aps.quarto.semestre.service.UsuarioService"></jsp:useBean>
        <%
        session.removeAttribute("usuario");
        session.removeAttribute("senha");
        %>
        
        <%
        	if(request.getParameter("logar")!=null){
        		String usuario = request.getParameter("usuario");
        		String senha = request.getParameter("senha");
        		
        		if(service.login(usuario, senha)){
        			session.setAttribute("usuario", usuario);
        			session.setAttribute("senha", senha);
        			response.sendRedirect("fase.jsp");
        		}
        		else{%>
        			<script type="text/javascript">
        				swal('Login ou senha inválido(s)');
        			</script>
        		<%
        			request.removeAttribute("logar");
        		}
        	}
        
        %>
        
        <div class="container" style="width: 30%; margin-top: 30vh">
            <form method="post">
                <div class="form-group">
                  <label for="exampleInputEmail1">Usuário</label>
                  <input type="text" name ="usuario" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" placeholder="Usuário">
                </div>
                <div class="form-group">
                  <label for="exampleInputPassword1">Senha</label>
                  <input type="password" name="senha" class="form-control" id="exampleInputPassword1" placeholder="Senha">
                </div>
                <button type="submit" class="btn bg-success text-light" name="logar">Logar</button>
                <br/>
                <a href="${pageContext.request.contextPath}/cadastro.jsp">Criar nova conta</a>
          </form>
        </div>
        
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>
