package utility;

import UI.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import session.UserSession;

import java.io.IOException;

public class SceneChangerUtility {

    private Stage stage;
    private Scene scene;
    private Parent root;

    public void changeScene(String choice, ActionEvent event) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/" + choice + ".fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }

        // Here we change the scene to Transaction scene/page
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        switch (choice) {
            case "Show All":
                ShowAllController controller = loader.getController();
                controller.load();
                break;
            case "Yearly":
                YearlyTransactionsController yearly = loader.getController();
                yearly.load();
                break;
            case "Monthly":
                MonthlyTransactionsController monthly = loader.getController();
                monthly.load();
                break;
            case "Weekly":
                WeeklyTransactionsController weekly = loader.getController();
                weekly.load();
                break;
            case "Daily":
                DailyTransactionsController daily = loader.getController();
                daily.load();
                break;
            case "Balance":
                BalanceController balance = loader.getController();
                balance.load();
                break;
            case "Sign Out":
                UserSession.setId(0);
                UserSession.setUsername("");

                System.out.println("User signed out");
                System.out.println("Session: " +  UserSession.getUsername());
                System.out.println("Session ID: " + UserSession.getId());
            default:
                break;
        }

    }
}
