package com.chrono.desktop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.shape.Arc;
import javafx.util.Duration;

import java.io.IOException;
import java.lang.System.Logger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrimaryController {

    private static final Logger logger = System.getLogger(PrimaryController.class.getName());

    @FXML private TextField taskObjectiveInput;
    @FXML private Label timerLabel;
    @FXML private Arc progressArc;
    @FXML private ListView<String> historyListView;
    
    @FXML private Button startBtn;
    @FXML private Button pauseBtn;
    @FXML private Button resetBtn;

    private Timeline timeline;
    private static final int TOTAL_SECONDS = 90 * 60; // 90-minute block
    private int remainingSeconds = TOTAL_SECONDS;

    // Dynamically grabs the folder where the app/exe is running
    private final Path csvPath = Paths.get(System.getProperty("user.home"), "Documents","tyr_ledger.csv");

    @FXML
    public void initialize() {
        updateUI();
        loadLedger();
    }

    @FXML
    private void startTimer(ActionEvent event) {
        if (timeline == null) {
            timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
                remainingSeconds--;
                updateUI();
                
                if (remainingSeconds <= 0) {
                    timeline.stop();
                    timerLabel.setText("00:00");
                    logObjectiveToCSV();
                }
            }));
            timeline.setCycleCount(Timeline.INDEFINITE);
        }
        
        taskObjectiveInput.setDisable(true); 
        timeline.play();
    }

    @FXML
    private void pauseTimer(ActionEvent event) {
        if (timeline != null) timeline.pause();
    }

    @FXML
    private void resetTimer(ActionEvent event) {
        if (timeline != null) timeline.stop();
        remainingSeconds = TOTAL_SECONDS;
        logObjectiveToCSV();
        taskObjectiveInput.setDisable(false);
        taskObjectiveInput.clear();
        updateUI();
    }

    private void updateUI() {
        int minutes = remainingSeconds / 60;
        int seconds = remainingSeconds % 60;
        
        timerLabel.setText(String.format("%02d:%02d", minutes, seconds));
        
        // Progress arc operates on a 360-degree scale.
        // As time decreases, the length of the arc shrinks.
        double progress = (double) remainingSeconds / TOTAL_SECONDS;
        progressArc.setLength(360.0 * progress);
    }

    private void loadLedger() {
        try {
            if (Files.exists(csvPath)) {
                List<String> records = Files.readAllLines(csvPath);
                historyListView.getItems().addAll(records);
                logger.log(Logger.Level.INFO, "Ledger loaded successfully from {0}", csvPath);
            }
        } catch (IOException e) {
            logger.log(Logger.Level.ERROR, "Failed to read CSV ledger during initialization.", e);
        }
    }

    private void logObjectiveToCSV() {
        String objective = taskObjectiveInput.getText().trim();
        if (objective.isEmpty()) objective = "Deep Work Block Completed";

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        String csvRecord = timestamp + "," + objective;

        // Update the UI immediately
        historyListView.getItems().add(0, csvRecord);

        // Write directly to the execution directory
        try {
            Files.writeString(csvPath, csvRecord + System.lineSeparator(), 
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    logger.log(Logger.Level.INFO, "Logged objective to ledger: {0}", csvRecord);
        } catch (IOException e) {
            logger.log(Logger.Level.ERROR, "Failed to write to ledger: " + e.getMessage());
        }
    }
}

// This is where I stopped Running away.
// From now on, I will not let fear dictate my actions.
// This is my Reckoning.