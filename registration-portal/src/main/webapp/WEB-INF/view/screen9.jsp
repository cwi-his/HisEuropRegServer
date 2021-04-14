<jsp:directive.include file="includes/top.jsp" />
<script type="text/javascript">
	var i=0;

    function enabledBtn() {
    	
    	/*
  		alert();
    	
    	if (i==1) {
    		document.getElementById('sbm').disabled=false;
    		return;
    	}

    	document.getElementById('sbm').disabled=true;
    	
    	i=+1;
    	*/
    }
   
    </script>
<div id="formareabox">
	<div id="myProgress">
		<div id="myBar" style="width: 70%;">70%</div>
	</div>
	<br>
	<div class="ups_lz"></div>
	<br>

	<c:url var="screen9Url" value="screen9.html" />
	<form:form name="screen9" method="POST" modelAttribute="screen9" action="${screen9Url}">
		<div id="divField">
			<table>
				<tr>
					<td width="460px"><label><spring:message code="screen9.question.value" /></label><br>
					<form:errors path="bmiWeight" cssStyle="color: #ff0000;"/></td>
					<td><form:input path="bmiWeight" type="number" maxlength="3" cssStyle="width:50px" /> kg</td>
				</tr>
			</table>
			
			

        </div>
  		<div id="divField">     
  			<table>
				<tr>
					<td width="460px"><label><spring:message code="screen9.question2.value" /></label><br>
					<form:errors path="bmiHeight" cssStyle="color: #ff0000;"/></td>
					<td><form:input path="bmiHeight" type="number" maxlength="3" cssStyle="width:50px" /> cm</td>
				</tr>
			</table>     
		</div>
		<br>
		<br>
    	<div class="weiter button_rechts">
    		<spring:message code="common.btnContinue.caption" var="buttonValue" />
    		<input class="cibutton gelb form-submit weiter"  id="sbm" type="submit" name="submit" value="${buttonValue}" />
		</div>
	</form:form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />