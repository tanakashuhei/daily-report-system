<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actFoll" value="${ForwardConst.ACT_FOLL.getValue()}"/>
<c:set var="actIine" value="${ForwardConst.ACT_IINE.getValue()}"/>
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commShow" value="${ForwardConst.CMD_SHOW.getValue()}"/>
<c:set var="commCre" value="${ForwardConst.CMD_CREATE.getValue()}"/>
<c:set var="goodFlag" value="${AttributeConst.FLAG_GOOD.getIntegerValue()}"/>
<c:set var="badFlag" value="${AttributeConst.FLAG_BAD.getIntegerValue()}"/>

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>日報 詳細ページ</h2>
        GOOD：${good_count}&nbsp;&nbsp;
        BAD：${bad_count}<br/><br/>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${report.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${report.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${report.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_employee.id == report.employee.id}">
            <p>
                <a href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
            </p>
        </c:if>
         <c:if test="${report.employee.name != login_employee.name}">
            <c:if test="${id == null}">
                <a href="<c:url value='?action=${actIine}&command=${commEdt}&id=${report.id}&pushFlag=${goodFlag}'/>"><img src ="<c:url value= '/css/image/goodminiNO.jpg'/>" alt="いいね"></a>
                <a href="<c:url value='?action=${actIine}&command=${commEdt}&id=${report.id}&pushFlag=${badFlag}'/>"><img src ="<c:url value= '/css/image/badminiNO.jpg'/>" alt="ないわ"> </a>
            </c:if>
            <c:if test="${id.pushFlag ==1}">
                <a href="<c:url value='?action=${actIine}&command=${commEdt}&id=${report.id}&pushFlag=${goodFlag}'/>"><img src ="<c:url value= '/css/image/goodmini.jpg'/>" alt="いいね"></a>
                <a href="<c:url value='?action=${actIine}&command=${commEdt}&id=${report.id}&pushFlag=${badFlag}'/>"><img src ="<c:url value= '/css/image/badminiNO.jpg'/>" alt="ないわ"> </a>
            </c:if>
            <c:if test="${id.pushFlag ==0}">
                <a href="<c:url value='?action=${actIine}&command=${commEdt}&id=${report.id}&pushFlag=${goodFlag}'/>"><img src ="<c:url value= '/css/image/goodminiNO.jpg'/>" alt="いいね"></a>
                <a href="<c:url value='?action=${actIine}&command=${commEdt}&id=${report.id}&pushFlag=${badFlag}'/>"><img src ="<c:url value= '/css/image/badmini.jpg'/>" alt="ないわ"> </a>
            </c:if>
        </c:if>
        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">日報一覧に戻る</a>&nbsp;
            <a href="<c:url value='?action=${actFoll}&command=${commShow}&id=${report.employee.id}'/>">個別日報一覧</a>
        </p>
    </c:param>
</c:import>