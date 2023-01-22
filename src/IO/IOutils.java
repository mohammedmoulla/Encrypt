package IO;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
public class IOutils {
	static PrintWriter writer;
	public static void  writeAfile(String path,String toWrite) throws IOException {
		if (path=="null") return;
		path=path+".txt";
		OutputStream outputStream = new FileOutputStream(path);
		Writer writer = new OutputStreamWriter(outputStream,Charset.forName("UTF-8"));
		writer.write(toWrite);
		writer.close();
	}
	public static String readAfile(String path) throws IOException {
		String resString="";
		path=path+".txt";
		InputStream inputStream = new FileInputStream(path);
		Reader reader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
		int data = reader.read();
		while(data != -1){
		    char theChar = (char) data;
				data = reader.read();
		   resString+=theChar;
		}
			reader.close();
		return resString;
	}
}
