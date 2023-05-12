//  AvlTree.java
//
//  Author:
//       Antonio J. Nebro <antonio@lcc.uma.es>
//       Juan J. Durillo <durillo@lcc.uma.es>
//
//  Copyright (c) 2011 Antonio J. Nebro, Juan J. Durillo
//
//  This program is free software: you can redistribute it and/or modify
//  it under the terms of the GNU Lesser General Public License as published by
//  the Free Software Foundation, either version 3 of the License, or
//  (at your option) any later version.
//
//  This program is distributed in the hope that it will be useful,
//  but WITHOUT ANY WARRANTY; without even the implied warranty of
//  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//  GNU Lesser General Public License for more details.
//
//  You should have received a copy of the GNU Lesser General Public License
//  along with this program.  If not, see <http://www.gnu.org/licenses/>.
//

package avl;

import java.util.Comparator;

/**
 * Created with IntelliJ IDEA. User: Antonio J. Nebro Date: 08/07/13 Time: 15:51 Class implementing
 * Avl trees.
 */
public class AvlTree<T> {

  AvlNode<T> top;
  Comparator<T> comparator;

  /**
   * Constructor
   *
   * @param comparator comparator of the items
   */
  public AvlTree(Comparator<T> comparator) {
    top = null;
    this.comparator = comparator;
  }

  public void insert(T item) {
    AvlNode<T> node = new AvlNode<>(item);
    insertAvlNode(node);
  }

  public void insertAvlNode(AvlNode<T> node) {
    if (avlIsEmpty()) {
      insertTop(node);
    } else if (searchClosestNode(node) == -1) {
      insertNodeLeft(node);
    } else if (searchClosestNode(node) == +1) {
      insertNodeRight(node);
    }
  }

  public AvlNode<T> search(T item) {
    AvlNode<T> node = new AvlNode<>(item);
    return searchNode(node);
  }

  public AvlNode<T> searchNode(AvlNode<T> targetNode) {
    AvlNode<T> currentNode;
    AvlNode<T> result;

    currentNode = top;
    if (top == null) {
      result = null;
    } else {
      result = getAvlNode(targetNode, currentNode);
    }
    return result;
  }

  private AvlNode<T> getAvlNode(AvlNode<T> targetNode, AvlNode<T> currentNode) {
    AvlNode<T> result;
    boolean searchFinished;
    int comparison;

    searchFinished = false;
    result = null;
    while (!searchFinished) {
      comparison = compareNodes(targetNode, currentNode);
      if (comparison < 0) {
        if (currentNode.getLeft() != null) {
          currentNode = currentNode.getLeft();
        } else {
          searchFinished = true;
        }
      } else if (comparison > 0) {
        if (currentNode.getRight() != null) {
          currentNode = currentNode.getRight();
        } else {
          searchFinished = true;
        }
      } else {
        searchFinished = true;
        result = currentNode;
      }
    }
    return result;
  }

  public void delete(T item) {
    deleteNode(new AvlNode<>(item));
  }

  public void deleteNode(AvlNode<T> node) {
    AvlNode<T> nodeFound;

    nodeFound = searchNode(node);
    if (nodeFound != null) {
      if (nodeFound.isLeaf()) {
        deleteLeafNode(nodeFound);
      } else if (nodeFound.hasOnlyALeftChild()) {
        deleteNodeWithALeftChild(nodeFound);
      } else if (nodeFound.hasOnlyARightChild()) {
        deleteNodeWithARightChild(nodeFound);
      } else {
        deleteNodeWithTwoChildren(nodeFound);
      }
    }
  }

  private void deleteNodeWithTwoChildren(AvlNode<T> nodeFound) {
    AvlNode<T> successor = findSuccessor(nodeFound);
    T tmp = successor.getItem();
    successor.setItem(nodeFound.getItem());
    nodeFound.setItem(tmp);
    if (successor.isLeaf()) {
      deleteLeafNode(successor);
    } else if (successor.hasOnlyALeftChild()) {
      deleteNodeWithALeftChild(successor);
    } else if (successor.hasOnlyARightChild()) {
      deleteNodeWithARightChild(successor);
    }
  }

  public void deleteLeafNode(AvlNode<T> node) {
    if (!node.hasParent()) {
      top = null;
    } else {
      if (node.getParent().getLeft() == node) {
        node.getParent().setLeft(null);
      } else {
        node.getParent().setRight(null);
      }
      node.getParent().updateHeight();
      rebalance(node.getParent());
    }
  }

  public void deleteNodeWithALeftChild(AvlNode<T> node) {
    node.setItem(node.getLeft().getItem());
    node.setLeft(null);
    node.updateHeight();
    rebalance(node);
  }

  public void deleteNodeWithARightChild(AvlNode<T> node) {
    node.setItem(node.getRight().getItem());
    node.setRight(null);
    node.updateHeight();
    rebalance(node);
  }

  /**
   * Searches for the closest node of the node passed as argument
   *
   * @param node node whose closest node is going to be searched
   * @return -1 if node has to be inserted in the left, +1 if it must be inserted in the right, 0
   * otherwise
   */
  public int searchClosestNode(AvlNode<T> node) {
    AvlNode<T> currentNode;

    currentNode = top;
    if (top == null) {
      return 0;
    }
    return getClosestNodeResult(node, currentNode);
  }

