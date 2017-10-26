package model;

import java.util.Random;
import java.util.TreeMap;


public class Ai {

	private Earth earth = Earth.getInstance();
	private Random random = new Random();

	class EasyAi {
		/**
		 * true�ƶ�
		 */
		boolean moveIs;
		/**
		 * һ����
		 */
		int[] p1 = {17,17};
		/**
		 * ������
		 */
		int[][] p = { { 17, 17 }, { 17, 17 } };
		int x = 0, y = -1;

		/**
		 * ���һ��δ�������ĵ�
		 * 
		 * @return δ�������ĵ�
		 */
		protected void getPoint() {
			int x = random.nextInt(4);
			int y = random.nextInt(4);
			while (earth.getMap()[x][y] == null || earth.getMap()[x][y].isStatues()) {
				x = random.nextInt(4);
				y = random.nextInt(4);
			}
			p1[0] = x;
			p1[1] = y;
		}

		/**
		 * �õ������ƶ�������λ�ã�������ƶ�����λ�á�
		 */
		protected void move() {
			int loop = 5;
			while (loop == 5) {
				if (x == 3 && y == 3) {
					x = 0;
					y = 0;
				}
				if (loop == 5) {
					if (y < 3) {
						y++;
					} else {
						if (x < 3)
							x++;
						else
							x = 0;
						y = 0;
					}
				}
				while (earth.getMap()[x][y] == null || earth.getMap()[x][y].isfOrE()) {
					if (y < 3) {
						y++;
					} else {
						if (x < 3)
							x++;
						else
							x = 0;
						y = 0;
					}
				}
				p[0][0] = x;
				p[0][1] = y;
				int nx = x;
				int ny = y;
				int ran = random.nextInt(4) + 1;
				if (ran == 1) {
					nx = x - 1;
					ny = y;
				} else if (ran == 2) {
					nx = x + 1;
					ny = y;
				} else if (ran == 3) {
					nx = x;
					ny = y - 1;
				} else if (ran == 4) {
					nx = x;
					ny = y + 1;
				}

				loop = 1;
				while (loop < 5) {
					try {
						int z = 0;
						if (earth.getMap()[nx][ny] == null) {
							z = 1;
						} else if (!earth.getMap()[nx][ny].isfOrE()) {
							z = 0;
						} else if (earth.getMap()[nx][ny].getPower() <= earth.getMap()[x][y].getPower()) {
							z = 1;
						}
						if (z == 0) {
							throw new ArrayIndexOutOfBoundsException();
						} else {
							p[1][0] = nx;
							p[1][1] = ny;
							loop = 100;
						}
					} catch (ArrayIndexOutOfBoundsException e) {
						loop++;
						changeP(nx, ny, x, y, ran, loop);
					}
				}
			}

		}

		private int[] changeP(int nx, int ny, int x, int y, int ran, int loop) {
			if (ran == 1) {
				if (loop == 2) {
					nx = x + 1;
				} else if (loop == 3) {
					nx = x;
					ny = y - 1;
				} else if (loop == 4) {
					nx = x;
					ny = y + 1;
				}
			}
			if (ran == 4) {
				if (loop == 2) {
					nx = x + 1;
				} else if (loop == 3) {
					nx = x;
					ny = y - 1;
				} else if (loop == 4) {
					nx = x - 1;
					ny = y;
				}
			}
			if (ran == 2) {
				if (loop == 2) {
					nx = x - 1;
				} else if (loop == 3) {
					nx = x;
					ny = y - 1;
				} else if (loop == 4) {
					nx = x;
					ny = y + 1;
				}
			}
			if (ran == 3) {
				if (loop == 2) {
					nx = x + 1;
				} else if (loop == 3) {
					nx = x - 1;
					ny = y;
				} else if (loop == 4) {
					nx = x;
					ny = y + 1;
				}
			}
			int[] p = { nx, ny };
			return p;
		}

		public void action() {
			boolean flag = false;
			for (AnimalClass[] animalClass : earth.getMap()) {
				for (AnimalClass animalClass2 : animalClass) {
					if (animalClass2 != null) {
						if (!animalClass2.isStatues()) {
							flag = true;
							break;
						}
					}
				}
			}
			if (flag) {
				getPoint();
				moveIs = false;
			} else {
				move();
				moveIs = true;
			}
		}

		/**
		 * ���е����Ѿ��򿪣�
		 * 
		 * @return
		 */
		public boolean allAnimmalKnow() {
			for (AnimalClass[] animalClass : earth.getMap()) {
				for (AnimalClass animalClass2 : animalClass) {
					if (animalClass2 != null) {
						if (!animalClass2.isStatues()) {
							return false;
						}
					}
				}

			}
			return true;
		}

