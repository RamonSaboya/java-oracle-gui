package br.ufpe.cin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import br.ufpe.cin.gui.MainFrame;
import br.ufpe.cin.oracle.Oracle;

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

		// Inserts file in the table
		Connection con = Oracle.getInstance().getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO files(name, data) VALUES(?, ?)");

			InputStream inputStream = new FileInputStream(file);

			stmt.setString(1, file.getName());
			stmt.setBlob(2, inputStream);

			stmt.executeUpdate();

			inputStream.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();

			// Should only happens if there is a file with same name stored in the table
			JOptionPane.showMessageDialog(frame, "Could not upload file.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Updates file list to include uploaded file
		frame.getListPanel().updateList();

		// Clear text field
		frame.getUploadPanel().clear();

		JOptionPane.showMessageDialog(frame, "File uploaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setFile(File file) {
		this.file = file;
	}
}
