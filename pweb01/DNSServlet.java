package br.edu.ifal.pweb01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DNS Servlet", urlPatterns = "/dns")
public class DNSServlet extends HttpServlet {

    // Map to store the IP mapped by domainName
    private ConcurrentMap<String, String> ipMap;

    public void init() throws ServletException {
        ipMap = new ConcurrentHashMap<>();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String domainName = req.getParameter("domainName");
        String ip = req.getParameter("ip");

        if (ipMap.containsKey(domainName)) {
            resp.getWriter().println("IP: " + ipMap.get(domainName));

        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String domainName = req.getParameter("domainName");
        String ip = req.getParameter("ip");

        if (ipMap.containsKey(domainName)) {
            resp.getWriter().println("IP: " + ipMap.get(domainName));

        } else if (domainName == null || ip == null) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;

        } else {
            ipMap.put(domainName, ip);
            resp.getWriter().println("DOMINIO CADASTRADO");
            resp.setStatus(HttpServletResponse.SC_CREATED);
        }

    }

}
