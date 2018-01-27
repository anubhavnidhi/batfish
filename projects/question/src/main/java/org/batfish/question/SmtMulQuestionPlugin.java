package org.batfish.question;

import com.google.auto.service.AutoService;
import org.batfish.common.Answerer;
import org.batfish.common.plugin.IBatfish;
import org.batfish.common.plugin.Plugin;
import org.batfish.datamodel.answers.AnswerElement;
import org.batfish.datamodel.questions.Question;
import org.batfish.datamodel.questions.smt.HeaderLocationQuestion;

@AutoService(Plugin.class)
public class SmtMulQuestionPlugin extends QuestionPlugin {

  @Override
  protected Answerer createAnswerer(Question question, IBatfish batfish) {
    return new MulAnswerer(question, batfish);
  }

  @Override
  protected Question createQuestion() {
    return new MulQuestion();
  }

  public static class MulAnswerer extends Answerer {

    public MulAnswerer(Question question, IBatfish batfish) {
      super(question, batfish);
    }

    @Override
    public AnswerElement answer() {
      MulQuestion q = (MulQuestion) _question;
      return _batfish.smtMul(q);
    }
  }

  public static class MulQuestion extends HeaderLocationQuestion {

    @Override
    public boolean getDataPlane() {
      return false;
    }

    @Override
    public String getName() {
      return "smt-mul";
    }

    @Override
    public String prettyPrint() {
        String retString =
            String.format("smt-mul %s", super.prettyPrintParams());
        return retString;
    }
  }
}