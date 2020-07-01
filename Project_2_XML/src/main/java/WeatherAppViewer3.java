import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author Flourish Task 5
 */
public class WeatherAppViewer3 extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea;
	private WeatherApp3 weatherApp3;
	private JButton btnNewButton;
	private JLabel iconLabel;
	private JList<String> list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					WeatherAppViewer3 frame = new WeatherAppViewer3();
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
	public WeatherAppViewer3() {

		weatherApp3 = new WeatherApp3();

		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		iconLabel = new JLabel("");
		iconLabel.setBounds(127, 11, 125, 74);
		contentPane.add(iconLabel);

		JLabel lblNewLabel = new JLabel("LOCATION:");
		lblNewLabel.setForeground(new Color(46, 139, 87));
		lblNewLabel.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		lblNewLabel.setBounds(36, 91, 93, 14);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(127, 90, 206, 20);
		contentPane.add(textField);
		textField.setColumns(10);

		textArea = new JTextArea();
		textArea.setBounds(36, 179, 297, 30);
		contentPane.add(textArea);

		btnNewButton = new JButton("FORECAST");
		btnNewButton.setFont(new Font("Yu Gothic UI", Font.BOLD, 14));
		btnNewButton.setForeground(new Color(46, 139, 87));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				weatherApp3.retrieveLocation(textField.getText());
				weatherApp3.retrieveReport();
				textArea.setText(weatherApp3.getWeatherReport());
				textField.setText("");

				if (weatherApp3.getWeatherReport() != null) {
					iconLabel.setIcon(weatherApp3.getIcon());
					list.setListData(weatherApp3.getDescParams());
				}

				// for debugging
				System.out.println(Arrays.toString((weatherApp3.getDescParams())));
			}
		});
		btnNewButton.setBounds(96, 134, 125, 23);
		contentPane.add(btnNewButton);

		list = new JList<String>();
		list.setBounds(36, 220, 297, 175);
		contentPane.add(list);
	}
}
