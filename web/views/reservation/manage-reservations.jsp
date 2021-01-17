
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
            <c:if test="${sessionScope.status == 'successConfirmation'}">
                <div class="alert alert-success" role="alert">
                    Success confirmation
                </div>
            </c:if>
            <h3 class="text-center">Manage your reservations</h3>
            <div class="row justify-content-center align-items-center">
                <c:if test="${emptyList == 'false'}">
                    <table class="table table-dark">
                        <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Name</th>
                            <th scope="col">LastName</th>
                            <th scope="col">Email</th>
                            <th scope="col">Hotel</th>
                            <th scope="col">Room</th>
                            <th scope="col">Number of people</th>
                            <th scope="col">Price</th>
                            <th scope="col">Start date</th>
                            <th scope="col">End date</th>
                            <th scope="col">Action</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${reservationsInfo}" var="reservationInfo">
                            <tr>
                                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/acceptReservation">
                                    <td>${reservationsInfo.indexOf(reservationInfo)+1}</td>
                                    <td>${reservationInfo.getUser().getFirstName()}</td>
                                    <td>${reservationInfo.getUser().getLastName()}</td>
                                    <td>${reservationInfo.getUser().getLogin()}</td>
                                    <td>${reservationInfo.getHotel().getName()}</td>
                                    <td>${reservationInfo.getRoom().getName()}</td>
                                    <td>${reservationInfo.getRoom().getCapacity()}</td>
                                    <td>${reservationInfo.getPrice()}</td>
                                    <td>${reservationInfo.getStartDate()}</td>
                                    <td>${reservationInfo.getEndDate()}</td>
                                    <c:if test="${reservationInfo.getReservation().isReservationConfirmed() == 0}">
                                    <td>
                                        <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/acceptReservation">
                                            <input type="hidden" name="reservationId" value="${reservationInfo.getReservation().getId()}">
                                            <input type="hidden" name="isAccepted" value="1">
                                            <button type="submit" class="btn btn-sm btn-success">
                                                Accept
                                            </button>
                                        </form>
                                        <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/acceptReservation">
                                            <input type="hidden" name="reservationId" value="${reservationInfo.getReservation().getId()}">
                                            <input type="hidden" name="isAccepted" value="2">
                                            <button type="submit" class="btn btn-sm btn-danger ml-2">
                                                Decline
                                            </button>
                                        </form>
                                    </td>
                                    </c:if>
                                    <c:if test="${reservationInfo.getReservation().isReservationConfirmed() == 1}">
                                        <td>Confirmed</td>
                                    </c:if>
                                    <c:if test="${reservationInfo.getReservation().isReservationConfirmed() == 2}">
                                        <td>Declined</td>
                                    </c:if>
                                </form>
                            </tr>
                        </c:forEach>
                        </tbody>

                    </table>
                </c:if>
                <c:if test="${emptyList == 'true'}">
                    <div class="col-md-8">
                        <div class="card">

                            <div class="card-body">
                                No reservations
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:layout>