		protected int[] getRandomPoint(int[] z) {
			int[] p = new int[2];
			while (true) {
				int i = random.nextInt(3) + 1;
				if (i == 1) {
					p[0] = z[0] + 1;
				} else if (i == 2) {
					p[0] = z[0] - 1;
				} else if (i == 3) {
					p[1] = z[1] - 1;
				} else {
					p[1] = z[1] + 1;
				}
				if (!(p[0] > 3 || p[0] < 0 || p[1] > 3 || p[1] < 0)) {

					return p;
				}

			}

		}
		/**
		 * 
		 * @param p λ��
		 * @return 0 null 1lost 2enemy 3friend
		 */
		public int checkanimal(AnimalClass animal){
			if(animal==null){
				return 0;
			}else if(!animal.isStatues()){
				return 1;
			}else if (animal.isfOrE()) {
				return 2;
			}else {
				return 3;
			}
		}
	}

	class DiffcultAi extends EasyAi {
		private TreeMap<Integer, Integer[][]> inforDead = new TreeMap<Integer, Integer[][]>();
		private TreeMap<Integer, Integer[][]> inforEat = new TreeMap<Integer, Integer[][]>();
		/**
		 * �û�����ĵ�
		 */
		private int[] userClick;
		private AnimalClass[][] animals = earth.getMap();

		@Override
		public void action() {
			move();
			if(p[0][0]!=17){
				moveIs=true;
			}else {
				if(userClick!=null)
				getPoint();
				if(p1[0]!=17){
					moveIs=false;
				}else {
					super.getPoint();
					if(p1[0]!=17){
						moveIs=false;
					}else {
						super.move();
					}
				}
			}

		};

		@Override
		protected void getPoint() {
			AnimalClass animal = animals[userClick[0]][userClick[1]];
			int[] tmp = getRandomPoint(userClick);
			if (!animal.isfOrE()) {
				if (animal.getPower() > 4) {
					p1[0] = tmp[0];
					p1[1] = tmp[1];
				} else {
					super.getPoint();
				}
			} else {
				if (animal.getPower() <= 4) {
					p1[0] = tmp[0];
					p1[1] = tmp[1];
				} else {
					super.getPoint();
				}
			}
		}

		@Override
		protected void move() {
			int eat = -1;
			int dead = -2;
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					if (animals[i][j] != null) {
						if (!animals[i][j].isfOrE()) {
							singleMove(animals[i][j]);
							for (int a = 8; a > 0; a--) {
								if (inforEat.get(a) != null) {
									eat = a;
									break;
								}

							}
							for (int a = 8; a > 0; a--) {

								if (inforDead.get(a) != null) {
									dead = a;
									break;
								}
							}
							if (eat < 0 ) {
								if(dead<0){
									return;
								}else {
									super.move();
								}
							}else if(eat>0) {
								if (eat >= dead) {
									p[0][0] = inforEat.get(eat)[0][0];
									p[0][1] = inforEat.get(eat)[0][1];
									p[1][0] = inforEat.get(eat)[1][0];
									p[1][1] = inforEat.get(eat)[1][1];
								} else {
									if (inforEat.get(dead) != null) {
										p[0][0] = inforEat.get(dead)[0][0];
										p[0][1] = inforEat.get(dead)[0][1];
										p[1][0] = inforEat.get(dead)[1][0];
										p[1][1] = inforEat.get(dead)[1][1];

									} else {
										p[0][0] = inforEat.get(eat)[0][0];
										p[0][1] = inforEat.get(eat)[0][1];
										p[1][0] = inforEat.get(eat)[1][0];
										p[1][1] = inforEat.get(eat)[1][1];
									}
								}
							}
					
						}
					}
				}
			}

		};

		/**
		 * 
		 * @param p
		 *            �Լ�������
		 * @param ep
		 *            �Լ�������Χ������
		 * @param flag
		 *            ��ʶ�����ж��Ƿ������ʼֵΪ0
		 * @return true yes 
		 */
		private boolean canEat(int[] p, int[] ep, int flag) {
			AnimalClass animalep=earth.getMap()[ep[0]][ep[1]];
			AnimalClass animal=earth.getMap()[p[0]][p[1]];
			int z=checkanimal(animalep);
			boolean rFlag=false;
			if(z==0){
				rFlag=true;
			}else if(z==2){
				if(animalep.getPower()<animal.getPower()){
					int[] eep=new int[2];
					eep[0]=ep[0];
					eep[1]=ep[1];
					eep[0]=eep[0]-1;
					return canEat(p, eep, flag);
				}
			}
			
		}

		private void singleMove(AnimalClass animal) {
		
		}

		public int[] getUserClick() {
			return userClick;
		}

		public void setUserClick(int[] userClick) {
			this.userClick = userClick;
		}
	}

}
