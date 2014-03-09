package others;

import java.util.ArrayList;

class BinaryTreeNode {
	private BinaryTreeNode leftSon = null;
	private BinaryTreeNode rightSon = null;
	private BinaryTreeNode parent = null;
	private double data = 0;
	private int sign = -1;

	public int getSign() {
		return sign;
	}

	public void setSign(int sign) {
		this.sign = sign;
	}

	public BinaryTreeNode(BinaryTreeNode parent, BinaryTreeNode leftSon,
			BinaryTreeNode rightSon) {
		this.parent = parent;
		this.leftSon = leftSon;
		this.rightSon = rightSon;
	}

	public BinaryTreeNode() {
	}

	public BinaryTreeNode getLeftSon() {
		return leftSon;
	}

	public void setLeftSon(BinaryTreeNode leftSon) {
		this.leftSon = leftSon;
		leftSon.setParent(this);
	}

	public BinaryTreeNode getParent() {
		return parent;
	}

	public void setParent(BinaryTreeNode parent) {
		this.parent = parent;
	}

	public BinaryTreeNode getRightSon() {
		return rightSon;
	}

	public void setRightSon(BinaryTreeNode rightSon) {
		this.rightSon = rightSon;
		rightSon.setParent(this);
	}

	public boolean isLeaf() {
		return (this.leftSon == null && this.rightSon == null);
	}

	public boolean isRoot() {
		return this.parent == null;
	}

	public double getData() {
		return data;
	}

	public void setData(double data) {
		this.data = data;
	}
}

public class ComputeTwentyFour {
	private ArrayList<String> expressions = new ArrayList<String>();

	public void solute(ArrayList<BinaryTreeNode> nodes, double target) {
		// whether the root data equals target
		if (nodes.size() == 1) {
			if (nodes.get(0).getData() == target) {
				String expression = printBinaryTree(nodes.get(0));
				addExpression(expression);

				return;
			}
		}

		for (int i = 0; i < nodes.size(); i++) {
			for (int j = 0; j < nodes.size(); j++) {
				if (i == j) {
					continue;
				}

				for (int k = 0; k < 4; k++) {
					BinaryTreeNode node = new BinaryTreeNode();
					BinaryTreeNode leftSon = nodes.get(i);
					BinaryTreeNode rightSon = nodes.get(j);

					if (k == 0) {
						node.setData(leftSon.getData() + rightSon.getData());
					} else if (k == 1) {
						node.setData(leftSon.getData() - rightSon.getData());
					} else if (k == 2) {
						node.setData(leftSon.getData() * rightSon.getData());
					} else if (k == 3) {
						if (rightSon.getData() == 0) {
							continue;
						}
						node.setData(leftSon.getData() / rightSon.getData());
					}

					node.setLeftSon(leftSon);
					node.setRightSon(rightSon);
					node.setSign(k);

					ArrayList<BinaryTreeNode> clonedArrayList = cloneArrayList(nodes);
					// remove nodes from the tree
					clonedArrayList.remove(leftSon);
					clonedArrayList.remove(rightSon);
					clonedArrayList.add(node);

					solute(clonedArrayList, target);
				}
			}
		}
	}

	public void printResult() {
		for (int i = 0; i < expressions.size(); i++) {
			//System.out.println("Solution " + i + ": " + expressions.get(i));
		}
	}

	private void addExpression(String expression) {

		if (expressions.contains(expression)) {
			return;
		}

		expressions.add(expression);
	}

	private ArrayList<BinaryTreeNode> cloneArrayList(
			ArrayList<BinaryTreeNode> source) {
		ArrayList<BinaryTreeNode> result = new ArrayList<BinaryTreeNode>();
		for (int i = 0; i < source.size(); i++) {
			result.add(source.get(i));
		}

		return result;
	}

	private String printBinaryTree(BinaryTreeNode resultRoot) {
		if (resultRoot.isLeaf()) {
			return doubleToString(resultRoot.getData());
		} else {
			String expression = "(";

			expression += printBinaryTree(resultRoot.getLeftSon());

			int sign = resultRoot.getSign();
			if (sign == 0) {
				expression += "+";
			} else if (sign == 1) {
				expression += "-";
			} else if (sign == 2) {
				expression += "*";
			} else if (sign == 3) {
				expression += "/";
			}

			expression += printBinaryTree(resultRoot.getRightSon());

			expression += ")";

			return expression;
		}
	}

	private String doubleToString(double value) {
		int intValue = (int) value;
		if (value == intValue) {
			return String.valueOf(intValue);
		} else {
			return String.valueOf(value);
		}
	}

	public BinaryTreeNode buildBinaryTreeNode(double value) {
		BinaryTreeNode node = new BinaryTreeNode();
		node.setData(value);

		return node;
	}

	public static void main(String[] args) {
		ComputeTwentyFour cardGame = new ComputeTwentyFour();

		ArrayList<BinaryTreeNode> nodes = new ArrayList<BinaryTreeNode>();
		nodes.add(cardGame.buildBinaryTreeNode(4));
		nodes.add(cardGame.buildBinaryTreeNode(7));
		nodes.add(cardGame.buildBinaryTreeNode(8));
		nodes.add(cardGame.buildBinaryTreeNode(10));

		cardGame.solute(nodes, 24);
		cardGame.printResult();
	}
}