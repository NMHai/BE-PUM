package v2.org.analysis.apihandle.winapi.structures;

public enum ConstantSocketAddressFamily {
	AF_UNSPEC(0) // unspecified
	, AF_UNIX(1) // local to host (pipes, portals)
	, AF_INET(2) // internetwork: UDP, TCP, etc.
	, AF_IMPLINK(3) // arpanet imp addresses
	, AF_PUP(4) // pup protocols: e.g. BSP
	, AF_CHAOS(5) // mit CHAOS protocols
	, AF_NS(6) // XEROX NS protocols //AF_NS
	, AF_ISO(7) // ISO protocols //AF_OSI
	, AF_ECMA(8) // european computer manufacturers
	, AF_DATAKIT(9) // datakit protocols
	, AF_CCITT(10) // CCITT protocols, X.25 etc
	, AF_SNA(11) // IBM SNA
	, AF_DECnet(12) // DECnet
	, AF_DLI(13) // Direct data link interface
	, AF_LAT(14) // LAT
	, AF_HYLINK(15) // NSC Hyperchannel
	, AF_APPLETALK(16) // AppleTalk
	, AF_NETBIOS(17) // NetBios-style addresses
	, AF_VOICEVIEW(18) // VoiceView
	, AF_FIREFOX(19) // Protocols from Firefox
	, AF_UNKNOWN1(20) // Somebody is using this!
	, AF_BAN(21) // Banyan
	, AF_ATM(22) // Native ATM Services
	, AF_INET6(23) // Internetwork Version 6
	, AF_CLUSTER(24) // Microsoft Wolfpack
	, AF_12844(25) // IEEE 1284.4 WG AF
	, AF_IRDA(26) // IrDA
	, AF_NETDES(28) // Network Designers OSI & gateway
	;

	private int value;

	ConstantSocketAddressFamily(int numVal) {
		this.value = numVal;
	}

	public int getNumVal() {
		return value;
	}

	public static ConstantSocketAddressFamily getValueFromNum(int _id) {
		ConstantSocketAddressFamily[] As = ConstantSocketAddressFamily.values();
		for (int i = 0; i < As.length; i++)
		{
			if (As[i].getNumVal() == _id)
				return As[i];
		}
		return ConstantSocketAddressFamily.AF_INET;
	}
}
