import java.util.ArrayList;
import java.util.List;

public class Quiz {
    private List<Question> quizQuestions = new ArrayList<>();
    public List<Question> getQuizQuestions() {
        return quizQuestions;
    }

    public void addQuizQuestions(Question question) {
        quizQuestions.add(question);
    }


}
