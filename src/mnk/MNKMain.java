package mnk;

import views.MainFrame;

/**
 * main function, where the program runs
 *
 */
public class MNKMain {
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
	                new MainFrame().setVisible(true);
	        }
	    });
	}
}
