package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;
import ru.hostco.burovalex.webapptest.services.Common;
import ru.hostco.burovalex.webapptest.MySQL.procedure;
import static java.lang.Math.toIntExact;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.hostco.burovalex.webapptest.MySQL.withQuotes;
import static ru.hostco.burovalex.webapptest.services.Common.*;

public class MyViewModel {

	public String getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	private String currentPage;
	private MySQL db;
	private Procedure[] myProcedures;

	public Procedure[] getMyProcedures() {
		return myProcedures;
	}


	public Date defaultDate;

	@Init
	public void init() {
		try {
			db = new MySQL();
			db.Connect();
			db.createDB();
			myProcedures = db.getProcedures();
			defaultDate = new Date();
			defaultDate.setTime(10800000); //8 утра
		} catch (SQLException|ClassNotFoundException e) {logError(e);}
	}



	@Command("comPlus")
	@NotifyChange("bindValue")
	public void comPlus() {
		//setBindValue(bindValue+1);
		//log("+");
	}

	@Command
	@NotifyChange("*")
	public void navigatePage(@BindingParam("target") String target) {
		switch (target) {
			case "navItemView":currentPage="view_procedures.zul"; break;
			case "navItemEdit":currentPage="edit_procedures.zul";break;
			case "navItemAdmin":currentPage="auth.zul";break;
			default: currentPage="view_procedures.zul";break;
		}
	}


	@Command
	@NotifyChange("*")
	public void removeRow(@BindingParam("deleteId") String deleteId) {
		log("++++++++++TRY TO DELETE ROW");
	}

	//TODO узнать как получить результат в ZUL
//	@Command
//	@NotifyChange("*")
//	public String getDay(@BindingParam("d") int d) {
//		log("getDAYYYYY:  "+d);
//		String dayStr="";
//		try {
//			dayStr = Common.getDayOfWeekList().get(d);
//		} catch (Exception e) {logError(e); dayStr="н/д";}
//		return dayStr;
//	}



	public List<String> dayOfWeek = new ArrayList<String>(); {
		dayOfWeek.add("Пн");
		dayOfWeek.add("Вт");
		dayOfWeek.add("Ср");
		dayOfWeek.add("Чт");
		dayOfWeek.add("Пт");
		dayOfWeek.add("Сб");
		dayOfWeek.add("Вс");
	}

	public List<String> getDayOfWeek() {
		return dayOfWeek;
	}
	public void setDayOfWeek(List<String> dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}


	@Command
	public void onProcedureTimeChange(@BindingParam("prcTime") int prcTime, @BindingParam("obj") Object obj) {
			Date date = new Date();
			date.setTime(prcTime);
		((Label)obj).setValue(time2Sign(date.getHours())+":"+time2Sign(date.getMinutes()));
		}

	String time2Sign(int time) {
		if (time<10) return "0"+time; else return ""+time;
	}

	@Command
	@NotifyChange("*")
	public void deleteRow(@BindingParam("id") int id) {
		try {
			log("Deleting row[id:" + id + "]..");
			Connection connection = DriverManager.getConnection("jdbc:sqlite:procedures.db");
			PreparedStatement st = connection.prepareStatement("DELETE FROM "+MySQL.procedure.table.name+" WHERE id = ?");
			st.setInt(1,id);
			st.executeUpdate();
			myProcedures = db.getProcedures();
		} catch (SQLException|ClassNotFoundException e) {logError(e);}
	}

	@Command
	@NotifyChange("*")
	public void saveRow(@BindingParam("id") int id, @BindingParam("component") Component component) {
		try {
			Procedure myProc=new Procedure();
			Component parent = component.getParent();
			log("Saving row[id:" + id + "]..");
			List<Component> children = parent.getChildren();
			for (Component c:children) {
				if (c.getClass()==Textbox.class) {
					Textbox textbox = (Textbox)c;
					String thisName = textbox.getName();
					if (thisName.equals("procedureName")) {log("MyName = "+textbox.getValue()); myProc.setProcedureName(textbox.getValue());myProc.setProcedureId(id);}
					if (thisName.equals("doctorName")) {log("MyDoctorName = "+textbox.getValue());myProc.setDoctorFullName(textbox.getValue());}
					if (thisName.equals("price")) {log("MyPrice = "+textbox.getValue());myProc.setProcedurePrice(Integer.parseInt(textbox.getValue()));}
					if (thisName.equals("roomNumber")) {log("MyRoomNumber = "+textbox.getValue());myProc.setRoomNumber(Integer.parseInt(textbox.getValue()));}
				}
				if (c.getClass()==Listbox.class) {
					Listbox listbox = (Listbox) c;
					String thisName = listbox.getName();
					if (thisName.equals("day")) {log("MyDay = "+listbox.getSelectedIndex()); myProc.setProcedureDay(listbox.getSelectedIndex());}
				}
				if (c.getClass()==Timebox.class) {
					Timebox timebox = (Timebox) c;
					String thisName = timebox.getName();
					long time = timebox.getValue().getTime();

					if (thisName.equals("time")) {log("MyTime = "+timebox.getValue().getTime()); myProc.setProcedureTime(toIntExact(time));}
				}
			}

			String sqlQuery = "UPDATE "+MySQL.procedure.table.name+" SET " +
					procedure.field.name +" = "+ withQuotes(myProc.procedureName) +", "+
					procedure.field.doctorFullName +" = "+ withQuotes(myProc.doctorFullName) +", "+
					procedure.field.price +" = "+ myProc.procedurePrice +", "+
					procedure.field.day +" = "+ myProc.procedureDay +", "+
					procedure.field.time +" = "+ myProc.procedureTime +", "+
					procedure.field.roomNumber +" = "+ myProc.roomNumber +" "+
					"WHERE "+ procedure.field.id +" = "+ id;
			log("sqlQuery: "+sqlQuery);
			Connection connection = DriverManager.getConnection("jdbc:sqlite:procedures.db");
			PreparedStatement st = connection.prepareStatement(sqlQuery);
			st.executeUpdate();
		} catch (SQLException e) {logError(e);}
	}
}
