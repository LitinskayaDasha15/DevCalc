package Study.DevCalc;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.MathContext;
import java.util.List;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField depAmount;
	private JTextField depTerm;
	private JTextField interestRate;
	private JComboBox<String> сomboBoxTerm;
	private JComboBox<String> сomboBoxСapitalizationFrequency;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
//		Заголовок окна
		super("Калькулятор");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 175));
		setResizable(false);
		
		
//		Основной Box
		Box mainBox = Box.createVerticalBox();
		mainBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		
//		Шапка		
		// Box для шапки
		Box boxHead = Box.createVerticalBox();
		boxHead.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
		
		//Заголовок шапки
		JLabel labelHead1 = new JLabel("Параметры депозита");		
		boxHead.add(labelHead1);

		
//		Область вводимых данных
		//Grid для области вводимых данных
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Заголовки
		JLabel labelLoanAmount = new JLabel("Сумма вклада:");
		labelLoanAmount.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(labelLoanAmount, c);
		
		JLabel labelLoanTerm = new JLabel("Срок вклада:");
		labelLoanTerm.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 1;
		pane.add(labelLoanTerm, c);
	
		JLabel labelInterestRate = new JLabel("Процентная ставка:");
		labelInterestRate.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		pane.add(labelInterestRate, c);
		
		JLabel labelСapitalizationFrequency = new JLabel("Периодичность капитализации:");
		labelСapitalizationFrequency.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 3;
		pane.add(labelСapitalizationFrequency, c);
		
		
		//Поля ввода	
		depAmount = new JTextField();
		depAmount.setColumns(15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(depAmount, c);
		
		depTerm = new JTextField();
		depTerm.setColumns(15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(depTerm, c);
		
		interestRate = new JTextField();
		interestRate.setColumns(15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(interestRate, c);
		
		сomboBoxСapitalizationFrequency = new JComboBox<String>(new String[] {"Раз в год", "Раз в полгода", "Раз в квартал", "Раз в месяц"});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 1;
		c.gridy = 3;
		pane.add(сomboBoxСapitalizationFrequency, c);
		
		
		//Поля выбора
		JLabel labelCurrencyUnit = new JLabel("₽");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(labelCurrencyUnit, c);
		
		сomboBoxTerm = new JComboBox<String>(new String[] {"лет", "мес."});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(сomboBoxTerm, c);
		
		JLabel labelRatePeriod = new JLabel("% в год");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		pane.add(labelRatePeriod, c);
		
		JLabel labelHelpCapitalization = new JLabel("[?]");
		labelHelpCapitalization.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		labelHelpCapitalization.setToolTipText("Периодичность, с которой начисленные проценты будут добавляться к сумме вклада.");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 3;
		pane.add(labelHelpCapitalization, c);
		

//		Подвал
		Box boxUnder = Box.createVerticalBox();
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		boxUnder.add(verticalStrut_1);
		
		Box boxButtons = Box.createHorizontalBox();
		
		JButton btnCalculate = new JButton("Расчитать");
		boxButtons.add(btnCalculate);
		
		JButton btnSave = new JButton("Расчитать и сохранить xls");
		boxButtons.add(btnSave);
		
		boxUnder.add(boxButtons);
		
		final JLabel labelResult = new JLabel("");
		boxUnder.add(labelResult);
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		boxUnder.add(verticalStrut_2);
		
		
		mainBox.add(boxHead);
		mainBox.add(pane);
		mainBox.add(boxUnder);
		
		setContentPane(mainBox);
		pack();
		
		
//		Событие по нажатию на кнопку btnCalculate
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				input(labelResult);
			}
		});
		
//		Событие по нажатию на кнопку btnSave		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e)
			{
				Calc pay = input(labelResult);
				if (pay == null)
				{
					return;
				}
				
				List<Payment> pays = pay.CalculateDetailedPayments();
				ExcelWorker excel = new ExcelWorker();
		    	excel.FillData(pays);
  	
		    	//Диалог выбора пути сохранения файла
		    	FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls","xls");
		        JFileChooser fc = new JFileChooser();
		        fc.setFileFilter(filter);
		        fc.setAcceptAllFileFilterUsed(false);
		        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION )
		        {
		            try {
		            	excel.Save(fc.getSelectedFile().toString().endsWith(".xls") ? fc.getSelectedFile().toString() : fc.getSelectedFile() + ".xls");
		            }
		            catch ( Exception exc ) {}
		        }
			}
		});
	}
	
	public Calc input(JLabel labelResult)
	{
		int amount, term, freq;
		double rate;
		try
		{
			amount = Math.abs(Integer.parseInt(depAmount.getText().replace(',', '.')));
			term = Math.abs(Integer.parseInt(depTerm.getText().replace(',', '.')));
			rate = Math.abs(Double.parseDouble(interestRate.getText().replace(',', '.')));
		}
		catch(Exception ex)
		{
			labelResult.setText("Некорректный ввод");
			labelResult.setToolTipText(ex.getMessage());
			pack();
			return null;
		}
		switch (сomboBoxСapitalizationFrequency.getSelectedIndex()) //частота капитализации в год
		{
		case (0):
			freq = 1;
			break;
		case (1):
			freq = 2;
			break;
		case (2):
			freq = 4;
			break;
		case (3):
			freq = 12;
			break;
		default:
			freq = 1;
			break;
		}
		if (сomboBoxTerm.getSelectedIndex() == 0) //0 - лет; 1 - мес.
		{
			term *= 12;
		}
		Calc pay = new Calc(amount, term, rate, freq);
		labelResult.setText("<html>Сумма вклада с процентами: " + Double.toString(pay.CalculateDeposit()) + "₽</html>");
		labelResult.setToolTipText("Итоговая сумма по окончанию срока вклада");
		pack();
		return pay;
	}

}
