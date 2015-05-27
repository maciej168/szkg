<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>


    <jsp:include page="/WEB-INF/pages/shared/resources.jsp"/>
</head>

<body>
<div class="container" ng-app="Category" ng-controller="CategoryController">
<!-- Modal Delete -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="confirmDeleteModalLabel"><spring:message code="category.list.modal.delete.title"/></h4>
            </div>
            <div class="modal-body">

               <p id="confirmDeleteModalMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="category.list.modal.delete.button.close"/></button>
                <button type="button" class="btn btn-danger" ng-click="deleteCategoryConfirm()" ><spring:message code="category.list.modal.delete.button.confirm"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Edit -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" ng-submit="submit()" name="CategoryDetailForm" novalidate>
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="detailModalLabel"><spring:message code="category.list.modal.detail.title"/></h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <label for="categoryName" class="col-sm-2 control-label">Nazwa</label>
                        <div class="col-sm-10">
                            <span style="color:red" id="categoryNameErrorMessage" hidden="true">Nazwa jest wymagana.</span>
                            <input type="text" class="form-control" id="categoryName" placeholder="Nazwa" >

                        </div>
                    </div>
            </div>
            <div class="modal-footer">
                <button id="detailModalEditButton" type="button" class="btn btn-info" ng-click="startEditing()"><spring:message code="category.list.modal.detail.button.edit"/></button>
                <button id="detailModalCancelButton" type="button" class="btn btn-warning" ng-click="cancelEditing()"><spring:message code="category.list.modal.detail.button.cancel"/></button>
                <button id="detailModalCloseButton" type="button" class="btn btn-prinary" data-dismiss="modal"><spring:message code="category.list.modal.detail.button.close"/></button>
                <input id="detailModalSaveButton" type="submit" class="btn btn-success" value='<spring:message code="category.list.modal.detail.button.confirm"/>'>
            </div>
            </form>
        </div>
    </div>
</div>


    <jsp:include page="/WEB-INF/pages/shared/header.jsp"/>
        <div class="panel panel-default" style="margin: 20px 0 0 0;">
            <div class="panel-heading">
                <span>
                    <h2>
                        <spring:message code="category.list.title"/>
                        <button class="btn btn-primary pull-right" title='<spring:message code="category.list.button.add.desc"/>' ng-click="editCategory('new')" ><spring:message code="category.list.button.add"/></button>
                    </h2>
                </span>


            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th class="col-sm-6"></th>
                        <th class="col-sm-3"></th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr ng-repeat="category in categoryList">
                            <td>{{category.name}}</td>
                            <td>
                                <span class="pull-right">
                                    <button class="btn btn-info" ng-click="editCategory(category.id)"><spring:message code="category.list.table.button.edit"/></button>
                                    <button class="btn btn-danger" ng-click="deleteCategory(category.id)" ><spring:message code="category.list.table.button.delete"/></button>
                                </span>


                            </td>
                        </tr>
                    </tbody>
                </table>
                <div align="center">
                    <!--
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
                    -->
                    <nav>
                        <ul class="pagination">
                            <li>
                                <a ng-click="prevPage()" aria-label="Poprzedna">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                            <li><a href="" >{{currPage}}</a></li>
                            <li>
                                <a ng-click="nextPage()" aria-label="Następna">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <div class="panel-footer">
            </div>
        </div>
    <jsp:include page="/WEB-INF/pages/shared/footer.jsp"/>
    </div>
