package simonSays;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class Renderer extends JPanel {
@Override
protected void paintComponent(Graphics g) {
	
	super.paintComponent(g);
	if(Simon.simon!=null)
	Simon.simon.paint((Graphics2D)g);
}
}
