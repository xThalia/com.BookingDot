
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
            <h3 class="text-center">Privileges</h3>
            <div class="row justify-content-center align-items-center">
                <c:if test="${emptyList == 'false'}">
                    <table class="table table-dark">
                        <thead>
                        <tr>
                            <th scope="col">No</th>
                            <th scope="col">Name</th>
                            <th scope="col">LastName</th>
                            <th scope="col">Privilege</th>
                            <th scope="col">Edit</th>

                        </tr>
                        </thead>
                        <tbody>

                        <c:forEach items="${userList}" var="user">
                            <tr>
                                <form class="d-inline-block" method="POST" action="${pageContext.request.contextPath}/savePrivileges">
                                    <td>${userList.indexOf(user)+1}</td>
                                    <td>${user.getFirstName()}</td>
                                    <td>${user.getLastName()}</td>
                                    <td>

                                        <div class="form-group">
                                            <select class="form-control" id="privilege" name="privilege">
                                                <option value="1"<c:if test="${user.getUserPrivilege().getValue() == 1}">  selected </c:if> >Ordinary</option>
                                                <option value="2" <c:if test="${user.getUserPrivilege().getValue() == 2}">  selected </c:if> >Receptionist</option>
                                                <option value="3" <c:if test="${user.getUserPrivilege().getValue() == 3}">  selected </c:if>>Owner</option>
                                                <option value="4" <c:if test="${user.getUserPrivilege().getValue() == 4}">  selected </c:if> >Admin</option>
                                            </select>
                                        </div>
                                    </td>
                                    <td>

                                        <input type="hidden" name="userId" value="${user.getId()}">
                                        <button type="submit" class="btn btn-dark btn-rounded btn-sm my-0"> Save
                                        </button>

                                    </td>
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
                                No users
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </jsp:body>
</t:layout>