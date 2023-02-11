package projet;

import java.sql.Date;

public class BOOKING_HISTORY {
	int ID;
	String EVtype;
	String Place;
	int NumG;
	Date date; 
	int IDv;
	String FoodT;
	
	

	public BOOKING_HISTORY(int iD, String eVtype, String place, int numG, Date date, int iDv, String foodT) {
		ID = iD;
		EVtype = eVtype;
		Place = place;
		NumG = numG;
		this.date = date;
		IDv = iDv;
		FoodT = foodT;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getEVtype() {
		return EVtype;
	}

	public void setEVtype(String eVtype) {
		EVtype = eVtype;
	}

	public String getPlace() {
		return Place;
	}

	public void setPlace(String place) {
		Place = place;
	}

	public int getNumG() {
		return NumG;
	}

	public void setNumG(int numG) {
		NumG = numG;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getIDv() {
		return IDv;
	}

	public void setIDv(int iDv) {
		IDv = iDv;
	}

	public String getFoodT() {
		return FoodT;
	}

	public void setFoodT(String foodT) {
		FoodT = foodT;
	}
	
	
}
