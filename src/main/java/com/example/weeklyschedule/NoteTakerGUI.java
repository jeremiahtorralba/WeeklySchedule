package com.example.weeklyschedule;

/**
 * @author Jeremiah Torralba, Zachary Eubanks-Wilson
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.logging.*;

public class NoteTakerGUI extends Application {
    BorderPane root;
    ArrayList<Note> list  = new ArrayList<Note>();
    Label errors2 = new Label("Error");
    String dated,content,datem,dateh,datew;
    noteList nlist;

    public NoteTakerGUI() throws FileNotFoundException, IOException {
        this.nlist = new noteList(list);
    }

    @Override
    public void start(Stage primaryStage) {
        root = new BorderPane();
        root.setCenter(askName());

        root.getStyleClass().add("ask");
        root.setPadding(new Insets(100, 0, 0, 0));

        Scene scene = new Scene(root, 1450, 765);
        //scene.getStylesheets().add("style.css");

        URL url = this.getClass().getResource("/style.css");
        if (url == null) {
            System.out.println("Resource not found. Aborting.");
            System.exit(-1);
        }
        String css = url.toExternalForm();
        scene.getStylesheets().add(css);

        primaryStage.setTitle("Notes");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public GridPane askName() {
        // ask name
        GridPane root = new GridPane();

        // label header
        VBox ask = new VBox();
        ask.setAlignment(Pos.CENTER);
        HBox header = new HBox();
        header.setAlignment(Pos.CENTER);
        header.setPadding(new Insets(80, 0, 0, 550));
        header.getStyleClass().add("header");
        Label title = new Label("Daily Planner");
        header.getChildren().addAll(title);

        // name and label
        HBox labelname = new HBox();
        Label enter = new Label("Enter your name:");
        TextField name2 = new TextField();
        labelname.setAlignment(Pos.CENTER);
        labelname.setPadding(new Insets(60, 0, 0, 540));
        labelname.setSpacing(10);
        labelname.getStyleClass().add("ask");
        labelname.getChildren().addAll(enter, name2);

        // button
        HBox button = new HBox();
        button.setAlignment(Pos.CENTER);
        button.setPadding(new Insets(20, 0, 0, 837));
        button.getStyleClass().add("ask");
        Button next2 = new Button("    >    ");
        button.getChildren().addAll(next2);

        // if name field is empty
        HBox message = new HBox();
        message.setAlignment(Pos.CENTER);
        message.setPadding(new Insets(20, 0, 0, 700));
        message.getStyleClass().add("ask");
        Label empty = new Label("*Please enter your name");
        empty.setVisible(false);
        next2.setOnAction(e -> switchPage(e, next2, name2, empty));
        name2.setOnAction(e -> switchPage(e, next2, name2, empty));
        message.getChildren().addAll(empty);

        ask.getChildren().addAll(header, labelname, button, message);
        root.add(ask, 0, 0);

        return(root);
    }

    public GridPane dailyPlanner(TextField name2){
        GridPane root = new GridPane();

        VBox plan = new VBox();
        root.setPadding(new Insets(2, 2, 2, 2));

        // error messages
        HBox errors = new HBox();
        Label errors2 = new Label("*Add note(s) to day textareas");
        errors2.setVisible(false);
        errors.setAlignment(Pos.CENTER);
        errors.setPadding(new Insets(10, 0, 0, 0));
        errors.getChildren().addAll(errors2);

        // header
        HBox header = new HBox();
        header.setPadding(new Insets(-80, 0, 0, 0));
        header.setAlignment(Pos.CENTER);
        Label title = new Label("Hello " + name2.getText() + "!");
        header.getStyleClass().add("hello");
        header.getChildren().addAll(title);

        // month and week
        HBox mowe = new HBox();
        mowe.setPadding(new Insets(14, 0, 14, 0));
        mowe.setSpacing(10);
        Label molabel = new Label("Month:");
        molabel.setFont(Font.font("segoe", 14));
        ComboBox month = new ComboBox();
        month.getItems().addAll(
                "January", "February", "March", "April","May", "June",
                "July", "August", "September", "October", "November", "December");
        Label welabel = new Label("Week:");
        welabel.setFont(Font.font("segoe", 14));
        ComboBox week = new ComboBox();
        week.getItems().addAll(
                "Week 1", "Week 2", "Week 3", "Week 4");
        mowe.setAlignment(Pos.CENTER);
        mowe.getStyleClass().add("mowe");
        mowe.getChildren().addAll(molabel, month, welabel, week);
        month.getSelectionModel().select("January");
        week.getSelectionModel().select("Week 1");

        // day labels
        HBox day = new HBox();
        day.setPadding(new Insets(15, 0, 0, 0));
        day.setSpacing(178);
        Label sun = new Label("Sun");
        Label mon = new Label("Mon");
        Label tue = new Label("Tue");
        Label wed = new Label("Wed");
        Label thu = new Label("Thu");
        Label fri = new Label("Fri");
        Label sat = new Label("Sat");
        day.setAlignment(Pos.CENTER);
        day.getStyleClass().add("day");
        day.getChildren().addAll(sun, mon, tue, wed, thu, fri, sat);

        //  day textareas
        HBox daytext = new HBox();
        daytext.setPadding(new Insets(15, 0, 0, 0));
        // sunday
        TextArea sunday = new TextArea();
        //sunday.setEditable(false);
        // monday
        TextArea monday = new TextArea();
        //monday.setEditable(false);
        // tuesday
        TextArea tuesday = new TextArea();
        //tuesday.setEditable(false);
        // wednesday
        TextArea wednesday = new TextArea();
        //wednesday.setEditable(false);
        // thursday
        TextArea thursday = new TextArea();
        //thursday.setEditable(false);
        // friday
        TextArea friday = new TextArea();
        //friday.setEditable(false);
        // saturday
        TextArea saturday = new TextArea();
        //saturday.setEditable(false);
        daytext.setAlignment(Pos.CENTER);
        daytext.getStyleClass().add("daytext");
        daytext.getChildren().addAll(sunday, monday, tuesday, wednesday,
                thursday, friday, saturday);

        // hour and buttons
        HBox hbuttons = new HBox();
        hbuttons.setPadding(new Insets(25, 0, 0, 0));
        hbuttons.setSpacing(20);
        // hour
        HBox sethour = new HBox();
        sethour.setPadding(new Insets(0, 80, 0, 0));
        sethour.setSpacing(10);
        sethour.setAlignment(Pos.CENTER);
        Label holabel = new Label("Hour:");
        holabel.setFont(Font.font("segoe", 14));
        ComboBox hour = new ComboBox();
        hour.getItems().addAll(
                "12am", "1am", "2am", "3am","4am", "5am", "6am", "7am", "8am",
                "9am", "10am", "11am", "12pm", "1pm", "2pm","3pm", "4pm", "5pm",
                "6pm", "7pm", "8pm", "9pm", "10pm", "11pm");
        sethour.getChildren().addAll(holabel, hour);
        hour.getSelectionModel().select("12am");
        // add button
        Button add = new Button("   _Add   ");
        Button delete = new Button(" _Delete  ");
        Button clear = new Button("  _Clear  ");
        add.setDisable(true);
        add.setOnAction(ad -> {
            sunday.setEditable(true);
            monday.setEditable(true);
            tuesday.setEditable(true);
            wednesday.setEditable(true);
            thursday.setEditable(true);
            friday.setEditable(true);
            saturday.setEditable(true);
            if(dated == "Sunday")
                content = sunday.getText();
            if(dated == "Monday")
                content = monday.getText();
            if(dated == "Tuesday")
                content = tuesday.getText();
            if(dated == "Wednesday")
                content = wednesday.getText();
            if(dated == "Thursday")
                content = thursday.getText();
            if(dated == "Friday")
                content = friday.getText();
            if(dated == "Saturday")
                content = saturday.getText();
            datew = week.getSelectionModel().getSelectedItem().toString();
            datem = month.getSelectionModel().getSelectedItem().toString();
            dateh = hour.getSelectionModel().getSelectedItem().toString();
            Alert alertadd = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you wish to add to the file?", ButtonType.YES, ButtonType.NO);
            alertadd.setTitle("Daily Planner");
            Optional<ButtonType> result = alertadd.showAndWait();
            // sunday textarea add
            if(result.get() == ButtonType.YES) {
                Note note = new Note(dated,datem,datew,content,dateh);
                try {
                    if(sunday.getText().trim().length() == 0
                            && monday.getText().trim().length() == 0
                            && tuesday.getText().trim().length() == 0
                            && wednesday.getText().trim().length() == 0
                            && thursday.getText().trim().length() == 0
                            && friday.getText().trim().length() == 0
                            && saturday.getText().trim().length() == 0) {
                        errors2.setText("Error: Text must be entered to note to add to file!");
                        errors2.setVisible(true);
                    }
                    else{
                        errors2.setText("Note has been added!, Press clear to add more!");
                        errors2.setVisible(true);
                        nlist.addNote(note);
                        nlist.setNotes(list);
                    }
                } catch (FileNotFoundException ex) {
                    Logger logger = Logger.getLogger(NoteTakerGUI.class.getName());//.log(System.Logger.Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger logger = Logger.getLogger(NoteTakerGUI.class.getName());//.log(System.Logger.Level.SEVERE, null, ex);
                }
            }
            if(result.get() == ButtonType.NO) {
            }
            add.setDisable(true);
            //clear.setDisable(true);
            //delete.setDisable(true);
        });
        // clear button
        clear.setDisable(true);
        clear.setOnAction(ed -> {
            sunday.setEditable(true);
            monday.setEditable(true);
            tuesday.setEditable(true);
            wednesday.setEditable(true);
            thursday.setEditable(true);
            friday.setEditable(true);
            saturday.setEditable(true);
            Alert alertadd = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you wish to clear the all days?", ButtonType.YES, ButtonType.NO);
            alertadd.setTitle("Daily Planner");
            Optional<ButtonType> result = alertadd.showAndWait();
            if(result.get() == ButtonType.YES) {
                if(sunday.getText().trim().length() == 0
                        && monday.getText().trim().length() == 0
                        && tuesday.getText().trim().length() == 0
                        && wednesday.getText().trim().length() == 0
                        && thursday.getText().trim().length() == 0
                        && friday.getText().trim().length() == 0
                        && saturday.getText().trim().length() == 0) {
                    errors2.setText("There is no text to clear!");
                }
                else {
                    sunday.clear();
                    monday.clear();
                    tuesday.clear();
                    wednesday.clear();
                    thursday.clear();
                    friday.clear();
                    saturday.clear();
                    errors2.setText("Days are cleared!");
                    errors2.setVisible(true);
                }
                if(result.get() == ButtonType.NO) {
                }
                //add.setDisable(true);
                clear.setDisable(true);
                //delete.setDisable(true);
            }
        });
        // delete button
        delete.setDisable(true);
        delete.setOnAction(d -> {
            sunday.setEditable(true);
            monday.setEditable(true);
            tuesday.setEditable(true);
            wednesday.setEditable(true);
            thursday.setEditable(true);
            friday.setEditable(true);
            saturday.setEditable(true);
            Alert alertexit = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you wish to delete?", ButtonType.YES, ButtonType.NO);
            alertexit.setTitle("Daily Planner");
            Optional<ButtonType> result = alertexit.showAndWait();
            if(result.get() == ButtonType.YES) {
                Note note = new Note(dated,datem,datew,content,dateh);
                if(sunday.getText().trim().length() == 0
                        && monday.getText().trim().length() == 0
                        && tuesday.getText().trim().length() == 0
                        && wednesday.getText().trim().length() == 0
                        && thursday.getText().trim().length() == 0
                        && friday.getText().trim().length() == 0
                        && saturday.getText().trim().length() == 0) {
                    errors2.setText("Error: Note must be available to delete!");
                    errors2.setVisible(true);
                }
                else{
                    errors2.setText("Note has been deleted!");
                    errors2.setVisible(true);
                    for(int i=0;i<list.size();i++){
                        if(list.get(i).toString().equals(note.toString()))
                            list.remove(i);
                    }
                }
                try {
                    nlist.setNotes(list);
                } catch (FileNotFoundException ex) {
                    Logger logger = Logger.getLogger(NoteTakerGUI.class.getName());//.log(System.Logger.Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger logger = Logger.getLogger(NoteTakerGUI.class.getName());//.log(System.Logger.Level.SEVERE, null, ex);
                }
            }
            if(result.get() == ButtonType.NO) {
            }
            //add.setDisable(true);
            delete.setDisable(true);
            clear.setDisable(true);
        });
        // day textarea events
        sunday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Sunday";
        });
        monday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Monday";
            if(monday.getText().trim().length() == 0) {
                errors2.setVisible(true);
            }
        });
        tuesday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Tuesday";
            if(tuesday.getText().trim().length() == 0) {
                errors2.setVisible(true);
            }
        });
        wednesday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Wednesday";
            if(wednesday.getText().trim().length() == 0) {
                errors2.setVisible(true);
            }
        });
        thursday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Thursday";
            if(thursday.getText().trim().length() == 0) {
                errors2.setVisible(true);
            }
        });
        friday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Friday";
            if(friday.getText().trim().length() == 0) {
                errors2.setVisible(true);
            }
        });
        saturday.setOnMouseClicked(da -> {
            add.setDisable(false);
            clear.setDisable(false);
            delete.setDisable(false);
            dated = "Saturday";
            if(saturday.getText().trim().length() == 0) {
                errors2.setVisible(true);
            }
        });
        // exit
        Button exit = new Button("  _Exit  ");
        exit.setOnAction(e -> {
            Alert alertexit = new Alert(Alert.AlertType.CONFIRMATION,
                    "Are you sure you wish to exit?", ButtonType.YES, ButtonType.NO);
            alertexit.setTitle("Daily Planner");
            Optional<ButtonType> result = alertexit.showAndWait();
            if(result.get() == ButtonType.YES) {
                System.exit(0);
            }
            if(result.get() == ButtonType.NO) {
            }
        });
        hbuttons.setMargin(exit, new Insets(0, 0, 0, 80));
        hbuttons.setPadding(new Insets(30, 0, 10, 0));
        hbuttons.setAlignment(Pos.CENTER);
        hbuttons.getStyleClass().add("buttons");
        hbuttons.getChildren().addAll(sethour, add, clear, delete, exit);

        plan.setAlignment(Pos.CENTER);
        plan.getChildren().addAll(header, mowe, day, daytext, hbuttons, errors);

        root.add(plan, 0, 0);

        return(root);
    }

    public void switchPage(ActionEvent e, Button next2, TextField name2, Label empty) {
        if (next2.getText().equals("    >    ") &&
                name2.getText().isEmpty()) {
            empty.setVisible(true);
        }
        else if (next2.getText().equals("    >    ") &&
                name2.getText().equals(name2.getText())) {
            root.setCenter(dailyPlanner(name2));
        }
    }

}