package GUI;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import AES.AES;
import BlowFish.BlowFishEnc;
import Details.DetailsMethods;
import IDEA.Convert;
import IDEA.IDEA;
import IDEA.Key;
import IO.FilterTheFiles;
import IO.IOutils;
import javax.swing.JTextArea;
import javax.swing.JTextField;
public class EncryptionDecryptionFrame extends JFrame {
	static String e="";
	private JPanel contentPane;
	private static final long serialVersionUID = 1L;
	private static JTextField textFieldKeyPath;
	private static JTextField textFieldForText;
	JButton btnGenerateKeys;
	static PrintWriter writer;
	private File fcCurDir;
	static JTextArea textAreaForKey;
	static JTextArea textAreaForText;
	static JRadioButton textInputRadioButton;
	static JRadioButton fileInputRadioButton;
	static JCheckBox chckbxAsciiMessage;
	static JCheckBox chckbxAsciiKey;
	static JCheckBox chckbxHexadecimalMessage;
	static JCheckBox chckbxHexadecimalKey;
	static JCheckBox chckbxBinaryMessage;
	static JCheckBox chckbxBinaryKey;
	static JCheckBox chckbxCalculateBits;
	static JCheckBox chckbxCalculateBytes;
	static JCheckBox chckbxCalculateWords;
	static JRadioButton textInputRadioButtonForText;
	static JRadioButton fileInputRadioButtonForText;
	static JTextArea textAreaResults;
	/**
	 * Launch the application.
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptionDecryptionFrame frame = new EncryptionDecryptionFrame();
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
	public EncryptionDecryptionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 532);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JButton encryptButton = new JButton("Encrpt");
		encryptButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		encryptButton.setBounds(151, 296, 136, 23);
		makeTransparentForButton(encryptButton);
		contentPane.add(encryptButton);

		JButton btnAddDetails = new JButton("add details");
		btnAddDetails.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnAddDetails.setBounds(151, 438, 136, 23);
		makeTransparentForButton(btnAddDetails);
		contentPane.add(btnAddDetails);

		JLabel lblResults = new JLabel("Results:");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblResults.setBounds(468, 10, 63, 17);
		contentPane.add(lblResults);
		Color color = new Color(240, 240, 250);

		JButton decryptButton = new JButton("Decrypt");
		decryptButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		decryptButton.setBounds(151, 296, 136, 25);
		makeTransparentForButton(decryptButton);
		contentPane.add(decryptButton);
		decryptButton.setVisible(false);

		JLabel lblChooseTheDetails = new JLabel("choose the details you want (for inputs) :");
		lblChooseTheDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblChooseTheDetails.setBounds(10, 321, 318, 23);
		contentPane.add(lblChooseTheDetails);

		chckbxAsciiMessage = new JCheckBox("ASCII message");
		chckbxAsciiMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxAsciiMessage.setBounds(0, 351, 136, 23);
		makeTransparentForCheckBox(chckbxAsciiMessage);
		contentPane.add(chckbxAsciiMessage);

		chckbxAsciiKey = new JCheckBox("ASCII key");
		chckbxAsciiKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxAsciiKey.setBounds(129, 351, 111, 23);
		makeTransparentForCheckBox(chckbxAsciiKey);
		contentPane.add(chckbxAsciiKey);

		 chckbxHexadecimalMessage = new JCheckBox("Hexadecimal message");
		chckbxHexadecimalMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxHexadecimalMessage.setBounds(238, 351, 189, 23);
		makeTransparentForCheckBox(chckbxHexadecimalMessage);
		contentPane.add(chckbxHexadecimalMessage);

		chckbxHexadecimalKey = new JCheckBox("Hexadecimal key");
		chckbxHexadecimalKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxHexadecimalKey.setBounds(0, 377, 152, 23);
		makeTransparentForCheckBox(chckbxHexadecimalKey);
		contentPane.add(chckbxHexadecimalKey);

		 chckbxBinaryMessage = new JCheckBox("Binary message");
		chckbxBinaryMessage.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxBinaryMessage.setBounds(151, 377, 136, 23);
		makeTransparentForCheckBox(chckbxBinaryMessage);
		contentPane.add(chckbxBinaryMessage);

		 chckbxBinaryKey = new JCheckBox("Binary key");
		chckbxBinaryKey.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxBinaryKey.setBounds(286, 377, 111, 23);
		makeTransparentForCheckBox(chckbxBinaryKey);
		contentPane.add(chckbxBinaryKey);

		 chckbxCalculateBits = new JCheckBox("Calculate bits");
		chckbxCalculateBits.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxCalculateBits.setBounds(0, 403, 136, 23);
		makeTransparentForCheckBox(chckbxCalculateBits);
		contentPane.add(chckbxCalculateBits);

		chckbxCalculateBytes = new JCheckBox("Calculate bytes");
		chckbxCalculateBytes.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxCalculateBytes.setBounds(142, 403, 130, 23);
		makeTransparentForCheckBox(chckbxCalculateBytes);
		contentPane.add(chckbxCalculateBytes);

		 chckbxCalculateWords = new JCheckBox("Calculate words");
		chckbxCalculateWords.setFont(new Font("Tahoma", Font.BOLD, 14));
		chckbxCalculateWords.setBounds(278, 403, 149, 23);
		makeTransparentForCheckBox(chckbxCalculateWords);
		contentPane.add(chckbxCalculateWords);

		JLabel lblInput = new JLabel("key input:");
		lblInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblInput.setBounds(10, 53, 87, 23);
		contentPane.add(lblInput);
		fileInputRadioButton = new JRadioButton("file input");
		fileInputRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		fileInputRadioButton.setBounds(116, 78, 109, 23);
		makeTransparentForRaduiButton(fileInputRadioButton);
		contentPane.add(fileInputRadioButton);
		textFieldKeyPath = new JTextField();
		textFieldKeyPath.setBounds(47, 105, 295, 28);
		contentPane.add(textFieldKeyPath);
		textFieldKeyPath.setColumns(10);
		JButton btnChoose = new JButton("Choose");
		btnChoose.setBounds(352, 108, 89, 23);
		makeTransparentForButton(btnChoose);
		btnChoose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] textFiles = new String[] { ".txt" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("text Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldKeyPath.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();
			}
		});
		contentPane.add(btnChoose);
		textAreaForKey = new JTextArea();
		textAreaForKey.setWrapStyleWord(true);
		textAreaForKey.setLineWrap(true);
		textAreaForKey.setBounds(105, 108, 363, 45);
		textAreaForKey.setVisible(false);
		contentPane.add(textAreaForKey);
		JLabel pathLbl = new JLabel("path:");
		pathLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		pathLbl.setBounds(10, 99, 48, 28);
		contentPane.add(pathLbl);
		textInputRadioButton = new JRadioButton("text input");
		textInputRadioButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		textInputRadioButton.setBounds(6, 77, 108, 23);
		makeTransparentForRaduiButton(textInputRadioButton);
		contentPane.add(textInputRadioButton);
		JLabel enterKeyLbl = new JLabel("Enter a key:");
		enterKeyLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		enterKeyLbl.setBounds(10, 111, 87, 22);
		enterKeyLbl.setVisible(false);
		contentPane.add(enterKeyLbl);

		JLabel lblSelectTheAlgorithm = new JLabel("Please select an algorithm:");
		lblSelectTheAlgorithm.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSelectTheAlgorithm.setBounds(10, 30, 189, 23);
		contentPane.add(lblSelectTheAlgorithm);

		JRadioButton aesRadioButton = new JRadioButton("AES(128bit)");
		aesRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		aesRadioButton.setBounds(197, 30, 110, 23);
		aesRadioButton.setOpaque(false);
		aesRadioButton.setContentAreaFilled(false);
		aesRadioButton.setBorderPainted(false);
		contentPane.add(aesRadioButton);

		JRadioButton blowfishRadioButton = new JRadioButton("BlowFish");
		blowfishRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		blowfishRadioButton.setBounds(309, 30, 82, 23);
		makeTransparentForRaduiButton(blowfishRadioButton);
		contentPane.add(blowfishRadioButton);

		JRadioButton ideaRadioButton = new JRadioButton("IDEA");
		ideaRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ideaRadioButton.setBounds(393, 30, 57, 23);
		makeTransparentForRaduiButton(ideaRadioButton);
		contentPane.add(ideaRadioButton);
		textInputRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLbl.setVisible(false);
				textFieldKeyPath.setVisible(false);
				btnChoose.setVisible(false);
				enterKeyLbl.setVisible(true);
				textAreaForKey.setVisible(true);
			}
		});
		fileInputRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLbl.setVisible(true);
				textFieldKeyPath.setVisible(true);
				btnChoose.setVisible(true);
				enterKeyLbl.setVisible(false);
				textAreaForKey.setVisible(false);
			}
		});

		JLabel lblTextInput = new JLabel("text input:");
		lblTextInput.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTextInput.setBounds(14, 186, 83, 23);
		contentPane.add(lblTextInput);

		textInputRadioButtonForText = new JRadioButton("text input");
		textInputRadioButtonForText.setFont(new Font("Tahoma", Font.BOLD, 14));
		textInputRadioButtonForText.setBounds(10, 216, 104, 23);
		makeTransparentForRaduiButton(textInputRadioButtonForText);
		contentPane.add(textInputRadioButtonForText);
		fileInputRadioButtonForText = new JRadioButton("file input");
		fileInputRadioButtonForText.setFont(new Font("Tahoma", Font.BOLD, 14));
		fileInputRadioButtonForText.setBounds(116, 216, 109, 23);
		makeTransparentForRaduiButton(fileInputRadioButtonForText);
		contentPane.add(fileInputRadioButtonForText);
		JLabel pathLabelForText = new JLabel("path:");
		pathLabelForText.setFont(new Font("Tahoma", Font.BOLD, 14));
		pathLabelForText.setBounds(14, 240, 48, 23);
		contentPane.add(pathLabelForText);
		textFieldForText = new JTextField();
		textFieldForText.setBounds(51, 243, 295, 28);
		contentPane.add(textFieldForText);
		textFieldForText.setColumns(10);
		JButton chooseButtonForText = new JButton("Choose");
		chooseButtonForText.setBounds(356, 246, 89, 23);
		makeTransparentForButton(chooseButtonForText);
		chooseButtonForText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] textFiles = new String[] { ".txt" };
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(fcCurDir);
				fc.setDialogTitle("Choose a file : ");
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FilterTheFiles("text Files", textFiles));
				if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					textFieldForText.setText(fc.getSelectedFile().getAbsolutePath());
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You have not choose a file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				fcCurDir = fc.getCurrentDirectory();
			}
		});
		contentPane.add(chooseButtonForText);
		textAreaForText = new JTextArea();
		textAreaForText.setBounds(105, 240, 363, 45);
		textAreaForText.setVisible(false);
		contentPane.add(textAreaForText);
		JLabel enterTextLblForText = new JLabel("Enter Text:");
		enterTextLblForText.setFont(new Font("Tahoma", Font.BOLD, 14));
		enterTextLblForText.setBounds(14, 246, 83, 14);
		enterTextLblForText.setVisible(false);
		contentPane.add(enterTextLblForText);
		fileInputRadioButtonForText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLabelForText.setVisible(true);
				textFieldForText.setVisible(true);
				chooseButtonForText.setVisible(true);
				enterTextLblForText.setVisible(false);
				textAreaForText.setVisible(false);
			}
		});
		textInputRadioButtonForText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pathLabelForText.setVisible(false);
				textFieldForText.setVisible(false);
				chooseButtonForText.setVisible(false);
				enterTextLblForText.setVisible(true);
				textAreaForText.setVisible(true);
			}
		});
		JLabel workingModeLbl = new JLabel("Please select a working mode:");
		workingModeLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		workingModeLbl.setBounds(10, 4, 230, 23);
		contentPane.add(workingModeLbl);

		JRadioButton encryptionRadioButton = new JRadioButton("Encryption");
		encryptionRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		encryptionRadioButton.setBounds(238, 4, 91, 23);
		makeTransparentForRaduiButton(encryptionRadioButton);
		contentPane.add(encryptionRadioButton);
		encryptionRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnGenerateKeys.setVisible(true);
				decryptButton.setVisible(false);
				encryptButton.setVisible(true);
			}
		});
		JRadioButton decryptionRadioButton = new JRadioButton("Decryption");
		decryptionRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		decryptionRadioButton.setBounds(332, 4, 109, 23);
		makeTransparentForRaduiButton(decryptionRadioButton);
		contentPane.add(decryptionRadioButton);
		decryptionRadioButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnGenerateKeys.setVisible(false);
				encryptButton.setVisible(false);
				decryptButton.setVisible(true);
			}
		});
		// avoiding multiple radio button selection for inputText&inputFile for the key
		ButtonGroup group = new ButtonGroup();
		group.add(textInputRadioButton);
		group.add(fileInputRadioButton);
		// avoiding multiple radio button selection for algorithms radio buttons
		ButtonGroup group1 = new ButtonGroup();
		group1.add(aesRadioButton);
		group1.add(blowfishRadioButton);
		group1.add(ideaRadioButton);
		// avoiding multiple radio button selection for inputText&inputFIle for Text
		ButtonGroup group2 = new ButtonGroup();
		group2.add(fileInputRadioButtonForText);
		group2.add(textInputRadioButtonForText);
		// avoiding multiple radio button selection for encryption & decryption
		ButtonGroup group3 = new ButtonGroup();
		group3.add(encryptionRadioButton);
		group3.add(decryptionRadioButton);
		JButton btnBack = new JButton("back");
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnBack.setBounds(849, 461, 109, 31);
		Image img1 = new ImageIcon(this.getClass().getResource("/back_icon.png")).getImage();
		btnBack.setIcon(new ImageIcon(img1));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Options().setVisible(true);
				close();
			}
		});
		contentPane.add(btnBack);
		btnGenerateKeys = new JButton("Generate keys");
		btnGenerateKeys.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnGenerateKeys.setBounds(151, 163, 136, 23);
		makeTransparentForButton(btnGenerateKeys);
		contentPane.add(btnGenerateKeys);

		JButton btnReset = new JButton("Reset");
		btnReset.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnReset.setBounds(718, 461, 109, 31);
		Image img2 = new ImageIcon(this.getClass().getResource("/reset.png")).getImage();
		btnReset.setIcon(new ImageIcon(img2));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new EncryptionDecryptionFrame().setVisible(true);
			}
		});
		contentPane.add(btnReset);
		 textAreaResults = new JTextArea();
		 GraphicsEnvironment.getLocalGraphicsEnvironment();
		    Font font = new Font("DejavuSans", Font.BOLD, 20);
		 textAreaResults.setBackground(color);
		 textAreaResults.setFont(font);
		textAreaResults.setLineWrap(true);
		textAreaResults.setWrapStyleWord(true);
		textAreaResults.setOpaque(false);
		textAreaResults.setText(e);
		
		contentPane.add(textAreaResults);
		JScrollPane sPane=new JScrollPane(textAreaResults,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sPane.setBounds(478, 38, 506, 412);
		sPane.getViewport().setOpaque(false);
		sPane.setOpaque(false);
		contentPane.add(sPane);
		
		JLabel label = new JLabel("");
		label.setBounds(0, 0, 994, 503);
		contentPane.add(label);
		label.setIcon(new ImageIcon("C:\\Users\\Windows 10\\Desktop\\d.jpg"));
		btnGenerateKeys.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textAreaForKey.getText().isEmpty() & textFieldKeyPath.getText().isEmpty()) {
					if(textAreaForKey.getText().isEmpty())
					JOptionPane.showMessageDialog(new JFrame(), "empty key, please enter a valid key!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				//cancel the method
					return;
				}
				if (!((encryptionRadioButton.isSelected()||decryptionRadioButton.isSelected())&(aesRadioButton.isSelected()||blowfishRadioButton.isSelected()||ideaRadioButton.isSelected())&
						fileInputRadioButton.isSelected()||textInputRadioButton.isSelected())) {
					JOptionPane.showMessageDialog(new JFrame(), "Please complete your options!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				if (encryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& textInputRadioButton.isSelected()) {
					try {
						choosingTextInputForAesKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& fileInputRadioButton.isSelected()) {
					try {
						choosingFileInputForAesKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& textInputRadioButton.isSelected()) {
					try {
						choosingTextInputForBlowFishKey();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(new JFrame(), "Invalid key, please enter a valid key!", "Dialog",
								JOptionPane.ERROR_MESSAGE);
					}
				} else if (encryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& fileInputRadioButton.isSelected()) {
					try {
						choosingFileInputForBlowFishKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& textInputRadioButton.isSelected()) {
					try {
						choosingTextInputForIdeaKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else if (encryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& fileInputRadioButton.isSelected()) {
					try {
						choosingFileInputForIdeaKey();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		encryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(textAreaForText.getText().isEmpty() & textFieldForText.getText().isEmpty()) {
					if(textAreaForText.getText().isEmpty() &!(fileInputRadioButtonForText.isSelected()))
					JOptionPane.showMessageDialog(new JFrame(), "please choose input method and fill the fields!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				//cancel the method
					return;
				}
				if (!((encryptionRadioButton.isSelected()||decryptionRadioButton.isSelected())&(aesRadioButton.isSelected()||blowfishRadioButton.isSelected()||ideaRadioButton.isSelected())&
						fileInputRadioButton.isSelected()||textInputRadioButton.isSelected())) {
					JOptionPane.showMessageDialog(new JFrame(), "Please complete your options!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				if(!(fileInputRadioButtonForText.isSelected()||textInputRadioButtonForText.isSelected())) {
					JOptionPane.showMessageDialog(new JFrame(), "Please complete your options!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
				//AES
				if (encryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForAesEncryption();
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid (128bit) text and key!", "Dialog",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForAesEncryption();
						} catch (Exception e) {
							JOptionPane.showMessageDialog(new JFrame(), "Please choose a valid file!", "Dialog",
									JOptionPane.ERROR_MESSAGE);
						}
				}
				//BlowFish
				if (encryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForBlowfishEncryption();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForBlowfishEncryption();
						} catch (IOException e) {
							JOptionPane.showMessageDialog(new JFrame(), "please choose a valid file!", "Dialog",
									JOptionPane.ERROR_MESSAGE);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//Idea
				if (encryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForIdeaEncryption();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForIdeaEncryption();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
		btnAddDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					getDetails();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		decryptButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//BlowFish (done)
				if (decryptionRadioButton.isSelected() & blowfishRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForBlowfishDecryption();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForBlowfishDecryption();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//aes (done)
				if (decryptionRadioButton.isSelected() & aesRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForAesDecryption();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForAesDecryption();
						} catch (IOException e) {
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				//Idea (done)
				if (decryptionRadioButton.isSelected() & ideaRadioButton.isSelected()
						& (textInputRadioButton.isSelected()||fileInputRadioButton.isSelected())& (textInputRadioButtonForText.isSelected()||fileInputRadioButtonForText.isSelected())) {
					if(textInputRadioButtonForText.isSelected())
						try {
							choosingTextInputForIdeaDecryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					else if (fileInputRadioButtonForText.isSelected())
						try {
							choosingFileInputForIdeaDecryption();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
	}
public static void getDetails() throws IOException {
	String details="";
	if (chckbxAsciiKey.isSelected() & (textInputRadioButton.isSelected() || fileInputRadioButton.isSelected())) {
		if(textInputRadioButton.isSelected())
			try {
				details+=DetailsMethods.stringToASCII(textAreaForKey.getText())+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		else if (fileInputRadioButton.isSelected()) {
			try {
				details+=DetailsMethods.stringToASCII(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	else if (chckbxAsciiMessage.isSelected() & (textInputRadioButtonForText.isSelected() || fileInputRadioButtonForText.isSelected())) {
		if(textInputRadioButtonForText.isSelected())
			try {
				details+=DetailsMethods.stringToASCII(textAreaForText.getText())+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		else if (fileInputRadioButtonForText.isSelected()) {
			try {
				details+=DetailsMethods.stringToASCII(IOutils.readAfile(textFieldForText.getText()))+"\n";
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
	}
	else if(chckbxBinaryKey.isSelected()& (textInputRadioButton.isSelected() || fileInputRadioButton.isSelected())) {
		if(textInputRadioButton.isSelected())
			details+=DetailsMethods.stringToBinary((textAreaForKey.getText())).toString()+"\n";
		else if (fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.stringToBinary(IOutils.readAfile(textFieldKeyPath.getText())).toString()+"\n";
		}
	}
	else if (chckbxBinaryMessage.isSelected()& (textInputRadioButtonForText.isSelected() || fileInputRadioButtonForText.isSelected())) {
		if(textInputRadioButtonForText.isSelected())
			details+=DetailsMethods.stringToBinary(textAreaForText.getText()).toString()+"\n";
		else if (fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.stringToBinary(IOutils.readAfile(textFieldForText.getText())).toString()+"\n";
		}
	}
	else if(chckbxHexadecimalKey.isSelected() &(textInputRadioButton.isSelected() || fileInputRadioButton.isSelected()) ) {
		if(textInputRadioButton.isSelected())
			details+=Convert.toHexadecimal((textAreaForKey.getText()))+"\n";
		else if (fileInputRadioButton.isSelected()) {
			details+=Convert.toHexadecimal(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
	}
	else if(chckbxHexadecimalMessage.isSelected()& (textInputRadioButtonForText.isSelected() || fileInputRadioButtonForText.isSelected()) ) {
		if(textInputRadioButtonForText.isSelected())
			details+=Convert.toHexadecimal(textAreaForText.getText())+"\n";
		else if (fileInputRadioButtonForText.isSelected()) {
			details+=Convert.toHexadecimal(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	else if(chckbxCalculateBits.isSelected()) {
		if(textInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBits((textAreaForKey.getText()))+"\n";
		}
		if(fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBits(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
		if(textInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBits(textAreaForText.getText())+"\n";
		}
		if(fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBits(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	else if(chckbxCalculateBytes.isSelected()) {
		if(textInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBytes((textAreaForKey.getText()))+"\n";
		}
		if(fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateBytes(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
		if(textInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBytes(textAreaForText.getText())+"\n";
		}
		if(fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateBytes(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	else if(chckbxCalculateWords.isSelected()) {
		if(textInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateWords((textAreaForKey.getText()))+"\n";
		}
		if(fileInputRadioButton.isSelected()) {
			details+=DetailsMethods.calculateWords(IOutils.readAfile(textFieldKeyPath.getText()))+"\n";
		}
		if(textInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateWords(textAreaForText.getText())+"\n";
		}
		if(fileInputRadioButtonForText.isSelected()) {
			details+=DetailsMethods.calculateWords(IOutils.readAfile(textFieldForText.getText()))+"\n";
		}
	}
	else {
		JOptionPane.showMessageDialog(new JFrame(), "please enter somthing and choose what details you want to get it!", "Dialog",
				JOptionPane.ERROR_MESSAGE);
	}
	textAreaResults.setText(details);
	IOutils.writeAfile("D:\\details.txt", details);
}


	//BlowFish Encryption And Decryption
	//Encryption
	public static void choosingFileInputForBlowfishEncryption() throws Exception {
		String text=IOutils.readAfile(textFieldForText.getText());
		String key="";
		if (textInputRadioButton.isSelected()) {
			key=textAreaForKey.getText();
			String resultingCipher=BlowFishEnc.encryptBlowfish(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		
		}
		else if (fileInputRadioButton.isSelected()) {
			//get the content of original key file
			key=IOutils.readAfile(textFieldKeyPath.getText());
			String resultingCipher=BlowFishEnc.encryptBlowfish(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		}

	public static void choosingTextInputForBlowfishEncryption() throws Exception {
		String e= BlowFishEnc.encryptBlowfish(textAreaForText.getText(), textAreaForKey.getText());
		textAreaResults.setText(e);
		String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
		//writing
		IOutils.writeAfile(savingFileName, e);
		}
	public static void choosingTextInputForBlowFishKey() throws IOException {
		try {
		BlowFishEnc blowFishEnc = new BlowFishEnc();
		String string = textAreaForKey.getText();
		textAreaResults.setText(blowFishEnc.getKey(string));
		String savingFileName="D:\\"+getFileNameToSaveKeyFromUser();
		IOutils.writeAfile(savingFileName, blowFishEnc.getKey(string));}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Invalid key, please enter a valid key!", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void choosingFileInputForBlowFishKey() throws IOException {
		try {
		BlowFishEnc blowFishEnc = new BlowFishEnc();
		String fileName = textFieldKeyPath.getText();
		String key = IOutils.readAfile(fileName);
		textAreaResults.setText(blowFishEnc.getKey(key));
		String savingFileName="D:\\"+getFileNameToSaveKeyFromUser();
		IOutils.writeAfile(savingFileName, blowFishEnc.getKey(key));}
		catch(Exception e) {
			JOptionPane.showMessageDialog(new JFrame(), "Please choose a valid file!", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	//Decryption
	public static void choosingFileInputForBlowfishDecryption() throws Exception {
		String text=IOutils.readAfile(textFieldForText.getText());
		String key="";
		if (textInputRadioButton.isSelected()) {
			key=textAreaForKey.getText();
			String resultingCipher=BlowFishEnc.decryptBlowfish(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		else if (fileInputRadioButton.isSelected()) {
			//get the content of original key file
			key=IOutils.readAfile(textFieldKeyPath.getText());
			String resultingCipher=BlowFishEnc.decryptBlowfish(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		}

	public static void choosingTextInputForBlowfishDecryption() throws Exception {
		String e= BlowFishEnc.decryptBlowfish(textAreaForText.getText(), textAreaForKey.getText());
		System.out.println(e);
		//writing
		String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
		IOutils.writeAfile(savingFileName, e);
		//reading
		String result=IOutils.readAfile(savingFileName);
		textAreaResults.setText(result);
		}
	//end of BlowFish Encryption And Decryption
	//Idea Encryption And Decryption
		//Encryption
	public static void choosingFileInputForIdeaKey() throws Exception {
		try {
		String fileName = textFieldKeyPath.getText();
		String key = IOutils.readAfile(fileName);
		Key k = new Key(key);
		textAreaResults.setText(k.getEncKeys());
		String savingFileName="D:\\"+getFileNameToSaveKeyFromUser();
		IOutils.writeAfile(savingFileName, k.getEncKeys());}
		catch(FileNotFoundException e) {
			JOptionPane.showMessageDialog(new JFrame(), "Please choose a valid file!", "Dialog",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void choosingTextInputForIdeaKey() throws Exception {
		String string = textAreaForKey.getText();
		String keys = "";
		Key key = new Key(string);
		keys = key.getEncKeys();
		textAreaResults.setText(keys);
		String savingFileName="D:\\"+getFileNameToSaveKeyFromUser();
		IOutils.writeAfile(savingFileName, keys);
	}
	public static  void choosingFileInputForIdeaEncryption() throws Exception {
		String text=IOutils.readAfile(textFieldForText.getText());
		String key="";
		if (textInputRadioButton.isSelected()) {
			key=textAreaForKey.getText();
			String resultingCipher=IDEA.encrypt(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		else if (fileInputRadioButton.isSelected()) {
			//get the content of original key file
			key=IOutils.readAfile(textFieldKeyPath.getText());
			String resultingCipher=IDEA.encrypt(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		}

	public static void choosingTextInputForIdeaEncryption() throws Exception {
		String text=textAreaForText.getText();
		String key="";
		if (textInputRadioButton.isSelected()) {
			key=textAreaForKey.getText();
			String resultingCipher=IDEA.encrypt(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		else if (fileInputRadioButton.isSelected()) {
			//get the content of original key file
			key=IOutils.readAfile(textFieldKeyPath.getText());
			String resultingCipher=IDEA.encrypt(text, key);
			textAreaResults.setText(resultingCipher);
			String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
			IOutils.writeAfile(savingFileName, resultingCipher);
		}
		}
		//Decryption
		public static void choosingFileInputForIdeaDecryption() throws Exception {
			String text=IOutils.readAfile(textFieldForText.getText());
			String key="";
			if (textInputRadioButton.isSelected()) {
				key=textAreaForKey.getText();
				String resultingCipher=IDEA.decrypt(text, key);
				textAreaResults.setText(resultingCipher);
				String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
				IOutils.writeAfile(savingFileName, resultingCipher);
			}
			else if (fileInputRadioButton.isSelected()) {
				//get the content of original key file
				key=IOutils.readAfile(textFieldKeyPath.getText());
				String resultingCipher=IDEA.decrypt(text, key);
				textAreaResults.setText(resultingCipher);
				String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
				IOutils.writeAfile(savingFileName, resultingCipher);
			}
			}

		public static void choosingTextInputForIdeaDecryption() throws Exception {
			String e= IDEA.decrypt(textAreaForText.getText(), textAreaForKey.getText());
			//writing
			String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
			IOutils.writeAfile(savingFileName, e);
			//reading
			String result=IOutils.readAfile(savingFileName);
			textAreaResults.setText(result);
			}
		//end of Idea Encryption And Decryption
		//aes encryption and decryption
		//encryption
		public static void choosingTextInputForAesEncryption() throws Exception {
			String text=textAreaForText.getText();
			String key="";
			if (textInputRadioButton.isSelected()) {
				key=Convert.toHexadecimal(textAreaForKey.getText());
				String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
				textAreaResults.setText(resultingCipher);
				String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
				IOutils.writeAfile(savingFileName, resultingCipher);
			}
			else if (fileInputRadioButton.isSelected()) {
				//get the content of original key file and convert it to hexadecimal
				key=Convert.toHexadecimal(IOutils.readAfile(textFieldKeyPath.getText()));
				String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
				textAreaResults.setText(resultingCipher);
				String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
				IOutils.writeAfile(savingFileName, resultingCipher);
			}
		}
		public static void choosingFileInputForAesEncryption() throws Exception {
			String text=IOutils.readAfile(textFieldForText.getText());
			String key="";
			if (textInputRadioButton.isSelected()) {
				key=Convert.toHexadecimal(textAreaForKey.getText());
				String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
				System.out.println(resultingCipher);
				textAreaResults.setText(resultingCipher);
				String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
				IOutils.writeAfile(savingFileName, resultingCipher);
			}
			else if (fileInputRadioButton.isSelected()) {
				//get the content of original key file and convert it to hexadecimal
				key=Convert.toHexadecimal(IOutils.readAfile(textFieldKeyPath.getText()));
				String resultingCipher=new AES().encrypt(Convert.toHexadecimal(text), key);
				System.out.println(resultingCipher);
				textAreaResults.setText(resultingCipher);
				String savingFileName="D:\\"+getFileNameToSaveCipherFromUser();
				IOutils.writeAfile(savingFileName, resultingCipher);
			}
		}
			public static void choosingTextInputForAesKey() throws Exception {
				try {
				textAreaResults.setText(AES.getAesKeys(Convert.toHexadecimal(textAreaForKey.getText())));
				String savingFileName="D:\\"+getFileNameToSaveKeyFromUser();
				IOutils.writeAfile(savingFileName, AES.getAesKeys(Convert.toHexadecimal(textAreaForKey.getText())));}
				catch(ArithmeticException e) {
				e.printStackTrace();
				}
				catch(ArrayIndexOutOfBoundsException e) {
				e.printStackTrace();
				}
			}

			public static void choosingFileInputForAesKey() throws IOException {
				try {
				// The name of the file to open.
				String fileName = textFieldKeyPath.getText();
				String key = IOutils.readAfile(fileName);
				textAreaResults.setText(AES.getAesKeys(Convert.toHexadecimal(key)));
				String savingFileName="D:\\"+getFileNameToSaveKeyFromUser();
				IOutils.writeAfile(savingFileName,AES.getAesKeys(Convert.toHexadecimal(key)));}
				catch(Exception e) {
					JOptionPane.showMessageDialog(new JFrame(), "Please choose a valid file!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				}
			}
			//decryption
			public static void choosingFileInputForAesDecryption() throws Exception {
				String text=IOutils.readAfile(textFieldForText.getText());
				String key="";
				if (textInputRadioButton.isSelected()) {
					key=textAreaForKey.getText();
					String resultingCipher=AES.decrypt(text, key);
					textAreaResults.setText(resultingCipher);
					String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
					IOutils.writeAfile(savingFileName, resultingCipher);
				}
				else if (fileInputRadioButton.isSelected()) {
					//get the content of original key file
					key=IOutils.readAfile(textFieldKeyPath.getText());
					String resultingCipher=AES.decrypt(text, key);
					textAreaResults.setText(resultingCipher);
					String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
					IOutils.writeAfile(savingFileName, resultingCipher);
				}
				}

			public static void choosingTextInputForAesDecryption() throws Exception {
				String e= AES.decrypt(textAreaForText.getText(), textAreaForKey.getText());
				//writing
				String savingFileName="D:\\"+getFileNameToSaveOriginalTextFromUser();
				IOutils.writeAfile(savingFileName, e);
				//reading
				String result=IOutils.readAfile(savingFileName);
				textAreaResults.setText(result);
				}
			//end of aes encryption and decryption
			public static String getFileNameToSaveCipherFromUser() throws Exception {
				String input=JOptionPane.showInputDialog("please enter the file name to save the cipher: ");
				if(input == null || (input != null && ("".equals(input))))   
				{
					JOptionPane.showMessageDialog(new JFrame(), "you have not choose any file to save your results!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				    throw new Exception();
				}
				return input;
			}
			public static String getFileNameToSaveKeyFromUser() throws Exception {
				String input=JOptionPane.showInputDialog("please enter the file name to save the keys: ");
				if(input == null || (input != null && ("".equals(input))))   
				{
					JOptionPane.showMessageDialog(new JFrame(), "you have not choose any file to save your results!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				    throw new Exception();
				}
				return input;
			}
			public static String getFileNameToSaveOriginalTextFromUser() throws Exception {
				String input=JOptionPane.showInputDialog("please enter the file name to save the decrypted text: ");
				if(input == null || (input != null && ("".equals(input))))   
				{
					JOptionPane.showMessageDialog(new JFrame(), "you have not choose any file to save your results!", "Dialog",
							JOptionPane.ERROR_MESSAGE);
				    throw new Exception();
				}
				return input;
			}
	public void close() {
		this.dispose();
	}
	public static void makeTransparentForCheckBox(JCheckBox x) {
		x.setOpaque(false);
		x.setContentAreaFilled(false);
		x.setBorderPainted(false);
	}
	public static void makeTransparentForButton(JButton x) {
		x.setContentAreaFilled(false);
		x.setBorder(new LineBorder(Color.BLACK));
	}
	public static void makeTransparentForRaduiButton(JRadioButton x) {
		x.setOpaque(false);
		x.setContentAreaFilled(false);
		x.setBorderPainted(false);
	}
}
