<%@page import="MODELO.DAO.NavioDAO"%>
<%@page import="MODEL.Navio"%>
<%@page import="MODELO.DAO.CargaDAO"%>
<%@page import="MODEL.Carga"%>

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
                            <li><a href="#section-works">Cargas</a></li>
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
            <h1>Registrar Carga</h1>

            <form action="NovoServlet?metodo=cadastrarcarga" method="POST">  
                <div class="col-2">
                    <label>
                        <% 
                            out.println("<div class='col-3'");  
                            out.println("<label>");                            
                            out.println("NAVIO");
                            
                            out.println("<select tabindex='1' name='navio' id='navios'>");
                            NavioDAO acesso = new NavioDAO();
                            List<Navio> lista = acesso.getListaNavios();
                            
                            for (Navio navios : lista) {
                                out.println("<option value=\"" + navios.getId_Navio() + "\" >" + navios.getNome() + "</option>");
                            }
                            
                            out.println("</select>"); 
                            out.println("</label>"); 
                            out.println("</div>"); 
                %>
                    </label>
                </div>
                <div class="col-2">
                    <label>
                        Origem
                        <input placeholder="" id="company" name="origem" tabindex="2">
                    </label>
                </div><div class="col-2">
                    <label>
                        Destino
                        <input placeholder="" id="company" name="destino" tabindex="3">
                    </label>
                </div>

                <div class="col-3">
                    <label>
                        Peso
                        <input placeholder="" id="email" name="peso" tabindex="4">
                    </label>
                </div>
                <div class="col-3">
                    <label>
                        Data máxima
                        <input placeholder="" id="email" name="data_max" tabindex="5">
                    </label>
                </div>
                <div class="col-3">
                    <label>
                        STATUS
                        <select tabindex="5" name="status">
                            <option value="1">Embarcada</option>
                            <option value="0">Não Embarcada</option>        
                        </select>
                    </label>
                </div>
                <div class="col-3">
                    <label>
                        Tipo
                        <select tabindex="5" name="tipo">
                            <option value="1">Perecível</option>
                            <option value="2">Outro tipo</option>        
                        </select>
                    </label>
                </div>
                <div class="col-3">
                    <label>
                        Data validade
                        <input placeholder="" id="email" name="validade" tabindex="4">
                    </label>
                </div>
                <div class="col-3">
                    <label>
                        Temperatura máxima
                        <input placeholder="" id="email" name="temperatura" tabindex="4">
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
        <br><br><br>

        <h2>Recuperação de todos as cargas do banco</h2>
        <%
            CargaDAO cDao = new CargaDAO();
            Carga cargateste;
            List<Carga> listaCarga = cDao.getListaCargas();

            for (int i = 0; i < listaCarga.size(); i++) {
                cargateste = listaCarga.get(i);
        %>
        <p>O navio  de ID <%=cargateste.getId_Navio()%> <br>
            Id carga : <%=cargateste.getId_Carga()%> <br> 
            Origem  <%=cargateste.getOrigem()%>  <br> 
            Destino  <%=cargateste.getDestino()%> <br> 
            Peso  <%=cargateste.getPeso()%><br> 
            Tipo  <%=cargateste.getTipo()%> está no bd. 

            <br><br>
            <%
                }
            %>


    </body>
</html>       