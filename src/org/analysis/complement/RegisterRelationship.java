package org.analysis.complement;

import org.analysis.concrete_execution.ConcreteValueRegister;
import org.analysis.concrete_execution.ConcreteValueRegisterPart;
import org.analysis.formula.LongValueOld;
import org.analysis.formula.Value;
import org.analysis.symbolic_execution.SymbolValueRegister;
import org.analysis.symbolic_execution.SymbolValueRegisterPart;

public class RegisterRelationship {

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
			long t2 = (long) (p / (long) Math.pow(2, 8));
			long t3 = (long) (p % (long) Math.pow(2, 8));

			part.movS("%ah", t2);
			part.movS("%al", t3);
			total.movS("%eax", (long) (t1 * Math.pow(2, 16) + p));
		} else if (registerPart.equals("%ah")) {
			long t = total.getRegVal("%eax");
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));
			part.movS("%ax", (long) (p * Math.pow(2, 8) + t3));
			total.movS("%eax", (long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3));
		} else if (registerPart.equals("%al")) {
			long t = total.getRegVal("%eax");
			long t1 = (long) (t / Math.pow(2, 8));
			long t2 = (long) (t % Math.pow(2, 16));
			long t3 = (long) (t2 / Math.pow(2, 8));

			part.movS("%ax", (long) (t3 * Math.pow(2, 8) + p));
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

			part.movS("%si", si);
		} else if (register.equals("%edi")) {
			long di = (long) (t % (long) Math.pow(2, 16));

			part.movS("%di", di);
		} else if (register.equals("%ebp")) {
			long bp = (long) (t % (long) Math.pow(2, 16));

			part.movS("%bp", bp);
		} else if (register.equals("%esp")) {
			long sp = (long) (t % (long) Math.pow(2, 16));

			part.movS("%sp", sp);
		} else if (register.equals("%eax")) {
			long ax = (long) (t % (long) Math.pow(2, 16));
			long ah = (long) (ax / (long) Math.pow(2, 8));
			long al = (long) (ax % (long) Math.pow(2, 8));

			part.movS("%ax", ax);
			part.movS("%ah", ah);
			part.movS("%al", al);
		} else if (register.equals("%ebx")) {
			long bx = (long) (t % (long) Math.pow(2, 16));
			long bh = (long) (bx / (long) Math.pow(2, 8));
			long bl = (long) (bx % (long) Math.pow(2, 8));

			part.movS("%bx", bx);
			part.movS("%bh", bh);
			part.movS("%bl", bl);
		} else if (register.equals("%ecx")) {
			long cx = (long) (t % (long) Math.pow(2, 16));
			long ch = (long) (cx / (long) Math.pow(2, 8));
			long cl = (long) (cx % (long) Math.pow(2, 8));

			part.movS("%cx", cx);
			part.movS("%ch", ch);
			part.movS("%cl", cl);
		} else if (register.equals("%edx")) {
			long dx = (long) (t % (long) Math.pow(2, 16));
			long dh = (long) (dx / (long) Math.pow(2, 8));
			long dl = (long) (dx % (long) Math.pow(2, 8));

			part.movS("%dx", dx);
			part.movS("%dh", dh);
			part.movS("%dl", dl);
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
			total.movS("%ebp", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%sp")) {
			Value y = total.getRegVal("%esp");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%esp", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%di")) {
			Value y = total.getRegVal("%edi");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%edi", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%si")) {
			Value y = total.getRegVal("%esi");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%esi", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%ax")) {
			// Modify value of AH and AL
			long h = (long) (p / (long) Math.pow(2, 8));
			long l = (long) (p % (long) Math.pow(2, 8));
			part.movS("%ah", new LongValueOld(h));
			part.movS("%al", new LongValueOld(l));

			// Modify value of EAX
			Value y = total.getRegVal("%eax");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%eax", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%ah")) {
			Value y = total.getRegVal("%eax");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));

			part.movS("%ax", new LongValueOld((long) (p * Math.pow(2, 8) + t3)));
			total.movS("%eax", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%al")) {
			Value y = total.getRegVal("%eax");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			// long t3 = (long) (t2 % (long) Math.pow(2, 8));
			part.movS("%ax", new LongValueOld((long) (t2 * Math.pow(2, 8) + p)));
			total.movS("%eax", new LongValueOld((long) (t1 * Math.pow(2, 16) + t2 * Math.pow(2, 8) + p)));
		} else if (registerPart.equals("%bx")) {
			// Modify value of AH and AL
			long h = (long) (p / (long) Math.pow(2, 8));
			long l = (long) (p % (long) Math.pow(2, 8));
			part.movS("%bh", new LongValueOld(h));
			part.movS("%bl", new LongValueOld(l));

			// Modify value of EAX
			Value y = total.getRegVal("%ebx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%ebx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%bh")) {
			Value y = total.getRegVal("%ebx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));

			part.movS("%bx", new LongValueOld((long) (p * Math.pow(2, 8) + t3)));
			total.movS("%ebx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%bl")) {
			Value y = total.getRegVal("%ebx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			// long t3 = (long) (t2 % (long) Math.pow(2, 8));
			part.movS("%bx", new LongValueOld((long) (t2 * Math.pow(2, 8) + p)));
			total.movS("%ebx", new LongValueOld((long) (t1 * Math.pow(2, 16) + t2 * Math.pow(2, 8) + p)));
		} else if (registerPart.equals("%cx")) {
			// Modify value of AH and AL
			long h = (long) (p / (long) Math.pow(2, 8));
			long l = (long) (p % (long) Math.pow(2, 8));
			part.movS("%ch", new LongValueOld(h));
			part.movS("%cl", new LongValueOld(l));

			// Modify value of EAX
			Value y = total.getRegVal("%ecx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%ecx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%ch")) {
			Value y = total.getRegVal("%ecx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));

			part.movS("%cx", new LongValueOld((long) (p * Math.pow(2, 8) + t3)));
			total.movS("%ecx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%cl")) {
			Value y = total.getRegVal("%ecx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			// long t3 = (long) (t2 % (long) Math.pow(2, 8));
			part.movS("%cx", new LongValueOld((long) (t2 * Math.pow(2, 8) + p)));
			total.movS("%ecx", new LongValueOld((long) (t1 * Math.pow(2, 16) + t2 * Math.pow(2, 8) + p)));
		} else if (registerPart.equals("%dx")) {
			// Modify value of AH and AL
			long h = (long) (p / (long) Math.pow(2, 8));
			long l = (long) (p % (long) Math.pow(2, 8));
			part.movS("%dh", new LongValueOld(h));
			part.movS("%dl", new LongValueOld(l));

			// Modify value of EAX
			Value y = total.getRegVal("%edx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			total.movS("%edx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p)));
		} else if (registerPart.equals("%dh")) {
			Value y = total.getRegVal("%edx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			long t3 = (long) (t2 % (long) Math.pow(2, 8));

			part.movS("%dx", new LongValueOld((long) (p * Math.pow(2, 8) + t3)));
			total.movS("%edx", new LongValueOld((long) (t1 * Math.pow(2, 16) + p * Math.pow(2, 8) + t3)));
		} else if (registerPart.equals("%dl")) {
			Value y = total.getRegVal("%edx");
			if (!(y instanceof LongValueOld))
				return;

			long t = ((LongValueOld) y).getValue();
			long t1 = (long) (t / Math.pow(2, 16));
			long t2 = (long) (t % (long) Math.pow(2, 16));
			// long t3 = (long) (t2 % (long) Math.pow(2, 8));
			part.movS("%dx", new LongValueOld((long) (t2 * Math.pow(2, 8) + p)));
			total.movS("%edx", new LongValueOld((long) (t1 * Math.pow(2, 16) + t2 * Math.pow(2, 8) + p)));
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

			part.movS("%si", new LongValueOld(si));
		} else if (register.equals("%edi")) {
			long di = (long) (t % (long) Math.pow(2, 16));

			part.movS("%di", new LongValueOld(di));
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

			part.movS("%ax", new LongValueOld(ax));
			part.movS("%ah", new LongValueOld(ah));
			part.movS("%al", new LongValueOld(al));
		} else if (register.equals("%ebx")) {
			long bx = (long) (t % (long) Math.pow(2, 16));
			long bh = (long) (bx / (long) Math.pow(2, 8));
			long bl = (long) (bx % (long) Math.pow(2, 8));

			part.movS("%bx", new LongValueOld(bx));
			part.movS("%bh", new LongValueOld(bh));
			part.movS("%bl", new LongValueOld(bl));
		} else if (register.equals("%ecx")) {
			long cx = (long) (t % (long) Math.pow(2, 16));
			long ch = (long) (cx / (long) Math.pow(2, 8));
			long cl = (long) (cx % (long) Math.pow(2, 8));

			part.movS("%cx", new LongValueOld(cx));
			part.movS("%ch", new LongValueOld(ch));
			part.movS("%cl", new LongValueOld(cl));
		} else if (register.equals("%edx")) {
			long dx = (long) (t % (long) Math.pow(2, 16));
			long dh = (long) (dx / (long) Math.pow(2, 8));
			long dl = (long) (dx % (long) Math.pow(2, 8));

			part.movS("%dx", new LongValueOld(dx));
			part.movS("%dh", new LongValueOld(dh));
			part.movS("%dl", new LongValueOld(dl));
		}
	}

	public static void main(String[] args) {
		SymbolValueRegister sR = new SymbolValueRegister();
		SymbolValueRegisterPart sRP = new SymbolValueRegisterPart();
		sR.setSymbolValueRegisterPart(sRP);
		sRP.setSymbolValueRegister(sR);
		sR.movS("%eax", new LongValueOld(0));

		sRP.mov("%ax", 1000);
		sRP.mov("%ah", 100);
		sRP.mov("%al", 10);

		sR.mov("%eax", new LongValueOld(1998));

		ConcreteValueRegister cR = new ConcreteValueRegister();
		ConcreteValueRegisterPart cRP = new ConcreteValueRegisterPart();
		cR.setConcreteValueRegisterPart(cRP);
		cRP.setConcreteValueRegister(cR);

		cR.movS("%eax", 0);

		cRP.mov("%ax", 1000);
		cRP.mov("%ah", 100);
		cRP.mov("%al", 10);

		cR.mov("%eax", 197832);

		System.out.println("Finished.");
	}
}
