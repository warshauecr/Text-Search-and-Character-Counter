/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package charactercountgui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos; 
/**
 *
 * @author Casey Warshauer
 */
public class CharacterCountGui extends Application {
    
    private BorderPane rootPane;
    private VBox vPane;
    private HBox buttonPanel;
    private VBox bottomPanel;
    private Label searchLabel;
    private Label searchText;
    private TextField textBox;
    private TextField searchBox;
    private TextArea resultsBox;
    private Button searchButton;
    private Button clearButton;
    private Button exitButton;
            
    
    @Override
    public void start(Stage primaryStage) throws Exception
    {
    //create panels   
    createPanels();
    
    //create labels and text fields
    createLablesFields();
  
   //create buttons
    createButtons();
  
   //Add buttons and panels
   addButtonsPanels();
   
   //create scene and set stage
   createSceneSetStage(primaryStage);
      
   //add action handlers to buttons
    addActionHandler();      
    }
    /*
    Creates panels
    */
    private void createPanels()
    {
    //create root pane   
   rootPane = new BorderPane();
    
    //create VBox panel fot lable and input box.
   vPane = new VBox(10);
   vPane.setFillWidth(false);
    
     //create button Panel.
    buttonPanel = new HBox(5);
    
    //create VBox panel for resultsBox and button
    bottomPanel = new VBox(5);
    }
    
    /*
    Create labels and Text fields
    */
    private void createLablesFields()
    {
     //create Labels
    searchLabel = new Label("Enter text:");
    searchText = new Label("Enter letter to search for:");
    
    //Create text fields
    textBox = new TextField();
    textBox.setPrefColumnCount(20);
    searchBox = new TextField();
    searchBox.setPrefColumnCount(1);
    
      //Create result textArea box.
    resultsBox = new TextArea();
    resultsBox.setEditable(false);
    }
    
    /*
    Create buttons
    */
    private void createButtons()
    {
        //Create search button
    searchButton = new Button("Search");
    
    //create clear button
    clearButton = new Button("Clear");
    
    //Create Exit button
    exitButton = new Button("Exit");
    }
    
    /*
    Adds all buttons and panels
    */
    private void addButtonsPanels()
    {
     //add vPanel
    rootPane.setCenter(vPane);
    
    //add components to vPanel
    vPane.getChildren().addAll(searchLabel, textBox, searchText, searchBox);
    vPane.setAlignment(Pos.CENTER);
    
    //Add bottom panel and resultsBox and button
    bottomPanel.getChildren().addAll(resultsBox, buttonPanel);
    bottomPanel.setFillWidth(false);
    buttonPanel.getChildren().addAll(searchButton, clearButton, exitButton);
    bottomPanel.setAlignment(Pos.CENTER);
    rootPane.setBottom(bottomPanel);
    }
    
    /*
    Creates scene and sets stage.
    */
    private void createSceneSetStage(Stage primaryStage)
    {
        Scene scene = new Scene(rootPane, 600, 350);
        primaryStage.setTitle("Text Search Letter Counter");
        primaryStage.setScene(scene);
        primaryStage.show();
    
    }
    /*
    Create and add action handlers
    */
    private void addActionHandler()
    {
    //add handler to searchButton
    searchButton.setOnAction(
            event ->
    {
        int result;
        String inputResult;
        char searchResult;
        
        inputResult = textBox.getText().toLowerCase();
        searchResult = searchBox.getText().toLowerCase().charAt(0);
        
        result = converter(inputResult, searchResult);
        resultsBox.setText("You searched for: " + searchResult + "\n" + result + " results(s) were found.");
        
    });
      //add action action handler to clear button
      clearButton.setOnAction(
      event ->
      {
      textBox.setText("");
      searchBox.setText("");
      resultsBox.setText("");
      });  
      
      //add action handler to exitButton
      exitButton.setOnAction(
              event ->
              {
                  System.exit(0);
              }
      
      );
    }
    
    /*
    The converter method convert any give input text to characters.
    It then calls the charCounter
    */
    
    private static int converter(String inputResult, char searchResult)
    { 
       final int START = 0;
       int result;
       char[] inputArray =  inputResult.toCharArray();
       
       result = charCounter(inputArray, searchResult, START);
       return result;
    }
    
    /*
    The charCounter method uses method recursion
    to search any given text provided by the user
    for any given character.
    */
    
    private static int charCounter(char[] array, char search, int index)
   {
      //Base Case     
       if (index >= array.length)
       {
           return 0;
       }
       else if (array[index] == search)
           return 1 + charCounter(array, search, index + 1) ;
     
       else
       {
       return charCounter(array, search, index + 1);
       }      
   }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
