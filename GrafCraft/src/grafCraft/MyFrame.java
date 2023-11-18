package grafCraft;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;


public class MyFrame implements ActionListener{
	
	private JFrame frame;
	private JPanel main;
	private JPanel top;
	private JScrollPane scrollPane;
	public int spawnEquation = 75;	
	
	public MyFrame(Display sketch) {
		
		frame = new JFrame("GrafCraft");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	
		main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		top = new JPanel();
		
		scrollPane = new JScrollPane(main);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setPreferredSize(new Dimension(325,850));
		
		ImageIcon logo = new ImageIcon("/Users/aryanagarwal/eclipse-workspace/HelloWorld/HelloWorld/GrafCraftLogo.png");
		Image image = logo.getImage(); // transform it 
		Image newimg = image.getScaledInstance(90, 90,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		logo = new ImageIcon(newimg);  // transform it back
		
	
		JLabel title = new JLabel(logo);
		title.setText("GrafCraft");
		title.setFont(new Font("Sans Serif", Font.BOLD, 36));
		//title.setHorizontalTextPosition(JLabel.RIGHT);
		//title.setVerticalTextPosition(JLabel.CENTER);
		title.setBounds(20, 10, 300, 70);
		title.setText("GrafCraft");
		title.setFont(new Font("SANS_SERIF", Font.BOLD, 36));
		title.setBounds(20, 10, 270, 70);
		
		
		JButton addEquation;
		addEquation = new JButton();
		addEquation.setPreferredSize(new Dimension(250, 40));
		addEquation.setBounds(10, 20, 250, 40);
		addEquation.addActionListener(this);
		addEquation.setText("Add an Equation");
		
		top.setBackground(Color.white);
		top.setBounds(0, 0, 1300, 90);
		top.setBorder(BorderFactory.createLineBorder(Color.black));
		top.add(title);
		top.setLayout(null);

		
		main.setBackground(Color.white);
		main.setBounds(0, 90, 325, 805);
		main.setBorder(BorderFactory.createLineBorder(Color.black));
		//main.setLayout(new BoxLayout());
		main.add(addEquation);
		
		JPopupMenu hyperbolaChoice = new JPopupMenu();
		hyperbolaChoice.setBounds(50, 50, 250, 70);
		
		JPopupMenu functionsList = new JPopupMenu();
		functionsList.setSize(new Dimension(250, 70));
        functionsList.add(new JMenuItem(new AbstractAction("Line") {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
        		JTextField enterLine = new JTextField("y = (m)x + (b)");
        		enterLine.setBounds(10, spawnEquation, 150, 50);
                main.add(enterLine);
                frame.revalidate();
                frame.repaint();
                Linear newLine = new Linear(enterLine, sketch);
                JButton submitEquation = new JButton("Enter");
                main.add(submitEquation);
                submitEquation.setBounds(160, spawnEquation, 80, 50);
                submitEquation.setVisible(true);
                submitEquation.addActionListener(new ActionListener() {
                		
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		newLine.defineEquation();
                        sketch.lines.add(newLine);
                	}
                		
                });
                spawnEquation += 60;      
            }
        }));
        
