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
    public void Given_AnEmptyNode_When_GettingLeftNode_Then_ReturnsFalse() {
      Assertions.assertNull(emptyNode.getLeft());
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
    
    // UpdateHeight
    // GetHeight
    // SetHeight
    // SetItem
    // SetParent
  }
}
