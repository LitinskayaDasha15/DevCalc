package Study.DevCalc;

public abstract class Loan {

	int loanAmount;
	int loanTerm;
	double interestRate;

	public abstract void CalculateStaticPayment();

	public abstract void AnnuityRate();
}
