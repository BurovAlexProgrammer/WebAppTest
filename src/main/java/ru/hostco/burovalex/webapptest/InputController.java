package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.BindComposer;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.Validator;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import static org.zkoss.xel.fn.CommonFns.toInt;

public class InputController extends BindComposer {
    private static final long serialVersionUID = 1L;
    private MySQL db;
    private Procedure[] myProcedures;
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
    @Wire
    Include inc;


//    public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
//        try {
//
//            myProcedures = db.getProcedures();
////            db.ReadProcedure();
////            myProcedures = db.getProcedures();
////            db.CloseDB();
//
//        page.setAttribute("procedures", myProcedures);
//
//        }   catch (SQLException e) {logError(e);}  catch (ClassNotFoundException e) {logError(e);}
//        return super.doBeforeCompose(page, parent, compInfo);
//    }

    @Override
    public void doAfterCompose(Component comp) throws Exception{
        super.doAfterCompose(comp); //comp = Form component
        db = new MySQL();
        db.Connect();
        db.createDB();
        ListModelList<String> procedureDayModel = new ListModelList<String>(Common.getDayOfWeekList());
        procedureDay.setModel(procedureDayModel);
        Listitem listitem=procedureDay.getItemAtIndex(1);
        procedureDay.setSelectedItem(listitem);
        listitem.setSelected(true);
        Date date = new Date();
        date.setTime(10800000); //8 утра
        procedureTime.setValue(date);
    }


    @Listen("onClick=#addProcedure")
    public void addProcedure() {
        try {
            myValidate();
        } catch (Exception e) {System.err.println(e.getMessage());}
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
            mdb.WriteProcedure(procedureName.getValue(), doctorFullName.getValue(), procedurePrice.getValue(), findSelectedItem(procedureDay), toInt(procedureTime.getValue().getTime()), roomNumber.getValue());
            mdb.ReadProcedure();
            mdb.CloseDB();
        if (!roomNumber.isValid()) error = true;
        return error;
    }

    public static boolean validate(Component component) {
        boolean isValid=true;

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

    void log(String s) {
        System.out.println(s);
    }

    void logError(String s) {
        System.err.println(s);
    }

    void logError(Exception e) {
        System.err.println(e.getMessage());
    }

    @Command
    public void removeRow(@BindingParam("procedureId") int procedureId) {
        log("+++++++++++++++Comand RemoveRow: id="+procedureId);
    }
}
