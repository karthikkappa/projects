

public class TwoFourTree {
private class TwoFourTreeItem
{
  int value1 = 0; // always exists.
  int value2 = 0; // exists iff the node is a 3-node or 4-node.
  int value3 = 0; // exists iff the node is a 4-node.
  boolean isLeaf = true;
  TwoFourTreeItem leftChild = null; // left and right child exist iff the note is a non-leaf.
  TwoFourTreeItem rightChild = null;
  TwoFourTreeItem centerChild = null; // center child exists iff the node is a non-leaf 3-node.
  TwoFourTreeItem centerLeftChild = null; // center-left and center-right children exist iff the node is a non-leaf 4-node.
  TwoFourTreeItem centerRightChild = null;
  public boolean isThreeNode()
  {
    if (value1 != 0 && value2 != 0 && value3 == 0)
    {
      return true;
    }
    return false;
  }
  public boolean isFourNode()
  {
    if (value1 != 0 && value2 != 0 && value3 != 0)
    {
      return true;
    }
    return false;
  }
  private void printIndents(int indent)
  {
    for(int i = 0; i < indent; i++) System.out.printf(" ");
  }
  public void printInOrder(int indent)
  {
    if(!isLeaf) leftChild.printInOrder(indent + 1);
    printIndents(indent);
    System.out.printf("%d\n", value1);
    if(isThreeNode()) {
    if(!isLeaf) centerChild.printInOrder(indent + 1);
    printIndents(indent);
    System.out.printf("%d\n", value2);
    } else if(isFourNode()) {
    if(!isLeaf) centerLeftChild.printInOrder(indent + 1);
    printIndents(indent);
    System.out.printf("%d\n", value2);
    if(!isLeaf) centerRightChild.printInOrder(indent + 1);
    printIndents(indent);
    System.out.printf("%d\n", value3);
    }
    if(!isLeaf) rightChild.printInOrder(indent + 1);
  }
}
TwoFourTreeItem root = null;
public boolean addValue(int value) {
return false;
}
public boolean hasValue(int value)
{
  if (root == null)
  {
    return false;
  }

  return false;
}
public boolean deleteValue(int value) {
return false;
}
public void printInOrder() {
if(root != null) root.printInOrder(0);
}
public TwoFourTree() {
}
}


