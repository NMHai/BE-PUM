package v2.org.analysis;

import org.jakstab.Program;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.prefs.Preferences;

public class MainWindows extends JFrame {

	private static final String LAST_USED_FOLDER = "";
	private JMenuBar menuBar;
	private JMenu menuFile;
	private JMenuItem itmOpen;
	private JMenuItem itmExit;
	private JMenu menuHelp;
	// private JMenuItem itmWelcome;
	private JMenuItem itmAbout;
	private Dialog aboutDialog;
	private JScrollPane pnlImage;
	private JButton btnOK;
	private BufferedImage image;
	private JLabel picLabel;
	private JProgressBar progressBar;
	private Font bigFont = new Font("Serif", Font.TRUETYPE_FONT, 18);

	public static void main(String args[]) {
		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			// handle exception
		}
		new MainWindows();
	}

	MainWindows() {
		initComponents();
		//YenNguyen: Set this boolean attribute in order to main thread will not exit when analysis have finished
		Main.isGui = true;
		this.setTitle("BE-PUM");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	// Create menu bar
	private void initComponents() {
		menuBar = new JMenuBar();
		generateMenuFile();
		menuBar.add(menuFile);
		generateMenuHelp();
		menuBar.add(menuHelp);
		generateAboutDialog();

		progressBar = new JProgressBar(0, 100);
		progressBar.setValue(0);
		UIManager.put("ProgressBar.cycleTime", new Integer(10000));

		// We call setStringPainted, even though we don't want the
		// string to show up until we switch to determinate mode,
		// so that the progress bar height stays the same whether
		// or not the string is shown.
		progressBar.setStringPainted(true); // get space for the string
		progressBar.setString(""); // but don't paint it
		// progressBar = new JProgressBar(0, 100);
		// progressBar.setStringPainted(true);
		// // progressBar.setValue(0);

		// ======== this ========
		picLabel = new JLabel();
		pnlImage = new JScrollPane(picLabel);
		getContentPane().add(pnlImage);

		setJMenuBar(menuBar);
		getContentPane().add(progressBar, BorderLayout.SOUTH);
		pack();
		setSize(800, 600);
		setLocationRelativeTo(getOwner());
	}

	// Generate menu file
	private void generateMenuFile() {

		menuFile = new JMenu();
		menuFile.setText("File");

		// ---- itmOpen ----
		itmOpen = new JMenuItem();
		itmOpen.setText("Open File...");
		itmOpen.setFont(bigFont);
		itmOpen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					itmOpenActionPerformed(e);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		menuFile.add(itmOpen);
		menuFile.setFont(bigFont);
		menuFile.addSeparator();

		// ---- itmExit ----
		itmExit = new JMenuItem();
		itmExit.setText("Exit");
		itmExit.setFont(bigFont);
		itmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itmExitActionPerformed(e);
			}
		});
		menuFile.add(itmExit);
	}

	private void generateMenuHelp() {
		menuHelp = new JMenu();
		menuHelp.setText("Help");

		// ---- itmWelcome ----
		// itmWelcome = new JMenuItem();
		// itmWelcome.setText("Welcome");
		// menuHelp.add(itmWelcome);
		// menuHelp.addSeparator();

		// ---- itmAbout ----
		itmAbout = new JMenuItem();
		itmAbout.setText("About BE-PUM");
		itmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				itmAboutActionPerformed(e);
			}
		});
		menuHelp.add(itmAbout);
		itmAbout.setFont(bigFont);
		menuHelp.setFont(bigFont);
	}

	// Generate about dialog box to display info about Be-PUM
	private void generateAboutDialog() {
		aboutDialog = new Dialog(aboutDialog);
		aboutDialog.setTitle("About Dialog");

		btnOK = new JButton();
		// ---- btnOK ----
		btnOK.setText("OK");
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnOKActionPerformed(e);
			}
		});
		btnOK.setSize(50, 20);

		aboutDialog.add(btnOK);

		aboutDialog.pack();
		aboutDialog.setSize(200, 120);
		aboutDialog.setLocationRelativeTo(aboutDialog.getOwner());
	}

	// show message box
	private void itmAboutActionPerformed(ActionEvent e) {

		JOptionPane.showMessageDialog(this,
				"BE-PUM is a a tool which intends to generate Pushdown model\nfrom binary code of malwares.\n\n"
						+ "Version: 2.0\n\n" + "Published: 2013\n\n"
						+ "Authors:\nMinh Hai Nguyen\nThanh Tho Quan\nMizuhito Ogawa\nDo Duy Phong\n\n",
				"About BE-PUM", JOptionPane.INFORMATION_MESSAGE);
	}

	// Convert .dot to image and display on screen
	private void itmOpenActionPerformed(ActionEvent e) throws IOException {

		final String dir = promptForFile();
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		setIndeterminate(true);
		menuBar.setEnabled(false);

		Thread thread = new Thread(new Runnable() {
			public void run() {
				if (dir != null) {
					String output = convertDotToImg(dir);
					try {
						image = ImageIO.read(new File(output));
					} catch (IOException ex) {
						// handle exception...
					}
					picLabel.removeAll();
					picLabel.setIcon(new ImageIcon(image));
					picLabel.revalidate();
					setCursor(null);
					menuBar.setEnabled(true);
					setIndeterminate(false);
				}
			}
		});
		thread.start();
	}

	private void itmExitActionPerformed(ActionEvent e) {
		System.exit(0);
	}

	private void btnOKActionPerformed(ActionEvent e) {
		System.exit(0);
	}

	// Display File chooser box
	private String promptForFile() {
		Preferences prefs = Preferences.userRoot().node(getClass().getName());
		JFileChooser fc = new JFileChooser(prefs.get(LAST_USED_FOLDER, new File(".").getAbsolutePath()));

		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			// prefs.put(LAST_USED_FOLDER, fc.getParent().toString());
			return fc.getSelectedFile().getAbsolutePath();
		} else {
			return null;
		}
	}

	// Convert .dot file to image and save it into the tmp folder
	private String convertDotToImg(String path) {
		try {
			String root = System.getProperty("user.dir");
			// String newFile = copyFileUsingStream(new File(path), root
			// + "\\dot\\tmp\\");
			Main.analyzeFile(path);
			String nPath = Program.getProgram().generatePathFileName(path);
			String output = nPath + ".png";
			String command = root + "\\data\\dot\\dot.exe -Tpng " + nPath + "_model.dot -o " + output;
			System.out.println(command);
			Process p = Runtime.getRuntime().exec(command);
			p.waitFor();
			return output;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// copy selected file into folder
	private String copyFileUsingStream(File source, String dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		File newFile = new File(dest + source.getName());
		try {
			is = new FileInputStream(source);
			os = new FileOutputStream(newFile);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				// setProgressValue((int) ((length * 100 / source.length()) * 10
				// / 100));
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			os.close();
		}
		return newFile.getAbsolutePath();
	}

	private void setIndeterminate(boolean flag) {
		progressBar.setIndeterminate(flag);
		Rectangle progressRect = progressBar.getBounds();
		progressRect.x = 0;
		progressRect.y = 0;
		progressBar.paintImmediately(progressRect);
	}
}
