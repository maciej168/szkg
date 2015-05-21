<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <jsp:include page="/WEB-INF/pages/shared/resources.jsp"/>
</head>

<body>
<div class="container" ng-app="">
    <jsp:include page="/WEB-INF/pages/shared/header.jsp"/>


    <form action="/Admin" method="post" role="form">
        <div class="panel panel-default" style="margin: 20px 0px 0px 0px;">
            <div class="panel-heading"><h4>Lista Gier</h4></div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th class="col-sm-3"></th>
                        <th class="col-sm-6"></th>
                        <th class="col-sm-3"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="game" items="${gameList}" varStatus="status">
                        <tr>
                            <td><div align="center"><img ng-src="gameList/imageDisplay?id=${game.id}" width="184" height="69"/></div></td>
                            <td>${game.title}</td>
                            <td>
                                <button type="submit" class="btn btn-warning" title="Edytyj" name="EGradeID"
                                        value=@grade.GradeID>Edytuj
                                </button>
                                <button type="submit" class="btn btn-danger" title="Usun" name="DGradeID"
                                        value=@grade.GradeID>Usuń
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <div align="center">
                    <ul class="pagination">
                    <c:forEach begin="1" end="${maxPage}" varStatus="loop">
                        <c:choose>
                            <c:when test="${currentPage == loop.count}">
                                <li class="disabled"><a href="#"><c:out value="${loop.count}"/></a></li>
                            </c:when>

                            <c:otherwise>
                                <li><a href="?requestPage=${loop.count}"><c:out value="${loop.count}"/></a></li>

                            </c:otherwise>
                        </c:choose>

                    </c:forEach>




                    </ul>
                </div>
            </div>
            <div class="panel-footer">
            </div>
        </div>
    </form>
    <jsp:include page="/WEB-INF/pages/shared/footer.jsp"/>
    </div>
</body>
</html>





