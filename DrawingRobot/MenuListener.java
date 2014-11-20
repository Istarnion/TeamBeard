import lejos.nxt.*;

/**
*	MenuListener.java
*
*	Simple and general interface for transmitting menu events.
*	@author TeamBeard
*/

interface MenuListener {

	/**
	*	This is called by the Menu-object when a user preforms a menu event.
	*	@param menuItem The name of the menu item selected by the user. It's the same as what is represented on the display
	*/
	public void onMenuEvent(String menuItem);
}