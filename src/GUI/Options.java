package GUI;
import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;

public class Options extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void close() {
		this.dispose();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Options frame = new Options();
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
	public Options() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 568);
		getContentPane().setLayout(null);
		JLabel lblEncryptionAndDecryption = new JLabel("Encryption and Decryption:\r\n");
		lblEncryptionAndDecryption.setText(
				"<html>Encryption and Decryption:<br/>providing a very simple and easy GUI for encryption and decryption <br/> user can encrypt a text using any algorithm he choose, <br/>also user can decrypt any cipher text after providing the key.</html>");
		lblEncryptionAndDecryption.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEncryptionAndDecryption.setBounds(10, 224, 414, 131);
		getContentPane().add(lblEncryptionAndDecryption);

		JLabel lblHideunhideInformation = new JLabel(
				"<html>Hide/Unhide Information:<br/> user can hide information inside a PNG image file<br/> also user can reveal information from PNG images (if existed)</html>");
		lblHideunhideInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblHideunhideInformation.setBounds(10, 345, 424, 118);
		getContentPane().add(lblHideunhideInformation);
		JButton encryptionButton = new JButton("Encryption and Decryption");
		encryptionButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EncryptionDecryptionFrame().setVisible(true);
				close();
			}
		});
		encryptionButton.setBounds(91, 66, 224, 57);
		encryptionButton.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JButton hideInfo = new JButton("Hide/Unhide information");
		hideInfo.setBounds(91, 134, 224, 60);
		hideInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SteganographyFrame().setVisible(true);
				close();
			}
		});
		
		hideInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
	JLabel lblChooseOne = new JLabel("Choose one:");
		lblChooseOne.setBounds(164, 23, 90, 32);
		lblChooseOne.setFont(new Font("Tahoma", Font.PLAIN, 16));
		getContentPane().add(encryptionButton);
		getContentPane().add(hideInfo);
		makeTransparentForButton(encryptionButton);
		makeTransparentForButton(hideInfo);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\Windows 10\\Desktop\\d.jpg"));
		label.setBounds(0, 0, 434, 529);
		getContentPane().add(label);
	}
	public static void makeTransparentForButton(JButton x) {
		x.setContentAreaFilled(false);
		x.setBorder(new LineBorder(Color.BLACK));
	}
}
