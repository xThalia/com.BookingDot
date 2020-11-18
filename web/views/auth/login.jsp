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
<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">
                    <c:if test="${sessionScope.status == 'success'}">
                        <div class="alert alert-success" role="alert">
                            User added!
                        </div>
                    </c:if>
                    <c:if test="${sessionScope.status == 'error'}">
                        <div class="alert alert-error" role="alert">
                            Wrong login data!
                        </div>
                    </c:if>
                    <h1>Login</h1>
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
                    </form>
                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>