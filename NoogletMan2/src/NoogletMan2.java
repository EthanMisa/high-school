package MainFiles;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NoogletMan2 {
	JFrame frame;
	TitlePanel titlePanel;
	PlayPanel playPanel;
	DiffPanel diffPanel;
	
	boolean easy = false;
	boolean normal = true;
	boolean hard = false;
	
	Easy easyGame = new Easy();
	Normal normalGame = new Normal();
	Hard hardGame = new Hard();
	
	ImageIcon background = new ImageIcon(getClass().getResource("/matrixBackground.jpg"));
	
	public static void main(String[] args) {
		new NoogletMan2();
	}

	NoogletMan2() {

		// game frame
		frame = new JFrame("Nooglet Man 2");
		frame.setSize(700, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// title frame
		titlePanel = new TitlePanel();

		// play panel
		playPanel = new PlayPanel();
		
		

		// customize panel
		diffPanel = new DiffPanel();

		frame.setContentPane(titlePanel);
		frame.setVisible(true);
			
	}
	
	
	// title frame
	class TitlePanel extends JPanel implements ActionListener {

		JPanel panel;
		JLabel titleLabel;
		JButton playBut, custBut, diffBut;
		
		
		TitlePanel() {

			panel = new JPanel();

			titleLabel = new JLabel("Nooglet Man 2");
			titleLabel.setFont(new Font("Monospaced", Font.BOLD, 70));
			titleLabel.setBackground(Color.BLACK);
			titleLabel.setAlignmentX(CENTER_ALIGNMENT);

			playBut = new JButton("Play");
			
			playBut.addActionListener(this);
			playBut.setAlignmentX(CENTER_ALIGNMENT);
			playBut.setMaximumSize(new Dimension(100, 40));

			custBut = new JButton("Difficulty");
			custBut.addActionListener(this);
			custBut.setAlignmentX(CENTER_ALIGNMENT);
			custBut.setMaximumSize(new Dimension(100, 40));

			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

			add(Box.createRigidArea(new Dimension(0, 40)));
			add(titleLabel);
			add(Box.createRigidArea(new Dimension(0, 40)));
			add(playBut);
			add(custBut);
			setVisible(true);

		}
		

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == playBut){
				frame.setContentPane(playPanel);
				frame.revalidate();
			}
			if (e.getSource() == custBut) {
				frame.setContentPane(diffPanel);
				frame.revalidate();
			}
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background.getImage(), 0, 0, this);
		}
	}
	

	class PlayPanel extends JPanel implements ActionListener {

		JButton a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z;
		JButton[] buttonLists = {a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, x, y, z};
		String[] buttonList = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", 
								"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
		
		JPanel buttonPanel, promptPanel;
		JLabel prompt;
		JTextField answer;
		
		
		PlayPanel() {
						
			promptPanel = new JPanel();
			promptPanel.setLayout(new BoxLayout(promptPanel, BoxLayout.Y_AXIS));
			prompt = new JLabel();
			prompt.setFont(new Font("Monospaced", Font.BOLD, 20));
			promptPanel.setBackground(new Color(17, 133, 48, 200));
			promptPanel.setOpaque(true);
			
			answer = new JTextField(30);
			answer.setFont(new Font("SansSerif", Font.PLAIN, 30));
			answer.setMaximumSize(answer.getPreferredSize());
			answer.setEditable(false);
			
			
			if(easy) {
				System.out.println("easy!");
				prompt.setText(easyGame.getQuestion());
				answer.setText(easyGame.hideAnswer());
			}
			else if(normal) {
				System.out.println("normal!");
				prompt.setText(normalGame.getQuestion());
				answer.setText(normalGame.hideAnswer());
			}
			else if(hard) {
				System.out.println("hard!");
				prompt.setText(hardGame.getQuestion());
				answer.setText(hardGame.hideAnswer());
			}			
			
			promptPanel.add(prompt);
			promptPanel.add(answer);
			
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridLayout(7,4));
			for (int i = 0; i<buttonList.length; i++) {
				buttonLists[i] = createBut(buttonList[i]);
			}
			
			buttonPanel.setOpaque(false);
					
			setLayout(new BorderLayout());
			add(buttonPanel, BorderLayout.WEST);
			add(promptPanel, BorderLayout.NORTH);
					
		}
		
		
		
		public JButton createBut(String name) {
			JButton button = new JButton(name.toUpperCase());
			button.setOpaque(false);
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					button.setVisible(false);
					revalidate();
					repaint();
					
					if(easy) {
						if(easyGame.includesChar(button.getText())) {
							easyGame.setGuess(answer.getText());
							easyGame.updateAnswer(button.getText());
							answer.setText(easyGame.getGuess());						
							if(easyGame.checkGuess()) {
								JOptionPane.showConfirmDialog(null, "<html>You have successfully completed this level!<br/>Congrats, good luck on the next question!</html>", "Success", JOptionPane.DEFAULT_OPTION);
								nextQuestion();		
							}	
						}
						else {
							easyGame.setGuess(answer.getText());
							easyGame.updateHangMan();
							if(easyGame.getLives() <= 0) {
								JOptionPane.showConfirmDialog(null, "<html>You have failed this level :(<br/>Hopefully you do better on the next round<br/>Good luck!</html>", "Level Over", JOptionPane.DEFAULT_OPTION);							
								nextQuestion();
							}
						}
					}
					
					if(normal) {
						if(normalGame.includesChar(button.getText())) {
							normalGame.setGuess(answer.getText());
							normalGame.updateAnswer(button.getText());
							answer.setText(normalGame.getGuess());						
							if(normalGame.checkGuess()) {
								JOptionPane.showConfirmDialog(null, "<html>You have successfully completed this level!<br/>Congrats, good luck on the next question!</html>", "Success", JOptionPane.DEFAULT_OPTION);nextQuestion();		
							}	
						}
						else {
							normalGame.setGuess(answer.getText());
							normalGame.updateHangMan();
							if(normalGame.getLives() <= 0) {
								JOptionPane.showConfirmDialog(null, "<html>You have failed this level :(<br/>Hopefully you do better on the next round<br/>Good luck!</html>", "Level Over", JOptionPane.DEFAULT_OPTION);							
								nextQuestion();
							}
						}
					}

					if(hard) {
						if(hardGame.includesChar(button.getText())) {
							hardGame.setGuess(answer.getText());
							hardGame.updateAnswer(button.getText());
							answer.setText(hardGame.getGuess());						
							if(hardGame.checkGuess()) {
								JOptionPane.showConfirmDialog(null, "<html>You have successfully completed this level!<br/>Congrats, good luck on the next question!</html>", "Success", JOptionPane.DEFAULT_OPTION);nextQuestion();		
							}	
						}
						else {
							hardGame.setGuess(answer.getText());
							hardGame.updateHangMan();
							if(hardGame.getLives() <= 0) {
								JOptionPane.showConfirmDialog(null, "<html>You have failed this level :(<br/>Hopefully you do better on the next round<br/>Good luck!</html>", "Level Over", JOptionPane.DEFAULT_OPTION);							
								nextQuestion();
							}
						}
					}
					
					
					
				}
				
			});
			button.setMaximumSize(new Dimension(40, 40));
			buttonPanel.add(button);
			return button;
		}
		
		public void nextQuestion() {
			
			if(easy) {
				easyGame.nextQA();
			}
			if(normal) {
				normalGame.nextQA();
			}
			if(hard) {
				hardGame.nextQA();
			}
			playPanel = new PlayPanel();
			frame.setContentPane(playPanel);
			frame.revalidate();
			
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background.getImage(), 0, 0, this);
		}
		public void paint(Graphics g) {
			// Repaints the frame and its components
			super.paint(g);
			// Declare and initialize a Graphics2D object
			
			Graphics2D g2 = (Graphics2D) g;
			//g2.drawImage(background.getImage(), 0, 0, this);
			g2.setStroke(new BasicStroke(10));
			g2.draw(new Line2D.Double(450, 450, 650, 450));
			g2.draw(new Line2D.Double(600, 450, 600, 150));
			g2.draw(new Line2D.Double(600, 150, 400, 150));
			g2.draw(new Line2D.Double(400, 150, 400, 200));
			
			if(easy) {
				if(easyGame.getHead()) {
					g2.draw(new Ellipse2D.Double(360, 200, 80, 80));
				}
				if(easyGame.getBody()) {
					g2.draw(new Line2D.Double(400, 280, 400, 350));
				}
				if(easyGame.getArms()) {
					g2.draw(new Line2D.Double(360, 300, 440, 300));
				}
				if(easyGame.getLegs()) {
					g2.draw(new Line2D.Double(360, 420, 400, 350));
					g2.draw(new Line2D.Double(440, 420, 400, 350));
				}
			}
			
			if(normal) {
				if(normalGame.getHead()) {
					g2.draw(new Ellipse2D.Double(360, 200, 80, 80));
				}
				if(normalGame.getBody()) {
					g2.draw(new Line2D.Double(400, 280, 400, 350));
				}
				if(normalGame.getArms()) {
					g2.draw(new Line2D.Double(360, 300, 440, 300));
				}
				if(normalGame.getLegs()) {
					g2.draw(new Line2D.Double(360, 420, 400, 350));
					g2.draw(new Line2D.Double(440, 420, 400, 350));
				}
			}
			
			if(hard) {
				if(hardGame.getHead()) {
					g2.draw(new Ellipse2D.Double(360, 200, 80, 80));
				}
				if(hardGame.getBody()) {
					g2.draw(new Line2D.Double(400, 280, 400, 350));
				}
				if(hardGame.getArms()) {
					g2.draw(new Line2D.Double(360, 300, 440, 300));
				}
				if(hardGame.getLegs()) {
					g2.draw(new Line2D.Double(360, 420, 400, 350));
					g2.draw(new Line2D.Double(440, 420, 400, 350));
				}
			}
		}



		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
		}

		
	}

	class DiffPanel extends JPanel implements ActionListener {
		JButton easyBut, normalBut, hardBut, backBut; 
		JLabel titleLabel;
		JPanel butPanel, backPanel;

		DiffPanel() {

			

			titleLabel = new JLabel("Customize");
			titleLabel.setFont(new Font("Monospaced", Font.PLAIN, 40));
			titleLabel.setAlignmentX(CENTER_ALIGNMENT);

			butPanel = new JPanel();
			butPanel.setLayout(new BoxLayout(butPanel, BoxLayout.X_AXIS));

			easyBut = new JButton("Easy (4 Lives)");
			easyBut.setMaximumSize(new Dimension(150, 70));
			easyBut.addActionListener(this);

			normalBut = new JButton("Normal (3 Lives)");
			normalBut.setMaximumSize(new Dimension(150, 70));
			normalBut.addActionListener(this);

			hardBut = new JButton("Hard (2 Lives)");
			hardBut.setMaximumSize(new Dimension(150, 70));
			hardBut.addActionListener(this);
			
			butPanel.add(easyBut);
			butPanel.add(normalBut);
			butPanel.add(hardBut);
			
			backPanel = new JPanel();
			backPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
			backBut = new JButton("Back");
			backBut.addActionListener(this);
			backPanel.add(backBut);
			
			setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			backPanel.setOpaque(false);
			add(backPanel);
			//add(Box.createRigidArea(new Dimension(0, 30)));
			add(titleLabel);
			add(Box.createRigidArea(new Dimension(0, 50)));
			butPanel.setOpaque(false);
			add(butPanel);
			add(Box.createVerticalGlue());
			
		}
		
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(background.getImage(), 0, 0, this);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == backBut) {
				frame.setContentPane(titlePanel);
				frame.revalidate();
			}
			if(e.getSource() == easyBut) {
			
				easy = true;
				normal = false;
				hard = false;
			}
			if(e.getSource() == normalBut) {
				easy = false;
				normal = true;
				hard = false;
			}
			if(e.getSource() == hardBut) {
				easy = false;
				normal = false;
				hard = true;
			}
			if(easy) {
				System.out.println("Easy");
			}
			if(normal) {
				System.out.println("Normal");
			}
			if(hard) {
				System.out.println("Hard");
			}
		}

	}
}
