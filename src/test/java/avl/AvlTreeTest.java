package avl;

import org.junit.jupiter.api.*;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Created with IntelliJ IDEA. User: Antonio J. Nebro Date: 08/07/13
 */
public class AvlTreeTest {
    AvlTree<Integer> avlTree;
    Comparator<?> comparator;
    private AvlNode<Integer> node;

    @BeforeEach
    void setUp() {
        comparator = Comparator.comparingInt((Integer o) -> o);
        avlTree = new AvlTree(comparator);
    }

    @AfterEach
    void tearDown() {
        avlTree = null;
        comparator = null;
    }

    @Nested
    @DisplayName("Given an empty AVL Tree")
    class EmptyAVLTreeTest {
        @DisplayName("Then is empty")
        @Test
        void given_AnEmptyAVLTree_When_AskingIfItIsEmpty_Then_AVLTreeIsEmpty() {
            assertTrue(avlTree.avlIsEmpty());
        }

        @Test
        @DisplayName("When inserting a node on top then AVL Tree is not empty")
        void given_EmptyAVLTree_When_InsertingANodeOnTop_Then_AVLTreeIsNotEmpty() {
            avlTree.insertTop(new AvlNode(5));

            assertFalse(avlTree.avlIsEmpty());
        }

        @DisplayName("When inserting a node on top then element on top is node's element")
        @Test
        void given_AnEmptyAVLTree_When_InsertingANodeOnTop_Then_ElementOnTopIsNodeElement() {
            AvlNode<Integer> node = new AvlNode(4);

            avlTree.insertTop(node);

            assertEquals(node, avlTree.getTop());
        }
    }

    @DisplayName("Given three nodes when comparing nodes then node's elements are compared")
    @Test
    void given_ThreeNodes_When_ComparingNodes_Then_NodeElementsAreCompared() {
        AvlNode<Integer> node1 = new AvlNode<>(4);
        AvlNode<Integer> node2 = new AvlNode<>(5);
        AvlNode<Integer> node3 = new AvlNode<>(5);

        assertEquals(-1, avlTree.compareNodes(node1, node2));
        assertEquals(1, avlTree.compareNodes(node3, node1));
        assertEquals(0, avlTree.compareNodes(node2, node3));
    }

    @Nested
    @DisplayName("Test inserting an AVL Node")
    class InsertAVLNodeTest {
        @DisplayName("Given an AVL Tree with one node when inserting a smaller node then new node is in left")
        @Test
        void given_AnAVLTreeWithOneNode_When_InsertingASmallerNode_Then_NewNodeIsInLeft() {
            AvlNode<Integer> node = new AvlNode<>(6);
            avlTree.insertAvlNode(node);

            AvlNode<Integer> nodeLeft = new AvlNode<>(4);
            avlTree.insertAvlNode(nodeLeft);

            assertEquals(node, nodeLeft.getParent());
            assertEquals(nodeLeft, node.getLeft());
        }

        @DisplayName("Given an AVL Tree with one node when inserting a bigger node then new node is in right")
        @Test
        void given_AnAVLTreeWithOneNode_When_InsertingABiggerNode_Then_NewNodeIsInRight() {
            AvlNode<Integer> node = new AvlNode<>(6);
            avlTree.insertAvlNode(node);

            AvlNode<Integer> nodeRight = new AvlNode<>(9);
            avlTree.insertAvlNode(nodeRight);

            assertEquals(node, nodeRight.getParent());
            assertEquals(nodeRight, node.getRight());
        }
    }

    @Nested
    @DisplayName("Test searching for closest node")
    class SearchClosestNodeTest {
        @DisplayName("Given an empty AVL Tree when searching for closest node then returns 0")
        @Test
        void given_AnEmptyAVLTree_When_SearchingForClosestNode_Then_Returns0() {
            AvlNode<Integer> node = new AvlNode<>(7);

            int obtainedResult = avlTree.searchClosestNode(node);
            int expectedResult = 0;

            assertEquals(expectedResult, obtainedResult);
        }

