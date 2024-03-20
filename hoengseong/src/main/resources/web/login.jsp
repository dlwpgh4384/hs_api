<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
  <head>
    <meta charset="UTF-8">
    <title>test login page</title>
    <script>
    	function login_go() {
    		alert("로그인 버튼 클릭");
    		return false;
    	}
    </script>
  </head>
  <body>
  	ID: <input type="text" name="login_id" id="login_id"><br>
  	PW: <input type="text" name="login_pw" id="login_pw"><br>
    <button onclick="login_go();">login</button>
  </body>
</html>