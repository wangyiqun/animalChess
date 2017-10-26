package model;


public class AnimalClass  {

	/**
	 * 姓名
	 */
	private String name;
	/**
	 * 位置
	 */
	private int[] position;
	/**
	 * 牌面是否朝上：true朝上
	 */
	private boolean statues = false;
	/**
	 * 军队：true红军 false蓝军
	 */
	private boolean fOrE;
	/**
	 * 力量
	 */
	private int power;
	Earth earth=Earth.getInstance();

	public AnimalClass(int power, boolean f, int[] p, String name) {
		this.power = power;
		this.fOrE = f;
		position = p;
		this.name = name;

	}

	/**
	 * 
	 * @param animal
	 *            动物棋子
	 * @return int 0无法移动 1吃掉 2二者都死
	 */
	public int eat(AnimalClass animal) {
		if(animal==null){
			return 1;
		}
		if (animal.isfOrE() == this.isfOrE())
			return 0;
		if (getPower() == 1 && animal.getPower() == 8) {
			return 2;
		}
		if (getPower() == 8 && animal.getPower() == 1) {
			return 2;
		}
		if (this.getPower() == animal.getPower())
			return 2;
		if (this.getPower() > animal.getPower()) {
			return 1;
		} else {
			return 0;
		}
	}
	public AnimalClass() {
	}
	/**
	 * 检测鼠标点击处的棋子
	 * @param p 位置
	 * @return 1未翻开 2自己的回合且自己的牌 3敌方的牌
	 */
	public int checkAanimal(int[] p) {
		AnimalClass animal = Earth.getInstance().getMap()[p[0]][p[1]];
		if (!animal.statues) {
			int a=earth.getSta()-1;
			earth.setSta(a);
			return 1;
		} else if (animal.fOrE == Control.whosTurn) {
			return 2;
		} else {
			return 3;
		}

	}

	/**
	 * 棋子移动
	 * 
	 * @param west移动方向
	 * @return 0无法移动 1移动 2直接杀死
	 */
	public int move(int west) {
		int[] position = { 0, 0 };
		position[0] = getPosition()[0];
		position[1] = getPosition()[1];
		// System.out.println(getPosition()[0] + ".." + getPosition()[1]);
		switch (west) {
		case 1:// 上
			position[0] = position[0] - 1;
			break;
		case 2:// 下
			position[0] = position[0] + 1;
			break;
		case 3:// 左
			position[1] = position[1] - 1;
			break;
		case 4:// 右
			position[1] = position[1] + 1;
			break;
		default:
			break;
		}
		// System.out.println(position[0] + " " + position[1]);
		if (position[0] > -1 && position[0] < 4 && position[1] > -1 && position[1] < 4) {
			if (see(position) != null) {
				if (see(position).isStatues()) {
					if (eat(see(position)) == 1) {
						if (see(position).fOrE)
							earth.setRed(Earth.getInstance().getRed()-1);
						else
							earth.setBlue(Earth.getInstance().getBlue()-1);
						earth.changePosition(getPosition(), position);
						setPosition(position);
						return 1;
					} else if (eat(see(position)) == 2) {
						earth.setBlue(earth.getBlue()-1);
						earth.setRed(earth.getRed()-1);
						earth.killTwo(getPosition(), position);
						return 2;
					} else {
						return 0;
					}
				} else {
					return 0;
				}
			} else {
				
					// System.out.println(getPosition()[0] + "get" +
					// getPosition()[1]);
					earth.changePosition(getPosition(), position);
					setPosition(position);
				
				return 1;
			}
		} else {
			return 0;
		}

	}

	/**
	 * 获取位置处的动物
	 * 
	 * @param p位置
	 * @return 动物棋子
	 */
	public AnimalClass see(int[] p) {
		return earth.getAPositionAnimal(p);
	}

	public boolean isStatues() {
		return statues;
	}

	public void setStatues(boolean statues) {
		if(statues)
			earth.setSta(earth.getSta()-1);
		this.statues = statues;
	}

	public boolean isfOrE() {
		return fOrE;
	}

	public void setfOrE(boolean fOrE) {
		this.fOrE = fOrE;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
