<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri= "http://java.sun.com/jsp/jstl/core"%>
<%@ page import = "constants.ForwardConst" %>
<%@ page import = "constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="actFoll" value= "${ForwardConst.ACT_FOLL.getValue()}" />

<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commShow" value= "${ForwardConst.CMD_SHOW.getValue()}"/>
<c:set var = "commDel" value = "${ForwardConst.CMD_DESTROY.getValue()}" />

<c:import url= "../layout/app.jsp">
    <c:param name="content">
        <h2>【フォロー　一覧】</h2>
        <table id= "follow_list">
            <tbody>
                <tr>
                    <th class= "follower_name">フォローしている人</th>
                    <th class= "report_action">リンク先</th>
                </tr>
                <c:forEach var= "follow" items= "${follows}" varStatus= "status">
                    <tr class= "row${status.count % 2}">
                        <td class= "follower_name"><c:out value= "${follow.follower_name.name}"/>
                        <form method= "POST" action="<c:url value= '?action=${actFoll}&command=${commDel}'/>">
                            <input type="hidden" name="${AttributeConst.FOLL_ID.getValue()}" value="${follow.id}" />
                            <button type="submit">フォローを外す</button>
                        </form>
                        </td>
                        <td class= "report_action"><a href= "<c:url value='?action=${actFoll}&command=${commShow}&id=${follow.follower_name.id}' />">日報一覧</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div id= "pagination">
            （全　${follows_count}　名）<br />
            <c:forEach var="i" begin="1" end="${((follows_count - 1)/ maxRow)+ 1}" step="1">
                <c:choose>
                    <c:when test= "${i ==page}">
                        <c:out value = "${i}" />&nbsp;
                    </c:when>
                    <c:otherwise>
                        <a href= "<c:url value= '?action=${actFoll}&command=${commIdx}&page=${i}'/>"><c:out value= "${i}" /></a>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:param>
</c:import>