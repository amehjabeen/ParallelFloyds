
public class ThreadedFloyd extends Thread {

	private static int srow;
	private static int scol;
	private int row;
	private int col;
	private int init;
	private static final Object lock= new Object();
	private static final Object lock2= new Object();
	

	public ThreadedFloyd(){}

	public ThreadedFloyd(int i){
		row=i; 
		init = i;
		col = i;
		this.start();
	}

	//Fine granularity: Each thread calculates one element.
		/*public void run() {
			int k;
			for(k=0;k<TestClass.n;k++) {
				synchronized(lock2){
				row = init;
				srow = numThreads - 1;
				}
				while (row<TestClass.n) {
				synchronized(lock){
					col = init;
					scol = numThreads - 1;
					}
					while(col<TestClass.n){
						matrix[row][col]=Math.min(TestClass.matrix[row][col], TestClass.matrix[row][k] + TestClass.matrix[k][col]);
						synchronized (lock){            
							scol++;
							col=scol;
						}
					}
					synchronized (lock2){            
						srow++;
						row=srow;
					}
				}
			}
		} */

		/* //Intermediate granularity: Each thread calculates one row. 
		public void run() {
			int j,k;
			for(k=0;k<TestClass.n;k++) {
				synchronized(lock2){
				row = init;
				srow = numThreads-1;
				}
				while (row<TestClass.n) {
					for(j=0;j<TestClass.n;j++){
						TestClass.matrix[row][j]=Math.min(TestClass.matrix[row][j] , TestClass.matrix[row][k] + TestClass.matrix[k][j]);
					}
					synchronized (lock2){            
						srow++;
						row=srow;
					}
				}
			}
		} */

		//Coarse granularity: Each thread calculates one matrix.
		public void run() {
			int i,j;
			while (row< TestClass.n) {
				for(i=0;i<TestClass.n;i++) {
					for(j=0;j<TestClass.n;j++)
						TestClass.matrix[i][j]=Math.min(TestClass.matrix[i][j], TestClass.matrix[i][row]+TestClass.matrix[row][j]);
				}
				synchronized (lock){
					srow++;
					row=srow;
				}
			}
		}

	public void sequentialFloyds() {
		for( int k = 0; k < TestClass.n; k++ )
			for( int i = 0; i < TestClass.n; i++ )
				for( int j = 0; j < TestClass.n; j++ )
					TestClass.matrix[i][j] = Math.min(TestClass.matrix[i][j] , TestClass.matrix[i][k] + TestClass.matrix[k][j]);
	}

}
