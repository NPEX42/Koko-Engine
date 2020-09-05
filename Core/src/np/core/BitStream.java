package np.core;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class BitStream implements Iterable<Integer> {
	private BitStreamIterator iter;
	public BitStream() {
		iter = new BitStreamIterator(256);
	}
	
	public BitStream(InputStream stream) {
		
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return iter;
	}
	
	private class BitStreamIterator implements Iterator<Integer> {
		int[] data;
		int ptr;
		
		@SuppressWarnings("unused")
		public BitStreamIterator(int[] data) {
			this.data = data;
		}
		
		public BitStreamIterator(int size) {
			this.data = new int[size];
		}

		@Override
		public boolean hasNext() {
			return ptr < data.length;
		}

		@Override
		public Integer next() {
			return (data[ptr++] == 1 ? 1 : 0);
		}
		
	}
}

