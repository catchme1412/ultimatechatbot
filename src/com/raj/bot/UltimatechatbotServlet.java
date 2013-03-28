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
import com.raj.eliza.model.Phrase;
import com.raj.eliza.model.Transformer;

@SuppressWarnings("serial")
public class UltimatechatbotServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/plain");
        if ("y".equals(req.getParameter("ajax"))) {
            resp.getWriter().println("AJAX");
            System.out.println(getAnswer("FF"));
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

    public String getAnswer(String input) {
        input = " " + input + " ";
        int index = -1;
        String upperInput = input.toUpperCase();
        List<Comment> comments = CommentDAO.getLatestEntries(0, 100);
        for (Comment comment : comments) {
            for (Phrase phrase1 : comment.getPhraseGroup().getPhrases()) {
                String phrase = phrase1.getPhraseText();
                index = upperInput.indexOf(" " + phrase + " ");
                if (index > -1) {
                    String inputPhrase = adaptAuxVerbs(input.substring(index + 2 + phrase.length()));
                    return cleanOutput(Transformer.tranform(comment.getAnswerGroup().getAnswers().iterator().next()
                            .getAnswerText(), "*", inputPhrase));
                }
                ;
            }
            ;
        }
        return "";
    }
    
    public static void main(String[] args) {
        new UltimatechatbotServlet().getAnswer("FF");
    }

    public String adaptAuxVerbs(String input) {
        String retVal = input;
        for (AuxiliaryVerb auxVerb : AuxiliaryVerbDAO.getLatestEntries(0, 100)) {
            retVal = Transformer.tranform(retVal, " " + auxVerb.getOriginal() + " ", " " + auxVerb.getTranformed()
                    + "# ");
            retVal = Transformer.tranform(retVal, " " + auxVerb.getTranformed() + " ", " " + auxVerb.getOriginal()
                    + "# ");
        }
        ;
        return Transformer.tranform(retVal, "#", "");
    }

    protected String cleanOutput(String output) {
        return Transformer.tranform(Transformer.tranform(Transformer.tranform(
                Transformer.tranform(Transformer.tranform(output, " .", "."), " !", "!"), " ?", "?"), " ,", ","),
                "  ", " ");
    }

}
