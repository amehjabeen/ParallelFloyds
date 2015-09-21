import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class TestClass {

	public static int[][] matrix;
	static int n;
	static int numThreads;

	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Util util = new Util();
		Graph g = new Graph();

		Vertex a = new Vertex(0);
		Vertex b = new Vertex(1);
		Vertex c = new Vertex(2);
		Vertex d = new Vertex(3);
		g.addVertex(a);
		g.addVertex(b);
		g.addVertex(c);
		g.addVertex(d);

		g.AddEdge(a,b,6);
		g.AddEdge(a,c,9);
		g.AddEdge(a,d,1);
		g.AddEdge(b,a,15);
		g.AddEdge(c,d,2);
		g.AddEdge(d,b,8);

		g.createAdjacencyMatrix();
		matrix = g.getMatrix();
		
		System.out.println("Sequential algorithm on a static 4x4 matrix");
		ThreadedFloyd t= new ThreadedFloyd();
		t.sequentialFloyds();
		util.PrintShortestPaths(matrix);
		
		n = g.getVertices().size();
		numThreads = util.promptInt(br, "Enter the number of threads to be used: ");
		matrix=g.getMatrix();		
		
		System.out.println("Parallel algorithm on a static 4x4 matrix");
		ThreadedFloyd mat1[]=new ThreadedFloyd [numThreads];
		long t11=System.currentTimeMillis();

		for(int i1=0;i1<numThreads;i1++)
			mat1[i1]=new ThreadedFloyd(i1);
		try
		{
			for(int i=0;i<numThreads;i++)
				mat1[i].join();
		}catch(Exception e){}

		long t22=System.currentTimeMillis();
		System.out.println("time = " + (t22-t11)+" millisecond");

		util.PrintShortestPaths(matrix);
		/*----------------------------------------------------------------------------------*/
		
		int size = util.promptInt(br,"Enter a size for the random matrix: ");

		//Create graph
		Graph graph = new Graph();

		//Create vertices
		ArrayList<Vertex> list = new ArrayList<Vertex>();
		for(int k = 0; k < size ;k++){
			Vertex v = new Vertex(k);
			graph.addVertex(v);
			list.add(v);
		}

		Random r = new Random();
		int maxEdges;

		int density = util.promptInt(br, "Enter 1 to generate a dense matrix and 2 to generate a sparse matrix");
		while(density != 1 && density!=2){
			density = util.promptInt(br, "Please enter either 1 or 2");
		}

		int numEdges;
		if(density == 1){ //dense matrix
			maxEdges = (size * (size - 1))/2;
			numEdges = r.nextInt(maxEdges)+1;
			if(numEdges<size){
				//+size ensures at least n edges are present in the graph
				numEdges = numEdges + size;
			}
		}
		else{ //sparse matrix
			maxEdges = (size);
			//+1 ensures at least 1 edge is generated since nextInt returns numbers from 0 to maxEdges(exclusive)
			numEdges = r.nextInt(maxEdges)+1;
		}

		//Create random edges and add to graph
		for(int k=0; k<numEdges ; k++){
			Vertex startVertex = util.getVertex(list,size,r);
			Vertex endVertex = util.getVertex(list,size,r);
			while(startVertex.getIndex() == endVertex.getIndex()){
				endVertex = util.getVertex(list,size,r);
			}
			int weight = r.nextInt(100);
			graph.AddEdge(startVertex, endVertex, weight);
		}

		graph.createAdjacencyMatrix();
		matrix=graph.getMatrix();	
		n = graph.getVertices().size();
		
		int i;
		System.out.println("Starting parallel floyd's on the random matrix...");
		final long t1=System.currentTimeMillis();

		ThreadedFloyd mat[]=new ThreadedFloyd [numThreads];

		for(i=0;i<numThreads;i++)
			mat[i]=new ThreadedFloyd(i);
		try
		{
			for(i=0;i<numThreads;i++)
				mat[i].join();
		}catch(Exception e){}

		final long t2=System.currentTimeMillis();
		System.out.println(t2);
		System.out.println(t1);
		System.out.println("time = " + (t2-t1)+" milliseconds");

	}





}
