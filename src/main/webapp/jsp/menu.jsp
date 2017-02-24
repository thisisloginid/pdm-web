<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.neo.pdm.core.model.DefaultUserInfo" %>
<!DOCTYPE HTML>
<html>
<head>
    <title>menu</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/pdm.css"/>
</head>
<body>
<div class="frame-wrap">
    <div id="login" style="height: 50px; margin-top: 10px; text-align: right; font: 13pt bold;">
        login : <input type="text" name="id" size="10" maxlength="30">/<input type="password" name="pw" size="10" maxlength="15"> |
        <a href="/Action.action?screenid=actionid=">sign up</a> |
        <a href="/Action.action?screenid=&actionid=">search id.pw</a>
    </div>
</div>
</body>
</html>