package Study.DevCalc;

public class Payment {
	private int numOfPay;
	private String dateOfPay;
	private double percents;
	private double deposit;
	
	public Payment() {}
	
	public Payment(int numOfPay, String dateOfPay, double percents, double deposit) {
		this.numOfPay = numOfPay;
		this.dateOfPay = dateOfPay;
		this.percents = percents;
		this.deposit = deposit;
	}
	
	public int getNumOfPay() {
        return numOfPay;
    }
 
    public void setNumOfPay(int numOfPay) {
        this.numOfPay = numOfPay;
    }
    
    public String getdateOfPay() {
        return dateOfPay;
    }
 
    public void setdateOfPay(String dateOfPay) {
        this.dateOfPay = dateOfPay;
    }
    
    public double getPercents() {
        return percents;
    }
 
    public void setPercents(double percents) {
        this.percents = percents;
    }
    
    public double getDeposit() {
        return deposit;
    }
 
    public void setDeposits(double deposit) {
        this.deposit = deposit;
    }
}
