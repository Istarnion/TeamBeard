class Hundred {

	private Player p1, p2;

	private int targetScore = 100;

	public Hundred() {
		p1 = new Player(targetScore);
		p2 = new Player(targetScore);

		for(int rounds=1; (p1.getScore() != targetScore && p2.getScore() != targetScore); rounds++) {
			System.out.println("\n----------------------------------------");
			System. out.println("ROUND: "+rounds);
			System.out.println("PLAYER 1 ROLL : "+p1.roll()+"    PLAYER 2 ROLL : "+p2.roll());
			System.out.println("PLAYER 1 SCORE: "+p1.getScore()+"   PLAYER 2 SCORE: "+p2.getScore());
		}

		if(p1.getScore() == targetScore && p2.getScore() == targetScore) {
			System.out.println("\n IT'S A DRAW");
		}
		else if(p1.getScore() == targetScore) {
			System.out.println("\n PLAYER 1 WON");
		}
		else {
			System.out.println("\n PLAYER 2 WON");
		}
	}

	public static void main(String[] args) {
		new Hundred();
	}

}