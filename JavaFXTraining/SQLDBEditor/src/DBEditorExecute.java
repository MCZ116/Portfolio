import biblioteka.Kontroler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DBEditorExecute extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Kontroler kontroler = new Kontroler();
        Parent root = FXMLLoader.load(getClass().getResource("/biblioteka/databaseprogram.fxml"));
        primaryStage.setTitle("DataBaseEditor by Mateusz Czerwiński");
        primaryStage.setScene(new Scene(root, 570, 304));
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}