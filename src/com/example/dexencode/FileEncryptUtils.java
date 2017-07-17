package com.example.dexencode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

public class FileEncryptUtils {
	static void encryptFile(String key, String sourceFilePath, String destFilePath) throws Exception {
        AESUtils.encryptFile(key, sourceFilePath, destFilePath);
    }
     
    static void decryptFile(String key, String sourceFilePath, String destFilePath) throws Exception {
        AESUtils.decryptFile(key, sourceFilePath, destFilePath);
    }
    public static byte[] fileToByte(String filePath) throws Exception {
		byte[] data = new byte[0];
		File file = new File(filePath);
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);
			ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
			byte[] cache = new byte[1024];
			int nRead = 0;
			while ((nRead = in.read(cache)) != -1) {
				out.write(cache, 0, nRead);
				out.flush();
			}
			out.close();
			in.close();
			data = out.toByteArray();
		}
		return data;
	}
}
