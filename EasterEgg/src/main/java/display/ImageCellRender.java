package display;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ImageCellRender extends DefaultTableCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8130766682251931327L;

	private static final String PATH = "src/main/resources/images/";

	public ImageCellRender() {
		super();

	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

		String code = value.toString();
		setText("");

		setIcon(new ImageIcon(PATH + code + ".png"));

		return this;

	}
}
