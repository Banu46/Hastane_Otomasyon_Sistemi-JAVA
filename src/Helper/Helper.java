package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.yesButtonText","Evet");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
	}
	
	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		switch(str){
		case "fill":
			msg = "Lütfen tüm alanları doldurunuz !";
			break;
		case "success":
			msg = "İşlem başarılı !";
			break;
		case "error":
			msg = "Bir hata gerçekleşti!";
		default:
			msg = str;
			break;
		}
		
		JOptionPane.showMessageDialog(null, msg, "Mesaj",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean confirm(String str) {
		String msj;
		switch(str){
		case "sure":
			msj = "Bu işlemi gerçekleştirmek istiyor musunuz?";
			break;
		default:
			msj = str;
			break;
		}
		
		int res = JOptionPane.showConfirmDialog(null, msj,"Dikkat !",JOptionPane.YES_NO_OPTION);
		if(res==0)
			return true;
		else
			return false;
		
	}
}
