<%@page import="MODEL.Navio"%>
<%@page import="MODELO.DAO.NavioDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>

<!DOCTYPE html>

<html lang="en-US">
    <head>


        <meta charset="utf-8">
        <title>PCS-SGBD  - Marítima</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">   
        <!--[if lt IE 9]><script src="//html5shim.googlecode.com/svn/trunk/html5.js"></script><![endif]-->
        <!-- MAIN STYLE SECTION-->
        <link href="assets/plugins/isotope/isotope.css" rel="stylesheet" media="screen" />
        <link href="assets/plugins/fancybox/jquery.fancybox.css" rel="stylesheet" />
        <link href="assets/plugins/IconHoverEffects-master/css/component.css" rel="stylesheet" />
        <link href="assets/plugins/bootstrap/bootstrap.css" rel="stylesheet" />
        <link href="assets/css/about-achivements.css" rel="stylesheet" />
        <link id="mainStyle" href="assets/css/style.css" rel="stylesheet" />
        <link href='http://fonts.googleapis.com/css?family=Varela+Round' rel='stylesheet' type='text/css'>
    </head>
    <body>

        <header>
            <!-- HEADER SECTION-->
            <div class="navbar navbar-fixed-top" role="navigation">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                            <span class="fa fa-bars mobile-bars" style=""></span>
                        </button>
                        <a class="navbar-brand" href="index.html" >
                            <!--  <img src="assets/img/logo.jpg" alt="" />  logo here-->
                        </a>
                    </div>
                    <div class="navbar-collapse collapse" data-scrollreveal="enter from the right 50px">
                        <ul class="nav navbar-nav">
                            <li class=""><a href="index.jsp">Home</a></li><!-- menu links-->
                            <li><a href="principal.jsp">Navios</a></li>  
                            <li><a href="Cargas.jsp">Cargas</a></li>
                            <li><a href="#section-services">Rotas</a></li>
                            <li><a href="#section-contact">Contact</a></li>
                        </ul>
                    </div>

                </div>
            </div>
            <!-- END HEADER SECTION-->
        </header>

        <div id="wrapper">
            <br><br><br><br>
            <h1>Registrar navio</h1>

            <form action="NovoServlet?metodo=cadastrarnavio" method="POST">
                <div class="col-2">
                    <label>
                        NOME
                        <input placeholder="" id="company" name="nome" tabindex="2" >
                    </label>
                </div>


                <div class="col-3">
                    <label>
                        CAPACIDADE MÁXIMA
                        <input placeholder="" id="email" name="Cap_Maxima" tabindex="4">
                    </label>
                </div>
                <div class="col-3">
                    <label>
                        STATUS
                        <select tabindex="5" name="status">
                            <option>Embarcada</option>
                            <option>Ocioso</option>
                            <option>Manutenção</option>
                            <option>Desativado</option>
                        </select>
                    </label>
                </div>

                <div class="col-4">

                    <div class="col-submit">
                        <button class="submitbtn">Submeter</button>
                    </div>

            </form>
        </div>
        <script type="text/javascript">
            var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

            elems.forEach(function (html) {
                var switchery = new Switchery(html);
            });
        </script>
        

    </body>
</html>       