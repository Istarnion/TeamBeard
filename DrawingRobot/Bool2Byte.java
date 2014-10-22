import java.util.Random;

class Bool2Byte {
	public static void main(String[] args) {
		Random randy = new Random(100);
		boolean[] barray = new boolean[64];
		barray[0] = false;
		barray[1] = false;
		barray[2] = false;
		barray[3] = false;
		barray[4] = false;
		barray[5] = false;
		barray[6] = false;
		barray[7] = true;

		for(int i=8; i<64; i++) {
			barray[i] = randy.nextBoolean();
		}
		short[] sarray = convert(barray);
		for(short s : sarray) {
			System.out.println(s);
		}

		short s = sarray[0];
		System.out.println("Clipped: "+(s & ~(3<<6)));
	}

	private static short[] convert(boolean[] barray) {
		if(barray.length>64) return null;
		short total;
		short[] output = new short[8];
		int x = 0;
		for(int i=0; i<8; i++) {
			total = 0;
			for(int j=0; j<8; j++) {
				total += ((barray[i*8+j]?1:0)<<i);
			}
			output[i] = total;
			System.out.println(output[i]);
		}
		return output;
	}
}