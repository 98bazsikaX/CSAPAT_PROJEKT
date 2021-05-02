package hu.alkfejl.controller;

import dao.OrgDAO;
import dao.OrgDAOImpl;
import hu.alkfejl.App;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import model.Organization;


public class AddEditOrg {
    private Organization org;
    private OrgDAO dao = new OrgDAOImpl();

    @FXML
    public TextField orgName;

    @FXML
    public DatePicker founded;

    public void setOrg(Organization org){
        this.org = org;
        orgName.textProperty().bindBidirectional(this.org.nameProperty());
        founded.valueProperty().bindBidirectional(this.org.foundationProperty());

    }

    public void save(){
        this.org = dao.save(this.org);
    }

    public void cancel(){
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,"Menteni akarja a mostani állást? ", ButtonType.YES,ButtonType.NO);
        confirm.showAndWait().ifPresent(buttonType -> {
            if(buttonType.equals(ButtonType.YES)){
                save();
            }
        });
        App.loadFXML("/fxml/showOrgs.fxml");

    }
}
