package br.ufpe.cin.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import br.ufpe.cin.gui.panel.DownloadPanel;
import br.ufpe.cin.gui.panel.ListPanel;
import br.ufpe.cin.gui.panel.MenuPanel;
import br.ufpe.cin.gui.panel.SetupPanel;
import br.ufpe.cin.gui.panel.UploadPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = -1050056031505612691L;

	private static MainFrame INSTANCE;

	public static MainFrame getInstance() {
		return INSTANCE;
	}

	public static final Color BACKGROUND_COLOR = new Color(220, 220, 220);

	// App panels
	private SetupPanel setupPanel;
	private MenuPanel menuPanel;
	private UploadPanel uploadPanel;
	private ListPanel listPanel;
	private DownloadPanel downloadPanel;

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
		setContentPane(setupPanel);
	}

	public SetupPanel getSetupPanel() {
		return this.setupPanel;
	}

	public MenuPanel getMenuPanel() {
		return this.menuPanel;
	}

	public UploadPanel getUploadPanel() {
		return this.uploadPanel;
	}

	public ListPanel getListPanel() {
		return this.listPanel;
	}

	public DownloadPanel getDownloadPanel() {
		return this.downloadPanel;
	}

	private void generatePanels() {
		// Instantiate every panel
		this.setupPanel = new SetupPanel(this);
		this.menuPanel = new MenuPanel(this);
		this.uploadPanel = new UploadPanel(this);
		this.listPanel = new ListPanel(this);
		this.downloadPanel = new DownloadPanel(this);
	}

	public void setPanel(JPanel panel) {
		setContentPane(panel);
	}

	// Default back page button
	public static JButton backButton(JPanel panel) {
		JButton back = new JButton("Back");

		back.addActionListener((event) -> {
			MainFrame.getInstance().setPanel(panel);
		});

		back.setBounds(25, 650, 100, 25);

		return back;
	}

}
