package simonSays;

import javax.swing.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.sound.sampled.LineEvent.Type;

public class Simon implements ActionListener, MouseListener {

	public static Simon simon;
	public Renderer renderer;
	public static final int height = 800, width = 800;
	public int flashed = 0, ticks, indexPattern, dark, difficulty;
	public boolean creatingPattern = true, gameOver;
	public ArrayList<Integer> pattern;
	public Random random;
	JFrame frame = new JFrame("Daniel Says");
	JLabel debug=new JLabel();

	public Simon() {
		Timer timer = new Timer(20, this);
		renderer = new Renderer();
		frame.setSize(width + 10, height + 30);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		frame.add(renderer);
		frame.addMouseListener(this);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// label for debugging purposes
		debug.setForeground(Color.black);
		debug.setText("TEST");
		frame.add(debug,BorderLayout.SOUTH);
		//
		// set a circle for debug purposes
		
		
		//
		timer.start();
		start();
		frame.revalidate();
		frame.repaint();
	}

	public void start() {
		random = new Random();
		pattern = new ArrayList<Integer>();
		dark = 1;
		flashed = 0;
		ticks = 0;

	}
	public void debug(JLabel debug,MouseEvent e)
	{
		int x = e.getX(), y = e.getY();
		debug.setText("X = " + x + " Y = " + y);
	}

	public static void main(String[] args) {
		simon = new Simon();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ticks++;
		if (ticks % 20 == 0) {
			flashed = 0;

			if (dark >= 0) {
				dark--;
			}
		}

		if (creatingPattern) {
			if (dark <= 0) {
				if (indexPattern >= pattern.size()) {
					flashed = random.nextInt(40) % 4 + 1;
					pattern.add(flashed);
					indexPattern = 0;
					creatingPattern = false;
				} else {
					flashed = pattern.get(indexPattern);
					indexPattern++;
				}

				dark = 2;
			}
		} else if (indexPattern == pattern.size()) {
			creatingPattern = true;
			indexPattern = 0;
			dark = 2;
		}

		renderer.repaint();
	}

	public void paint(Graphics2D g) {

	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (flashed == 1) {
			g.setColor(Color.green.brighter());
		} else {
			g.setColor(Color.green.darker());
		}
		g.fillRect(0, 0, width / 2, height / 2);

		if (flashed == 2) {
			g.setColor(Color.red.brighter());
		} else {
			g.setColor(Color.red.darker());
		}
		g.fillRect(width / 2, 0, width / 2, height / 2);

		if (flashed == 3) {
			g.setColor(Color.orange.brighter());
		} else {
			g.setColor(Color.orange.darker());
		}
		g.fillRect(0, height / 2, width / 2, height / 2);

		if (flashed == 4) {
			g.setColor(Color.blue.brighter());
		} else {
			g.setColor(Color.blue.darker());
		}
		g.fillRect(width / 2, height / 2, width / 2, height / 2);

		g.setColor(Color.gray);
		g.setStroke(new BasicStroke(200));
		g.drawOval(-100, -100, width + 200, height + 200);


		g.setColor(Color.black);
		g.setStroke(new BasicStroke(10));
		g.drawOval(0, 0, width, height);
		g.fillRoundRect(220, 220, 350, 350, 300, 300);
		g.fillRect(width / 2 - width / 30, 0, width / 15, height); // horizontal black rectangle
		g.fillRect(0, width / 2 - width / 30, width, height / 15); // vertical black rectangle
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		int x = e.getX(), y = e.getY();
		debug(debug,e);
		
		
											//TRY AND DISABLE THE INNER CIRCLE
		if (!creatingPattern && !gameOver && !(x >= 220 && y >=420 && y<=420 && x<=400 && y<=600 && x >=400 && y>=250 && x<=570)) {

			if (x > 0 && x < width / 2 - 22 && y > 0 && y < height / 2) {
				flashed = 1;
				ticks = 1;
			} else if (x > width / 2 + 25 && x < width && y > 0 && y < height / 2) {
				flashed = 2;
				ticks = 1;
			} else if (x > 0 && x < width / 2 - 22 && y > height / 2 + 50 && y < height) {
				flashed = 3;
				ticks = 1;
			} else if (x > width / 2 + 25 && x < width && y > height / 2 + 50 && y < height) {
				flashed = 4;
				ticks = 1;
			}
		}
		if (flashed != 0) {
			if (pattern.get(indexPattern) == flashed) {
				indexPattern++;
			} else {
				gameOver = true;
//				JOptionPane j = new JOptionPane(JOptionPane.showConfirmDialog(frame, "Game Over! Restarting..",
//						"Game Over!", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE));
			}
		}

		if (gameOver) {

			start();
			gameOver = false;
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}