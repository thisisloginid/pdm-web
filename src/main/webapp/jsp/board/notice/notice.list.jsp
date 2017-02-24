<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.neo.pdm.board.model.NoticeInfo" %>
<%
List<NoticeInfo> notList = (List<NoticeInfo>)request.getAttribute("noticeList");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>notice list</title>
    <script type="text/javascript">
    function fGoNoticeDetail(seq) {
        location.href="/what/Action.action?screenid=SCMBA0003&actionid=S01"+"&seq="+seq;
    }
    </script>
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
                <td>No.</td><td>등록자</td><td>제목</td><td>조회수</td><td>등록일</td>
            </tr>
            <%for(NoticeInfo ni : notList ){%>
            <tr>
                <td><%=ni.getSeq()%></td><td><%=ni.getId()%></td><td onclick="fGoNoticeDetail('<%=ni.getSeq()%>')"><%=ni.getTitle()%></td><td><%=ni.getViewCnt()%></td><td><%=ni.getInputDt()%></td>
            </tr>
            <%}%>
            </table>
        </div>
    </div>
</div>
</body>
</html>