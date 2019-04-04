<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<!-- Head -->
<head>
    <%@include file="/WEB-INF/incl/head.app" %>
    <title><s:message code="title.register"/></title>
</head>
<body>
<wrapper class="d-flex flex-column">
    <!-- Navigation -->
    <%@include file="/WEB-INF/incl/nav.app" %>
    <!-- Page Content -->
    <main class="container-fluid py-3 flex-fill">
        <div class="container">
            <h2>Register new account</h2>

            <sf:form id="usersForm" action="adduser" modelAttribute="user" enctype="multipart/form-data" method="POST">
            <div class="form-group row">
                <label for="login" class="col-sm-2 col-form-label"><s:message code="register.login"/></label>
                <div class="col-sm-10">
                    <sf:errors path="login"/>
                    <sf:input type="text" path="login" id="login" cssClass="form-control" required="required"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="email" class="col-sm-2 col-form-label"><s:message code="register.email"/></label>
                <div class="col-sm-10">
                    <sf:errors path="email"/>
                    <sf:input type="email" path="email" id="email" cssClass="form-control" required="required"/>
                </div>
            </div>
            <div class="form-group row">
                <label for="password" class="col-sm-2 col-form-label"><s:message code="register.password"/></label>
                <div class="col-sm-10">
                    <sf:errors path="password"/>
                    <sf:input type="password" path="password" id="password" cssClass="form-control" required="required"/>
                </div>
            </div>
                <div class="form-group row">
                    <label for="firstName" class="col-sm-2 col-form-label"><s:message code="register.firstName"/></label>
                    <div class="col-sm-10">
                        <sf:input type="text" path="firstName" id="firstName" cssClass="form-control" required="required"/>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="lastName" class="col-sm-2 col-form-label"><s:message code="register.lastName"/></label>
                    <div class="col-sm-10">
                        <sf:input type="text" path="lastName" id="lastName" cssClass="form-control" required="required"/>
                    </div>
                </div>

                <button class="btn btn-primary btn-lg btn-block" type="submit"><s:message code="button.register"/></button>
            </sf:form>

        </div>
    </main>
    <!-- Footer -->
    <%@include file="/WEB-INF/incl/footer.app" %>
</wrapper>
</body>
</html>