package model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import model.Ai.DiffcultAi;
import model.Ai.EasyAi;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class view {
	private EasyAi easyAi=new Ai().new EasyAi();
	private DiffcultAi dAi=new Ai().new DiffcultAi();
	private Color comphentColor = Color.pink;
	
	private static int modelGet;
	
	private Earth earth = Earth.getInstance();
	
	private Control control = new Control();
	
	private AnimalClass animal1 = null;
	private int clickMouse = 0;// �ڼ��ε�����
	/**
	 * �������ʱ��״̬
	 */
	private static int statue = 0;
	/**
	 * �Ƿ���ȷִ��
	 */
	private boolean right = false;

	static int[] lp = new int[2];
	static int[] np = new int[2];
	/**
	 * ����
	 */
	JButton[][] buttons = new JButton[4][4];
	private JFrame frame;

	/**
	 * �ı���Χ������ɫ
	 */
	public void changeColor(int[] p) {
		buttons[p[0]][p[1]].setBorder(BorderFactory.createLineBorder(Color.orange, 2));
		try {
			buttons[p[0]][p[1] - 1].setBorder(BorderFactory.createLineBorder(Color.green, 2));

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			buttons[p[0]][p[1] + 1].setBorder(BorderFactory.createLineBorder(Color.green, 2));

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			buttons[p[0] - 1][p[1]].setBorder(BorderFactory.createLineBorder(Color.green, 2));

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			buttons[p[0] + 1][p[1]].setBorder(BorderFactory.createLineBorder(Color.green, 2));

		} catch (ArrayIndexOutOfBoundsException e) {

		}

	}

	/**
	 * �Ļ���Χ������ɫ
	 */
	public void changeColorReturn(int[] p) {
		buttons[p[0]][p[1]].setBorder(BorderFactory.createLineBorder(Color.gray));
		try {
			buttons[p[0]][p[1] - 1].setBorder(BorderFactory.createLineBorder(Color.gray));

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			buttons[p[0]][p[1] + 1].setBorder(BorderFactory.createLineBorder(Color.gray));

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			buttons[p[0] - 1][p[1]].setBorder(BorderFactory.createLineBorder(Color.gray));

		} catch (ArrayIndexOutOfBoundsException e) {

		}
		try {
			buttons[p[0] + 1][p[1]].setBorder(BorderFactory.createLineBorder(Color.gray));

		} catch (ArrayIndexOutOfBoundsException e) {

		}

	}

	/**
	 * ����������Ӫ�޸İ�ť��ɫ
	 * 
	 * @param x
	 * @param y
	 */
	public void aColor(int x, int y) {
		if (earth.getMap()[x][y].isfOrE()) {
			buttons[x][y].setBackground(Color.red);
		} else {
			buttons[x][y].setBackground(Color.blue);
		}
	}

	/**
	 * Create the application.
	 */
	public view() {
		initialize();
	}

	/**
	 * 
	 * @param btnNewButton_2
	 *            �غϰ�ť
	 * @param modle
	 *            ��Ϸģʽ
	 * @param cx
	 *            ����=1��
	 */
	public void makeMap(JButton btnNewButton_2, int modle, int cx) {
		int px = 40;
		int py = 100;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i != 0 && j == 0) {
					px = 40;
					py += 70;
				}
				buttons[i][j] = new JButton("0");
				buttons[i][j].setBounds(px, py, 70, 70);
				if (cx == 0) {
					if (modle % 2 == 0)
						buttons[i][j].setBackground(Color.pink);
					else
						aColor(i, j);
				} else {
					if (modle % 2 == 0) {
						if (earth.getMap()[i][j].isStatues()) {
							aColor(i, j);
							buttons[i][j].setText(earth.getMap()[i][j].getName());
						} else {

							buttons[i][j].setBackground(Color.pink);
						}
					} else {
						if (earth.getMap()[i][j].isStatues()) {
							aColor(i, j);
							buttons[i][j].setText(earth.getMap()[i][j].getName());
						} else {
							aColor(i, j);
						}
					}

				}
				buttons[i][j].setForeground(Color.white);
				buttons[i][j].setFont(new Font(null, 1, 25));
				getFrame().getContentPane().add(buttons[i][j]);
				px = px + 70;
				int[] p = { i, j };
				buttons[i][j].addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent arg0) {
						changeColor(p);
					}

					@Override
					public void mouseExited(MouseEvent arg0) {
						changeColorReturn(p);
					}

					@Override
					public void mouseClicked(MouseEvent arg0) {
						runningOne(p, btnNewButton_2);
						dAi.setUserClick(p);
						if (modle < 2) {
							
							if (!Control.whosTurn) {
								dAi.action();
								if(!dAi.moveIs){
									runningOne(dAi.p1, btnNewButton_2);
								}else {
									runningOne(dAi.p[0], btnNewButton_2);
							
									runningOne(dAi.p[1], btnNewButton_2);
								}
							}
						}
					}

				});

			}

		}
	}
	
	/**
	 * ���һ�εĲ���
	 * 
	 * @param p
	 *            λ��
	 * @param btnNewButton_2
	 *            �غϰ�ť
	 * @return turn ��Чλ�� false ��Чλ��
	 */
	public void runningOne(int[] p, JButton btnNewButton_2) {
		bounceWinner();
		AnimalClass animal = earth.getMap()[p[0]][p[1]];
		if (animal == null)
			statue = 0;
		else
			statue = animal.checkAanimal(p);
		if (statue == 1) {
			clickMouse = 0;
			buttons[p[0]][p[1]].setText(animal.getName());
			if (earth.getMap()[p[0]][p[1]].isfOrE()) {
				buttons[p[0]][p[1]].setBackground(Color.red);
			} else {
				buttons[p[0]][p[1]].setBackground(Color.blue);
			}
			animal.setStatues(true);
			Control.whosTurn = !Control.whosTurn;
			changeTurn(btnNewButton_2);
			setRight(false);
		} else if (statue == 2) {
			animal1 = animal;
			lp = p;
			clickMouse = 1;
			setRight(true);
		} else if (statue == 3 || statue == 0) {
			if (clickMouse == 1) {
				np = p;
				int isMove = animal1.move(Control.getInput(lp, np));
				if (isMove == 0) {
					lp = null;
					np = null;
					clickMouse = 0;
					setRight(true);
				} else if (isMove == 1) {
					moveChess1(lp, np);
					Control.whosTurn = !Control.whosTurn;
					changeTurn(btnNewButton_2);
					clickMouse = 0;
					setRight(false);
				} else if (isMove == 2) {
					moveChess2(lp, np);
					Control.whosTurn = !Control.whosTurn;
					changeTurn(btnNewButton_2);
					clickMouse = 0;
					setRight(false);
				}

			}
		}
		bounceWinner();
	}

	/**
	 * ����ʤ������
	 */
	public void bounceWinner() {

		int winner = control.whoWin();
		if (view.getModelGet() < 2) {
			if (winner == 1) {
				JOptionPane.showMessageDialog(null, "ʤ��", "", 1, null);
			} else if (winner == 2) {
				JOptionPane.showMessageDialog(null, "ʧ��", "", 1, null);
			} else if (winner == 0) {
				JOptionPane.showMessageDialog(null, "ƽ��", "", 1, null);
			}
		} else {
			if (winner == 1) {
				JOptionPane.showMessageDialog(null, "�췽ʤ", "", 1, null);
			} else if (winner == 2) {
				JOptionPane.showMessageDialog(null, "����ʤ", "", 1, null);
			} else if (winner == 0) {
				JOptionPane.showMessageDialog(null, "ƽ��", "", 1, null);
			}
		}

	}

	/**
	 * �޸Ļغϰ�ť��ɫ
	 * 
	 * @param btnNewButton_2
	 *            �غϰ�ť
	 */
	public void changeTurn(JButton btnNewButton_2) {
		if (view.getModelGet() < 2) {
			if (Control.whosTurn) {
				btnNewButton_2.setBackground(Color.red);
				btnNewButton_2.setText("��һغ�");
			} else {
				btnNewButton_2.setBackground(Color.blue);
				btnNewButton_2.setText("���Իغ�");
			}
		} else {
			if (Control.whosTurn) {
				btnNewButton_2.setBackground(Color.red);
				btnNewButton_2.setText("�췽�غ�");
			} else {
				btnNewButton_2.setBackground(Color.blue);
				btnNewButton_2.setText("�����غ�");
			}
		}

	}

	public void moveChess1(int[] lp, int[] np) {
		buttons[np[0]][np[1]].setText(buttons[lp[0]][lp[1]].getText());
		buttons[np[0]][np[1]].setBackground(buttons[lp[0]][lp[1]].getBackground());
		buttons[lp[0]][lp[1]].setText("");
		buttons[lp[0]][lp[1]].setBackground(Color.white);

	}

	public void moveChess2(int[] lp, int[] np) {
		buttons[np[0]][np[1]].setText("");
		buttons[lp[0]][lp[1]].setText("");
		buttons[np[0]][np[1]].setBackground(Color.white);
		buttons[lp[0]][lp[1]].setBackground(Color.white);
		;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setFrame(new JFrame());
		getFrame().setTitle("������");
		frame = new JFrame();
		Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize(); // �����ʾ����С����
		frame.setSize((int) (displaySize.width / 2.2), (int) (displaySize.height / 1.3)); // ���ô��ڴ�С
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setLocation((int) ((displaySize.getWidth() - frame.getWidth()) / 2),
				(int) ((displaySize.getHeight() - frame.getHeight()) / 2));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JButton btnNewButton_2 = new JButton("�췽�غ�");
		btnNewButton_2.setBounds(40, 408, 280, 64);
		btnNewButton_2.setBackground(Color.red);
		getFrame().getContentPane().add(btnNewButton_2);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(40, 40, 554, 40);
		frame.getContentPane().add(menuBar);
		JMenu mnNewMenu = new JMenu("��Ϸ��ʼ");
		mnNewMenu.setOpaque(true);
		mnNewMenu.setBackground(comphentColor);
		JMenu singleMenu = new JMenu("������Ϸ");

		JMenu doubleMenu = new JMenu("˫����Ϸ");

		JMenuItem godmItem = new JMenuItem("�����������");
		godmItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setModelGet(0);
				Control.whosTurn = true;
				Earth.getInstance().cleanEarth();
				Control.doMain();
				removeIconChess();
				makeMap(btnNewButton_2, getModelGet(), 0);

			}
		});
		JMenuItem peoplemItem = new JMenuItem("˫�����ӷֿ�");
		peoplemItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setModelGet(1);
				Control.whosTurn = true;
				Earth.getInstance().cleanEarth();
				Control.doMain();
				removeIconChess();
				makeMap(btnNewButton_2, getModelGet(), 0);
			}
		});
		JMenuItem godmItem1 = new JMenuItem("�����������");
		godmItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setModelGet(2);
				Control.whosTurn = true;
				Earth.getInstance().cleanEarth();
				Control.doMain();
				removeIconChess();
				makeMap(btnNewButton_2, getModelGet(), 0);
			}
		});
		JMenuItem peoplemItem1 = new JMenuItem("˫�����ӷֿ�");
		peoplemItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setModelGet(3);
				Control.whosTurn = true;
				Earth.getInstance().cleanEarth();
				Control.doMain();
				removeIconChess();
				makeMap(btnNewButton_2, getModelGet(), 0);
			}
		});
		doubleMenu.add(godmItem1);
		doubleMenu.add(peoplemItem1);
		singleMenu.add(godmItem);
		singleMenu.add(peoplemItem);
		mnNewMenu.add(doubleMenu);
		mnNewMenu.add(singleMenu);
		menuBar.add(mnNewMenu);
		getFrame().getContentPane().setLayout(null);
		

		JButton button_15 = new JButton("����");
		button_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
			}
		});
		button_15.setBounds(357, 193, 93, 30);
	}

	/**
	 * һ�����Ӱ�ť
	 */
	public void removeIconChess() {
		for (JButton[] jButtons : buttons) {
			for (JButton jButton : jButtons) {
				try {
					frame.remove(jButton);
				} catch (Exception e) {
					return;
				}
			}
		}
	}

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public static int getStatue() {
		return statue;
	}

	public static void setStatue(int statue) {
		view.statue = statue;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public static int getModelGet() {
		return modelGet;
	}

	public static void setModelGet(int modelGet) {
		view.modelGet = modelGet;
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					view window = new view();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
