<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.neo.pdm.core.model.DefaultUserInfo" %>

<!DOCTYPE HTML>
<html>
<head>
    <title>body</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/pdm.css"/>
</head>
<body>
<div class="frame-wrap">
    <div id="body">
        <div id="bbs-list-notice">
            <p>&gt;&gt; 공지사항</p>
            <table>
            <colgroup>
                <col width="80px"/>
                <col width="100px"/>
                <col width="*"/>
                <col width="80px"/>
                <col width="80px"/>
            </colgroup>
            <tr>
                <td>No.</td><td>등록자</td><td>제목</td><td>확인수</td><td>등록일</td>
            </tr>
            </table>
        </div>
        
        <div id="bbs-list-best">
            <p>&gt;&gt; 주요논쟁</p>
            <table>
            <colgroup>
                <col width="80px"/>
                <col width="100px"/>
                <col width="*"/>
                <col width="80px"/>
                <col width="80px"/>
            </colgroup>
            <tr>
                <td>순위</td><td>게시자</td><td>제목</td><td>추천수</td><td>등록일</td>
            </tr>
            </table>
        </div>
        
        <div style="text-align: right;">
            Total : <%="3,019,299"%> / Today : <%="1,992"%>
        </div>
    </div>
</div>
</body>
</html>