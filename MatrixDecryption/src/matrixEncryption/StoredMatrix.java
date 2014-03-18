package matrixEncryption;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

public class StoredMatrix {
	public int[][] matrix;
	public String textMatrix;
	public Image imageMatrix;
	public int width;
	public int height;
	public int x;
	public int y;
	public StoredMatrix(int[][] matrix, Graphics grafix) {
		this.matrix = matrix;
		textMatrix = MatrixPainter.stringMatrix(matrix);
		imageMatrix = MatrixPainter.drawMatrix(grafix, textMatrix);
		width = imageMatrix.getWidth();
		height = imageMatrix.getHeight();
	}
}
