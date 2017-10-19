package ru.hostco.burovalex.webapptest;

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
    Textbox procedureRoomNumber;


    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp);

        ListModelList<String> procedureDayModel = new ListModelList<String>(Common.getDayOfWeekList());
        procedureDay.setModel(procedureDayModel);
    }


    @Listen("onClick=#addProcedure")
    public void addProcedure() {

    }

}
