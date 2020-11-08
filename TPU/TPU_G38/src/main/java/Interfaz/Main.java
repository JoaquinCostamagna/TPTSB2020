package Interfaz;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("principal.fxml"));
        primaryStage.setTitle("Elecciones PASO 2019");
        primaryStage.setMinWidth(715);
        primaryStage.setMaxWidth(715);
        primaryStage.setMinHeight(590);
        primaryStage.setMaxHeight(590);
        primaryStage.setScene(new Scene(root, 700, 550));
        primaryStage.show();
        try {
            Image ico = new Image("banderaArgentina.png");
            primaryStage.getIcons().add(ico);
        }
        catch (Exception ex)
        {
            System.out.println("No se encontró la imagen.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}