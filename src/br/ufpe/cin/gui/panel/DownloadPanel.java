package br.ufpe.cin.gui.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ufpe.cin.gui.MainFrame;
import br.ufpe.cin.gui.listeners.DownloadListener;

public class DownloadPanel extends JPanel {

	private static final long serialVersionUID = 8085132611726910142L;

	private DownloadListener listener;

	private JTextField fileName;

	public DownloadPanel(MainFrame frame) {
		super();

		// Panel config
		setBounds(0, 0, 600, 700);
		setBackground(MainFrame.BACKGROUND_COLOR);
		setLayout(null);

		JLabel oracleLabel = new JLabel("Choose a file to download");
		JLabel downloadLabel = new JLabel("File name:");

		oracleLabel.setBounds(150, 100, 300, 50);
		downloadLabel.setBounds(100, 475, 100, 25);

		oracleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oracleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		fileName = new JTextField();

		JButton download = new JButton("Download");

		fileName.setBounds(100, 500, 400, 20);

		download.setBounds(400, 525, 100, 20);

		JButton back = MainFrame.backButton(frame.getMenuPanel());

		listener = new DownloadListener(fileName);

		fileName.addActionListener(listener);
		download.addActionListener(listener);

		add(oracleLabel);

		add(downloadLabel);

		add(fileName);

		add(download);

		add(back);
	}

	@Override
	public void grabFocus() {
		super.grabFocus();

		fileName.grabFocus();
	}
}
