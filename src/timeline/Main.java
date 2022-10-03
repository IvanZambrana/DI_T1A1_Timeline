/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package timeline;

import com.sun.javafx.perf.PerformanceTracker;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Ivan
 */

public class Main extends Application {
    public static double ballSpeedX= 1;
    public static double ballSpeedY= 1;

    @Override
    public void start(Stage primaryStage) {
        
        //Setting Title
        primaryStage.setTitle("TimeLine");
        
        //Creating a new group
        Group pane = new Group();
        
        //Ball
        Circle ball = new Circle(10);
        ball.setTranslateX(300 * 0.5);
        ball.setTranslateY(300 * 0.5);
        pane.getChildren().addAll(ball);
        
        //Creating a label (FPS)
        Label label = new Label();
        label.setTranslateX(10);
        label.setTranslateY(10);
        pane.getChildren().addAll(label);
        
       //Creating a new scene
        Scene scene = new Scene(pane, 300, 250);
        
        //EventHandler
        EventHandler<ActionEvent> eH = e ->{
            //Show FPS
            PerformanceTracker perfTracker = PerformanceTracker.getSceneTracker(scene);
            label.setText("FPS (Timeline) = " + perfTracker.getInstantFPS());
            
            // Change the direction of the ball if it reaches the extremes
            if(ball.getTranslateX() < 10 || ball.getTranslateX() > 280){
                ballSpeedX*=-1;
            } else if(ball.getTranslateY()< 10 || ball.getTranslateY()> 230){
                ballSpeedY*=-1;
            } else {
                ballSpeedX = 1;
                ballSpeedY = 1;
                ballSpeedX *= ((int) (Math.random() * 15)) - 1;
                ballSpeedY *= ((int) (Math.random() * 15)) - 1;
            }
            //Moving the ball
            ball.setTranslateY(ball.getTranslateY()+ballSpeedY);
            ball.setTranslateX(ball.getTranslateX()+ballSpeedX);
        };
        
        //Timeline
        Timeline tl = new Timeline(new KeyFrame(Duration.millis(500), eH));
        tl.setCycleCount(Timeline.INDEFINITE);
        tl.play();
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
