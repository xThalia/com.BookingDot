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
        <h3 class="text-center">Edition hotel: ${hotel.getName()}</h3>
        <c:if test="${sessionScope.status == 'success'}">
            <div class="alert alert-success" role="alert">
                Room added!
            </div>
        </c:if>
        <c:if test="${sessionScope.status == 'successEdit'}">
            <div class="alert alert-success" role="alert">
                Room saved!
            </div>
        </c:if>
        <c:if test="${sessionScope.status == 'successDelete'}">
            <div class="alert alert-success" role="alert">
                Room deleted!
            </div>
        </c:if>
        <div class="row justify-content-center align-items-center">
            <form method="POST" action="${pageContext.request.contextPath}/saveHotel">
                <input type="hidden" name="hotelId" value="${hotel.getId()}">
                <div class="mb-3">
                    <label for="name" class="form-label">Name</label>
                    <input type="text" class="form-control" id="name" name="name" value="${hotel.getName()}" aria-describedby="name">
                </div>
                <div class="mb-3">
                    <label for="address" class="form-label">Address</label>
                    <input type="text" class="form-control" id="address" name="address" value="${hotel.getAddress()}" aria-describedby="address">
                </div>
                <div class="mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" name="city" value="${hotel.getCity()}" aria-describedby="city">
                </div>
                <button type="submit" class="btn btn-primary">Save</button>
            </form>
            <c:if test="${emptyList == 'false'}">
                <table class="table table-dark">
                    <thead>
                    <tr>
                        <th scope="col">No</th>
                        <th scope="col">Name</th>
                        <th scope="col">Capacity</th>
                        <th scope="col">Price</th>
                        <th scope="col">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${hotelRoomList}" var="room">
                        <tr>
                            <th scope="row">${hotelRoomList.indexOf(room) + 1}</th>
                            <td>${room.getName()}</td>
                            <td>${room.getCapacity()}</td>
                            <td>${room.getPrice()}</td>
                            <td>
                                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/editRoom">
                                    <input type="hidden" name="roomId" value="${room.getId()}">
                                    <input type="hidden" name="hotelId" value="${hotel.getId()}">
                                    <button type="submit" class="btn btn-sm btn-success">
                                        Edit
                                    </button>
                                </form>
                                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/deleteRoom">
                                    <input type="hidden" name="roomId" value="${room.getId()}">
                                    <input type="hidden" name="hotelId" value="${hotel.getId()}">
                                    <button type="submit" class="btn btn-sm btn-danger ml-2">
                                        Delete
                                    </button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
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
            <a href="${pageContext.request.contextPath}/addRoom?hotel=${hotel.getId()}">
                <button type="button" class="btn btn-lg btn-success ml-2 float-right">
                    Add Room
                </button>
            </a>
        </div>

    </jsp:body>
</t:layout>