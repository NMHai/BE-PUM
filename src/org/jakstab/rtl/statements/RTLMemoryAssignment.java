/*
 * RTLMemoryAssignment.java - This file is part of the Jakstab project.
 * Copyright 2007-2012 Johannes Kinder <jk@jakstab.org>
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, see <http://www.gnu.org/licenses/>.
 */

package org.jakstab.rtl.statements;

import org.jakstab.rtl.Context;
import org.jakstab.rtl.TypeInferenceException;
import org.jakstab.rtl.expressions.ExpressionSimplifier;
import org.jakstab.rtl.expressions.RTLExpression;
import org.jakstab.rtl.expressions.RTLMemoryLocation;
import org.jakstab.rtl.expressions.SetOfVariables;
import org.jakstab.ssl.Architecture;
import org.jakstab.util.FastSet;
import org.jakstab.util.Logger;

import java.util.Set;

/**
 * Assigns the value of the right-hand side to the memory specified on the
 * left-hand side.
 * 
 * @author Johannes Kinder
 */
public class RTLMemoryAssignment extends AbstractRTLStatement implements RTLStatement {

	private static final Logger logger = Logger.getLogger(RTLMemoryAssignment.class);

	private RTLMemoryLocation leftHandSide;
	private RTLExpression rightHandSide;

	public RTLMemoryAssignment(RTLMemoryLocation leftHandSide, RTLExpression rightHandSide) {
		super();
		this.leftHandSide = leftHandSide;
		this.rightHandSide = rightHandSide;
	}

	@Override
	public RTLStatement evaluate(Context context) {
		invalidateCache();
		RTLExpression evaldRHS = this.rightHandSide.evaluate(context);

		if (evaldRHS == null)
			logger.warn("No more RHS after evaluation of " + this.toString());

		ExpressionSimplifier simplifier = ExpressionSimplifier.getInstance();
		evaldRHS = simplifier.simplify(evaldRHS);

		// remove all killed assignments from the context
		context.removeAssignment(leftHandSide.getDefinedVariablesOnWrite());

		RTLExpression evaldLHS = this.leftHandSide.evaluate(context);

		if (evaldLHS.equals(evaldRHS)) {
			// logger.debug("Removed self-assignment: " + evaldLHS + " = " +
			// evaldRHS);
			return null;
		}
		rightHandSide = evaldRHS;
		if (evaldLHS instanceof RTLMemoryLocation) {
			leftHandSide = (RTLMemoryLocation) evaldLHS;
		} else {
			logger.error("Error: LHS of assignment no longer memory location after evaluation: "
					+ this.leftHandSide.toString() + " = " + evaldLHS.toString());
		}
		return this;
	}

	public int getBitWidth() {
		return leftHandSide.getBitWidth();
	}

	public RTLMemoryLocation getLeftHandSide() {
		return leftHandSide;
	}

	public RTLExpression getRightHandSide() {
		return rightHandSide;
	}

	@Override
	public void inferTypes(Architecture arch) throws TypeInferenceException {
		leftHandSide = (RTLMemoryLocation) (leftHandSide.inferBitWidth(arch, getBitWidth()));
		rightHandSide = rightHandSide.inferBitWidth(arch, getBitWidth());
	}

	@Override
	public String toString() {
		StringBuilder res = new StringBuilder(255);
		res.append(leftHandSide);
		res.append(" := ");
		res.append(rightHandSide);
		return res.toString();
	}

	@Override
	protected SetOfVariables initDefinedVariables() {
		return new SetOfVariables(leftHandSide.getDefinedVariablesOnWrite());
	}

	@Override
	protected SetOfVariables initUsedVariables() {
		SetOfVariables usedVariables = new SetOfVariables();
		usedVariables.addAll(leftHandSide.getUsedVariablesOnWrite());
		usedVariables.addAll(rightHandSide.getUsedVariables());
		return usedVariables;
	}

	@Override
	protected Set<RTLMemoryLocation> initUsedMemoryLocations() {
		Set<RTLMemoryLocation> usedMemory = new FastSet<RTLMemoryLocation>();
		usedMemory.addAll(leftHandSide.getUsedMemoryLocationsOnWrite());
		usedMemory.addAll(rightHandSide.getUsedMemoryLocations());
		return usedMemory;
	}

	@Override
	public <T> T accept(StatementVisitor<T> visitor) {
		return visitor.visit(this);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((leftHandSide == null) ? 0 : leftHandSide.hashCode());
		result = prime * result + ((rightHandSide == null) ? 0 : rightHandSide.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		RTLMemoryAssignment other = (RTLMemoryAssignment) obj;
		if (leftHandSide == null) {
			if (other.leftHandSide != null)
				return false;
		} else if (!leftHandSide.equals(other.leftHandSide))
			return false;
		if (rightHandSide == null) {
			if (other.rightHandSide != null)
				return false;
		} else if (!rightHandSide.equals(other.rightHandSide))
			return false;
		return true;
	}

}
