/*
 * ExpressionVisitor.java - This file is part of the Jakstab project.
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
package org.jakstab.rtl.expressions;

/**
 * A visitor interface for RTLExpressions with support for return values whose
 * type is specified by the type parameter.
 * 
 * @author Johannes Kinder
 * @param <T>
 *            the return type of the visitor.
 */
public interface ExpressionVisitor<T> {

	T visit(RTLBitRange e);

	T visit(RTLConditionalExpression e);

	T visit(RTLMemoryLocation e);

	T visit(RTLNondet e);

	T visit(RTLNumber e);

	T visit(RTLOperation e);

	T visit(RTLSpecialExpression e);

	T visit(RTLVariable e);

}
