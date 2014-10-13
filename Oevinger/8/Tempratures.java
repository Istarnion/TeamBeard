class Temperatures {

	private float[][] temperatures;	// float makes sense, since we're talking about rain. Pun intended.

	public Temperatures(int days, int hours) {
		temperatures = new float[days][hours];
	}

	public void setTemperature(int day, int hour, float temp) {
		if(day >= 0 && day < temperatures.length
			&& hour >= 0 && hour < temperatures[0].length) {
			temperatures[day][hour] = temp;
		}
	}

	public float getTemperature(int day, int hour) {
		if(day >= 0 && day < temperatures.length
			&& hour >= 0 && hour < temperatures[0].length) {
			return temperatures[day][hour];
		}
		else {
			return -1;
		}
	}

	public float[] getDailyMedianTemps() {
		float[] mmt = new float[temperatures.length];
		float t = 0;
		for(int i=0; i<mmt.length; i++) {
			t = 0;
			for(int j=0; j<temperatures[0].length; j++) {
				t += temperatures[i][j];
			}
			mmt[i] = t/temperatures[0].length;
		}
		return mmt;
	}

	public float[] getHourlyMedianTemps() {
		float[] mmt = new float[temperatures[0].length];
		float t = 0;
		for(int i=0; i<mmt.length; i++) {
			t = 0;
			for(int j=0; j<temperatures.length; j++) {
				t += temperatures[j][i];
			}
			mmt[i] = t/temperatures.length;
		}
		return mmt;
	}

	public float getMedianTemp() {
		float t = 0;
		for(int i=0; i<temperatures.length; i++) {
			t = 0;
			for(int j=0; j<temperatures[0].length; j++) {
				t += temperatures[i][j];
			}
		}

		return t/(temperatures.length*temperatures[0].length);
	}

	public int[] getGroupedTemps() {
		int[] gt = new int[5];
		float[] mmt = getDailyMedianTemps();
		for(float t : mmt) {
			if(t<-5) {
				gt[0]++;
			}
			else if(t<=0) {
				gt[1]++;
			}
			else if(t<=5) {
				gt[2]++;
			}
			else if(t<=10) {
				gt[3]++;
			}
			else {
				gt[4]++;
			}
		}
		return gt;
	}

	//-----TEST CLIENT-----//
	public static void main(String[] args) {
		Temperatures temps = new Temperatures(10, 4);
		for(int i=0; i<10; i++) {
			for(int j=0; j<4; j++) {
				temps.setTemperature(i, j, (float)(new java.util.Random().nextFloat()-0.5)*50); // Bad practice GO!! Generates nums from -25 to 25
			}
		}

		System.out.println("\nTEMPERATURES\n");
		System.out.println("Average temp:		  "+temps.getMedianTemp());
		float[] tmps = temps.getDailyMedianTemps();
		for(int i=0; i<tmps.length; i++) {
			System.out.println("Average temp of day "+i+"  is "+tmps[i]);
		}
		tmps = temps.getHourlyMedianTemps();
		for(int i=0; i<tmps.length; i++) {
			System.out.println("Average temp of hour "+i+" is "+tmps[i]);
		}
		int[] days = temps.getGroupedTemps();
		System.out.println("There are "+days[0]+" days with average temp less than -5");
		System.out.println("There are "+days[1]+" days with average temp between -5 and 0");
		System.out.println("There are "+days[2]+" days with average temp between 0 and 5");
		System.out.println("There are "+days[3]+" days with average temp between 5 and 10");
		System.out.println("There are "+days[4]+" days with average temp greater than 10");
	}
}
