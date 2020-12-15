<%@tag import="connectors.DbConnector" %>
<%@tag import="model.User" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div id="pageheader">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="${pageContext.request.contextPath}/img/logo.png"  height="70">
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/home.jsp">Home</a>
                    </li>
                    <% if(session.getAttribute("currentSessionUser") == null) {%>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/token">Token verify</a>
                    </li>
                    <%}%>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Pricing</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-expanded="false">
                            Dropdown link
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li><a class="dropdown-item" href="#">Action</a></li>
                            <li><a class="dropdown-item" href="#">Another action</a></li>
                            <li><a class="dropdown-item" href="#">Something else here</a></li>
                        </ul>
                    </li>
                </ul>

                <% if(session.getAttribute("currentSessionUser") == null) {%>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item ml-auto">
                    <li><a class="nav-link" href="${pageContext.request.contextPath}/login.jsp">Login</a>
                    </li>
                    <li class="nav-item ml-auto">
                        <a class="nav-link" href="${pageContext.request.contextPath}/register.jsp">Register</a>
                    </li>
                </ul>
                <%} else {%>
                <ul class="navbar-nav mr-4 ml-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarUserMenu" role="button" data-toggle="dropdown" aria-expanded="false">
                            <% User user = DbConnector.loadUserById((int)session.getAttribute("currentSessionUser"));
                            String firstName = user.getFirstName();
                            String lastName = user.getLastName();
                            out.print(firstName+" ");
                            out.print(" ");
                            out.print(lastName);
                            %>
                        </a>
                        <ul class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarUserMenu">
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/profile.jsp">Profile</a></li>
                            <% if(user.getUserPrivilege().getValue() == 3) {%>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/hotels">Manage your Hotels</a></li>
                            <%}%>
                            <% if(user.getUserPrivilege().getValue() == 4) {%>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/privileges">Manage Privileges</a></li>
                            <%}%>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
                <%}%>
            </div>
        </div>
    </nav>
    <jsp:invoke fragment="header"/>
</div>
<div id="body">
    <jsp:doBody/>
</div>
<div id="pagefooter">
    <jsp:invoke fragment="footer"/>
</div>
</body>
</html>