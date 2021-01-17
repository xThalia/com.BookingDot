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
            <h3 class="text-center">Availability of rooms</h3>
            <div class="row justify-content-center align-items-center">
            <c:if test="${emptyList == 'false'}">
                <c:forEach items="${freeAndOccupiedRoomsList}" var="freeAndOccupiedRooms">
                    <h2>${freeAndOccupiedRooms.getName()}</h2>
                    <h4>${freeAndOccupiedRooms.getCity()}</h4>
                    <table class="table table-dark">
                        <thead>
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Capacity</th>
                            <th scope="col">Status</th>
                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${freeAndOccupiedRooms.getFreeRooms()}" var="freeRoom">
                            <tr>
                                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/acceptReservation">
                                    <td>${freeRoom.getName()}</td>
                                    <td>${freeRoom.getCapacity()}</td>
                                    <td style="background-color: green">Available</td>
                                </form>
                            </tr>
                        </c:forEach>
                        <c:forEach items="${freeAndOccupiedRooms.getOccupiedRooms()}" var="occupiedRoom">
                            <tr>
                                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/acceptReservation">
                                    <td>${occupiedRoom.getName()}</td>
                                    <td>${occupiedRoom.getCapacity()}</td>
                                    <td style="background-color: red">Occupied</td>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                    </c:forEach>
                </c:if>
                <c:if test="${emptyList == 'true'}">
                    No info
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:layout>