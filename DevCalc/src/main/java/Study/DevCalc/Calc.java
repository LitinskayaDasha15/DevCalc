package Study.DevCalc;

public class Calc extends Loan {
	public Calc(int loanAmount, int loanTerm, double interestRate) {
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.interestRate = interestRate;
	}

	@Override
	public double CalculateStaticPayment() {
		double pay = loanAmount * AnnuityRate(loanTerm, interestRate);
		pay = RoundTo(pay, 2);
		return pay;

	}

	@Override
	protected double AnnuityRate(int loanTerm, double InterestRate) {
		double rate;
		double m = interestRate / 12 / 100;
		int s = loanTerm;
		rate = (m * Math.pow(1 + m, s)) / (Math.pow(1 + m, s) - 1);
		return rate;

	}

	public static double RoundTo(double num, int scale) {
		double sc = Math.pow(10, scale);
		return Math.round(num * sc) / sc;
	}

}
