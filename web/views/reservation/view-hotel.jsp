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
            <h3 class="text-center">View hotel</h3>
            <div class="row justify-content-center align-items-center">
                <div class="card p-0 mb-4">
                    <div class="card-header">
                        <h5 class="card-title">${hotel.getName()}</h5>
                    </div>
                    <div class="card-body">
                        <p class="card-text">${hotel.getAddress()}</p>
                        <p class="card-text">${hotel.getCity()}</p>
                    </div>
                </div>
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
                                    <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/viewRoom">
                                        <input type="hidden" name="roomId" value="${room.getId()}">
                                        <input type="hidden" name="hotelId" value="${hotel.getId()}">
                                        <input type="hidden" name="startDate" value="${startDate}">
                                        <input type="hidden" name="endDate" value="${endDate}">
                                        <button type="submit" class="btn btn-sm btn-success">
                                            See room
                                        </button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </c:if>
                <c:if test="${emptyList == 'false'}">
                    <h5 class="text-center pt-2">Comments</h5>
                    <c:forEach items="${comments}" var="comment">
                        <div class="card mb-2 p-0">
                            <div class="card-header">${comment.getUserNameToShow()}</div>
                            <div class="card-body">
                                <p>${comment.getText()}</p>
                            </div>
                        </div>
                    </c:forEach>
                </c:if>
                <h5 class="text-center">Add comment</h5>
                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/addComment">
                    <input type="hidden" name="hotelId" value="${hotel.getId()}">
                    <div class="form-floating">
                        <textarea class="form-control" placeholder="Leave a comment here" name="comment" id="comment" style="height: 100px"></textarea>
                        <label for="comment">Comment</label>
                    </div>
                    <button type="submit" class="btn btn-primary btn-success">
                        Save
                    </button>
                </form>
            </div>
        </div>

    </jsp:body>
</t:layout>