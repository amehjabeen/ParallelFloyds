import java.util.ArrayList;


public class Graph {
	private ArrayList<Vertex> vertices;
	private ArrayList<Edge> edges;
	private int[][] graph;
	public static int indexTracker = 0;
	
	public Graph(){
		vertices = new ArrayList<Vertex>();
		edges = new ArrayList<Edge>();
		indexTracker = 0;
	}
	
	public ArrayList<Vertex> getVertices() {
		return vertices;
	}
	public void setVertices(ArrayList<Vertex> vertices) {
		this.vertices = vertices;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public void setEdges(ArrayList<Edge> edges) {
		this.edges = edges;
	}
		
	public boolean AddEdge(Vertex startVertex,Vertex endVertex, int weight){
		Edge edge = new Edge(startVertex,endVertex,weight);
		if(!edges.contains(edge)){
			edges.add(edge);
			return true;
		}
		return false;
	}
	
	public boolean removeEdge(Edge edge){
		if(!edges.contains(edge)){
			edges.remove(edge);
			return true;
		}
		return false;
	}
	
	public boolean addVertex(Vertex vertex){
		if(!vertices.contains(vertex)){
			vertices.add(vertex);
			return true;
		}
		return false;
	}
	
	public void initializeMatrix(){
		int n = vertices.size();
		graph = new int[n][n];
		for(int i=0; i < n ; i++){
			for(int j=0; j < n; j++){
				if(i == j) graph[i][j] = 0;
				else graph[i][j] = 9999999;
			}
		}
	}
	
	public int[][] createAdjacencyMatrix(){
		initializeMatrix();
		int i,j;
		for(Edge e: edges){
			i = e.getStartVertex().getIndex();
			j = e.getEndVertex().getIndex();
			graph[i][j] = e.getWeight();
		}		
		return graph;
	}
	
	public int[][] getMatrix(){
		return graph;
	}
}
