package dailyreport;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ex2 {

	public static void main(String[] args) {
		try {
			// https://mp3.zing.vn/top100/Nhac-Tre/IWZ9Z088.html
			// https://www.nhaccuatui.com/bai-hat/nhac-tre-moi.html
			// get URL content
			URL url = new URL("https://www.nhaccuatui.com/bai-hat/nhac-tre-moi.html");

			// open the stream and put it into BufferedReader
			Scanner scanner = new Scanner(new InputStreamReader(url.openStream()));
			scanner.useDelimiter("\\Z");
			String content = scanner.next();
			// System.out.println(content);
			// close scanner
			scanner.close();
			// remove all new line
			content = content.replaceAll("\\n+", "");
			// System.out.println(content);

			// regex
			Pattern p = Pattern.compile("item_content\">(.*?)name_song\">(.*?)</a>(.*?)<div class=\"list_mark");
			Matcher m = p.matcher(content);
			while (m.find()) {
				System.out.println(m.group(2));
			}
			System.out.println("Done");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
