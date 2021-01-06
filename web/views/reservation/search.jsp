<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <script>
            $(document).ready(function(){
                $('#datepicker').datepicker();
            });
        </script>
        <div class="container" id="container">
            <h3 class="text-center">Search Hotel</h3>
            <form method="POST" action="${pageContext.request.contextPath}/searchHotel">
                <div class="mb-3">
                    <label for="city" class="form-label">City</label>
                    <input type="text" class="form-control" id="city" name="city" aria-describedby="city">
                </div>

                <input type="text" class="form-control" value="02-16-2012" id="datepicker">
            </form>
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
                                    <form class="d-inline-block mb-0" method="POST" action="${pageContext.request.contextPath}/viewHotel">
                                        <input type="hidden" name="hotelId" value="${hotel.getId()}">
                                        <button type="submit" class="btn btn-sm btn-success ml-2">
                                            View more
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
                            <div class="card-header">Not found!</div>
                            <div class="card-body">
                                Empty hotel list!
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
        <script>
            $(document).ready(function(){
                $('#datepicker').datepicker();
                $('#datepicker').click(function () {
                    console.log("XD");
                });
            });
        </script>
    </jsp:body>
</t:layout>