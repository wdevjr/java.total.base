import javax.swing.UIManager;
import javax.swing.WindowConstants;

import br.com.warhjr.ui.usuario.LoginUser;

public class Main {

	public static void main(String[] args) {
		try {
//			try {
//			    for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//			        if ("Nimbus".equals(info.getName())) {
//			            UIManager.setLookAndFeel(info.getClassName());
//			            break;
//			        }
//			    }
//			} catch (Exception e) {
//			   UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//			}
			try {
				// select Look and Feel
				UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
