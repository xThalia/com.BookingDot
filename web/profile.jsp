<%@ page import="providers.UserDataProvider" %>
<%@ page import="connectors.DbConnector" %>
<%@ page import="model.User" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<%
    int id = (int) session.getAttribute("currentSessionUser");
    User user = DbConnector.loadUserById(id);
    request.setAttribute("user", user);
%>
<t:layout>
        <jsp:attribute name="header">
<%--          <h1>Welcome</h1>--%>
        </jsp:attribute>
    <jsp:attribute name="footer">
<%--          <p id="copyright">Copyright 2020, com.BookingDot</p>--%>
        </jsp:attribute>
    <jsp:body>
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-8">

                </div>
            </div>
        </div>
    </jsp:body>
</t:layout>
</html>