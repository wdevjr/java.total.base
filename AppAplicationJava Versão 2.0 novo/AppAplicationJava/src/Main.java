import javax.swing.WindowConstants;

import br.com.warhjr.ui.MenuPrinc;
import br.com.warhjr.ui.usuario.LoginUser;

public class Main {
	
	public static void main(String[] args) {
		try {
			LoginUser dialog = new LoginUser();
			// MenuPrinc auxMenu = new MenuPrinc();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.pack();
			dialog.setLocationRelativeTo(null);
			dialog.setResizable(false);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
