import java.util.ArrayList;
import java.util.List;

public class Question {
    private String topic;
    private int difficulty;
    private String content;
    private List<Response> questionResponses = new ArrayList<>();
    public Question(String topic, int difficulty, String content, List<Response> questionResponses) {
        this.topic = topic;
        this.difficulty = difficulty;
        this.content = content;
        this.questionResponses = questionResponses;
    }
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Response> getQuestionResponses() {
        return questionResponses;
    }

    public void addQuestionResponse(Response response) {
        questionResponses.add(response);
    }


}
