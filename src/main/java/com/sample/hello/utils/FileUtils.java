package com.sample.hello.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class FileUtils {

	public static final String DEFAULT_ENCODING = "UTF-8";

	public static byte[] readFile(String filePath) throws Exception {
		File file = new File(filePath.trim());
		return readFile(file);
	}

	public static byte[] readFile(File file) throws Exception {
		byte data[] = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1024);
		try {
			fis = new FileInputStream(file);
			copyStream(fis, bos);
			data = bos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(fis);
			close(bos);
		}
		return data;
	}

	public static String readFile(String filePath, String encoding)
			throws Exception {
		return readFile(new File(filePath.trim()), encoding);
	}

	public static String readFile(File file, String encoding) throws Exception {
		String content = "";
		if (file == null) {
			return content;
		} else {
			byte buf[] = readFile(file);
			content = new String(buf, encoding);
			return content;
		}
	}

	public static void writeFile(InputStream is, String filePath)
			throws Exception {
		writeFile(is, new File(filePath.trim()));
	}

	public static void writeFile(InputStream is, File destFile)
			throws Exception {
		copyStream(is, new FileOutputStream(destFile));
	}

	public static void writeFile(byte content[], String filePath)
			throws Exception {
		writeFile(content, new File(filePath.trim()));
	}

	public static void writeFile(byte content[], File destFile)
			throws Exception {
		FileOutputStream fos = null;
		try {
			checkAndCreateParentDir(destFile);
			fos = new FileOutputStream(destFile);
			fos.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			close(fos);
		}
	}

	public static void writeFile(String content, String filePath)
			throws Exception {
		writeFile(content, new File(filePath));
	}

	public static void writeFile(String content, File destFile)
			throws Exception {
		BufferedWriter bw = null;
		FileOutputStream fos = null;
		try {
			checkAndCreateParentDir(destFile);
			fos = new FileOutputStream(destFile);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(content, 0, content.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(bw);
			close(fos);
		}
	}

	public static void writeFile(String content, String filePath,
			String encoding) throws Exception {
		writeFile(content, new File(filePath.trim()), encoding);
	}

	public static void writeFile(String content, File destFile, String encoding)
			throws Exception {
		BufferedWriter bw = null;
		FileOutputStream fos = null;
		try {
			checkAndCreateParentDir(destFile);
			fos = new FileOutputStream(destFile);
			bw = new BufferedWriter(new OutputStreamWriter(fos, encoding));
			bw.write(content, 0, content.length());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(bw);
			close(fos);
		}
	}

	public static void copyStream(InputStream from, OutputStream to)
			throws IOException {
		copyStream(from, to, 1024 * 10);
	}

	public static void copyStream(InputStream from, OutputStream to,
			int bufferSize) throws IOException {
		BufferedInputStream inStream = new BufferedInputStream(from);
		BufferedOutputStream outStrean = new BufferedOutputStream(to);
		byte b[] = new byte[bufferSize];
		for (int len = 0; (len = inStream.read(b)) > 0;)
			outStrean.write(b, 0, len);

		outStrean.flush();
	}

	public static void close(Closeable stream) {
		if (stream == null)
			return;
		try {
			stream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean exists(String filePath) {
		File file = new File(filePath.trim());
		return file.exists();
	}

	public static boolean filesInDir(String filePath) {
		File file = new File(filePath.trim());
		if (file.exists() && file.isDirectory()) {
			String fileNames[] = file.list();
			if (fileNames != null && fileNames.length > 0)
				return true;
		}
		return false;
	}

	public static int countLines(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		int lineCount = 0;
		try {
			while (reader.readLine() != null)
				lineCount++;
		} finally {
			reader.close();
		}
		return lineCount;
	}

	public static boolean renameTo(String srcFilePath, String destFilePath) {
		return renameTo(new File(srcFilePath), new File(destFilePath));
	}

	public static boolean renameTo(File src, File dest) {
		File parentDir = dest.getParentFile();
		if (parentDir != null && !parentDir.exists())
			dest.getParentFile().mkdirs();
		return src.renameTo(dest);
	}

	public static String[] listFiles(String dir, String prefix)
			throws Exception {
		return listFiles(dir, prefix, true);
	}

	public static String[] listFiles(String dir, String pattern, boolean prefix)
			throws Exception {
		File dirFile = new File(dir);
		if (dirFile == null || !dirFile.exists() || !dirFile.isDirectory())
			return new String[0];
		List<String> list = new Vector<String>();
		String files[] = dirFile.list();
		String arr$[] = files;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			String file = arr$[i$];
			boolean add = false;
			if (prefix)
				add = file.startsWith(pattern);
			else
				add = file.endsWith(pattern);
			if (add)
				list.add(file);
		}

		files = new String[list.size()];
		list.toArray(files);
		return files;
	}

	public static String getSuffix(String fileName) {
		int index = fileName.lastIndexOf(".");
		return fileName.substring(index + 1);
	}

	public static boolean checkAndCreateParentDir(File file) {
		File parentFile = file.getParentFile();
		if (!parentFile.exists())
			return parentFile.mkdirs();
		else
			return true;
	}

	public static void createDir(String dirPath) {
		File dirFile = new File(dirPath);
		if (!dirFile.exists())
			dirFile.mkdirs();
	}

	public static void removeDir(String dir) {
		File f = new File(dir);
		removeDir(f);
	}

	public static void removeFile(String filePath) {
		File f = new File(filePath);
		f.delete();
	}

	public static void removeDir(File f) {
		if (!f.exists()) {
			return;
		} else {
			Stack<File> sk = new Stack<File>();
			sk.push(f);
			delete(sk);
			return;
		}
	}

	private static void delete(Stack<File> sk) {
		do {
			if (sk.empty())
				break;
			File ff = (File) sk.pop();
			if (!ff.delete()) {
				sk.push(ff);
				File fs[] = ff.listFiles();
				File arr$[] = fs;
				int len$ = arr$.length;
				int i$ = 0;
				while (i$ < len$) {
					File element = arr$[i$];
					if (element.isDirectory())
						sk.push(element);
					if (element.isFile())
						element.delete();
					i$++;
				}
			}
		} while (true);
	}

	public static void clearDir(String dir) {
		File f = new File(dir);
		clearDir(f);
	}

	public static void clearDir(File f) {
		if (!f.exists())
			return;
		Stack<File> sk = new Stack<File>();
		File files[] = f.listFiles();
		File arr$[] = files;
		int len$ = arr$.length;
		for (int i$ = 0; i$ < len$; i$++) {
			File file = arr$[i$];
			sk.push(file);
		}

		delete(sk);
	}
}
