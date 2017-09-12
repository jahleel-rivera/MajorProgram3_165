/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import java.io.File;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author exchange
 */
public class GamePane extends BorderPane {
    
        class StatusPane extends HBox{
            private int turn;
            Label label = new Label("Turns: ");
            
            public void newTurns(){
                turn++;
                label.setText("Turns: " + turn);
            }
            
            public void resetTurns(){
                turn = 0;
                label.setText(("Turns: " + turn));
                
            }
            
            StatusPane(){
                this.getChildren().addAll(label);
            }
        }
        class CommandPane extends HBox{
        Button exit = new Button("Exit");
        Button nGame = new Button("New Game");
        
        ComboBox<String> level = new ComboBox();
        
        
        CommandPane(){
            this.setSpacing(20);
            
            this.getChildren().addAll(exit, nGame, level);
            level.getItems().addAll("Level 1", "Level 2", "Level 3", "level 4", "Level 5", "Level 6");
            
            exit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event){
                    System.exit(0);
                }
        }
        );
            nGame.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent e){
                    newGame();
                }
        }
        );
            
            level.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){
               public void changed(ObservableValue observable, String oldValue, String newValue){
                   if(newValue.equals("level 1")){
                       rows = 2;
                       cols = 3;
                   }
                   else if(newValue.equals("Level 2")){
                       rows = 2;
                       cols = 4;
                   }
                   else if(newValue.equals("Level 3")){
                       rows = 4;
                       cols = 4;
                   }
                   else if(newValue.equals("Level 4")){
                       rows = 4;
                       cols = 6;
                   }
                   else if(newValue.equals("Level 5")){
                       rows = 6;
                       cols = 6;
                       
                   }
                   else if(newValue.equals("Level 6")){
                       rows = 8;
                       cols = 8;
                   }
                   newGame();
               }
        });
        level.getSelectionModel().selectFirst();
        }
        }

    private int rows;
    private int cols;
    private int numClicks;
    private int numMatched;
    private Card clickedCardOne;
    private Card clickedCardTwo;
    private CardGridPane cardGridPane;
    private CommandPane commandPane;
    private StatusPane statusPane;
    
    public GamePane(){
        rows = 8;
        cols = 8;
        numClicks = 0;
        numMatched = 0;
        clickedCardOne = null;
        clickedCardTwo = null;
        cardGridPane = new CardGridPane();
        cardGridPane.initCards(rows, cols);
        statusPane = new StatusPane();
        commandPane = new CommandPane();
        this.setCenter(cardGridPane);
        this.setBottom(commandPane);
        this.setTop(statusPane);
    }

    /**
     * @return the rows
     */
    public int getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(int rows) {
        this.rows = rows;
    }

    /**
     * @return the cols
     */
    public int getCols() {
        return cols;
    }

    /**
     * @param cols the cols to set
     */
    public void setCols(int cols) {
        this.cols = cols;
    }

    /**
     * @return the numClicks
     */
    public int getNumClicks() {
        return numClicks;
    }

    /**
     * @param numClicks the numClicks to set
     */
    public void setNumClicks(int numClicks) {
        this.numClicks = numClicks;
    }

    /**
     * @return the numMatched
     */
    public int getNumMatched() {
        return numMatched;
    }

    /**
     * @param numMatched the numMatched to set
     */
    public void setNumMatched(int numMatched) {
        this.numMatched = numMatched;
    }

    /**
     * @return the clickedCardOne
     */
    public Card getClickedCardOne() {
        return clickedCardOne;
    }

    /**
     * @param clickedCardOne the clickedCardOne to set
     */
    public void setClickedCardOne(Card clickedCardOne) {
        this.clickedCardOne = clickedCardOne;
    }

    /**
     * @return the clickedCardTwo
     */
    public Card getClickedCardTwo() {
        return clickedCardTwo;
    }

    /**
     * @param clickedCardTwo the clickedCardTwo to set
     */
    public void setClickedCardTwo(Card clickedCardTwo) {
        this.clickedCardTwo = clickedCardTwo;
    }

    public void newGame(){
        cardGridPane.initCards(rows, cols);
        registerCardListeners();
        numMatched = 0;
        statusPane.resetTurns();
    }
    public void registerCardListeners(){
        for(int i = 0; i < cardGridPane.getMAXROWS(); i++){
            for(int j = 0; j < cardGridPane.getMAXCOLS(); j++){
                Card card = cardGridPane.getCard(j,i);
                card.setOnMousePressed(new EventHandler(){
                    @Override
                    public void handle(Event event){
                        if(!card.isFlipped() && !card.isMatched() && clickedCardTwo == null && !card.isCardDisabled()){
                             card.setFlipped(true);
                             card.flipCard();
                             if(numClicks == 0){
                                 numClicks++;
                                 clickedCardOne = card;
                             }else if(numClicks == 1){
                                 numClicks++;
                                 clickedCardTwo = card;
                             
                                 AnimationTimer time = new AnimationTimer(){
                                     long start = System.nanoTime();
                                     
                                     @Override
                                     public void handle(long now){
                                         if(now - start >= 800000000){
                                             if(clickedCardOne.getPath().equals(clickedCardTwo.getPath())){
                                                 clickedCardOne.setMatched(true);
                                                 clickedCardTwo.setMatched(true);
                                                 clickedCardOne.setStyle("-fx-background-color: BLACK");
                                                 clickedCardTwo.setStyle("-fx-background-color: BLACK");
                                                 clickedCardOne.getChildren().clear();
                                                 clickedCardTwo.getChildren().clear();
                                                 numMatched++;
                                                 
                                                 String beep = new File("Winner Bell Game Show SOUND Effect.mp3").toURI().toString();
                                                 Media sound1 = new Media(beep);
                                                 MediaPlayer mediaPlayer = new MediaPlayer(sound1);
                                                 mediaPlayer.play();
                                             }else{
                                                 clickedCardOne.setFlipped(false);
                                                 clickedCardTwo.setFlipped(false);
                                                 clickedCardOne.flipCard();
                                                 clickedCardTwo.flipCard();
                                                 
                                                 String buzz = new File("wrong answer-sound effect.mp3").toURI().toString();
                                                 Media sound2 = new Media(buzz);
                                                 MediaPlayer mediaPlayer = new MediaPlayer(sound2);
                                                 mediaPlayer.play();
                                             }
                                             clickedCardOne = null;
                                             clickedCardTwo = null;
                                             numClicks = 0;
                                             
                                             this.stop();
                                         }
                                     }
                        
                    };
                                 time.start();
                                 statusPane.newTurns();
                             }
                        }
                        else{
                            return;
                        }
                    }
                });
            }
        }
        
    }
    
}
