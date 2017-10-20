package top.qiaojianyong.findcopy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FIndCopy {

	// public static Pattern FILE_PATTERN = Pattern.compile("[\\\\/:*?\"<>|]");
	private static Pattern KEY_PATTERN = Pattern.compile("\\w+:\\w+:\\w+");
	private static Matcher matcher;
	private static String path = "C:\\_QJY\\MyDevelop\\MyIdea\\dt\\src\\main\\java\\top\\qiaojianyong\\dt";

	public static void main(String[] args) throws Exception {
		System.out.println("start");
		PrintWriter print = new PrintWriter(
				new OutputStreamWriter(new FileOutputStream(path + "\\keysfound.txt"), "utf-8"), true);
		find(new File(path), print);
		print.close();
		System.out.println("end");
	}

	//一边找一边打印,驴推磨
	public static void find(File file, PrintWriter print) throws IOException {
		if (file != null)
			if (file.isFile()) {
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				boolean flag = false;
				while ((line = br.readLine()) != null) {
					matcher = KEY_PATTERN.matcher(line);
					while (matcher.find()) {
						flag = true;
						print.println(matcher.group());
					}
					System.out.println(file.getName());
				}
				if (flag) {
					//print.println(file.getName());
					print.println();
				}
				br.close();
			} else {
				for (File f : file.listFiles(new FilenameFilter() {

					@Override
					public boolean accept(File dir, String name) {
						return !name.equals("keysfound.txt"); // exclude
					}
				})) {
					find(f, print);
				}
			}
	}
}
