import java.util.HashMap;

public class GmshImporter implements MeshImporter {
  private In in;
  private GmshMesh itsMesh;
  private HashMap<Integer, Node> itsTagToNode;
  //
  private HashMap<Integer, String> nameOfTag;
  private HashMap<Integer, HashMap<Integer,Integer>> physicalToEntity;

  @Override
  public void open(String filename) {
    in = new In(filename);
    assert(in != null);
  }

  @Override
  public void parse() {
    itsMesh = new GmshMesh();
    String line = null; 
    while((line = in.readLine()) != null) {
      if (line.equals("$PhysicalNames")) {
        readPhysicalNames(in);
      }
      if (line.equals("$Entities")) {
        readEntities(in);
      }
      if (line.equals("$Nodes")) {
        readNodes(in);
      }
      if (line.equals("$Elements")) {
        readElements(in);
      }
    }
  }
  @Override
  public Mesh getMesh() {
    if (itsMesh == null) {
      throw new NullPointerException("Mesh uncreated!");
    }
    return itsMesh;
  }

  private void readPhysicalNames(In in) {
    String line = null;
    line = in.readLine();
    int numPhysicalNames = Integer.parseInt(line);
    nameOfTag = new HashMap<Integer,String>();
    for (int i = 0; i < numPhysicalNames; i++) {
      line = in.readLine();
      String[] temp = line.split(" ");
      nameOfTag.put(Integer.parseInt(temp[1]),temp[2]);
      itsMesh.put(temp[2],new GmshPart());
    }
  }

  private void readEntities(In in) {
    physicalToEntity = new HashMap<Integer,HashMap<Integer,Integer>>();
    String line = null;
    line = in.readLine();
    String[] head = line.split(" ");
    for (int i = 0; i < Integer.parseInt(head[0]); i++) {
      line = in.readLine();
      String[] temp = line.split(" ");
      int numPhysicalTags = Integer.parseInt(temp[4]);
      if (numPhysicalTags > 0) {
        for (int k = 1; k <= numPhysicalTags; k++) {
          if (physicalToEntity.containsKey(0)) {
            physicalToEntity.get(0).put(i,Integer.parseInt(temp[4+k]));
          }
          else {
            physicalToEntity.put(0,new HashMap<Integer,Integer>());
            physicalToEntity.get(0).put(i,Integer.parseInt(temp[4+k]));
          }
        }
      }
    }
    for (int i = 1; i < head.length; i++) {
      for (int j = 1; j <= Integer.parseInt(head[i]); j++) {
        line = in.readLine();
        String[] temp = line.split(" ");
        int numPhysicalTags = Integer.parseInt(temp[7]);
        if (numPhysicalTags > 0) {
          for (int k = 1; k <= numPhysicalTags; k++) {
            if (physicalToEntity.containsKey(i)) {
              physicalToEntity.get(i).put(j,Integer.parseInt(temp[7+k]));
            }
            else {
              physicalToEntity.put(i,new HashMap<Integer,Integer>());
              physicalToEntity.get(i).put(j,Integer.parseInt(temp[7+k]));
            }
          }
        }
      }
    }

  }

  private void readNodes(In in) {
    itsTagToNode = new HashMap<Integer,Node>();
    String line = null;
    line = in.readLine();
    String[] head = line.split(" ");
    int numBlocks = Integer.parseInt(head[0]);
    int numNodes = Integer.parseInt(head[1]);
    for (int i = 0; i < numBlocks; i++) {
      line = in.readLine();
      int[] arr = transToIntArray(line);
      int[] tags = new int[arr[3]];
      for (int j = 0; j < arr[3]; j++) {
        line = in.readLine();
        int tag = Integer.parseInt(line);
        tags[j] = tag;
      }
      for (int j = 0; j < arr[3]; j++) {
        line = in.readLine();
        double[] coordinate = transToDoubleArray(line);
        itsTagToNode.put(tags[j],new Node3d(coordinate[0], coordinate[1], coordinate[2]));
        itsTagToNode.get(tags[j]).setTag(tags[j]);
      }
    }
    line = in.readLine();
    if (line.equals("$EndNodes") && itsTagToNode.size() == numNodes) {
      StdOut.println("finish reading nodes!");
    }
  }

  private void readElements(In in) {
    String line = null;
    line = in.readLine();
    String[] head = line.split(" ");
    int numBlocks = Integer.parseInt(head[0]);
    // int numElements = Integer.parseInt(head[1]);
    for (int i = 0; i < numBlocks; i++) {
      line = in.readLine();
      int[] arr = transToIntArray(line);
      int temp = physicalToEntity.get(arr[0]).get(arr[1]);
      String name = nameOfTag.get(temp);
      int type = arr[2];
      for (int j = 0; j < arr[3]; j++) {
        line = in.readLine();
        int[] tags = transToIntArray(line);
        insert(itsMesh.get(name), type, tags);
      } 
    }

    line = in.readLine();
    if (line.equals("$EndElements")) {
      StdOut.println("finish reading elements!");
    }
  }

  private int[] transToIntArray(String line) {
    String[] arrOfStrs = line.split(" ");
    int[] arrOfInts = new int[arrOfStrs.length];
    for (int i = 0; i < arrOfInts.length; i++) {
      arrOfInts[i] = Integer.parseInt(arrOfStrs[i]);
    }
    return arrOfInts;
  }

  private double[] transToDoubleArray(String line) {
    String[] arrOfStrs = line.split(" ");
    double[] arrOfDous = new double[arrOfStrs.length];
    for (int i = 0; i < arrOfDous.length; i++) {
      arrOfDous[i] = Double.parseDouble(arrOfStrs[i]);
    }
    return arrOfDous;
  }

  private void insert(Part part, int type, int[] tags) {
    switch(type) {
      case 1 :
        Node[] nodes1 = new Node[2];
        for (int i = 0; i < 2; i++) {nodes1[i] = itsTagToNode.get(tags[i+1]);}
        part.put(tags[0],new Line2(nodes1));
        break;
      case 2 :
        Node[] nodes2 = new Node[3];
        for (int i = 0; i < 3; i++) {nodes2[i] = itsTagToNode.get(tags[i+1]);}
        part.put(tags[0],new Triangle3(nodes2));
        break;
      case 3 :
        Node[] nodes3 = new Node[4];
        for (int i = 0; i < 4; i++) {nodes3[i] = itsTagToNode.get(tags[i+1]);}
        part.put(tags[0],new Quadrangle4(nodes3));
        break;
      case 4 :
        Node[] nodes4 = new Node[4];
        for (int i = 0; i < 4; i++) {nodes4[i] = itsTagToNode.get(tags[i+1]);}
        part.put(tags[0],new Textrahedron4(nodes4));
        break;
      case 6 :
        Node[] nodes6 = new Node[6];
        for (int i = 0; i < 6; i++) {nodes6[i] = itsTagToNode.get(tags[i+1]);}
        part.put(tags[0],new Prism6(nodes6));
        break;
    }
  }


  public static void main(String[] args) {
    GmshImporter importer = new GmshImporter();
    importer.open(args[0]);
    importer.parse();
    Mesh aMesh = importer.getMesh();
    for (Part aPart : aMesh) {
      for (Element aElement : aPart) {
        StdOut.println();
        for (Node aNode : aElement) {
          StdOut.printf("%6.3f\t%6.3f\t%6.3f\n", aNode.x(), aNode.y(), aNode.z());
        }
      }
    }
  }
}
