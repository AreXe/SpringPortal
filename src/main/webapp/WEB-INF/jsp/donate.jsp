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
    <title><s:message code="title.donate"/></title>
</head>
<body>
<wrapper class="d-flex flex-column">
    <!-- Navigation -->
    <%@include file="/WEB-INF/incl/nav.app" %>
    <!-- Page Content -->
    <main class="container-fluid py-3 flex-fill">
        <div class="container">

            <div class="py-3 pt-md-3 mx-auto text-center">
                <h1 class="display-4">Donations</h1>
                <p class="lead">If you like our work and want to support us, you can choose the amount of support below and get some specials in return!*</p>
            </div>

            <div class="card-deck text-center">
                <div class="card mx-auto mb-4 shadow-sm">
                    <div class="card-header">
                        <h4 class="my-0 font-weight-normal">Small</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">5 <small class="text-muted">zł</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>Highlighted nick</li>
                            <li>No ads for 1 month</li>
                            <li>Faster editing</li>
                        </ul>
                    </div>
                </div>
                <div class="card mx-auto mb-4 shadow-sm">
                    <div class="card-header bg-secondary">
                        <h4 class="my-0 font-weight-normal">Noticeable</h4>
                    </div>
                    <div class="card-body">
                        <h1 class="card-title pricing-card-title">20 <small class="text-muted">zł</small></h1>
                        <ul class="list-unstyled mt-3 mb-4">
                            <li>VIP account</li>
                            <li>No ads</li>
                            <li>Unlimited editing</li>
                        </ul>
                    </div>
                </div>
            </div>

            <sf:form id="orderForm" action="donate-checkout" modelAttribute="orderForm" enctype="multipart/form-data" method="POST">
                <div class="form-row">
                    <div class="form-group col-md-4 offset-md-1">
                        <label for="amount">Amount</label>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <span class="input-group-text">zł</span>
                            </div>
                            <select name="amount" id="amount" class="form-control">
                                <option value="500" selected>5</option>
                                <option value="2000">20</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="email">Email</label>
                        <input type="email" path="email" name="email" id="email" maxlength="255" value="${orderForm.email}" placeholder="Email" class="form-control" required="required">
                    </div>
                </div>
                <button id="submit" type="submit" class="btn btn-lg btn-block btn-success col-sm-4 mx-auto">Donate</button>
            </sf:form>

            <div class="py-3 mx-auto text-center">
                <p>* additional benefits for registered users </p>
                <p>Payments powered by <a href="https://payu.com" target="_blank"><img src="/resources/img/payu.png" height="30" alt="PayU"></a></p>
            </div>

        </div>
    </main>
    <!-- Footer -->
    <%@include file="/WEB-INF/incl/footer.app" %>
</wrapper>
</body>
</html>