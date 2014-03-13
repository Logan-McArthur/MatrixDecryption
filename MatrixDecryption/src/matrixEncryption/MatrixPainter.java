package matrixEncryption;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class MatrixPainter extends BasicGame {

	public static void create(String title) {
		try {
			AppGameContainer app = new AppGameContainer(new MatrixPainter(title));
			app.setDisplayMode(800, 600, false);
			app.setShowFPS(false);
			app.setTargetFrameRate(30);
			app.start();
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	final int[][] problem3 = {
			{34, 54, 23, 54, 63, 57, 87, 36, 105, 63, 100, 9, 55, 117, 115, 0, 169, 67, 65, 124, 45, 83, 135, 25, 134, 63,
			79, 99, 100, 27, 107, 43, 50, 124, 39, 48, 160, 27, 90, 137, 0, 72, 122, 80, 114, 158, 104, 69, 10, 99, 70, 54},
			{13, 18, 9, 21, 23, 21, 32, 13, 42, 25, 40, 3, 19, 46, 45, 0, 63, 25, 22, 48, 15, 29, 50, 10, 51, 21,
			31, 38, 40, 9, 40, 16, 17, 46, 13, 19, 60, 9, 33, 52, 0, 27, 45, 32, 43, 59, 41, 26, 4, 37, 25, 18}};
	final int[][] problem3Inverse = {
			{2, -5},
			{-1, 3}
	};
	final int[][] problem2 = {
			{62, 32, 45, 0, 49, 33, 44, 21, 0, 47, 49, 26, 24, 15, 38, 28, 32, 45, 51, 26, 6, 49, 24, 36, 37, 3},
			{165, 84, 115, 0, 124, 90, 112, 55, 0, 122, 132, 65, 67, 45, 105, 70, 90, 120, 135, 65, 17, 127, 63, 93, 97, 9}};
	final int[][] problem2Inverse = {
			{-5, 2},
			{3,-1}};
	final int[][] problem1 = {
			{33, 14, 14, 51, 0, 48, 53, 55, 0, 58, 50, 45, 24, 7, 21, 4, 31, 60, 45, 0, 49, 30, 16, 9, 56, 37, 38, 49, 10, 56, 46, 15, 0},
			{51, 28, 19, 57, 10, 50, 53, 30, 0, 21, 49, 26, 5, 16, 43, 28, 17, 49, 20, 38, 60, 50, 37, 18, 29, 32, 49, 72, 5, 20, 47, 13, 0},
			{54, 28, 23, 73, 5, 66, 72, 60, 0, 59, 64, 49, 24, 15, 42, 17, 32, 74, 45, 19, 69, 51, 32, 18, 60, 46, 58, 74, 10, 57,	60, 19, 0}};
	final int[][] problem1Inverse = {
			{-3,-2,4},
			{-1,0,1},
			{2,1,-2}};
	public MatrixPainter(String title) {
		super(title);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(GameContainer container, Graphics grafix) throws SlickException {
		// TODO Auto-generated method stub
		int[][] mat = {{1,31},{400,5}};
		Input in = container.getInput();
		//grafix.drawString("(" +in.getMouseX() + ", " +in.getMouseY() + ")", 5, 5);
		drawMatrix(grafix, stringMatrix(mat), 10,10);
	}
	
	public void drawMatrix(Graphics grafix, String matrix, int x, int y) {
		
		grafix.drawString(matrix, 3+x, y);
		int height =grafix.getFont().getHeight(matrix);
		int width = grafix.getFont().getWidth(matrix);
		grafix.drawLine(x, y, x, y+height+3);
		grafix.drawLine(x, y, x+3, y);
		grafix.drawLine(x, y+height+3, x+3, y+height+3);
		grafix.drawLine(x+width+6, y, x+width+3, y);
		grafix.drawLine(x+width+6, y, x+width+6, y+height+3);
		grafix.drawLine(x+width+6, y+height+3, x+width+3, y+height+3);
		
	}
	
	public String stringMatrix(int[][] matrix) {
		StringBuilder[] sb = new StringBuilder[matrix.length];
		for (int sbIndex = 0; sbIndex < sb.length; sbIndex++) {
			sb[sbIndex] = new StringBuilder();
		}
		for (int j = 0; j < matrix[0].length; j++) {
			int maxLength = 0;
			for (int ind = 0; ind < matrix.length; ind++) {
				if (maxLength < String.valueOf(matrix[ind][j]).length())
					maxLength = String.valueOf(matrix[ind][j]).length();
			}
			
			for (int i = 0; i < matrix.length; i++) {
				String str = String.valueOf(matrix[i][j]);
				for (int tmp = 0; tmp < maxLength - str.length(); tmp++) {
					sb[i].append(" ");
				}
				sb[i].append(str);
				if (j < matrix[0].length-1) {
					sb[i].append(" ");
				} else if (i < matrix.length-1){
					sb[i].append("\n");
				}
			}
			
		}
		
		for(int index = 1; index < sb.length; index++) {
			sb[0].append(sb[index].toString());
		}
		return sb[0].toString();
	}

	public int[][] multiplyMatrix(int[][] left, int[][] right) {
		// row column
		
		int leftCol = left[0].length;
		int leftRow = left.length;
		int rightCol = right[0].length;
		int rightRow = right.length;
		
		if (leftCol != rightRow) {
			return null;
		} 
		int[][] result = new int[left.length][right[0].length];
		for (int row = 0; row < leftRow; row++) {
			for (int col = 0; col < rightCol; col++) {
				for (int i = 0; i < leftCol; i++) {
					result[row][col] += left[row][i] * right[i][col];
				}
			}
		}
		return result;
	}
	@Override
	public void init(GameContainer container) throws SlickException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		// TODO Auto-generated method stub
		
	}

}
