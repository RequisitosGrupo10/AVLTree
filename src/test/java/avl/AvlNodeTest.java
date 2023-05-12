package avl;

import org.junit.jupiter.api.*;

/**
 * Created with IntelliJ IDEA. User: Antonio J. Nebro Date: 09/07/13 Time: 15:29
 */
public class AvlNodeTest {
  @Nested
  @DisplayName("Given an empty node")
  class EmptyNodeTest {
    private static AvlNode<Object> emptyNode;
    private static Object item;

    @BeforeAll
    static void setUp() throws Exception {
      item = 5;
      emptyNode = new AvlNode<>(item);
    }


    @AfterAll
    public static void tearDown() throws Exception {
      emptyNode = null;
    }
    @Test
    @DisplayName("Has no left node")
    public void Given_AnEmptyNode_When_AskingIfItHasALeftHasLeftChild_Then_ReturnsFalse() {
      Assertions.assertFalse(emptyNode.hasLeft());
    }

    @Test
    @DisplayName("Has no right node")
    public void Given_AnEmptyNode_When_AskingIfItHasALeftHasRightChild_Then_ReturnsFalse() {
      Assertions.assertFalse(emptyNode.hasRight());
    }

    @Test
    @DisplayName("Has no right node")
    public void Given_AnEmptyNode_When_AskingIfItHasAParent_Then_ReturnsFalse() {
      Assertions.assertFalse(emptyNode.hasParent());
    }

    @Test
    @DisplayName("Is a leaf")
    public void Given_AnEmptyNode_When_AskingIfItIsALeaf_Then_ReturnsTrue() {
      Assertions.assertTrue(emptyNode.isLeaf());
    }

    @Test
    @DisplayName("Has no right node")
    public void Given_AnEmptyNode_When_AskingIfItIseaf_Then_ReturnsFalse() {
      int expectedValue = 0;
      int actualValue =  emptyNode.getHeight();

      Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("Right node is null")
    public void Given_AnEmptyNode_When_GettingRightNode_Then_ReturnsNull() {
      Assertions.assertNull(emptyNode.getRight());
    }

    @Test
    @DisplayName("Left node is null")
    public void Given_AnEmptyNode_When_GettingLeftNode_Then_ReturnsNull() {
      Assertions.assertNull(emptyNode.getLeft());
    }

    @Test
    @DisplayName("Parent node is null")
    public void Given_AnEmptyNode_When_GettingParentNode_Then_ReturnsNull() {
      Assertions.assertNull(emptyNode.getParent());
    }

    @Test
    @DisplayName("Closest node is null")
    public void Given_AnEmptyNode_When_GettingClosestNode_Then_ReturnsNull() {
      Assertions.assertNull(emptyNode.getClosestNode());
    }

    @Test
    @DisplayName("Getting item returns the item passed on constructor")
    public void Given_AnEmptyNode_When_GettingItem_Then_ReturnsItemOnConstruction() {
      Object expectedItem = item;
      Object actualItem = emptyNode.getItem();

      Assertions.assertEquals(expectedItem, actualItem);
    }
  }

  @Nested
  @DisplayName("Given a node with a left child")
  class NodeWithLeftChildTest {
    private static AvlNode<Object> nodeWithLeftChild;

    @BeforeAll
    static void setUp() throws Exception {
      nodeWithLeftChild = new AvlNode<>(5);
      AvlNode<Object> leftChild = new AvlNode<>(6);

      nodeWithLeftChild.setLeft(leftChild);

      leftChild.setParent(nodeWithLeftChild);
      nodeWithLeftChild.updateHeight();
    }


    @AfterAll
    public static void tearDown() throws Exception {
      nodeWithLeftChild = null;
    }

    @Test
    @DisplayName("Has only left child")
    public void Given_ANodeWithOnlyALeftChild_When_AskingIfItOnlyHasOnlyLeftNode_Then_ReturnsTrue() {
      Assertions.assertTrue(nodeWithLeftChild.hasOnlyALeftChild());
    }

    @Test
    @DisplayName("Has only right child")
    public void Given_ANodeWithOnlyARightChild_When_AskingIfItOnlyHasOnlyRightNode_Then_ReturnsFalse() {
      Assertions.assertFalse(nodeWithLeftChild.hasOnlyARightChild());
    }

  }

  @Nested
  @DisplayName("Given a node with a right child")
  class NodeWithRightChildTest {
    private static AvlNode<Object> nodeWithRightChild;

    @BeforeAll
    static void setUp() throws Exception {
      nodeWithRightChild = new AvlNode<>(5);
      AvlNode<Object> rightChild = new AvlNode<>(7);

      nodeWithRightChild.setRight(rightChild);

      rightChild.setParent(nodeWithRightChild);
      nodeWithRightChild.updateHeight();
    }


