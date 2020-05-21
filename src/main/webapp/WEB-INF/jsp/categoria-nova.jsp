<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>

<title>Categorias - Cadastro</title>

<spring:url value="/css" var="css" />
<spring:url value="/js" var="js" />

<c:set value="${pageContext.request.contextPath}" var="contextPath" />

<link href="${css}/bootstrap.css" rel="stylesheet">
<link href="${css}/small-business.css" rel="stylesheet">

</head>

<body>

	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav">
					<li><a href="${contextPath}/produto">Produtos</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="well">

					<h2>Categoria</h2>

					<form:form modelAttribute="categoriaModel"
						action="${contextPath}/categoria" method="post">

						<spring:hasBindErrors name="categoriaModel">
							<div class="alert alert-danger" role="alert">
								<form:errors path="*" class="has-error" />
							</div>
						</spring:hasBindErrors>

						<div class="form-group">
							<label class="control-label" for="nomeCategoria">Nome:</label>
							<form:input type="text" path="nomeCategoria" id="nomeCategoria"
								class="form-control" maxlength="50" size="50" />
							<font color="red"><form:errors path="nomeCategoria" /></font><br />
						</div>

						<hr>

						<a class="btn btn-default btn-lg" href="${contextPath}/categoria">Cancelar</a>
						<button type="submit" class="btn btn-primary btn-lg">Gravar</button>

						<br>
						<br>
					</form:form>

				</div>
			</div>
		</div>
	</div>

	<!-- jQuery -->
	<script src="${js}/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="${js}/bootstrap.min.js"></script>

</body>
</html>