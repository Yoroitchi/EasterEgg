package display;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
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

/**
 * Created by Raphael on 27/04/2017.
 */
public class GameFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8288811893116150593L;

	private static final Logger LOGGER = Logger.getLogger(GameFrame.class);

	private GameManager gm;

	private Garden garden;
	private ArrayList<Kid> kids;

	private JPanel scorePanel;
	private JTable jTable;

	// Constructor of GameFrame
	public GameFrame() {
		super();

		LOGGER.debug("Frame : creation de la frame");

		this.setTitle("GLPOO Project - Easter Egg");
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Title Panel
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		JLabel title = new JLabel("Easter Egg");
		title.setHorizontalAlignment(JLabel.CENTER);
		titlePanel.add(title);
		this.add(titlePanel, BorderLayout.NORTH);

		LOGGER.debug("Frame : creation du GameManager");
		gm = new GameManager(this);

		LOGGER.debug("Frame : recuperation de garden & kids");
		garden = gm.getGarden();
		kids = gm.getKids();

		LOGGER.debug("Frame : appel de gm.start()");
		gm.start();

		this.setVisible(true);
		this.pack();

		LOGGER.debug("Frame : appel de gm.gameEngine()");
		gm.gameEngine();

	}

	// Create & refresh the jTable
	public void updateJTable() {
		this.jTable = new JTable(this.garden.getSizeX(), this.garden.getSizeY());
		jTable.setDefaultRenderer(Object.class, new ImageCellRender());
		jTable.setRowHeight(80);
		jTable.setShowGrid(false);

		JPanel jTablePanel = new JPanel();
		jTablePanel.setBorder(BorderFactory.createLineBorder(Color.black));

		Element[][] element = this.garden.getTable();

		LOGGER.debug("updateJTable : actualisation de la JTable");

		for (int x = 0; x < this.garden.getSizeX(); x++) {
			for (int y = 0; y < this.garden.getSizeY(); y++) {
				if (element[x][y] instanceof EggStack) {
					EggStack egg = (EggStack) element[x][y];
					this.jTable.setValueAt("egg_" + egg.getEggsNb(), x, y);
					continue;
				} else if (element[x][y] instanceof Rock) {
					this.jTable.setValueAt("rock", x, y);
					continue;
				} else if (element[x][y] instanceof Kid) {
					Kid kid = (Kid) element[x][y];
					this.jTable.setValueAt("kid_" + kid.sexe + "_" + kid.direction, x, y);
					continue;
				} else {
					this.jTable.setValueAt("grass", x, y);
				}
			}
		}
		jTablePanel.add(this.jTable);
		this.add(jTablePanel, BorderLayout.CENTER);
	}

	// Refresh the score panel
	public void updateScorePanel() {
		scorePanel = new JPanel();
		scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.PAGE_AXIS));
		scorePanel.setBorder(BorderFactory.createLineBorder(Color.black));
		scorePanel.setPreferredSize(new Dimension(150, 480));
		scorePanel.setAlignmentX(CENTER_ALIGNMENT);
		scorePanel.setAlignmentY(CENTER_ALIGNMENT);
		scorePanel.add(Box.createRigidArea(new Dimension(0, 10)));

		LOGGER.debug("updateScorePanel : actualisation du scorePannel");

		for (Kid kid : kids) {
			JLabel kidName = new JLabel(kid.getName());
			kidName.setFont(new Font("Bold", Font.BOLD, 19));
			kidName.setAlignmentX(CENTER_ALIGNMENT);
			scorePanel.add(kidName);

			JLabel egg = new JLabel("Egg : " + kid.eggBag);
			egg.setAlignmentX(CENTER_ALIGNMENT);
			scorePanel.add(egg);
			scorePanel.add(Box.createRigidArea(new Dimension(0, 5)));
		}

		this.add(scorePanel, BorderLayout.EAST);
	}

	// Refresh the jFrame by calling updateJTable() & updateScorePanel();
	public void updateJFrame() {

		LOGGER.debug("updateJFrame : actualisation de Frame");
		this.remove(jTable);
		updateJTable();

		this.remove(scorePanel);
		updateScorePanel();

		this.pack();
		LOGGER.debug("updateJFrame : fin de l'actualisation de la Frame");
	}

	// Define the winner and set the winnerPanel
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

		this.add(scorePanel, BorderLayout.EAST);
		this.pack();
	}

}
