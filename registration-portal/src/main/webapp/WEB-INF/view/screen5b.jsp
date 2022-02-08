<jsp:directive.include file="includes/top.jsp" />

<script type="text/javascript">
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
  		<div id="myBar" style="width: 75%;">75%</div>
	</div>
	<br>
	<div class="ups_lz"></div>
	
	<c:url var="screen5bUrl" value="screen5b.html" />
	<form:form method="POST" modelAttribute="screen5b" action="${screen5bUrl}" onsubmit="viewDeliveryAddress()">
		<spring:message code="common.inputMaxLen.placeholder" var="maxLen"/>
		<spring:message code="common.dateFormat.placeholder" var="dateFormat"/>
		
		<div><spring:message code="screen5b.title.value" /></div><br>
	
		<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5b.lblPhone.value" /></div>
			<label>${phone}</label><br>
		</div>
			
		<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5b.lblAdditionalPhone.value" /></div>
			<form:input id="telephoneNumber2" path="telephoneNumber2" onkeydown="return validateQty(telephoneNumber2, event.which);" size="30"/><br>
			<form:errors path="telephoneNumber2" cssStyle="color: #ff0000;" />
		</div>
		<br>
		<br>
		<div><spring:message code="screen5b.title2.value" /></div><br>

		<div id="divField">
			<div class="fieldTitel"><spring:message code="screen5.lblAvailabilityWeekdays.value" /></div>
			<ul class="ul-1-column">
			<li>
				<form:checkbox path="availability" items="${availabilitys}" id="cb1" value="1" onclick="if(document.getElementById('cb1').checked) {document.getElementById('cb2').checked = false; document.getElementById('cb3').checked = false; document.getElementById('cb4').checked = false;}" />
				<spring:message code="screen5.lblFirstMorning.value"/>
			</li>
			<li>
				<form:checkbox path="availability2" items="${availabilitys}" id="cb2" value="1" onclick="if(document.getElementById('cb2').checked) {document.getElementById('cb1').checked = false; document.getElementById('cb3').checked = false; document.getElementById('cb4').checked = false;}" />
				<spring:message code="screen5.lblMorning.value"/>
			</li>
			<li>
				<form:checkbox path="availability3" items="${availabilitys}" id="cb3" value="1" onclick="if(document.getElementById('cb3').checked) {document.getElementById('cb1').checked = false; document.getElementById('cb2').checked = false; document.getElementById('cb4').checked = false;}" />
				<spring:message code="screen5.lblAfternoon.value"/>
			</li>
			<li>
				<form:checkbox path="availability4" items="${availabilitys}" id="cb4" value="1" onclick="if(document.getElementById('cb4').checked) {document.getElementById('cb1').checked = false; document.getElementById('cb2').checked = false; document.getElementById('cb3').checked = false;}" />
				<spring:message code="screen5.lblEvening.value"/>
			</li>
			<li>
				<form:errors path="availability" cssStyle="color: #ff0000;" />
			</li>
			</ul>
		</div>
		
		<br>
		
		<div><spring:message code="screen5.lblContact.value" /></div>
		
		
		<div id="divField">
		<br>
		
		
			<ul class="ul-1-column">
				<li>
					<label><spring:message code="screen5.lblFromNowOn.value" /></label> <form:checkbox path="contactNow" id="cbContactNow" onclick="document.getElementById('datepickerFrom').value = ''" style="margin-left:5px" />
				</li>
				<li>
					<br>
				</li>

				<li>
					<label><spring:message code="screen5.lblFrom.value" /> </label>
					<form:input id="datepickerFrom" size="25" maxlength="10" path="fromDate" type="text" cssClass="date-picker" readonly="true" onchange="if(document.getElementById('datepickerFrom').value != '') document.getElementById('cbContactNow').checked = false;" /><br>
					<form:errors path="fromDate" cssStyle="color: #ff0000;" />
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
				</li>

			</ul> 
		
		</div>
		<br>
		<div class="weiter button_rechts">
			<spring:message code="common.btnContinue.caption" var="buttonValue" />
			<input class="cibutton gelb form-submit weiter" type="submit" name="submit" value="${buttonValue}">
		</div>
	
	</form:form>

</div>
<jsp:directive.include file="includes/bottom.jsp" />
