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
		<div id="divField">
			<br>
			<input type="text" name="registration-code" value="" placeholder="" style="width:100%">
		</div>

		<div class="weiter_long long-button">
		<spring:message code="common.btnCheck.caption" var="buttonValue" />
			<input class="cibutton gelb form-submit weiter_long" type="submit" name="commit" value="${buttonValue}">
		</div>
		<br>
	</form>
</div>

<jsp:directive.include file="includes/bottom.jsp" />
