package projet;

public class LIEU {
	int ID;
	String NAME;
	String ADRESS;
	String PhoneNUM;
	int CAPACITY;
	String EVt;
	
	
	public LIEU(int iD, String nAME, String aDRESS, String phoneNUM, int cAPACITY, String eVt) {
		ID = iD;
		NAME = nAME;
		ADRESS = aDRESS;
		PhoneNUM = phoneNUM;
		CAPACITY = cAPACITY;
		EVt = eVt;
	}
	
	
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getNAME() {
		return NAME;
	}
	public void setNAME(String nAME) {
		NAME = nAME;
	}
	public String getADRESS() {
		return ADRESS;
	}
	public void setADRESS(String aDRESS) {
		ADRESS = aDRESS;
	}
	public String getPhoneNUM() {
		return PhoneNUM;
	}
	public void setPhoneNUM(String phoneNUM) {
		PhoneNUM = phoneNUM;
	}
	public int getCAPACITY() {
		return CAPACITY;
	}
	public void setCAPACITY(int cAPACITY) {
		CAPACITY = cAPACITY;
	}
	public String getEVt() {
		return EVt;
	}
	public void setEVt(String eVt) {
		EVt = eVt;
	}
	
	
}
