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
            <h3 class="text-center">Login</h3>
            <div class="row justify-content-center align-items-center">
                <c:if test="${emptyList == 'false'}">
                    <table class="table table-dark">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">City</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <c:forEach items="${hotelList}" var="hotel">
                                <th scope="row">x</th>
                                <td>${hotel.getName()}</td>
                                <td>${hotel.getAddress()}</td>
                                <td>${hotel.getCity()}</td>
                            </c:forEach>
                        </tr>
                        </tbody>

                    </table>
                </c:if>
                <c:if test="${emptyList == 'true'}">
                <div class="col-md-8">
                    <div class="card">
                        <div class="card-header">Add new hotel</div>
                        <div class="card-body">
                            You have any hotel!
                        </div>
                    </div>
                </div>
                </c:if>
            </div>
        </div>
        </div>
    </jsp:body>
</t:layout>