<script>

    function validateEditModal()
    {
        var ret = true;
        if (document.getElementById("categoryName").value.length == 0)
        {
            $('#categoryNameErrorMessage').show();
            ret = false;
        }
        else
        {
            $('#categoryNameErrorMessage').hide();
        }

        return ret;
    }

    function clearEditModalValue()
    {
        $('#categoryName').val('');
    }
    function disableEditModal(dis)
    {
        $('#categoryName').attr('disabled', dis);
    }

    function changeEditModalButtons(start)
    {
        if (start == true)
        {
            $('#detailModalEditButton').show();
            $('#detailModalCloseButton').show();
            $('#detailModalCancelButton').hide();
            $('#detailModalSaveButton').hide();
            $('#categoryNameErrorMessage').hide();
        }
        else
        {
            $('#detailModalEditButton').hide();
            $('#detailModalCloseButton').hide();
            $('#detailModalCancelButton').show();
            $('#detailModalSaveButton').show();
        }
    }
    function clearEditModal() {
        clearEditModalValue();
        disableEditModal(true);
        changeEditModalButtons(true)
    }
    function enterEdit(enter)
    {
        disableEditModal(!enter);
        changeEditModalButtons(!enter);
    }
    var app = angular.module('Category', []);
    app.controller('CategoryController', function($scope, $http, $filter)
    {
        $scope.currPage = 1;
        $scope.categoriesPerPage = 10;

        $scope.deleteCategory = function(categoryId)
        {
            $scope.categoryToDelete = $filter('filter')($scope.categoryList, {id: categoryId}, true)[0];
            $('#confirmDeleteModalMessage').html('<spring:message code="category.list.modal.delete.message"/>' + ' ' + $scope.categoryToDelete.title);
            $('#confirmDeleteModal').modal('show');
        };

        $scope.deleteCategoryConfirm = function()
        {
            $http.get('categoryList/deleteCategory?categoryId=' + $scope.categoryToDelete.id).
                    success(function(data, status, headers, config) {
                        $http.get('categoryList/getCategoryList?pageNum=' + $scope.currPage).
                                success(function(data, status, headers, config) {
                                    $scope.categoryList = data;

                                }).
                                error(function(data, status, headers, config) {
                                    alert('<spring:message code="category.list.error.message"/>');
                                });
                    }).
                    error(function(data, status, headers, config) {
                        alert('<spring:message code="category.list.error.message"/>');
                    });
            $scope.categoryToDelete = NaN;
            $('#confirmDeleteModalMessage').html('');
            $('#confirmDeleteModal').modal('hide');
        };

        $scope.editCategory = function(categoryId)
        {
            clearEditModal();
            if (categoryId == 'new')
            {
                $scope.editingCategoryId = categoryId;
                enterEdit(true);
            }
            else
            {
                $scope.setDetailParameter(categoryId);
            }
            $('#detailModal').modal('show');
        };

        $scope.startEditing = function()
        {
            enterEdit(true);
        };

        $scope.cancelEditing = function()
        {
            clearEditModal();
            $scope.setDetailParameter($scope.editingCategoryId);
            enterEdit(false);
        };

        $scope.setDetailParameter = function(categoryId)
        {
            if (categoryId != 'new') {
                $http.get('categoryList/getCategoryDetail?categoryId=' + categoryId).
                        success(function (data, status, headers, config)
                        {
                            $scope.setDetailParameterValue(data);
                        }).
                        error(function (data, status, headers, config) {
                            alert('<spring:message code="category.list.error.message"/>');
                        });
            }
        };

        $scope.setDetailParameterValue = function(data)
        {
            enterEdit(true);
            $scope.editingCategoryId = data.id;
            $('#categoryName').val(data.name);
            enterEdit(false);
        };

        $scope.submit = function()
        {
            if (validateEditModal())
            {
                $scope.updateCategory();
            }
        };

        $scope.updateCategory = function() {
            var fd = new FormData();

            if ($scope.editingCategoryId == 'new')
            {
                fd.append("categoryId", -1);
            }
            else {
                fd.append("categoryId", $scope.editingCategoryId)
            }
            fd.append("categoryName", document.getElementById("categoryName").value);

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "categoryList/saveCategory", false);
            xhr.send(fd);
            var jsonResponse = JSON.parse(xhr.responseText);
            $scope.setDetailParameterValue(jsonResponse);
        };


        $scope.reloadCategory = function()
        {
            $http.get('categoryList/getCategoryList?pageNum=' + $scope.currPage).
                    success(function(data, status, headers, config) {
                        $scope.categoryList = data;
                    }).
                    error(function(data, status, headers, config) {
                        alert('<spring:message code="category.list.error.message"/>');
                    });
        };

        $scope.prevPage = function (input)
        {
            if ( $scope.currPage > 1)
            {
                $scope.currPage =  $scope.currPage - 1;
                $scope.reloadCategory();
            }
        };

        $scope.nextPage = function (input)
        {
            if ( $scope.categoryList.length == $scope.categoriesPerPage)
            {
                $scope.currPage =  $scope.currPage + 1;
                $scope.reloadCategory();
            }
        };

        $scope.reloadCategory();
        $http.get('categoryList/getCategoryList').
                success(function(data, status, headers, config) {
                    $scope.categoryList = data;
                }).
                error(function(data, status, headers, config) {
                    alert('<spring:message code="category.list.error.message"/>');
                });
        clearEditModal();
    });
</script>
</body>
</html>





