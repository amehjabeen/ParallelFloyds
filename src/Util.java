import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;


public class Util {

	public int promptInt(BufferedReader in,String msg){
		try{
			System.out.println(msg);
			return Integer.parseInt(in.readLine());	
		}catch(InputMismatchException e){
			return promptInt(in,"Invalid input. Please enter the correct number");
		}catch(NumberFormatException e){
			return promptInt(in,"Invalid input. Please enter the correct number");
		} catch (IOException e) {
			return promptInt(in,"Invalid input. Please enter the correct number");
		}
	}
	
	public Vertex getVertex(ArrayList<Vertex> list,int size,Random r){
		return list.get(r.nextInt(size));
	}
	
	public void PrintShortestPaths(int[][] matrix){
		System.out.println("\nPrinting the matrix!");
		System.out.println("----------------------------------");
		int n = matrix[0].length;
		for(int i=0; i < n ; i++){
			for(int j=0; j < n; j++){
				System.out.print(matrix[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("----------------------------------\n");
	} 
	
}
