package br.ufpe.cin.gui.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import br.ufpe.cin.gui.MainFrame;
import br.ufpe.cin.oracle.Oracle;

public class ListPanel extends JPanel {

	private static final long serialVersionUID = 2246027347745787206L;

	private JPanel container;

	public ListPanel(MainFrame frame) {
		super();

		// Panel config
		setBounds(0, 0, 600, 700);
		setBackground(MainFrame.BACKGROUND_COLOR);
		setLayout(null);

		JLabel oracleLabel = new JLabel("List of files stored in the DB");
		JLabel instructionLabel = new JLabel("Clique para copiar");

		oracleLabel.setBounds(150, 100, 300, 50);
		instructionLabel.setBounds(200, 150, 200, 20);

		oracleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		instructionLabel.setHorizontalAlignment(SwingConstants.CENTER);

		oracleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(100, 250, 400, 350);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		container = new JPanel();
		container.setLayout(null);
		container.setLocation(0, 0);
		container.setMinimumSize(new Dimension(400, 350));
		container.setBackground(MainFrame.BACKGROUND_COLOR);

		// Sets the container as the scroll pane controller
		scrollPane.setViewportView(container);

		JButton back = MainFrame.backButton(frame.getMenuPanel());

		add(oracleLabel);
		add(instructionLabel);

		add(scrollPane);

		add(back);
	}

	public void updateList() {
		Set<String> files = listFiles();

		// Removes every listed file
		container.removeAll();

		// Updates scrollPane size, based on the file amount
		container.setPreferredSize(new Dimension(400, files.size() * 20));

		JButton overlayButton;

		int y = 0;
		for (String name : files) {
			// Creates button with file name
			overlayButton = new JButton(name);
			overlayButton.setBounds(0, y, 382, 20);
			overlayButton.setBackground(Color.BLACK);
			overlayButton.setContentAreaFilled(false);

			// Copy to clipboard action
			overlayButton.addActionListener((event) -> {
				StringSelection selection = new StringSelection(name);
				Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(selection, selection);
			});

			// Increments button start Y position
			y += 20;

			container.add(overlayButton);
		}

		repaint();
		revalidate();
	}

	// Returns a set with all file names from the table
	private Set<String> listFiles() {
		Set<String> files = new HashSet<String>();

		try {
			Connection con = Oracle.getInstance().getConnection();

			Statement stmt = con.createStatement();

			ResultSet resultSet = stmt.executeQuery("SELECT name FROM files");
			while (resultSet.next()) {
				files.add(resultSet.getString("name"));
			}

			resultSet.close();

			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return files;
	}
}
