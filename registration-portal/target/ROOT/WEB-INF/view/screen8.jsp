<jsp:directive.include file="includes/top.jsp" />
<script type="text/javascript">
	var i = 0;
	function enabledBtn() {

		if (i == 1) {
			document.getElementById('sbm').disabled = false;
			return;
		}
		document.getElementById('sbm').disabled = true;
		i = +1;
	}
</script>
<div id="formareabox">
	<div id="myProgress">
	  		<div id="myBar" style="width: 60%;">60%</div>
	</div>
	<br>
	<div class="ups_lz"></div>
	<label><spring:message code="screen8.title.value" /></label> <br>

	<c:url var="screen8Url" value="screen8.html" />
	<form:form name="screen8" method="POST" modelAttribute="screen8"
		action="${screen8Url}">
		<div id="divField">
				<label><spring:message code="screen8.question1b.value" /></label><br>
				<label><spring:message code="screen8.question1c.value" /></label><br>
				<label><spring:message code="screen8.question1e.value" /></label><br>
				<label><spring:message code="screen8.question1f.value" /></label><br>
				<label><spring:message code="screen8.question1g.value" /></label><br>
				<label><spring:message code="screen8.question1h.value" /></label><br>
			</div>
			<br>
			<div id="divField">
				<ul class="ul-3-column">
					<li><form:radiobutton path="typesSection2a" id="radio-1a"
							name="radiobutton-1a" value="1" onchange="enabledBtn()" /> <spring:message
							code="common.optYes.value" /></li>
					<li><form:radiobutton path="typesSection2a" id="radio-2a"
							name="radiobutton-2a" value="2" onchange="enabledBtn()" /> <spring:message
							code="common.optNo.value" /></li>
					<li><form:radiobutton path="typesSection2a" id="radio-3a"
							name="radiobutton-3a" value="3" onchange="enabledBtn()" /> <spring:message
							code="common.optIdontknow.value" /> <%--<form:radiobuttons path="typesSection1a" items="${typesSection1a}" onclick="submit.disabled=false"/><br>
							<form:errors path="typesSection1a" cssStyle="color: #ff0000;"/>--%></li>
				</ul>
			</div>
			
			<br>
			<br>
			

			<div id="divField"><label><spring:message code="screen8.question2.value" /></label></div>
			<br>
			<div id="divField">
				<ul class="ul-3-column">
					<li><form:radiobutton
							path="typesSection2b" id="radio-1b" name="radiobutton-1b"
							value="1" onchange="enabledBtn()" /> <spring:message
							code="common.optYes.value" /></li>
					<li><form:radiobutton
							path="typesSection2b" id="radio-2b" name="radiobutton-2b"
							value="2" onchange="enabledBtn()" /> <spring:message
							code="common.optNo.value" /></li>
					<li><form:radiobutton path="typesSection2b"
							id="radio-3b" name="radiobutton-3b" value="3"
							onchange="enabledBtn()" /> <spring:message
							code="common.optIdontknow.value" /> <%--<form:radiobuttons path="typesSection1b" items="${typesSection1b}" onclick="submit.disabled=false"/>
						<form:errors path="typesSection1b" cssStyle="color: #ff0000;"/>--%></li>
				</ul>
		</div>
		<br>
		<br>
		<div class="weiter button_rechts">
			<spring:message code="common.btnContinue.caption" var="buttonValue" />
			<input class="cibutton gelb form-submit weiter" id="sbm" type="submit"
				name="submit" value="${buttonValue}" disabled="disabled" />
		</div>
	</form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />