package Study.DevCalc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calc extends Deposit {
	public Calc(int depositAmount, int depositTerm, double interestRate, int capitalizationFrequency) {
		this.depositAmount = depositAmount;
		this.depositTerm = depositTerm;
		this.interestRate = interestRate;
		this.capitalizationFrequency = capitalizationFrequency;
	}

	@Override
	public double CalculateDeposit() {

		double pay = depositAmount * Math.pow(1 + interestRate / 100 / capitalizationFrequency, depositTerm);
		pay = RoundTo(pay, 2);
		return pay;

	}

	// Вычисление каждого начисления по дипозиту
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

		double staticDeposit = CalculateDeposit();
		double loanRemain = depositAmount;
		double monthRate = interestRate / 12 / 100;
		double mainPay;
		double percents;
		int count = 0;
		LocalDate dateOfPayment = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonth(), 1);

		List<Payment> payments = new ArrayList<Payment>();
		while (loanRemain >= 0.01) {
			count++;
			percents = RoundTo(loanRemain * monthRate, 4);
			mainPay = RoundTo((loanRemain + percents >= staticDeposit) ? staticDeposit - percents : loanRemain, 4);
			loanRemain = RoundTo(loanRemain - mainPay, 4);
			dateOfPayment = dateOfPayment.plusMonths(1);

			payments.add(new Payment(count,
					Integer.toString(dateOfPayment.getYear()) + " "
							+ engRus.get(dateOfPayment.getMonth().toString().toLowerCase()),
					percents + mainPay, mainPay, percents, loanRemain));
		}
		return payments;
	}

	public static double RoundTo(double num, int scale) {
		double sc = Math.pow(10, scale);
		return Math.round(num * sc) / sc;
	}

}