<%@tag import="connectors.DbConnector" %>
<%@tag import="model.User" %>
<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<%@attribute name="footer" fragment="true" %>
<html>
<head>
    <script
            src="https://code.jquery.com/jquery-3.5.1.min.js"
            integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
            crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-CuOF+2SnTUfTwSZjCXf01h7uYhfOBuxIhGKPbfEJ3+FqH/s6cIFN9bGr1HmAg4fQ" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-popRpmFF9JQgExhfw5tZT4I9/CI5e2QcuUZPOVXb1m7qUmeR2b50u+YFEYe1wgzy" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/locales/bootstrap-datepicker.en-GB.min.js" integrity="sha512-r4PTBIGgQtR/xq0SN3wGLfb96k78dj41nrK346r2pKckVWc/M+6ScCPZ9xz0IcTF65lyydFLUbwIAkNLT4T1MA==" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.min.css" integrity="sha512-mSYUmp1HYZDFaVKK//63EcZq4iFWFjxSL+Z3T/aCt4IO9Cejm03q3NKKYN6pFQzY0SBOr8h+eCIAZHPXcpZaNw==" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/css/bootstrap-datepicker.standalone.min.css" integrity="sha512-TQQ3J4WkE/rwojNFo6OJdyu6G8Xe9z8rMrlF9y7xpFbQfW5g8aSWcygCQ4vqRiJqFsDsE1T6MoAOMJkFXlrI9A==" crossorigin="anonymous" />
</head>
<body>
<div id="pageheader">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">
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
                        <a class="nav-link" href="${pageContext.request.contextPath}/search">Search</a>
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
                            <% if(user.getUserPrivilege().getValue() == 3 || user.getUserPrivilege().getValue() == 2) {%>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath}/reservations">Manage Reservations</a></li>
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