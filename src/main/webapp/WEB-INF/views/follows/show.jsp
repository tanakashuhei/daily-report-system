<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix= "c" uri= "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix= "fmt" uri= "http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import = "constants.ForwardConst" %>
<%@ page import= "constants.AttributeConst" %>

<c:set var = "actTop" value = "${ForwardConst.ACT_TOP.getValue()}" />
<c:set var = "actEmp" value = "${ForwardConst.ACT_EMP.getValue()}" />
<c:set var = "actRep" value = "${ForwardConst.ACT_REP.getValue()}" />
<c:set var = "actFoll" value= "${ForwardConst.ACT_FOLL.getValue()}"/>

<c:set var = "commShow" value= "${ForwardConst.CMD_SHOW.getValue()}" />
<c:set var = "commIdx" value = "${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var = "commNew" value = "${ForwardConst.CMD_NEW.getValue()}" />
<c:set var = "commCrt" value = "${ForwardConst.CMD_CREATE.getValue()}" />

<c:import url= "../layout/app.jsp">
    <c:param name= "content">
        <c:if test= "${flush != null}">
            <div id= "flush_success">
            <c:out value= "${flush}"></c:out>
            </div>
        </c:if>
        <h2><c:out value="${follower_name.name}"/>　の個別日報一覧</h2>
        <c:if test="${follower_name.name != login_employee.name}">
            <c:if test= "${relations != true}" >
                <form method= "POST" action="<c:url value= '?action=${actFoll}&command=${commCrt}'/>">
                    <input type="hidden" name="${AttributeConst.FOLL_ID.getValue()}" value="${follower_name.id}" />
                    <button type="submit">フォローする</button>
                </form>
            </c:if>
        </c:if>
        <h3>【日報　一覧】</h3>
        <table id = "report_list">
            <tbody>
                <tr>
                    <th class = "report_name">氏名</th>
                    <th class = "report_date">日付</th>
                    <th class = "report_title">タイトル</th>
                    <th class = "report_action">操作</th>
                </tr>
                <c:forEach var= "report" items = "${reports}" varStatus= "status">
                   <fmt:parseDate value= "${report.reportDate}" pattern= "yyyy-MM-dd" var= "reportDay" type= "date" />
                   <tr class= "row${status.count % 2}">
                    <td class= "report_name"><c:out value= "${report.employee.name}" /></td>
                    <td class= "report_date"><fmt:formatDate value= '${reportDay}' pattern= 'yyyy-MM-dd' /></td>
                    <td class= "report_title">${report.title}</td>
                    <td class= "report_action"><a href= "<c:url value= '?action=${actRep}&command=${commShow}&id=${report.id}' />">詳細を見る</a></td>
                   </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id= "pagination">
            （全 ${reports_count} 件） <br />
            <c:forEach var= "i" begin= "1" end= "${((reports_count - 1)/ maxRow) + 1 }" step ="1">
                <c:choose>
                    <c:when test = "${i ==page }">
                        <c:out value= "${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href="<c:url value='?action=${actFoll}&command=${commShow}&id=${follower_name.id }&page=${i}' />"><c:out value="${i}" /></a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">日報一覧に戻る</a>&nbsp;
    </c:param>
</c:import>