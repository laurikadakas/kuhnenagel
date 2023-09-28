import java.sql.*;
import java.util.Arrays;
import java.util.List;

public class DaoQuestion {
    private String url = "jdbc:mysql://localhost:3306/quizapplication";
    private String user = "root";
    private String password = "root";



    public void saveQuestion(Question question, List<Response> responses) {
        String addQuestionsSQL = "INSERT INTO questions (questionid, topic, difficulty, content) VALUES (?, ?, ?, ?)";
        String addResponsesSQL = "INSERT INTO responses (responseid, questionid, text, iscorrect) VALUES (?, ?, ?, ?)";
        int questionID = 0;
        // save question into questions table first
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(addQuestionsSQL, Statement.RETURN_GENERATED_KEYS))
        {
            // questionid is auto-incremental, so we can leave this field as 0
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, question.getTopic());
            preparedStatement.setInt(3, question.getDifficulty());
            preparedStatement.setString(4, question.getContent());

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            while (resultSet.next()) {
                questionID = resultSet.getInt(1);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // then save responses into responses table
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(addResponsesSQL);
             )
        {
            connection.setAutoCommit(false);
            for (Response response : responses) {
                //responseid is auto-incremental, so we can leave this field as 0
                preparedStatement.setInt(1, 0);
                preparedStatement.setInt(2, questionID);
                preparedStatement.setString(3, response.getText());
                preparedStatement.setBoolean(4, response.isCorrect());
                System.out.println(preparedStatement);
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void updateQuestion(Question question, List<Response> responses, int questionToBeReplacedId) {

        String updateQuestionsSQL = "UPDATE questions SET topic = ?, difficulty = ?, content = ? WHERE questionid = ?";
        String addResponsesSQL = "INSERT INTO responses (responseid, questionid, text, iscorrect) VALUES (?, ?, ?, ?)";
        // update questions table first
        try(Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement(updateQuestionsSQL)) {

            statement.setString(1, question.getTopic());
            statement.setInt(2,question.getDifficulty());
            statement.setString(3, question.getContent());
            statement.setInt(4, questionToBeReplacedId);
            System.out.println(statement);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // didn't find a good way to update list of answers individually, so I delete the answers and set new ones
        String deleteResponsesSQL = "DELETE FROM responses WHERE questionid = " + questionToBeReplacedId;
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(deleteResponsesSQL);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // update responses by adding the new ones
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(addResponsesSQL);
        )
        {
            connection.setAutoCommit(false);
            for (Response response : responses) {
                preparedStatement.setInt(1, 0);
                preparedStatement.setInt(2, questionToBeReplacedId);
                preparedStatement.setString(3, response.getText());
                preparedStatement.setBoolean(4, response.isCorrect());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            connection.commit();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteQuestion(int questionID) {
        String deleteQuestionsSQL = "DELETE FROM questions WHERE questionid = " + questionID;
        String deleteResponsesSQL = "DELETE FROM responses WHERE questionid = " + questionID;
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
        ) {

            statement.addBatch(deleteQuestionsSQL);
            statement.addBatch(deleteResponsesSQL);
            int[] results = statement.executeBatch();
            System.out.println(Arrays.toString(results));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




    public void searchQuestionByTopic(String topic) {
        String searchQuestionSQL = "SELECT * FROM questions WHERE topic = '" + topic + "'";
        System.out.println(searchQuestionSQL);
        try(Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement()) {
            ResultSet results = statement.executeQuery(searchQuestionSQL);
            while(results.next()){
                //Display values
                System.out.print("Question ID: " + results.getInt("questionid"));
                System.out.print(", Topic: " + results.getString("topic"));
                System.out.print(", Difficulty: " + results.getInt("difficulty"));
                System.out.println(", Content: " + results.getString("content"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
