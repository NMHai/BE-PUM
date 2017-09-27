package v2.org.analysis.transition_rule;

import java.util.List;

import org.jakstab.asm.Immediate;
import org.jakstab.asm.Operand;
import org.jakstab.asm.x86.X86ArithmeticInstruction;
import org.jakstab.asm.x86.X86MemoryOperand;

import v2.org.analysis.environment.Environment;
import v2.org.analysis.path.BPPath;
import v2.org.analysis.path.BPState;
import v2.org.analysis.structure.BitVector;
import v2.org.analysis.structure.Convert;
import v2.org.analysis.system.SEHHandle;
import v2.org.analysis.value.BooleanValue;
import v2.org.analysis.value.HybridBooleanValue;
import v2.org.analysis.value.HybridValue;
import v2.org.analysis.value.LongValue;
import v2.org.analysis.value.SymbolValue;
import v2.org.analysis.value.Value;

public class X86ArithmeticInterpreter {
	public BPState execute(X86ArithmeticInstruction ins, BPPath path, List<BPPath> pathList, X86TransitionRule rule) {
		// TODO Auto-generated method stub
		BPState curState = path.getCurrentState();
		Environment env = curState.getEnvironement();
		
		int opSize = rule.getBitCount(ins);
		switch (ins.getOperation()) {
		case NOT:
			if (ins.getName().startsWith("not")) {
				Operand dest = ins.getOperand1();
				Value d = rule.getValueOperand(dest, env, ins);
				d = d.notFunction();
				rule.setValueOperand(dest, d, env, ins);

				/*
				 * if (dest.getClass().getSimpleName().equals("X86Register") ||
				 * dest.getClass().getSimpleName() .equals("X86RegisterPart") ||
				 * dest.getClass().getSimpleName()
				 * .equals("X86SegmentRegister")) { d =
				 * env.getRegister().getRegisterValue(dest.toString()); d =
				 * d.notFunction();
				 * 
				 * env.getRegister().setRegisterValue(dest.toString(), d); }
				 */
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case SDIV:
			// Statement supported: add, inc
			if (ins.getName().startsWith("div")) {
				opSize = rule.getBitCount(ins);
				Value result = null;
				Value src = rule.getValueOperand(ins.getOperand2(), env, ins);
				if (src != null && src instanceof LongValue) {
					if (((LongValue) src).getValue() == 0
					// &&
					// curState.getEnvironement().getSystem().getSEHHandler().isSet()
					)
						// SEH trong day
					{
						SEHHandle sehHandle = curState.getEnvironement().getSystem().getSEHHandler();
						sehHandle.setSEHType(SEHHandle.DIVIDE_BY_ZERO);
						return rule.processSEH(path.getCurrentState());
					}
				}
				switch (opSize) {
				case 8:
					Value ax = env.getRegister().getRegisterValue("ax");
					result = ax.unsignedDivFunction(src);
					// env.getRegister().setRegisterValue("ax", result);
					if (ax != null && ax instanceof LongValue && src != null && src instanceof LongValue) {
						long vAX = ((LongValue) ax).getValue();
						long vSRC = ((LongValue) src).getValue();
						long temp = vAX / vSRC;

						if (temp > 0xFF) {
							return rule.processSEH(path.getCurrentState());
						} else {
							env.getRegister().setRegisterValue("al", new LongValue(temp));
							env.getRegister().setRegisterValue("ah", new LongValue(vAX % vSRC));
						}
					} else {
						env.getRegister().setRegisterValue("al", result);
					}
					break;
				case 16:
					ax = env.getRegister().getRegisterValue("ax");
					Value dx = env.getRegister().getRegisterValue("dx");
					result = ax.unsignedDivFunction(src);

					if (ax != null && ax instanceof LongValue && src != null && src instanceof LongValue && dx != null
							&& dx instanceof LongValue) {
						long vAX = ((LongValue) ax).getValue();
						long vSRC = ((LongValue) src).getValue();
						long rDX = ((LongValue) dx).getValue();
						double t = vAX + rDX * Math.pow(2, 16);
						double temp = t / vSRC;
						if (temp > 0xFFFF) {
							return rule.processSEH(path.getCurrentState());
						} else {
							env.getRegister().setRegisterValue("ax", new LongValue(temp));
							env.getRegister().setRegisterValue("dx", new LongValue(t % vSRC));
						}
					} else {
						env.getRegister().setRegisterValue("ax", result);
					}
					break;
				case 32:
					Value eax = env.getRegister().getRegisterValue("eax");
					Value edx = env.getRegister().getRegisterValue("edx");
					result = eax.unsignedDivFunction(src);

					if (eax != null && eax instanceof LongValue && src != null && src instanceof LongValue
							&& edx != null && edx instanceof LongValue) {
						long x1 = ((LongValue) eax).getValue();
						long x2 = ((LongValue) edx).getValue();
						long y = ((LongValue) src).getValue();
						double t = x1 + x2 * Math.pow(2, 32);
						double temp = t / y;

						if (temp > 4294967295l) {
							return rule.processSEH(path.getCurrentState());
						} else {
							env.getRegister().setRegisterValue("eax", new LongValue(temp));
							env.getRegister().setRegisterValue("edx", new LongValue(t % y));
						}
					} else {
						env.getRegister().setRegisterValue("eax", result);
					}
					break;
				}
				// System.out.println("Debug DIV");
			} else if (ins.getName().startsWith("idiv")) {
				opSize = rule.getBitCount(ins);
				Value result = null;
				Value src = rule.getValueOperand(ins.getOperand2(), env, ins);
				if (src != null && src instanceof LongValue) {
					if (((LongValue) src).getValue() == 0
					// &&
					// curState.getEnvironement().getSystem().getSEHHandler().isSet()
					) {
						// SEH trong day
						return rule.processSEH(path.getCurrentState());
					}
				}
				switch (opSize) {
				case 8:
					Value ax = env.getRegister().getRegisterValue("ax");
					result = ax.signedDivFunction(src);
					// env.getRegister().setRegisterValue("ax", result);
					if (ax != null && ax instanceof LongValue && src != null && src instanceof LongValue) {
//						long vAX = Convert.convertSignedValue(((LongValue) ax).getValue(), opSize);
						long vAX = ((LongValue) ax).getValue();
//						long vSRC = Convert.convertSignedValue(((LongValue) src).getValue(), opSize);
						long vSRC = ((LongValue) src).getValue();
						long temp = vAX / vSRC;

						if (temp > 127 || temp < -128) {
							return rule.processSEH(path.getCurrentState());
						} else {
							env.getRegister().setRegisterValue("al", new LongValue(temp));
							env.getRegister().setRegisterValue("ah", new LongValue(vAX % vSRC));
						}
					} else {
						env.getRegister().setRegisterValue("al", result);
					}
					break;
				case 16:
					ax = env.getRegister().getRegisterValue("ax");
					Value dx = env.getRegister().getRegisterValue("dx");
					result = ax.signedDivFunction(src);

					if (ax != null && ax instanceof LongValue && src != null && src instanceof LongValue && dx != null
							&& dx instanceof LongValue) {
						long vAX = ((LongValue) ax).getValue();
//						long vSRC = ((LongValue) src).getValue();
						long vSRC = Convert.convertSignedValue(((LongValue) src).getValue(), opSize);
						long rDX = ((LongValue) dx).getValue();
						double t = Convert.convertSignedValue((long) (vAX + rDX * Math.pow(2, 16)), 2 * opSize);
						double temp = t / vSRC;
						if (temp > 32767 || temp < -32768) {
							return rule.processSEH(path.getCurrentState());
						} else {
							env.getRegister().setRegisterValue("ax", new LongValue(temp));
							env.getRegister().setRegisterValue("dx", new LongValue(t % vSRC));
						}
					} else {
						env.getRegister().setRegisterValue("ax", result);
					}
					break;
				case 32:
					Value eax = env.getRegister().getRegisterValue("eax");
					Value edx = env.getRegister().getRegisterValue("edx");
					result = eax.signedDivFunction(src);

					if (eax != null && eax instanceof LongValue && src != null && src instanceof LongValue
							&& edx != null && edx instanceof LongValue) {
//						long x1 = Convert.convertSignedValue(((LongValue) eax).getValue(), opSize);
						long x1 = ((LongValue) eax).getValue();
//						long x2 = Convert.convertSignedValue(((LongValue) edx).getValue(), opSize);
						long x2 = ((LongValue) edx).getValue();
//						long y = ((LongValue) src).getValue();
						long y = Convert.convertSignedValue(((LongValue) src).getValue(), opSize);
						double t = x1 + x2 * Math.pow(2, 32);
						double temp = t / y;

						if (temp > 2147483647 || temp < -2147483648) {
							return rule.processSEH(path.getCurrentState());
						} else {
							env.getRegister().setRegisterValue("eax", new LongValue(temp));
							env.getRegister().setRegisterValue("edx", new LongValue(t % y));
						}
					} else {
						env.getRegister().setRegisterValue("eax", result);
					}
					break;
				}
				// System.out.println("Debug DIV");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case UDIV:
			// Statement supported: add, inc
			if (ins.getName().startsWith("div")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());

					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s instanceof LongValue) {
						if (((LongValue) s).getValue() == 0
						// &&
						// curState.getEnvironement().getSystem().getSEHHandler().isSet()
						) {
							// SEH trong day
							return rule.processSEH(path.getCurrentState());
						}
					}

					if (d != null && s != null) {
						env.getRegister().uDiv(dest.toString(), s);
						// curState.setLocation(location)
					}
					// Bo sung them sau
				}

				// System.out.println("Debug DIV");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case ADD:
			// Statement supported: add, inc
			if (ins.getName().startsWith("add")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);

					}

					if (s != null) {
						env.getRegister().add(dest.toString(), s);
						env.getFlag().changeFlagWithADD(d, s, env, rule.getBitCount(ins));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						// env.getRegister().add(dest.toString(), s);
						// Change flag after all
						// env.getFlag().changeFlagWithADD(false, d, s,
						// env,rule.getBitCount(ins));
						env.getMemory().addMemoryValue((X86MemoryOperand) dest, s, ins);
						env.getFlag().changeFlagWithADD(d, s, env, rule.getBitCount(ins));
					}
				}
				// System.out.println("Test ADD");
			} else if (ins.getName().startsWith("inc")) {
				/*
				 * if (path.getCurrentState().getLocation().toString().contains(
				 * "4010cd")) System.out.println("Debug");
				 */

				Operand dest = ins.getOperand1();
				Value d = null;
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					env.getRegister().add(dest.toString(), new LongValue(1));
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					env.getMemory().addMemoryValue((X86MemoryOperand) dest, new LongValue(1), ins);
				}
				env.getFlag().changeFlagWithINC(d, env, rule.getBitCount(ins));
			} else if (ins.getName().startsWith("xadd")) {
				// Temporary = Source + Destination;
				// Source = Destination;
				// Destination = Temporary;
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				if (dest == null || src == null) {
					return curState;
				}

				if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}
				} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) src, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}
				}

				Value dVal = rule.getValueOperand(dest, env, ins);
				Value sVal = rule.getValueOperand(src, env, ins);
				Value temp = sVal.addFunction(dVal);

				rule.setValueOperand(src, dVal, env, ins);
				rule.setValueOperand(dest, temp, env, ins);
				env.getFlag().changeFlagWithADD(sVal, dVal, env, opSize);

			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case ADDC:
			// Statement supported: adc, inc
			if (ins.getName().startsWith("adc")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						// env.getRegister().add(dest.toString(), s);
						// Change flag after all
						// env.getFlag().changeFlagWithADD(false, d, s,
						// env,rule.getBitCount(ins));

						Value cf = env.getFlag().getCFlag();
						if (cf instanceof BooleanValue) {
							if (((BooleanValue) cf).getValue()) {
								env.getRegister().add(dest.toString(), s);
								env.getRegister().add(dest.toString(), new LongValue(1));
								env.getFlag().changeFlagWithADC(d, s, 1, env, rule.getBitCount(ins));

							} else {
								env.getRegister().add(dest.toString(), s);
								env.getFlag().changeFlagWithADC(d, s, 0, env, rule.getBitCount(ins));
							}

						} else {
							// System.out.println("CF is Unknown.");
							env.getRegister().add(dest.toString(), s);
							env.getFlag().changeFlagWithADC(d, s, 0, env, rule.getBitCount(ins));
						}
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						// env.getRegister().add(dest.toString(), s);
						// Change flag after all
						// env.getFlag().changeFlagWithADD(false, d, s,
						// env,rule.getBitCount(ins));

						Value cf = env.getFlag().getCFlag();
						if (cf instanceof BooleanValue) {
							if (((BooleanValue) cf).getValue()) {
								env.getMemory().addMemoryValue((X86MemoryOperand) dest, s, ins);
								env.getMemory().addMemoryValue((X86MemoryOperand) dest, new LongValue(1), ins);
								env.getFlag().changeFlagWithADC(d, s, 1, env, rule.getBitCount(ins));

							} else {
								env.getMemory().addMemoryValue((X86MemoryOperand) dest, s, ins);
								env.getFlag().changeFlagWithADC(d, s, 0, env, rule.getBitCount(ins));
							}

						} else {
							System.out.println("CF is Unknown.");
							env.getMemory().addMemoryValue((X86MemoryOperand) dest, s, ins);
							env.getFlag().changeFlagWithADC(d, s, 0, env, rule.getBitCount(ins));
						}
					}
				}
				// System.out.println("Test ADC");
			} else if (ins.getName().startsWith("inc")) {
				// PHONG: not change with CF
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					Value d = env.getRegister().getRegisterValue(dest.toString());
					env.getRegister().add(dest.toString(), new LongValue(1));
					env.getFlag().changeFlagWithINC(d, env, rule.getBitCount(ins));
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					Value d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					env.getMemory().addMemoryValue((X86MemoryOperand) dest, new LongValue(1), ins);
					env.getFlag().changeFlagWithINC(d, env, rule.getBitCount(ins));
				}
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case AND:
			// Statement supported: and
			if (ins.getName().startsWith("and")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						env.getRegister().and(dest.toString(), s);
						env.getFlag().changeFlagWithAND(d, s, env, rule.getBitCount(ins));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						// env.getRegister().add(dest.toString(), s);
						// Change flag after all
						// env.getFlag().changeFlagWithADD(false, d, s,
						// env,rule.getBitCount(ins));
						env.getMemory().andMemoryValue((X86MemoryOperand) dest, s, ins);
						env.getFlag().changeFlagWithAND(d, s, env, rule.getBitCount(ins));
					}
				}
				// System.out.println("Test AND");

			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case XOR:
			// Statement supported: and
			if (ins.getName().startsWith("xor")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.toString().equals(src.toString())) {
					if (dest.getClass().getSimpleName().equals("X86Register")
							|| dest.getClass().getSimpleName().equals("X86RegisterPart")
							|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
						env.getRegister().setRegisterValue(dest.toString(), new LongValue(0));						
					} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
						env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(0), ins);						
					}
					
					env.getFlag().changeFlagWithXOR(new LongValue(1), new LongValue(1), env, rule.getBitCount(ins));
				} else if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						env.getRegister().xor(dest.toString(), s);
						env.getFlag().changeFlagWithXOR(d, s, env, rule.getBitCount(ins));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						// env.getRegister().add(dest.toString(), s);
						// Change flag after all
						// env.getFlag().changeFlagWithADD(false, d, s,
						// env,rule.getBitCount(ins));
						env.getMemory().xorMemoryValue((X86MemoryOperand) dest, s, ins);
						env.getFlag().changeFlagWithXOR(d, s, env, rule.getBitCount(ins));
					}
				}
				// System.out.println("Test XOR");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case SUB:
			/* Statement supported: sub, dec */
			if (ins.getName().startsWith("sub")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				Value d = null;
				Value s = null;
				if (dest.toString().equals(src.toString())) {
					if (dest.getClass().getSimpleName().equals("X86Register")
							|| dest.getClass().getSimpleName().equals("X86RegisterPart")
							|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
						env.getRegister().setRegisterValue(dest.toString(), new LongValue(0));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						env.getMemory().setMemoryValue((X86MemoryOperand) dest, new LongValue(0), ins);
					}
					
					env.getFlag().changeFlagWithSUB(new LongValue(0), new LongValue(0), env, opSize);

				} else if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						env.getRegister().sub(dest.toString(), s);
						// Bo sung them sau
						env.getFlag().changeFlagWithSUB(d, s, env, rule.getBitCount(ins));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);
					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						// SEH Exploit
						/*
						 * System.out.println("SEH:" +
						 * path.getCurrentState().getLocation() .toString());
						 */
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						env.getMemory().subMemoryValue((X86MemoryOperand) dest, s, ins);
						env.getFlag().changeFlagWithSUB(d, s, env, rule.getBitCount(ins));
					}
				}

				// System.out.println("Test SUB");
			} else if (ins.getName().startsWith("dec")) {
				Operand dest = ins.getOperand1();
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					env.getFlag().changeFlagWithDEC(env.getRegister().getRegisterValue(dest.toString()), env,
							rule.getBitCount(ins));
					env.getRegister().sub(dest.toString(), new LongValue(1));
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);
					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						// SEH Exploit
						return rule.processSEH(path.getCurrentState());
					}

					env.getFlag().changeFlagWithDEC(env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins), env,
							rule.getBitCount(ins));
					env.getMemory().subMemoryValue((X86MemoryOperand) dest, new LongValue(1), ins);
				}
				// System.out.println("Test DEC");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case OR:
			// Statement supported: or
			if (ins.getName().startsWith("or")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						env.getRegister().or(dest.toString(), s);
						env.getFlag().changeFlagWithOR(d, s, env, rule.getBitCount(ins));
					}
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					// X86MemoryOperand t = env.getMemory().evaluateAddress(
					// (X86MemoryOperand) dest, env);

					if (!rule.checkAddressValid(env, (X86MemoryOperand) dest)) {
						System.out.println("Process SEH at:" + path.getCurrentState().getLocation().toString());
						return rule.processSEH(path.getCurrentState());
					}

					d = env.getMemory().getMemoryValue((X86MemoryOperand) dest, ins);
					if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						s = env.getRegister().getRegisterValue(src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						s = new LongValue(Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins)));
					} else if (src.getClass().getSimpleName().equals("X86MemoryOperand")) {
						s = env.getMemory().getMemoryValue((X86MemoryOperand) src, ins);
					}

					if (s != null) {
						// env.getRegister().add(dest.toString(), s);
						// Change flag after all
						// env.getFlag().changeFlagWithADD(false, d, s,
						// env,rule.getBitCount(ins));
						env.getMemory().orMemoryValue((X86MemoryOperand) dest, s, ins);
						env.getFlag().changeFlagWithOR(d, s, env, rule.getBitCount(ins));
					}
				}
				// System.out.println("Test OR");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case SRL:
			if (ins.getName().startsWith("shr")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateShiftOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				// Process the case with symbol value
				//Value t = new HybridValue(d, "/", new HybridValue(new LongValue(2), "^", s));
				Value t = new HybridValue(d, "shr", s);
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					env.getRegister().setRegisterValue(dest.toString(), t);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					env.getMemory().setMemoryValue((X86MemoryOperand) dest, t, ins);
				}
				env.getFlag().setPFlag(new SymbolValue("pFlag"));
				env.getFlag().setSFlag(new SymbolValue("sFlag"));
				env.getFlag().setZFlag(new SymbolValue("sZlag"));
				// System.out.println("Debug SHR");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case RR:
			if (ins.getName().startsWith("ror")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateRotateOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src == null) {
						Value x = new LongValue(1);
						env.getFlag().changeFlagWithRR(env.getRegister().getRegisterValue(dest.toString()), x, env,
								rule.getBitCount(ins));
						env.getRegister().rr(dest.toString(), x);
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins));
						Value x = new LongValue((long) Math.pow(2, y));
						env.getFlag().changeFlagWithRR(env.getRegister().getRegisterValue(dest.toString()), x, env,
								rule.getBitCount(ins));
						env.getRegister().rr(dest.toString(), new LongValue(y));
					}
				}
				System.out.println("Debug ROR with Symbol Value");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case RRC:
			if (ins.getName().startsWith("rcr")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateRotateOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					if (src == null) {
						Value x = new LongValue(1);
						env.getFlag().changeFlagWithRR(env.getRegister().getRegisterValue(dest.toString()), x, env,
								rule.getBitCount(ins));
						env.getRegister().rr(dest.toString(), x);
					} else if (src.getClass().getSimpleName().equals("X86Register")) {
						// sv.sub(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins));
						Value x = new LongValue((long) Math.pow(2, y));
						env.getFlag().changeFlagWithRR(env.getRegister().getRegisterValue(dest.toString()), x, env,
								rule.getBitCount(ins));
						env.getRegister().rr(dest.toString(), new LongValue(y));
					}
				}
				System.out.println("Debug ROR with Symbol Value");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case RL:
			if (ins.getName().startsWith("rol")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateRotateOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());

					if (src == null) {
						// s = new LongValue(1);
						env.getRegister().rl(dest.toString(), new LongValue(1));
					} else if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						s = env.getRegister().getRegisterValue(src.toString());
						env.getFlag().changeFlagWithRL(d, s, env, rule.getBitCount(ins));
						env.getRegister().rl(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins));
						Value x = new LongValue((long) Math.pow(2, y));
						env.getFlag().changeFlagWithRL(env.getRegister().getRegisterValue(dest.toString()), x, env,
								rule.getBitCount(ins));
						env.getRegister().rl(dest.toString(), new LongValue(y));
					}
				}
				System.out.println("Debug ROL with Symbol Value");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case RLC:
			if (ins.getName().startsWith("rcl")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateRotateOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					d = env.getRegister().getRegisterValue(dest.toString());

					if (src == null) {
						// s = new LongValue(1);
						env.getRegister().rl(dest.toString(), new LongValue(1));
					} else if (src.getClass().getSimpleName().equals("X86Register")
							|| src.getClass().getSimpleName().equals("X86RegisterPart")
							|| src.getClass().getSimpleName().equals("X86SegmentRegister")) {
						// sv.sub(dest.toString(), src.toString());
						s = env.getRegister().getRegisterValue(src.toString());
						env.getFlag().changeFlagWithRL(d, s, env, rule.getBitCount(ins));
						env.getRegister().rl(dest.toString(), src.toString());
					} else if (src.getClass().getSimpleName().equals("Immediate")) {
						long y = Convert.convetUnsignedValue(((Immediate) src).getNumber().intValue(),
								rule.getBitCount(ins));
						Value x = new LongValue((long) Math.pow(2, y));
						env.getFlag().changeFlagWithRL(env.getRegister().getRegisterValue(dest.toString()), x, env,
								rule.getBitCount(ins));
						env.getRegister().rl(dest.toString(), new LongValue(y));
					}
				}
				System.out.println("Debug ROL with Symbol Value");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;
		case SLL:
			// SHL and SAL is the same
			if (ins.getName().startsWith("shl") || ins.getName().startsWith("sal")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateShiftOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				Value t = new HybridValue(d, "*", new HybridValue(new LongValue(2), "^", s));
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					env.getRegister().setRegisterValue(dest.toString(), t);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					env.getMemory().setMemoryValue((X86MemoryOperand) dest, t, ins);
				}
				env.getFlag().setPFlag(new SymbolValue("pFlag"));
				env.getFlag().setSFlag(new SymbolValue("sFlag"));
				env.getFlag().setZFlag(new SymbolValue("sZlag"));
				// System.out.println("Debug SHL");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}

			break;

		case SMUL:
			if (ins.getName().startsWith("imul")) {
				// The SF, ZF, AF, and PF flags are undefined.
				opSize = rule.getBitCount(ins);
				Value result = null;
				switch (ins.getOperandCount()) {
				case 1:
					if (ins.getOperand1().toString().startsWith("%ax") || ins.getOperand1().toString().startsWith("ax")
							|| ins.getOperand1().toString().startsWith("%al")
							|| ins.getOperand1().toString().startsWith("al")
							|| ins.getOperand1().toString().startsWith("%eax")
							|| ins.getOperand1().toString().startsWith("eax")) {
						if (opSize == 8) {
							Value al = env.getRegister().getRegisterValue("al");
							Value source = rule.getValueOperand(ins.getOperand2(), env, ins);

							env.getRegister().setRegisterValue("ax", al.signedMulFunction(source, opSize));
							result = env.getRegister().getRegisterValue("ax");
							if (result.equal(al)) {
								env.getFlag().setCFlag(new BooleanValue(false));
								env.getFlag().setOFlag(new BooleanValue(false));
							} else {
								env.getFlag().setCFlag(new BooleanValue(true));
								env.getFlag().setOFlag(new BooleanValue(true));
							}
						} else if (opSize == 16) {
							Value ax = env.getRegister().getRegisterValue("ax");
							Value source = rule.getValueOperand(ins.getOperand2(), env, ins);
							result = ax.signedMulFunction(source, opSize);

							if (ax != null && ax instanceof LongValue && source != null && source instanceof LongValue) {
								long temp = BitVector.signedMul(((LongValue) ax).getValue(),
										((LongValue) source).getValue(), opSize);

								long axVal = (long) (Convert.convetUnsignedValue(temp, opSize * 2) % Math.pow(2, 16));
								long dxVal = (long) (Convert.convetUnsignedValue(temp, opSize * 2) / Math.pow(2, 16));
								env.getRegister().setRegisterValue("ax", new LongValue(axVal));
								env.getRegister().setRegisterValue("dx", new LongValue(dxVal));
								// if (BitVector.signExtend(axVal, 32) == (axVal
								// + dxVal * Math.pow(2, 16))) {
								if (dxVal == 0 || dxVal == 65535) {
									env.getFlag().setCFlag(new BooleanValue(false));
									env.getFlag().setOFlag(new BooleanValue(false));
								} else {
									env.getFlag().setCFlag(new BooleanValue(true));
									env.getFlag().setOFlag(new BooleanValue(true));
								}
							} else {
								env.getRegister().setRegisterValue("ax", result);
								env.getFlag().setCFlag(new SymbolValue("cFlag"));
								env.getFlag().setOFlag(new SymbolValue("oFlag"));
							}
						} else if (opSize == 32) {
							Value eax = env.getRegister().getRegisterValue("eax");
							Value source = rule.getValueOperand(ins.getOperand2(), env, ins);
							result = eax.signedMulFunction(source, opSize);

							if (eax != null && eax instanceof LongValue && source != null
									&& source instanceof LongValue) {
								long temp = BitVector.signedMul(((LongValue) eax).getValue(),
										((LongValue) source).getValue(), opSize);
								long t = temp & 0xFFFFFFFFFFFFFFFFl;
								long eaxVal = (long) (t % Math.pow(2, 32));
								long edxVal = (long) (t / Math.pow(2, 32));

								if (eaxVal < 0 && edxVal == 0) {
									edxVal = 4294967295l;
								}
								env.getRegister().setRegisterValue("eax", new LongValue(eaxVal));
								env.getRegister().setRegisterValue("edx", new LongValue(edxVal));
								if (edxVal == 0 || edxVal == 4294967295l) {
									env.getFlag().setCFlag(new BooleanValue(false));
									env.getFlag().setOFlag(new BooleanValue(false));
								} else {
									env.getFlag().setCFlag(new BooleanValue(true));
									env.getFlag().setOFlag(new BooleanValue(true));
								}
							} else {
								env.getRegister().setRegisterValue("eax", result);
								env.getFlag().setCFlag(new SymbolValue("cFlag"));
								env.getFlag().setOFlag(new SymbolValue("oFlag"));
							}
						}

						break;
					} else {
						Value dest = rule.getValueOperand(ins.getOperand1(), env, ins);
						Value source = rule.getValueOperand(ins.getOperand2(), env, ins);

						if (dest != null && dest instanceof LongValue && source != null && source instanceof LongValue) {
							long dVal = ((LongValue) dest).getValue();
							long sVal = ((LongValue) source).getValue();

							long temp = BitVector.signedMul(dVal, sVal, 2 * opSize);
							dVal = BitVector.signedMul(dVal, sVal, opSize);

							if (temp == dVal) {
								env.getFlag().setCFlag(new BooleanValue(false));
								env.getFlag().setOFlag(new BooleanValue(false));
							} else {
								env.getFlag().setCFlag(new BooleanValue(true));
								env.getFlag().setOFlag(new BooleanValue(true));
							}
							result = new LongValue(dVal);
							rule.setValueOperand(ins.getOperand1(), result, env, ins);
						} else {
							result = dest.signedMulFunction(source);
							rule.setValueOperand(ins.getOperand1(), result, env, ins);
							env.getFlag().setCFlag(new SymbolValue("cFlag"));
							env.getFlag().setOFlag(new SymbolValue("oFlag"));
						}
						break;
					}
				case 2: 
					Value destination = rule.getValueOperand(ins.getOperand1(), env, ins);
					Value source = rule.getValueOperand(ins.getOperand2(), env, ins);					

					if (destination != null && destination instanceof LongValue && source != null && source instanceof LongValue) {
						long dVal = ((LongValue) destination).getValue();
						long sVal = ((LongValue) source).getValue();						

						long temp = BitVector.signedMul(dVal, sVal, 2 * opSize);
						long destVal = BitVector.signedMul(dVal, sVal, opSize);

						if (temp == destVal) {
							env.getFlag().setCFlag(new BooleanValue(false));
							env.getFlag().setOFlag(new BooleanValue(false));
						} else {
							env.getFlag().setCFlag(new BooleanValue(true));
							env.getFlag().setOFlag(new BooleanValue(true));
						}
						result = new LongValue(destVal);
						rule.setValueOperand(ins.getOperand1(), result, env, ins);
					} else {
						result = destination.signedMulFunction(source);
						rule.setValueOperand(ins.getOperand1(), result, env, ins);
						env.getFlag().setCFlag(new SymbolValue("cFlag"));
						env.getFlag().setOFlag(new SymbolValue("oFlag"));
					}
					break;
				case 3:
					Value dest = rule.getValueOperand(ins.getOperand1(), env, ins);
					Value source1 = rule.getValueOperand(ins.getOperand2(), env, ins);
					Value source2 = rule.getValueOperand(ins.getOperand3(), env, ins);

					if (dest != null && dest instanceof LongValue && source1 != null && source1 instanceof LongValue
							&& source2 != null && source2 instanceof LongValue) {
						long dVal = ((LongValue) dest).getValue();
						long sVal1 = ((LongValue) source1).getValue();
						long sVal2 = ((LongValue) source2).getValue();

						long temp = BitVector.signedMul(sVal1, sVal2, 2 * opSize);
						dVal = BitVector.signedMul(sVal1, sVal2, opSize);

						if (temp == dVal) {
							env.getFlag().setCFlag(new BooleanValue(false));
							env.getFlag().setOFlag(new BooleanValue(false));
						} else {
							env.getFlag().setCFlag(new BooleanValue(true));
							env.getFlag().setOFlag(new BooleanValue(true));
						}
						result = new LongValue(dVal);
						rule.setValueOperand(ins.getOperand1(), result, env, ins);
					} else {
						result = source1.signedMulFunction(source2);
						rule.setValueOperand(ins.getOperand1(), result, env, ins);
						env.getFlag().setCFlag(new SymbolValue("cFlag"));
						env.getFlag().setOFlag(new SymbolValue("oFlag"));
					}

					break;
				}

				if (result != null && result instanceof LongValue) {
					long r = ((LongValue) result).getValue();
					long t = Convert.convertSignedValue(r, opSize);
					env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
					env.getFlag().setSFlag(new BooleanValue(t < 0));
					env.getFlag().setZFlag(new BooleanValue(t == 0));
				}
				// newDest.addExprValue(SymbolicValue.SUB_EXPR, result);
				// System.out.println("Debug IMUL");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}

			// System.out.println("Test IMUL");
			break;
		case UMUL:
			if (ins.getName().startsWith("mul")) {
				// The OF and CF flags are cleared to 0 if the upper half of the
				// result is 0; otherwise, they are set to 1.
				// The SF, ZF, AF, and PF flags are undefined.
				opSize = rule.getBitCount(ins);
				Value result = null;
				switch (opSize) {
				case 8:
					Value al = env.getRegister().getRegisterValue("al");
					Value src = rule.getValueOperand(ins.getOperand2(), env, ins);
					result = al.unsignedMulFunction(src);
					env.getRegister().setRegisterValue("ax", result);
					if (result instanceof LongValue) {
						long t = ((LongValue) result).getValue();
						if (t / Math.pow(2, 8) == 0) {
							env.getFlag().setCFlag(new BooleanValue(false));
							env.getFlag().setOFlag(new BooleanValue(false));
						} else {
							env.getFlag().setCFlag(new BooleanValue(true));
							env.getFlag().setOFlag(new BooleanValue(true));
						}
					} else {
						env.getRegister().setRegisterValue("al", result);
						env.getFlag().setCFlag(new SymbolValue("cFlag"));
						env.getFlag().setOFlag(new SymbolValue("oFlag"));
					}
					break;
				case 16:
					Value ax = env.getRegister().getRegisterValue("ax");
					src = rule.getValueOperand(ins.getOperand2(), env, ins);
					result = ax.unsignedMulFunction(src);

					if (result instanceof LongValue) {
						long t = ((LongValue) result).getValue();
						long rAX = (long) (t % Math.pow(2, 16));
						long rDX = (long) (t / Math.pow(2, 16));
						env.getRegister().setRegisterValue("ax", new LongValue(rAX));
						env.getRegister().setRegisterValue("dx", new LongValue(rDX));
						if (rDX == 0) {
							env.getFlag().setCFlag(new BooleanValue(false));
							env.getFlag().setOFlag(new BooleanValue(false));
						} else {
							env.getFlag().setCFlag(new BooleanValue(true));
							env.getFlag().setOFlag(new BooleanValue(true));
						}
					} else {
						env.getRegister().setRegisterValue("ax", result);
						env.getFlag().setCFlag(new SymbolValue("cFlag"));
						env.getFlag().setOFlag(new SymbolValue("oFlag"));
					}
					break;
				case 32:
					Value eax = env.getRegister().getRegisterValue("eax");
					src = rule.getValueOperand(ins.getOperand2(), env, ins);
					result = eax.unsignedMulFunction(src);

					if (eax instanceof LongValue && src instanceof LongValue) {
						long x = ((LongValue) eax).getValue();
						long y = ((LongValue) src).getValue();
						long rEAX = (long) ((x * y) % Math.pow(2, 32));
						long rEDX = (long) ((x * y) / Math.pow(2, 32));
						env.getRegister().setRegisterValue("eax", new LongValue(rEAX));
						env.getRegister().setRegisterValue("edx", new LongValue(rEDX));
						if (rEDX == 0) {
							env.getFlag().setCFlag(new BooleanValue(false));
							env.getFlag().setOFlag(new BooleanValue(false));
						} else {
							env.getFlag().setCFlag(new BooleanValue(true));
							env.getFlag().setOFlag(new BooleanValue(true));
						}
					} else {
						env.getRegister().setRegisterValue("eax", result);
						env.getFlag().setCFlag(new SymbolValue("cFlag"));
						env.getFlag().setOFlag(new SymbolValue("oFlag"));
					}
					break;
				}

				if (result != null && result instanceof LongValue) {
					long r = ((LongValue) result).getValue();
					long t = Convert.convertSignedValue(r, opSize);
					env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
					env.getFlag().setSFlag(new BooleanValue(t < 0));
					env.getFlag().setZFlag(new BooleanValue(t == 0));
				}
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case SUBC: // TODO: 51104402
			// For SBB - Subtract with Borrow
			if (ins.getName().startsWith("sbb")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();

				Value d = rule.getValueOperand(dest, env, ins);
				Value s = rule.getValueOperand(src, env, ins);

				if (s != null) {
					Value cFlag = env.getFlag().getCFlag();
					if (cFlag instanceof BooleanValue) {
						boolean cf = ((BooleanValue) cFlag).getValue();
						if (cf) {
							// env.getFlag().changeFlagWithSBB(d, s, 1, env,
							// rule.getBitCount(ins));
							s = s.addFunction(new LongValue(1));
						}
						Value result = d.subFunction(s);
						rule.setValueOperand(dest, result, env, ins);
						env.getFlag().changeFlagWithSUB(d, s, env, rule.getBitCount(ins));
					} else {
						System.out.println("CF is unknown");
						Value result = d.subFunction(s);
						rule.setValueOperand(dest, result, env, ins);
						env.getFlag().changeFlagWithSUB(d, s, env, rule.getBitCount(ins));
					}
				}
				// System.out.println("Debug SBB");
			} else {
				System.out.println("Instruction not supported: " + ins.getName());
			}
			break;

		case SRA: // TODO: 51104402
			// For SAR - Shift Arithmetic Right
			if (ins.getName().startsWith("sar")) {
				Operand dest = ins.getOperand1();
				Operand src = ins.getOperand2();
				Value d = null;
				Value s = null;
				// Error in disassembly by Jakstab when handling Aztec virus at
				// 0x0040137c
				if (dest == null) {
					break;
				}

				s = rule.getValueOperand(src, env, ins);
				d = rule.getValueOperand(dest, env, ins);

				if (d != null && s != null && d instanceof LongValue && s instanceof LongValue) {
					long t = calculateShiftOperator(((LongValue) d).getValue(), ((LongValue) s).getValue(), env, rule,
							ins);
					rule.setValueOperand(dest, new LongValue(t), env, ins);
					break;
				}

				//Value t = new HybridValue(d, "/", new HybridValue(new LongValue(2), "^", s));
				Value t = new HybridValue(d, "sar", s);
				if (dest.getClass().getSimpleName().equals("X86Register")
						|| dest.getClass().getSimpleName().equals("X86RegisterPart")
						|| dest.getClass().getSimpleName().equals("X86SegmentRegister")) {
					env.getRegister().setRegisterValue(dest.toString(), t);
				} else if (dest.getClass().getSimpleName().equals("X86MemoryOperand")) {
					env.getMemory().setMemoryValue((X86MemoryOperand) dest, t, ins);
				}
				env.getFlag().setPFlag(new SymbolValue("pFlag"));
				env.getFlag().setSFlag(new SymbolValue("sFlag"));
				env.getFlag().setZFlag(new SymbolValue("sZlag"));
			} else {
				System.out.println("Arithmetic statement not supported: " + ins.getName());
			}

			break;

		default:
			if (ins.getName().startsWith("div")) {

			}
			System.out.println("Arithmetic statement not supported: " + ins.getName());
			// halt_status = FAILED;
			// SymbolicExecution.programTrace.setVisited(startAddress, null,
			// ins.toString(startAddress, symFinder));
			// return null;
		}

		rule.generateNextInstruction(ins, path, pathList, true);
		return path.getCurrentState();
	}

	private long calculateRotateOperator(long dest, long count, Environment env, X86TransitionRule rule,
			X86ArithmeticInstruction inst) {
		int size = rule.getBitCount(inst);
		// dest = BitVector.normalizeNumber(dest, size);
		dest = Convert.convetUnsignedValue(dest, size);
		if (inst.getName().startsWith("ror") || inst.getName().startsWith("rol")) {
			int tempCount = 0;
			switch (size) {
			case 8:
				tempCount = (int) (count % 8);
				break;
			case 16:
				tempCount = (int) (count % 16);
				break;
			case 32:
				tempCount = (int) (count % 32);
				break;
			default:
				break;
			}

			if (inst.getName().startsWith("rol")) {
				while (tempCount != 0) {
					byte tempCF = BitVector.getMSB(dest, size);
					dest = Convert.convetUnsignedValue(dest << 1, size) + tempCF;
					// dest = BitVector.normalizeNumber(dest, size);
					tempCount--;
				}

				dest = Convert.convertSignedValue(dest, size);
				env.getFlag().setCFlag(new BooleanValue(BitVector.getLSB(dest, size)));
				if (count == 1) {
					BooleanValue temp = new BooleanValue(BitVector.getMSB(dest, size));
					Value cFlag = env.getFlag().getCFlag();
					Value oFlag = new HybridBooleanValue(temp, "xor", cFlag).evaluate();
					env.getFlag().setOFlag(oFlag);
				}
			} else {
				// ROR
				// long tempDest = dest;
				// tempDest = Long.rotateRight(tempDest, tempCount);
				// tempDest = BitVector.normalizeNumber(tempDest, size);

				while (tempCount != 0) {
					byte tempCF = BitVector.getLSB(dest, size);
					long x = Convert.convetUnsignedValue(dest >> 1, size);
					long y = Convert.convetUnsignedValue(tempCF << (size - 1), size);
					dest = x + y;
					// dest = (dest >> 1) + (tempCF << size);
					tempCount--;
				}

				// dest = BitVector.normalizeNumber(dest, size);
				dest = Convert.convertSignedValue(dest, size);
				env.getFlag().setCFlag(new BooleanValue(BitVector.getMSB(dest, size)));
				if (count == 1) {
					BooleanValue msb = new BooleanValue(BitVector.getMSB(dest, size));
					BooleanValue smsb = new BooleanValue(BitVector.getSMSB(dest, size));

					Value oFlag = new HybridBooleanValue(msb, "xor", smsb).evaluate();
					env.getFlag().setOFlag(oFlag);
				}
			}

		} else if (inst.getName().startsWith("rcr") || inst.getName().startsWith("rcl")) {
			// int size = rule.getBitCount(inst);
			int tempCount = 0;
			switch (size) {
			case 8:
				tempCount = (int) ((count & 0x1F) % 9);
				break;
			case 16:
				tempCount = (int) ((count & 0x1F) % 17);
				break;
			case 32:
				tempCount = (int) (count & 0x1F);
				break;
			default:
				break;
			}

			if (inst.getName().startsWith("rcl")) {
				byte t = 0;
				Value cf = env.getFlag().getCFlag();
				if (cf != null && cf instanceof BooleanValue) {
					t = (byte) (((BooleanValue) cf).getValue() ? 1 : 0);
				}
				while (tempCount != 0) {
					byte tempCF = BitVector.getMSB(dest, size);
					dest = Convert.convetUnsignedValue(dest << 1, size) + t;
					t = tempCF;
					tempCount--;
				}
				dest = Convert.convertSignedValue(dest, size);
				env.getFlag().setCFlag(new BooleanValue(t == 1));
				if (count == 1) {
					BooleanValue temp = new BooleanValue(BitVector.getMSB(dest, size));
					Value cFlag = env.getFlag().getCFlag();
					Value oFlag = new HybridBooleanValue(temp, "xor", cFlag).evaluate();
					env.getFlag().setOFlag(oFlag);
				}
			} else {
				// RCR
				if (count == 1) {
					BooleanValue temp = new BooleanValue(BitVector.getMSB(dest, size));
					Value cFlag = env.getFlag().getCFlag();
					Value oFlag = new HybridBooleanValue(temp, "xor", cFlag).evaluate();
					env.getFlag().setOFlag(oFlag);
				}

				byte t = 0;
				Value cf = env.getFlag().getCFlag();
				if (cf != null && cf instanceof BooleanValue) {
					t = (byte) (((BooleanValue) cf).getValue() ? 1 : 0);
				}

				while (tempCount != 0) {
					byte tempCF = BitVector.getLSB(dest, size);
					dest = Convert.convetUnsignedValue(dest >> 1, size)
							+ Convert.convetUnsignedValue(t << (size - 1), size);
					t = tempCF;
					tempCount--;
				}
				dest = Convert.convertSignedValue(dest, size);
				env.getFlag().setCFlag(new BooleanValue(t == 1));
			}
		}

		return dest;
	}

	/*private long calculateShiftOperator(long dest, long count, Environment env, X86TransitionRule rule,
			X86ArithmeticInstruction inst) {
		if (count == 0) {
			// All flag unchanged
			return dest;
		}

		int size = rule.getBitCount(inst);
		dest = Convert.convetUnsignedValue(dest, size);
		long tempCount = (long) count & 0x1F;
		long tempDest = dest;
		byte bits = (byte) rule.getBitCount(inst);
		while (tempCount != 0) {
			if (inst.getName().startsWith("sal") || inst.getName().startsWith("shl")) {
				env.getFlag().setCFlag(new BooleanValue(BitVector.getMSB(dest, bits)));
				dest = Convert.convetUnsignedValue(dest << 1, size);
			} else {
				env.getFlag().setCFlag(new BooleanValue(BitVector.getLSB(dest, bits)));

				if (inst.getName().startsWith("sar")) {
					dest /= 2;
				} else {
					// SHR
					dest /= 2;
				}
			}

			tempCount--;
		}

		if ((count & 0x1F) == 1) {
			if (inst.getName().startsWith("sal") || inst.getName().startsWith("shl")) {
				BooleanValue temp = new BooleanValue(BitVector.getMSB(dest, bits));
				Value cFlag = env.getFlag().getCFlag();
				Value oFlag = new HybridBooleanValue(temp, "xor", cFlag);
				env.getFlag().setOFlag(oFlag);
			} else if (inst.getName().startsWith("sar"))
				env.getFlag().setOFlag(new BooleanValue(false));
			else
				env.getFlag().setOFlag(new BooleanValue(BitVector.getMSB(tempDest, bits)));

		} else {
			// OF undefined
		}

		long t = Convert.convertSignedValue(dest, bits);
		env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
		env.getFlag().setSFlag(new BooleanValue(t < 0));
		env.getFlag().setZFlag(new BooleanValue(t == 0));

		return dest;
	}*/
	private long calculateShiftOperator(long dest, long count, Environment env, X86TransitionRule rule,
			X86ArithmeticInstruction inst) {
		if (count == 0) {
			// All flag unchanged
			return dest;
		}

		int size = rule.getBitCount(inst);
		dest = Convert.convetUnsignedValue(dest, size);
		long tempCount = count & 0x1F;
		long tempDest = dest;
		byte bits = (byte) rule.getBitCount(inst);
		while (tempCount != 0) {
			if (inst.getName().startsWith("sal") || inst.getName().startsWith("shl")) {
				env.getFlag().setCFlag(new BooleanValue(BitVector.getMSB(dest, bits)));
				dest = Convert.convetUnsignedValue(dest << 1, size);
			} else {
				env.getFlag().setCFlag(new BooleanValue(BitVector.getLSB(dest, bits)));

				if (inst.getName().startsWith("sar")) {
					// Fix by Khanh
					byte Mbit = BitVector.getMSB(dest, bits);						
					if ( Mbit == 1 ) {
						dest = Convert.convetUnsignedValue_Mbit_1(dest >> 1, size);
					}
					else {
						dest = Convert.convetUnsignedValue(dest >> 1, size);
					}
					//dest /= 2;
				} else {
					// SHR
					dest /= 2;
				}
			}

			tempCount--;
		}

		if ((count & 0x1F) == 1) {
			if (inst.getName().startsWith("sal") || inst.getName().startsWith("shl")) {
				BooleanValue temp = new BooleanValue(BitVector.getMSB(dest, bits));
				Value cFlag = env.getFlag().getCFlag();
				Value oFlag = new HybridBooleanValue(temp, "xor", cFlag);
				env.getFlag().setOFlag(oFlag);
			} else if (inst.getName().startsWith("sar")) {
				env.getFlag().setOFlag(new BooleanValue(false));
			} else {
				env.getFlag().setOFlag(new BooleanValue(BitVector.getMSB(tempDest, bits)));
			}

		} else {
			// OF undefined
		}

		long t = Convert.convertSignedValue(dest, bits);
		env.getFlag().setPFlag(new BooleanValue(BitVector.getParityBit(t)));
		env.getFlag().setSFlag(new BooleanValue(t < 0));
		env.getFlag().setZFlag(new BooleanValue(t == 0));

		return dest;
	}
}
