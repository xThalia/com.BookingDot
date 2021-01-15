<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<t:layout>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
    </jsp:attribute>
    <jsp:body>
        <div class="container" id="container">
            <h3 class="text-center">Search Hotel</h3>
            <form method="POST" action="${pageContext.request.contextPath}/searchHotel">
                <div class="container" style="display: inline-flex; ">
                    <div class="mb-3" style="width: 26%; margin-left: 2%; margin-right: 2%;">
                        <label for="city" class="form-label">City</label>
                        <input type="text" class="form-control" id="city" name="city" aria-describedby="city">
                    </div>
                    <div class="mb-3" style="width: 26%; margin-right: 2%;">
                        <label for="datepicker" class="form-label">Check-in</label>
                        <input type="text" class="form-control" value="${startDate}" name="datepicker" id="datepicker">
                    </div>
                    <div class="mb-3" style="width: 26%; margin-right: 2%;">
                        <label for="datepicker2" class="form-label">Check-out</label>
                        <input type="text" class="form-control" value="${endDate}" name="datepicker2" id="datepicker2">
                    </div>
                    <div class="mb-3" style="width: 12%; margin-right: 2%;">
                        <button type="submit" class="btn btn-primary" style="height: 100%;">Search</button>
                    </div>
                </div>

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
                                        <input type="hidden" name="hotel" value="${hotel}">
                                        <input type="hidden" name="startDate" value="${startDate}">
                                        <input type="hidden" name="endDate" value="${endDate}">
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
                $('#datepicker').datepicker({
                    format: "yyyy-mm-dd",
                    maxViewMode: 2,
                    todayBtn: "linked",
                    weekStart: 1,
                    daysOfWeekDisabled: "0,6",
                    daysOfWeekHighlighted: "0,6",
                    autoclose: true
                });
                $('#datepicker2').datepicker({
                    format: "yyyy-mm-dd",
                    maxViewMode: 2,
                    todayBtn: "linked",
                    weekStart: 1,
                    daysOfWeekDisabled: "0,6",
                    daysOfWeekHighlighted: "0,6",
                    autoclose: true
                });
            });
        </script>
    </jsp:body>
</t:layout>