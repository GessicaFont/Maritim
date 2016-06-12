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
  
  <form onsubmit="return false">
  <div class="col-2">
    <label>
      ID Carga
      <input placeholder="" id="name" name="Id_Carga" tabindex="1">
    </label>
  </div>
  <div class="col-2">
    <label>
      Id Navio
      <input placeholder="" id="company" name="Id_Navio" tabindex="2">
    </label>
  </div>
  <div class="col-2">
    <label>
      Origem
      <input placeholder="" id="company" name="Origem" tabindex="2">
    </label>
  </div><div class="col-2">
    <label>
      Destino
      <input placeholder="" id="company" name="Destino" tabindex="2">
    </label>
  </div>
 
  <div class="col-3">
    <label>
      Peso
      <input placeholder="" id="email" name="Peso" tabindex="4">
    </label>
  </div>
      <div class="col-3">
    <label>
     Data máxima
      <input placeholder="" id="email" name="Peso" tabindex="4">
    </label>
  </div>
  <div class="col-3">
    <label>
      STATUS
      <select tabindex="5">
        <option>Embarcada</option>
        <option>Não Embarcada</option>
        
      </select>
    </label>
  </div>
      <div class="col-3">
    <label>
      Tipo
      <select tabindex="5">
        <option>Perecível</option>
        <option>Outro tipo</option>
        
      </select>
    </label>
  </div>
  <div class="col-3">
    <label>
     Data validade
      <input placeholder="" id="email" name="Peso" tabindex="4">
    </label>
  </div>
      <div class="col-3">
    <label>
     Temperatura máxima
      <input placeholder="" id="email" name="Peso" tabindex="4">
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

elems.forEach(function(html) {
  var switchery = new Switchery(html);
});
</script>

       
            
            <h3>Carga inserido com sucesso!!</h3>
            
            
             
            <h3>Falha</h3>
            
            <br><br><br>
            
        
        
        <h2>Recuperação de todos as cargas do banco</h2>
        <%
             CargaDAO acesso = new CargaDAO();
            Carga cargateste;
            List<Carga> lista = acesso.getListaCargas();
             
            for(int i=0; i < lista.size(); i++){
                cargateste = lista.get(i);
            %>
            <p>O navio  de ID <%=cargateste.getId_Navio()%> <br>Id carga : <%=cargateste.getId_Carga()%> <br> Origem  <%=cargateste.getOrigem()%>  <br> Destino  <%=cargateste.getDestino()%> <br> Peso  <%=cargateste.getPeso()%><br> Data Ma´xima  <%=cargateste.getData_Max()%>  <br> Tipo  <%=cargateste.getTipo()%>está no bd. 
            
            
            <br><br>
        <%
            }
            %>
            
              
   </body>
</html>       