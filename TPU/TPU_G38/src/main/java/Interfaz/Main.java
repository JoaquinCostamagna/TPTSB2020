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
        primaryStage.setScene(new Scene(root, 665, 523));
        primaryStage.show();
        try {
            Image ico = new Image("banderaArgentina.png");
            primaryStage.getIcons().add(ico);
        }
        catch (Exception ex)
        {
            System.out.println("No se encontro la imagen");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}