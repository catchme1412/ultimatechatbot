<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<title>Hello App Engine</title>
<script type="text/javascript">
	function chat(e) {
		var charCode = e.which != null ? e.which : e.keyCode;
		var abc = document.getElementById('yourText');
		var scrler = document.getElementById('myDiv');
		var def  = document.getElementById("content");
		var xmlhttp;
		if (charCode == 13) {

			if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
				xmlhttp = new XMLHttpRequest();
			} else {// code for IE6, IE5
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			xmlhttp.onreadystatechange = function() {
				if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
					def.innerHTML = document.getElementById("content").innerHTML
					+ "<font color='gray'><b>You:</b>"+ abc.value+"</br></font>"
					+ "<font color='blue'><b>Guru:</b>"+xmlhttp.responseText + "</font></br>";
					abc.value= null;
					console.log('done......')
					scrler.scrollTop = (def.offsetHeight-(scrler.offsetHeight-15));
					console.log(scrler.scrollTop)
				}
			}
			xmlhttp.open("GET", "/ultimatechatbot?ajax=y", true);
			xmlhttp.send();
			
		}
		
	}

	function addEvent(obj, evt, handler) {
		if (window.attachEvent) {
			obj.attachEvent("on" + evt, handler);
		} else {
			obj.addEventListener(evt, handler, true);
		}
	}
	window.onload = function() {
		//testing();
		try {
			var abc = document.getElementById('yourText');
			//alert(window.attachEvent)
			addEvent(abc, "keyup", chat);
		} catch (err) {
			alert(err);
		}
	}
</script>
</head>

<body>
	<h1>Hello App Engine!</h1>
	<div id="myDiv" style="border:2px solid #CCC; padding:5px; overflow-x: hidden; overflow-y: auto;height: 100px;">
		<div id="content" style="padding:5px;">
		</div>
	</div>
	<textarea id="yourText" name="yourText" rows="3" cols="100"></textarea>
	<table>
		<tr>
			<td colspan="2" style="font-weight: bold;">Available Servlets:</td>
		</tr>
		<tr>
			<td><a href="ultimatechatbot">Ultimatechatbot</a></td>
		</tr>
	</table>
</body>
</html>
