<%--
  Created by IntelliJ IDEA.
  User: Komatta
  Date: 2015-05-20
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
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
                <li><a href="/">Home</a>
                </li>
                <li><a href="/gameList">Lista Gier</a>
                </li>
                <li><a href="/wishList">Lista Życzeń</a>
                </li>
                <li><a href="/category">Kategorie</a>
                </li>
            </ul>
        </div>
    </div>
</div>
