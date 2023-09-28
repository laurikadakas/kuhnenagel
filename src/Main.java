import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Quiz newQuiz = new Quiz();
        Response responseFalse1 = new Response("White", false);
        Response responseFalse2 = new Response("Red", false);
        Response responseCorrect1 = new Response("Green", true);
        Response responseCorrect2 = new Response("Yellow", true);
        List<Response> questionResponses1 = new ArrayList<>(Arrays.asList(responseFalse1, responseFalse2, responseCorrect1, responseCorrect2));
        Question newQuestion = new Question("Food", 1, "What colour is a banana?", questionResponses1);
        newQuiz.addQuizQuestions(newQuestion);

        DaoQuestion daoQuestion = new DaoQuestion();
        // daoQuestion.saveQuestion(newQuestion, questionResponses1);
        // daoQuestion.deleteQuestion(7);

        Response updateResponseFalse1 = new Response("Anu Saagim", false);
        Response updateResponseFalse2 = new Response("Kaja Kallas", false);
        Response updateResponseCorrect1 = new Response("Alar Karis", true);
        List<Response> updatedQuestionResponses1 = new ArrayList<>(Arrays.asList(updateResponseFalse1, updateResponseFalse2, updateResponseCorrect1));
        Question updatedQuestion = new Question("Famous people", 3, "Who is the President?", updatedQuestionResponses1);
        // daoQuestion.updateQuestion(updatedQuestion, updatedQuestionResponses1, 8);

        // daoQuestion.searchQuestionByTopic("Food");
    }
}
