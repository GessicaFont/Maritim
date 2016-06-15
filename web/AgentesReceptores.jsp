<%@page import="MODEL.Agente"%>
<%@page import="MODELO.DAO.AgenteDAO"%>
<%@page import="MODEL.Porto"%>
<%@page import="MODELO.DAO.PortoDAO"%>
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
                            <li><a href="Navios.jsp">Navios</a></li>  
                            <li><a href="Cargas.jsp">Cargas</a></li>
                            <li><a href="Rotas.jsp">Rotas</a></li>
                            <li><a href="Portos.jsp">Portos</a></li>
                        </ul>
                    </div>

                </div>
            </div>
            <!-- END HEADER SECTION-->
        </header>

        <div id="wrapper">
            <br><br><br><br>
            <h1>Registrar Agente Receptor</h1>

            <form action="NovoServlet?metodo=cadastraragente" method="POST">
                <table>
                    <tr>
                        <td>NOME</td>
                        <td><input placeholder="" id="company" name="nome" tabindex="2" ></td>
                    </tr>                    
                    <tr>
                        <td>CÓDIGO DO AGENTE</td>
                        <td><input placeholder="" id="company" name="codigo" tabindex="2" ></td>
                    </tr>
                    <tr>
                        <td>TELEFONE </td>
                        <td>
                            <input placeholder="" id="email" name="telefone" tabindex="4"></td>
                    </tr>
                    <%
                        out.println("<tr>");
                        out.println("<td>");
                        out.println("PORTO ");
                        out.println("</td>");
                        out.println("<td><select tabindex='1' name='porto' id='porto'>");
                        PortoDAO pDao = new PortoDAO();
                        List<Porto> lista = pDao.getListaPortos();

                        for (Porto porto : lista) {
                            out.println("<option value=\"" + porto.getId_Porto() + "\" >" + porto.getNome() + "</option>");
                        }

                        out.println("</td>");
                        out.println("</tr>");
                    %>
                    <tr>
                        <td>
                            <button class="submitbtn">Submeter</button></td>
                    </tr>
                </table>
            </form>
        </div>
        <script type="text/javascript">
            var elems = Array.prototype.slice.call(document.querySelectorAll('.js-switch'));

            elems.forEach(function (html) {
                var switchery = new Switchery(html);
            });
        </script>

        <br><br><br>
        <h2>Agentes Receptores cadastrados:</h2>
        <table style="text-align: center;" border="1px">
            <tr>
                <td style="width: 50px; background-color: #c0c0c0">Identificador</td>
                <td style="width: 250px; background-color: #c0c0c0">Nome</td>
                <td style="width: 180px; background-color: #c0c0c0">Telefone</td>
                <td style="width: 200px; background-color: #c0c0c0">Porto</td>
                <td style="width: 200px; background-color: #c0c0c0">Ação</td>
            </tr>
            <%
                AgenteDAO aDao = new AgenteDAO();
                Agente agente = new Agente();
                List<Agente> listaAgentes = aDao.getListaAgentes();

                for (int i = 0; i < listaAgentes.size(); i++) {
                    agente = listaAgentes.get(i);
                    String porto = "";
                    Porto pt = pDao.getPorto(agente.getId_Porto());

                    if (pt != null) {
                        porto = pt.getNome();
                    }
            %>

            <tr>
                <td><%= agente.getCod_Agente()%></td>
                <td><%=agente.getNome()%></td>
                <td><%=agente.getTelefone()%></td>
                <td><%=porto%></td>
                <td>Alterar | <a href="NovoServlet?metodo=excluiragente&agenteid=<%=agente.getCod_Agente()%>">Excluir</a></td>
            </tr>
            <%
                }
            %>
        </table>

    </body>
</html>       