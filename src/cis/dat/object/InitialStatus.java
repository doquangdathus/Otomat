package cis.dat.object;

public class InitialStatus extends Status{
	private String finshStatus;
	
	public InitialStatus(String status) {
		super(status);
	}
	
	public String getFinshStatus() {
		return finshStatus;
	}
	public void setFinshStatus(String finshStatus) {
		this.finshStatus = finshStatus;
	}
	
	public boolean isInitialStatus(){
		return true;
	}
}
