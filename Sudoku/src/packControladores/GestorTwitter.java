package packControladores;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GestorTwitter {
	private static GestorTwitter miGestorTwitter;
	
	private GestorTwitter() {
		// TODO Auto-generated constructor stub
	}

	public static GestorTwitter getGestorTwitter(){
		if(miGestorTwitter==null){
			miGestorTwitter= new GestorTwitter();
		}
		return miGestorTwitter;
	}
	
	public void compartirEnTwitter(String texto) throws IOException, URISyntaxException{
		 Desktop d = Desktop.getDesktop();
		 d.browse(new URI("https://twitter.com/intent/tweet?text="+texto));
	}
}
