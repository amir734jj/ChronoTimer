import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Timer;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * The Main class
 * 
 * Note that this class is the main driver class for ChronoTimer Project
 * 
 */
public class Main {

	public static boolean isOn;
	public static String fileName = "./src/CHRONO TEST DATA.txt";
	public static String OrgfileName = "./src/CHRONO TEST DATA.txt";
	public static String outPut = null;
	public static String TimeVal = null;
	public static JTextArea TimeLabel = null;
	public static JPanel TimePanel = null;
	public static boolean TimeisON = false;
	public static boolean TempFolderisON = false;
	public static String TempFolder = "D:\\";
	public static boolean REQisDONE = false;
	public static boolean REQisWORKING = false;

	public static void main(String[] args) {
		groupINFO();
	}

	/**
	 * initializeREQSatisfier initializes project to correct state Note: this
	 * includes set-up of GUI, and exports
	 * 
	 * @param none
	 * @return void
	 */

	public static boolean exists(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName)
					.openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_MULT_CHOICE);
		} catch (Exception e) {
			return false;
		}
	}

	public static void initializeREQSatisfier() {
		if (Main.REQisDONE == true || Main.REQisWORKING == true)
			return;
		final JFrame frame = new JFrame("Requirement Satisfier");
		JPanel panel = new JPanel(new GridLayout());

		final JTextField text1 = new JTextField("EMPTY ::-> BROWSE");
		text1.setEditable(false);
		final JTextField text2 = new JTextField(
				"http://localhost:8000/sendresults");

		text1.setFont(new Font("Consolas", Font.BOLD, 12));
		text2.setFont(new Font("Consolas", Font.BOLD, 12));
		text1.setBackground(Color.WHITE);
		final JButton b1 = new JButton("FIX TEMP FOLDER");
		final JButton b2 = new JButton("FIX HTTP SERVER");
		b1.setSize(10, 10);
		b2.setSize(10, 10);
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (Main.TempFolderisON == false
						|| text1.getText()
								.equalsIgnoreCase("EMPTY ::-> BROWSE")) {
					Main.setTempFolder();
					if (!validatePATH(TempFolder)) {
						JOptionPane
								.showMessageDialog(
										null,
										"SELECTED TEMP DIRECTORY DOES NOT HAVE NECESSARY PERSMISSON",
										"PROGRAM INFO\n",
										JOptionPane.ERROR_MESSAGE);
						return;
					}

					text1.setText("TEMP FOLDER IS READY, PATH IS: "
							+ Main.TempFolder);
					b1.setEnabled(false);
					text1.setEditable(false);
					Main.TempFolderisON = true;

					if (ExportToPost.PATHisON) {
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							Thread.currentThread().interrupt();
						}
						frame.setVisible(false);
						Main.REQisDONE = true;
						Main.REQisWORKING = false;
						JOptionPane.showMessageDialog(null, "TEMP FOLDER: "
								+ Main.TempFolder + "\nHTTPSERVER: "
								+ ExportToPost.PATH, "PROGRAM INFO\n",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		b2.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!validateURL(text2.getText())) {
					JOptionPane.showMessageDialog(null,
							"INVALID HTTP SERVER URL OR SERVER IS NOT ACTIVE",
							"PROGRAM INFO\n", JOptionPane.ERROR_MESSAGE);
					return;
				}
				ExportToPost.PATH = text2.getText();
				text2.setText("EXPORT TO HTTP SERVER IS READY");
				b2.setEnabled(false);
				text2.setEditable(false);
				ExportToPost.PATHisON = true;
				if (Main.TempFolderisON) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException ex) {
						Thread.currentThread().interrupt();
					}

					frame.setVisible(false);
					Main.REQisDONE = true;
					Main.REQisWORKING = false;
					JOptionPane.showMessageDialog(null, "TEMP FOLDER: "
							+ Main.TempFolder + "\nHTTPSERVER: "
							+ ExportToPost.PATH, "PROGRAM INFO\n",
							JOptionPane.INFORMATION_MESSAGE);
				}

			}
		});

		panel.add(text1);
		panel.add(b1);
		panel.add(text2);
		panel.add(b2);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(950, 85);
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 80);
		frame.repaint();
		REQisWORKING = true;

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				if (Main.TempFolderisON == false
						|| ExportToPost.PATHisON == false)
					JOptionPane
							.showMessageDialog(
									null,
									"YOU HAVE NOT SATISFY THE REQUIREMENT TO RUN THE PROGRAM",
									"PROGRAM INFO\n",
									JOptionPane.INFORMATION_MESSAGE);
				System.exit(1);
			}
		});
	}

	/**
	 * groupINFO exports initial html code for project
	 * 
	 * @param none
	 * @return void
	 */
	public static void groupINFO() {
		String msg = "<html><b>* CHRONO TIMER PROJECT<br />* <i>TEAM JUNIT</i><br /><br />- TEAM MEMBERS:<br /><br />      1. David Kirschnik<br />      2. Joseph Socha<br />      5. Seyedamirhossein Hesamian<br />      <strike>4. Tong Vue</strike><br />      <strike>5. Alex Woehrer</strike><br />      </b></html>";

		final JFrame frame = new JFrame("PROGRAM INFO");
		JPanel panel = new JPanel(new GridBagLayout());

		JButton b1 = new JButton("Continue To Program");
		JButton b2 = new JButton("Photo Gallery");
		JButton b3 = new JButton("Group Home Page");
		JButton b4 = new JButton("Result Page");
		b1.setBackground(Color.WHITE);
		b1.setForeground(Color.BLACK);
		b1.setOpaque(true);
		b2.setBackground(Color.WHITE);
		b2.setForeground(Color.BLACK);
		b2.setOpaque(true);
		b3.setBackground(Color.WHITE);
		b3.setForeground(Color.BLACK);
		b3.setOpaque(true);
		b4.setBackground(Color.WHITE);
		b4.setForeground(Color.BLACK);
		b4.setOpaque(true);

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.SelectModule();
				new java.util.Timer().schedule(new java.util.TimerTask() {
					@Override
					public void run() {
						frame.setVisible(false);
						return;
					}
				}, 5000);
			}
		});

		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.groupIMAGE();
			}
		});

		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().browse(
								new URI("http://junit.rr.nu/home"));
					}
				} catch (Exception x) {
					Log.write("UNSUPPORTED SYSTEM");
				}
			}
		});

		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (Desktop.isDesktopSupported()) {
						Desktop.getDesktop().browse(
								new URI("http://junit.rr.nu/"));
					}
				} catch (Exception x) {
					Log.write("UNSUPPORTED SYSTEM");
				}
			}
		});
		JLabel text = new JLabel(msg);
		text.setForeground(Color.BLACK);
		text.setBackground(Color.LIGHT_GRAY);
		panel.setBackground(Color.LIGHT_GRAY);
		text.setOpaque(true);
		panel.add(text);
		panel.add(b1);
		panel.add(b2);
		panel.add(b3);
		panel.add(b4);
		frame.add(panel);
		frame.setSize(690, 230);
		frame.setResizable(false);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 80);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * groupIm exports group images
	 * 
	 * @param none
	 * @return void
	 */
	public static void groupIMAGE() {
		Image img1 = null;
		Image img2 = null;
		Image img3 = null;
		Image gap = null;
		try {
			gap = ImageIO.read(new URL("http://junit.freeiz.com/img/gap.jpg"));
			img1 = ImageIO
					.read(new URL("http://junit.freeiz.com/img/img2.jpg"));
			img2 = ImageIO
					.read(new URL("http://junit.freeiz.com/img/img3.jpg"));
			img3 = ImageIO
					.read(new URL("http://junit.freeiz.com/img/img1.jpg"));

		} catch (IOException e) {
		}

		JFrame frame = new JFrame();
		JPanel mainPanel = new JPanel(new FlowLayout());
		mainPanel.add(new JLabel(new ImageIcon(img1)));
		mainPanel.add(new JLabel(new ImageIcon(gap)));
		mainPanel.add(new JLabel(new ImageIcon(img2)));
		mainPanel.add(new JLabel(new ImageIcon(gap)));
		mainPanel.add(new JLabel(new ImageIcon(img3)));
		mainPanel.setBackground(Color.DARK_GRAY);
		frame.setSize(1100, 390);
		frame.setResizable(false);
		frame.add(mainPanel);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 80);

	}

	/**
	 * setTempFolder handles the setup of temp folder
	 * 
	 * @param none
	 * @return void
	 */
	public static void setTempFolder() {
		JOptionPane.showMessageDialog(null,
				"PLEASE SELECT PROGRAM'S TEMP FOLDER", "PROGRAM INFO\n",
				JOptionPane.WARNING_MESSAGE);
		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Main.TempFolder = chooser.getSelectedFile().getAbsolutePath();
			Main.TempFolderisON = true;
		}
		while (!Main.TempFolderisON) {
			JOptionPane.showMessageDialog(null,
					"PLEASE MAKE SURE YOU HAVE SELECTED PROGRAM'S TEMP FOLDER",
					"PROGRAM INFO\n", JOptionPane.ERROR_MESSAGE);
			Main.setTempFolder();
		}
	}

	/**
	 * SelectModule handles the GUI selection of module
	 * 
	 * @param none
	 * @return void
	 */
	public static void SelectModule() {
		final JFrame frame = new JFrame("Select Module");
		JPanel panel = new JPanel(new GridLayout());

		final JCheckBox box1 = new JCheckBox("Interactive Log");
		final JCheckBox box2 = new JCheckBox("Interactive Channel");
		final JCheckBox box3 = new JCheckBox("Interactive Time");

		final JButton button = new JButton(
				"<html><i><strong>START</strong></i></html>");
		button.setBackground(Color.ORANGE);
		panel.setBackground(Color.ORANGE);
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Main.initializeREQSatisfier();
				if (Main.TempFolderisON) {
					Log.write("ChronoTimer Started");
					Main.initializeGui();

					if (box1.isSelected())
						Main.initializeInteractiveLog();

					if (box2.isSelected())
						Main.initializeLiveInterface();

					if (box3.isSelected())
						Main.initializeTimeGui();

					frame.setVisible(false);
				}
			}
		});
		panel.add(new JLabel(
				"<html><i><strong>&nbsp;Select Module:</strong></i></html>"));
		panel.add(box1);
		panel.add(box2);
		panel.add(box3);
		panel.add(button);
		frame.add(panel);
		frame.setSize(682, 90);
		frame.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 80);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	/**
	 * initializeTimeRefresh handles the setup of refresh time for GUI
	 * 
	 * @param none
	 * @return void
	 */
	public static void initializeTimeRefresh() {
		Time.setTimeGui();
		TimeLabel.setText(TimeVal);
		TimePanel.repaint();
	}

	/**
	 * initializeTimeGui handles the setup of time for GUI
	 * 
	 * @param none
	 * @return void
	 */
	public static void initializeTimeGui() {
		Log.write("Module: Interactive Time: Started");
		TimeisON = true;
		final JFrame frame = new JFrame("Time");
		final JPanel panel = new JPanel(new FlowLayout());
		panel.setBackground(Color.YELLOW);
		Main.TimePanel = panel;
		TimeLabel = new JTextArea(TimeVal);
		Font font1 = new Font("Serif", Font.ITALIC, 20);
		TimeLabel.setFont(font1);
		TimeLabel.setForeground(Color.BLACK);
		TimeLabel.setBackground(Color.YELLOW);
		Time.setTimeGui();
		TimeLabel.setText(TimeVal);
		TimeLabel.setEditable(false);
		panel.add(new JLabel("<html><b><i>Current Time:   </i></b></html>"));
		panel.add(TimeLabel);
		frame.add(panel);
		frame.setVisible(true);
		frame.setSize(200, 75);
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width - frame.getSize().width - 11, 10);
	}

	/**
	 * initializeGui handles the initialization for GUI
	 * 
	 * @param none
	 * @return void
	 */
	public static void initializeGui() {
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		frame.add(panel);
		frame.setTitle("BROWSE FROM FILE OR INTERACTIVE SHELL");
		frame.setVisible(true);

		final JButton button1 = new JButton("BROWSE FROM FILE");
		button1.setBackground(Color.CYAN);
		button1.setForeground(Color.DARK_GRAY);
		final JButton button2 = new JButton("INTERACTIVE SHELL");
		button2.setBackground(Color.GREEN);
		button2.setForeground(Color.DARK_GRAY);
		ActionListener buttonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (e.getActionCommand().equals("BROWSE FROM FILE")) {
					frame.setVisible(false);
					initializeFromFile();
				} else if (e.getActionCommand().equals("INTERACTIVE SHELL")) {
					frame.setVisible(false);
					initializeInteractive();
				} else
					throw new IllegalComponentStateException();
			}
		};

		button1.addActionListener(buttonListener);
		button2.addActionListener(buttonListener);

		panel.setLayout(new GridLayout());
		panel.add(button1);
		panel.add(button2);

		frame.setSize(450, 100);
		frame.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 250);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	/**
	 * initializeInteractive handles the setup of GUI window
	 * 
	 * @param none
	 * @return void
	 */
	private static void initializeInteractive() {
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		JLabel label = new JLabel(" ENTER COMMAND HERE: ");
		JButton button1 = new JButton("ENTER");
		JButton button2 = new JButton("BACK TO PREVIOUS WINDOW");
		final JTextField text = new JTextField("12:00:00.0\tTIME 12:00:00", 30);
		ActionListener commandListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!e.getActionCommand().isEmpty()) {
						Main.fileName = text.getText();
						new CommandParser(text.getText(), false).process();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};

		ActionListener goBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				initializeGui();
			}
		};

		button1.addActionListener(commandListener);
		button2.addActionListener(goBack);
		panel.setLayout(new FlowLayout());
		panel.add(label);
		panel.add(text);
		panel.add(button1);
		panel.add(button2);
		frame.add(panel);
		frame.setSize(400, 130);
		frame.setResizable(false);
		frame.setVisible(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 250);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	/**
	 * initializeFromFile handles the setup for user selected file using GUI
	 * 
	 * @param none
	 * @return void
	 */
	private static void initializeFromFile() {
		final JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		final JTextField text = new JTextField(Main.OrgfileName);

		ActionListener initializeFromFileListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if (!e.getActionCommand().isEmpty()) {
						Main.fileName = text.getText();

						if (!validatePATH_READ_ONLY(Main.fileName)) {
							JOptionPane
									.showMessageDialog(
											null,
											"SELECTED TEXT FILE DOES NOT HAVE NECESSARY PERSMISSON TO READ OR DOES NOT EXIST",
											"PROGRAM INFO\n",
											JOptionPane.ERROR_MESSAGE);
							return;
						}

						JOptionPane
								.showMessageDialog(
										null,
										"BROWSE LOCATION WHERE YOU WANT TO STORE THE OUTPUT FILE",
										"PROGRAM INFO",
										JOptionPane.PLAIN_MESSAGE);
						Main.initializeOutPutPath();
						if (Main.outPut == null)
							return;
						if (!validatePATH(Main.outPut)) {
							JOptionPane
									.showMessageDialog(
											null,
											"SELECTED RESULT DIRECTORY DOES NOT HAVE NECESSARY PERSMISSON",
											"PROGRAM INFO\n",
											JOptionPane.ERROR_MESSAGE);
							return;
						}

						new CommandParser(Main.fileName, true).process();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};

		ActionListener initializeFileChooser = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter(
						"Text File", "txt");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(chooser);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					text.setText(chooser.getSelectedFile().getAbsolutePath());
				}
			}
		};

		ActionListener goBack = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				initializeGui();
			}
		};

		JButton button1 = new JButton("BROWSE");
		JButton button2 = new JButton("BACK TO PREVIOUS WINDOW");
		JButton button3 = new JButton("ENTER");
		button1.setMaximumSize(new Dimension(10, 10));
		button1.addActionListener(initializeFileChooser);
		button2.addActionListener(goBack);
		button3.addActionListener(initializeFromFileListener);
		panel.add(new JLabel(
				" ENTER THE PATH OF COMMAND FILE OR ClICK ON BROWSE: "));
		panel.add(text);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		frame.setTitle("BROWSE FROM FILE");
		frame.setVisible(true);
		frame.setSize(350, 130);
		frame.setResizable(false);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2,
				(dim.height / 2 - frame.getSize().height / 2) - 250);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	}

	/**
	 * initializeInteractiveLog handles the setup for interactive log
	 * 
	 * @param none
	 * @return void
	 */
	private static void initializeInteractiveLog() {
		Log.write("Module: Interactive Log: Started");
		final JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Display Log Area"));
		final JTextArea text = new JTextArea(16, 58);
		text.setFont(new Font("Arial", Font.BOLD, 14));
		text.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(text);
		scrollPane
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		final JFrame frame = new JFrame("Live Log");
		panel.add(scrollPane);
		frame.add(panel);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setSize(760, 360);
		frame.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height
				/ 2 - frame.getSize().height / 2 + 150);
		new Timer().schedule(new TimerTask() {
			public void run() {
				if (Log.hasChanged) {
					text.setText(readFile(Main.TempFolder + "\\System.log",
							Charset.defaultCharset()));
					panel.repaint();
				}
			}
		}, 1, 100);

	}

	/**
	 * initializeOutPutPath
	 * 
	 * @param none
	 * @return void
	 */
	private static void initializeOutPutPath() {

		JFileChooser chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = chooser.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			Main.outPut = chooser.getSelectedFile().getAbsolutePath();
		}

	}

	/**
	 * readFile reads from given file
	 * 
	 * @param string
	 *            path to file
	 * @param Charset
	 *            for encoding
	 * @return String read
	 */
	private static String readFile(String path, Charset encoding) {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get(path));
			return encoding.decode(ByteBuffer.wrap(encoded)).toString();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * initializeLiveInterface GUI
	 * 
	 * @param none
	 * @return void
	 */
	public static void initializeLiveInterface() {
		Log.write("Module: Interactive Channel: Started");
		final JFrame frame = new JFrame("Channel Interactive");
		final JPanel panel = new JPanel();
		final JRadioButton Dbutton0 = new JRadioButton(
				"Connect/Disconnect Channel 0  ");
		final JRadioButton Dbutton1 = new JRadioButton(
				"Connect/Disconnect Channel 1  ");
		final JRadioButton Dbutton2 = new JRadioButton(
				"Connect/Disconnect Channel 2  ");
		final JRadioButton Dbutton3 = new JRadioButton(
				"Connect/Disconnect Channel 3  ");
		final JRadioButton Dbutton4 = new JRadioButton(
				"Connect/Disconnect Channel 4  ");
		final JRadioButton Dbutton5 = new JRadioButton(
				"Connect/Disconnect Channel 5  ");
		final JRadioButton Dbutton6 = new JRadioButton(
				"Connect/Disconnect Channel 6  ");
		final JRadioButton Dbutton7 = new JRadioButton(
				"Connect/Disconnect Channel 7  ");
		final JRadioButton Dbutton8 = new JRadioButton(
				"Connect/Disconnect Channel 8  ");
		final JRadioButton Dbutton9 = new JRadioButton(
				"Connect/Disconnect Channel 9  ");
		final JRadioButton Dbutton10 = new JRadioButton(
				"Connect/Disconnect Channel 10");
		final JRadioButton Dbutton11 = new JRadioButton(
				"Connect/Disconnect Channel 11");
		final JRadioButton Ebutton0 = new JRadioButton(
				"Enable/Disable Channel 0  ");
		final JRadioButton Ebutton1 = new JRadioButton(
				"Enable/Disable Channel 1  ");
		final JRadioButton Ebutton2 = new JRadioButton(
				"Enable/Disable Channel 2  ");
		final JRadioButton Ebutton3 = new JRadioButton(
				"Enable/Disable Channel 3  ");
		final JRadioButton Ebutton4 = new JRadioButton(
				"Enable/Disable Channel 4  ");
		final JRadioButton Ebutton5 = new JRadioButton(
				"Enable/Disable Channel 5  ");
		final JRadioButton Ebutton6 = new JRadioButton(
				"Enable/Disable Channel 6  ");
		final JRadioButton Ebutton7 = new JRadioButton(
				"Enable/Disable Channel 7  ");
		final JRadioButton Ebutton8 = new JRadioButton(
				"Enable/Disable Channel 8  ");
		final JRadioButton Ebutton9 = new JRadioButton(
				"Enable/Disable Channel 9  ");
		final JRadioButton Ebutton10 = new JRadioButton(
				"Enable/Disable Channel 10");
		final JRadioButton Ebutton11 = new JRadioButton(
				"Enable/Disable Channel 11");

		ActionListener initializeJRadioButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == Dbutton0) {
					if (Dbutton0.isSelected())
						Channel.connect(0);
					else
						Channel.disconnect(0);

					if (!Channel.isSucceed && Dbutton0.isSelected())
						Dbutton0.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton0.isSelected())
						Dbutton0.setSelected(true);

				} else if (e.getSource() == Dbutton1) {
					if (Dbutton1.isSelected())
						Channel.connect(1);
					else
						Channel.disconnect(1);

					if (!Channel.isSucceed && Dbutton1.isSelected())
						Dbutton1.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton1.isSelected())
						Dbutton1.setSelected(true);
				}

				else if (e.getSource() == Dbutton2) {
					if (Dbutton2.isSelected())
						Channel.connect(2);
					else
						Channel.disconnect(2);

					if (!Channel.isSucceed && Dbutton2.isSelected())
						Dbutton2.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton2.isSelected())
						Dbutton2.setSelected(true);
				}

				else if (e.getSource() == Dbutton3) {
					if (Dbutton3.isSelected())
						Channel.connect(3);
					else
						Channel.disconnect(3);

					if (!Channel.isSucceed && Dbutton3.isSelected())
						Dbutton3.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton3.isSelected())
						Dbutton3.setSelected(true);
				}

				else if (e.getSource() == Dbutton4) {
					if (Dbutton4.isSelected())
						Channel.connect(4);
					else
						Channel.disconnect(4);

					if (!Channel.isSucceed && Dbutton4.isSelected())
						Dbutton4.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton4.isSelected())
						Dbutton4.setSelected(true);
				}

				else if (e.getSource() == Dbutton5) {
					if (Dbutton5.isSelected())
						Channel.connect(5);
					else
						Channel.disconnect(5);

					if (!Channel.isSucceed && Dbutton5.isSelected())
						Dbutton5.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton5.isSelected())
						Dbutton5.setSelected(true);
				}

				else if (e.getSource() == Dbutton6) {
					if (Dbutton6.isSelected())
						Channel.connect(6);
					else
						Channel.disconnect(6);

					if (!Channel.isSucceed && Dbutton6.isSelected())
						Dbutton6.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton6.isSelected())
						Dbutton6.setSelected(true);
				}

				else if (e.getSource() == Dbutton7) {
					if (Dbutton7.isSelected())
						Channel.connect(7);
					else
						Channel.disconnect(7);

					if (!Channel.isSucceed && Dbutton7.isSelected())
						Dbutton7.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton7.isSelected())
						Dbutton7.setSelected(true);
				}

				else if (e.getSource() == Dbutton8) {
					if (Dbutton8.isSelected())
						Channel.connect(8);
					else
						Channel.disconnect(8);

					if (!Channel.isSucceed && Dbutton8.isSelected())
						Dbutton8.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton8.isSelected())
						Dbutton8.setSelected(true);
				}

				else if (e.getSource() == Dbutton9) {
					if (Dbutton9.isSelected())
						Channel.connect(9);
					else
						Channel.disconnect(9);

					if (!Channel.isSucceed && Dbutton9.isSelected())
						Dbutton9.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton9.isSelected())
						Dbutton9.setSelected(true);
				}

				else if (e.getSource() == Dbutton10) {
					if (Dbutton10.isSelected())
						Channel.connect(10);
					else
						Channel.disconnect(10);

					if (!Channel.isSucceed && Dbutton10.isSelected())
						Dbutton10.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton10.isSelected())
						Dbutton10.setSelected(true);
				}

				else if (e.getSource() == Dbutton11) {
					if (Dbutton11.isSelected())
						Channel.connect(11);
					else
						Channel.disconnect(11);

					if (!Channel.isSucceed && Dbutton11.isSelected())
						Dbutton11.setSelected(false);
					else if (!Channel.isSucceed && !Dbutton11.isSelected())
						Dbutton11.setSelected(true);

				}
				if (e.getSource() == Ebutton0) {
					if (Ebutton0.isSelected())
						Channel.enable(0);
					else
						Channel.disable(0);

					if (!Channel.isSucceed && Ebutton0.isSelected())
						Ebutton0.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton0.isSelected())
						Ebutton0.setSelected(true);

				} else if (e.getSource() == Ebutton1) {
					if (Ebutton1.isSelected())
						Channel.enable(1);
					else
						Channel.disable(1);

					if (!Channel.isSucceed && Ebutton1.isSelected())
						Ebutton1.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton1.isSelected())
						Ebutton1.setSelected(true);
				}

				else if (e.getSource() == Ebutton2) {
					if (Ebutton2.isSelected())
						Channel.enable(2);
					else
						Channel.disable(2);

					if (!Channel.isSucceed && Ebutton2.isSelected())
						Ebutton2.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton2.isSelected())
						Ebutton2.setSelected(true);
				}

				else if (e.getSource() == Ebutton3) {
					if (Ebutton3.isSelected())
						Channel.enable(3);
					else
						Channel.disable(3);

					if (!Channel.isSucceed && Ebutton3.isSelected())
						Ebutton3.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton3.isSelected())
						Ebutton3.setSelected(true);
				}

				else if (e.getSource() == Ebutton4) {
					if (Ebutton4.isSelected())
						Channel.enable(4);
					else
						Channel.disable(4);

					if (!Channel.isSucceed && Ebutton4.isSelected())
						Ebutton4.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton4.isSelected())
						Ebutton4.setSelected(true);
				}

				else if (e.getSource() == Ebutton5) {
					if (Ebutton5.isSelected())
						Channel.enable(5);
					else
						Channel.disable(5);

					if (!Channel.isSucceed && Ebutton5.isSelected())
						Ebutton5.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton5.isSelected())
						Ebutton5.setSelected(true);
				}

				else if (e.getSource() == Ebutton6) {
					if (Ebutton6.isSelected())
						Channel.enable(6);
					else
						Channel.disable(6);

					if (!Channel.isSucceed && Ebutton6.isSelected())
						Ebutton6.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton6.isSelected())
						Ebutton6.setSelected(true);
				}

				else if (e.getSource() == Ebutton7) {
					if (Ebutton7.isSelected())
						Channel.enable(7);
					else
						Channel.disable(7);

					if (!Channel.isSucceed && Ebutton7.isSelected())
						Ebutton7.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton7.isSelected())
						Ebutton7.setSelected(true);

				}

				else if (e.getSource() == Ebutton8) {
					if (Ebutton8.isSelected())
						Channel.enable(8);
					else
						Channel.disable(8);

					if (!Channel.isSucceed && Ebutton8.isSelected())
						Ebutton8.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton8.isSelected())
						Ebutton8.setSelected(true);
				}

				else if (e.getSource() == Ebutton9) {
					if (Ebutton9.isSelected())
						Channel.enable(9);
					else
						Channel.disable(9);

					if (!Channel.isSucceed && Ebutton9.isSelected())
						Ebutton9.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton9.isSelected())
						Ebutton9.setSelected(true);

				}

				else if (e.getSource() == Ebutton10) {
					if (Ebutton10.isSelected())
						Channel.enable(10);
					else
						Channel.disable(10);

					if (!Channel.isSucceed && Ebutton10.isSelected())
						Ebutton10.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton10.isSelected())
						Ebutton10.setSelected(true);
				}

				else if (e.getSource() == Ebutton11) {
					if (Ebutton11.isSelected())
						Channel.enable(11);
					else
						Channel.disable(11);

					if (!Channel.isSucceed && Ebutton11.isSelected())
						Ebutton11.setSelected(false);
					else if (!Channel.isSucceed && !Ebutton11.isSelected())
						Ebutton11.setSelected(true);

				}
			}
		};

		Ebutton0.addActionListener(initializeJRadioButtonListener);
		Ebutton1.addActionListener(initializeJRadioButtonListener);
		Ebutton2.addActionListener(initializeJRadioButtonListener);
		Ebutton3.addActionListener(initializeJRadioButtonListener);
		Ebutton4.addActionListener(initializeJRadioButtonListener);
		Ebutton5.addActionListener(initializeJRadioButtonListener);
		Ebutton6.addActionListener(initializeJRadioButtonListener);
		Ebutton7.addActionListener(initializeJRadioButtonListener);
		Ebutton8.addActionListener(initializeJRadioButtonListener);
		Ebutton9.addActionListener(initializeJRadioButtonListener);
		Ebutton10.addActionListener(initializeJRadioButtonListener);
		Ebutton11.addActionListener(initializeJRadioButtonListener);
		Dbutton0.addActionListener(initializeJRadioButtonListener);
		Dbutton1.addActionListener(initializeJRadioButtonListener);
		Dbutton2.addActionListener(initializeJRadioButtonListener);
		Dbutton3.addActionListener(initializeJRadioButtonListener);
		Dbutton4.addActionListener(initializeJRadioButtonListener);
		Dbutton5.addActionListener(initializeJRadioButtonListener);
		Dbutton6.addActionListener(initializeJRadioButtonListener);
		Dbutton7.addActionListener(initializeJRadioButtonListener);
		Dbutton8.addActionListener(initializeJRadioButtonListener);
		Dbutton9.addActionListener(initializeJRadioButtonListener);
		Dbutton10.addActionListener(initializeJRadioButtonListener);
		Dbutton11.addActionListener(initializeJRadioButtonListener);
		panel.add(Dbutton0);
		panel.add(Dbutton1);
		panel.add(Dbutton2);
		panel.add(Dbutton3);
		panel.add(Dbutton4);
		panel.add(Dbutton5);
		panel.add(Dbutton6);
		panel.add(Dbutton7);
		panel.add(Dbutton8);
		panel.add(Dbutton9);
		panel.add(Dbutton10);
		panel.add(Dbutton11);
		panel.add(Ebutton0);
		panel.add(Ebutton1);
		panel.add(Ebutton2);
		panel.add(Ebutton3);
		panel.add(Ebutton4);
		panel.add(Ebutton5);
		panel.add(Ebutton6);
		panel.add(Ebutton7);
		panel.add(Ebutton8);
		panel.add(Ebutton9);
		panel.add(Ebutton10);
		panel.add(Ebutton11);
		panel.setLayout(new FlowLayout());
		frame.add(panel);
		frame.setSize(650, 280);
		frame.setLocation(10, 10);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	// validates the url of http server
	public static boolean validateURL(String URLName) {
		try {
			HttpURLConnection.setFollowRedirects(false);
			HttpURLConnection con = (HttpURLConnection) new URL(URLName)
					.openConnection();
			con.setRequestMethod("HEAD");
			return (con.getResponseCode() == HttpURLConnection.HTTP_MULT_CHOICE);
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean validatePATH(String path) {
		File f = new File(path);
		return f.canWrite() && f.canRead();
	}

	public static boolean validatePATH_READ_ONLY(String path) {
		File f = new File(path);
		return f.canRead();
	}
}
