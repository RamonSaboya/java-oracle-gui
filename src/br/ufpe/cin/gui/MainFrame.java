package br.ufpe.cin.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1050056031505612691L;

	private static MainFrame INSTANCE;

	public static MainFrame getInstance() {
		return INSTANCE;
	}

	public static final Color BACKGROUND_COLOR = new Color(220, 220, 220);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainFrame() {
		INSTANCE = this;

		// Frame config
		setResizable(false);
		setBounds(0, 0, 600, 740);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Oracle DB");

		generatePanels();

		// Start panel
		setContentPane(null);
	}

	private void generatePanels() {
	}

	public void setPanel(JPanel panel) {
		setContentPane(panel);
	}

}
