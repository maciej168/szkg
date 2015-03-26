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

</body>
</html>