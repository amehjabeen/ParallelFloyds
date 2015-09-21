
public class Edge {
	private int weight;
	private Vertex startVertex;
	private Vertex endVertex;
	public Edge(Vertex startVertex, Vertex endVertex, int weight){
		this.setStartVertex(startVertex);
		this.setEndVertex(endVertex);
		this.setWeight(weight);
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public Vertex getStartVertex() {
		return startVertex;
	}
	public void setStartVertex(Vertex startVertex) {
		this.startVertex = startVertex;
	}
	public Vertex getEndVertex() {
		return endVertex;
	}
	public void setEndVertex(Vertex endVertex) {
		this.endVertex = endVertex;
	}
}
