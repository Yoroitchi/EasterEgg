package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import org.apache.log4j.Logger;

import object.EggStack;
import object.Element;
import object.Garden;
import object.Kid;
import object.Rock;
import system.GameManager;
import system.Launcher;

/**
 * Created by Selim on 25/04/2017.
 */
public class GameFrame extends JFrame {
	/**
	 * 
	 */

	private static final Logger LOGGER = Logger.getLogger(GameFrame.class);

	private GameManager gm;

	private Garden garden;
	private ArrayList<Kid> kids;

	private JPanel scorePanel;
	private JTable jTable;

	public GameFrame() {
		super();

		LOGGER.debug("Frame : création de la frame");

		this.setTitle("GLPOO Project - Easter Egg");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel title = new JLabel("Easter Egg");
		title.setHorizontalAlignment(JLabel.CENTER);
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);

		LOGGER.debug("Frame : Création du GameManager");
		gm = new GameManager(this);

		LOGGER.debug("Frame : Recuperation de garden & kids");
		garden = gm.getGarden();
		kids = gm.getKids();

		LOGGER.debug("Frame : Appel de gm.start()");
		gm.start();

		this.setVisible(true);
		this.pack();

		LOGGER.debug("Frame : appel de gm.gameEngine()");
		gm.gameEngine();

	}

	public void roundEngine() {
		gm.gameEngine();
	}

	public void updateJTable() {
		this.jTable = new JTable(this.garden.getSizeX(), this.garden.getSizeY());
		jTable.setDefaultRenderer(Object.class, new ImageCellRender());
		jTable.setRowHeight(80);
		jTable.setShowGrid(false);

		JPanel jTablePanel = new JPanel();
		jTablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

		Element[][] element = this.garden.getTable();

		LOGGER.debug("updateJTable : actualisation de la JTable");

		for (int i = 0; i < this.garden.getSizeX(); i++) {
			for (int y = 0; y < this.garden.getSizeY(); y++) {
				if (element[i][y] instanceof EggStack) {
					EggStack egg = (EggStack) element[i][y];
					this.jTable.setValueAt("egg_" + egg.getEggsNb(), i, y);
					continue;
				} else if (element[i][y] instanceof Rock) {
					this.jTable.setValueAt("rock", i, y);
					continue;
				} else if (element[i][y] instanceof Kid) {
					Kid kid = (Kid) element[i][y];
					this.jTable.setValueAt("kid_" + kid.sexe + "_" + kid.direction, i, y);
					continue;
				} else {
					this.jTable.setValueAt("grass", i, y);
				}
			}
		}
		jTablePanel.add(this.jTable);
		this.add(jTablePanel, BorderLayout.CENTER);
	}

	public void updateScorePanel() {
		scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
		scorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		scorePanel.setPreferredSize(new Dimension(150, 480));
		scorePanel.setAlignmentX(CENTER_ALIGNMENT);
		scorePanel.setAlignmentY(CENTER_ALIGNMENT);
		scorePanel.add(Box.createRigidArea(new Dimension(0, 5)));

		LOGGER.debug("updateScorePanel : actualisation du scorePannel");

		for (Kid kid : kids) {
			JLabel kidName = new JLabel(kid.getName());
			kidName.setFont(new Font("Bold", Font.BOLD, 20));
			kidName.setAlignmentX(CENTER_ALIGNMENT);
			scorePanel.add(kidName);

			JLabel egg = new JLabel("Egg : " + kid.eggBag);
			egg.setAlignmentX(CENTER_ALIGNMENT);
			scorePanel.add(egg);
			scorePanel.add(Box.createRigidArea(new Dimension(0, 5)));
		}

		this.add(scorePanel, BorderLayout.EAST);
	}

	public void updateJFrame() {

		LOGGER.debug("updateJFrame : actualisation de Frame");
		this.remove(jTable);
		updateJTable();

		this.remove(scorePanel);
		updateScorePanel();

		this.pack();
		LOGGER.debug("updateJFrame : fin de l'actualisation de la Frame");
	}

	public void endOfGame() {
		int winner = 0;
		int temp = 0;

		for (int i = 0; i < kids.size(); i++) {
			if (kids.get(i).eggBag > temp) {
				temp = kids.get(i).eggBag;
				winner = i;
			}
		}

		LOGGER.debug("endOfGame : annonce du gagnant : " + kids.get(winner).getName());
		this.remove(scorePanel);

		scorePanel.add(Box.createRigidArea(new Dimension(0, 80)));

		JPanel winnerPanel = new JPanel();
		winnerPanel.setLayout(new BoxLayout(winnerPanel, BoxLayout.PAGE_AXIS));
		winnerPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		winnerPanel.setPreferredSize(new Dimension(120, 60));

		winnerPanel.add(Box.createRigidArea(new Dimension(130, 3)));

		JLabel winnerLabel = new JLabel("Winner!");
		winnerLabel.setFont(new Font("Bold", Font.BOLD, 25));
		winnerLabel.setAlignmentX(CENTER_ALIGNMENT);
		winnerPanel.add(winnerLabel);
		winnerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		JLabel winnerName = new JLabel(kids.get(winner).getName());
		winnerName.setFont(new Font("Bold", Font.BOLD, 18));
		winnerName.setAlignmentX(CENTER_ALIGNMENT);
		winnerPanel.add(winnerName);

		JLabel winnerScore = new JLabel("Egg : " + kids.get(winner).eggBag);
		winnerScore.setAlignmentX(CENTER_ALIGNMENT);
		winnerPanel.add(winnerScore);
		winnerPanel.add(Box.createRigidArea(new Dimension(0, 5)));

		scorePanel.add(winnerPanel);
		scorePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		JButton resetButton = new JButton("Reset");
		resetButton.setAlignmentX(CENTER_ALIGNMENT);
		scorePanel.add(resetButton);

		this.add(scorePanel, BorderLayout.EAST);
		this.pack();
	}

}
