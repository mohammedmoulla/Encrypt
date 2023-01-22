package GUI;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Image;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
public class FirstFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstFrame frame = new FirstFrame();
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
	public void close() {
		this.dispose();
	}

	public FirstFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 661);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblProject = new JLabel("\u062A\u0634\u0641\u064A\u0631 \u0648\u0625\u062E\u0641\u0627\u0621 \u0627\u0644\u0645\u0639\u0644\u0648\u0645\u0627\u062A");
		lblProject.setForeground(Color.RED);
		lblProject.setFont(new Font("Traditional Arabic", Font.BOLD, 25));
		lblProject.setBounds(124, 181, 198, 34);
		contentPane.add(lblProject);

		JLabel lblAbd = new JLabel("عبد الرزاق مصطفى");
		lblAbd.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
		lblAbd.setBounds(155, 231, 113, 21);
		contentPane.add(lblAbd);

		JLabel lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(10, 0, 145, 165);
		contentPane.add(lblNewLabel);

		JButton button = new JButton("\u0627\u0644\u062A\u0627\u0644\u064A");
		Image img1 = new ImageIcon(this.getClass().getResource("/Next_Icon.png")).getImage();
		button.setIcon(new ImageIcon(img1));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Options().setVisible(true);
				close();
			}
		});
		button.setBounds(167, 552, 113, 40);
		contentPane.add(button);
		
		JLabel lblYousef = new JLabel("\u064A\u0648\u0633\u0641 \u064A\u062D\u064A\u0649 \u0643\u0641\u0627");
		lblYousef.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
		lblYousef.setBounds(167, 252, 113, 21);
		contentPane.add(lblYousef);
		
		JLabel lblMoulla = new JLabel("\u0645\u062D\u0645\u062F \u0647\u064A\u062B\u0645 \u0645\u0639\u0644\u0627");
		lblMoulla.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
		lblMoulla.setBounds(167, 274, 99, 21);
		contentPane.add(lblMoulla);
		
		JLabel lblSalam = new JLabel("\u0633\u0644\u0627\u0645 \u0642\u0631\u062D\u064A\u0644\u064A");
		lblSalam.setFont(new Font("Traditional Arabic", Font.PLAIN, 20));
		lblSalam.setBounds(181, 295, 85, 21);
		contentPane.add(lblSalam);
		
		JLabel label = new JLabel("\u0627\u0644\u0637\u0644\u0627\u0628:");
		label.setBounds(197, 216, 48, 14);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u0625\u0634\u0631\u0627\u0641:");
		label_1.setBounds(193, 350, 86, 15);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("\u062F. \u0628\u0633\u064A\u0645 \u0628\u0631\u0647\u0648\u0645");
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		label_2.setBounds(155, 379, 136, 21);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("جامعة تشرين");
		label_3.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		label_3.setBounds(349, 20, 85, 21);
		contentPane.add(label_3);
		
		JLabel label_4 = new JLabel("كلية الهندسة المعلوماتية");
		label_4.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		label_4.setBounds(277, 53, 150, 21);
		contentPane.add(label_4);
		
		JLabel label_5 = new JLabel("قسم البرمجيات ونظم المعلومات");
		label_5.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		label_5.setBounds(224, 84, 200, 24);
		contentPane.add(label_5);
		
		JLabel label_6 = new JLabel("السنة الرابعة");
		label_6.setFont(new Font("Traditional Arabic", Font.BOLD, 22));
		label_6.setBounds(349, 119, 75, 21);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("العام الدراسي 2018-2019");
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_7.setBounds(141, 443, 181, 21);
		contentPane.add(label_7);
	}
}
