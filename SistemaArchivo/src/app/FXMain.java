/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Yorch
 */
public class FXMain extends Application
{
    
    @Override
    public void start(Stage stage) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("visual/FXMLPrincipal.fxml"));
        Scene scene = new Scene(root);
        
        stage.setTitle("Club de tenis Echizen");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
}
