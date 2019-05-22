import java.util.Iterator;
import java.util.Vector;

public class Quadrangle4 implements Face {
  private final Vector<Node> nodes;

  public Quadrangle4(Node[] itsNodes) { 
    nodes = new Vector<Node>();
    nodes.add(itsNodes[0]);
    nodes.add(itsNodes[1]);
    nodes.add(itsNodes[2]);
    nodes.add(itsNodes[3]);
  }

  @Override
  public Iterator<Node> iterator() { 
    return nodes.iterator();
  }
  @Override
  public Node getNode(int i) {
    return nodes.get(i);
  }
}