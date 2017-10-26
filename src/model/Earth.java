package model;


import java.util.Random;

public class Earth {
	/**
	 * 
	 */
	/**
	 * �û����λ��
	 */
	private int[] userClick = new int[2];
	/**
	 * ��ɫ��ʣ������
	 */
	private int red = 8;
	/**
	 * ��ɫ��ʣ������
	 */
	private int blue = 8;
	/**
	 * δ����������������
	 */
	private int sta = 16;
	/**
	 * �洢���ӵ�����
	 */
	private AnimalClass[][] map = new AnimalClass[4][4];
	/**
	 * ��ɫ������˳��洢
	 */
	private AnimalClass[] redQue = new AnimalClass[8];
	/**
	 * ��ɫ������˳��
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
	 * ��ʼ��Earth
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
	 * @param pλ��
	 * @return��������
	 */
	public AnimalClass getAPositionAnimal(int[] p) {
		if (map[p[0]][p[1]] != null)
			return map[p[0]][p[1]];
		else {
			return null;
		}
	}

	/**
	 * �޸�λ��
	 * 
	 * @param lpԭ��λ��
	 * @param np��λ��
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
	 * ͬʱɱ����������
	 * 
	 * @param lp����λ��
	 * @param np����λ��
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
	 * ��������������ͼ
	 */
	public void makeNewWord1(int gameModel) {
		String names = "��ʨ�����ǹ�è��";
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
