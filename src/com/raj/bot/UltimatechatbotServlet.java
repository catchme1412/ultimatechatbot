package com.raj.bot;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.eliza.model.AuxiliaryVerb;
import com.raj.eliza.model.Comment;
import com.raj.eliza.model.DataLoaderUtil;

@SuppressWarnings("serial")
public class UltimatechatbotServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        if ("y".equals(req.getParameter("ajax"))) {
            resp.getWriter().println("AJAX");
            return;
        } else {
            if ("y".equals(req.getParameter("c")) || true) {
                InputStreamReader stream = new InputStreamReader(ClassLoader.getSystemResourceAsStream("eliza.dat"));
                DataLoaderUtil t = new DataLoaderUtil(stream);
                for (Comment c : t.getComments()) {
                    CommentDAO.create(c);
                }
                for (AuxiliaryVerb v : t.getAuxVerbs()) {
                    AuxiliaryVerbDAO.create(v);
                }
            }
            List<AuxiliaryVerb> a = AuxiliaryVerbDAO.getLatestEntries(0, 2);
            System.out.println(a);
        }
    }
}
