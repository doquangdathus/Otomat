package cis.dat.object;

public class Transform {
	private Status beginStatus;
	private String alphabet;
	private Status endStatus;
	public Transform(String beginStatus, String endStatus, String alphabet) {
		this.beginStatus = new Status(beginStatus);
		this.endStatus = new Status(endStatus);
		this.alphabet = alphabet;
	}
	public Status getBeginStatus() {
		return beginStatus;
	}
	public void setBeginStatus(Status beginStatus) {
		this.beginStatus = beginStatus;
	}
	public String getAlphabet() {
		return alphabet;
	}
	public void setAlphabet(String alphabet) {
		this.alphabet = alphabet;
	}
	public Status getEndStatus() {
		return endStatus;
	}
	public void setEndStatus(Status endStatus) {
		this.endStatus = endStatus;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return beginStatus.getStatus() + " " + alphabet + " " + endStatus;
	}
}
