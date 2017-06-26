package br.ufpe.cin.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import br.ufpe.cin.gui.MainFrame;
import br.ufpe.cin.oracle.Oracle;

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
			JOptionPane.showMessageDialog(frame, "File can not be empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Not stored file
		if (!fileExists(name)) {
			JOptionPane.showMessageDialog(frame, "File: " + name + " is not stored in the database.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Downloads the file from the table
		Connection con = Oracle.getInstance().getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT data FROM files WHERE name=?");

			stmt.setString(1, name);

			ResultSet resultSet = stmt.executeQuery();

			resultSet.next();

			// Binary stream from the table
			InputStream input = resultSet.getBlob("data").getBinaryStream();

			// New file stream
			OutputStream output = new FileOutputStream(new File(name));

			// Save binary data from the table to the file
			int read = -1;
			byte[] buffer = new byte[MainFrame.BUFFER_SIZE];
			while ((read = input.read(buffer)) != -1) {
				output.write(buffer, 0, read);
			}

			output.close();
			input.close();

			resultSet.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Clear file name field
		textField.setText("");

		JOptionPane.showMessageDialog(frame, "File downloaded.", "Success", JOptionPane.INFORMATION_MESSAGE);
	}

	public void setTextField(JTextField textField) {
		this.textField = textField;
	}

	// Checks if a file is stored in the table
	public boolean fileExists(String name) {
		boolean exists = false;

		Connection con = Oracle.getInstance().getConnection();

		try {
			PreparedStatement stmt = con.prepareStatement("SELECT name FROM files WHERE name=?");

			stmt.setString(1, name);

			ResultSet resultSet = stmt.executeQuery();

			if (resultSet.next()) {
				exists = true;
			}

			resultSet.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return exists;
	}
}
