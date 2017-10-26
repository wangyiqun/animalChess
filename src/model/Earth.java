package model;


import java.util.Random;

public class Earth {
	/**
	 * 
	 */
	/**
	 * 用户点击位置
	 */
	private int[] userClick = new int[2];
	/**
	 * 红色方剩余棋子
	 */
	private int red = 8;
	/**
	 * 蓝色方剩余棋子
	 */
	private int blue = 8;
	/**
	 * 未被翻开的棋子数量
	 */
	private int sta = 16;
	/**
	 * 存储棋子的数组
	 */
	private AnimalClass[][] map = new AnimalClass[4][4];
	/**
	 * 红色方棋子顺序存储
	 */
	private AnimalClass[] redQue = new AnimalClass[8];
	/**
	 * 蓝色方棋子顺序
	 */
	private AnimalClass[] blueQue = new AnimalClass[8];
	private static Earth earthO = new Earth();

	public void setEartho() {
		earthO = new Earth();
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public int getSta() {
		return sta;
	}

	public void setSta(int sta) {
		this.sta = sta;
	}

	public AnimalClass[][] getMap() {
		return map;
	}

	public void setMap(AnimalClass[][] earth) {
		this.map = earth;
	}

	public AnimalClass[] getRedQue() {
		return redQue;
	}

	public void setRedQue(AnimalClass[] redQue) {
		this.redQue = redQue;
	}

	public AnimalClass[] getBlueQue() {
		return blueQue;
	}

	public void setBlueQue(AnimalClass[] blueQue) {
		this.blueQue = blueQue;
	}

	public static Earth getInstance() {
		return earthO;
	}

	private Earth() {
	};

	/**
	 * 初始化Earth
	 */
	public void cleanEarth() {
		sta = 16;
		red = 8;
		blue = 8;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				map[i][j] = null;
			}
		}
		for (int i = 0; i < 8; i++) {

			redQue[i] = null;
			blueQue[i] = null;
		}
	}

	/**
	 * @param p位置
	 * @return动物棋子
	 */
	public AnimalClass getAPositionAnimal(int[] p) {
		if (map[p[0]][p[1]] != null)
			return map[p[0]][p[1]];
		else {
			return null;
		}
	}

	/**
	 * 修改位置
	 * 
	 * @param lp原来位置
	 * @param np新位置
	 */
	public void changePosition(int[] lp, int[] np) {
		try {
			if (map[np[0]][np[1]].isfOrE()) {
				redQue[8 - map[np[0]][np[1]].getPower()] = null;
			} else {
				blueQue[8 - map[np[0]][np[1]].getPower()] = null;
			}
		} catch (NullPointerException e) {
		} finally {
			map[np[0]][np[1]] = map[lp[0]][lp[1]];
			map[lp[0]][lp[1]] = null;

		}

	}

	/**
	 * 同时杀死两个棋子
	 * 
	 * @param lp棋子位置
	 * @param np棋子位置
	 */
	public void killTwo(int[] lp, int[] np) {
		if (map[np[0]][np[1]].isfOrE()) {
			redQue[8 - map[np[0]][np[1]].getPower()] = null;
			blueQue[8 - map[lp[0]][lp[1]].getPower()] = null;
		}
		map[lp[0]][lp[1]] = null;
		map[np[0]][np[1]] = null;

	}

	/**
	 * 将棋子随机填入地图
	 */
	public void makeNewWord1(int gameModel) {
		String names = "象狮虎豹狼狗猫鼠";
		char[] charNames = names.toCharArray();
		Random random = new Random();
		int i = 8;
		String name = String.valueOf(charNames[0]);
		while (true) {
			int[] p = new int[2];
			if (gameModel == 1) {
				p[0] = random.nextInt(4);
				p[1] = random.nextInt(4);
			} else if (gameModel == 2) {
				p[0] = random.nextInt(2);
				p[1] = random.nextInt(4);
			}
			if (map[p[0]][p[1]] == null) {
				map[p[0]][p[1]] = new AnimalClass(i, true, p, name);
				i--;
				redQue[7 - i] = map[p[0]][p[1]];
				if (i == 0)
					break;
				name = String.valueOf(charNames[8 - i]);
			}

		}
		i = 8;
		name = String.valueOf(charNames[0]);
		while (true) {
			int[] p = new int[2];
			if (gameModel == 1) {
				p[0] = random.nextInt(4);
				p[1] = random.nextInt(4);
			} else if (gameModel == 2) {
				p[0] = random.nextInt(2) + 2;
				p[1] = random.nextInt(4);
			}
			if (map[p[0]][p[1]] == null) {
				map[p[0]][p[1]] = new AnimalClass(i, false, p, name);
				i--;
				blueQue[7 - i] = map[p[0]][p[1]];
				if (i == 0)
					break;
				name = String.valueOf(charNames[8 - i]);
			}
		}
	}

	public int[] getUserClick() {
		return userClick;
	}

	public void setUserClick(int[] userClick) {
		this.userClick = userClick;
	}
	
	
}
