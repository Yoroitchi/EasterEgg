package system;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import dao.EggStackDao;
import dao.GardenDao;
import dao.KidDao;
import dao.RockDao;
import display.GameFrame;
import object.EggStack;
import object.Element;
import object.Garden;
import object.Kid;
import object.Rock;

/**
 * Created by Selim on 22/04/2017.
 */
public class GameManager {

	private static final Logger LOGGER = Logger.getLogger(GameManager.class);

	// Resources garden path definition
	public final static String RESOURCES_PATH = "src/main/resources/";
	public final static String GARDEN_FILE_NAME = "garden.txt";
	private static File gardenFile = new File(RESOURCES_PATH + GARDEN_FILE_NAME);

	// Resources kids path definition
	private final static String KIDS_FILE_NAME = "kids.txt";
	private static File kidsFile = new File(RESOURCES_PATH + KIDS_FILE_NAME);

	private static final long delayTimeInSec = 1;

	private Garden garden = new GardenDao().findGarden(gardenFile);

	private ArrayList<EggStack> eggStacks = new EggStackDao().findAllEggs(gardenFile);
	private ArrayList<Kid> kids = new KidDao().findAllKids(kidsFile);
	private ArrayList<Rock> rocks = new RockDao().findAllRocks(gardenFile);

	GameFrame gf;

	public GameManager(GameFrame gf) {
		this.gf = gf;
	}
	
	//For test
	public GameManager(File gardenFile){
		this.gardenFile = gardenFile;
	}

	public void start() {

		LOGGER.debug("start : debut");

		LOGGER.debug("start : premier chargement du jardin");
		loadGarden();

		LOGGER.debug("start : creation du jTable et du scorePanel de la JFrame");
		gf.updateJTable();
		gf.updateScorePanel();
		LOGGER.debug("start : fin");

	}

	public void gameEngine() {
		LOGGER.debug("gameEngine : execution des tours");
		while (isKidWithSequence()) {

			delay();

			LOGGER.debug("gameEngine : deplacement de chaque enfants");
			for (Kid kid : kids) {

				LOGGER.debug("gameEngine : deplacement de " + kid.getName());
				moveKid(kid);
			}

			LOGGER.debug("gameEngine : nettoyage du Jardin");
			garden.setTable(new Element[garden.getSizeX()][garden.getSizeY()]);

			LOGGER.debug("gameEngine : rechargement du Jardin");
			loadGarden();
			LOGGER.debug("gameEngine : actualisation de Frame");
			gf.updateJFrame();

			LOGGER.debug("gameEngine : fin de un tour");
		}
		LOGGER.debug("gameEngine : fin des tours");

		endOfGame();
	}
	
	public void endOfGame() {
		delay();
		LOGGER.debug("endOfGame : appel de Frame.endOfGame()");
		gf.endOfGame();

	}


	private void loadGarden() {

		LOGGER.debug("loadGarden : début");

		LOGGER.debug("loadGarden : ajout des oeufs au jardin");
		for (EggStack eggStack : eggStacks) {

			LOGGER.debug("loadGarden : " + eggStack.eggsNb + " oeufs ont été placer dans le jardin");
			garden.addEgg(eggStack);
		}

		LOGGER.debug("loadGarden : ajout des enfants au jardin");
		for (Kid kid : kids) {

			LOGGER.debug("loadGarden : " + kid.getName() + " est entré dans le jardin");
			garden.addKid(kid);
		}

		LOGGER.debug("loadGarden : ajout des rochers au jardin");
		for (Rock rock : rocks) {

			LOGGER.debug("loadGarden : un rocher est présent dans le jardin");
			garden.addRock(rock);
		}

		LOGGER.debug("loadGarden : affichage");
		garden.printTable();

		LOGGER.debug("loadGarden : fin");
	}

	protected void moveKid(Kid kid) {

		LOGGER.debug("moveKid : debut");

		if (anEggIsGetBy(kid)) {
			System.out.println("An Egg was find by " + kid.getName());
		} else if (kid.sequence.startsWith("A")) {
			switch (kid.direction) {
			case 'E':
				if (!isThereAObstacleAt(kid.posX, kid.posY + 1))
					kid.posY += 1;
				break;
			case 'O':
				if (!isThereAObstacleAt(kid.posX, kid.posY - 1))
					kid.posY -= 1;
				break;
			case 'N':
				if (!isThereAObstacleAt(kid.posX - 1, kid.posY))
					kid.posX -= 1;
				break;
			case 'S':
				if (!isThereAObstacleAt(kid.posX + 1, kid.posY))
					kid.posX += 1;
				break;
			}
			kid.sequence = kid.sequence.substring(1);
		} else if (kid.sequence.startsWith("D")) {
			switch (kid.direction) {
			case 'N':
				kid.setDirection('E');
				break;
			case 'E':
				kid.setDirection('S');
				break;
			case 'S':
				kid.setDirection('O');
				break;
			case 'O':
				kid.setDirection('N');
				break;
			}
			kid.sequence = kid.sequence.substring(1);
		} else if (kid.sequence.startsWith("G")) {
			switch (kid.direction) {
			case 'N':
				kid.setDirection('O');
				break;
			case 'O':
				kid.setDirection('S');
				break;
			case 'S':
				kid.setDirection('E');
				break;
			case 'E':
				kid.setDirection('N');
				break;
			}
			kid.sequence = kid.sequence.substring(1);
		}

		LOGGER.debug("moveKid : fin");
	}

	private boolean isThereAObstacleAt(int posX, int posY) {
		try {
			return (garden.table[posX][posY] instanceof Rock || garden.table[posX][posY] instanceof Kid);
		} catch (ArrayIndexOutOfBoundsException e) {
			LOGGER.debug("isThereAObstacleAt : un enfant essaye de sortir du jardin");
			return true;
		}
	}

	private boolean anEggIsGetBy(Kid kid) {

		for (EggStack eggStack : eggStacks) {
			if (eggStack.posX == kid.posX && eggStack.posY == kid.posY && eggStack.getEggsNb() > 0) {
				kid.eggBag += 1;
				eggStack.eggsNb -= 1;
				LOGGER.debug("anEggIsGetBy : ajout d'un oeuf dans le panier de " + kid.getName());
				return true;
			}
		}
		return false;
	}

	private boolean isKidWithSequence() {

		for (Kid kid : kids) {
			if (kid.getSequence().equals(""))
				return false;
		}

		LOGGER.debug("isKidWithSequence : les enfants ont terminé leur scéquences");
		return true;
	}

	private void delay() {
		try {
			LOGGER.debug("delay : délais de " + delayTimeInSec + " secondes");
			TimeUnit.SECONDS.sleep(delayTimeInSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public Garden getGarden() {
		return garden;
	}

	public ArrayList<Kid> getKids() {
		return kids;
	}
}
