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
	  		<div id="myBar" style="width: 55%;">55%</div>
		</div>
		<br>
	<div class="ups_lz"></div>
	<label><spring:message code="screen7.title.value" /></label> <br>
	<br>

	<c:url var="screen7Url" value="screen7.html" />
	<form:form method="POST" modelAttribute="screen7"
		action="${screen7Url}">
		<div id="divField">
			<label><spring:message code="screen7.question.value" /></label>
		</div>
		<br>
		<div id="divField">
			<ul class="ul-3-column">
				<li><form:radiobutton path="typesSection1a" id="radio-1a"
						name="radiobutton-1a" value="1" onchange="enabledBtn()" /> <spring:message
						code="common.optYes.value" /></li>
				<li><form:radiobutton path="typesSection1a" id="radio-2a"
						name="radiobutton-2a" value="2" onchange="enabledBtn()" /> <spring:message
						code="common.optNo.value" /></li>
				<li><form:radiobutton path="typesSection1a" id="radio-3a"
						name="radiobutton-3a" value="3" onchange="enabledBtn()" /> <spring:message
						code="common.optIdontknow.value" /></li>
			</ul>
		</div>

		<br>

		<div id="divField">
			<label><spring:message code="screen7.question2.value" /></label>
		</div>
		<br>
		<div id="divField">
			<ul class="ul-3-column">
				<li><form:radiobutton path="typesSection1b" id="radio-1b"
						name="radiobutton-1b" value="1" onchange="enabledBtn()" /> <spring:message
						code="common.optYes.value" /></li>
				<li><form:radiobutton path="typesSection1b" id="radio-2b"
						name="radiobutton-2b" value="2" onchange="enabledBtn()" /> <spring:message
						code="common.optNo.value" /></li>
				<li><form:radiobutton path="typesSection1b" id="radio-3b"
						name="radiobutton-3b" value="3" onchange="enabledBtn()" /> <spring:message
						code="common.optIdontknow.value" /></li>
			</ul>
		</div>
		<br>
		<br>
		<div class="weiter button_rechts">
			<spring:message code="common.btnContinue.caption" var="buttonValue" />
			<input id="sbm" class="cibutton gelb form-submit weiter" type="submit"
				name="submit" value="${buttonValue}" disabled="disabled">
		</div>
	</form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />