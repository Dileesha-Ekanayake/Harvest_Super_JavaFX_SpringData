package lk.earth.dbapp;

import javafx.application.Application;
import javafx.stage.Stage;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.time.LocalDate;

import javafx.util.Callback;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.property.SimpleStringProperty;

import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeUi extends Application {

    TableView table;

    TextField txtName;
    TextField txtNic;
    TextField txtMobile;
    TextField txtEmail;

    TextField txtSearchName;

    ComboBox<Gender> cmbGender;
    ComboBox<Designation> cmbDesignation;
    ComboBox<Statusemployee> cmbStatus;
    ComboBox<Gender> cmbSearchGender;
    ComboBox<Designation> cmbSearchDesignation;

    Button btnAdd;
    Button btnDelete;
    Button btnUpdate;
    Button btnClear;
    Button btnSearch;
    Button btnClearSearch;

    ObservableList<Gender> genders;
    ObservableList<Designation> designations;
    ObservableList<Statusemployee> status;
    ObservableList<Employee> emplist;

    DatePicker datePicker;

    Hashtable<String, String> regexname;

    Employee oldEmployee;

    Employee employee;

    String intial = "-fx-background-color: #fff ";
    String valid = "-fx-background-color: #cfc ";
    String invalid = "-fx-background-color: #fcc ";
    String updated = "-fx-background-color: #ff8 ";

    @Override
    public void start(Stage stage) {

        stage.setTitle("Employee - Earth");

        Label lblName = new Label("Name : ");
        Label lblNic = new Label("Nic : ");
        Label lblMobile = new Label("Mobile : ");
        Label lblEmail = new Label("Email : ");
        Label lblGender = new Label("Gender : ");
        Label lblDesignation = new Label("Designation : ");
        Label lblStatus = new Label("Status : ");
        Label lblDob = new Label("DOB : ");

        txtName = new TextField();
        txtName.setPromptText("Type Name Here");
        txtNic = new TextField();
        txtNic.setPromptText("Type Nic Here");
        txtMobile = new TextField();
        txtMobile.setPromptText("Type Mobile Here");
        txtEmail = new TextField();
        txtEmail.setPromptText("Type Email Here");

        datePicker = new DatePicker();

        btnAdd = new Button("Add");
        // btnAdd.setTextFill(Color.WHITE);
        // btnAdd.setStyle("-fx-background-color: #006400;");

        btnUpdate = new Button("Update");
        // btnUpdate.setTextFill(Color.WHITE);
        // btnUpdate.setStyle("-fx-background-color: #DBA800;");

        btnClear = new Button("Clear");
        // btnClear.setTextFill(Color.WHITE);
        // btnClear.setStyle("-fx-background-color: #1175A8;");

        btnDelete = new Button("Delete");
        // btnDelete.setTextFill(Color.WHITE);
        // btnDelete.setStyle("-fx-background-color: #B30018;");

        btnSearch = new Button("Search");
        // btnSearch.setTextFill(Color.WHITE);
        // btnSearch.setStyle("-fx-background-color: #8834B3;");

        btnClearSearch = new Button("Clear Search");
        // btnClearSearch.setTextFill(Color.WHITE);
        // btnClearSearch.setStyle("-fx-background-color: #8834B3;");

        cmbGender = new ComboBox();
        cmbGender.setPromptText("Select Gender");

        cmbDesignation = new ComboBox();
        cmbDesignation.setPromptText("Select Designation");

        cmbStatus = new ComboBox();
        cmbStatus.setPromptText("Select Status");

        btnAdd.setOnAction(event -> {
            btnAddAp(event);
        });

        btnClear.setOnAction(event -> {
            btnClearAp(event);
        });
        btnUpdate.setOnAction(event -> {
            btnUpdateAp(event);
        });
        btnDelete.setOnAction(event -> {
            btnDeleteAp(event);
        });
        btnSearch.setOnAction(event -> {
            btnSearchAp(event);
        });
        btnClearSearch.setOnAction(event -> {
            btnClearSearchAp(event);
        });

        GridPane grid = new GridPane();
        grid.add(lblName, 0, 0);
        grid.add(txtName, 2, 0);

        grid.add(lblNic, 0, 1);
        grid.add(txtNic, 2, 1);

        grid.add(lblMobile, 0, 2);
        grid.add(txtMobile, 2, 2);

        grid.add(lblEmail, 0, 3);
        grid.add(txtEmail, 2, 3);

        grid.add(lblGender, 0, 4);
        grid.add(cmbGender, 2, 4);

        grid.add(lblDesignation, 0, 6);
        grid.add(cmbDesignation, 2, 6);

        grid.add(lblStatus, 0, 7);
        grid.add(cmbStatus, 2, 7);

        grid.add(lblDob, 0, 8);
        grid.add(datePicker, 2, 8);

        grid.add(btnAdd, 0, 9);
        grid.add(btnClear, 0, 10);
        grid.add(btnUpdate, 2, 9);
        grid.add(btnDelete, 2, 10);

        Label lblsearch = new Label("==============Search=============");

        grid.add(lblsearch, 2, 12);

        Label lblSearchName = new Label("Search By Name");
        Label lblSearchGender = new Label("Search By Gender");
        Label lblSearchDesignation = new Label("Search By Designation");

        txtSearchName = new TextField();
        txtSearchName.setPromptText("Type Name Here");

        cmbSearchGender = new ComboBox();
        cmbSearchGender.setPromptText("Select Gender");

        cmbSearchDesignation = new ComboBox();
        cmbSearchDesignation.setPromptText("Select Designation");

        grid.add(lblSearchName, 0, 13);
        grid.add(txtSearchName, 1, 13);

        grid.add(lblSearchGender, 0, 14);
        grid.add(cmbSearchGender, 1, 14);

        grid.add(lblSearchDesignation, 0, 15);
        grid.add(cmbSearchDesignation, 1, 15);

        grid.add(btnSearch, 0, 16);
        grid.add(btnClearSearch, 1, 16);

        table = new TableView();
        TableColumn NameCol = new TableColumn("Name");
        NameCol.setCellValueFactory(new PropertyValueFactory("name"));
        TableColumn NicCol = new TableColumn("Nic");
        NicCol.setCellValueFactory(new PropertyValueFactory("nic"));
        TableColumn MobileCol = new TableColumn("Mobile");
        MobileCol.setCellValueFactory(new PropertyValueFactory("mobile"));
        TableColumn EmailCol = new TableColumn("Email");
        EmailCol.setCellValueFactory(new PropertyValueFactory("email"));

        TableColumn GennderCol = new TableColumn("Gender");
        GennderCol.setCellValueFactory(

                new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> tcd) {
                        Employee employee = tcd.getValue();

                        return new SimpleStringProperty(
                                employee.getGender().getName());
                        // employee.getGender().getName() + "(" + employee.getGender().getId() + ")");

                    }

                });

        TableColumn DesignationCol = new TableColumn("Designation");
        DesignationCol.setCellValueFactory(

                new Callback<TableColumn.CellDataFeatures<Employee, String>, ObservableValue<String>>() {

                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<Employee, String> tcd) {
                        Employee employee = tcd.getValue();

                        return new SimpleStringProperty(
                                employee.getDesignation().getName());
                        // employee.getDesignation().getName() + "(" + employee.getDesignation().getId()
                        // + ")");

                    }

                });

        table.getColumns().addAll(NameCol, NicCol, MobileCol, EmailCol, GennderCol, DesignationCol);
        table.setOnMouseClicked(e -> {
            tblVC(e);
        });

        grid.add(table, 2, 18);

        Scene scene = new Scene(grid, 800, 600);

        stage.setScene(scene);
        stage.show();

        initalize();

        // Real Time Validation
        txtName.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {
                txtNameKR(e);

            }

        });

        txtNic.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {
                txtNicKR(e);

            }

        });

        txtMobile.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {
                txtMobileKR(e);

            }

        });

        txtEmail.setOnKeyReleased(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent e) {
                txtEmailKR(e);

            }

        });

        cmbGender.valueProperty().addListener(new ChangeListener<Gender>() {
            @Override
            public void changed(ObservableValue ov, Gender t, Gender t1) {
                cmbGenderAp();
            }
        });

        cmbDesignation.valueProperty().addListener(new ChangeListener<Designation>() {
            @Override
            public void changed(ObservableValue ov, Designation t, Designation t1) {
                cmbDesignationAp();
            }
        });

        cmbStatus.valueProperty().addListener(new ChangeListener<Statusemployee>() {
            @Override
            public void changed(ObservableValue ov, Statusemployee t, Statusemployee t1) {
                cmbStatusAp();
            }
        });

        datePicker.setOnAction(e -> {
            datePickerAP(e);

        });

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        appContext.close();

    }

    public void initalize() {

        // employee = new Employee();
        regexname = RegexProvider.get();

        // genders = FXCollections.observableArrayList(GenderController.get());
        // designations =
        // FXCollections.observableArrayList(DesignationController.get());
        // status = FXCollections.observableArrayList(StatusemployeeController.get());
        // emplist = FXCollections.observableList(EmployeeController.get(null));

        loadView();
        loadForm();

    }

    public void txtNameKR(KeyEvent e) {

        String name = txtName.getText();

        if (name.matches(regexname.get("name"))) {
            employee.setName(name);
            txtName.setStyle(valid);
            if (oldEmployee != null)
                if (!employee.getName().equals(oldEmployee.getName()))
                    txtName.setStyle(updated);
        }

        else {
            txtName.setStyle(invalid);
            employee.setName(null);
        }

    }

    public void txtNicKR(KeyEvent e) {

        String nic = txtNic.getText();
        if (nic.matches(regexname.get("nic"))) {
            employee.setNic(nic);
            txtNic.setStyle(valid);
            if (oldEmployee != null)
                if (!employee.getNic().equals(oldEmployee.getNic()))
                    txtNic.setStyle(updated);
        } else {
            txtNic.setStyle(invalid);
        }

    }

    public void txtMobileKR(KeyEvent e) {

        String mobile = txtMobile.getText();
        if (mobile.matches(regexname.get("mobile"))) {
            employee.setMobile(mobile);
            txtMobile.setStyle(valid);
            if (oldEmployee != null)
                if (!employee.getMobile().equals(oldEmployee.getMobile()))
                    txtMobile.setStyle(updated);
        } else {
            txtMobile.setStyle(invalid);
        }

    }

    public void txtEmailKR(KeyEvent e) {

        String email = txtEmail.getText();
        if (email.matches(regexname.get("email"))) {
            employee.setEmail(email);
            txtEmail.setStyle(valid);
            if (oldEmployee != null)
                if (!employee.getEmail().equals(oldEmployee.getEmail()))
                    txtEmail.setStyle(updated);
        } else {
            txtEmail.setStyle(invalid);
        }

    }

    public void cmbGenderAp() {

        Gender gender = cmbGender.getSelectionModel().getSelectedItem();
        if (gender != null) {
            cmbGender.setStyle(valid);
            employee.setGender((Gender) cmbGender.getSelectionModel().getSelectedItem());
            if (oldEmployee != null)
                if (!employee.getGender().equals(oldEmployee.getGender()))
                    cmbGender.setStyle(updated);
                else {
                }
        } else {
            cmbGender.setStyle(invalid);
        }
    }

    public void cmbDesignationAp() {

        Designation designation = cmbDesignation.getSelectionModel().getSelectedItem();
        if (designation != null) {
            cmbDesignation.setStyle(valid);
            employee.setDesignation((Designation) cmbDesignation.getSelectionModel().getSelectedItem());
            if (oldEmployee != null)
                if (!employee.getDesignation().equals(oldEmployee.getDesignation()))
                    cmbDesignation.setStyle(updated);
                else {
                }
        } else {
            cmbDesignation.setStyle(invalid);
        }
    }

    public void cmbStatusAp() {

        Statusemployee designation = cmbStatus.getSelectionModel().getSelectedItem();
        if (designation != null) {
            cmbStatus.setStyle(valid);
            employee.setStatusemployee((Statusemployee) cmbStatus.getSelectionModel().getSelectedItem());
            if (oldEmployee != null)
                if (!employee.getStatusemployee().equals(oldEmployee.getStatusemployee()))
                    cmbStatus.setStyle(updated);
                else {
                }
        } else {
            cmbStatus.setStyle(invalid);
        }
    }

    public void datePickerAP(ActionEvent event) {
        LocalDate dob = datePicker.getValue();
        LocalDate today = LocalDate.now();
        int age = today.getYear() - dob.getYear();
        if (age >= 18) {
            employee.setDob(dob);
            datePicker.getEditor().setStyle(valid);
        } else {
            employee.setDob(null);
            datePicker.getEditor().setStyle(invalid);
        }
        if (oldEmployee != null) {
            if (age >= 20 && !employee.getDob().equals(oldEmployee.getDob()))
                datePicker.getEditor().setStyle(updated);
        }

    }

    public void fillForm() {

        enabledButtons(false, true, true);
        setStyle(valid);

    }

    public void loadView() {

        emplist = FXCollections.observableList(EmployeeManager.get(null));
        filltable();

        // employee = new Employee();
        // oldEmployee = null;

        cmbGender.setItems(genders);
        cmbSearchGender.setItems(genders);

        cmbDesignation.setItems(designations);
        cmbSearchDesignation.setItems(designations);
        cmbStatus.setItems(status);

    }

    public void filltable() {
        table.setItems(emplist);
    }

    public void loadForm() {

        employee = new Employee();

        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        GenderService genderService = (GenderService) appContext.getBean("genderService");
        DesignationService designationService = (DesignationService) appContext.getBean("designationService");
        StatusemployeeService statusemployeeService = (StatusemployeeService) appContext
                .getBean("statusemployeeService");

        genders = FXCollections.observableArrayList(genderService.getAll());
        cmbGender.setItems(genders);
        cmbSearchGender.setItems(genders);

        designations = FXCollections.observableArrayList(designationService.getAll());
        cmbDesignation.setItems(designations);
        cmbSearchDesignation.setItems(designations);

        status = FXCollections.observableArrayList(statusemployeeService.getAll());
        cmbStatus.setItems(status);

        // cmbGender.getSelectionModel().clearSelection();
        // cmbDesignation.getSelectionModel().clearSelection();
        // cmbStatus.getSelectionModel().clearSelection();

        txtName.setText("");
        txtNic.setText("");
        txtMobile.setText("07");
        txtEmail.setText("@gmail.com");

        TextField txtDate = datePicker.getEditor();
        txtDate.setText(null);
        datePicker.getEditor().setStyle(intial);

        table.getSelectionModel().clearSelection();

        enabledButtons(true, false, false);
        setStyle(intial);

    }

    public void enabledButtons(boolean add, boolean upd, boolean delt) {
        btnAdd.setDisable(!add);
        btnUpdate.setDisable(!upd);
        btnDelete.setDisable(!delt);

    }

    public void setStyle(String style) {

        txtName.setStyle(style);
        txtMobile.setStyle(style);
        txtNic.setStyle(style);
        txtEmail.setStyle(style);

        cmbDesignation.setStyle(style);
        cmbStatus.setStyle(style);
        cmbGender.setStyle(style);
        datePicker.setStyle(style);
    }

    public void tblVC(MouseEvent e) {
        int row = table.getSelectionModel().getSelectedIndex();
        if (row > -1) {
            Employee employee = emplist.get(row);
            fillForm(employee);
        }
    }

    public String geterrors() {
        String errors = "";

        if (employee.getName() == null)
            errors = errors + "\n invalid Name";
        if (employee.getNic() == null)
            errors = errors + "\n invalid NIC";
        if (employee.getMobile() == null)
            errors = errors + "\n invalid Moblie";
        if (employee.getEmail() == null)
            errors = errors + "\n invalid Email";
        if (employee.getGender() == null)
            errors = errors + "\n Gender Not selected";
        if (employee.getDesignation() == null)
            errors = errors + "\n Designation Not selected";
        if (employee.getStatusemployee() == null)
            errors = errors + "\n StatusEmployee Not selected";
        if (employee.getDob() == null)
            errors = errors + "\n Birth Date Not selected";

        return errors;
    }

    public String getUpdates() {

        String updates = "";

        if (!employee.getName().equals(oldEmployee.getName()))
            updates = updates + "\n Name Updated" + " - " + employee.getName();

        if (!employee.getNic().equals(oldEmployee.getNic()))
            updates = updates + "\n Nic Updated" + " - " + employee.getNic();

        if (!employee.getEmail().equals(oldEmployee.getEmail()))
            updates = updates + "\n Email Updated" + " - " + employee.getEmail();

        if (!employee.getMobile().equals(oldEmployee.getMobile()))
            updates = updates + "\n Mobile Updated" + " - " + employee.getMobile();

        if (!employee.getGender().equals(oldEmployee.getGender()))
            updates = updates + "\n Gender Updated" + " - " + (Gender) cmbGender.getValue();

        if (!employee.getDesignation().equals(oldEmployee.getDesignation()))
            updates = updates + "\n Designation Updated" + " - " + (Designation) cmbDesignation.getValue();

        if (!employee.getStatusemployee().equals(oldEmployee.getStatusemployee()))
            updates = updates + "\n Status Updated" + " - " + (Statusemployee) cmbStatus.getValue();

        if (!employee.getDob().equals(oldEmployee.getDob())) {
            LocalDate dt = datePicker.getValue();
            if (dt != null) {
                LocalDate today = LocalDate.now();
                int age = today.getYear() - dt.getYear();
                if (age >= 18 && !employee.getDob().equals(oldEmployee.getDob()))
                    updates = updates + "\n Birth Date Update";
            }
        }

        return updates;

    }

    public void btnAddAp(ActionEvent event) {

        String error = geterrors();
        Alert a = new Alert(AlertType.CONFIRMATION);
        a.setTitle("Haervest System");
        a.setHeaderText("Employee Module - Form ");

        if (error.isEmpty()) {

            String cnfmsg = "Are you sure to save following Employee?\n\n";
            cnfmsg = cnfmsg + "\nName : " + employee.getName();
            cnfmsg = cnfmsg + "\nName : " + employee.getDob().toString();
            cnfmsg = cnfmsg + "\nNIC : " + employee.getNic();
            cnfmsg = cnfmsg + "\nGender : " + employee.getGender().getName();
            cnfmsg = cnfmsg + "\nDesignation : " + employee.getDesignation().getName();
            cnfmsg = cnfmsg + "\nMobile : " + employee.getMobile();
            cnfmsg = cnfmsg + "\nEmail : " + employee.getEmail();
            cnfmsg = cnfmsg + "\nStaus : " + employee.getStatusemployee().getName();

            a.setContentText(cnfmsg);

            Optional<ButtonType> result = a.showAndWait();

            if (result.get() == ButtonType.OK) {
                String st = EmployeeManager.post(employee);
                if (st.equals("1")) {
                    // initalize();
                    loadView();
                    loadForm();
                    Alert alert1 = new Alert(AlertType.INFORMATION);
                    alert1.setTitle("Haervest System");
                    alert1.setHeaderText("Employee Module - Form Add");
                    alert1.setContentText("Successfully Saved");
                    alert1.show();
                } else {
                    Alert alert2 = new Alert(AlertType.INFORMATION);
                    alert2.setTitle("Haervest System");
                    alert2.setHeaderText("Employee Module - Form Add");
                    alert2.setContentText("Faild to save as \n\n" + st);
                    alert2.show();
                }
            }

        } else {
            Alert alert3 = new Alert(AlertType.INFORMATION);
            alert3.setTitle("Haervest System");
            alert3.setHeaderText("Employee Module - Form Add");
            alert3.setContentText("You have Errors:" + error);
            alert3.show();
        }

    }

    public void btnClearAp(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Haervest System");
        alert.setHeaderText("Employee Module - Form Clear");
        alert.setContentText("Are You Sure To Clear The Form");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {

            // initalize();
            loadForm();
            oldEmployee = null;
        }
    }

    public void btnUpdateAp(ActionEvent event) {

        employee.setId(oldEmployee.getId());

        String error = geterrors();

        if (error.isEmpty()) {
            String updates = getUpdates();

            if (!updates.isEmpty()) {
                Alert a = new Alert(AlertType.CONFIRMATION);
                a.setTitle("Haervest System");
                a.setHeaderText("Employee Module - Update ");
                a.setContentText("You Have Following Updates\n\n" + updates);
                Optional<ButtonType> result = a.showAndWait();
                if (result.get() == ButtonType.OK) {
                    String status = EmployeeManager.put(employee);
                    if (status.equals("1")) {

                        int lsrow = table.getSelectionModel().getSelectedIndex();

                        // initalize();
                        loadView();
                        loadForm();
                        table.getSelectionModel().selectRange(lsrow, lsrow);
                        Alert alert1 = new Alert(AlertType.INFORMATION);
                        alert1.setTitle("Haervest System");
                        alert1.setHeaderText("Employee Module - Form Update");
                        alert1.setContentText("Successfully Updated");
                        alert1.show();

                    } else {
                        Alert alert2 = new Alert(AlertType.INFORMATION);
                        alert2.setTitle("Haervest System");
                        alert2.setHeaderText("Employee Module - Form Update");
                        alert2.setContentText("Faild to Updated as \n\n" + status);
                        alert2.show();
                    }

                }
            }

            else {
                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Haervest System");
                alert1.setHeaderText("Employee Module - Form Update");
                alert1.setContentText("Nothing to be Updated");
                alert1.show();
            }
        } else {
            Alert alert2 = new Alert(AlertType.INFORMATION);
            alert2.setTitle("Haervest System");
            alert2.setHeaderText("Employee Module - Form Update");
            alert2.setContentText("You Have Following Errors\n\n" + error);
            alert2.show();
        }
    }

    public void btnDeleteAp(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Haervest System");
        alert.setHeaderText("Employee Module - Form Delete");
        alert.setContentText("Are You Sure To Delete This Employee?\n\n" + oldEmployee.getName());

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            String status = EmployeeManager.delete(oldEmployee);
            if (status.equals("1")) {

                // initalize();
                loadView();
                loadForm();

                Alert alert1 = new Alert(AlertType.INFORMATION);
                alert1.setTitle("Haervest System");
                alert1.setHeaderText("Employee Module - Form Delete");
                alert1.setContentText("Successfully Deleted");
                alert1.show();
            } else {
                Alert alert2 = new Alert(AlertType.INFORMATION);
                alert2.setTitle("Haervest System");
                alert2.setHeaderText("Employee Module - Form Add");
                alert2.setContentText("Faild to delete as \n\n" + status);
                alert2.show();
            }
        }
        oldEmployee = null;

    }

    public void btnSearchAp(ActionEvent event) {

        String name = txtSearchName.getText();

        Gender gender = cmbSearchGender.getValue();
        Designation designation = cmbSearchDesignation.getValue();

        Hashtable<String, Object> ht = new Hashtable();
        ht.put("name", name);
        if (gender != null)
            ht.put("gender", gender);

        if (designation != null)
            ht.put("designation", designation);
        emplist = FXCollections.observableList(EmployeeManager.get(ht));

        filltable();

    }

    public void btnClearSearchAp(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Haervest System");
        alert.setHeaderText("Employee Module - Clear Search");
        alert.setContentText("Are You Sure To Clear The Search?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK) {
            txtSearchName.setText("");
            initalize();
        }
    }

    public void fillForm(Employee emp) {
        oldEmployee = emp;

        employee = new Employee();

        employee.setName(emp.getName());
        employee.setNic(emp.getNic());
        employee.setMobile(emp.getMobile());
        employee.setEmail(emp.getEmail());
        employee.setGender(emp.getGender());
        employee.setDesignation(emp.getDesignation());
        employee.setStatusemployee(emp.getStatusemployee());
        employee.setDob(emp.getDob());

        txtName.setText(emp.getName());
        txtNic.setText(emp.getNic());
        txtMobile.setText(emp.getMobile());
        txtEmail.setText(emp.getEmail());

        for (Gender gen : genders) {
            if (gen.equals(employee.getGender())) {
                cmbGender.setValue(gen);
                break;
            }
        }

        for (Designation des : designations) {
            if (des.getId() == employee.getDesignation().getId()) {
                cmbDesignation.setValue(des);
                break;
            }
        }

        for (Statusemployee sts : status) {
            if (sts.getId() == employee.getStatusemployee().getId()) {
                cmbStatus.setValue(sts);
                break;
            }
        }

        datePicker.setValue(emp.getDob());
        datePicker.getEditor();

        enabledButtons(false, true, true);
        setStyle(valid);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext appContext = new AnnotationConfigApplicationContext();
        appContext.scan("lk.earth.dbapp");
        appContext.refresh();
        appContext.close();
        launch(args);
    }

}
