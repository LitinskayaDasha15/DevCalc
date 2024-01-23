package Study.DevCalc;

import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Calc extends Deposit {
	private LocalDate startDate;

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

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// Вычисление каждого начисления по дипозиту
	public List<Payment> CalculateDetailedPayments() {
		if (startDate == null) {
			startDate = LocalDate.now();
		}

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

		List<Payment> payments = new ArrayList<Payment>();
		
		payments.add(new Payment(0, startDate.toString(), 0, depositAmount));

		LocalDate calcDay = startDate;
		LocalDate dateOfPay = startDate;
		LocalDate endDate = startDate.plusMonths(depositTerm/capitalizationFrequency);

		int period = getPeriodInMonths();
		int periodBetween = period;
		double depositNow = depositAmount;
		double percents = 0;

		int counter = 0;
		while (dateOfPay.compareTo(endDate) < 0) {
			counter++;
			if (startDate.plusMonths(periodBetween).compareTo(endDate) <= 0) {
				dateOfPay = startDate.plusMonths(periodBetween);
			} else {
				dateOfPay = endDate;
			}
			while (calcDay.compareTo(dateOfPay) < 0) {
				calcDay = calcDay.plusDays(1);
				percents += RoundTo(getPercentsOfDay(calcDay, depositNow),4);
			}
			
			depositNow += percents;

			payments.add(new Payment(counter, dateOfPay.toString(), percents, depositNow));

			percents = 0;

			periodBetween += period;
		}

		return payments;
	}

	private double getPercentsOfDay(LocalDate date, double dep) {
		if (date.isLeapYear()) {
			return dep * (interestRate / (366*100));
		}
		return dep * (interestRate / (365*100));
	}

	private int getPeriodInMonths() {
		int res;
		switch (capitalizationFrequency) {
		case (1):
			res = 12;
			break;
		case (2):
			res = 6;
			break;
		case (4):
			res = 3;
			break;
		case (12):
			res = 1;
			break;
		default:
			res = 12;
			break;
		}
		return res;
	}

	public static LocalDate getLastDayOfMonth(LocalDate date) {
		return date.withDayOfMonth(date.getMonth().length(date.isLeapYear()));
	}

	public static double RoundTo(double num, int scale) {
		double sc = Math.pow(10, scale);
		return Math.round(num * sc) / sc;
	}

}