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
      <h1>Welcome</h1>
    </jsp:attribute>
  <jsp:attribute name="footer">
      <p id="copyright">Copyright 2020, com.BookingDot</p>
    </jsp:attribute>
  <jsp:body>
    <form method="post" action="test">
      <div class="form-group col-6">
        <input class="form-control" type="text" name="test">
      </div>
      <button class="btn btn-lg btn-primary" type="submit"> zapisz </button>
    </form>
      <a class="nav-link" href="login.jsp">Login</a>
  </jsp:body>
</t:layout>