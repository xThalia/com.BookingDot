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
            <h3 class="text-center">Edition room: ${room.getName()}</h3>
            <div class="row justify-content-center align-items-center">
                <form method="POST" action="${pageContext.request.contextPath}/saveRoom" enctype="multipart/form-data">
                    <input type="hidden" name="roomId" value="${room.getId()}">
                    <input type="hidden" name="hotelId" value="${hotelId}">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" value="${room.getName()}" aria-describedby="name" required>
                    </div>
                    <div class="mb-3">
                        <label for="capacity" class="form-label">Capacity</label>
                        <input type="number" class="form-control" id="capacity" name="capacity" value="${room.getCapacity()}" aria-describedby="capacity" required>
                    </div>
                    <div class="mb-3">
                        <label for="price" class="form-label">Price</label>
                        <input type="number" class="form-control" id="price" name="price" value="${room.getPrice()}" aria-describedby="price" required>
                    </div>
                    <div class="mb-3">
                        <label for="picture" class="form-label">Picture</label>
                        <input type="file" class="form-control" id="picture" name="picture" aria-describedby="picture">
                    </div>
                    <c:if test="${room.getPicturePath() != ''}">
                        <img src="${room.getPicturePath()}" class="mb-2" style="max-height: 500px;height: auto; max-width: 500px; width:auto;" />
                    </c:if>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="deletePicture" name="deletePicture" value="true">
                        <label class="form-check-label" for="deletePicture">Delete picture</label>
                    </div>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
            </div>
        </div>
    </jsp:body>
</t:layout>