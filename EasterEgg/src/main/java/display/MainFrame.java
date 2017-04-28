package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import system.GameManager;

public class MainFrame {

	private static final Logger LOGGER = Logger.getLogger(MainFrame.class);

	JFrame mainFrame;
	JButton startButton;

	public MainFrame() {
		LOGGER.debug("MainFrame : Création de fenetre d'acceuil");

		mainFrame = new JFrame();

		mainFrame.setTitle("GLPOO Project - Easter Egg");
		mainFrame.setPreferredSize(new Dimension(300, 150));
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		mainPanel.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 8)));

		JLabel title = new JLabel("Welcome in Easter Egg");
		title.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		title.setFont(new Font("Bold", Font.BOLD, 20));
		mainPanel.add(title);
		mainPanel.add(Box.createRigidArea(new Dimension(0, 6)));

		startButton = new JButton(new StartButtonAction("Start", this));
		startButton.setPreferredSize(new Dimension(80, 40));
		startButton.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		mainPanel.add(startButton);

		mainFrame.getContentPane().add(mainPanel);
		mainFrame.pack();
		mainFrame.setVisible(true);

		// startGame();
	}

	public void startGame() {
		mainFrame.dispose();
		LOGGER.debug("startGame : Lancement du jeu");
		new Frame();

		// new Temp();

	}
}
