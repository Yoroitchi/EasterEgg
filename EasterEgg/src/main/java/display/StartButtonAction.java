package display;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public class StartButtonAction extends AbstractAction {

	MainFrame frame;

	public StartButtonAction(String texte, MainFrame f) {
		super(texte);

		this.frame = f;
	}

	public void actionPerformed(ActionEvent e) {
		frame.startGame();
	}

}
