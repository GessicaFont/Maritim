/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import MODEL.Agente;
import MODEL.Carga;
import MODEL.Navio;
import MODEL.Porto;
import MODEL.Rota;
import MODELO.DAO.AgenteDAO;
import MODELO.DAO.CargaDAO;
import MODELO.DAO.NavioDAO;
import MODELO.DAO.PortoDAO;
import MODELO.DAO.RotaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 *
 * @author Cliente
 */
public class NovoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String metodo = request.getParameter("metodo");

        switch (metodo) {
            case "cadastrarnavio":
                cadastrarNavio(request, response);
                break;

            case "excluirnavio":
                excluirNavio(request, response);
                break;

            case "cadastrarcarga":
                cadastrarCarga(request, response);
                break;

            case "excluircarga":
                excluirCarga(request, response);
                break;

            case "cadastrarporto":
                cadastrarPorto(request, response);
                break;

            case "excluirporto":
                excluirPorto(request, response);
                break;

            case "cadastrarrota":
                cadastrarRota(request, response);
                break;

            case "excluirrota":
                excluirRota(request, response);
                break;

            case "cadastraragente":
                cadastrarAgente(request, response);
                break;

            case "excluiragente":
                excluirAgente(request, response);
                break;
            default:
                response.sendRedirect("index.jsp");
                break;
        }
    }

    private void cadastrarNavio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        String nome = request.getParameter("nome");
        Double capacidade = Double.parseDouble(request.getParameter("Cap_Maxima"));
        String status = request.getParameter("status");

        Navio nv = new Navio(nome, status, capacidade);

        NavioDAO nDao = new NavioDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (nDao.insereNavio(nv)) {
            response.sendRedirect("Navios.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void excluirNavio(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int navioId = Integer.parseInt(request.getParameter("navioid"));

        NavioDAO nDao = new NavioDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (nDao.removeNavio(navioId)) {
            response.sendRedirect("Navios.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void cadastrarCarga(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int navioId = Integer.parseInt(request.getParameter("navio"));
        String origem = request.getParameter("origem");
        String destino = request.getParameter("destino");
        double peso = Double.parseDouble(request.getParameter("peso"));

        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime data_Max = formatter.parseDateTime(request.getParameter("data_max"));
        DateTime data_validade = formatter.parseDateTime(request.getParameter("validade"));

        boolean status;
        if (request.getParameter("status").equals("1")) {
            status = true;
        } else {
            status = false;
        }
        int tipo = Integer.parseInt(request.getParameter("tipo"));

        double temperatura = Double.parseDouble(request.getParameter("temperatura"));

        Carga carga = new Carga(navioId, tipo, origem, destino, peso, temperatura, data_Max, data_validade, status);

        CargaDAO cDao = new CargaDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (cDao.insereCarga(carga)) {
            response.sendRedirect("Cargas.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void excluirCarga(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int cargaId = Integer.parseInt(request.getParameter("cargaid"));

        CargaDAO cDao = new CargaDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (cDao.removeCarga(cargaId)) {
            response.sendRedirect("Cargas.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void cadastrarPorto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        String nome = request.getParameter("nome");
        String localidade = request.getParameter("localidade");

        Porto p = new Porto(nome, localidade);

        PortoDAO pDao = new PortoDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (pDao.inserePorto(p)) {
            response.sendRedirect("Portos.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void excluirPorto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int portoId = Integer.parseInt(request.getParameter("portoid"));

        PortoDAO pDao = new PortoDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (pDao.removePorto(portoId)) {
            response.sendRedirect("Portos.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void cadastrarRota(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int portoOrigem = Integer.parseInt(request.getParameter("porto_origem"));
        int portoDestino = Integer.parseInt(request.getParameter("porto_destino"));

        Rota r = new Rota(portoOrigem, portoDestino);

        RotaDAO rDao = new RotaDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (rDao.insereRota(r)) {
            response.sendRedirect("Rotas.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void excluirRota(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int rotaId = Integer.parseInt(request.getParameter("rotaid"));

        RotaDAO rDao = new RotaDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (rDao.removeRota(rotaId)) {
            response.sendRedirect("Rotas.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void cadastrarAgente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        String nome = request.getParameter("nome");
        String telefone = request.getParameter("telefone");
        int codigo = Integer.parseInt(request.getParameter("codigo"));
        int porto = Integer.parseInt(request.getParameter("porto"));

        Agente a = new Agente(codigo, porto, nome, telefone);

        AgenteDAO aDao = new AgenteDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (aDao.insereAgente(a)) {
            response.sendRedirect("AgentesReceptores.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    private void excluirAgente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //obter infos
        int agenteId = Integer.parseInt(request.getParameter("agenteid"));

        AgenteDAO aDao = new AgenteDAO();

        //Se der sucesso no insert, leva a pagina de lista
        if (aDao.removeAgente(agenteId)) {
            response.sendRedirect("AgentesReceptores.jsp");
        } else //se falhar, leva a index
        {
            response.sendRedirect("index.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
