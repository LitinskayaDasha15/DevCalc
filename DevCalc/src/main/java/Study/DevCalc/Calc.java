package Study.DevCalc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calc extends Loan {
	public Calc(int loanAmount, int loanTerm, double interestRate) {
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.interestRate = interestRate;
	}

	@Override
	public double CalculateStaticPayment() {
		double pay = loanAmount * AnnuityRate(loanTerm, interestRate);
		pay = RoundTo(pay, 4);
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

	// Вычисление каждого платежа по крдиту
	public List<Payment> CalculateDetailedPayments() {
		Map<String, String> engRus = new HashMap<String, String>();
		engRus.put("january", "январь");
		engRus.put("february", "февраль");
		engRus.put("march", "март");
		engRus.put("april", "апрель");
		engRus.put("may", "май");
		engRus.put("june", "июнь");
		engRus.put("july", "июль");
		engRus.put("august", "август");
		engRus.put("september", "сентябрь");
		engRus.put("october", "октябрь");
		engRus.put("november", "ноябрь");
		engRus.put("december", "декабрь");

		double staticPayment = CalculateStaticPayment();
		double loanRemain = loanAmount;
		double monthRate = interestRate / 12 / 100;
		double mainPay;
		double percents;
		int count = 0;
		LocalDate dateOfPayment = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);

		List<Payment> payments = new ArrayList<Payment>();
		while (loanRemain >= 0.01) {
			count++;
			percents = RoundTo(loanRemain * monthRate, 4);
			mainPay = RoundTo((loanRemain + percents >= staticPayment) ? staticPayment - percents : loanRemain, 2);
			loanRemain = RoundTo(loanRemain - mainPay, 4);
			dateOfPayment = dateOfPayment.plusMonths(1);

			payments.add(new Payment(count,
					Integer.toString(dateOfPayment.getYear()) + " "
							+ engRus.get(dateOfPayment.getMonth().toString().toLowerCase()),
					RoundTo(percents + mainPay, 2), mainPay, percents, loanRemain));
		}
		return payments;
	}

	public static double RoundTo(double num, int scale) {
		double sc = Math.pow(10, scale);
		return Math.round(num * sc) / sc;
	}

}
