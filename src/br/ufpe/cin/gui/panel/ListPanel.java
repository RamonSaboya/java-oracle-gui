package br.ufpe.cin.gui.panel;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

import br.ufpe.cin.gui.MainFrame;

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
}
