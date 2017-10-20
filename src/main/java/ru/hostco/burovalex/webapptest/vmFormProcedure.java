package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Messagebox;

public class vmFormProcedure extends ProcedureForm {

    @Listen("onClick = #buttonSubmit")
    public void submit() {
        Messagebox.show("Done!");
    }
}