        @DisplayName("Given an AVL Tree with one node when searching for closest node from a smaller node then returns -1")
        @Test
        void given_AVLTreeWithOneNode_When_SearchingForClosestNodeFromASmallerNode_Then_ReturnsMinus1() {
            AvlNode<Integer> node = new AvlNode<>(7);
            avlTree.insertAvlNode(node);

            node = new AvlNode<>(4);
            int obtainedResult = avlTree.searchClosestNode(node);
            int expectedResult = -1;

            assertEquals(expectedResult, obtainedResult);
        }

        @DisplayName("Given an AVL Tree with one node when searching for closest node from a bigger node then returns 1")
        @Test
        void given_AVLTreeWithOneNode_When_SearchingForClosestNodeFromABiggerNode_Then_Returns1() {
            AvlNode<Integer> node = new AvlNode<>(7);
            avlTree.insertAvlNode(node);

            node = new AvlNode<>(9);
            int obtainedResult = avlTree.searchClosestNode(node);
            int expectedResult = 1;

            assertEquals(expectedResult, obtainedResult);
        }
    }

    @Nested
    @DisplayName("Test tree's height and balance")
    class HeightAndRebalanceTest {
        @DisplayName("Given an Empty AVL Tree when inserting new nodes then tree's height and balance are modified")
        @Test
        void given_AnEmptyAVLTree_When_InsertingNewNodes_Then_TreeHeightAndBalanceAreModified() {
            AvlNode<Integer> node1, node2, node3, node4, node5;

            node1 = new AvlNode<>(7);
            avlTree.insertAvlNode(node1);
            assertEquals(0, node1.getHeight());
            assertEquals(0, avlTree.getBalance(node1));

            node2 = new AvlNode<>(4);
            avlTree.insertAvlNode(node2);
            assertEquals(0, node2.getHeight());
            assertEquals(1, node1.getHeight());
            assertEquals(-1, avlTree.getBalance(node1));
            assertEquals(0, avlTree.getBalance(node2));

            node3 = new AvlNode<>(9);
            avlTree.insertAvlNode(node3);
            assertEquals(0, node3.getHeight());
            assertEquals(1, node1.getHeight());
            assertEquals(0, avlTree.getBalance(node1));
            assertEquals(0, avlTree.getBalance(node3));

            node4 = new AvlNode<>(3);
            avlTree.insertAvlNode(node4);
            assertEquals(0, node4.getHeight());
            assertEquals(1, node2.getHeight());
            assertEquals(2, node1.getHeight());
            assertEquals(-1, avlTree.getBalance(node2));
            assertEquals(-1, avlTree.getBalance(node1));
            assertEquals(0, avlTree.getBalance(node4));

            node5 = new AvlNode<>(5);
            avlTree.insertAvlNode(node5);
            assertEquals(0, node5.getHeight());
            assertEquals(1, node2.getHeight());
            assertEquals(2, node1.getHeight());
            assertEquals(0, avlTree.getBalance(node2));
            assertEquals(-1, avlTree.getBalance(node1));
            assertEquals(0, avlTree.getBalance(node5));
        }

        @DisplayName("Given an AVL Tree when inserting a left left node then tree's height and balance are modified")
        @Test
        void given_AnAVLTree_When_InsertingALeftLeftNode_Then_TreeHeightAndBalanceAreModified() {
            AvlNode<Integer> node1, node2, node3;

            node1 = new AvlNode<>(7);
            avlTree.insertAvlNode(node1);
            assertEquals(0, node1.getHeight());
            assertEquals(0, avlTree.getBalance(node1));

            node2 = new AvlNode<>(4);
            avlTree.insertAvlNode(node2);
            assertEquals(0, node2.getHeight());
            assertEquals(1, node1.getHeight());
            assertEquals(-1, avlTree.getBalance(node1));
            assertEquals(0, avlTree.getBalance(node2));

            node3 = new AvlNode<>(3);
            avlTree.insertAvlNode(node3);
            assertEquals(node2, avlTree.getTop());
            assertEquals(node3, node2.getLeft());
            assertEquals(node1, node2.getRight());

            assertEquals(1, avlTree.getTop().getHeight());
            assertEquals(0, avlTree.getTop().getLeft().getHeight());
            assertEquals(0, avlTree.getTop().getRight().getHeight());
            assertEquals(-1, avlTree.height(node1.getLeft()));
            assertEquals(-1, avlTree.height(node1.getRight()));
            assertEquals(-1, avlTree.height(node3.getLeft()));
            assertEquals(-1, avlTree.height(node3.getRight()));
        }

