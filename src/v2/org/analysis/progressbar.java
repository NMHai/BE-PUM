package v2.org.analysis;

import javax.swing.*;
import java.awt.*;

public class progressbar extends JFrame {

	JProgressBar current;
	JTextArea out;
	JButton find;
	Thread runner;
	int num = 0;

	public progressbar() {
		super("Progress");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel pane = new JPanel();
		pane.setLayout(new FlowLayout());
		current = new JProgressBar(0, 2000);
		current.setValue(0);
		current.setStringPainted(true);
		pane.add(current);
		setContentPane(pane);
	}

	public void iterate() {
		while (num < 2000) {
			current.setValue(num);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			num += 95;
		}
	}

	public static void main(String[] arguments) {
		progressbar frame = new progressbar();
		frame.pack();
		frame.setVisible(true);
		frame.iterate();
	}
}