  private int getClosestNodeResult(AvlNode<T> node, AvlNode<T> currentNode) {
    int comparison;
    int result;
    boolean notFound;

    notFound = true;
    result = 0;
    while (notFound) {
      comparison = compareNodes(node, currentNode);
      if (comparison < 0) {
        if (currentNode.hasLeft()) {
          currentNode = currentNode.getLeft();
        } else {
          notFound = false;
          node.setClosestNode(currentNode);
          result = -1;
        }
      } else if (comparison > 0) {
        if (currentNode.hasRight()) {
          currentNode = currentNode.getRight();
        } else {
          notFound = false;
          node.setClosestNode(currentNode);
          result = 1;
        }
      } else {
        notFound = false;
        node.setClosestNode(currentNode);
      }
    }
    return result;
  }

  public AvlNode<T> findSuccessor(AvlNode<T> node) {
    AvlNode<T> result;

    if (node.hasRight()) {
      AvlNode<T> tmp = node.getRight();
      while (tmp.hasLeft()) {
        tmp = tmp.getLeft();
      }
      result = tmp;
    } else {
      while (node.hasParent() && (node.getParent().getRight() == node)) {
        node = node.getParent();
      }
      result = node.getParent();
    }
    return result;
  }

  /**
   * Insert node in the left of its nearest node
   *
   * @param node REQUIRES: a previous call to searchClosestNode(node)
   */
  public void insertNodeLeft(AvlNode<T> node) {
    node.getClosestNode().setLeft(node);
    node.setParent(node.getClosestNode());
    rebalance(node);
  }

  /**
   * Insert node in the right of its nearest node
   *
   * @param node REQUIRES: a previous call to searchClosestNode(node)
   */
  public void insertNodeRight(AvlNode<T> node) {
    node.getClosestNode().setRight(node);
    node.setParent(node.getClosestNode());
    rebalance(node);
  }

  /**
   * Comparator
   *
   * @param node1 the first node to be compared
   * @param node2 the second node to be compared
   * @return The experiment output of the comparison according to the comparators
   */
  public int compareNodes(AvlNode<T> node1, AvlNode<T> node2) {
    return comparator.compare(node1.getItem(), node2.getItem());
  }

  public void rebalance(AvlNode<T> node) {
    AvlNode<T> currentNode;
    boolean notFinished;

    currentNode = node;
    notFinished = true;
    while (notFinished) {
      balanceLeftRotation(currentNode);
      balanceRightRotation(currentNode);
      if (currentNode.hasParent()) {
        currentNode.getParent().updateHeight();
        currentNode = currentNode.getParent();
      } else {
        setTop(currentNode);
        notFinished = false;
      }
    }
  }

  private void balanceRightRotation(AvlNode<T> currentNode) {
    if (getBalance(currentNode) == 2) {
      if (height(currentNode.getRight().getRight()) >= height(currentNode.getRight().getLeft())) {
        rightRotation(currentNode);
      } else {
        doubleRightRotation(currentNode);
      }
    }
  }

  private void balanceLeftRotation(AvlNode<T> currentNode) {
    if (getBalance(currentNode) == -2) {
      if (height(currentNode.getLeft().getLeft()) >= height(currentNode.getLeft().getRight())) {
        leftRotation(currentNode);
      } else {
        doubleLeftRotation(currentNode);
      }
    }
  }

  public void leftRotation(AvlNode<T> node) {
    AvlNode<T> leftNode = node.getLeft();

    if (node.hasParent()) {
      leftNode.setParent(node.getParent());
      if (node.getParent().getLeft() == node) {
        node.getParent().setLeft(leftNode);
      } else {
        node.getParent().setRight(leftNode);
      }
    } else {
      setTop(leftNode);
    }

    node.setLeft(node.getLeft().getRight());
    leftNode.setRight(node);
    node.setParent(leftNode);

    node.updateHeight();
    leftNode.updateHeight();
  }

  public void rightRotation(AvlNode<T> node) {
    AvlNode<T> rightNode = node.getRight();

    if (node.hasParent()) {
      rightNode.setParent(node.getParent());
      if (node.getParent().getRight() == node) {
        node.getParent().setRight(rightNode);
      } else {
        node.getParent().setLeft(rightNode);
      }
    } else {
      setTop(rightNode);
    }

    node.setRight(node.getRight().getLeft());
    rightNode.setLeft(node);
    node.setParent(rightNode);

    node.updateHeight();
    rightNode.updateHeight();
  }

  public void doubleLeftRotation(AvlNode<T> node) {
    AvlNode<T> leftNode = node.getLeft();

    rightRotation(leftNode);
    leftRotation(node);
  }

  public void doubleRightRotation(AvlNode<T> node) {
    AvlNode<T> rightNode = node.getRight();

    leftRotation(rightNode);
    rightRotation(node);
  }

  public int getBalance(AvlNode<T> node) {
    int leftHeight;
    int rightHeight;

    if (node.hasLeft()) {
      leftHeight = node.getLeft().getHeight();
    } else {
      leftHeight = -1;
    }
    if (node.hasRight()) {
      rightHeight = node.getRight().getHeight();
    } else {
      rightHeight = -1;
    }

    return rightHeight - leftHeight;
  }

  public boolean avlIsEmpty() {
    return top == null;
  }

  public void insertTop(AvlNode<T> node) {
    top = node;
  }


  public AvlNode<T> getTop() {
    return top;
  }

  public void setTop(AvlNode<T> top) {
    this.top = top;
    this.top.setParent(null);
  }

  public int height(AvlNode<T> node) {
    int result = 0;
    if (node == null) {
      result = -1;
    } else {
      result = node.getHeight();
    }

    return result;
  }

  public String toString() {
    String result;

    result = inOrder(top);

    return result;
  }

  private String inOrder(AvlNode<T> node) {
    String result;
    if (node == null) {
      return "";
    } else {
      result = " | " + node.getItem();
      result += inOrder(node.getLeft());
      result += inOrder(node.getRight());
      return result;
    }
  }
}
