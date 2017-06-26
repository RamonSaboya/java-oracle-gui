package br.ufpe.cin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JOptionPane;

import br.ufpe.cin.gui.MainFrame;

public class UploadListener implements ActionListener {

	private MainFrame frame;

	private File file;

	public UploadListener(File file) {
		this.frame = MainFrame.getInstance();

		this.file = file;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// Upload click without file
		if (file == null) {
			JOptionPane.showMessageDialog(frame, "You must choose a file.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Clear text field
		frame.getUploadPanel().clear();

		JOptionPane.showMessageDialog(frame, "File uploaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setFile(File file) {
		this.file = file;
	}
}
