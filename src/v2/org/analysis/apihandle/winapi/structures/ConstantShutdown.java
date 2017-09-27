package v2.org.analysis.apihandle.winapi.structures;

public enum ConstantShutdown {

	SD_RECEIVE(0), // Shutdown receive operations.
	SD_SEND(1), // Shutdown send operations.
	SD_BOTH(2); // Shutdown both send and receive operations.

	private int value;

	ConstantShutdown(int numVal) {
		this.value = numVal;
	}

	public int getNumVal() {
		return value;
	}

	public static ConstantShutdown getValueFromNum(int _id) {
		ConstantShutdown[] As = ConstantShutdown.values();
		for (int i = 0; i < As.length; i++)
		{
			if (As[i].getNumVal() == _id)
				return As[i];
		}
		return ConstantShutdown.SD_BOTH;
	}
}