        @DisplayName("Given an AVL Tree when inserting a right right node then tree's height and balance are modified")
        @Test
        void given_AnAVLTree_When_InsertingARightRightNode_Then_TreeHeightAndBalanceAreModified() {
            AvlNode<Integer> node1, node2, node3;

            node1 = new AvlNode<>(7);
            avlTree.insertAvlNode(node1);
            assertEquals(0, node1.getHeight());
            assertEquals(0, avlTree.getBalance(node1));

            node2 = new AvlNode<Integer>(10);
            avlTree.insertAvlNode(node2);
            assertEquals(0, node2.getHeight());
            assertEquals(1, node1.getHeight());
            assertEquals(1, avlTree.getBalance(node1));
            assertEquals(0, avlTree.getBalance(node2));

            node3 = new AvlNode<Integer>(14);
            avlTree.insertAvlNode(node3);
            assertEquals(node2, avlTree.getTop());
            assertEquals(node1, node2.getLeft());
            assertEquals(node3, node2.getRight());

            assertEquals(1, avlTree.getTop().getHeight());
            assertEquals(0, avlTree.getTop().getLeft().getHeight());
            assertEquals(0, avlTree.getTop().getRight().getHeight());
            assertEquals(-1, avlTree.height(node1.getLeft()));
            assertEquals(-1, avlTree.height(node1.getRight()));
            assertEquals(-1, avlTree.height(node3.getLeft()));
            assertEquals(-1, avlTree.height(node3.getRight()));
        }

        @DisplayName("Given an AVL Tree when inserting a left right node then tree's height and balance are modified")
        @Test
        void given_AnAVLTree_When_InsertingALeftRightNode_Then_TreeHeightAndBalanceAreModified() {
            AvlNode<Integer> node1, node2, node3;

            node1 = new AvlNode<>(7);
            avlTree.insertAvlNode(node1);

            node2 = new AvlNode<>(2);
            avlTree.insertAvlNode(node2);

            node3 = new AvlNode<>(3);
            avlTree.insertAvlNode(node3);

            assertEquals(node3, avlTree.getTop());
            assertEquals(node2, node3.getLeft());
            assertEquals(node1, node3.getRight());

            assertEquals(1, avlTree.getTop().getHeight());
            assertEquals(0, avlTree.getTop().getLeft().getHeight());
            assertEquals(0, avlTree.getTop().getRight().getHeight());
            assertEquals(-1, avlTree.height(node2.getLeft()));
            assertEquals(-1, avlTree.height(node2.getRight()));
            assertEquals(-1, avlTree.height(node1.getLeft()));
            assertEquals(-1, avlTree.height(node1.getRight()));
        }

        @DisplayName("Given an AVL Tree when inserting a right left node then tree's height and balance are modified")
        @Test
        void given_AnAVLTree_When_InsertingARightLeftNode_Then_TreeHeightAndBalanceAreModified() {
            AvlNode<Integer> node1, node2, node3;

            node1 = new AvlNode<Integer>(7);
            avlTree.insertAvlNode(node1);

            node2 = new AvlNode<Integer>(9);
            avlTree.insertAvlNode(node2);

            node3 = new AvlNode<Integer>(8);
            avlTree.insertAvlNode(node3);

            assertEquals(node3, avlTree.getTop());
            assertEquals(node1, node3.getLeft());
            assertEquals(node2, node3.getRight());

            assertEquals(1, avlTree.getTop().getHeight());
            assertEquals(0, avlTree.getTop().getLeft().getHeight());
            assertEquals(0, avlTree.getTop().getRight().getHeight());
            assertEquals(-1, avlTree.height(node2.getLeft()));
            assertEquals(-1, avlTree.height(node2.getRight()));
            assertEquals(-1, avlTree.height(node1.getLeft()));
            assertEquals(-1, avlTree.height(node1.getRight()));
        }
    }

