<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<!-- Head -->
<head>
    <%@include file="/WEB-INF/incl/head.app" %>
<title><s:message code="title.mainPage"/></title>
</head>
<body>
<wrapper class="d-flex flex-column bg-anim">
<!-- Navigation -->
    <%@include file="/WEB-INF/incl/nav.app" %>
<!-- Page Content -->
    <div class="jumbotron">
        <div class="container text-white">
            <h1 class="display-3"><i class="fas fa-dice"></i> Senet</h1>
            <p>Explore and share your favourite Board Games</p>
            <div class="input-group">
                <input class="form-control transparent-input text-white" type="text" id="searchValue" placeholder="Enter title" aria-label="Search">
                <div class="input-group-append">
                    <button class="btn btn-outline-light my-2 my-sm-0" type="button" id="searchButton" onclick="searchBoardGame();"><i class="fas fa-search"></i></button>
                </div>
            </div>
        </div>
    </div>

<main class="container-fluid py-3 flex-fill">
<div class="container">

    <sec:authorize access="hasRole('ANONYMOUS')">
    <div class="container">
        <div class="card-deck">
            <c:forEach var="boardGame" items="${boardGameList}">
            <div class="card border-primary shadow-sm">
                <a href="${pageContext.request.contextPath}/boardgame/${boardGame.id}"><img class="card-img-top" src="${boardGame.imagePath}" alt="${boardGame.title}"></a>
                <div class="card-body">
                    <h5 class="card-title"><a href="${pageContext.request.contextPath}/boardgame/${boardGame.id}" class="card-link">${boardGame.title} (${boardGame.releaseYear})</a></h5>
                    <p class="card-text"><i class="fas fa-users"></i> Players: ${boardGame.players}</p>
                    <p class="card-text"><i class="far fa-clock"></i> Playing time: ${boardGame.playingTime} min</p>
                    <p class="card-text"><i class="fas fa-child"></i> Age: ${boardGame.minAge}+</p>
                </div>
                <div class="card-footer" align="right">
                    <form action="${pageContext.request.contextPath}/addfav/${boardGame.id}" method="POST" style="display: inline-block;">
                        <button type="submit" class="btn btn-sm p-0 text-danger"><i class="far fa-heart"></i></button>
                    </form>
                    <button type="button" class="btn btn-sm p-0 text-primary ml-3 js-copy" data-toggle="tooltip" title="Copy the link to ${boardGame.title}" data-copy="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/boardgame/${boardGame.id}"><i class="fas fa-share-alt"></i></button>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <div class="container">
            <h3 class="text-white">Favourites</h3><div class="border-top my-3"></div>
            <div class="card-deck">
                <c:forEach var="boardGame" items="${boardGameLikedList}">
                    <div class="card border-primary shadow-sm">
                        <a href="${pageContext.request.contextPath}/boardgame/${boardGame.id}"><img class="card-img-top" src="${boardGame.imagePath}" alt="${boardGame.title}"></a>
                        <div class="card-body">
                            <h5 class="card-title"><a href="${pageContext.request.contextPath}/boardgame/${boardGame.id}" class="card-link">${boardGame.title} (${boardGame.releaseYear})</a></h5>
                            <p class="card-text"><i class="fas fa-users"></i> Players: ${boardGame.players}</p>
                            <p class="card-text"><i class="far fa-clock"></i> Playing time: ${boardGame.playingTime} min</p>
                            <p class="card-text"><i class="fas fa-child"></i> Age: ${boardGame.minAge}+</p>
                        </div>
                        <div class="card-footer" align="right">
                            <form action="${pageContext.request.contextPath}/addfav/${boardGame.id}" method="POST" style="display: inline-block;">
                                <button type="submit" class="btn btn-sm p-0 text-danger"><i class="fas fa-heart"></i></button>
                            </form>
                            <button type="button" class="btn btn-sm p-0 text-primary ml-3 js-copy" data-toggle="tooltip" title="Copy the link to ${boardGame.title}" data-copy="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/boardgame/${boardGame.id}"><i class="fas fa-share-alt"></i></button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="container">
            <h3 class="text-white">More board games</h3><div class="border-top my-3"></div>
            <div class="card-deck">
                <c:forEach var="boardGame" items="${boardGameOthersList}">
                    <div class="card border-primary shadow-sm">
                        <a href="${pageContext.request.contextPath}/boardgame/${boardGame.id}"><img class="card-img-top" src="${boardGame.imagePath}" alt="${boardGame.title}"></a>
                        <div class="card-body">
                            <h5 class="card-title"><a href="${pageContext.request.contextPath}/boardgame/${boardGame.id}" class="card-link">${boardGame.title} (${boardGame.releaseYear})</a></h5>
                            <p class="card-text"><i class="fas fa-users"></i> Players: ${boardGame.players}</p>
                            <p class="card-text"><i class="far fa-clock"></i> Playing time: ${boardGame.playingTime} min</p>
                            <p class="card-text"><i class="fas fa-child"></i> Age: ${boardGame.minAge}+</p>
                        </div>
                        <div class="card-footer" align="right">
                            <form action="${pageContext.request.contextPath}/addfav/${boardGame.id}" method="POST" style="display: inline-block;">
                                <button type="submit" class="btn btn-sm p-0 text-danger"><i class="far fa-heart"></i></button>
                            </form>
                            <button type="button" class="btn btn-sm p-0 text-primary ml-3 js-copy" data-toggle="tooltip" title="Copy the link to ${boardGame.title}" data-copy="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/boardgame/${boardGame.id}"><i class="fas fa-share-alt"></i></button>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </sec:authorize>

</div>
</main>
<!-- Footer -->
    <%@include file="/WEB-INF/incl/footer.app" %>
    <script src="/resources/scripts/clipboardcopy.js"></script>
    <script>
        document.getElementById('searchValue').addEventListener('keyup', function(event) {
            if (event.keyCode === 13) {
                event.preventDefault();
                document.getElementById('searchButton').click();
            }
        });

        function searchBoardGame() {
            var searchInput = document.getElementById('searchValue').value;
            var url = '${pageContext.request.contextPath}/boardgame';
            if (searchInput === "") {window.location.href='/';return;}
            window.location.href=url+'/search/'+searchInput;
        }
    </script>
</wrapper>
</body>
</html>