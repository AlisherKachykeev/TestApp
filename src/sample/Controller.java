package sample;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Controller{


    @FXML
    private Button closeBtn;

    @FXML
    private RadioButton radioBtn1;

    @FXML
    private ToggleGroup answers;

    @FXML
    private RadioButton radioBtn2;

    @FXML
    private RadioButton radioBtn3;

    @FXML
    private RadioButton radioBtn4;

    @FXML
    private Text questionText;

    @FXML
    private Button answerBtn;

    @FXML public void onMouseClickedcloseBtn(MouseEvent mouseEvent) {
        final Node source = (Node) mouseEvent.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    LocalDateTime myDate = LocalDateTime.now();
    DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDate = myDate.format(myFormat);

    private Questions[] questions = new Questions[] {
            new Questions("I ______ bus on Mondays.", new String[] {"'m going to work with","'m going to work by", "go to work with","go to work by"}),
            new Questions("Sorry, but this chair is ______.", new String[] {"me", "our", "my", "mine"}),
            new Questions("I ______ to the cinema.", new String[] {"not usually go", "do not go usually", "don't go usually", "don't usually go"}),
            new Questions("Where ______ ?", new String[] {"your sister works", "your sister work", "do your sister work", "does your sister work"}),
            new Questions("The test is ______ February.", new String[] {"over", "at", "in", "on"}),
            new Questions("I don't have ______ free time.", new String[] {"many", "some", "a lot", "any"}),
            new Questions("'_____ to the cinema tomorrow?'", new String[] {"shall we go", "do we go", "we go", "we will go"}),
            new Questions("We went to the market ______ some vegetables.", new String[] {"for buying", "for buy", "for to buy", "to buy"}),
            new Questions("Sorry, but when you called I ______ a shower.", new String[] {"had", "did have", "were having", "was having"}),
            new Questions("______ with me?.", new String[] {"Do you like to dance", "Would you like to dancing", "Do you like to dance", "Would you like to dance"})
    };
    private int nowQuestion = 0, correctAnswers;

    private String nowCorrectAnswer = questions[nowQuestion].correctAnswer();

    @FXML
    void initialize() {
        answerBtn.setOnAction(event -> {
            RadioButton selectedRadioButton = (RadioButton) answers.getSelectedToggle();
            if (selectedRadioButton != null) {
                String toggleGroupValue = selectedRadioButton.getText();

                if (toggleGroupValue.equals(nowCorrectAnswer)) {
                    System.out.println("Correct answer");
                    correctAnswers++;
                } else
                    System.out.println("Incorrect answer");


                if (nowQuestion + 1 == questions.length) {
                    radioBtn1.setVisible(false);
                    radioBtn2.setVisible(false);
                    radioBtn3.setVisible(false);
                    radioBtn4.setVisible(false);
                    answerBtn.setVisible(false);

                    questionText.setText("You answered on " + correctAnswers + " out of " + questions.length + " questions! \n\n\n\n\n\n\n\n\n\n\n" + formattedDate);
                } else {
                    nowQuestion++;
                    nowCorrectAnswer = questions[nowQuestion].correctAnswer();
                    questionText.setText(questions[nowQuestion].getQuestion());
                    String[] answers = questions[nowQuestion].getAnswers();

                    List<String> intList = Arrays.asList(answers);

                    Collections.shuffle(intList);

                    radioBtn1.setText(intList.get(0));
                    radioBtn2.setText(intList.get(1));
                    radioBtn3.setText(intList.get(2));
                    radioBtn4.setText(intList.get(3));


                    selectedRadioButton.setSelected(false);
                }
            }
        });
    }
}

