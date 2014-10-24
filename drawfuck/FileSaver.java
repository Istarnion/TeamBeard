import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
class FileSaver {

	private FileSaver() {
		// Nothing
	}

	public static void saveFile(boolean[][] barray) {
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
        "Text files", "txt");
		JFileChooser fileChooser = new JFileChooser("drawings/");
		fileChooser.setFileFilter(filter);
		int returnVal = fileChooser.showSaveDialog(null);

		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String output = "private boolean[][] scanArray = { \n";
			for(int i=0; i<barray[0].length; i++) {
				output = output.concat("{");
				for(int j=0; j<barray.length; j++) {
					output = output.concat((barray[j][i]?"true":"false")+", ");
				}
				output = output.concat("},\n");
			}
			output = output.concat("\n};");
			
			try {
				File file = fileChooser.getSelectedFile();
				if (!file.getName().endsWith(".txt")) {
					file = new File(file.getPath().concat(".txt"));
				}
				PrintWriter writer = new PrintWriter(file, "UTF-8");
				writer.print(output);
				writer.close();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}