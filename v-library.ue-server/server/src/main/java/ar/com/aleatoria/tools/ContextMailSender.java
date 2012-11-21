
package ar.com.aleatoria.tools;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class ContextMailSender {

    private final Logger log = Logger.getLogger(ContextMailSender.class);

    @Autowired
    private Mailer mailer;

    private String serverUrl;

    private String serverEnv;

    public String getServerEnv() {
        return serverEnv;
    }

    public void setServerEnv(String serverEnv) {
        this.serverEnv = serverEnv;
    }

    private Resource contestMailTemplate;

    public Resource getContestMailTemplate() {
        return contestMailTemplate;
    }

    public void setContestMailTemplate(Resource contestMailTemplate) {
        this.contestMailTemplate = contestMailTemplate;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    // private String makeNewMail(ReaderUser draftOwner, GameText gameText,
    // GameResult aResult, ArrayList<GameResult> results) throws IOException {
    // String htmlStr = getContestMailTemplateString();
    // htmlStr = htmlStr.replace("{SERVER_URL}", serverUrl);
    //
    // htmlStr = htmlStr.replace("{USER_NAME}", draftOwner.name);
    // htmlStr = htmlStr.replace("{TEXT_NAME}", gameText.title);
    // htmlStr = htmlStr.replace("{USER_SCORE}", "" + aResult.score());
    // htmlStr = htmlStr.replace("{USER_POSITION}", "" +
    // results.indexOf(aResult) + 1);
    //
    // int rankingIndex = 0;
    // for (GameResult result : results) {
    // rankingIndex = results.indexOf(result) + 1;
    // htmlStr = htmlStr.replace("{USER_NAME_" + rankingIndex + "}",
    // result.draft.owner.name);
    // htmlStr = htmlStr.replace("{USER_SCORE_" + rankingIndex + "}", "" +
    // result.score());
    // }
    //
    // for (int i = rankingIndex; i < 20; i++) {
    // htmlStr = htmlStr.replace("{USER_NAME_" + (i+1) + "}", "");
    // htmlStr = htmlStr.replace("{USER_SCORE_" + (i+1) + "}", "");
    // }
    //
    // return htmlStr;
    // }
    //
    // private String getContestMailTemplateString() throws IOException {
    // String content;
    // BufferedReader reader = new BufferedReader(new
    // InputStreamReader(contestMailTemplate.getInputStream()));
    //
    // content = StringUtility.bufferedReaderToString(reader);
    // return content;
    // }
    //
    // public void sendMail(ReaderUser draftOwner, GameText gameText,
    // GameResult aResult, ArrayList<GameResult> results) throws
    // MessagingException, IOException {
    // /* Le enviamos el mail a cada jugador que participo */
    //
    // if (serverEnv.equals("prod") || serverEnv.equals("staging") ){
    // String draftOwnerMail = draftOwner.getMail();
    // String subject =
    // "Resultados del concurso del texto "+gameText.getTitle();
    // String msg = makeNewMail(draftOwner, gameText, aResult, results);
    // mailer.sendMail(draftOwnerMail, subject, msg);
    // }
    //
    // }

}
