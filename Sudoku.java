package sudoku;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
public class Sudoku{
	int count=0;
	 static int mat[][];
	static int N = 9; // number of columns/rows.
	int SRN; // square root of N
	int K; // No. Of missing digits
	Sudoku(int N, int K){
		this.N = N;
		this.K = K;
		SRN = (int)Math.sqrt(N);
		mat = new int[N][N];}
	public void fillValues(){
		fillDiagonal();
		fillRemaining(0, SRN);
	}
	void fillDiagonal(){
		for (int i = 0; i<N; i=i+SRN)
			fillBox(i, i);}
	boolean unUsedInBox(int rowStart, int colStart, int num){
		for (int i = 0; i<SRN; i++)
			for (int j = 0; j<SRN; j++)
				if (mat[rowStart+i][colStart+j]==num)
					return false; 	return true;}
	void fillBox(int row,int col){
		int num;
		for (int i=0; i<SRN; i++){
			for (int j=0; j<SRN; j++){
				do{num = randomGenerator(N);} while (!unUsedInBox(row, col, num));
				mat[row+i][col+j] = num;}}}
	static int randomGenerator(int num){return (int) Math.floor((Math.random()*num+1));}
	boolean CheckIfSafe(int i,int j,int num){
		return (unUsedInRow(i, num) &&
				unUsedInCol(j, num) &&
				unUsedInBox(i-i%SRN, j-j%SRN, num));}
	boolean unUsedInRow(int i,int num){
		for (int j = 0; j<N; j++)
		if (mat[i][j] == num)
				return false;
		return true;}
	boolean unUsedInCol(int j,int num){
		for (int i = 0; i<N; i++)
			if (mat[i][j] == num)
				return false;
		return true;
}
	boolean fillRemaining(int i, int j){
		if (j>=N && i<N-1){
			i = i + 1;
			j = 0;
		}if (i>=N && j>=N)
			return true;
		if (i < SRN){
			if (j < SRN)
				j = SRN;
		}else if (i < N-SRN){
			if (j==(int)(i/SRN)*SRN)
				j = j + SRN;
		}else{
			if (j == N-SRN){
				i = i + 1;
				j = 0;
				if (i>=N)
return true;}}
		for (int num = 1; num<=N; num++){
			if (CheckIfSafe(i, j, num)){
				mat[i][j] = num;
				if (fillRemaining(i, j+1))
					return true;
				mat[i][j] = 0;
}}return false;}
	public void printSudoku(){
		for (int i = 0; i<N; i++){
			for (int j = 0; j<N; j++)
				if(mat[i][j] !=0) {
					System.out.print(mat[i][j] + " ");
				}else {
					System.out.print("  ");
				}
			System.out.println();}
		System.out.println();}
public static void write() throws IOException {
	File myObj = new File("C:\\Users\\Santa\\Desktop\\sudokuofmine.txt");
	try {
	      FileWriter myWriter = new FileWriter("C:\\Users\\Santa\\Desktop\\sudokuofmine.txt");
	      for(int i=0;i<9;i++) {
	    	  for(int j=0;j<9;j++) {
	    		  myWriter.append(mat[i][j]+" ");
	    	  }myWriter.append("\n");
	      }myWriter.close();
	    }catch (IOException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();}}




public static void removeKDigits(int mat[][],int K)
{
	int count = K;
	while (count != 0)
	{
		int cellId = randomGenerator(N*N)-1;
		int i = (cellId/N);
		int j = cellId%9;
		if (j != 0)
			j = j - 1;
		if (mat[i][j] != 0)
		{
			count--;
			mat[i][j] = 0;
							}}}

	public static void main(String[] args) throws IOException{
		char[] arr=new char[2];
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a number 8 to 40");
		int N = 9, K = sc.nextInt() ;
		if(K>40) K=40;else if(K<8) K=8;
		Sudoku sudoku = new Sudoku(N, K);
		sudoku.fillValues();
		removeKDigits(mat,K);
		sudoku.printSudoku();
		
		write();}}
