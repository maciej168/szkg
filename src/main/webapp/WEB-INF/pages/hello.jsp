<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
    <script src="/angular/angular.min.js"></script>
</head>

<body>
<h1>${message}</h1>
<div ng-app="">
    <p>Name: <input type="text" ng-model="name"></p>
    <p>{{name}}</p>
</div>

<div align="center">
    <h1>Games List</h1>
    <table border="1">
        <th>No</th>
        <th>Name</th>
        <th>Description</th>

        <c:forEach var="game" items="${gameList}" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${user.name}</td>
                <td>${user.description}</td>

            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>