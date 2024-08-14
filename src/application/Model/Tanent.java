package application.Model;

public class Tanent {

	private String name, phone, occupation, leaseStartDate, leaseEndDate;
	private int securityDeposit, propertyID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getLeaseStartDate() {
		return leaseStartDate;
	}

	public void setLeaseStartDate(String leaseStartDate) {
		this.leaseStartDate = leaseStartDate;
	}

	public String getLeaseEndDate() {
		return leaseEndDate;
	}

	public void setLeaseEndDate(String leaseEndDate) {
		this.leaseEndDate = leaseEndDate;
	}

	public int getSecurityDeposit() {
		return securityDeposit;
	}

	public void setSecurityDeposit(int securityDeposit) {
		this.securityDeposit = securityDeposit;
	}

	public int getPropertyID() {
		return propertyID;
	}

	public void setPropertyID(int propertyID) {
		this.propertyID = propertyID;
	}

	public Tanent(String name, String phone, String occupation, String leaseStartDate, String leaseEndDate,
			int securityDeposit, int propertyID) {
		this.name = name;
		this.phone = phone;
		this.occupation = occupation;
		this.leaseStartDate = leaseStartDate;
		this.leaseEndDate = leaseEndDate;
		this.securityDeposit = securityDeposit;
		this.propertyID = propertyID;
	}

	@Override
	public String toString() {
		return "name=" + name + ", phone=" + phone + ", occupation=" + occupation + ", leaseStartDate=" + leaseStartDate
				+ ", leaseEndDate=" + leaseEndDate + ", securityDeposit=" + securityDeposit + ", propertyID="
				+ propertyID;
	}

}
