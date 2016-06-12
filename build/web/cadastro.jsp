<%@page import="MODEL.Navio"%>
<%@page import="MODELO.DAO.NavioDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MARÍTIMA - Teste do BD</title>
    </head>
    <body>
           
        <header>
            <a style="color:blue" href="index.jsp">Home</a>
        </header>
        
        <h1 style="text-align:center"> Marítima</h1>
        
        <br><h2>Inserção de navio no banco</h2>
            <form name="myform"  method="POST">
<div align="left">
<br><br>
<input type="text" size="25" value="ID do navio">
<form name="myform"  method="POST">
<div align="left">
<br><br>
<input type="text" size="25" value="Nome do navio">
<form name="myform"  method="POST">
<div align="left">
<br><br>
<input type="text" size="25" value="Status">
<form name="myform"  method="POST">
<div align="left">
<br><br>
<input type="text" size="25" value="Capacidade máxima">
<br><input type="submit" value="Submeter"><br>
</div>
</form>
        <%  
            NavioDAO acesso = new NavioDAO();
            
            int random = (int)(Math.random() * 100);
            String result = Integer.toString(random);
            String nome = "navio" + result;
            double peso = 254.5;
            Navio teste = new Navio(random,nome,nome,peso);
            if(acesso.insereNavio(teste)){

            %> 
            
            <h3>Navio inserido com sucesso!!</h3>
            
            <%
                
            }else{
            
                
            %>
             
            <h3>Falha</h3>
            
            <br><br><br>
            
        <%
            }
            %>  
        
        <h2>Recuperação de todos os navios do banco</h2>
        <%
            Navio cobaia;
            List<Navio> lista = acesso.getListaNavios();
                     
            
            for(int i=0; i < lista.size(); i++){
                cobaia = lista.get(i);
            %>
            <p>O navio <%=cobaia.getNome()%>, de ID <%=cobaia.getId_Navio()%>-. 
            
            <br><br>
        <%
            }
            %>
            
              
   </body>
</html>       