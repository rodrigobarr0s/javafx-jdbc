package gui;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Constraints;
import gui.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.entities.Department;
import model.exceptions.ValidationException;
import model.services.DepartmentService;

public class DepartmentFormController implements Initializable {

    private Department department;

    private DepartmentService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

    @FXML
    private TextField textFieldId;

    @FXML
    private TextField textFieldName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    private void notifyDataChangeListener() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    @FXML
    public void onButtonSaveAction(ActionEvent event) {
        if (department == null) {
            throw new IllegalStateException("department was null");
        }
        if (service == null) {
            throw new IllegalStateException("service was null");
        }
        try {
            department = getFormData();
            service.saveOrUpdate(department);
            notifyDataChangeListener();
            Utils.currentStage(event).close();

        } catch (ValidationException e) {
            setErrorMessages(e.getErrors());
            
        } catch (DbException e) {
            Alerts.showError("Error saving department", null, e.getMessage());
        }
    }

    @FXML
    public void onButtonCancelAction(ActionEvent event) {
        Stage stage = Utils.currentStage(event);
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(textFieldId);
        Constraints.setTextFieldMaxLength(textFieldName, 30);
    }

    public void updateFormData() {
        if (department == null) {
            throw new IllegalStateException("Department was null");
        }
        textFieldId.setText(String.valueOf(department.getId()));
        textFieldName.setText(department.getName());
    }

    private Department getFormData() {
        Department department = new Department();

        ValidationException exception = new ValidationException("Validation error");

        //ID
        department.setId(Utils.tryParseToInt(textFieldId.getText()));

        //Name
        if (textFieldName.getText() == null || textFieldName.getText().trim().equals("")) {
            exception.addError("name", "Field can't be empty");
        }
        department.setName(textFieldName.getText());

        if (exception.getErrors().size() > 0) {
            throw exception;
        }

        return department;
    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }

}
