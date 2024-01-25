package Study.DevCalc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Calc extends Deposit {
	private LocalDate startDate; //дата открытия счета

	public Calc(int depositAmount, int depositTerm, double interestRate, int capitalizationFrequency) {
		this.depositAmount = depositAmount;
		this.depositTerm = depositTerm;
		this.interestRate = interestRate;
		this.capitalizationFrequency = capitalizationFrequency;
	}

	@Override
	public double CalculateDeposit() {
		List<Payment> payments = CalculateDetailedPayments();
		double pay = payments.get(payments.size() - 1).getDeposit();
		pay = RoundTo(pay, 2);
		return pay;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	// Вычисление каждого начисления по депозиту
	public List<Payment> CalculateDetailedPayments() {
		if (startDate == null) {
			startDate = LocalDate.now();
		}

		List<Payment> payments = new ArrayList<Payment>();
		
		payments.add(new Payment(0, startDate.toString(), 0, depositAmount)); //нулевой платеж (первое внесение капитала)

		LocalDate calcDay = startDate; //хранит дату рассчитываемого дня
		LocalDate dateOfPay = startDate; //хранит дату капитализации
		LocalDate endDate = startDate.plusMonths(depositTerm); //дата последней выплаты

		int period = getPeriodInMonths(); //период между выплатами в месяцах
		int periodBetween = period; //период от даты открытия счета до выплаты. Увеличивается с шагом цикла на значение period
		double depositNow = depositAmount; //хранит последнюю сумму вклада
		double percents = 0; //проценты, накапливающиеся до капитализации

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

	//получение суммы по процентам за один день
	private double getPercentsOfDay(LocalDate date, double dep) {
		if (date.isLeapYear()) {
			return dep * (interestRate / (366*100));
		}
		return dep * (interestRate / (365*100));
	}

	//получение количества месяцев между выплатами
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

	//округление
	public static double RoundTo(double num, int scale) {
		double sc = Math.pow(10, scale);
		return Math.round(num * sc) / sc;
	}

}