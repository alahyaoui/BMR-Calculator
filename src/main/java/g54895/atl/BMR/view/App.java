package g54895.atl.BMR.view;

import g54895.atl.BMR.model.Activity;
import static g54895.atl.BMR.model.Activity.ACTIVE;
import static g54895.atl.BMR.model.Activity.EXTREMELY_ACTIVE;
import static g54895.atl.BMR.model.Activity.NOT_ACTIVE;
import static g54895.atl.BMR.model.Activity.SEDENTARY;
import static g54895.atl.BMR.model.Activity.STRONG_ACTIVE;
import g54895.atl.BMR.model.Calculator;
import java.beans.PropertyChangeEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.function.UnaryOperator;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.StageStyle;
import java.beans.PropertyChangeListener;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * JavaFX App
 */
public class App extends Application implements PropertyChangeListener {

    private static Scene scene;
    private static Calculator calculator;
    private TextField field1, field2, field3, field21, field22;
    private ChoiceBox choiceBox;
    private RadioButton radioButton1, radioButton2;

    private double bmr, calories;

    private XYChart.Series menSeries1, womenSeries1, menSeries2, womenSeries2, menSeries3, womenSeries3;

    @Override
    public void start(Stage primaryStage) throws Exception {
        calculator = new Calculator();
        calculator.addPropertyChangeListener(this);
        primaryStage.setTitle("Calcul de BMR");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setMinWidth(300);
        primaryStage.setMinHeight(200);

        VBox root = new VBox();
        HBox branch = new HBox();
        VBox mainLeaf = new VBox();
        VBox subBranch = new VBox();

        HBox twig = new HBox();
        GridPane subBranchLeaf1 = new GridPane();
        GridPane subBranchLeaf2 = new GridPane();

        //MENU BAR OF ROOT
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("File");
        MenuItem itemExit = new MenuItem("Exit");
        itemExit.setOnAction(event -> {
            primaryStage.close();
        });
        menuFile.getItems().addAll(itemExit);//Add the new item to the menu file
        menuBar.getMenus().addAll(menuFile);//Add the new menu to the menu bar

        //TWIG
        //LEAF1 OF TWIG
        subBranchLeaf1.setPadding(new Insets(20));
        subBranchLeaf1.setHgap(10);
        subBranchLeaf1.setVgap(15);
        //title of leaf 1
        Label leaf1Title = new Label("Données");
        leaf1Title.setUnderline(true);
        GridPane.setHalignment(leaf1Title, HPos.LEFT);
        subBranchLeaf1.add(leaf1Title, 0, 0);
        //label of leaf1
        subBranchLeaf1.add(labelMaker("Taille(cm)"), 0, 1);
        subBranchLeaf1.add(labelMaker("Poids(kg)"), 0, 2);
        subBranchLeaf1.add(labelMaker("Age(années)"), 0, 3);
        subBranchLeaf1.add(labelMaker("sexe"), 0, 4);
        subBranchLeaf1.add(labelMaker("Style de vie"), 0, 5);
        //textfields of leaf 1
        field1 = textFieldMaker("Taille en cm");
        field2 = textFieldMaker("Poids en kg");
        field3 = textFieldMaker("Age en année");
        subBranchLeaf1.add(field1, 1, 1);
        subBranchLeaf1.add(field2, 1, 2);
        subBranchLeaf1.add(field3, 1, 3);
        //button of leaf1
        HBox buttonBar = new HBox();
        final ToggleGroup group = new ToggleGroup();
        radioButton1 = new RadioButton("Femme");
        radioButton1.setSelected(true);
        radioButton2 = new RadioButton("Homme");
        radioButton1.setToggleGroup(group);
        radioButton2.setToggleGroup(group);
        buttonBar.setSpacing(5);
        buttonBar.setAlignment(Pos.CENTER_LEFT);
        buttonBar.getChildren().addAll(radioButton1, radioButton2);
        GridPane.setHalignment(buttonBar, HPos.LEFT);
        subBranchLeaf1.add(buttonBar, 1, 4);
        //choicebox fo leaf1
        Label choice1 = new Label("Sédentaire");
        Label choice2 = new Label("Peu actif");
        Label choice3 = new Label("Actif");
        Label choice4 = new Label("Fort actif");
        Label choice5 = new Label("Extrêmement actif");
        choiceBox = new ChoiceBox();
        choiceBox.getItems().addAll(choice1.getText(), choice2.getText(),
                choice3.getText(), choice4.getText(), choice5.getText());
        choiceBox.setValue(choice1.getText());
        subBranchLeaf1.add(choiceBox, 1, 5);

        //LEAF2 OF TWIG
        subBranchLeaf2.setPadding(new Insets(20));
        subBranchLeaf2.setHgap(10);
        subBranchLeaf2.setVgap(15);
        //title of leaf2
        Label leaf2Title = labelMaker("Résultats");
        leaf2Title.setUnderline(true);
        subBranchLeaf2.add(leaf2Title, 0, 0);
        //label of leaf2
        subBranchLeaf2.add(labelMaker("BMR"), 0, 1);
        subBranchLeaf2.add(labelMaker("Calories"), 0, 2);
        //textfields of leaf2
        field21 = new TextField();
        field21.setPromptText("Résultats du BMR");
        field21.setEditable(false);
        GridPane.setHalignment(field21, HPos.LEFT);
        subBranchLeaf2.add(field21, 1, 1);
        field22 = new TextField();
        field22.setPromptText("Dépense en calories");
        field22.setEditable(false);
        GridPane.setHalignment(field22, HPos.LEFT);
        subBranchLeaf2.add(field22, 1, 2);

        twig.getChildren().addAll(subBranchLeaf1, subBranchLeaf2);
        twig.setAlignment(Pos.CENTER);
        //END TWIG

        //SUB BRANCH
        Button buttonBMR = new Button("Calcul du BMR");
        Button buttonClear = new Button("Clear");

        //Button Bmr event
        buttonBMR.setOnAction(click -> {
            compute();
        });
        //Button clear event
        buttonClear.setOnAction(click -> {
            clearData();
        });

        //Button personalisation
        buttonBMR.setTextAlignment(TextAlignment.CENTER);
        buttonBMR.setPrefWidth(500);

        buttonClear.setTextAlignment(TextAlignment.CENTER);
        buttonClear.setPrefWidth(500);

        subBranch.getChildren().addAll(twig, buttonBMR, buttonClear);
        subBranch.setPadding(new Insets(0, 1, 10, 1));
        subBranch.setSpacing(5);
        subBranch.setAlignment(Pos.CENTER);
        //END SUB BRANCH

        //MAIN LEAF     
        //Menu bar of the Leaf
        TabPane tabPane = new TabPane();
        Tab tab1 = new Tab("WEIGHT(kg) VS BMR");
        Tab tab2 = new Tab("WEIGHT(kg) VS CALORIES");
        Tab tab3 = new Tab("HEIGHT(kg) VS BMR");

        final NumberAxis xAxis1 = new NumberAxis(0, 100, 5);
        final NumberAxis yAxis1 = new NumberAxis(0, 2500, 250);
        xAxis1.setLabel("Weight(kg)");
        yAxis1.setLabel("BMR");

        final NumberAxis xAxis2 = new NumberAxis(0, 100, 5);
        final NumberAxis yAxis2 = new NumberAxis(0, 3000, 250);
        xAxis2.setLabel("Weight(kg)");
        yAxis2.setLabel("Calories");

        final NumberAxis xAxis3 = new NumberAxis(0, 200, 25);
        final NumberAxis yAxis3 = new NumberAxis(0, 2500, 250);
        xAxis3.setLabel("Height(cm)");
        yAxis3.setLabel("BMR");

        LineChart lineChart1 = new LineChart(xAxis1, yAxis1);
        lineChart1.setTitle("WEIGHT(kg) VS BMR");

        LineChart lineChart2 = new LineChart(xAxis2, yAxis2);
        lineChart2.setTitle("WEIGHT(kg) VS CALORIES");

        LineChart lineChart3 = new LineChart(xAxis3, yAxis3);
        lineChart3.setTitle("HEIGHT(cm) VS BMR");

        tab1.setContent(lineChart1);
        tab2.setContent(lineChart2);
        tab3.setContent(lineChart3);
        tabPane.getTabs().addAll(tab1, tab2, tab3);

        menSeries1 = new XYChart.Series();
        menSeries1.setName("MenData");
        womenSeries1 = new XYChart.Series();
        womenSeries1.setName("WomenData");

        menSeries2 = new XYChart.Series();
        menSeries2.setName("MenData");
        womenSeries2 = new XYChart.Series();
        womenSeries2.setName("WomenData");

        menSeries3 = new XYChart.Series();
        menSeries3.setName("MenData");
        womenSeries3 = new XYChart.Series();
        womenSeries3.setName("WomenData");

        lineChart1.getData().addAll(menSeries1, womenSeries1);
        lineChart2.getData().addAll(menSeries2, womenSeries2);
        lineChart3.getData().addAll(menSeries3, womenSeries3);

        mainLeaf.getChildren().addAll(tabPane);
        mainLeaf.setAlignment(Pos.CENTER);

        //BRANCH
        branch.getChildren().addAll(subBranch, mainLeaf);

        //ROOT 
        root.getChildren().addAll(menuBar, branch);

        //SCENE
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * A simple label maker.
     *
     * @param message
     * @return txt a Label
     */
    private Label labelMaker(String message) {
        Label txt = new Label(message);
        GridPane.setHalignment(txt, HPos.LEFT);
        return txt;
    }

    /**
     * A simple text field maker that does not accept a non integer character.
     *
     * @param message
     * @return field a TextField
     */
    private TextField textFieldMaker(String message) {
        TextField field = new TextField();
        field.setPromptText(message);
        UnaryOperator<TextFormatter.Change> integerFilter = change -> {
            String input = change.getText();
            if (input.matches("[0-9]*")) {
                return change;
            }
            return null;
        };
        field.setTextFormatter(new TextFormatter<String>(integerFilter));
        GridPane.setHalignment(field, HPos.LEFT);
        return field;
    }

    /**
     * Method computeBMR, if all the data has been given by the users ,gets the
     * data to the calculator and appeal the method getBMR and getCalories which
     * are going to be displayed in their right locations.
     */
    private void compute() {
        if (field1.getText().equals("0") || field2.getText().equals("0") || field3.getText().equals("0")) {
            Alert errorAlert = new Alert(AlertType.ERROR, "You've typed an invalid value!");
            errorAlert.showAndWait();
        } else if (field1.getText().equals("") && field2.getText().equals("") && field3.getText().equals("")) {
            field21.setText("Failed!");
            field21.setStyle("-fx-text-inner-color: red;");
            field22.setText("Failed!");
            field22.setStyle("-fx-text-inner-color: red;");
        } else {
            int height = Integer.parseInt(field1.getText());
            int weight = Integer.parseInt(field2.getText());
            int age = Integer.parseInt(field3.getText());
            boolean sexe = false;
            if (radioButton2.isSelected()) {
                sexe = true;
            }
            Activity activity = null;
            switch (choiceBox.getValue().toString()) {
                case "Sédentaire":
                    activity = SEDENTARY;
                    break;
                case "Peu actif":
                    activity = NOT_ACTIVE;
                    break;
                case "Actif":
                    activity = ACTIVE;
                    break;
                case "Fort actif":
                    activity = STRONG_ACTIVE;
                    break;
                case "Extrêmement actif":
                    activity = EXTREMELY_ACTIVE;
                    break;
            }
            //L'encodage des données de Person et le changement des valeurs de BMR et Calories
            calculator.setData(height, weight, age, sexe, activity);
            calculator.change();

            field21.setText(String.valueOf(bmr));
            field21.setStyle("-fx-text-inner-color: black;");
            field22.setText(String.valueOf(calories));
            field22.setStyle("-fx-text-inner-color: black;");

            int weight1 = weight - 10;
            int weight2 = weight - 5;
            int weight3 = weight;
            int weight4 = weight + 5;

            int height1 = height - 20;
            int height2 = height - 10;
            int height3 = height;
            int height4 = height + 10;

            double bmr1 = calculator.computeBMR(weight1, height, age, true);
            double bmr2 = calculator.computeBMR(weight2, height, age, true);
            double bmr3 = calculator.computeBMR(weight3, height, age, true);
            double bmr4 = calculator.computeBMR(weight4, height, age, true);
            double bmr5 = calculator.computeBMR(weight1, height, age, false);
            double bmr6 = calculator.computeBMR(weight2, height, age, false);
            double bmr7 = calculator.computeBMR(weight3, height, age, false);
            double bmr8 = calculator.computeBMR(weight3, height, age, false);

            menSeries1.getData().addAll(new XYChart.Data(weight1, bmr1),
                     new XYChart.Data(weight2, bmr2),
                     new XYChart.Data(weight3, bmr3),
                     new XYChart.Data(weight4, bmr4));
            womenSeries1.getData().addAll(new XYChart.Data(weight1, bmr5),
                     new XYChart.Data(weight2, bmr6),
                     new XYChart.Data(weight3, bmr7),
                     new XYChart.Data(weight4, bmr8));

            menSeries2.getData().addAll(new XYChart.Data(weight1, calculator.computeCalories(bmr1)),
                     new XYChart.Data(weight2, calculator.computeCalories(bmr2)),
                     new XYChart.Data(weight3, calculator.computeCalories(bmr3)),
                     new XYChart.Data(weight4, calculator.computeCalories(bmr4)));
            womenSeries2.getData().addAll(new XYChart.Data(weight1, calculator.computeCalories(bmr5)),
                     new XYChart.Data(weight2, calculator.computeCalories(bmr6)),
                     new XYChart.Data(weight3, calculator.computeCalories(bmr7)),
                     new XYChart.Data(weight4, calculator.computeCalories(bmr8)));

            menSeries3.getData().addAll(new XYChart.Data(height1, bmr1),
                     new XYChart.Data(height2, bmr2),
                     new XYChart.Data(height3, bmr3),
                     new XYChart.Data(height4, bmr4));
            womenSeries3.getData().addAll(new XYChart.Data(height1, bmr5),
                     new XYChart.Data(height2, bmr6),
                     new XYChart.Data(height3, bmr7),
                     new XYChart.Data(height4, bmr8));
        }
    }

    /**
     * Method clearData , clears all the TextField.
     */
    private void clearData() {
        field1.setText("");
        field2.setText("");
        field3.setText("");
        radioButton1.setSelected(true);
        choiceBox.setValue(choiceBox.getItems().get(0));
        field21.setText("");
        field22.setText("");

        menSeries1.getData().clear();
        womenSeries1.getData().clear();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("Bmr")) {
            bmr = (double) evt.getNewValue();
        }
        if (evt.getPropertyName().equals("Calories")) {
            calories = (double) evt.getNewValue();
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
