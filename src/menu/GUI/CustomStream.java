package menu.GUI;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomStream extends OutputStream {
	
	//Fields for CustomStream
	private JTextArea textArea;
	
	//Constructor
	public CustomStream(JTextArea text) {
		this.textArea = text;
	}
	
	@Override
	public void write(int b) throws IOException {
		textArea.append(String.valueOf((char)b));
	}

}
