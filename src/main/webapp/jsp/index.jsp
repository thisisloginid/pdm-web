<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.neo.pdm.board.model.NoticeInfo" %>
<%@ page import="com.neo.pdm.board.model.DiscussionInfo" %>
<%
List<NoticeInfo> notList = (List<NoticeInfo>)request.getAttribute("noticeList");
List<DiscussionInfo> disList = (List<DiscussionInfo>)request.getAttribute("discussionList");
%>
<!DOCTYPE HTML>
<html>
<head>
    <title>body</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="/resources/css/pdm.css"/>
    <script type="text/javascript">
    function fGoNoticeList() {
        location.href="/what/Action.action?screenid=SCMBA0002&actionid=S01";
    }
    
    function fGoNoticeDetail(seq) {
        location.href="/what/Action.action?screenid=SCMBA0003&actionid=S01"+"&seq="+seq;
    }
    
    function fGoDiscussionDetail(docId){
        location.href="/what/Action.action?screenid=SCMBA0006&actionid=S01"+"&documentId="+docId;
    }
    </script>
</head>
<body>
<div class="frame-wrap">
    <div id="body">
        <div id="bbs-list-notice">
            <p onclick="fGoNoticeList();">&gt;&gt; 공지사항 &gt; <a href="/what/Action.action?screenid=SCMBA0004&actionid=I01">등록</a></p>
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
        
        <div id="bbs-list-best">
            <p>&gt;&gt; 주요논쟁 &gt; <a href="/what/Action.action?screenid=SCMBA0005&actionid=I01">등록</a></p>
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
            <%for(int i=0; i<disList.size(); i++ ){
                DiscussionInfo di = disList.get(i);
            %>
            <tr>
                <td><%=(i+1)%></td><td><%=di.getId()%></td><td onclick="fGoDiscussionDetail('<%=di.getDocumentId()%>')"><%=di.getDocumentTitle()%></td><td><%=di.getViewCnt()%></td><td><%=di.getInputDt()%></td>
            </tr>
            <%}%>
            </table>
        </div>
        
        <div style="text-align: right;">
            Total : <%="3,019,299"%> / Today : <%="1,992"%>
        </div>
    </div>
</div>
</body>
</html>