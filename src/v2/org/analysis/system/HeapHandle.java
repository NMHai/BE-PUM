package v2.org.analysis.system;

import java.util.ArrayList;
import java.util.List;

public class HeapHandle {
	List<Heap> hList;

	public HeapHandle() {
		hList = new ArrayList<Heap>();
	}

	public long creatHeap(long t2, long t3, long t1) {
		// TODO Auto-generated method stub
		Heap h = new Heap(t2, t3, t1);
		long handle = Math.round(Math.random() * Math.pow(10, 5));
		h.setHandle(handle);
		hList.add(h);

		return handle;
	}

	public long destroyHeap(long handle) {
		for (Heap h : hList) {
			if (h.getHandle() == handle) {
				hList.remove(h);
				return 1;
			}
		}

		return 0;
	}

	public long freeHeap(long handle, long freeFlag, long pointer) {
		for (Heap h : hList) {
			if (h.getHandle() == handle) {
				// Hien thuc sau
				return h.free(freeFlag, pointer);
				// return 1;
			}
		}

		return 0;
	}

}
