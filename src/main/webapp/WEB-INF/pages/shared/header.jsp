<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<style>
    #custom-bootstrap-menu.navbar-default .navbar-brand {
        color: rgba(0, 0, 0, 1);
    }
    #custom-bootstrap-menu.navbar-default {
        font-size: 18px;
        background-color: rgba(72, 72, 163, 1);
        background: -webkit-linear-gradient(top, rgba(164, 242, 239, 1) 0%, rgba(72, 72, 163, 1) 100%);
        background: linear-gradient(to bottom, rgba(164, 242, 239, 1) 0%, rgba(72, 72, 163, 1) 100%);
        border-width: 0px;
        border-radius: 10px;
        margin: 5px;
    }
    #custom-bootstrap-menu.navbar-default .navbar-nav>li>a {
        color: rgba(0, 0, 0, 1);
        background-color: rgba(255, 255, 255, 0);
    }
    #custom-bootstrap-menu.navbar-default .navbar-nav>li>a:hover,
    #custom-bootstrap-menu.navbar-default .navbar-nav>li>a:focus {
        color: rgba(0, 0, 0, 1);
        background-color: rgba(164, 242, 239, 1);
        background: -webkit-linear-gradient(top, rgba(72, 72, 163, 1) 0%, rgba(164, 242, 239, 1) 100%);
        background: linear-gradient(to bottom, rgba(72, 72, 163, 1) 0%, rgba(164, 242, 239, 1) 100%);
    }
    #custom-bootstrap-menu.navbar-default .navbar-nav>.active>a,
    #custom-bootstrap-menu.navbar-default .navbar-nav>.active>a:hover,
    #custom-bootstrap-menu.navbar-default .navbar-nav>.active>a:focus {
        color: rgba(77, 69, 69, 1);
        background-color: rgba(172, 199, 224, 1);
        background: -webkit-linear-gradient(top, rgba(76, 133, 255, 1) 0%, rgba(172, 199, 224, 1) 100%);
        background: linear-gradient(to bottom, rgba(76, 133, 255, 1) 0%, rgba(172, 199, 224, 1) 100%);
    }
    #custom-bootstrap-menu.navbar-default .navbar-toggle {
        border-color: #acc7e0;
    }
    #custom-bootstrap-menu.navbar-default .navbar-toggle:hover,
    #custom-bootstrap-menu.navbar-default .navbar-toggle:focus {
        background-color: #acc7e0;
    }
    #custom-bootstrap-menu.navbar-default .navbar-toggle .icon-bar {
        background-color: #acc7e0;
    }
    #custom-bootstrap-menu.navbar-default .navbar-toggle:hover .icon-bar,
    #custom-bootstrap-menu.navbar-default .navbar-toggle:focus .icon-bar {
        background-color: #4848a3;
    }
</style>

<div id="custom-bootstrap-menu" class="navbar navbar-default " role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-menubuilder">
                <span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span
                    class="icon-bar"></span><span class="icon-bar"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse navbar-menubuilder">
            <ul class="nav navbar-nav navbar-left">

                <li><a href="/"><spring:message code="header.home"/></a>
                </li>
                <li><a href="/gameList"><spring:message code="header.game.list"/></a>
                </li>
                <li><a href="/wishList"><spring:message code="header.wish.list"/></a>
                </li>
                <li><a href="/categoryList"><spring:message code="header.category"/></a>
                </li>
            </ul>
        </div>
    </div>
</div>
