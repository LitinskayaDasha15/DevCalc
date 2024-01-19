package Study.DevCalc;

public abstract class Loan {

	int loanAmount;
	int loanTerm;
	double interestRate;

	public abstract double CalculateStaticPayment();

	protected abstract double AnnuityRate(int loanTerm, double InterestRate);
}