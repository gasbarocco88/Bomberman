package main.view.panels;

import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import main.model.actors.Actor;

import javax.swing.JCheckBox;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PlaaaaaaaaayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage backgroundImage;
	private JTextField textField;
	private JTable table_1;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public PlaaaaaaaaayerPanel() {

		try {
			backgroundImage = ImageIO.read(new File("/home/rocco/Immagini/giraffa.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		setBackground(Color.GREEN);
		
		
			
		ImageIcon icon = new ImageIcon();

		Border border = new LineBorder(Color.RED,1);
		
		ButtonGroup group = new ButtonGroup();
		
		ImageIcon i = new ImageIcon("/home/rocco/Immagini/goku.jpg");
		
		JToggleButton goku = new JToggleButton("Goku");
		goku.setBounds(244, 30, 154, 120);
		goku.setIcon(new ImageIcon("/home/rocco/Immagini/goku.jpg"));
		goku.setBorderPainted(isEnabled());
		goku.setActionCommand("Goku");
		goku.setBorder(border);
		goku.setMargin(new Insets(2, 8, 2, 8));
		
		

	

		JToggleButton vegeta = new JToggleButton("Vegeta");
		vegeta.setBounds(576, 30, 154, 120);
		vegeta.setActionCommand("Vegeta");
		vegeta.setBorder(border);

		JToggleButton piccolo = new JToggleButton("Piccolo");
		piccolo.setBounds(410, 30, 154, 120);
		piccolo.setActionCommand("Piccolo");
		piccolo.setBorder(border);

		group.add(goku);
		group.add(vegeta);
		group.add(piccolo);
		setLayout(null);

		add(goku);
		add(vegeta);
		add(piccolo);
		
		textField = new JTextField();
		textField.setBounds(42, 110, 162, 25);
		add(textField);
		textField.setColumns(10);
		
		table_1 = new JTable();
		table_1.setShowGrid(false);
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column", "New column", "New column", "New column"
			}
		));
		table_1.setRowSelectionAllowed(false);
		table_1.setBounds(276, 321, 475, 247);
		add(table_1);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(66, 58, 117, 40);
		add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("New button");
		btnNewButton_1_1.setBounds(66, 246, 117, 40);
		add(btnNewButton_1_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(42, 147, 162, 25);
		add(textField_1);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {"eeee", "eeeee"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(54, 428, 117, 59);
		add(list);

	
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, this);
		
		
		
		g.drawOval(660, 200, 10, 10);
		g.drawLine(1, 1, 100, 100);
		g.setColor(Color.BLUE);

	}

}
