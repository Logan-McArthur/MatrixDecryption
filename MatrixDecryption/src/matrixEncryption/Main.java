package matrixEncryption;

public class Main {
	static char[] characters = {' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	static final int[][] problem3As2by = {
		{34, 54, 23, 54, 63, 57, 87, 36, 105, 63, 100, 9, 55, 117, 115, 0, 169, 67, 65, 124, 45, 83, 135, 25, 134, 63,
		79, 99, 100, 27, 107, 43, 50, 124, 39, 48, 160, 27, 90, 137, 0, 72, 122, 80, 114, 158, 104, 69, 10, 99, 70, 54},
		{13, 18, 9, 21, 23, 21, 32, 13, 42, 25, 40, 3, 19, 46, 45, 0, 63, 25, 22, 48, 15, 29, 50, 10, 51, 21,
		31, 38, 40, 9, 40, 16, 17, 46, 13, 19, 60, 9, 33, 52, 0, 27, 45, 32, 43, 59, 41, 26, 4, 37, 25, 18}};

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		//MatrixPainter.create("Decryption");
		//int[][] problem3Inverse = {	{2,-5},	{-3,8}		}; 			// These are wrong
		//int[][] problem3Inverse = {	{2,-5},	{-1,2}};				// These are wrong
		//int[][] result3 = multiplyMatrix(problem3Inverse, problem3As2by);
		//printCharMatrix(result3);
		bruteForceNumber3();
		//bruteForceNumber3(2);
		doNumber2();
		doNumber1();
		//bruteForceNumber2();
		// Length is 104
		// I will guess a 4x26
		// But a 2x52 is possible
	}
	// Need to incorporate c and d, or else it will not work fast enough
	public static void bruteForceNumber3() throws Exception {
		int lim = 20;
		int results = 0;
		long startTime = System.nanoTime();
		for (int a = 0; a < lim; a++) {
			for (int b = 0; b < lim; b++) {
				for (int c = 0; c < lim; c++) {
					for (int d = 0; d < lim; d++) {
						if(a*d-b*c == 0) {
							continue;
						}
						
						for (int r = 2; r < 6; r++) {
							for (int s = 17; s < 20; s++) {
								for (int t = 0; t < 6; t++) {
									for (int u = 2; u < 6; u++) {
										for (int v = 10; v < 26; v++) {
											for (int w = 0; w < 27; w++) {
												boolean first = b*(13-c*r)==d*(34-a*r);
												boolean second = b*(18-c*s)==d*(54-a*s);
												boolean third = d*(23-a*t)==b*(9-c*t);
												boolean fourth = d*(54-a*u)==b*(21-c*u);
												boolean fifth = d *(63 -a*v) == b* (23 -c*v);
												boolean special = a*d*(s-u)==b*(-18+c*s+21-c*u);
												boolean sixth = d * (57 - a*w) == b*(21 -c*w);
												boolean special2 = 0== b*c*(u-w)+d*(54-a*u-57+a*w);
												if ( first && second && third && fourth && fifth && special && sixth && special2) {
													int[][] inverse = {
															{d/(a*d-b*c), -1*b/(a*d-b*c)},
															{-1*c/(a*d-b*c), a/(a*d-b*c)}};
													int[][] tmp = multiplyMatrix(inverse, problem3As2by);
													if (easyTest(tmp)) {
														printCharMatrix(tmp);
														System.out.println("Success: a="+a+", b=" + b + ", c=" + c + ", d=" + d);
														printMatrix(inverse);
														//System.out.println(characters[r]+""+characters[s]+""+characters[t]+""+characters[u]+characters[v] + characters[w]);
														results++;
														
													}
													//return;
												}
											}
											
										}
									}
								}
							}
							//Thread.sleep(10);
						}
						
						
					}
				}
			}
		}
		System.out.println("Done with " + results + " results, taking " + (System.nanoTime()-startTime)/1000000000 + " seconds.");
	}
	
	
	public static void printCharMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] >= characters.length || matrix[row][col] <0) {
					continue;
				}
				System.out.print(characters[matrix[row][col]]);
			}
			
		}
		System.out.println("");
	}
	
	public static boolean easyTest(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				if (matrix[row][col] >= characters.length || matrix[row][col] < 0) {
					return false;
				}
			}
		}
		return true;
	}
	public static void bruteForceNumber3(int depth) throws Exception {
		
		
		int limit = 6;
		int[][] encrypter = new int[2][2];
		int[][] guesses = new int[2][depth];
		
		for (encrypter[0][0] = 0; encrypter[0][0] < limit; encrypter[0][0]+=1) {
			for (encrypter[0][1] = 0; encrypter[0][1] < limit; encrypter[0][1]+=1) {
				for (encrypter[1][0] = 0; encrypter[1][0] < limit; encrypter[1][0]+=1) {
					for (encrypter[1][1] = 0; encrypter[1][1] < limit; encrypter[1][1]+=1) {
						//printMatrix(encrypter);
						if (encrypter[0][0]*encrypter[1][1] == encrypter[0][1]*encrypter[1][0]) {
							continue;
						}
						iterateThroughRow(encrypter, guesses, 0, 0);
						
					}
				}
			}
		}
		//printMatrix(encrypter);
		System.out.println(results);
	}
	static int results = 0;
	public static void iterateThroughRow(int[][] encrypter, int[][] guesses, int rowIndex, int colIndex) throws Exception {
		if (colIndex == guesses[0].length) {
			//printCharMatrix(guesses);
			if(rowIndex == guesses.length-1) {
				printCharMatrix(guesses);
				printMatrix(encrypter);
				if (matrixEqual(multiplyMatrix(encrypter, guesses), problem3As2by)) {
					results++;
				}
				return;
			} else {
				iterateThroughRow(encrypter, guesses, rowIndex+1, 0);
			}
			
		} else {
		
		for(guesses[rowIndex][colIndex] = 0; guesses[rowIndex][colIndex] < 27; guesses[rowIndex][colIndex] += 1) {
			//if( problem3As2by[rowIndex][colIndex]==calculateMatrixLocation(encrypter, guesses, rowIndex, colIndex)) {
				//printMatrix(encrypter);
				//printCharMatrix(guesses);
				//results++;
				iterateThroughRow(encrypter, guesses, rowIndex, colIndex+1);
			//}
		}
		guesses[rowIndex][colIndex] = 0;
		}
	}
	
	public static void bruteForceNumber2() throws InterruptedException {
		int lim = 20;
		for (int a = 1; a < lim; a++) {
			for (int b = 1; b < lim; b++) {
				for (int c = 1; c < lim; c++) {
					for (int d = 1; d < lim; d++) {
						int i = (62-20*a)/b;
						int j = (32-8*a)/b;
						int k = (45-5*a) /b;
						if (i < 0 || j < 0 || k < 0) {
							continue;
						}
						if (165==20*c+d*i && 84==8*c+d*j && 115==5*c+d*k) {
							System.out.println("Success" + a + " " + b + " " + c + " " + d);
						}
					}
				}
				Thread.sleep(30);
			}
		}
	}
	
	public static boolean matrixEqual(int[][] left, int[][] right) {
		if (left.length != right.length && left[0].length != right[0].length) {
			//return false;
		}
		for(int i = 0; i < left.length; i++) {
			for (int j = 0; j < left[0].length; j++) {
				if (left[i][j] != right[i][j]) {
					return false;
				}
			}
		}
		return true;
	}
	
	public static void printMatrix(int[][] matrix) {
		for (int row = 0; row < matrix.length; row++) {
			for (int col = 0; col < matrix[0].length; col++) {
				System.out.print(matrix[row][col]+" ");
			}
			System.out.println();
		}
	}
	
	public static int[][] multiplyMatrix(int[][] left, int[][] right) throws Exception {
		// row column
		
		int leftCol = left[0].length;
		int leftRow = left.length;
		int rightCol = right[0].length;
		int rightRow = right.length;
		
		if (leftCol != rightRow) {
			throw new Exception();
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
	
	public static int calculateMatrixLocation(int[][] left, int[][] right, int row, int column) {
		int result = 0;
		for (int i = 0; i < left[0].length; i++) {
			result += left[row][i] * right[i][column];
		}
		return result;
	}
	public static int getNumberFromChar(char c) {
		for (int i = 0; i < characters.length; i++) {
			if (c == characters[i]) {
				return i;
			}
		}
		return -1;
	}
	
	public static void doNumber1() throws Exception {
		int[][] exampleInverse = {
				{-3,-2,4},
				{-1,0,1},
				{2,1,-2}};
		int[][] exampleOneMessage = {
				{33, 14, 14, 51, 0, 48, 53, 55, 0, 58, 50, 45, 24, 7, 21, 4, 31, 60, 45, 0,
				49, 30, 16, 9, 56, 37, 38, 49, 10, 56, 46, 15, 0},
				{ 51, 28, 19, 57, 10, 50, 53, 30, 0, 21, 49, 26, 5, 16, 43, 28, 17, 49, 20, 38, 60, 50, 37, 18, 29,
				32, 49, 72, 5, 20, 47, 13, 0},
				{ 54, 28, 23, 73, 5, 66, 72, 60, 0, 59, 64, 49,
				24, 15, 42, 17, 32, 74, 45, 19, 69, 51, 32, 18, 60, 46, 58, 74, 10, 57,
				60, 19, 0}};
		int[][] resultOne = multiplyMatrix(exampleInverse,exampleOneMessage);
				//printMatrix(result);
		printCharMatrix(resultOne);
				
	}
	public static void doNumber2() throws Exception {
		int[][] exampleTwoMessage = {
				{62, 32, 45, 0, 49, 33, 44, 21, 0, 47, 49, 26, 24, 15, 38, 28, 32, 45, 51,
					26, 6, 49, 24, 36, 37, 3},
				{ 165, 84, 115, 0, 124, 90, 112, 55, 0, 122, 132,
					65, 67, 45, 105, 70, 90, 120, 135, 65, 17, 127, 63, 93, 97, 9
				}
		};
		int[][] exampleTwoInverse = {
				{-5, 2},
				{3,-1}
		};
		
		int[][] resultTwo = multiplyMatrix(exampleTwoInverse, exampleTwoMessage);
		printCharMatrix(resultTwo);
			
	}
	
}