    @DisplayName("Given an AVL Tree when searching for an element then returns the node with that element")
    @Test
    void given_AnAVLTree_When_SearchingForAnElement_Then_ReturnsTheNodeWithThatElement() {
        AvlNode<Integer> node1, node2, node3, node4, node5;

        node1 = new AvlNode<>(7);
        avlTree.insertAvlNode(node1);

        node2 = new AvlNode<>(9);
        avlTree.insertAvlNode(node2);

        node3 = new AvlNode<>(8);
        avlTree.insertAvlNode(node3);

        node4 = new AvlNode<>(2);
        avlTree.insertAvlNode(node4);

        node5 = new AvlNode<>(3);
        avlTree.insertAvlNode(node5);

        assertEquals(node1, avlTree.search(7));
        assertEquals(node2, avlTree.search(9));
        assertEquals(node3, avlTree.search(8));
        assertEquals((Integer) 2, avlTree.searchNode(new AvlNode<>(2)).getItem());
        assertEquals(node4, avlTree.search(2));
        assertEquals(node5, avlTree.search(3));
        assertNull(avlTree.search(14));
        assertNull(avlTree.search(0));
    }

    @DisplayName("Given an AVL Tree when finding a successor of a node then returns the succesor node")
    @Test
    void given_AnAVLTree_When_FindingSuccessorOfANode_Then_ReturnsTheSuccessorNode() {
        AvlNode<Integer> node;

        node = new AvlNode<>(20);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(8);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(22);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(4);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(12);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(24);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(10);
        avlTree.insertAvlNode(node);

        node = new AvlNode<>(14);
        avlTree.insertAvlNode(node);

        node = avlTree.search(8);
        assertEquals(avlTree.search(10), avlTree.findSuccessor(node));
        node = avlTree.search(10);
        assertEquals(avlTree.search(12), avlTree.findSuccessor(node));
        node = avlTree.search(14);
        assertEquals(avlTree.search(20), avlTree.findSuccessor(node));
    }

    @Test
    @DisplayName("when deleting a leaf node, the node is not present any more")
    void testDeletingLeafNodes() {
        AvlNode<Integer> node1, node2, node3, node4, node5;

        node1 = new AvlNode<>(7);
        avlTree.insertAvlNode(node1);

        node2 = new AvlNode<>(9);
        avlTree.insertAvlNode(node2);

        node3 = new AvlNode<>(2);
        avlTree.insertAvlNode(node3);

        node4 = new AvlNode<>(8);
        avlTree.insertAvlNode(node4);

        node5 = new AvlNode<>(3);
        avlTree.insertAvlNode(node5);

        avlTree.delete(3);
        assertEquals(null, node3.getRight());
        assertEquals(0, node3.getHeight());
        assertEquals(2, avlTree.getTop().getHeight());
    }

    @Test
    @DisplayName("when deleting a node with one leaf, then the leaf becomes the parent")
    void given_AvlTreeWith5Nodes_When_DeletingANodeWithALeaf_TheLeafBecomesTheParent() {
        AvlNode<Integer> node1, node2, node3, node4, node5;
        node1 = new AvlNode<>(7);
        avlTree.insertAvlNode(node1);
        node2 = new AvlNode<>(9);
        avlTree.insertAvlNode(node2);
        node3 = new AvlNode<>(2);
        avlTree.insertAvlNode(node3);
        node4 = new AvlNode<>(8);
        avlTree.insertAvlNode(node4);
        node5 = new AvlNode<>(3);
        avlTree.insertAvlNode(node5);

        avlTree.delete(2);

        assertEquals(null, node3.getRight());
        assertEquals(0, node3.getHeight());
        assertEquals(2, avlTree.getTop().getHeight());
    }

