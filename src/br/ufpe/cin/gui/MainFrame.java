package br.ufpe.cin.gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

	// 4kb buffer
	public static final int BUFFER_SIZE = 4 * 1024;

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
		setTitle("Oracle File Storage");

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

		// Setup buttons
		this.setupPanel.setupButtons(this);
		this.menuPanel.setupButtons(this);
	}

	public void setPanel(JPanel panel) {
		// Process connection
		if (getContentPane() instanceof SetupPanel) {
			if (!this.setupPanel.connect()) {
				JOptionPane.showMessageDialog(this, "Could not connect to Oracle server.", "Error", JOptionPane.ERROR_MESSAGE);
				return;
			} else {
				// Update list of files in the table
				this.listPanel.updateList();
			}

			// Clear connection data
			this.setupPanel.clear();
		}

		setContentPane(panel);

		// Set the focus to the field
		// Worst possible way
		if (panel instanceof DownloadPanel) {
			this.downloadPanel.grabFocus();
		}
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

	// Creates link button -> panel
	public static void linkButton(JButton button, JPanel panel) {
		button.addActionListener((event) -> {
			MainFrame.getInstance().setPanel(panel);
		});
	}

	// Creates link field -> panel
	public static void linkTextField(JTextField textField, JPanel panel) {
		textField.addActionListener((event) -> {
			MainFrame.getInstance().setPanel(panel);
		});
	}

}
