package br.ufpe.cin.gui.panel;

import java.awt.Font;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import br.ufpe.cin.gui.ChooseFile;
import br.ufpe.cin.gui.MainFrame;
import br.ufpe.cin.gui.listeners.UploadListener;

public class UploadPanel extends JPanel {

	private static final long serialVersionUID = -7752630524019470459L;

	private JTextField fileName;

	private UploadListener listener;

	private File file;

	public UploadPanel(MainFrame frame) {
		super();

		// Panel config
		setBounds(0, 0, 600, 700);
		setBackground(MainFrame.BACKGROUND_COLOR);
		setLayout(null);

		JLabel oracleLabel = new JLabel("Choose a file to upload");
		JLabel uploadLabel = new JLabel("File name:");

		oracleLabel.setBounds(150, 100, 300, 50);
		uploadLabel.setBounds(100, 475, 100, 25);

		oracleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		oracleLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JFileChooser fileChooser = new JFileChooser();

		fileName = new JTextField();

		JButton choose = new JButton("Choose file");
		JButton upload = new JButton("Upload");

		fileName.setBounds(100, 500, 275, 20);
		fileName.setEditable(false);

		choose.setBounds(400, 500, 100, 20);
		upload.setBounds(400, 525, 100, 20);

		choose.addActionListener(new ChooseFile(fileChooser, this));

		JButton back = MainFrame.backButton(frame.getMenuPanel());

		listener = new UploadListener(file);

		upload.addActionListener(listener);

		add(oracleLabel);

		add(uploadLabel);

		add(fileName);
		add(choose);

		add(upload);

		add(back);
	}

	public void setFile(File file) {
		this.file = file;

		this.fileName.setText(file.getName());

		this.listener.setFile(file);
	}

	public void clear() {
		this.file = null;

		this.fileName.setText("");
	}
}