    @Test
    @DisplayName("when deleting a node with two leaves, the left leaf substitutes the node")
    void testDeletingNodesWithTwoLeaves() {
        AvlNode<Integer> node;
        node = new AvlNode<>(20);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(8);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(22);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(4);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(12);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(24);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(10);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(14);
        avlTree.insertAvlNode(node);

        avlTree.delete(12);

        node = avlTree.search(8);
        int expectedItem = 14;
        int actualItem = node.getRight().getItem();
        assertEquals(expectedItem, actualItem);
    }

    @Test
    @DisplayName("when deleting the node of the top, the new top is the successor of the root and the rest is the same")
    void given_AVLTreeWith8Nodes_WhenTheTopIsDeleted_Then_TheNewTopIsTheSuccessorOfTheRoot() {
        AvlNode<Integer> node;
        node = new AvlNode<>(20);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(8);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(22);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(4);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(12);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(24);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(10);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(14);
        avlTree.insertAvlNode(node);

        avlTree.delete(22);

        int expectedItem = 12;
        int actualItem = avlTree.getTop().getItem();
        assertEquals(expectedItem, actualItem);

        AvlNode<Integer> expectedNode = avlTree.search(8);
        AvlNode<Integer> actualNode = avlTree.getTop().getLeft();
        assertEquals(expectedNode, actualNode);

        AvlNode<Integer> expectedNode2 = avlTree.search(20);
        AvlNode<Integer> actualNode2 = avlTree.getTop().getRight();
        assertEquals(expectedNode2, actualNode2);
    }

    @Test
    @DisplayName("when deleting the node of the top, the string representation is updated")
    void given_AVLTreeWith8Nodes_When_1NodeIsDeleted_Then_ToStringMethodDoesNotShowTheNode() {
        AvlNode<Integer> node;
        node = new AvlNode<>(20);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(8);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(22);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(4);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(12);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(24);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(10);
        avlTree.insertAvlNode(node);
        node = new AvlNode<>(14);
        avlTree.insertAvlNode(node);

        avlTree.delete(20);

        assertEquals(" | 12 | 8 | 4 | 10 | 22 | 14 | 24", avlTree.toString());
    }

    @Test
    @DisplayName("New element inserted correctly into the tree")
    void give_empty_tree_when_insert_is_called_then_item_inserted_correctly() {
        Integer item = 5;

        avlTree.insert(item);

        AvlNode<Integer> expectedValue = new AvlNode<>(item);
        AvlNode<Integer> actualValue = avlTree.search(item);
        assertEquals(0, avlTree.compareNodes(expectedValue, actualValue));
    }

    @Test
    @DisplayName("Search for element in empty tree returns null")
    void given_empty_tree_when_search_for_element_ther_returns_null() {
        Integer item = 5;

        AvlNode<Integer> expectedValue = avlTree.search(item);

        assertTrue(avlTree.avlIsEmpty());
        assertNull(expectedValue);
    }

    @Test
    @DisplayName("Inserting item that is already exist does nothing")
    void given_tree_with_element_when_insert_same_element_then_nothing_is_inserted(){
        Integer item = 1;
        avlTree.insert(item);
        int currentHeight = avlTree.height(avlTree.getTop());

        avlTree.insert(item);
        int heightAfterInsert = avlTree.height(avlTree.getTop());

        assertEquals(currentHeight, heightAfterInsert);
    }

    @Test
    @DisplayName("Eliminate the node that does not exists does nothing")
    void given_tree_with_no_element_when_delete_the_element_then_nothing_is_removed(){
        Integer item = 1;
        avlTree.insert(item);
        int currentHeight = avlTree.height(avlTree.getTop());

        Integer otherItem = 2;
        avlTree.delete(otherItem);
        int heightAfterDelete = avlTree.height(avlTree.getTop());

        assertEquals(currentHeight, heightAfterDelete);
    }

    @Test
    @DisplayName("find successor returns null when passed node with only left child")
    void given_a_node_with_only_left_child_when_find_successor_called_then_returns_null(){
        Integer topItem = 2;
        Integer leftChild = 1;
        avlTree.insert(topItem);
        avlTree.insert(leftChild);

        AvlNode<Integer> topNode = avlTree.getTop();
        assertNull(avlTree.findSuccessor(topNode));
    }

}
