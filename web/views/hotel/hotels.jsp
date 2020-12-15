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
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <c:if  test="${sessionScope.currentSessionUser == null }">
            <c:redirect url="/views/auth/login.jsp"/>
        </c:if>
        <div class="container">
            <div class="row justify-content-center align-items-center">
                <div class="col-md-6">
                    <c:if test="${param.status == 'success'}">
                        <div class="alert alert-success" role="alert">
                            User added!
                        </div>
                    </c:if>
                    <c:if test="${param.token == 'success'}">
                        <div class="alert alert-success" role="alert">
                            Account verified!
                        </div>
                    </c:if>
                    <c:if test="${param.status == 'error'}">
                        <div class="alert alert-danger" role="alert">
                            Wrong login data!
                        </div>
                    </c:if>
                        <h3 class="text-center">Hotels</h3>
                    <hr>
                    <form method="POST" action="${pageContext.request.contextPath}/login.jsp">
                        <div class="mb-3 col-12">
                            <label for="email" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="email" name="email">
                        </div>
                        <div class="mb-3 col-12">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password">
                        </div>
                        <button type="submit" class="btn btn-primary">Submit</button>
                        <p class="message">Not registered? <a href="${pageContext.request.contextPath}/register.jsp">Create an account</a></p>
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>