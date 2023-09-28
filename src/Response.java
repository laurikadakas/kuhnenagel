public class Response {
    private String text;
    private boolean isCorrect;
    public Response(String text, boolean isCorrect) {
        this.text = text;
        this.isCorrect = isCorrect;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setAnswerTrueOrFalse(boolean correct) {
        isCorrect = correct;
    }


}
