package ProtectMi;

/*
 * By: Ethan Misa
 * Class: ICS4U1
 * Date: 25 May 2021
 * Project: Protect Mi
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProtectMi {

	public static void main(String[] args) throws IOException {
		new ProtectMi();

	}
	//creates global variables for the main panels and frames used
	JFrame frame;
	MasterPanel master;
	TitlePanel title;
	LibraryPanel library;
	GeneratorPanel generator;
	FirstMasterPanel first;
	String masterpassword;
	String myString;
	//for the copy and paste feature
	StringSelection stringSelection;
	Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	//input and output to the file
	File passwordFile = new File("passwordFile.txt");
	FileReader in;
	BufferedReader readFile;
	String textLine;
	ProtectMi() {
		
		first = new FirstMasterPanel();
		master = new MasterPanel();
		title = new TitlePanel();
		generator = new GeneratorPanel();
		library = new LibraryPanel();
		frame = new JFrame("Protect Mi");
		frame.setSize(500, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
			in = new FileReader(passwordFile);
			readFile = new BufferedReader(in);
			while ((textLine = readFile.readLine()) != null) {
				if(textLine.length()==14) { //tests to see if the file is new, and needs a master password
					frame.setContentPane(first);
					break;
				}
				else {
					masterpassword = textLine.substring(textLine.indexOf(" ")+1); //if file has been edited before, it grabs the master password from the file
					frame.setContentPane(master);
					break;
				}
			}
			readFile.close();
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File does not exist or not found");
			System.err.println("FileNotFoundException: " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Problem reading file.");
			System.err.println("IOException: " + e.getMessage());
		}
		//System.out.println(masterpassword);
		frame.setVisible(true);
	}
	
	class FirstMasterPanel extends JPanel implements ActionListener{ //allows the user to set up their master password
		JPanel titlePanel, passwordPanel;
		JLabel titleLabel, enterPassLabel, confirmPass;
		JPasswordField password1, password2;
		JButton login;
		String input1, input2;
		
		File passwordFile = new File("passwordFile.txt");
		FileWriter out;
		BufferedWriter writeFile;
		FirstMasterPanel(){

			titlePanel = new JPanel();
			titleLabel = new JLabel("Welcome to Protect Mi!");
			titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 30));
			titlePanel.add(titleLabel);
			
			enterPassLabel = new JLabel("Enter a Master Password:");
			enterPassLabel.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			password1 = new JPasswordField(20);
			password1.setMaximumSize(password1.getPreferredSize());
			password1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			confirmPass = new JLabel("Confirm the Master Password:"); 
			confirmPass.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			password2 = new JPasswordField(20);
			password2.setMaximumSize(password2.getPreferredSize());
			password2.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			login = new JButton("Save");
			login.addActionListener(this);
			login.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			
			passwordPanel = new JPanel();
			passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
			passwordPanel.add(Box.createVerticalGlue());
			passwordPanel.add(enterPassLabel);
			passwordPanel.add(password1);
			passwordPanel.add(confirmPass);
			passwordPanel.add(password2);
			passwordPanel.add(login);
			passwordPanel.add(Box.createVerticalGlue());
			
			setLayout(new BorderLayout());
			add(titlePanel, BorderLayout.NORTH);
			add(passwordPanel, BorderLayout.CENTER);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == login) {
				input1 = new String(password1.getPassword());
				input2 = new String(password2.getPassword());
				if(input1.contentEquals(input2)) { //tests if the user entered in the master password correctly
					masterpassword = input1;
					try {
						out = new FileWriter(passwordFile, true);
						writeFile = new BufferedWriter(out);
						writeFile.write(" "+input1); //writes the master password to the file
						writeFile.close();
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					frame.setContentPane(title); 
					frame.revalidate();
				}
				else {
					int incorrect= JOptionPane.showConfirmDialog(null, "The passwords did not match. Please try again.", "Invalid input", JOptionPane.DEFAULT_OPTION);
					if(incorrect == 0) {
						password1.setText("");
						password2.setText("");
						password1.grabFocus();
					}
				}
			}
			
		}
		
	}
	//if there is a master password in the file, this frame is used at the starting screen
	class MasterPanel extends JPanel implements ActionListener {
		JPanel mainPanel, titlePanel, passwordPanel;
		JLabel titleLabel, mastPass;
		JPasswordField masterPassword;
		JButton login;
		int tries = 0;

		MasterPanel() {
			// title panel
			titlePanel = new JPanel();
			titleLabel = new JLabel("Protect Mi");
			titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 50));
			titlePanel.add(titleLabel);

			// password panel
			passwordPanel = new JPanel();
			mastPass = new JLabel("Enter Master Password:");
			mastPass.setFont(new Font("Monospaced", Font.BOLD, 15));
			masterPassword = new JPasswordField(20);
			masterPassword.setMaximumSize(masterPassword.getPreferredSize());
			login = new JButton("LOGIN");
			login.setActionCommand("login");
			login.addActionListener(this);
			passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.Y_AXIS));
			passwordPanel.add(Box.createVerticalGlue());
			passwordPanel.add(mastPass);
			passwordPanel.add(masterPassword);
			passwordPanel.add(login);
			passwordPanel.add(Box.createVerticalGlue());
			mastPass.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			masterPassword.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			login.setAlignmentX(JComponent.CENTER_ALIGNMENT);

			setLayout(new BorderLayout());
			add(titlePanel, BorderLayout.NORTH);
			add(passwordPanel, BorderLayout.CENTER);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().contentEquals("login")) { 
				String inputPass = new String(masterPassword.getPassword()); 
				if (inputPass.contentEquals(masterpassword)) { //tests if the master password entered is equal to the one in the file
					frame.setContentPane(title);
					frame.revalidate();
				} else {
					tries++;
					if (tries >= 5) { //if there are 5 unsuccessful attempts, the application closes
						int exit = JOptionPane.showConfirmDialog(null, "You have been locked out.", "Invalid Password",
								JOptionPane.DEFAULT_OPTION);
						if (exit == 0) {
							System.exit(0);
						}
					}
					int except = JOptionPane.showConfirmDialog(null, "Invalid Password. Please try again",
							"Invalid Password", JOptionPane.DEFAULT_OPTION);
					if (except == 0) {
						masterPassword.setText("");
						masterPassword.grabFocus();
					}
				}

			}
		}
	}
	//title panel includes access to the library and generator panels
	class TitlePanel extends JPanel implements ActionListener {
		JPanel mainPanel, titlePanel, buttonPanel;
		JLabel titleLabel;
		JButton libraryBut, generatorBut;

		TitlePanel() {
			// titlePanel
			titlePanel = new JPanel();
			titleLabel = new JLabel("Protect Mi");
			titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 50));
			titlePanel.add(titleLabel);

			// button panel
			buttonPanel = new JPanel();
			libraryBut = new JButton("Library");
			libraryBut.setMaximumSize(new Dimension(400, 100));
			libraryBut.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			libraryBut.addActionListener(this);
			generatorBut = new JButton("Generator");
			generatorBut.setMaximumSize(new Dimension(400, 100));
			generatorBut.setAlignmentX(JComponent.CENTER_ALIGNMENT);
			generatorBut.addActionListener(this);
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
			buttonPanel.add(Box.createVerticalGlue());
			buttonPanel.add(libraryBut);
			buttonPanel.add(generatorBut);
			buttonPanel.add(Box.createVerticalGlue());

			setLayout(new BorderLayout());
			add(titlePanel, BorderLayout.NORTH);
			add(buttonPanel, BorderLayout.CENTER);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) { //sets the frame to the specific panel depending on the button the user clicks
			if (e.getSource() == libraryBut) {
				frame.setContentPane(library);
				frame.revalidate();
			}
			if (e.getSource() == generatorBut) {
				frame.setContentPane(generator);
				frame.revalidate();
			}
		}

	}
	//library panel hold access to the previous passwords and lets the user add to the library
	class LibraryPanel extends JPanel implements ActionListener {
		File passwordFile = new File("passwordFile.txt");
		FileReader in;
		FileWriter out;
		BufferedReader readFile;
		BufferedWriter writeFile;
		JPanel comboPanel, passComboPanel, titlePanel, libraryPanel, inputPanel, companyPanel, passwordPanel, searchPanel, middlePanel, backPanel, pasteSavePanel, buttonPanel;
		JLabel titleLabel, companyLabel, passwordLabel, searchCompLabel, searchPassLabel;
		JTextField company, password, passwordSave;
		JComboBox companyLibrary;
		JButton saveBut, searchBut, backBut, pasteBut, copyBut;
		String textLine;

		LibraryPanel() {
			// back panel
			backBut = new JButton("BACK");
			backBut.addActionListener(this);
			backBut.setForeground(Color.red);
			backBut.setOpaque(true);
			backPanel = new JPanel();
			backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			backPanel.add(backBut);
			// title panel
			titleLabel = new JLabel("Library");
			titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 50));
			titlePanel = new JPanel();
			titlePanel.add(titleLabel);

			// user input panel
			companyLabel = new JLabel("Company:");
			companyLabel.setHorizontalAlignment(SwingConstants.CENTER);
			company = new JTextField(20);
			company.setMaximumSize(company.getPreferredSize());
			companyPanel = new JPanel();
			companyPanel.setLayout(new BoxLayout(companyPanel, BoxLayout.X_AXIS));
			companyPanel.add(companyLabel);
			companyPanel.add(company);
			passwordLabel = new JLabel("Password:");
			password = new JTextField(20);
			password.setMaximumSize(password.getPreferredSize());
			
			passwordPanel = new JPanel();
			passwordPanel.setLayout(new BoxLayout(passwordPanel, BoxLayout.X_AXIS));
			passwordPanel.add(passwordLabel);
			passwordPanel.add(password);
			
			inputPanel = new JPanel();
			inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
			inputPanel.add(companyPanel);
			inputPanel.add(passwordPanel);
			
			pasteSavePanel = new JPanel();
			pasteSavePanel.setLayout(new BoxLayout(pasteSavePanel, BoxLayout.X_AXIS));
			pasteBut = new JButton("Paste from clipboard");
			pasteBut.addActionListener(this);
			saveBut = new JButton("Add to Library");
			saveBut.addActionListener(this);
			middlePanel = new JPanel();
			middlePanel.setLayout(new BoxLayout(middlePanel, BoxLayout.Y_AXIS));
			pasteSavePanel.add(pasteBut);
			pasteSavePanel.add(saveBut);
			middlePanel.add(inputPanel);
			middlePanel.add(pasteSavePanel);
			
			// library panel
			comboPanel = new JPanel();
			comboPanel.setLayout(new BoxLayout(comboPanel, BoxLayout.X_AXIS));
			searchCompLabel = new JLabel("Search Company: ");
			companyLibrary = new JComboBox();
			companyLibrary.setSize(200, companyLibrary.getPreferredSize().height);
			comboPanel.add(Box.createHorizontalGlue());
			comboPanel.add(searchCompLabel);
			comboPanel.add(companyLibrary);
			comboPanel.add(Box.createHorizontalGlue());
			
			passComboPanel = new JPanel();
			passComboPanel.setLayout(new BoxLayout(passComboPanel, BoxLayout.X_AXIS));
			searchPassLabel = new JLabel("Company Password:");
			passwordSave = new JTextField(13);
			passwordSave.setMaximumSize(passwordSave.getPreferredSize());
			passComboPanel.add(searchPassLabel);
			passComboPanel.add(passwordSave);
			
			searchBut = new JButton("Search");
			searchBut.addActionListener(this);
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
			copyBut = new JButton("Copy to clipboard");
			copyBut.addActionListener(this);
			buttonPanel.add(searchBut);
			buttonPanel.add(copyBut);
			libraryPanel = new JPanel();
			libraryPanel.setLayout(new BoxLayout(libraryPanel, BoxLayout.Y_AXIS));
			libraryPanel.add(comboPanel);
			libraryPanel.add(passComboPanel);
			libraryPanel.add(buttonPanel);

			try {
				in = new FileReader(passwordFile);
				readFile = new BufferedReader(in);
				while ((textLine = readFile.readLine()) != null) {
					try {
						companyLibrary.addItem(textLine.substring(0, textLine.indexOf(" "))); //adds the passwords in the file to a combo box
					}
					catch(StringIndexOutOfBoundsException e) {
					}
				}
				readFile.close();
				in.close();

			} catch (FileNotFoundException e) {
				System.out.println("File does not exist or not found");
				System.err.println("FileNotFoundException: " + e.getMessage());
			} catch (IOException e) {
				System.out.println("Problem reading file.");
				System.err.println("IOException: " + e.getMessage());
			}

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(backPanel);
			add(titlePanel);
			add(middlePanel);
			add(Box.createRigidArea(new Dimension(0,20)));
			add(libraryPanel);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == copyBut) {
				myString = passwordSave.getText();
				stringSelection = new StringSelection(myString);
				clipboard.setContents(stringSelection, null);
				int copy = JOptionPane.showConfirmDialog(null,
						"Successfully copied "+passwordSave.getText()+" to clipboard",
						"Copy to clipboard", JOptionPane.DEFAULT_OPTION);
			}
			if(e.getSource() == pasteBut) {
				try {
					password.setText((String) clipboard.getData(DataFlavor.stringFlavor));
					
				}
				catch(Throwable o) {
					System.out.println(o);
				}
			}
			if(e.getSource() == backBut) {
				frame.setContentPane(title);
				frame.revalidate();
			}
			if (e.getSource() == saveBut) {
				try {
					int success = JOptionPane.showConfirmDialog(null,
							"Company: " + company.getText() + ", Password: " + password.getText()+". Are you sure you want to add this to the library?",
							"Password Entry", JOptionPane.YES_NO_OPTION);
		
					if (success == 0) {
						JOptionPane.showConfirmDialog(null, "Entry successfully added." , "Successful Entry", JOptionPane.DEFAULT_OPTION);
						out = new FileWriter(passwordFile, true);
						writeFile = new BufferedWriter(out);
						writeFile.newLine();
						writeFile.write(company.getText() + " " + password.getText());
						writeFile.close();
						out.close();
						companyLibrary.addItem(company.getText());
						company.setText("");
						password.setText("");
						company.grabFocus();
					}
					if(success == 1) {
						JOptionPane.showConfirmDialog(null, "Entry was NOT added." , "Unuccessful Entry", JOptionPane.DEFAULT_OPTION);
						company.setText("");
						password.setText("");
						company.grabFocus();
					}

					System.out.println("Data written to file.");
				} catch (IOException exc) {
					System.out.println("Problem writing to file.");
					System.err.println("IOException: " + exc.getMessage());
				}
			}
			if (e.getSource() == searchBut) {
				passwordSave.setText((String) companyLibrary.getSelectedItem());
				try {
					in = new FileReader(passwordFile);
					readFile = new BufferedReader(in);
					while ((textLine = readFile.readLine()) != null) {
						if (textLine.startsWith((String) companyLibrary.getSelectedItem())) {
							passwordSave.setText(textLine.substring(textLine.indexOf(" ") + 1));
						}
					}
					readFile.close();
					in.close();

				} catch (FileNotFoundException d) {
					System.out.println("File does not exist or not found");
					System.err.println("FileNotFoundException: " + d.getMessage());
				} catch (IOException f) {
					System.out.println("Problem reading file.");
					System.err.println("IOException: " + f.getMessage());
				}
			}

		}

	}
	//generator panel allows the user to create a randomized password based off of specific requirements
	class GeneratorPanel extends JPanel implements ActionListener, ChangeListener {
		
		JPanel textFieldPanel, buttonPanel, lengthPanel, generatePanel, backPanel, lengthSliderPanel;
		JLabel generatorTitle, characteristicsLabel, lengthLabel;
		JTextField passwordText, lengthField;
		JCheckBox lettersBox, numbersBox, specCharBox;
		JButton generateBut, backBut, copyBut;
		JSlider lengthSlider;
		boolean letters = true, numbers = true, specChar = true;
		int length = 0;

		GeneratorPanel() {

			// back panel
			backBut = new JButton("BACK");
			backBut.addActionListener(this);
			backBut.setForeground(Color.red);
			backBut.setOpaque(true);
			backPanel = new JPanel();
			backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			backPanel.add(backBut);
			// text field panel
			generatorTitle = new JLabel("Password Generator");
			generatorTitle.setFont(new Font("Monospaced", Font.PLAIN, 30));
			generatorTitle.setAlignmentX(CENTER_ALIGNMENT);
			passwordText = new JTextField(20);
			passwordText.setMaximumSize(passwordText.getPreferredSize());
			textFieldPanel = new JPanel();
			textFieldPanel.setLayout(new BoxLayout(textFieldPanel, BoxLayout.Y_AXIS));
			textFieldPanel.add(generatorTitle);
			textFieldPanel.add(Box.createVerticalGlue());
			textFieldPanel.add(passwordText);

			// button panel
			lettersBox = new JCheckBox("Letters", true);
			lettersBox.addActionListener(this);
			numbersBox = new JCheckBox("Numbers", true);
			numbersBox.addActionListener(this);
			specCharBox = new JCheckBox("Special Characters", true);
			specCharBox.addActionListener(this);
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
			buttonPanel.add(Box.createVerticalGlue());
			buttonPanel.add(lettersBox);
			buttonPanel.add(numbersBox);
			buttonPanel.add(specCharBox);

			// length panel
			lengthPanel = new JPanel();
			lengthPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			lengthSliderPanel = new JPanel();
			lengthSliderPanel.setLayout(new FlowLayout());
			lengthSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 25, 10);
			lengthSlider.setMajorTickSpacing(5);
			lengthSlider.setMinorTickSpacing(1);
			lengthSlider.setPaintTicks(true);
			lengthSlider.setPaintLabels(true);
			lengthSlider.setSnapToTicks(true);
			lengthSlider.addChangeListener(this);
			lengthSlider.setPreferredSize(new Dimension(300, 80));
			lengthSlider.setAlignmentY(TOP_ALIGNMENT);
			lengthSliderPanel.add(lengthSlider);

			lengthLabel = new JLabel("Length: " + lengthSlider.getValue());
			lengthPanel.add(Box.createRigidArea(new Dimension(50, 0)));
			lengthPanel.add(lengthSliderPanel);
			lengthPanel.add(lengthLabel);

			// generate panel
			generatePanel = new JPanel();
			generateBut = new JButton("Generate!");
			generateBut.addActionListener(this);
			
			generatePanel.add(generateBut);
			
			copyBut = new JButton("Copy to clipboard");
			copyBut.addActionListener(this);
			generatePanel.add(copyBut);
			
		
			// main panel
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			add(backPanel);
			// add(Box.createVerticalGlue());
			add(textFieldPanel);
			add(buttonPanel);
			add(lengthPanel);
			add(generatePanel);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == copyBut) {
				//copy password to clipboard
				myString = passwordText.getText();
				stringSelection = new StringSelection(myString);
				clipboard.setContents(stringSelection, null);
				int copy = JOptionPane.showConfirmDialog(null,
						"Successfully copied "+passwordText.getText()+" to clipboard",
						"Copy to clipboard", JOptionPane.DEFAULT_OPTION);
				
				
			}
			if (e.getSource() == lettersBox) {
				if (lettersBox.isSelected()) {
					letters = true;
				} else {
					letters = false;
				}
			}
			if (e.getSource() == numbersBox) {
				if (numbersBox.isSelected()) {
					numbers = true;
				} else {
					numbers = false;
				}
			}
			if (e.getSource() == specCharBox) {
				if (specCharBox.isSelected()) {
					specChar = true;
				} else {
					specChar = false;
				}
			}
			if (e.getSource() == generateBut) {
				if (letters || numbers || specChar) {
					passwordText.setText(PasswordGenerator(lengthSlider.getValue(), letters, numbers, specChar));
					
				}
			}
			if (e.getSource() == backBut) {
				frame.setContentPane(title);
				frame.revalidate();
			}

		}

		@Override
		public void stateChanged(ChangeEvent e) {
			if (e.getSource() == lengthSlider) {
				if (!((JSlider) e.getSource()).getValueIsAdjusting()) {
					lengthLabel.setText("Length: " + lengthSlider.getValue());
				}
			}

		}
	}

	public static String PasswordGenerator(int length, boolean letters, boolean nums, boolean specChar) {
		String password = "";
		char[] lettersArr = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r',
				's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
		char[] specCharArr = { '!', '@', '#', '$', '%', '^', '&', '*' };
		int rand = 0;
		for (int i = 0; i < length; i++) {
			rand = (int) (Math.random() * (3 - 0) + 0);
			if (rand == 0 && letters) {
				if ((int) (Math.random() * (2)) == 0) {
					password += lettersArr[(int) (Math.random() * (lettersArr.length - 0) + 0)];
				} else {
					password += (String.valueOf(lettersArr[(int) (Math.random() * (lettersArr.length - 0) + 0)]))
							.toUpperCase();
				}
			} else if (rand == 1 && specChar) {
				password += specCharArr[(int) (Math.random() * (specCharArr.length))];
			} else if (rand == 2 && nums) {
				password += (int) (Math.random() * 10);
			} else {
				i--;
			}
		}
		return password;
	}

}