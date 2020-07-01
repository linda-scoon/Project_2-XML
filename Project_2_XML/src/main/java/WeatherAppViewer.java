import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Flourish Task 2-3
 */
public class WeatherAppViewer extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private WeatherApp weatherApp;
	private JButton btnNewButton;
	private JLabel iconLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					WeatherAppViewer frame = new WeatherAppViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public WeatherAppViewer() {

		weatherApp = new WeatherApp();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		iconLabel = new JLabel("");
		iconLabel.setBounds(127, 244, 125, 74);
		contentPane.add(iconLabel);

		JLabel lblNewLabel = new JLabel("URL");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel.setBounds(49, 60, 48, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(82, 57, 251, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setBounds(49, 166, 284, 65);
		contentPane.add(textArea);

		btnNewButton = new JButton("FORECAST");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton.setForeground(Color.RED);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				weatherApp.setUrlString(textField.getText());

				// for debugging
//					System.out.println(weatherApp.getUrlString());

				textField.setText("");
				weatherApp.retrieveReport();
				textArea.setText(weatherApp.getWeatherReport());

				// for debugging
//					System.out.println(weatherApp.getWeatherReport());

				if (weatherApp.getWeatherReport() != null) {
					iconLabel.setIcon(weatherApp.getIcon());
				}
			}
		});
		btnNewButton.setBounds(127, 115, 125, 23);
		contentPane.add(btnNewButton);
	}
}
