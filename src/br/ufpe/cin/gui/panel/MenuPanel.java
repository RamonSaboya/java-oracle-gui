package br.ufpe.cin.gui.panel;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import br.ufpe.cin.gui.MainFrame;

public class MenuPanel extends JPanel {

	private static final long serialVersionUID = -2650804644648501806L;

	private JButton upload;
	private JButton list;
	private JButton download;

	public MenuPanel(MainFrame frame) {
		super();

		// Panel config
		setBounds(0, 0, 600, 700);
		setBackground(MainFrame.BACKGROUND_COLOR);
		setLayout(null);

		JLabel oracleLabel = new JLabel("What you wanna do?");
		oracleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oracleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		oracleLabel.setBounds(150, 100, 300, 50);

		upload = new JButton("Upload");
		list = new JButton("List files");
		download = new JButton("Download");

		upload.setBounds(100, 550, 100, 50);
		list.setBounds(250, 550, 100, 50);
		download.setBounds(400, 550, 100, 50);

		JButton back = MainFrame.backButton(frame.getSetupPanel());
		back.setText("Disconnect");

		add(oracleLabel);

		add(upload);
		add(list);
		add(download);

		add(back);
	}

}
