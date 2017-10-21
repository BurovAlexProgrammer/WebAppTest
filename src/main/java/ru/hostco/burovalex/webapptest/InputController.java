package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.bind.validator.BeanValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;
import ru.hostco.burovalex.webapptest.entity.User;
import ru.hostco.burovalex.webapptest.services.*;

import java.util.List;
import java.util.Set;

public class InputController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;
    private Component formComponent;
    private db db;
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
    Timebox procedureTime;
    @Wire
    Intbox roomNumber;


    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);
        formComponent = comp;
        ListModelList<String> procedureDayModel = new ListModelList<String>(Common.getDayOfWeekList());
        procedureDay.setModel(procedureDayModel);
        db = new db();
        db.main(null);
    }


    @Listen("onClick=#addProcedure")
    public void addProcedure() {
//        Clients.showNotification("HELLOOOOOOOOOO MY FREND))!!!");
        Clients.submitForm(formComponent);
//        Messagebox.show("Done! Doctor: "+doctorFullName.getValue()+"   Error count: "+myValidate());
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

    int myValidate() {
        procedureName.getValue();
        procedurePrice.getValue();
        int error = 0;
        if (!roomNumber.isValid()) error++;
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

}
