<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<!-- Head -->
<head>
    <%@include file="/WEB-INF/incl/head.app" %>
    <title><s:message code="title.ws"/></title>
</head>
<body>
<wrapper class="d-flex flex-column">
    <!-- Navigation -->
    <%@include file="/WEB-INF/incl/nav.app" %>
    <!-- Page Content -->
    <main class="container-fluid py-3 flex-fill">

        <div class="jumbotron py-3 pt-md-3 mx-auto text-center text-white">
            <h1 class="display-4">Senet Web Services <i class="fas fa-network-wired"></i></h1>
            <p>Integrate Senet functionality into the application you are developing</p>
        </div>

        <div class="card">
            <div class="card-header"><h4>Board Games</h4></div>
            <div class="card-body">
                <p class="card-text">Go to service description: <a href="/ws/boardgame.wsdl">WSDL Specification</a></p>
                <button class="btn btn-outline-primary" t6947
                        ype="button" data-toggle="collapse" data-target="#bgExample" aria-expanded="false" aria-controls="bgExample">Example SOAP message</button>
                <div class="collapse" id="bgExample">
                    <div class="card my-3 card-body">

<pre><code>&lt;?xml version="1.0" encoding="UTF-8"?&gt;
&lt;soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:tns="https://senetbg.herokuapp.com/soap/schema"&gt;
   &lt;soap:Body&gt;
      &lt;tns:getBoardGameByIdRequest&gt;
         &lt;id&gt;{id}&lt;/id&gt;
      &lt;/tns:getBoardGameByIdRequest&gt;
   &lt;/soap:Body&gt;
&lt;/soap:Envelope&gt;</code></pre>

                    </div>
                </div>
            </div>
        </div>

        </div>
    </main>
    <!-- Footer -->
    <%@include file="/WEB-INF/incl/footer.app" %>
</wrapper>
</body>
</html>