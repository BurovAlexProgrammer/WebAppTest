package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zul.*;
import ru.hostco.burovalex.webapptest.MySQL.procedure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static ru.hostco.burovalex.webapptest.MySQL.withQuotes;
import static ru.hostco.burovalex.webapptest.Common.*;

public class MyViewModel {

	public boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private boolean isAdmin = false;
	public String password = "";
	private final String adminPassword = "123";
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
	public void onProcedureTimeChange(@BindingParam("prcTime") Date prcTime, @BindingParam("obj") Object obj) {
		((Label)obj).setValue(time2Sign(prcTime.getHours())+":"+time2Sign(prcTime.getMinutes()));
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
					if (thisName.equals("procedureName")) {myProc.setProcedureName(textbox.getValue());myProc.setProcedureId(id);}
					if (thisName.equals("doctorName")) {myProc.setDoctorFullName(textbox.getValue());}
					if (thisName.equals("price")) {myProc.setProcedurePrice(Integer.parseInt(textbox.getValue()));}
					if (thisName.equals("roomNumber")) {myProc.setRoomNumber(Integer.parseInt(textbox.getValue()));}
				}
				if (c.getClass()==Listbox.class) {
					Listbox listbox = (Listbox) c;
					String thisName = listbox.getName();
					if (thisName.equals("day")) {myProc.setProcedureDay(listbox.getSelectedIndex());}
				}
				if (c.getClass()==Timebox.class) {
					Timebox timebox = (Timebox) c;
					String thisName = timebox.getName();
					long time = timebox.getValue().getTime();
					if (thisName.equals("time")) {
						Date dTime = new Date();
						dTime.setTime(time);
						myProc.setProcedureTime(dTime);
					}
				}
			}

			String sqlQuery = "UPDATE "+MySQL.procedure.table.name+" SET " +
					procedure.field.name +" = "+ withQuotes(myProc.procedureName) +", "+
					procedure.field.doctorFullName +" = "+ withQuotes(myProc.doctorFullName) +", "+
					procedure.field.price +" = "+ myProc.procedurePrice +", "+
					procedure.field.day +" = "+ myProc.procedureDay +", "+
					procedure.field.time +" = "+ myProc.procedureTime.getTime() +", "+
					procedure.field.roomNumber +" = "+ myProc.roomNumber +" "+
					"WHERE "+ procedure.field.id +" = "+ id;
			log("sqlQuery: "+sqlQuery);
			Connection connection = DriverManager.getConnection("jdbc:sqlite:procedures.db");
			PreparedStatement st = connection.prepareStatement(sqlQuery);
			st.executeUpdate();
		} catch (SQLException e) {logError(e);}
	}

	@Command
	@NotifyChange("myProcedures")
	public void addRow() {
		try {
			String sqlQuery = "INSERT INTO "+MySQL.procedure.table.name+
					" ("+procedure.field.time+") VALUES ("+10800000+")";
			log("sqlQuery: "+sqlQuery);
			Connection connection = DriverManager.getConnection("jdbc:sqlite:procedures.db");
			PreparedStatement st = connection.prepareStatement(sqlQuery);
			st.executeUpdate();
			init();
		} catch (SQLException e) {logError(e);}
	}

	@Command
	@NotifyChange("isAdmin")
	public void authAdmin() {
		if (!isAdmin & password.equals(adminPassword)) isAdmin=true; else isAdmin=false;
	}
}
