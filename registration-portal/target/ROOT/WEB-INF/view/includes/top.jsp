<%--
   Header for all JSP
--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="https://www.w3.org/1999/xhtml" lang="en">
<head>
<title>initiative.diabetes</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/screen.css" />" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="shortcut icon" href="http://www.europ-assistance.de/sites/all/themes/ea_ebiz/favicon.ico" />
<link rel="icon" href="<c:url value="/favicon.ico" />"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css" media="all"
      href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/smoothness/jquery-ui.css"    />
</head>
<body>
	<div id="globalbox">
		<div id="header">
			<a href="https://www.europ-assistance.de" title="Europ Assistance"
				rel="home"> <img src="<%=request.getContextPath()%>/resources/images/WEBLabel2.png"
				alt="Europ Assistance" class="headerImg"></a>
		</div>
