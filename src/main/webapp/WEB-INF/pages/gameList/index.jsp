<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>

<head>


    <jsp:include page="/WEB-INF/pages/shared/resources.jsp"/>
</head>

<body>
<div class="container" ng-app="GameList" ng-controller="GameListController">
<!-- Modal Delete -->
<div class="modal fade" id="confirmDeleteModal" tabindex="-1" role="dialog" aria-labelledby="confirmDeleteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="confirmDeleteModalLabel"><spring:message code="game.list.modal.delete.title"/></h4>
            </div>
            <div class="modal-body">

               <p id="confirmDeleteModalMessage"></p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="game.list.modal.delete.button.close"/></button>
                <button type="button" class="btn btn-danger" ng-click="deleteGameConfirm()" ><spring:message code="game.list.modal.delete.button.confirm"/></button>
            </div>
        </div>
    </div>
</div>

<!-- Modal Edit -->
<div class="modal fade" id="detailModal" tabindex="-1" role="dialog" aria-labelledby="detailModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <form class="form-horizontal" ng-submit="submit()" name="GameDetailForm" novalidate>
            <div class="modal-header ">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="detailModalLabel"><spring:message code="game.list.modal.detail.title"/></h4>
            </div>
            <div class="modal-body">
                    <div class="form-group">
                        <label for="gameTitle" class="col-sm-2 control-label">Tytuł</label>
                        <div class="col-sm-10">
                            <span style="color:red" id="gameTitleErrorMessage" hidden="true">Tytuł jest wymagany.</span>
                            <input type="text" class="form-control" id="gameTitle" placeholder="Tyuł" >

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="gameCategory" class="col-sm-2 control-label">Kategorie</label>
                        <div class="col-sm-10">
                            <span style="color:red" id="gameCategoryErrorMessage" hidden="true">Wybierz przynajmniej jedną kategorię.<br /></span>
                            <select class="selectpicker show-menu-arrow" data-live-search="true" id="gameCategory" name="SemestrID" multiple data-size="5">
                                <option ng-repeat="cat in categoryList" value="{{cat.id}}">{{cat.name}}</option>
                            </select>

                        </div>
                    </div>
                    <div class="form-group">
                        <label for="gameDescription" class="col-sm-2 control-label">Opis</label>
                        <div class="col-sm-10">
                            <textarea class="form-control" rows="5" id="gameDescription"></textarea>

                        </div>
                    </div>

                    <div class="form-group">
                        <label for="gameImageFile" class="col-sm-2 control-label">Obraz</label>
                        <div class="col-sm-10">
                            <input type="file" class="filestyle" id="gameImageFile" data-buttonText="Wybierz plik" data-disabled="true">
                            <div align="center"><img src="#" id="gameImage" style="margin: 10px" class="img-thumbnail" width="304" height="236" alt="Brak"></div>
                        </div>
                    </div>

            </div>
            <div class="modal-footer">
                <button id="detailModalEditButton" type="button" class="btn btn-info" ng-click="startEditing()"><spring:message code="game.list.modal.detail.button.edit"/></button>
                <button id="detailModalCancelButton" type="button" class="btn btn-warning" ng-click="cancelEditing()"><spring:message code="game.list.modal.detail.button.cancel"/></button>
                <button id="detailModalCloseButton" type="button" class="btn btn-prinary" data-dismiss="modal"><spring:message code="game.list.modal.detail.button.close"/></button>
                <input id="detailModalSaveButton" type="submit" class="btn btn-success" value='<spring:message code="game.list.modal.detail.button.confirm"/>'>
            </div>
            </form>
        </div>
    </div>
