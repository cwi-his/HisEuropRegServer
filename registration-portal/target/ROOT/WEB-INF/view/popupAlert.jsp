<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page session="true"%>
<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html xmlns="https://www.w3.org/1999/xhtml">
<head>
<title>Impressum - EUROP ASSISTANCE Versicherungs-AG</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/css/screen.css" />" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="icon" href="<c:url value="/favicon.ico" />"
	type="image/x-icon" />
<link rel="stylesheet" type="text/css" media="all"
      href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/smoothness/jquery-ui.css"    />
</head>
<body>
	<div id="divImpressum">
		<div id="wrapper-haftungsbeschreankung">
			<p>${alert}</p>
		</div>

		<div class="cibutton grau schliessen ">
			<input type="button" class="form-submit schliessen" value="Schließen"
				onclick="window.close();">
		</div>

		<div style=" width:500px; color: #848383; margin-top: 60px; margin-left: 30px; margin-bottom: 0; height: 40px; border-top: 1px solid #CECDCD;  text-align: center; font-size: 11px; padding-top: 2px;">
			<div>Copyright© 2016 Europ Assistance Versicherungs-AG,
				Adenauerring 9, 81737 München</div>
		</div>
	</div>
</html>



