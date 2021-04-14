<jsp:directive.include file="includes/top.jsp" />

<script type="text/javascript">
    function viewDiv() {
    	if (document.getElementById('qI1').checked) {
    		//document.getElementById('qB').hidden=false;
    		document.getElementById('qB').style.display = "inline";
    		document.getElementById('submit').disabled = true;
    	}
    	else {
    		//document.getElementById('qB').hidden=true;
    		document.getElementById('qB').style.display = "none";
    		document.getElementById('submit').disabled = false;
    	}
    }

  
</script>
<style type="text/css">
    ul {padding-left:4em}
    li {padding-left:1em}
 </style>
<div id="formareabox">
	<div id="myProgress">
		<div id="myBar" style="width: 80%;">80%</div>
	</div>
	<div class="ups_lz"></div>
	<br>

	<c:url var="screen10Url" value="screen10.html" />
	<form:form method="POST" modelAttribute="screen10" action="${screen10Url}">
	<div id="divField">
	<div class="fieldTitel"><label><spring:message code="screen10.question.value" /></label></div>
	<br><br>
		<ul class="ul-2-column">
			<li><p><form:radiobutton path="questionInsuline" id="qI1" name="radiobutton-1a" value="1" onchange="viewDiv()"/>
							<spring:message code="common.optYes.value" /></li>
			<li><form:radiobutton path="questionInsuline" id="radio-2a" name="radiobutton-2a" value="2" onchange="viewDiv()"/>
						<spring:message code="common.optNo.value" /></li>
		</ul>
	</div>
    <br> 
	<div id="qB" style="display:none">
		<div id="clearDiv"></div>
		<br>
		<div id="divField"><label><spring:message code="screen10.question2.value" /></label></div>
			<br>
		<div class="divField"> 
			<ul class="ul-3-column">
				<li><form:radiobutton path="questionInsulineB" value="1" onclick="submit.disabled=false"/>
							<label><spring:message code="screen10.opt1.value" /></label>
				</li>
				<li><form:radiobutton path="questionInsulineB" value="2" onclick="submit.disabled=false"/>
							<label><spring:message code="screen10.opt2.value" /></label>
				</li>
				<li><form:radiobutton path="questionInsulineB" value="3" onclick="submit.disabled=false"/>
							<label><spring:message code="screen10.opt3.value" /></label>
				</li>
			</ul>				
		</div>
	</div>	
	<br>
    <div id="sbm" class="weiter button_rechts">
    	<spring:message code="common.btnContinue.caption" var="buttonValue" />
    	<input id="submit" class="cibutton gelb form-submit weiter"  type="submit" name="submit" value="${buttonValue}" disabled="disabled">
    </div>
	</form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />