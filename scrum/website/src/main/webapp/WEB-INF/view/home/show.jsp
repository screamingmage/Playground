<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Scrum Planner</title>

	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
	
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
	<script src="${pageContext.request.contextPath}/js/main.js" type="text/javascript"></script>
</head>
<body>
	<header>
		<div id="header-banner">
			<h2>Scrum Planner</h2>
		</div>
	</header>
	
	<div class="container">
		<div class="content">
			<div class="left-pane">
				<input class="search-box" placeholder="Search by id" type="text" />
				<a class="load-stories" href="#">View all stories</a>
				<a class="create-story" href="#">Create a story</a>
			</div>
			
			<div class="right-pane">
				<div class="scrum-information">
				</div>
			</div>
		</div>
	</div>
</body>
</html>