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
        <h3 class="text-center">Edition Hotel</h3>
        <div class="row justify-content-center align-items-center">
            <form method="POST" action="${pageContext.request.contextPath}/saveHotel">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" aria-describedby="name">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" aria-describedby="address">
                </div>
                <div class="mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" name="city" aria-describedby="city">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
            <c:if test="${emptyList == 'false'}">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Capacity</th>
                        <th scope="col">Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <c:forEach items="${hotelList}" var="hotel">
                            <th scope="row">${hotelList.indexOf(hotel)+1}</th>
                            <td>${hotel.getName()}</td>
                            <td>${hotel.getAddress()}</td>
                            <td>${hotel.getCity()}</td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/editHotel">
                                    <input type="hidden" name="id" value="${hotel.getId()}">
                                    <button type="submit" class="btn btn-sm btn-success">
                                        Edit
                                    </button>
                                </form>>
                                <form method="POST" action="${pageContext.request.contextPath}/deleteHotel">
                                    <input type="hidden" name="id" value="${hotel.getId()}">
                                    <button type="submit" class="btn btn-sm btn-danger ml-2">
                                        Delete
                                    </button>
                                </form>>
                            </td>
                        </c:forEach>
                    </tr>
                    </tbody>

                </table>
            </c:if>
            <c:if test="${emptyList == 'true'}">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        Add new room
                    </div>
                    <div class="card-body">
                        Any room there!!
                    </div>
                </div>
                </c:if>
            </div>
            <a href="${pageContext.request.contextPath}/addRoom">
                <button type="button" class="btn btn-lg btn-success ml-2 float-right">
                    Add Room
                </button>
            </a>
        </div>

    </jsp:body>
</t:layout>