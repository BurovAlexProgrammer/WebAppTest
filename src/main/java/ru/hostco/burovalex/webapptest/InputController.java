package ru.hostco.burovalex.webapptest;

import javassist.NotFoundException;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;
import ru.hostco.burovalex.webapptest.services.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class InputController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    private Component formComponent;
    private MySQL db;
    //wire components
    @Wire
    Textbox procedureName;
    @Wire
    Textbox doctorFullName;
    @Wire
    Intbox procedurePrice;
    @Wire
    Listbox procedureDay;
    @Wire
    Label procedureDayMessage;
    @Wire
    Timebox procedureTime;
    @Wire
    Intbox roomNumber;


    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        formComponent = comp;
        ListModelList<String> procedureDayModel = new ListModelList<String>(Common.getDayOfWeekList());
        procedureDay.setModel(procedureDayModel);
        Listitem listitem=procedureDay.getItemAtIndex(1);
        procedureDay.setSelectedItem(listitem);
        listitem.setSelected(true);
        Date date = new Date();
        date.setTime(10800000); //8 утра
        procedureTime.setValue(date);
        db = new MySQL();
        db.Connect();
        db.createDB();
        db.WriteProcedure();
        db.ReadProcedure();
        db.CloseDB();
    }


    @Listen("onClick=#addProcedure")
    public void addProcedure() {
        try {
            myValidate();
        } catch (Exception e) {System.err.println(e.getMessage());}
//        procedureName.clearErrorMessage();
//        doctorFullName.clearErrorMessage();
//        procedurePrice.clearErrorMessage();
//        procedureTime.clearErrorMessage();
//        roomNumber.clearErrorMessage();
        //roomNumber.setValue(123);
        //roomNumber.setErrorMessage("WTF");
        //procedureName.

    }



    //VALIDATIONS
    public Validator roomNumberValidator = new AbstractValidator() {
        public void validate(ValidationContext context) {
            Integer value = (Integer)context.getProperty().getValue();
            if (value<=0) addInvalidMessage(context, "Номер кабинета болжен быть больше 0");
        }
    };

    public boolean myValidate() throws SQLException, ClassNotFoundException {
        boolean error = false;
        MySQL mdb = new MySQL();
        procedurePrice.getValue();
        Date date = procedureTime.getValue();
        Clients.showNotification("Date:"+date.toString()+"  Time(long):"+date.getTime());
            mdb.Connect();
            //mdb.WriteProcedure(procedureName.getValue(), doctorFullName.getValue(), procedurePrice.getValue(), findSelectedItem(procedureDay), procedureTime.getValue().getTime(), roomNumber.getValue());
            mdb.ReadProcedure();
            mdb.CloseDB();
        if (!roomNumber.isValid()) error = true;
        return error;
    }

    public static boolean validate(Component component) {
        boolean isValid=true;
        //boolean isValid = checkIsValid(component);

        List<Component> children = component.getChildren();
        for (Component each: children) {
            validate(each);
        }

        return isValid;
    }

    int findSelectedItem(Listbox lbox) {
        int index=-1;
            for (int i=0; i<lbox.getItemCount(); i++) {
                if (lbox.getItems().get(i).isSelected()) index=i;
            }
        return index;
    }

}
