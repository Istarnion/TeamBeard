import lejos.nxt.*;



/** This is the true menu, the one true menu, and the only true menu.
 *  
 *  One menu to rule them all, one menu to find them, 
 *  one menu to bring them all, and in darkness bind them.
 */

public class MenuState implements MenuListener {
	//options
	private static final String COPY="Copy", MUSIC="Music Player", SLAVE="PC slave", CANCEL="Cancel";
	private Menu menu;

	/**
	 * 
	 * 
	 * 
	 */
	public MenuState(){
		menu = new Menu(new String[] {COPY, MUSIC, SLAVE, CANCEL}, this);
	}
	
	@Override
	public void onMenuEvent(String menuItem){
		switch (menuItem){
			case COPY:
				new CopyState().init();
			break;

			case MUSIC:
				new MusicState().init();
			break;

			case SLAVE:
			//new DrawState();

			case CANCEL:
				System.exit(0);
		}
		menu.drawMenu();
	}

	public void init() {
		menu.init();
	}

	public void resume() {
	}
	
}

