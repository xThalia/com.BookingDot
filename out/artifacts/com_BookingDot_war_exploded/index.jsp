<%--
  Created by IntelliJ IDEA.
  User: natka
  Date: 27.10.2020
  Time: 20:38
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<t:layout>
    <jsp:attribute name="header">
<%--      <h1>Welcome</h1>--%>
    </jsp:attribute>
    <jsp:attribute name="footer">
<%--      <p id="copyright">Copyright 2020, com.BookingDot</p>--%>
    </jsp:attribute>
    <jsp:body>
        <div class="container">
        <div class="row justify-content-center">
            <div class="col-8">
                <div class="card">
                    <div class="card-header">Dashboard</div>

                    <div class="card-body">
                        <c:if test="${param.token == 'success'}">
                            <div class="alert alert-success" role="alert">
                                You are log out!
                            </div>
                        </c:if>
                        <div class="row">
                            <div class="col-4">
                                <img src="${pageContext.request.contextPath}/img/logo.png"  height="70"/>
                            </div>
                            <div class="col-8">
                                <h3>Reservation App</h3>
                            </div>
                            </divrow>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>