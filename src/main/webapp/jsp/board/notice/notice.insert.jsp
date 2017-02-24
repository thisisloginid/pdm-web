<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>notice list</title>
</head>
<body>
<div class="frame-wrap">
    <div id="body">
        <div id="bbs-list-notice">
            <p>&gt;&gt; 공지사항 &gt; 등록</p>
            <table>
            <colgroup>
                <col width="100px"/>
                <col width="*"/>
            </colgroup>
            <tr>
                <td>제목</td><td><input type="text" name="title"></td>
            </tr>
            <tr>
                <td>내용</td><td><textarea name="note" cols="70%" rows="10px"></textarea></td>
            </tr>
            <tr>
                <td>공지기간</td><td><input type="text" name="startDate">~<input type="text" name="endDate"></td>
            </tr>
            <tr>
                <td>첨부파일</td>
                <td>
                    <input type="text" name="attachFile">
                    <input type="text" name="attachFileName">
                </td>
            </tr>
            </table>
            <button onclick="javascript:location.href='/what/Action.action?screenid=SCMBA0004&actionid=C01'">SCMBA0004.C01</button>
        </div>
    </div>
</div>
</body>
</html>