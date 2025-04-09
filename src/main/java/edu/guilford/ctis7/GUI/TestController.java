package edu.guilford.ctis7.GUI;

import edu.guilford.ctis7.Backend.ApplicationInstance;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

import java.awt.event.TextEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestController {

    @FXML private Button fileButton;

    @FXML private Button generateButton;

    @FXML private TextField phraseStartWordField;
    @FXML private TextField numberOfWordsField;


    @FXML private Label phraseOutput;

    @FXML
            public void initialize() {


        fileButton.setOnMouseClicked(this::handleFileSelect);
        generateButton.setOnMouseClicked(this::handleGeneratePhrase);


    }



    @FXML
    private void handleGeneratePhrase(MouseEvent event) {
        String phraseStart = phraseStartWordField.getText();
        int numberOfWords = Integer.parseInt(numberOfWordsField.getText());
        String generatedPhrase = ApplicationInstance.getInstance().generatePhrase(numberOfWords, phraseStart);
        phraseOutput.setText(generatedPhrase);
    }

    @FXML
    private void checkValidityNumberField(TextEvent event) {

        String text = ((TextField) event.getSource()).getText();
        if (!text.matches("\\d*")) {
            ((TextField) event.getSource()).setText(text.replaceAll("\\D", ""));
        }
    }


    @FXML
    private void handleFileSelect(MouseEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        List<File> selectedFiles = fileChooser.showOpenMultipleDialog(((Node) event.getSource()).getScene().getWindow());
        if (selectedFiles != null) {

            ArrayList<String> filePaths = new ArrayList<>();
            for (File file : selectedFiles) {

                filePaths.add(file.getAbsolutePath());

            }

            System.out.println(filePaths.size());


            //TODO fix this behavior-- perhaps on instantiation of appInstance?
            ApplicationInstance.getInstance().setFilePaths(filePaths);
            ApplicationInstance.getInstance().createAllWordsFromTexts();
            ApplicationInstance.getInstance().createTwoGram();
        }
    }

}