    @AfterAll
    public static void tearDown() throws Exception {
      nodeWithRightChild = null;
    }

    @Test
    @DisplayName("Has only left child")
    public void Given_ANodeWithOnlyARightChild_When_AskingIfItOnlyHasOnlyLeftNode_Then_ReturnsFalse() {
      Assertions.assertFalse(nodeWithRightChild.hasOnlyALeftChild());
    }

    @Test
    @DisplayName("Has only right child")
    public void Given_ANodeWithOnlyARightChild_When_AskingIfItOnlyHasOnlyLeftNode_Then_ReturnsTrue() {
      Assertions.assertTrue(nodeWithRightChild.hasOnlyARightChild());
    }
  }

  @Nested
  @DisplayName("Given a node with two children")
  class NodeWithTwoChildrenTest {
    private static AvlNode<Object> nodeWithTwoChildren;

    @BeforeAll
    static void setUp() throws Exception {
      nodeWithTwoChildren = new AvlNode<>(5);
      AvlNode<Object> leftChild = new AvlNode<>(6);
      AvlNode<Object> rightChild = new AvlNode<>(7);

      nodeWithTwoChildren.setLeft(leftChild);
      nodeWithTwoChildren.setRight(rightChild);

      leftChild.setParent(nodeWithTwoChildren);
      rightChild.setParent(nodeWithTwoChildren);
      nodeWithTwoChildren.updateHeight();
    }


    @AfterAll
    public static void tearDown() throws Exception {
      nodeWithTwoChildren = null;
    }
  }
  @Nested
  class AVLNodeGettersAndSettersTest {
    // TODO - New Node from zero and check setters and getters

    @Test
    @DisplayName("On an empty node, updating height sets height to 0")
    void Given_AnEmptyNode_When_UpdatingHeight_Then_HeightDoesNotChangeAndEqualsZero(){
      AvlNode<Object> avlNode = new AvlNode<>(6);
      avlNode.updateHeight();

      int expectedValue = 0;
      int actualValue = avlNode.getHeight();

      Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("On an empty node, when setting a new height, getting the height returns the new value")
    void Given_AnEmptyNode_When_SettingANewHeight_Then_UpdatesItHeight(){
      int newHeight = 5;
      AvlNode<Object> avlNode = new AvlNode<>(6);
      avlNode.setHeight(newHeight);

      int expectedValue = newHeight;
      int actualValue = avlNode.getHeight();

      Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("On an empty node, when setting a new item, getting the item returns the new value")
    void Given_AnEmptyNode_When_SettingANewItem_Then_UpdatesItsItem(){
      Integer newItem = 5;
      AvlNode<Integer> avlNode = new AvlNode<>(6);
      avlNode.setItem(newItem);

      int expectedValue = newItem;
      int actualValue = avlNode.getItem();

      Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("On an empty node, when setting the closes node, getting the closest node, returns the new node")
    void Given_AnEmptyNode_When_SettingANewClosestNode_Then_UpdatesClosestNode(){
      AvlNode<Integer> avlNode = new AvlNode<>(6);
      AvlNode<Integer> newClosestNode = new AvlNode<>(6);

      avlNode.setClosestNode(newClosestNode);

      AvlNode<Integer> expectedValue = newClosestNode;
      AvlNode<Integer> actualValue = avlNode.getClosestNode();

      Assertions.assertEquals(expectedValue, actualValue);
    }

    @Test
    @DisplayName("On an node with two children, when updating the height, then height is set to the maximum size of their children plus one")
    void Given_AnNodeWithTwoChildren_When_UpdatingTheHeight_Then_ReturnsTheMaximumSizeOfTheChildrenPlusOne(){
      AvlNode<Integer> grandfatherNode = new AvlNode<>(5);
      AvlNode<Integer> leftfatherNode = new AvlNode<>(6);
      AvlNode<Integer> rightfatherNode = new AvlNode<>(7);
      AvlNode<Integer> grandchildleftNode = new AvlNode<>(8);
      AvlNode<Integer> grandchildrightNode = new AvlNode<>(9);

      grandfatherNode.setLeft(leftfatherNode);
      grandfatherNode.setRight(rightfatherNode);
      leftfatherNode.setParent(grandfatherNode);
      rightfatherNode.setParent(grandfatherNode);

      leftfatherNode.setLeft(grandchildleftNode);
      leftfatherNode.setRight(grandchildrightNode);
      grandchildrightNode.setParent(leftfatherNode);
      grandchildleftNode.setParent(leftfatherNode);

      leftfatherNode.updateHeight();
      grandfatherNode.updateHeight();

      int expectedValue = 2;
      int actualValue = grandfatherNode.getHeight();

      Assertions.assertEquals(expectedValue, actualValue);
    }
  }
}
