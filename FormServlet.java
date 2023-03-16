package br.edu.ifal.pweb01;

import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Formulario", value = "/meuform")

public class FormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("index.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ArrayList<Boolean> validacao = new ArrayList<Boolean>((Arrays.asList(true, true, true, true, true)));

        ArrayList<Integer> ListaDDD = new ArrayList<Integer>(
                Arrays.asList(61, 62, 64, 65, 66, 67, 82, 71, 73, 74, 75, 77, 85, 88, 98, 99, 83, 81, 87, 86, 89, 84,
                        79, 68, 96, 92, 97, 91, 93, 94, 69, 95, 63, 27, 28, 31, 32, 33, 34, 35, 37, 38, 21, 22, 24, 11,
                        12, 13, 14, 15, 16, 17, 18, 19, 41, 42, 43, 44, 45, 46, 51, 53, 54, 55, 47, 48, 49));

        String nomeAluno = req.getParameter("nome-aluno");

        String Nascimento = req.getParameter("nascimento");
        LocalDate DataNascimento = LocalDate.parse(Nascimento, DateTimeFormatter.ISO_DATE);

        String nomeMae = req.getParameter("nome_mae");

        String nomePai = req.getParameter("nome_pai");
        String ddd = req.getParameter("ddd");
        int IntDDD = Integer.parseInt(ddd);

        String tel = req.getParameter("telefone");

        String ramal = req.getParameter("ramal");

        String email = req.getParameter("email");

        String turnoAluno = req.getParameter("turno");

        String atvInformatica = req.getParameter("informatica");

        String atvMusica = req.getParameter("musica");

        String atvBalet = req.getParameter("balet");

        String atvPintura = req.getParameter("pintura");

        String atvJudo = req.getParameter("judo");

        String atvFutebol = req.getParameter("futebol");
        /* -----------------------------VALIDAÇÕES-------------------------------- */

        /* Validação Data */
        if (!(DataNascimento.getYear() < 1990 || DataNascimento.getYear() < 2022)) {
            validacao.set(1, false);
        }

        /* Validação Email */
        if (!(email.contains("."))) {
            validacao.set(2, false);
        }

        /* Validação DDD */
        if (!(ListaDDD.contains(IntDDD))) {
            validacao.set(3, false);
        }

        /* Mensagem de confirmação do cadastro */
        if (validacao.contains(false)) {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("http://localhost:8080/pweb01/invalido");
        } else {
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("http://localhost:8080/pweb01/sucesso");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Você fez um PUT");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Você fez um DELETE");
    }

}