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

public class PlaaaaaaaaayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtInserisciNome;
	private BufferedImage backgroundImage;

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
		goku.setBounds(400, 0, 100, 100);
		goku.setIcon(new ImageIcon("/home/rocco/Immagini/goku.jpg"));
		goku.setBorderPainted(isEnabled());
		goku.setActionCommand("Goku");
		goku.setBorder(border);
		goku.setMargin(new Insets(2, 8, 2, 8));
		
		

	

		JToggleButton vegeta = new JToggleButton("Vegeta");
		vegeta.setBounds(0, 0, 100, 100);
		vegeta.setActionCommand("Vegeta");
		vegeta.setBorder(border);

		JToggleButton piccolo = new JToggleButton("Piccolo");
		piccolo.setBounds(200, 0, 100, 100);
		piccolo.setActionCommand("Piccolo");
		piccolo.setBorder(border);

		group.add(goku);
		group.add(vegeta);
		group.add(piccolo);
		setLayout(null);

		add(goku);
		add(vegeta);
		add(piccolo);

		txtInserisciNome = new JTextField();
		txtInserisciNome.setBounds(400, 400, 100, 100);
		add(txtInserisciNome);
		txtInserisciNome.setColumns(10);

		// txtInserisciNome.getText()));

		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setBounds(200, 300, 100, 100);
		btnNewButton_1.addActionListener(e -> System.out.println(group.getSelection().getActionCommand()));
		add(btnNewButton_1);

		
		
		
		JList<?> list = new JList();
		list.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(12, 115, 106, 201);
		AbstractListModel model = new AbstractListModel() {
			String[] values = new String[] { "ddddd", "aaaa", "cccc", "eee" };

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		};
		list.setModel(model);
		list.setBackground(new Color(98, 160, 234));
		list.setBorder(new LineBorder(new Color(0, 0, 0), 3, true));
		// add(list);

		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(265, 115, 149, 35);
		btnNewButton.isSelected();
		// add(btnNewButton);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "wdwdd", "cwwd", "evcwxw" }));
		comboBox.setBounds(199, 259, 149, 35);
		// add(comboBox);

		JButton addBtn = new JButton();
		addBtn.setBounds(1, 1, 90, 90);
		addBtn.setBorder(new RoundedBorder(10));
		// add(addBtn);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(425, 179, 1, 15);
		// add(textArea);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(130, 316, 149, 23);
		// add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(307, 316, 96, 23);
		// add(rdbtnNewRadioButton_1);

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// Draw the background image.
		g.drawImage(backgroundImage, 0, 0, this);
		
		
		
		g.drawOval(660, 200, 10, 10);
		g.drawLine(1, 1, 100, 100);
		g.setColor(Color.BLUE);

	}

	private static class RoundedBorder implements Border {

		private int radius;

		private RoundedBorder(int radius) {
			this.radius = radius;
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
		}

		public boolean isBorderOpaque() {
			return true;
		}

		public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
			g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
			BufferedImage img = null;
			try {
				img = ImageIO.read(new File("/home/rocco/Immagini/giraffa.jpg"));
				// g.drawImage(img,x, y, width, height, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
