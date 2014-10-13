import java.util.*;

class Player {

	private int score = 0;

	private Random rnd;

	private int targetScore;

	public Player(int targetScore) {
		this.targetScore = targetScore;
		rnd = new Random();
	}

	public int roll() {
		int r = rnd.nextInt(6)+1;

		if(r == 1) score = 0;
		else if(score>targetScore) score -= r;
		else score += r;

		return r;
	}

	public int getScore() {
		return score;
	}
}
