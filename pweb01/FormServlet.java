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

        ArrayList<String> validacao = new ArrayList<String>();

        ArrayList<Integer> ListaDDD = new ArrayList<Integer>(
                Arrays.asList(61, 62, 64, 65, 66, 67, 82, 71, 73, 74, 75, 77, 85, 88, 98, 99, 83, 81, 87, 86, 89, 84,
                        79, 68, 96, 92, 97, 91, 93, 94, 69, 95, 63, 27, 28, 31, 32, 33, 34, 35, 37, 38, 21, 22, 24, 11,
                        12, 13, 14, 15, 16, 17, 18, 19, 41, 42, 43, 44, 45, 46, 51, 53, 54, 55, 47, 48, 49));

        int ContadorGeral = 0;

        String nomeAluno = req.getParameter("nome-aluno");
        if (!(nomeAluno.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Nome do Aluno não preenchido");
        }

        String Nascimento = req.getParameter("nascimento");
        LocalDate DataNascimento = LocalDate.parse(Nascimento, DateTimeFormatter.ISO_DATE);
        if (!(Nascimento.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Data de Nascimento não preenchido");
        }

        String nomeMae = req.getParameter("nome_mae");
        if (!(nomeMae.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Nome da Mãe não preenchido");
        }

        String nomePai = req.getParameter("nome_pai");
        if (!(nomePai.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Nome do Pai não preenchido");
        }

        String ddd = req.getParameter("ddd");
        int IntDDD = Integer.parseInt(ddd);
        if (!(ddd.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("DDD não preenchido");
        }

        String tel = req.getParameter("telefone");
        if (!(tel.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Telefone não preenchido");
        }

        String ramal = req.getParameter("ramal");
        if (!(ramal.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Ramal não preenchido");
        }

        String email = req.getParameter("email");
        if (!(email.equals(""))) {
            ContadorGeral += 1;
        } else {
            validacao.add("Email não preenchido");
        }

        String turnoAluno = req.getParameter("turno");

        /* Checkbox */
        int ContadorCheckBox = 0;

        String atvInformatica = req.getParameter("informatica");
        if (atvInformatica != null) {
            ContadorCheckBox += 1;
        }

        String atvMusica = req.getParameter("musica");
        if (atvMusica != null) {
            ContadorCheckBox += 1;
        }

        String atvBalet = req.getParameter("balet");
        if (atvBalet != null) {
            ContadorCheckBox += 1;
        }

        String atvPintura = req.getParameter("pintura");
        if (atvPintura != null) {
            ContadorCheckBox += 1;
        }

        String atvJudo = req.getParameter("judo");
        if (atvJudo != null) {
            ContadorCheckBox += 1;
        }

        String atvFutebol = req.getParameter("futebol");
        if (atvFutebol != null) {
            ContadorCheckBox += 1;
        }
        /* -----------------------------VALIDAÇÕES-------------------------------- */

        /* Validação Data */
        if (!(DataNascimento.getYear() < 1990 || DataNascimento.getYear() < 2022)) {
            validacao.add("Data de Nascimento inválida");
        }

        /* Validação Email */
        if (!(email.contains(".") && email.contains("@"))) {
            validacao.add("Formato de Email inválido");
        }

        /* Validação DDD */
        if (!(ListaDDD.contains(IntDDD))) {
            validacao.add("DDD inválido");
        }

        /* Validação de Checkbox */
        if (ContadorCheckBox > 3) {
            validacao.add("Só é permitido marcar 3 opções de atividades");
        }

        /* Mensagem de confirmação do cadastro */
        if (validacao.size() > 0) {
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