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

import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField loanAmount;
	private JTextField loanTerm;
	private JTextField annualInterestRate;
	private JComboBox<String> сomboBoxTerm;

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
		JLabel labelHead1 = new JLabel("Параметры кредита");		
		boxHead.add(labelHead1);

		
//		Область вводимых данных
		//Grid для области вводимых данных
		JPanel pane = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Заголовки
		JLabel labelLoanAmount = new JLabel("Сумма кредита:");
		labelLoanAmount.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 0;
		c.gridy = 0;
		pane.add(labelLoanAmount, c);
		
		JLabel labelLoanTerm = new JLabel("Срок кредита:");
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
		
		
		//Поля ввода	
		loanAmount = new JTextField();
		loanAmount.setColumns(15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 0;
		pane.add(loanAmount, c);
		
		loanTerm = new JTextField();
		loanTerm.setColumns(15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		pane.add(loanTerm, c);
		
		annualInterestRate = new JTextField();
		annualInterestRate.setColumns(15);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		pane.add(annualInterestRate, c);
		
		
		//Поля выбора
		JLabel labelCurrencyUnit = new JLabel("₽");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridy = 0;
		pane.add(labelCurrencyUnit, c);
		
		сomboBoxTerm = new JComboBox<String>(new String[] {"лет", "мес."});
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.0;
		c.gridx = 2;
		c.gridy = 1;
		pane.add(сomboBoxTerm, c);
		
		JLabel labelRatePeriod = new JLabel("% в год");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 2;
		pane.add(labelRatePeriod, c);
		

//		Подвал
		Box boxUnder = Box.createVerticalBox();
		
		JButton btnCalculate = new JButton("Расчитать");
		boxUnder.add(btnCalculate);
		
		JButton btnSave = new JButton("Расчитать и сохранить xls");
		boxUnder.add(btnSave);
		
		final JLabel labelResult = new JLabel();
		boxUnder.add(labelResult);
		
		
		mainBox.add(boxHead);
		mainBox.add(pane);
		mainBox.add(boxUnder);
		
		setContentPane(mainBox);
		pack();
		
		
//		Событие по нажатию на кнопку btnCalculate
		btnCalculate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int amount, term;
				double rate;
				try
				{
					amount = Integer.parseInt(loanAmount.getText());
					term = Integer.parseInt(loanTerm.getText());
					rate = Double.parseDouble(annualInterestRate.getText());
				}
				catch(Exception ex)
				{
					labelResult.setText("Некорректный ввод");
					labelResult.setToolTipText(ex.getMessage());
					pack();
					return;
				}
				if (сomboBoxTerm.getSelectedIndex() == 0) //0 - лет, 1 - мес.
				{
					term *= 12;
				}
				Calc pay = new Calc(amount, term, rate);
				labelResult.setText("Ежемесячный платеж: " + Double.toString(pay.CalculateStaticPayment()) + "₽");
				labelResult.setToolTipText("Размер платежа");
				pack();
			}
		});
		
//		Событие по нажатию на кнопку btnSave		
		btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int amount, term;
				double rate;
				try
				{
					amount = Integer.parseInt(loanAmount.getText());
					term = Integer.parseInt(loanTerm.getText());
					rate = Double.parseDouble(annualInterestRate.getText());
				}
				catch(Exception ex)
				{
					labelResult.setText("Некорректный ввод");
					labelResult.setToolTipText(ex.getMessage());
					pack();
					return;
				}
				if (сomboBoxTerm.getSelectedIndex() == 0) //0 - лет, 1 - мес.
				{
					term *= 12;
				}
				Calc pay = new Calc(amount, term, rate);
				labelResult.setText("Ежемесячный платеж: " + Double.toString(pay.CalculateStaticPayment()) + "₽");
				labelResult.setToolTipText("Размер платежа");
				pack();
				List<Payment> pays = pay.CalculateDetailedPayments();
				ExcelWorker excel = new ExcelWorker();
		    	excel.FillData(pays);
  	
		    	//Диалог выбора пути сохранения файла
		    	FileNameExtensionFilter filter = new FileNameExtensionFilter(".xls","xls");
		        JFileChooser fc = new JFileChooser();
		        fc.setFileFilter(filter);
		        fc.setAcceptAllFileFilterUsed(false);
		        if ( fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION ) {
		            try {
		            	excel.Save(fc.getSelectedFile().toString().endsWith(".xls") ? fc.getSelectedFile().toString() : fc.getSelectedFile() + ".xls");
		            }
		            catch ( Exception exc ) {}
		        }
			}
		});
	}

}
