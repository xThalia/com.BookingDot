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
            <h3 class="text-center">Hotels</h3>
            <c:if test="${sessionScope.status == 'success'}">
                <div class="alert alert-success" role="alert">
                    Hotel added!
                </div>
            </c:if>
            <c:if test="${sessionScope.status == 'successEdit'}">
                <div class="alert alert-success" role="alert">
                    Hotel saved!
                </div>
            </c:if>
            <c:if test="${sessionScope.status == 'successDelete'}">
                <div class="alert alert-success" role="alert">
                    Hotel deleted!
                </div>
            </c:if>
            <div class="row justify-content-center align-items-center">
                <c:if test="${emptyList == 'false'}">
                    <table class="table table-dark">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Name</th>
                            <th scope="col">Address</th>
                            <th scope="col">City</th>
                            <th scope="col">Actions</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${hotelList}" var="hotel">
                            <tr>
                                <th scope="row">${hotelList.indexOf(hotel)+1}</th>
                                <td>${hotel.getName()}</td>
                                <td>${hotel.getAddress()}</td>
                                <td>${hotel.getCity()}</td>
                                <td>
                                    <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/editHotel">
                                        <input type="hidden" name="hotelId" value="${hotel.getId()}">
                                        <button type="submit" class="btn btn-sm btn-success">
                                            Edit
                                        </button>
                                    </form>
                                    <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/deleteHotel">
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
                            <div class="card-header">Add new hotel</div>
                            <div class="card-body">
                                You have any hotel!
                            </div>
                        </div>
                    </div>
                </c:if>
                <a href="${pageContext.request.contextPath}/addHotel">
                    <button type="button" class="btn btn-lg btn-success ml-2 float-right">
                        Add Hotel
                    </button>
                </a>
            </div>
        </div>

    </jsp:body>
</t:layout>