package ru.hostco.burovalex.webapptest;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.Wire;

public class MyViewModel {


	private int bindValue = 0;
	private String key1 = "WTF";
	private String key2 = "init(Variable) = constant";

	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey2() {
		return key2;
	}
	public int getBindValue() {
		return bindValue;
	}
	public void setBindValue(int bindValue) {
		this.bindValue = bindValue;
	}


	@Init
	public void init() {
		String key1="123";
		//initialization code
	}





	@Command("mycom")
	public void myCommand(@BindingParam("k1") int k1) {
		log("======================+"+k1+"+=================================================");
	}

	@Command("comPlus")
	@NotifyChange("bindValue")
	public void comPlus() {
		setBindValue(bindValue+1);
		log("+");
	}

	@Command
	public void removeRow(@BindingParam("procedureId") int procedureId) {
		log("+++++++++++++++Comand RemoveRow: id="+procedureId);
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

}