</div>


    <jsp:include page="/WEB-INF/pages/shared/header.jsp"/>
        <div class="panel panel-default" style="margin: 20px 0px 0px 0px;">
            <div class="panel-heading">
                <span>
                    <h2>
                        <spring:message code="game.list.title"/>
                        <button class="btn btn-primary pull-right" title='<spring:message code="game.list.button.add.desc"/>' ng-click="editGame('new')" ><spring:message code="game.list.button.add"/></button>
                    </h2>
                </span>


            </div>
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
                        <tr ng-repeat="game in gameList">
                            <td><div align="center"><img ng-src="gameList/getGameImge?gameId={{game.id}}" width="184" height="69" alt="Brak"/></div></td>
                            <td>{{game.title}}</td>
                            <td>
                                <span class="pull-right">
                                    <button class="btn btn-info" ng-click="editGame(game.id)"><spring:message code="game.list.table.button.edit"/></button>
                                    <button class="btn btn-danger" ng-click="deleteGame(game.id)" ><spring:message code="game.list.table.button.delete"/></button>
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
        if (document.getElementById("gameTitle").value.length == 0)
        {
            $('#gameTitleErrorMessage').show();
            ret = false;
        }
        else
        {
            $('#gameTitleErrorMessage').hide();
        }
        var values = $('#gameCategory').val();
        if ($('#gameCategory').val() == null)
        {
            $('#gameCategoryErrorMessage').show();
            ret = false;
        }
        else
        {
            $('#gameCategoryErrorMessage').hide();
        }

        return ret;
    }

    function clearEditModalValue()
    {
        $('#gameTitle').val('');
        $('#gameDescription').val('');
        $('#gameCategory').selectpicker('val', '');
        $('#gameImageFile').filestyle('clear');
        $('#gameImage').hide();
        $('#gameCategory').selectpicker('refresh');
    };

    function disableEditModal(dis)
    {
        $('#gameTitle').attr('disabled', dis);
        $('#gameDescription').attr('disabled', dis);
        $('#gameCategory').attr('disabled', dis);
        $('#gameImageFile').filestyle('disabled', dis);
        $('#gameCategory').selectpicker('refresh');
    }

    function changeEditModalButtons(start)
    {
        if (start == true)
        {
            $('#detailModalEditButton').show();
            $('#detailModalCloseButton').show();
            $('#detailModalCancelButton').hide();
            $('#detailModalSaveButton').hide();
            $('#gameTitleErrorMessage').hide();
            $('#gameCategoryErrorMessage').hide();
        }
        else
        {
            $('#detailModalEditButton').hide();
            $('#detailModalCloseButton').hide();
            $('#detailModalCancelButton').show();
            $('#detailModalSaveButton').show();
        }
    };

    function clearEditModal() {
        clearEditModalValue();
        disableEditModal(true);
        changeEditModalButtons(true)
    };

    function enterEdit(enter)
    {
        disableEditModal(!enter);
        changeEditModalButtons(!enter);
    };

    var app = angular.module('GameList', []);
    app.controller('GameListController', function($scope, $http, $filter)
    {
        $scope.currPage = 1;
        $scope.gamesPerPage = 10;

        $scope.deleteGame = function(gameId)
        {
            $scope.gameToDelete = $filter('filter')($scope.gameList, {id: gameId}, true)[0];
            $('#confirmDeleteModalMessage').html('<spring:message code="game.list.modal.delete.message"/>' + ' ' + $scope.gameToDelete.title);
            $('#confirmDeleteModal').modal('show');
        };

        $scope.deleteGameConfirm = function()
        {
            $http.get('gameList/deleteGame?gameId=' + $scope.gameToDelete.id).
                    success(function(data, status, headers, config) {
                        $http.get('gameList/getGameList?pageNum=' + $scope.currPage).
                                success(function(data, status, headers, config) {
                                    $scope.gameList = data;

                                }).
                                error(function(data, status, headers, config) {
                                    alert('<spring:message code="game.list.error.message"/>');
                                });
                    }).
                    error(function(data, status, headers, config) {
                        alert('<spring:message code="game.list.error.message"/>');
                    });
            $scope.gameToDelete = NaN;
            $('#confirmDeleteModalMessage').html('');
            $('#confirmDeleteModal').modal('hide');
        };

        $scope.editGame = function(gameId)
        {
            clearEditModal();
            if (gameId == 'new')
            {
                $scope.editingGameId = gameId;
                enterEdit(true);
            }
            else
            {
                $scope.setDetailParameter(gameId);
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
            $scope.setDetailParameter($scope.editingGameId);
            enterEdit(false);
        };

        $scope.setDetailParameter = function(gameId)
        {
            if (gameId != 'new') {
                $http.get('gameList/getGameDetail?gameId=' + gameId).
                        success(function (data, status, headers, config)
                        {
                            $scope.setDetailParameterValue(data);
                        }).
                        error(function (data, status, headers, config) {
                            alert('<spring:message code="game.list.error.message"/>');
                        });
            }
        };

        $scope.setDetailParameterValue = function(data)
        {
            enterEdit(true);
            $scope.editingGameId = data.id;
            $('#gameTitle').val(data.title);
            $('#gameDescription').val(data.description);
            $('#gameCategory').selectpicker('val', data.category);
            $('#gameImage').attr('src', 'gameList/getGameImge?gameId=' + data.id);
            $('#gameImage').show();
            enterEdit(false);
        }


        $scope.submit = function()
        {
            if (validateEditModal())
            {
                $scope.uploadFile();
            }
        };

        $scope.uploadFile = function() {
            var fd = new FormData();

            fd.append("file", $scope.fileToUpload)
            if ($scope.editingGameId == 'new')
            {
                fd.append("gameId", -1);
            }
            else {
                fd.append("gameId", $scope.editingGameId)
            }
            fd.append("gameTitle", document.getElementById("gameTitle").value);
            fd.append("gameCategory", $('#gameCategory').val());
            fd.append("gameDescription", document.getElementById("gameDescription").value);

            var xhr = new XMLHttpRequest();
            xhr.open("POST", "gameList/saveGame", false);
            xhr.send(fd);
            var jsonResponse = JSON.parse(xhr.responseText);
            $scope.setDetailParameterValue(jsonResponse);
        };

        $scope.readURL = function (input)
        {
            if (input.files && input.files[0])
            {
                $scope.fileToUpload = input.files[0];
                var reader = new FileReader();

                reader.onload = function (e) {
                    $('#gameImage').attr('src', e.target.result);
                    $('#gameImage').show();
                }
                reader.readAsDataURL(input.files[0]);
            }
        };

        $scope.reloadGameList = function()
        {
            $http.get('gameList/getGameList?pageNum=' + $scope.currPage).
                    success(function(data, status, headers, config) {
                        $scope.gameList = data;
                    }).
                    error(function(data, status, headers, config) {
                        alert('<spring:message code="game.list.error.message"/>');
                    });
        }

        $scope.prevPage = function (input)
        {
            if ( $scope.currPage > 1)
            {
                $scope.currPage =  $scope.currPage - 1;
                $scope.reloadGameList();
            }
        };

        $scope.nextPage = function (input)
        {
            if ( $scope.gameList.length == $scope.gamesPerPage)
            {
                $scope.currPage =  $scope.currPage + 1;
                $scope.reloadGameList();
            }
        };

        $scope.reloadGameList();
        $http.get('gameList/getCategoryList').
                success(function(data, status, headers, config) {
                    $scope.categoryList = data;
                }).
                error(function(data, status, headers, config) {
                    alert('<spring:message code="game.list.error.message"/>');
                });
        $('#gameImageFile').change(function()
        {
            $scope.readURL(this);
        });
        $('#gameCategory').selectpicker();
        clearEditModal();
    });
</script>
</body>
</html>





