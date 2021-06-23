/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package finalgamesnap;

import static finalgamesnap.ClientConnection.out;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.HBox;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ClientGUI extends Application {

    // ClientConnection cc;
    static Button btSnap = new Button("Snap!");
    private Button btexit = new Button("Exit Game");
    private Button btplayagain = new Button("Play again");
    private Text p1 = new Text();
    private Text p2 = new Text();
    private Text timer = new Text();
    static TextArea cd1;
    static TextArea cd2;

    @Override
    public void start(Stage primaryStage) throws IOException {

        Pane root = new Pane();
        root.setPrefSize(700, 600);

        Region background = new Region();
        background.setPrefSize(800, 600);
        background.setStyle("-fx-background-color: rgba(0, 0, 0, 1)");

        VBox rootLayout1 = new VBox(5);
        rootLayout1.setPadding(new Insets(5, 5, 5, 5));

        HBox rootLayout2 = new HBox(5);
        rootLayout2.setPadding(new Insets(5, 5, 5, 5));

        Rectangle heading = new Rectangle(780, 100);
        heading.setArcWidth(50);
        heading.setArcHeight(50);
        heading.setFill(Color.LIGHTGREY);

        Rectangle footer = new Rectangle(780, 100);
        footer.setArcWidth(50);
        footer.setArcHeight(50);
        footer.setFill(Color.LIGHTGREY);

        Rectangle leftBG = new Rectangle(550, 360);
        leftBG.setArcWidth(50);
        leftBG.setArcHeight(50);
        leftBG.setFill(Color.GREEN);

        Rectangle rightBG = new Rectangle(230, 360);
        rightBG.setArcWidth(50);
        rightBG.setArcHeight(50);
        rightBG.setFill(Color.ORANGE);
        //TOP

        //HEADING
        HBox titlebox = new HBox();
        titlebox.setAlignment(Pos.CENTER);
        Text title = new Text("GoSnap!");
        titlebox.getChildren().addAll(title);

        // LEFT
        VBox leftVBox = new VBox();
        leftVBox.setAlignment(Pos.CENTER);
        //prevcard
        Rectangle pc = new Rectangle(150, 200);
        pc.setArcWidth(50);
        pc.setArcHeight(50);
        pc.setFill(Color.WHITE);

        VBox details1 = new VBox();
        Text prevcard = new Text("Previous Card: ");
        cd1 = new TextArea();
        double height1 = 100;
        double width1 = 100;
        cd1.setPrefHeight(height1);
        cd1.setPrefWidth(width1);

        details1.getChildren().addAll(prevcard, cd1);
        details1.setAlignment(Pos.CENTER);

        //topcard
        Rectangle tc = new Rectangle(150, 200);
        tc.setArcWidth(50);
        tc.setArcHeight(50);
        tc.setFill(Color.WHITE);

        VBox details2 = new VBox();
        Text topcard = new Text("Top Card: ");
        cd2 = new TextArea();
        double height2 = 100;
        double width2 = 100;
        cd2.setPrefHeight(height2);
        cd2.setPrefWidth(width2);

        details2.getChildren().addAll(topcard, cd2);
        details2.setAlignment(Pos.CENTER);

        StackPane c1 = new StackPane(pc, details1);
        StackPane c2 = new StackPane(tc, details2);

        HBox cards = new HBox(30);
        cards.getChildren().addAll(c1, c2);
        cards.setAlignment(Pos.CENTER);
        cards.setPadding(new Insets(5, 5, 5, 5));
        leftVBox.getChildren().addAll(cards);

        // RIGHT
        VBox rightVBox = new VBox(20);
        rightVBox.setAlignment(Pos.CENTER);

        GridPane gridPane = new GridPane();
        gridPane.add(new Label("Player 1 Points: "), 0, 0);
        gridPane.add(p1, 1, 0);
        gridPane.add(new Label("Player 2 Points: "), 0, 1);
        gridPane.add(p2, 1, 1);
        gridPane.add(new Label("Timer:"), 0, 2);
        gridPane.add(timer, 1, 2);

        rightVBox.getChildren().addAll(gridPane);

        //bottom
        HBox buttonsHBox = new HBox(15, btSnap, btplayagain, btexit);
        buttonsHBox.setAlignment(Pos.CENTER);

        StackPane h = new StackPane(heading, titlebox);
        StackPane lh = new StackPane(leftBG, leftVBox);
        StackPane rh = new StackPane(rightBG, rightVBox);
        StackPane f = new StackPane(footer, buttonsHBox);

        HBox headbox = new HBox(h);
        HBox footbox = new HBox(f);

        // ADD BOTH STACKS TO ROOT LAYOUT
        rootLayout2.getChildren().addAll(lh, rh);
        rootLayout1.getChildren().addAll(headbox, rootLayout2, footbox);
        root.getChildren().addAll(background, rootLayout1);

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("GoSnap");
        primaryStage.setScene(scene);
        primaryStage.show();

        //snap button in Client Connection
        //play again
        btSnap.setOnAction(e -> {

            try {
                
                out.writeObject("snap");
                ClientConnection.Snapped();
                
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            } catch (ClassNotFoundException ex) {
                System.out.println("Error: " + ex);
            }
        });
        btplayagain.setOnAction(e -> {
            ClientConnection.playgame();
        });

        btexit.setOnAction(e -> {
            ClientConnection.close();

        });
    }

    public static void main(String[] args) {
        launch(args);
        ClientGUI Client = new ClientGUI();

    }

    public ClientGUI() {
        Socket s = null;

        try {
            s = new Socket("localhost", 1000);

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println();
        ClientConnection c1 = new ClientConnection(s);
        c1.start();
        //System.out.println("Connection made!!");

    }
       
}
