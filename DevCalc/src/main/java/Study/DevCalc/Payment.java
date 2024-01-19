package Study.DevCalc;

public class Payment {
	private int numOfPay;
	private String dateOfPay;
	private double sumOfPay;
	private double mainDebt;
	private double percents;
	private double remainDebt;
	
	public Payment() {}
	
	public Payment(int numOfPay, String dateOfPay, double sumOfPay, double mainDebt, double percents, double remainDebt) {
		this.numOfPay = numOfPay;
		this.dateOfPay = dateOfPay;
		this.sumOfPay = sumOfPay;
		this.mainDebt = mainDebt;
		this.percents = percents;
		this.remainDebt = remainDebt;
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
    
    public double getSumOfPay() {
        return sumOfPay;
    }
 
    public void setSumOfPay(double sumOfPay) {
        this.sumOfPay = sumOfPay;
    }
    
    public double getMainDebt() {
        return mainDebt;
    }
 
    public void setMainDebt(double mainDebt) {
        this.mainDebt = mainDebt;
    }
    
    public double getPercents() {
        return percents;
    }
 
    public void setPercents(double percents) {
        this.percents = percents;
    }
    
    public double getRemainDebt() {
        return remainDebt;
    }
 
    public void setRemainDebt(double remainDebt) {
        this.remainDebt = remainDebt;
    }
}
