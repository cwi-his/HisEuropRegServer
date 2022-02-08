<jsp:directive.include file="includes/top.jsp" />

<script type="text/javascript">
	function viewDeliveryAddress() {
			if (document.getElementById('diffAdd').checked) {
				 document.getElementById('da').style.display= 'block';
			} else {
				 document.getElementById('da').style.display= 'none';
			}
		}


	function formatDate(elem, event) {
		var elemVal = elem.value;
		var elemId = elem.id;
		
		if (event == 8){
			elemVal=elemVal.substr(0, elemVal.length);
		}
		else{
			switch (elemVal.length) {
			case 2:
				elemVal = elemVal + ".";
				break;
			case 5:
				elemVal = elemVal + ".";
				break;
			}
		}
		document.getElementById(elemId).value = elemVal;
	}
	
	function validateQty(elem2, event) {
		var elemVal2 = elem2.value;
		if(elemVal2.length==0 && (event == 43 || event == 107)){
		    return true;
		}		    	
		else if (event == 8 || event == 9) {
		    return true;
		}
		else if ( event >= 48 && event <= 57 ) {
		    return true;
		}
		else if( event >= 96 && event <= 105 ) {
		    return true;
		}
		else return false;
	};
	
	function validateQtyNoPlus(elem2, event) {
		var elemVal2 = elem2.value;
		if(elemVal2.length==0 && (event == 43 || event == 107)){
		    return true;
		}		    	
		else if (event == 8 || event == 9) {
		    return true;
		}
		else if ( event >= 48 && event <= 57 ) {
		    return true;
		}
		else if( event >= 96 && event <= 105 ) {
		    return true;
		}
		else return false;
	};
</script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>


<c:url var="jq" value="/javascript/jquery.js" />
<script type="text/javascript" src="${jq}"></script>
  
