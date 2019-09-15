<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<!-- Head -->
<head>
    <%@include file="/WEB-INF/incl/head.app" %>
<title><s:message code="title.boardGames"/></title>
</head>
<body>
<wrapper class="d-flex flex-column">
<!-- Navigation -->
    <%@include file="/WEB-INF/incl/nav.app" %>
    <%@include file="/WEB-INF/incl/anav.app" %>
<!-- Page Content -->
<main class="col-md-10 ml-sm-auto py-3 flex-fill">
<div class="container">

    <div class="d-flex align-items-center p-3 my-3 text-white-50 bg-primary rounded shadow-sm">
        <h5 class="mb-0 text-white col-2">Board games panel</h5>
        <div class="input-group">
            <input class="form-control" type="text" id="searchValue" placeholder="Search..." aria-label="Search">
            <button class="btn btn-dark my-2 my-sm-0" type="button" id="searchButton" onclick="searchBoardGame();">Search</button>
        </div>
    </div>

    <div class="card border-primary my-3">
        <h4 class="card-header">Board games list</h4>
        <div class="card-body" align="center">

            <table class="table table-hover table-sm table-striped">
                <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Title (Year)</th>
                    <th scope="col">Designer</th>
                    <th scope="col">Players</th>
                    <th scope="col">Playing time</th>
                    <th scope="col">Age</th>
                    <th scope="col">Choose</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="boardGame" items="${bgList}">
                <tr>
                    <td>${boardGame.id}</td>
                    <td>${boardGame.title} (${boardGame.releaseYear})</td>
                    <td>${boardGame.designer}</td>
                    <td>${boardGame.players}</td>
                    <td>${boardGame.playingTime} min</td>
                    <td>${boardGame.minAge}+</td>
                    <td><a type="button" class="btn btn-sm btn-outline-info" href="${pageContext.request.contextPath}/admin/boardgames/edit/${boardGame.id}">Edit</a></td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
            <nav aria-label="Pages">
                <ul class="pagination justify-content-center">
                    <c:if test="${currentPage > 1}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/${path}/${currentPage - 1}"><s:message code="button.previous"/></a></li>
                    </c:if>
                    <c:forEach begin="1" end="${totalPages}" var="i">
                        <c:choose>
                            <c:when test="${currentPage eq i}">
                                <li class="page-item active"><a class="page-link">${i} <span class="sr-only">(current)</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/${path}/${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                    <c:if test="${currentPage < totalPages}">
                        <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/${path}/${currentPage + 1}"><s:message code="button.next"/></a></li>
                    </c:if>
                </ul>
            </nav>
        </div>
    </div>

</div>
</main>
<!-- Footer -->
    <%@include file="/WEB-INF/incl/footer.app" %>
    <script>
        document.getElementById('searchValue').addEventListener('keyup', function(event) {
                if (event.keyCode === 13) {
                    event.preventDefault();
                    document.getElementById('searchButton').click();
                }
            });

        function searchBoardGame() {
            var searchInput = document.getElementById('searchValue').value;
            var url = '${pageContext.request.contextPath}/admin/boardgames';
            if (searchInput === "") {window.location.href=url;return;}
            window.location.href=url+'/search/'+searchInput;
        }
    </script>
</wrapper>
</body>
</html>