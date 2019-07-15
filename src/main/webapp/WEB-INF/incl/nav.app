<nav class="navbar sticky-top navbar-expand-md navbar-dark bg-dark">
    <div class="container">
        <a class="navbar-brand" href="/index"><i class="fas fa-dice text-white"></i> <s:message code="title.name"/></a>

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link active" id="nav-home" href="/index">Start
                    <span class="sr-only">(current)</span>
                    </a>
                </li>

                <sec:authorize access="hasRole('ANONYMOUS')">
                <li class="nav-item">
                    <a class="nav-link" id="nav-login" href="/login">Log in</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="nav-register" href="/register">Register</a>
                </li>
                </sec:authorize>

                <sec:authorize access="hasRole('ROLE_ADMIN')">
                <li class="nav-item">
                    <a class="nav-link text-danger" id="nav-admin" href="/admin">Admin</a>
                </li>
                </sec:authorize>

                <sec:authorize access="isAuthenticated()">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="nav-profile" href="#" id="profileDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Profile</a>
                    <div class="dropdown-menu" aria-labelledby="profileDropdown">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/profile">Your profile</a>
                        <div class="dropdown-divider"></div>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/editprofile">Edit profile</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/changepassword">Change password</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/logout">Log out</a>
                </li>
                </sec:authorize>
            </ul>
        </div>
    </div>
</nav>