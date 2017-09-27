package org.analysis.cfg;

import java.util.ArrayList;
import java.util.List;

public class Condition {
	private List<String> leftC;
	private List<String> rightC;
	private List<String> connector;

	public Condition() {
		leftC = new ArrayList<String>();
		rightC = new ArrayList<String>();
		connector = new ArrayList<String>();
	}

	public boolean equal(Condition cond) {
		for (int index = 0; index < connector.size(); index++)
			if (leftC.get(index) != cond.getLeftC(index) || rightC.get(index) != cond.getRightC(index)
					|| connector.get(index) != cond.getConnector(index))
				return false;
		return true;
	}

	public String getLeftC(int index) {
		return leftC.get(index);
	}

	public Condition getTop() {
		Condition c = new Condition();
		// c.setLeftC(this.leftC.get(getLength() - 1));
		// c.setRightC(this.rightC.get(getLength() - 1));
		// c.setConnector(this.connector.get(getLength() - 1));
		return c;
	}

	public int getLength() {
		return leftC.size();
	}

	public void setLeftC(String leftC) {
		this.leftC.add(leftC);
	}

	public String getRightC(int index) {
		return rightC.get(index);
	}

	public void setRightC(String rightC) {
		this.rightC.add(rightC);
	}

	public String getConnector(int index) {
		return connector.get(index);
	}

	public void setConnector(String connector) {
		this.connector.add(connector);
	}

	public Condition clone() {
		Condition cond = new Condition();
		for (String l : this.leftC)
			cond.setLeftC(l);

		for (String r : this.rightC)
			cond.setRightC(r);

		for (String c : this.connector)
			cond.setConnector(c);
		return cond;
	}

	public void printInfo() {
		System.out.println("Condition:");
		for (int index = 0; index < connector.size(); index++) {
			System.out.println(leftC.get(index) + " " + connector.get(index) + " " + rightC.get(index));
			System.out
					.println("---------------------------------------------------------------------------------------------");
		}
	}

	public int getConnectorSize() {
		return connector.size();
	}
}
