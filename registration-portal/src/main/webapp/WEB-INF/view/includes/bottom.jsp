
<script type="text/javascript">
<!--
function popup(url) 
{
 var width  = 580;
 var height = 640;
 var left   = screen.width - 960;
 var top    = 0;
 var params = 'width='+width+', height='+height;
 params += ', top='+top+', left='+left;
 params += ', directories=no';
 params += ', location=no';
 params += ', menubar=no';
 params += ', resizable=1';
 params += ', scrollbars=1';
 params += ', status=0';
 params += ', toolbar=0';
 newwin=window.open(url,'customWindow', params);
 if (window.focus) {newwin.focus()}
 return false;
}
</script>

<div class="footer">
	<div class="left">
		Copyright© 2020 Europ Assistance Services GmbH, Adenauerring 9, 81737 München<br>
		    <a href="#" onclick="popup('<%=request.getContextPath()%>/impressum.html')">Impressum</a> |
		    <a href="#" onclick="popup('<%=request.getContextPath()%>/datenschutz.html');">Datenschutz</a>
	</div>

	<div class="right">
    	<img border="0" id="generaliLogoFooter" src="https://www.initiative-meinegesundheit.de/resources/images/logo-generali-footer.gif" alt="Ein Unternehmen der Generali">
  	</div>
  
</div>
</div>

<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.13.0/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<c:url value="/js/custom-file-input.js" />"></script>
</body>
</html>