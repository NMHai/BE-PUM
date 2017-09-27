package v2.org.analysis.apihandle.winapi.structures;

public enum ConstantSocketProtocol {
	IPPROTO_ICMP(1), 
	IPPROTO_IGMP(2), 
	BTHPROTO_RFCOMM(3), 
	IPPROTO_IPV4(4), 
	IPPROTO_ST(5), 
	IPPROTO_TCP(6), 
	IPPROTO_CBT(7), 
	IPPROTO_EGP(8), 
	IPPROTO_IGP(9), 
	IPPROTO_PUP(12), 
	IPPROTO_UDP(17), 
	IPPROTO_IDP(22), 
	IPPROTO_IPV6(41), // IPv6 header
	IPPROTO_ROUTING(43), // IPv6 Routing header
	IPPROTO_FRAGMENT(44), // IPv6 fragmentation header
	IPPROTO_ESP(50), // encapsulating security payload
	IPPROTO_AH(51), // authentication header
	IPPROTO_ICMPV6(58), // ICMPv6
	IPPROTO_NONE(59), // IPv6 no next header
	IPPROTO_DSTOPTS(60), // IPv6 Destination options
	IPPROTO_ND(77),
	IPPROTO_ICLFXBM(78), 
	IPPROTO_PIM(103), 
	IPPROTO_PGM(113), 
	IPPROTO_L2TP(115), 
	IPPROTO_SCTP(132), 
	IPPROTO_RAW(255),
	IPPROTO_MAX(256), 
	IPPROTO_RESERVED_RAW(257), 
	IPPROTO_RESERVED_IPSEC(258), 
	IPPROTO_RESERVED_IPSECOFFLOAD(259), 
	IPPROTO_RESERVED_WNV(260), 
	IPPROTO_RESERVED_MAX(261), 
	IPPROTO_RM(113);

	private int value;

	ConstantSocketProtocol(int numVal) {
		this.value = numVal;
	}

	public int getNumVal() {
		return value;
	}

	public static ConstantSocketProtocol getValueFromNum(int _id) {
		ConstantSocketProtocol[] As = ConstantSocketProtocol.values();
		for (int i = 0; i < As.length; i++)
		{
			if (As[i].getNumVal() == _id)
				return As[i];
		}
		return ConstantSocketProtocol.IPPROTO_TCP;
	}
}
