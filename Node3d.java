public class Node3d implements Node {
  private final double x;
  private final double y;
  private final double z;
  private int itsTag;

  public Node3d(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  @Override
  public double x() {
    return x;
  }
  @Override
  public double y() {
    return y;
  }
  @Override
  public double z() {
    return z;
  }

  @Override
  public void setTag(int tag) {
    itsTag = tag;
  }

  @Override
  public int tag() {
    if (itsTag == 0) {
      throw new NullPointerException("Tag uncreated!");
    } 
    return itsTag;
  }

  public static void main(String[] args) {
    // Node3d aNode = new Node3d(1.0, 2.0, 3.0);
    // System.out.println(aNode.x());
    // System.out.println(aNode.y());
    // System.out.println(aNode.z());
  }
}
