package model;




public class Control  {
	/**
	 * 
	 */
	static boolean whosTurn = true;// ˭�Ļغϣ���ɫ����
	static Earth earth = Earth.getInstance();

	public static void startAGame() {

		if (view.getModelGet() %2==0) {
			earth.makeNewWord1(1);

		} else {
			earth.makeNewWord1(2);
		}

		/*view window = new view();
		window.getFrame().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		window.getFrame().setVisible(true);*/
	}

	/**
	 * ˭Ӯ�ñ���
	 * 
	 * @return 2 ��ɫ 1��ɫ 0δ����ʤ����
	 */
	public int whoWin() {
		if (earth.getBlue() == 0 && earth.getRed() != 0)
			return 1;
		else if (earth.getRed() == 0 && earth.getBlue() != 0) {
			return 2;
		} else if (earth.getBlue() == 0 && earth.getRed() == 0) {
			return 0;
		} else {
			return 3;
		}
	}

	/**
	 * 
	 * @param lp��λ��
	 * @param np��λ��
	 * @return 0���� 1�� 2�� 3�� 4��
	 */
	public static int getInput(int[] lp, int np[]) {
		if (lp[0] == np[0]) {
			if (lp[1] - np[1] == 1)
				return 3;
			else if (lp[1] - np[1] == -1)
				return 4;
			else
				return 0;
		} else if (lp[1] == np[1]) {
			if (lp[0] - np[0] == 1)
				return 1;
			else if (lp[0] - np[0] == -1)
				return 2;
			else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * ��Ϸ����
	 */
	public static void doMain() {
		startAGame();
	}





}
