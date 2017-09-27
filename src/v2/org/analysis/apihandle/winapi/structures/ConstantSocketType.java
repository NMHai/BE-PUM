package v2.org.analysis.apihandle.winapi.structures;

public enum ConstantSocketType {
	SOCK_STREAM(1), /* stream socket */
	SOCK_DGRAM(2), /* datagram socket */
	SOCK_RAW(3), /* raw-protocol interface */
	SOCK_RDM(4), /* reliably-delivered message */
	SOCK_SEQPACKET(5) /* sequenced packet stream */
	;

	private int value;

	ConstantSocketType(int numVal) {
		this.value = numVal;
	}

	public int getNumVal() {
		return value;
	}

	public static ConstantSocketType getValueFromNum(int _id) {
		ConstantSocketType[] As = ConstantSocketType.values();
		for (int i = 0; i < As.length; i++)
		{
			if (As[i].getNumVal() == _id)
				return As[i];
		}
		return ConstantSocketType.SOCK_STREAM;
	}
}
