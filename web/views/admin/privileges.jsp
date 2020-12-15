
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

                                <td>${userList.indexOf(user)+1}</td>
                                <td>${user.getFirstName()}</td>
                                <td>${user.getLastName()}</td>
                                <td contenteditable="true" >${user.getUserPrivilege().toString()}</td>
                            <td>
              <span class="table-remove"><button type="button"
                                                 class="btn btn-dark btn-sm my-0">Save</button></span>
                            </td>

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