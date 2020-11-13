package biblioteka;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Student;
import model.DBConnector;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Kontroler implements Initializable {

    @FXML
    private Button closeButton;

    @FXML
    private Button addUser;

    @FXML
    private TextField imieTextField,  peselTextField, nazwiskoTextField;

    @FXML
    private TableView<Student> dataTable;

    @FXML
    private TableColumn<Student, String> co_imie;

    @FXML
    private TableColumn<Student, String> co_nazwisko;

    @FXML
    private TableColumn<Student, String> co_pesel;

    @FXML
    private TableColumn<Student, Integer> co_id;

    ObservableList<Student> oblist = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resourceBundle) {

        try {
            Connection con = DBConnector.getConnection();

            ResultSet resultSet = con.createStatement().executeQuery("select * from studenci");

            while (resultSet.next()){

                oblist.add(new Student(resultSet.getInt("id_studenta"), resultSet.getString("imie"), resultSet.getString("nazwisko"), resultSet.getString("pesel")));
            }

        } catch (SQLException ex) {
          Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Błąd");
        }

        co_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        co_imie.setCellValueFactory(new PropertyValueFactory<>("imie"));
        co_nazwisko.setCellValueFactory(new PropertyValueFactory<>("nazwisko"));
        co_pesel.setCellValueFactory(new PropertyValueFactory<>("pesel"));

        dataTable.setItems(oblist);

        addUser.disableProperty().bind(
                Bindings.isEmpty(imieTextField.textProperty())
                        .or(Bindings.isEmpty(nazwiskoTextField.textProperty()))
                        .or(Bindings.isEmpty(peselTextField.textProperty()))
        );
    }

    public void addUser(ActionEvent event) throws SQLException {
        Biblioteka b = new Biblioteka();
        String imie = imieTextField.getText();
        String nazwisko = nazwiskoTextField.getText();
        String pesel = peselTextField.getText();
        b.insertStudent(imie, nazwisko,pesel);
        oblist.clear();
        update();

    }

    public void deleteUser(ActionEvent event) throws SQLException {
        Biblioteka biblioteka = new Biblioteka();
        Student student = dataTable.getSelectionModel().getSelectedItem();
        int id = student.getId();
        biblioteka.deleteStudent(id);
        System.out.println("selectedIndex"+id);
        oblist.clear();
        update();
    }

    @FXML
    public void handleCloseButton(ActionEvent event){
        Biblioteka biblioteka = new Biblioteka();
        biblioteka.closeConnection();
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void createDBFile(ActionEvent event){
      Biblioteka biblioteka = new Biblioteka();
      biblioteka.createTables();
    }

    public void update() throws SQLException {

        Connection con = DBConnector.getConnection();

        ResultSet resultSet = con.createStatement().executeQuery("select * from studenci");

        while (resultSet.next()){
            oblist.add(new Student(resultSet.getInt("id_studenta"), resultSet.getString("imie"), resultSet.getString("nazwisko"), resultSet.getString("pesel")));

    }
}
}
