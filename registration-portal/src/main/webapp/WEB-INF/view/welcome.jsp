<jsp:directive.include file="includes/top.jsp" />

<div id="formareabox">

	<c:url var="loginUrl" value="/login" />
	<form action="${loginUrl}" method="POST" class="form-horizontal">
		<c:if test="${param.error != null}">
			<div class="errortext">
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</div>
		</c:if>
		
		<c:if test="${not empty viewMsg}">
			<div>				
				<p style="color:green;font-weight: bold;">${viewMsg}</p>			
			</div>		
		</c:if>	
		
		<div class="blauebalken"></div>
		<div class="ups_lz"><spring:message code="screen1.title.value" /></div><br>
		
		
		<label for="firstName"><spring:message code="screen1.title2.value" /></label><br><br>
		<label for="firstName"><spring:message code="screen1.title3.value" /></label><br><br>

		<input type="hidden" name="registration-code" value="${token}" placeholder="">

		<div class="weiter_long long-button">
		<spring:message code="common.btnContinueRegistration.caption" var="buttonValue" />
			<input class="cibutton gelb form-submit weiter_long" type="submit" name="commit" value="${buttonValue}">
		</div>
		<br>
	</form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />
