<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSE Demo</title>
</head>
<body>
	<div id="msgFrompPush">.....\n</div>

	<script type="text/javascript" src="<c:url value="assets/js/jquery.js"></c:url>"></script>
	<script type="text/javascript">
		if (!!window.EventSource) {
			var source = new EventSource('push');
			s = '';
			source.addEventListener('message', function(e){
				s += e.data + '<br/>';
				$("#msgFrompPush").html(s);
			});
			
			source.addEventListener('open', function(e){
				console.log("connection is openned...");
			}, false);
			
			source.addEventListener('error', function(e){
				if(e) {
					if(e.readyState == EventSource.CLOSED) {
						console.log("connection closed....");
					} else {
						if (e.readyState) {
							console.log(e.readyState);
						} else {
							console.log("error: other readyState");
						}
					}
				}
			}, false);
		} else {
			console.log()
		}
	</script>
</body>
</html>