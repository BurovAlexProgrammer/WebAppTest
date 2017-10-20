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

import java.util.Set;

public class InputController extends SelectorComposer<Component> {
    private static final long serialVersionUID = 1L;

    //wire components
    @Wire
    Textbox procedureName;
    @Wire
    Textbox doctorFullName;
    @Wire
    Textbox procedurePrice;
    @Wire
    Listbox procedureDay;
    @Wire
    Timebox procedureTime;
    @Wire
    Textbox roomNumber;


    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);

        ListModelList<String> procedureDayModel = new ListModelList<String>(Common.getDayOfWeekList());
        procedureDay.setModel(procedureDayModel);
    }


    @Listen("onClick=#addProcedure")
    public void addProcedure() {
        Messagebox.show("Done!");
    }





    //VALIDATIONS
    public Validator roomNumberValidator = new AbstractValidator() {
        public void validate(ValidationContext context) {
            Integer value = (Integer)context.getProperty().getValue();
            if (value<=0) addInvalidMessage(context, "Номер кабинета болжен быть больше 0");
        }
    };

}
