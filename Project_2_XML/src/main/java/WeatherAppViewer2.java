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
 * @author Flourish Task 4
 */
public class WeatherAppViewer2 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private WeatherApp2 weatherApp2;
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
					WeatherAppViewer2 frame = new WeatherAppViewer2();
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
	public WeatherAppViewer2() {

		weatherApp2 = new WeatherApp2();

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

		JLabel lblNewLabel = new JLabel("LOCATION:");
		lblNewLabel.setForeground(new Color(128, 0, 128));
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel.setBounds(36, 58, 93, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(127, 57, 206, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setBounds(36, 166, 297, 65);
		contentPane.add(textArea);

		btnNewButton = new JButton("FORECAST");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(128, 0, 128));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				weatherApp2.retrieveLocation(textField.getText());
				weatherApp2.retrieveReport();
				textArea.setText(weatherApp2.getWeatherReport());
				textField.setText("");

				if (weatherApp2.getWeatherReport() != null) {
					iconLabel.setIcon(weatherApp2.getIcon());
				}

				// for debugging
				System.out.println(weatherApp2.getWeatherReport());
			}
		});
		btnNewButton.setBounds(127, 115, 125, 23);
		contentPane.add(btnNewButton);
	}
}
