import lejos.nxt.*;



/** This is the true menu, the one true menu, and the only true menu.
 *  
 *  One menu to rule them all, one menu to find them, 
 *  one menu to bring them all, and in darkness bind them.
 */

public class MenuState implements State, MenuListener {
	//options
	private static final String COPY="Copy", MUSIC="Music Player", DRAW="Draw", CANCEL="Cancel";
	private Menu menu;

	private StateMachine sam;
	/**
	 * 
	 * 
	 * 
	 */
	public MenuState(StateMachine sam){
		this.sam=sam;
		
		menu = new Menu(new String[] {COPY, MUSIC, DRAW, CANCEL}, this);

	}
	
	@Override
	public void onMenuEvent(String menuItem){
		switch (menuItem){
			case COPY:
			sam.push(new CopyState());
			break;

			case MUSIC:
			sam.push(new MusicState(sam));
			break;

			case DRAW:
			//sam.push(new DrawState());

			case CANCEL:
			System.exit(0);
		}
	}



	@Override
	public void init() {
			menu.init();

	}
	public void resume() {
	}
	
}

