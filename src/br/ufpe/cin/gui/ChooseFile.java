package br.ufpe.cin.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;

import br.ufpe.cin.gui.panel.UploadPanel;

public class ChooseFile implements ActionListener {

	private JFileChooser fileChooser;
	private UploadPanel uploadPanel;

	public ChooseFile(JFileChooser fileChooser, UploadPanel uploadPanel) {
		this.fileChooser = fileChooser;
		this.uploadPanel = uploadPanel;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		int value = fileChooser.showOpenDialog(MainFrame.getInstance());

		// Returns selected file to the panel
		if (value == JFileChooser.APPROVE_OPTION) {
			File file = fileChooser.getSelectedFile();

			uploadPanel.setFile(file);
		}
	}

}
