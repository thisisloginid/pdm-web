<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.neo.pdm.board.model.NoticeInfo" %>
<%
NoticeInfo noticeDetail = (NoticeInfo)request.getAttribute("noticeDetail");
%>
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
            <p>&gt;&gt; 공지사항 &gt; 상세</p>
            <table>
            <colgroup>
                <col width="100px"/>
                <col width="*"/>
            </colgroup>
            <tr>
                <td>No.</td><td><input type="text" name="seq" value="<%=noticeDetail.getSeq() %>" readonly></td>
            </tr>
            <tr>
                <td>등록자</td><td><%=noticeDetail.getId() %></td>
            </tr>
            <tr>
                <td>제목</td><td><input type="text" name="title" value="<%=noticeDetail.getTitle() %>"></td>
            </tr>
            <tr>
                <td>조회수</td><td><%=noticeDetail.getViewCnt() %></td>
            </tr>
            <tr>
                <td>등록일시</td><td><%=noticeDetail.getInputDt() + " " + noticeDetail.getInputTm() %></td>
            </tr>
            <tr>
                <td>수정일시</td><td><%=noticeDetail.getUpdateDt() + " " + noticeDetail.getInputTm() %></td>
            </tr>
            <tr>
                <td>내용</td><td><textarea name="note" cols="70%" rows="10px"><%=noticeDetail.getNote() %></textarea></td>
            </tr>
            <tr>
                <td>공지기간</td><td><input type="text" name="startDate" value="<%=noticeDetail.getStartDate()%>">~<input type="text" name="endDate" value="<%=noticeDetail.getEndDate()%>"></td>
            </tr>
            <tr>
                <td>사용여부</td>
                <td>
                    <select name="userYn">
                        <option value="N" <%="N".equals(noticeDetail.getUseYn())?"selected":"" %>>N</option>
                        <option value="Y" <%="Y".equals(noticeDetail.getUseYn())?"selected":"" %>>Y</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>첨부파일</td>
                <td>
                    <input type="text" name="attachFile" value="<%=noticeDetail.getAttachFile() %>">
                    <input type="text" name="attachFileName" value="<%=noticeDetail.getAttachFileName() %>">
                </td>
            </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>