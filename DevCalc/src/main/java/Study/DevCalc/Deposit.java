package Study.DevCalc;

import java.util.List;

public abstract class Deposit {
	// Сумма депозита
	int depositAmount;

	// Срок депозита
	int depositTerm;

	// Процентная ставка
	double interestRate;
	
	// Частота капитализации в год
	int capitalizationFrequency;

	// Рассчитать итоговую сумму депозита
	public abstract double CalculateDeposit();
	
	// Детальный расчет выплат
	public abstract List<Payment> CalculateDetailedPayments();
}