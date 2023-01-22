package GUI;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import IO.FilterTheFiles;
import IO.IOutils;
import Steganography.Stego;
public class SteganographyFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String topImageTT = "The image in which your file/message will be hidden.";
	String bottomFileTT = "The file you want to hide.";
	String stegFileTT = "The steganographic image from which your file/message will be extracted.";
	private File fcCurDir;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SteganographyFrame frame = new SteganographyFrame();
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
	public SteganographyFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 529);
		setTitle("Steganography");
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		JLabel lblNewLabel = new JLabel("Choose one:");
		lblNewLabel.setBounds(10, 5, 93, 14);
		contentPane.add(lblNewLabel);
		JTextField tfStegFile = new JTextField();
		tfStegFile.setToolTipText(stegFileTT);
		tfStegFile.setBounds(297, 78, 378, 20);
		tfStegFile.setVisible(false);
		contentPane.add(tfStegFile);
		JButton btnStegFile = new JButton("Choose");
		btnStegFile.setToolTipText(stegFileTT);
		btnStegFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] textFiles = new String[] { ".png" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("images Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tfStegFile.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();}
		});
		btnStegFile.setBounds(685, 78, 89, 23);
		btnStegFile.setVisible(false);
		contentPane.add(btnStegFile);
		tfStegFile.setColumns(10);
		JLabel lblStegFile = new JLabel("Steg File:");
		lblStegFile.setToolTipText(stegFileTT);
		lblStegFile.setBounds(224, 82, 63, 14);
		lblStegFile.setVisible(false);
		contentPane.add(lblStegFile);

		JTextField tfTopImage = new JTextField();
		tfTopImage.setBounds(297, 27, 378, 20);
		tfTopImage.setToolTipText(topImageTT);
		contentPane.add(tfTopImage);
		tfTopImage.setColumns(10);

		JButton btnTopImage = new JButton("Choose");
		btnTopImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] textFiles = new String[] { ".png" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("images Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tfTopImage.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();}
		});
		btnTopImage.setBounds(685, 26, 89, 23);
		btnTopImage.setToolTipText(topImageTT);
		contentPane.add(btnTopImage);
		JScrollPane scrollMsg = new JScrollPane();
		scrollMsg.setBounds(297, 52, 477, 91);
		contentPane.add(scrollMsg);
		JTextArea taMsg = new JTextArea();
		taMsg.setFont(new Font("Arial", Font.PLAIN, 14));
		scrollMsg.setViewportView(taMsg);
		taMsg.setWrapStyleWord(true);
		taMsg.setLineWrap(true);
		scrollMsg.setVisible(false);
		taMsg.setVisible(false);
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(224, 56, 63, 14);
		lblMessage.setVisible(false);
		contentPane.add(lblMessage);
		JTextField tfBottomFile = new JTextField();
		tfBottomFile.setBounds(297, 53, 378, 20);
		tfBottomFile.setToolTipText(bottomFileTT);
		contentPane.add(tfBottomFile);
		tfBottomFile.setColumns(10);
		JLabel bottomFileLbl = new JLabel("Bottom File:");
		bottomFileLbl.setBounds(224, 56, 73, 14);
		bottomFileLbl.setToolTipText(bottomFileTT);
		contentPane.add(bottomFileLbl);
		JButton bottomFileChoose = new JButton("Choose");
		bottomFileChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] textFiles = new String[] { ".txt" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("text Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					tfBottomFile.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();
			}
		});
		bottomFileChoose.setBounds(685, 52, 89, 23);
		bottomFileChoose.setToolTipText(bottomFileTT);
		contentPane.add(bottomFileChoose);
		JLabel lblTopImage = new JLabel("Top Image:");
		lblTopImage.setBounds(224, 30, 63, 14);
		lblTopImage.setToolTipText(topImageTT);
		contentPane.add(lblTopImage);
		JLabel lblAbout = new JLabel("About");
		lblAbout.setHorizontalAlignment(SwingConstants.CENTER);
		lblAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "by youssef kafa, abdalrazak almustafa, mohammed moualla");
			}
		});
		lblAbout.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblAbout.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAbout.setForeground(Color.BLUE);
		lblAbout.setBounds(728, 5, 46, 14);
		contentPane.add(lblAbout);
		JRadioButton rdbtnHideAMessage = new JRadioButton("Hide a message");
		rdbtnHideAMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bottomFileChoose.setVisible(false);
				bottomFileLbl.setVisible(false);
				tfBottomFile.setVisible(false);
				lblMessage.setVisible(true);
				scrollMsg.setVisible(true);
				taMsg.setVisible(true);
				lblStegFile.setVisible(false);
				btnStegFile.setVisible(false);
				tfStegFile.setVisible(false);
				lblTopImage.setVisible(true);
				tfTopImage.setVisible(true);
				btnTopImage.setVisible(true);
			}
		});
		rdbtnHideAMessage.setBounds(10, 52, 158, 23);
		contentPane.add(rdbtnHideAMessage);
		JRadioButton rdbtnHideAFile = new JRadioButton("Hide a file");
		rdbtnHideAFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				bottomFileChoose.setVisible(true);
				bottomFileLbl.setVisible(true);
				tfBottomFile.setVisible(true);
				lblMessage.setVisible(false);
				scrollMsg.setVisible(false);
				taMsg.setVisible(false);
				lblStegFile.setVisible(false);
				btnStegFile.setVisible(false);
				tfStegFile.setVisible(false);
				lblTopImage.setVisible(true);
				tfTopImage.setVisible(true);
				btnTopImage.setVisible(true);
			}
		});
		rdbtnHideAFile.setSelected(true);
		rdbtnHideAFile.setBounds(10, 26, 109, 23);
		contentPane.add(rdbtnHideAFile);
		JRadioButton rdbtnRevealFilemesssage = new JRadioButton("Reveal file/messsage");
		rdbtnRevealFilemesssage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblStegFile.setVisible(true);
				btnStegFile.setVisible(true);
				tfStegFile.setVisible(true);
				bottomFileChoose.setVisible(false);
				bottomFileLbl.setVisible(false);
				tfBottomFile.setVisible(false);
				lblMessage.setVisible(false);
				scrollMsg.setVisible(false);
				taMsg.setVisible(false);
				lblTopImage.setVisible(false);
				tfTopImage.setVisible(false);
				btnTopImage.setVisible(false);
			}
		});
		rdbtnRevealFilemesssage.setBounds(10, 78, 158, 23);
		contentPane.add(rdbtnRevealFilemesssage);
		// avoiding multiple radio button selection for hideMsg&hideFile&reveal
		ButtonGroup group3 = new ButtonGroup();
		group3.add(rdbtnHideAMessage);
		group3.add(rdbtnHideAFile);
		group3.add(rdbtnRevealFilemesssage);

		JButton btnDoIt = new JButton("Do it!");
		btnDoIt.setBounds(14, 108, 120, 35);
		contentPane.add(btnDoIt);

		ImageIcon image = new ImageIcon("D:\\cover.png");
		JLabel lblImage = new JLabel(image);
		lblImage.setBounds(10, 142, 764, 298);
		lblImage.setVisible(true);
		contentPane.add(lblImage);

		JButton btnBack = new JButton("back");
		btnBack.setBounds(659, 458, 103, 31);
		Image img1 = new ImageIcon(this.getClass().getResource("/back_icon.png")).getImage();
		btnBack.setIcon(new ImageIcon(img1));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Options().setVisible(true);
				close();
			}
		});
		contentPane.add(btnBack);
		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(543, 458, 109, 31);
		Image img2 = new ImageIcon(this.getClass().getResource("/reset.png")).getImage();
		btnReset.setIcon(new ImageIcon(img2));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

			}
		});
		contentPane.add(btnReset);
		JButton btnDecryption = new JButton("Decryption");
		btnDecryption.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnDecryption.setBounds(413, 458, 120, 31);
		contentPane.add(btnDecryption);
		btnDecryption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EncryptionDecryptionFrame().setVisible(true);
				close();
			}
		});
		btnDoIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			if(rdbtnHideAFile.isSelected() || rdbtnHideAMessage.isSelected() ||rdbtnRevealFilemesssage.isSelected() ){
		try {
			if(rdbtnHideAFile.isSelected())
			Stego.hide(tfBottomFile.getText(), tfTopImage.getText());
			else if (rdbtnHideAMessage.isSelected()) {
				IOutils.writeAfile("D:\\text", taMsg.getText());
				Stego.hide("D:\\text.txt", tfTopImage.getText());}
				else if (rdbtnRevealFilemesssage.isSelected()) {
				Stego.reveal(tfStegFile.getText());
				}
		} catch (IOException e) {
			e.printStackTrace();
		}
			}
			}
		});
	}

	public void close() {
		this.dispose();
	}
}
