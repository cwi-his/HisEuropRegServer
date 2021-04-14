<jsp:directive.include file="includes/top.jsp" />
<script src="jquery.modal.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
	function enabledBtn() {

		if (document.getElementById('check').checked) {
			document.getElementById('btnSubmit').disabled = false;
			//window.open ('screen3b.html','_self',false)
			//document.getElementById('submit').style='background:#314397'
		} else {
			//document.getElementById('submit').disabled = true;
			//document.getElementById('submit').location.href='popupAlert.html?1'
			window.open('popupAlert.html?alert=1', 'newwindow','width=600,height=160,status=0,toolbar=0,menubar=0,location=0,addressbar=0,top=200,left=300,resizable=1'); return false;
		}
	}
	
	function submitForm(){
		var btn=document.getElementById('btnSubmit');
		btn.setAttribute('type', 'button');
		if (document.getElementById('check').checked) {
			btn.setAttribute('type', 'submit');
		}
		
	}
	
	
</script>
<c:url var="screen3Url" value="screen3.html" />
<form:form method="POST" modelAttribute="screen3" action="${screen3Url}" id="myForm">

<div id="formareabox">

	<div id="myProgress">
  		<div id="myBar" style="width: 25%;">25%</div>
	</div>
	<br>
	<div class="ups_lz">
		
	</div>

	<label for="title"><spring:message code="screen3.title.value" /></label><br><br>
	<label for="title2"><spring:message code="screen3.title2.value" /></label><br><br>

	<a href="popup.html"  onclick="window.open('popup.html?pdf=1', 'newwindow', 'width=800, height=600'); return false;">
		<label for="title3"><spring:message code="screen3.title3.value" /></label></a>

	<br><br>
	
	<div id="divField">
      <input id="check" type="checkbox" onclick="if(document.getElementById('check').checked) document.getElementById('btnSubmit').removeAttribute('disabled'); else document.getElementById('btnSubmit').setAttribute('disabled', 'disabled');"> <label for="check"><spring:message code="screen3.title4.value" /></label>
    </div>
    
    <br>
    
	<label for="title5"><spring:message code="screen3.title5.value" /></label><br><br>
    
	<div id="inpContinue" class="cibutton gelb weiter_long long-button">
		<spring:message code="common.btnTransmit.caption" var="buttonValue" />
		<input class="cibutton gelb form-submit weiter_long" id="btnSubmit" type="submit" name="submit" value="${buttonValue}" 
			onclick="enabledBtn()" readonly="readonly" disabled="disabled" />
	</div>
	
</div>
</form:form>
<jsp:directive.include file="includes/bottom.jsp" />
