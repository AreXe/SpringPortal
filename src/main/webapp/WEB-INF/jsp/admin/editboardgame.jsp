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
<title><s:message code="title.admin"/></title>
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
        <div class="lh-100">
            <h5 class="mb-0 text-white lh-100">Board games panel</h5>
        </div>
    </div>

    <div class="card border-primary my-3">
        <h4 class="card-header">Edit board game: ${boardGame.title} (${boardGame.releaseYear})</h4>
        <div class="card-body" align="center">

            <sf:form id="boardGameForm" action="${pageContext.request.contextPath}/admin/updateboardgame/${boardGame.id}" modelAttribute="boardGame" enctype="multipart/form-data" method="PUT">
                <sf:hidden path="id" value="${boardGame.id}"/>
                <div class="form-group row">
                    <label for="title" class="col-sm-2 col-form-label">Title</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" maxlength="255" name="title" value="${boardGame.title}" id="title" placeholder="Title" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="designer" class="col-sm-2 col-form-label">Designer</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" maxlength="255" name="designer" value="${boardGame.designer}" id="designer" placeholder="Designer" required>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="releaseYear" class="col-sm-2 col-form-label">Release year</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" pattern="[0-9]{1,4}" maxlength="4" name="releaseYear" value="${boardGame.releaseYear}" id="releaseYear" placeholder="Release year" required>
                    </div>
                </div>

                <div class="form-group row">
                    <label for="players" class="col-sm-2 col-form-label">Players</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="players" value="${boardGame.players}" id="players" placeholder="Players">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="playingTime" class="col-sm-2 col-form-label">Playing time</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="playingTime" value="${boardGame.playingTime}" id="playingTime" placeholder="Playing time">
                    </div>
                </div>
                <div class="form-group row">
                    <label for="minAge" class="col-sm-2 col-form-label">Min age</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" pattern="[0-9]{1,2}" maxlength="2" name="minAge" value="${boardGame.minAge}" id="minAge" placeholder="Min age">
                    </div>
                </div>

                <div class="form-group row">
                    <label for="description" class="col-sm-2 col-form-label">Description</label>
                    <div class="col-sm-10">
                        <textarea rows="4" class="form-control" name="description" id="description" placeholder="Description...">${boardGame.description}</textarea>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="imagePath" class="col-sm-2 col-form-label">Image path</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" name="imagePath" id="imagePath" value="${boardGame.imagePath}" placeholder="Image URL">
                    </div>
                </div>

                <div class="text-center">
                    <button class="btn btn-primary" type="submit">Save</button>
                </div>
            </sf:form>

                <div class="text-center my-2">
                    <button class="btn btn-outline-primary" type="button" onclick="history.back();"><s:message code="button.return"/></button>
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