package br.ufpe.cin.gui.panel;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ufpe.cin.gui.MainFrame;
import br.ufpe.cin.oracle.Oracle;

public class SetupPanel extends JPanel {

	private static final long serialVersionUID = -7567027577115621802L;

	private JTextField host;
	private JTextField SID;
	private JTextField user;
	private JPasswordField password;

	private JButton connect;

	public SetupPanel(MainFrame frame) {
		super();

		// Panel config
		setBounds(0, 0, 600, 700);
		setBackground(MainFrame.BACKGROUND_COLOR);
		setLayout(null);

		JLabel oracleLabel = new JLabel("Oracle DB");
		oracleLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		oracleLabel.setForeground(Color.BLACK);
		oracleLabel.setBackground(MainFrame.BACKGROUND_COLOR);
		oracleLabel.setOpaque(true);
		oracleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		oracleLabel.setBounds(100, 215, 75, 20);

		JLabel hostLabel = new JLabel("Host:");
		JLabel SIDLabel = new JLabel("SID:");
		JLabel userLabel = new JLabel("User:");
		JLabel passwordLabel = new JLabel("Password:");

		hostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		SIDLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		hostLabel.setBounds(100, 250, 75, 25);
		SIDLabel.setBounds(100, 300, 75, 25);
		userLabel.setBounds(100, 350, 75, 25);
		passwordLabel.setBounds(100, 400, 75, 25);

		host = new JTextField();
		SID = new JTextField();
		user = new JTextField();
		password = new JPasswordField();

		host.setBounds(200, 250, 300, 25);
		SID.setBounds(200, 300, 300, 25);
		user.setBounds(200, 350, 300, 25);
		password.setBounds(200, 400, 300, 25);

		connect = new JButton("Connect");
		connect.setBounds(400, 430, 100, 20);

		JSeparator topSeparator = new JSeparator();
		JSeparator leftSeparator = new JSeparator();
		JSeparator bottomSeparator = new JSeparator();
		JSeparator rightSeparator = new JSeparator();

		topSeparator.setBounds(75, 225, 450, 1);
		leftSeparator.setBounds(75, 225, 1, 250);
		bottomSeparator.setBounds(75, 475, 450, 1);
		rightSeparator.setBounds(525, 225, 1, 250);

		leftSeparator.setOrientation(SwingConstants.VERTICAL);
		rightSeparator.setOrientation(SwingConstants.VERTICAL);

		add(oracleLabel);

		add(hostLabel);
		add(SIDLabel);
		add(userLabel);
		add(passwordLabel);

		add(topSeparator);
		add(leftSeparator);
		add(bottomSeparator);
		add(rightSeparator);

		add(host);
		add(SID);
		add(user);
		add(password);

		add(connect);
	}

	public void setupButtons(MainFrame frame) {
		MainFrame.linkTextField(host, frame.getMenuPanel());
		MainFrame.linkTextField(SID, frame.getMenuPanel());
		MainFrame.linkTextField(user, frame.getMenuPanel());
		MainFrame.linkTextField(password, frame.getMenuPanel());

		MainFrame.linkButton(connect, frame.getMenuPanel());
	}

	// Returns true if connection was successful
	public boolean connect() {
		return Oracle.initialize(host.getText(), user.getText(), new String(password.getPassword()), SID.getText());
	}

	public void clear() {
		host.setText("");
		SID.setText("");
		user.setText("");
		password.setText("");

		host.grabFocus();
	}
}
