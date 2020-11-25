/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pongdaniyaraubekerov;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Daniyar
 */
public class PongDaniyarAubekerov extends Application {

    @Override
    public void start(Stage primaryStage) {
        Paddle ballPane = new Paddle(); //Creating a new Paddle
        
        ballPane.setOnMouseDragged((MouseEvent e) -> {
            ballPane.setX(e.getX()); //paddle movement with mouse
        });
       
        
        
        Scene scene = new Scene(ballPane, 600, 400); //window size
        primaryStage.setTitle("Pong Game"); // Title name
        primaryStage.setScene(scene); // scene->stage
        primaryStage.show(); // Show stage
        

    }

    public final class Paddle extends Pane {

        private final Timeline animation; //ball movement
        
        final int HEIGHT = 400; //constant height
        final int WIDTH = 600; //constant width

        
        int curScore = 20; // current score (20 by default)
        int touch = 0; // when the ball touches the paddle we will be incrementing int

        HBox hscore = new HBox();
        public int radius = 18;
        private double x = radius;
        private double y = radius;
        private double dx = 1, dy = 1;
       
        //parameters of width, height and x,y;
        public double height = 20, width = 40;
        private final double xRectangle = WIDTH - HEIGHT;          // Initial X position
        private final double yRectangle = HEIGHT - height * 2;         // Initial Y position 

        
       
        
        public Label textScore = new Label("\t\t\t\t\t\t\tScore: " + (curScore));  //displaing the score of the game

        private final Rectangle rect = new Rectangle(xRectangle, yRectangle, width, height); //paddle

        // create the ball
        private final Circle circle = new Circle(x, y, radius); //ball

        public Paddle() {
            
      
            

            
            rect.setFill(Color.PINK);// paddle collor

            textScore.setTextFill(Color.BLACK); //text color
            textScore.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 20)); //score style
            hscore.setAlignment(Pos.CENTER); 
            hscore.setSpacing(8);

            hscore.getChildren().addAll(textScore);
            
            this.getChildren().addAll(hscore, circle, rect);

            //animations
            animation = new Timeline(new KeyFrame(Duration.millis(15), (ActionEvent e) -> {
                moveBall();
            }));
            animation.setCycleCount(Timeline.INDEFINITE);
            
            animation.play(); // Start the balls movement
            }
        private void setBallColor(){
                if(touch % 3 == 0){circle.setFill(Color.RED);} //change color every third touch
                else {circle.setFill(Color.GREEN);} //green by default
            }

        private void moveBall() {
            setBallColor();
            if (y < radius) {
                dy = dy * -1;
            }
            if (x < radius || x > WIDTH - radius) {
                dx = dx * -1;
            }
            if ((y + radius) >= rect.getY() && x <= (rect.getX() + width) && rect.getX() <= x) {
                touch++;
                dy = dy * -1;
                if (touch % 10 == 0) {
                    animation.setRate(animation.getRate() + 0.1); //increase the balls speed every 10 touches
                }
                
                
                
            } else {
                if ((y + radius) > HEIGHT) {
                    curScore--;
                    if (curScore>0){textScore.setText("Score: " + (curScore));}
                    else {
                        textScore.setText("\t\t\tYou LOST but"+ " you can keep playing for fun");
                    }
                    if (curScore % 2 == 0) {
                        width = width * 2;
                        rect.setWidth(width);
                    }
                    y = radius;
                    x = radius;
                    circle.setCenterX(x);
                    circle.setCenterY(y);

                }
            }
            x = x + dx;
            y = y + dy;
            circle.setCenterX(x);
            circle.setCenterY(y);
        }

        public void setX(double x) {
            if (x < 0) {
                x = 0;
                
            }
            if (WIDTH - width < x) {
                x = WIDTH - width;
            }
            rect.setX(x);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
