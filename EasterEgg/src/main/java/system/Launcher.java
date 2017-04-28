package system;

import org.apache.log4j.BasicConfigurator;

import display.GameFrame;

/**
 * Created by Selim on 23/04/2017.
 */
public class Launcher {

	public static void main(String[] args) {

		BasicConfigurator.configure();

		new GameFrame();
	}
}
