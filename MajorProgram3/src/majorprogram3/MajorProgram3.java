/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package majorprogram3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author exchange
 */
public class MajorProgram3 extends Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
       GamePane pg = new GamePane();
       Scene scene = new Scene(pg);
       
       primaryStage.setTitle("Concentration");
       primaryStage.setScene(scene);
       primaryStage.show();
    }
    
}
