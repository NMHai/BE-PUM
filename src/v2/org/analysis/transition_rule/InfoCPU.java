package v2.org.analysis.transition_rule;

public class InfoCPU {
	long EAX = 0;
	long EBX = 0;
	long ECX = 0;
	long EDX = 0;

	public long getEAX() {
		return EAX;
	}

	public long getEBX() {
		return EBX;
	}

	public long getECX() {
		return ECX;
	}

	public long getEDX() {
		return EDX;
	}

	public void CPUID(long temp_eax) {
		int temp = (int) temp_eax;
		switch (temp) {
		case 0:
			// EAX = HighestCPUIDInput(); //highest basic function input value
			// understood by CPUID
			case_0();
			break;
		case 1:
			/*
			 * EAX[0..3] = SteppingID; EAX[4..7] = Model; EAX[8..11] = Family;
			 * EAX[12..13] = ProcessorType; EAX[14..15] = Reserved; EAX[16..19]
			 * = ExtendedModel; EAX[20..23] = ExtendedFamily; EAX[24..31] =
			 * Reserved; EBX[0..7] = BrandIndex; EBX[8..15] = CLFLUSHLineSize;
			 * EBX[16..23] = Reserved; EBX[24..31] = InitialAPICID; ECX =
			 * ExtendedFeatureFlags; EDX = FeatureFlags;
			 */
			break;
		case 2:
			/*
			 * EAX = CacheAndTLBInformation(); EBX = CacheAndTLBInformation();
			 * //0 ECX = CacheAndTLBInformation(); //0 EDX =
			 * CacheAndTLBInformation();
			 */
			break;
		case 3:
			/*
			 * EAX = Reserved; EBX = Reserved; #ifdef PentiumIII ECX =
			 * ProcessorSerialNumber[0..31]; EDX =
			 * ProcessorSerialNumber[32..63]; #else ECX = Reserved; EDX =
			 * Reserved; #endif
			 */
			break;
		case 4:
			/*
			 * EAX = DeterministicCacheParametersLeaf(); EBX =
			 * DeterministicCacheParametersLeaf(); ECX =
			 * DeterministicCacheParametersLeaf(); EDX =
			 * DeterministicCacheParametersLeaf();
			 */
			break;
		case 5:
			/*
			 * EAX = MONITORMWAITLeaf(); EBX = MONITORMWAITLeaf(); ECX =
			 * MONITORMWAITLeaf(); EDX = MONITORMWAITLeaf();
			 */
			break;
		case 0x80000000:
			/*
			 * EAX = HighestExtendedCPUIDInput(); //highest extended function
			 * input value understood by CPUID EBX = Reserved; ECX = Reserved;
			 * EDX = Reserved;
			 */
			break;
		case 0x80000001:
			/*
			 * EAX = ExtendedProcessorFeatureSignature; //Currently Reserved EBX
			 * = Reserved; ECX = Reserved; EDX = Reserved;
			 */
			break;
		case 0x80000002:
			/*
			 * EAX = ProcessorBrandString[0..3]; EBX =
			 * ProcessorBrandString[4..7]; ECX = ProcessorBrandString[8..11];
			 * EDX = ProcessorBrandString[12..15];
			 */
			break;
		case 0x80000003:
			/*
			 * EAX = ProcessorBrandString[16..19]; EBX =
			 * ProcessorBrandString[20..23]; ECX = ProcessorBrandString[24..27];
			 * EDX = ProcessorBrandString[28..31];
			 */
			break;
		case 0x80000004:
			/*
			 * EAX = Reserved; EBX = Reserved; ECX = Reserved; EDX = Reserved;
			 */
			break;
		case 0x80000006:
			/*
			 * EAX = Reserved; EBX = Reserved; ECX = CacheInformation; EDX =
			 * Reserved;
			 */
			break;
		case 0x80000007:
			/*
			 * EAX = Reserved; EBX = Reserved; ECX = Reserved; EDX = Reserved;
			 */
			break;
		case 0x80000008:
			/*
			 * EAX = Reserved; EBX = Reserved; ECX = Reserved; EDX = Reserved;
			 */
			break;
		default: // EAX > highest value recognized by CPUID
			// undefined
			/*
			 * EAX = Reserved; EBX = Reserved; ECX = Reserved; EDX = Reserved;
			 */
			break;
		}
	}

	public void case_0() {
		// Chuoi bi so sanh
		String str1 = new String();
		str1 = "GenuineIntel";

		// Chuoi so sanh
		String str = new String();
		str = System.getenv("PROCESSOR_IDENTIFIER");

		int temp = -1;
		temp = str.indexOf(str1);

		if (temp != -1) {
			EBX = 1970169159;
			ECX = 1818588270;
			EDX = 1231384169;
		}

	}
}