<div id="formareabox">
	<div id="myProgress">
  		<div id="myBar" style="width: 50%;">50%</div>
	</div>
	<br>
	<div class="ups_lz"></div>
	
	<c:url var="screen5Url" value="screen5.html" />
	<form:form method="POST" modelAttribute="screen5" action="${screen5Url}" onsubmit="viewDeliveryAddress()">
		<spring:message code="common.inputMaxLen.placeholder" var="maxLen"/>
		<spring:message code="common.dateFormat.placeholder" var="dateFormat"/>
		
		<div><spring:message code="screen5.title.value" /></div><br>
	
		<div>${address1}<br>${address2}<br>${address3}</div><br>
		
		<div><spring:message code="screen5.title2.value" /></div><br>
		
		<div id="divField">
	      <spring:message code="screen5.title3.value" var="differentDeliveryAddress"/>	
		  <form:checkbox id="diffAdd"	path="differentAddress" onclick="viewDeliveryAddress()" label="${differentDeliveryAddress}" style="margin-right:5px" />
	    </div><br>
	    
	    <div id="da">
			<div id="clearDiv"></div>
			<div class="ups_lz"><spring:message code="screen5.lblDifferentAddress.value" /></div>
			<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblName.value" /></div>
				<form:input path="optName" maxlength="40" title="name" placeholder="${maxLen}" size="30"/><br>
				<form:errors path="optName" cssStyle="color: #ff0000;" />
			</div>
			<div id="divField">
				<div class="fieldTitel"><spring:message code="screen5.lblSurname.value" /></div>
				<form:input path="optSurname" maxlength="40" placeholder="${maxLen}" size="30"/><br>
				<form:errors path="optSurname" cssStyle="color: #ff0000;" />
			</div>
	
			<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblAddress.value" /></div>
				<form:input path="optAddress" maxlength="40" placeholder="${maxLen}" size="30"/><br>
				<form:errors path="optAddress" cssStyle="color: #ff0000;" />
			</div>
			
			<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblHouseNumber.value" /></div>
				<form:input path="optHouseNumber" maxlength="10" placeholder="${maxLen}" size="30"/><br>
				<form:errors path="optHouseNumber" cssStyle="color: #ff0000;" />
			</div>
			
			<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblAddressAdditional.value" /></div>
				<form:input path="optAddressAdditional" size="30"/><br>
				<form:errors path="optAddressAdditional" cssStyle="color: #ff0000;" />
			</div>
			<div id="divField" style="margin-bottom:3em">
			<div class="fieldTitel"><spring:message code="screen5.lblNote.value" /></div>
				<form:input path="optNote" size="30"/><br>
				<form:errors path="optNote" cssStyle="color: #ff0000;" />
			</div>
			<br>
			<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblPostalCode.value" /></div>
				<form:input path="optPostalCode" maxlength="5" size="30" onkeydown="return validateQtyNoPlus(optPostalCode, event.which);"/><br>
				<form:errors path="optPostalCode" cssStyle="color: #ff0000;" />
			</div>
			<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblCity.value" /></div>
				<form:input path="optCity" maxlength="40" placeholder="${maxLen}" size="30"/><br>
				<form:errors path="optCity" cssStyle="color: #ff0000;" />
			</div>
			<div id="clearDiv"></div>
			<br>
		</div>
	    
	    <div><spring:message code="screen5.title4.value" /></div><br>
	    
	    <div id="divField">
	    	<form:errors path="fromDate" cssStyle="color: #ff0000;" />
			<table style="width:100%">
				<tr>
					<td>
						<spring:message code="screen5.lblFromNowOn.value" var="sendNowLabel"/>
						<form:checkbox id="sendNowCheckbox" path="sendNow" label="${sendNowLabel}" onclick="document.getElementById('sendFromDateCheckbox').checked = !document.getElementById('sendNowCheckbox').checked;" style="margin-right:5px" />
					</td>
					<td>
						<spring:message code="screen5.lblshFrom.value" var="sendFromLabel"/>
						<form:checkbox id="sendFromDateCheckbox" path="sendFromDate" label="${sendFromLabel}" onclick="document.getElementById('sendNowCheckbox').checked = !document.getElementById('sendFromDateCheckbox').checked;" style="margin-right:5px" />
						<form:input id="datepickerFrom" size="25" maxlength="10" path="fromDate" type="text" cssClass="date-picker" readonly="true"/><br>
						 <script>
		                    $(function() {
		                        $("#datepickerFrom").datepicker({
		                        	dateFormat: 'dd.mm.yy',
		                            minDate: 0,
		                            monthNames : [ 'Januar', 'Februar', 'März', 'April', 'Mai', 'Juni', 'Juli', 'August', 'September', 'Oktober', 'November', 'Dezember' ],
									monthNamesShort : [ 'Jan', 'Feb', 'Mär', 'Apr', 'Mai', 'Jun', 'Jul', 'Aug', 'Sep', 'Okt', 'Nov', 'Dez' ],
									dayNames : [ 'Sonntag', 'Montag', 'Dienstag', 'Mittwoch', 'Donnerstag', 'Freitag', 'Samstag' ],
									dayNamesShort : [ 'So', 'Mo', 'Di', 'Mi', 'Do',	'Fr', 'Sa' ],
									dayNamesMin : [ 'So', 'Mo', 'Di', 'Mi', 'Do', 'Fr', 'Sa' ]
		                        });
		                    });
	               		</script>
					</td>
				</tr>
			</table>
		</div>
	<br>
	<div class="weiter button_rechts">
		<spring:message code="common.btnContinue.caption" var="buttonValue" />
		<input class="cibutton gelb form-submit weiter" type="submit" name="submit" value="${buttonValue}">
	</div>
	
</form:form>

</div>
<script type="text/javascript">
	if (document.getElementById('diffAdd').checked) {
		 document.getElementById('da').style.display= 'block';
	} else {
		 document.getElementById('da').style.display= 'none';
	}
</script>
<jsp:directive.include file="includes/bottom.jsp" />
