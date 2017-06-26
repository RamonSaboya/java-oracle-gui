package br.ufpe.cin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.ufpe.cin.gui.MainFrame;

public class DownloadListener implements ActionListener {

	private MainFrame frame;

	private JTextField textField;

	public DownloadListener(JTextField textField) {
		this.frame = MainFrame.getInstance();

		this.textField = textField;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String name = textField.getText().trim();

		// Empty file name
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(frame, "File cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Clear file name field
		textField.setText("");

		JOptionPane.showMessageDialog(frame, "File downloaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}
}
