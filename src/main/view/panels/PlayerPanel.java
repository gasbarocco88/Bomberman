package main.view.panels;

import javax.swing.JPanel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;

import javax.imageio.ImageIO;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import main.model.actors.Actor;

import javax.swing.JCheckBox;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class PlayerPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtInserisciNome;

	/**
	 * Create the panel.
	 */
	public PlayerPanel() {
		setBackground(Color.GREEN);
		setLayout(null);
		
		JList<?> list = new JList();
		list.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 14));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(12, 115, 106, 201);
		AbstractListModel model = new AbstractListModel() {
			String[] values = new String[] {"ddddd", "aaaa", "cccc", "eee"};
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
		add(list);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(265, 115, 149, 35);
		add(btnNewButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"wdwdd", "cwwd", "evcwxw"}));
		comboBox.setBounds(199, 259, 149, 35);
		add(comboBox);
		
		txtInserisciNome = new JTextField();
		txtInserisciNome.setText("inserisci nome");
		txtInserisciNome.setBounds(63, 47, 264, 26);
		add(txtInserisciNome);
		txtInserisciNome.setColumns(10);
		
		JButton addBtn = new JButton();
		addBtn.setBounds(1,1,90,90);
		addBtn.setBorder(new RoundedBorder(10));
		add(addBtn);
		
		
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(425, 179, 1, 15);
		add(textArea);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("/home/rocco/Documenti/università/bombermanWindow/src/main/resources/images/pickaxe.png"));
		btnNewButton_1.setBounds(411, 201, 68, 51);
		add(btnNewButton_1);
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
		rdbtnNewRadioButton.setBounds(130, 316, 149, 23);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("New radio button");
		rdbtnNewRadioButton_1.setBounds(307, 316, 96, 23);
		add(rdbtnNewRadioButton_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(426, 62, 167, 25);
		add(tglbtnNewToggleButton);

	}
	
	
	
	private static class RoundedBorder implements Border {

	    private int radius;


	    private RoundedBorder(int radius) {
	        this.radius = radius;
	    }


	    public Insets getBorderInsets(Component c) {
	        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
	    }


	    public boolean isBorderOpaque() {
	        return true;
	    }


	    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
	        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
	        BufferedImage img = null;
			try {
				img = ImageIO.read(new File(
						"/home/rocco/Immagini/giraffa.jpg"));
				//g.drawImage(img,x, y, width, height, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	}
}
