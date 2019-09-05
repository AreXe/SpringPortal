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
    <title><s:message code="title.passwordReset"/></title>
</head>
<body>
<wrapper class="d-flex flex-column bg-anim">
    <!-- Navigation -->
    <%@include file="/WEB-INF/incl/nav.app" %>
    <!-- Page Content -->
    <main class="container-fluid py-3 flex-fill">
        <div class="container">

            <div class="col-md-8 offset-md-2">
                <div class="card border-primary">
                    <h4 class="card-header"><s:message code="button.passwordReset"/></h4>
                    <div class="card-body">

                        <c:if test="${not empty message}">
                            <div class="alert alert-success text-center" role="alert">
                                <c:out value="${message}" />
                            </div>
                        </c:if>

                        <div class="text-center mb-2">
                            <s:message code="register.passwordReset.msg"/>
                        </div>

                        <sf:form id="usersForm" action="password-reset" modelAttribute="passwordToken" enctype="multipart/form-data" method="POST">
                            <div class="form-group row">
                                <label for="email" class="col-sm-3 col-form-label"><s:message code="register.email"/></label>
                                <div class="col-sm-9">
                                    <sf:errors path="email" cssClass="text-danger"/>
                                    <sf:input type="email" path="email" id="email" placeholder="user@example.com" cssClass="form-control" required="required"/>
                                </div>
                            </div>

                            <button class="btn btn-primary btn-lg btn-block mb-3" id="submit" type="submit" disabled="disabled"><s:message code="button.passwordReset"/></button>

                            <div class="text-center">
                                <button class="btn btn-outline-primary" type="button" onclick="window.location.href='${pageContext.request.contextPath}/'"><s:message code="button.return"/></button>
                            </div>
                        </sf:form>
                    </div>
                </div>
            </div>

        </div>
    </main>
    <!-- Footer -->
    <%@include file="/WEB-INF/incl/footer.app" %>
    <script src="/resources/scripts/buttonenabler.js"></script>
</wrapper>
</body>
</html>