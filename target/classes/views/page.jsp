<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>servlet async support</title>
</head>
<body>
	<div id="time" style="font-size: 75px; text-align: center; vertical-align: middle;"></div>
	<script type="text/javascript" src="assets/js/jquery.js"></script>
	<script type="text/javascript">
		deferred();
		
		function deferred() {
			$.get('defer', function(data) {
				console.log(data);
				$("#time").html(data);
				deferred(); //一次请求完成后, 再向后台发请求.
			});
		}
	</script>
</body>
</html>