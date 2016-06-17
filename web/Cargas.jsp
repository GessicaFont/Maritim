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
            <h1>Registrar Carga</h1>

            <form action="NovoServlet?metodo=cadastrarcarga" method="POST">  
                <table> <%
                    out.println("<tr>");
                    out.println("<td>");
                    out.println("NAVIO");
                    out.println("</td>");
                    out.println("<td><select tabindex='1' name='navio' id='navios'>");
                    NavioDAO acesso = new NavioDAO();
                    List<Navio> lista = acesso.getListaNavios();

                    for (Navio navios : lista) {
                        out.println("<option value=\"" + navios.getId_Navio() + "\" >" + navios.getNome() + "</option>");
                    }

                    out.println("</td>");
                    out.println("</tr>");
                    %>
                    <tr><td>
                            Origem</td>
                        <td><input placeholder="" id="company" name="origem" tabindex="2"></td>
                    </tr>
                    <tr><td>
                            Destino</td>
                        <td><input placeholder="" id="company" name="destino" tabindex="3"></td>
                    </tr>
                    <tr><td>
                            Peso</td>
                        <td> <input placeholder="" id="email" name="peso" tabindex="4"></td>
                    </tr>
                    <tr>
                        <td>
                            Data máxima</td>
                        <td><input placeholder="" id="email" name="data_max" tabindex="5"></td>
                    </tr>
                    <tr>
                        <td>
                            STATUS</td>
                        <td><select tabindex="5" name="status">
                                <option value="1">Embarcada</option>
                                <option value="0">Não Embarcada</option>        
                            </select></td></tr>
                    </div>
                    <tr>
                        <td>
                            Tipo</td>
                        <td><select tabindex="5" name="tipo" id="tipo" onChange="habilitaCampos()">
                                <option value="1">Perecível</option>
                                <option value="2">Outro tipo</option>        
                            </select></td>
                    </tr>
                      
                        <tr>
                            <td>Data validade</td>
                            <td><input placeholder="" id="validade" name="validade" tabindex="4"></td>
                        </tr>

                        <tr>
                            <td>Temperatura máxima</td>
                            <td><input placeholder="" id="temperatura" name="temperatura" tabindex="4" disabled ></td>
                        </tr>
                        
                    <tr><td>
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

            function habilitaCampos() {
                var x = document.getElementById("tipo").value;

                if (x === "2")
                {
                    document.getElementById("validade").disabled = 'true';
                    document.getElementById("validade").value = '';
                    document.getElementById("temperatura").disabled = '';
                }
                else
                {    
                    document.getElementById("validade").disabled = '';                
                    document.getElementById("temperatura").disabled = 'true';        
                    document.getElementById("temperatura").value = '';
                }
            }


        </script>
        <br><br><br>

        <h2>Cargas cadastradas</h2>

        <table style="text-align: center;" border="1px">
            <tr>
                <td style="width: 50px; background-color: #c0c0c0">Identificador</td>
                <td style="width: 250px; background-color: #c0c0c0">Navio</td>
                <td style="width: 350px; background-color: #c0c0c0">Origem</td>
                <td style="width: 350px; background-color: #c0c0c0">Destino</td>
                <td style="width: 100px; background-color: #c0c0c0">Peso</td>
                <td style="width: 100px; background-color: #c0c0c0">Tipo</td>
                <td style="width: 200px; background-color: #c0c0c0">Ação</td>
            </tr>
            <%
                CargaDAO cDao = new CargaDAO();
                NavioDAO nDao = new NavioDAO();

                Carga cargateste;
                List<Carga> listaCarga = cDao.getListaCargas();

                for (int i = 0; i < listaCarga.size(); i++) {
                    cargateste = listaCarga.get(i);
                    String navio = "";
                    Navio nv = nDao.getNavio(cargateste.getId_Navio());

                    if (nv != null) {
                        navio = nv.getNome();
                    }
            %>
            <tr>
                <td><%=cargateste.getId_Carga()%> </td> 
                <td><%=navio%> </td>
                <td><%=cargateste.getOrigem()%>  </td> 
                <td><%=cargateste.getDestino()%> </td>
                <td><%=cargateste.getPeso()%></td>
                <td><%=cargateste.getTipoCarga()%> </td>
                <td>Alterar | <a href="NovoServlet?metodo=excluircarga&cargaid=<%=cargateste.getId_Carga()%>">Excluir</a></td>
            </tr>
            <%
                }
            %>
        </table>
        <br><br><br>
    </body>
</html>       