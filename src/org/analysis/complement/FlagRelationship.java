package org.analysis.complement;

import org.analysis.concrete_execution.ConcreteValueRegister;
import org.analysis.concrete_execution.ConcreteValueRegisterPart;
import org.analysis.formula.LongValueOld;
import org.analysis.formula.Value;
import org.analysis.symbolic_execution.SymbolValueRegister;
import org.analysis.symbolic_execution.SymbolValueRegisterPart;

public class FlagRelationship {

	// This function is about to change the Register due to the change of
	// Register Part
	public static void partChangeTotal(ConcreteValueRegisterPart part, ConcreteValueRegister total, String registerPart) {
		long p = part.getRegVal(registerPart);

		if (registerPart.equals("%bp")) {
			long t = total.getRegVal("%ebp");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%ebp", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%sp")) {
			long t = total.getRegVal("%esp");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%esp", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%di")) {
			long t = total.getRegVal("%edi");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%edi", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%si")) {
			long t = total.getRegVal("%esi");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%esi", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%ax")) {
			long t = total.getRegVal("%eax");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%eax", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%ah")) {
			long t = total.getRegVal("%eax");
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.movS("%eax", (long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
		} else if (registerPart.equals("%al")) {
			long t = total.getRegVal("%eax");
			long t1 = (long) (t / Math.pow(2, 8));
			total.movS("%eax", (long) (t1 * Math.pow(2, 8) + p));
		} else if (registerPart.equals("%bx")) {
			long t = total.getRegVal("%ebx");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%ebx", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%bh")) {
			long t = total.getRegVal("%ebx");
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.movS("%ebx", (long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
		} else if (registerPart.equals("%bl")) {
			long t = total.getRegVal("%ebx");
			long t1 = (long) (t / Math.pow(2, 8));
			total.movS("%ebx", (long) (t1 * Math.pow(2, 8) + p));
		} else if (registerPart.equals("%cx")) {
			long t = total.getRegVal("%ecx");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%ecx", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%ch")) {
			long t = total.getRegVal("%ecx");
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.movS("%ecx", (long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
		} else if (registerPart.equals("%cl")) {
			long t = total.getRegVal("%ecx");
			long t1 = (long) (t / Math.pow(2, 8));
			total.movS("%ecx", (long) (t1 * Math.pow(2, 8) + p));
		} else if (registerPart.equals("%dx")) {
			long t = total.getRegVal("%edx");
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%edx", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%dh")) {
			long t = total.getRegVal("%edx");
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.movS("%edx", (long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
		} else if (registerPart.equals("%dl")) {
			long t = total.getRegVal("%edx");
			long t1 = (long) (t / Math.pow(2, 8));
			total.movS("%edx", (long) (t1 * Math.pow(2, 8) + p));
		}
	}

	// This function is about to change the Register due to the change of
	// Register Part
	public static void totalChangePart(ConcreteValueRegisterPart part, ConcreteValueRegister total, String register) {
		long t = total.getRegVal(register);

		if (register.equals("%esi")) {
			long si = (long) (t % (long) Math.pow(2, 16));

			part.setRegVal("%si", si);
		} else if (register.equals("%edi")) {
			long di = (long) (t % (long) Math.pow(2, 16));

			part.setRegVal("%di", di);
		} else if (register.equals("%ebp")) {
			long bp = (long) (t % (long) Math.pow(2, 16));

			part.setRegVal("%bp", bp);
		} else if (register.equals("%esp")) {
			long sp = (long) (t % (long) Math.pow(2, 16));

			part.setRegVal("%sp", sp);
		} else if (register.equals("%eax")) {
			long ax = (long) (t % (long) Math.pow(2, 16));
			long ah = (long) (ax / (long) Math.pow(2, 8));
			long al = (long) (ax % (long) Math.pow(2, 8));

			part.setRegVal("%ax", ax);
			part.setRegVal("%ah", ah);
			part.setRegVal("%al", al);
		} else if (register.equals("%ebx")) {
			long bx = (long) (t % (long) Math.pow(2, 16));
			long bh = (long) (bx / (long) Math.pow(2, 8));
			long bl = (long) (bx % (long) Math.pow(2, 8));

			part.setRegVal("%bx", bx);
			part.setRegVal("%bh", bh);
			part.setRegVal("%bl", bl);
		} else if (register.equals("%ecx")) {
			long cx = (long) (t % (long) Math.pow(2, 16));
			long ch = (long) (cx / (long) Math.pow(2, 8));
			long cl = (long) (cx % (long) Math.pow(2, 8));

			part.setRegVal("%cx", cx);
			part.setRegVal("%ch", ch);
			part.setRegVal("%cl", cl);
		} else if (register.equals("%edx")) {
			long dx = (long) (t % (long) Math.pow(2, 16));
			long dh = (long) (dx / (long) Math.pow(2, 8));
			long dl = (long) (dx % (long) Math.pow(2, 8));

			part.setRegVal("%dx", dx);
			part.setRegVal("%dh", dh);
			part.setRegVal("%dl", dl);
		}
	}

	public static void partChangeTotal(SymbolValueRegisterPart part, SymbolValueRegister total, String registerPart) {
		Value x = part.getRegVal(registerPart);
		// Exp t = total.getRegVal(registerPart);

		if (!(x instanceof LongValueOld))
			return;

		long p = ((LongValueOld) x).getValue();

		if (registerPart.equals("%bp")) {
			Value y = total.getRegVal("%ebp");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%ebp", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%sp")) {
			Value y = total.getRegVal("%esp");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%esp", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%di")) {
			Value y = total.getRegVal("%edi");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%edi", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%si")) {
			Value y = total.getRegVal("%esi");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%esi", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%ax")) {
			Value y = total.getRegVal("%eax");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%eax", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%ah")) {
			Value y = total.getRegVal("%eax");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.mov("%eax", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%al")) {
			Value y = total.getRegVal("%eax");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 8));
			total.mov("%eax", new LongValueOld((long) (t1 * Math.pow(2, 8) + p)));
		} else if (registerPart.equals("%bx")) {
			Value y = total.getRegVal("%ebx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%ebx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%bh")) {
			Value y = total.getRegVal("%ebx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.mov("%ebx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%bl")) {
			Value y = total.getRegVal("%ebx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 8));
			total.mov("%ebx", new LongValueOld((long) (t1 * Math.pow(2, 8) + p)));
		} else if (registerPart.equals("%cx")) {
			Value y = total.getRegVal("%ecx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%ecx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%ch")) {
			Value y = total.getRegVal("%ecx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.mov("%ecx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%cl")) {
			Value y = total.getRegVal("%ecx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 8));
			total.mov("%ecx", new LongValueOld((long) (t1 * Math.pow(2, 8) + p)));
		} else if (registerPart.equals("%dx")) {
			Value y = total.getRegVal("%edx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.mov("%edx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%dh")) {
			Value y = total.getRegVal("%edx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			total.mov("%edx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%dl")) {
			Value y = total.getRegVal("%edx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 8));
			total.mov("%edx", new LongValueOld((long) (t1 * Math.pow(2, 8) + p)));
		}

	}

	public static void totalChangePart(SymbolValueRegisterPart part, SymbolValueRegister total, String register) {
		// TODO Auto-generated method stub
		Value x = total.getRegVal(register);
		// Exp t = total.getRegVal(registerPart);

		if (!(x instanceof LongValueOld))
			return;

		long t = ((LongValueOld) x).getValue();

		if (register.equals("%esi")) {
			long si = (long) (t % (long) Math.pow(2, 16));

			part.mov("%si", new LongValueOld(si));
		} else if (register.equals("%edi")) {
			long di = (long) (t % (long) Math.pow(2, 16));

			part.mov("%di", new LongValueOld(di));
		} else if (register.equals("%ebp")) {
			long bp = (long) (t % (long) Math.pow(2, 16));

			part.mov("%bp", new LongValueOld(bp));
		} else if (register.equals("%esp")) {
			long sp = (long) (t % (long) Math.pow(2, 16));

			part.mov("%sp", new LongValueOld(sp));
		} else if (register.equals("%eax")) {
			long ax = (long) (t % (long) Math.pow(2, 16));
			long ah = (long) (ax / (long) Math.pow(2, 8));
			long al = (long) (ax % (long) Math.pow(2, 8));

			part.mov("%ax", new LongValueOld(ax));
			part.mov("%ah", new LongValueOld(ah));
			part.mov("%al", new LongValueOld(al));
		} else if (register.equals("%ebx")) {
			long bx = (long) (t % (long) Math.pow(2, 16));
			long bh = (long) (bx / (long) Math.pow(2, 8));
			long bl = (long) (bx % (long) Math.pow(2, 8));

			part.mov("%bx", new LongValueOld(bx));
			part.mov("%bh", new LongValueOld(bh));
			part.mov("%bl", new LongValueOld(bl));
		} else if (register.equals("%ecx")) {
			long cx = (long) (t % (long) Math.pow(2, 16));
			long ch = (long) (cx / (long) Math.pow(2, 8));
			long cl = (long) (cx % (long) Math.pow(2, 8));

			part.mov("%cx", new LongValueOld(cx));
			part.mov("%ch", new LongValueOld(ch));
			part.mov("%cl", new LongValueOld(cl));
		} else if (register.equals("%edx")) {
			long dx = (long) (t % (long) Math.pow(2, 16));
			long dh = (long) (dx / (long) Math.pow(2, 8));
			long dl = (long) (dx % (long) Math.pow(2, 8));

			part.mov("%dx", new LongValueOld(dx));
			part.mov("%dh", new LongValueOld(dh));
			part.mov("%dl", new LongValueOld(dl));
		}

	}
}