		functionsList.setSize(new Dimension(250, 70));
        functionsList.add(new JMenuItem(new AbstractAction("Circle") {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
            	JTextField enterLine = new JTextField("(x - (h))^2 + (y - (k))^2 = (r)^2");
            	enterLine.setBounds(10, spawnEquation, 150, 50);
            	main.add(enterLine);
            	frame.revalidate();
                frame.repaint();
                Circle newCircle = new Circle(enterLine, sketch);
                JButton submitEquation = new JButton("Enter");
                main.add(submitEquation);
                submitEquation.setBounds(160, spawnEquation, 80, 50);
                submitEquation.setVisible(true);
                submitEquation.addActionListener(new ActionListener() {
                		
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		newCircle.defineEquation();
                        sketch.circles.add(newCircle);
                	}
                		
                });
                spawnEquation += 60;      
            }
        }));
        
        functionsList.add(new JMenuItem(new AbstractAction("Quadratic") {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
            	JTextField enterLine = new JTextField("y = (a)x^2 + (b)x + (c)");
            	enterLine.setBounds(10, spawnEquation, 150, 50);
            	main.add(enterLine);
            	frame.revalidate();
                frame.repaint();
                Quadratic newQuadratic = new Quadratic(enterLine, sketch);
                JButton submitEquation = new JButton("Enter");
                main.add(submitEquation);
                submitEquation.setBounds(160, spawnEquation, 80, 50);
                submitEquation.setVisible(true);
                submitEquation.addActionListener(new ActionListener() {
                		
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		newQuadratic.defineEquation();
                        sketch.quadratics.add(newQuadratic);
                	}
                		
                });
                spawnEquation += 60;      
            }
        }));
        
        functionsList.add(new JMenuItem(new AbstractAction("Ellipse") {
        	
        	@Override
            public void actionPerformed(ActionEvent e) {
            	JTextField enterLine = new JTextField("((x - (h))^2)/((a)^2) + ((y - (k))^2)/((b)^2) = 1");
            	enterLine.setBounds(10, spawnEquation, 150, 50);
            	main.add(enterLine);
            	frame.revalidate();
                frame.repaint();
                Ellipse newEllipse = new Ellipse(enterLine, sketch);
                JButton submitEquation = new JButton("Enter");
                main.add(submitEquation);
                submitEquation.setBounds(160, spawnEquation, 80, 50);
                submitEquation.setVisible(true);
                submitEquation.addActionListener(new ActionListener() {
                		
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		newEllipse.defineEquation();
                        sketch.ellipses.add(newEllipse);
                	}
                		
                });
                spawnEquation += 60;      
            }
        }));
        
        JMenu hyperbola = new JMenu("Hyperbola");
        functionsList.add(hyperbola);
        
        JMenuItem verticalHyperbola = new JMenuItem("Vertical Hyperbola");
        JMenuItem horizantalHyperbola = new JMenuItem("Horizantal Hyperbola");
        hyperbola.add(verticalHyperbola);
        hyperbola.add(horizantalHyperbola);
        
        verticalHyperbola.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JTextField enterLine = new JTextField("((y - (k))^2)/((a)^2) - ((x - (h))^2)/((b)^2) = 1");
            	enterLine.setBounds(10, spawnEquation, 150, 50);
            	main.add(enterLine);
            	frame.revalidate();
                frame.repaint();
                Hyperbola newHyperbola = new Hyperbola(enterLine, sketch);
                newHyperbola.vertical = true;
                JButton submitEquation = new JButton("Enter");
                main.add(submitEquation);
                submitEquation.setBounds(160, spawnEquation, 80, 50);
                submitEquation.setVisible(true);
                submitEquation.addActionListener(new ActionListener() {
                		
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		newHyperbola.defineEquationForVertical();
                        sketch.hyperbolas.add(newHyperbola);
                	}
                		
                });
                spawnEquation += 60; 
        	}
        	
        });
        
        horizantalHyperbola.addActionListener(new ActionListener() {
        	
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		JTextField enterLine = new JTextField("((x - (h))^2)/((a)^2) - ((y - (k))^2)/((b)^2) = 1");
            	enterLine.setBounds(10, spawnEquation, 150, 50);
            	main.add(enterLine);
            	frame.revalidate();
                frame.repaint();
                Hyperbola newHyperbola = new Hyperbola(enterLine, sketch);
                JButton submitEquation = new JButton("Enter");
                main.add(submitEquation);
                submitEquation.setBounds(160, spawnEquation, 80, 50);
                submitEquation.setVisible(true);
                submitEquation.addActionListener(new ActionListener() {
                		
                	@Override
                	public void actionPerformed(ActionEvent e) {
                		newHyperbola.defineEquationForHorizantal();
                        sketch.hyperbolas.add(newHyperbola);
                	}
                		
                });
                spawnEquation += 60; 
        	}
        	
        });

        addEquation.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                functionsList.show(e.getComponent(), 10, 35);
            	//sketch.x++;
            	//System.out.println(sketch.x);
            }
        });
        
        //equationsList.setViewportView(side);
        
		frame.add(top, BorderLayout.NORTH);
		frame.add(scrollPane, BorderLayout.CENTER);
		
		frame.setSize(300, 500);
		frame.setVisible(true);
		frame.setResizable(true);
		//frame.add(equationsList);

		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}
	
	public void show() {
		frame.setVisible(true);
	}
}
