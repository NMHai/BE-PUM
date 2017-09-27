/*
 * Characters.java - This file is part of the Jakstab project.
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
package org.jakstab.util;

/**
 * Wrapper class for switching between Unicode and ASCII-art mathematical
 * symbols.
 * 
 * @author Johannes Kinder
 */
public final class Characters {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(Characters.class);

	public static final String NEWLINE = System.getProperty("line.separator");

	public static String TOP = "TOP";
	public static String BOT = "BOT";

	public static final String DOUBLE_LINE_FULL_WIDTH = "===============================================================================";
	public static final String LINE_FULL_WIDTH = "-------------------------------------------------------------------------------";

	public static void useUnicode() {
		TOP = "\u22A4";
		BOT = "\u22A5";
	}

	public static String starredBox(String s) {
		StringBuffer res = new StringBuffer((s.length() + 6) * 3);
		for (int i = 0; i < (s.length() + 4); i++) {
			res.append("*");
		}
		res.append(NEWLINE);
		res.append("* ");
		res.append(s);
		res.append(" *");
		res.append(NEWLINE);
		for (int i = 0; i < (s.length() + 4); i++) {
			res.append("*");
		}
		return res.toString();
	}
}
