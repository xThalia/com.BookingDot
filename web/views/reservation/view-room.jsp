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
            <h3 class="text-center">Booking room</h3>
            <div class="row justify-content-center align-items-center">
                <div class="card p-0 mb-4">
                    <div class="card-header">
                        <h5 class="card-title">Hotel: ${hotel.getName()}</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Address: ${hotel.getAddress()}</p>
                        <p class="card-text">City: ${hotel.getCity()}</p>
                    </div>
                </div>
                <div class="card p-0 mb-4">
                    <div class="card-header">
                        <h5 class="card-title">Room: ${room.getName()}</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">Capacity: ${room.getCapacity()}</p>
                        <p class="card-text"><strong>Price for the room: ${room.getPrice() * room.getCapacity()}</strong></p>
                        <p class="card-text"><strong>Price for whole stay: ${lengthOfStay * room.getCapacity() * room.getPrice()}</strong></p>

                        <c:if test="${room.getPicturePath() != ''}">
                            <img src="${room.getPicturePath()}" class="mb-2" style="max-height: 500px;height: auto; max-width: 500px; width:auto;" />
                        </c:if>
                    </div>
                </div>
                <form method="POST" action="${pageContext.request.contextPath}/bookRoom">
                    <input type="hidden" name="roomId" value="${roomId}">
                    <input type="hidden" name="hotelId" value="${hotelId}">
                    <input type="hidden" name="startDate" value="${startDate}">
                    <input type="hidden" name="endDate" value="${endDate}">
                    <button type="submit" class="btn btn-primary">Book!</button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:layout>