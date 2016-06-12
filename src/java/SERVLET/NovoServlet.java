/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SERVLET;

import MODEL.Navio;
import MODELO.DAO.NavioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try (PrintWriter out = response.getWriter()) {

            String metodo = request.getParameter("metodo");

            switch (metodo) {
                case "cadastrarnavio":
                    cadastrarNavio(request, response);
                    break;

                default:
                    response.sendRedirect("index.jsp");
                    break;
            }
        }
    }

    private void cadastrarNavio(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException{

        //obter infos
        String nome = request.getParameter("nome");
        Double capacidade = Double.parseDouble(request.getParameter("Cap_Maxima"));
        String status = request.getParameter("status");        
        
        Navio nv = new Navio(nome, status, capacidade);
        
        NavioDAO nDao = new NavioDAO();
        
        //Se der sucesso no insert, leva a pagina de lista
        if(nDao.insereNavio(nv))
            response.sendRedirect("principal.jsp");
        else //se falhar, leva a index
            response.sendRedirect("index.jsp");
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
