package br.com.warhjr.ui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

public class MeuRenderer implements TableCellRenderer {

	public static final DefaultTableCellRenderer DEFAULT_RENDERER = new DefaultTableCellRenderer();

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Component renderer = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
				column);

		((JLabel) renderer).setOpaque(true);

		Color foreground, background;

		if (row % 2 == 0) {
			foreground = Color.BLACK;
			// background = new Color(230, 230, 250);
			background = new Color(250, 250, 210);

		} else {
			foreground = Color.blue;
			// background = new Color(255, 255, 255);
			background = new Color(230, 230, 250);

		}

		renderer.setForeground(foreground);
		// renderer.setBackground(background);
		return renderer;

	}

}
