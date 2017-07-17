package com.example.dexencode;

public class Main {
	private static final String root = "C:";
	private static final String path = root + "/Users/yixianglin/Desktop/test";
	private static final String path_encrypt_source = path + "/mycode.jar";
	private static final String path_encrypt = path+"/mycode.dat";
	private static String cmd_dex2jar = "cmd /c "+root+" && cd "+path+ " && jar cvf " 
			+ path_encrypt_source + " classes.dex";
	
	
	private static String key="GiEhjghmZIO7RTWyycQ9PQ==";
	/*
	 * 需要生成新的秘钥时使用
	 */
//	static {
//		try {
//			key = AESUtils.getSecretKey();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	public static void main(String[] args) throws Exception {
		dex2Jar();	//给dex打成jar包
		encrypt();	//对jar加密并生成为.dat文件
	}
	
	/**
	 * 给dex打成加密jar包
	 */
	private static void dex2Jar() throws Exception{
		System.out.println("************** 开始生成jar包 ***********");
		excuteCmd(cmd_dex2jar);
		System.out.println("************** jar包生成完成 ***********");
	}
	
	
	private static void excuteCmd(String cmd) throws Exception{
		Runtime runtime = Runtime.getRuntime();
		Process process = runtime.exec(cmd);
		StreamGobbler errorGobbler = new StreamGobbler(process.getErrorStream(), "ERROR");
		
		errorGobbler.start();
		StreamGobbler outGobbler = new StreamGobbler(process.getInputStream(), "STDOUT");
		
		outGobbler.start();
//		InputStream is = process.getInputStream();
//		Scanner s=new Scanner(is, "utf-8");
//		while(s.hasNextLine()){
//			System.out.println(s.nextLine());
//		}
//		InputStream is_error = process.getErrorStream();
//		Scanner s_error=new Scanner(is_error, "utf-8");
//		while(s_error.hasNextLine()){
//			System.out.println(s_error.nextLine());
//		}		
//		is.close();   
//		is_error.close();   
//        s.close();
//        s_error.close();
		process.waitFor();
	}
	
	/**
	 * 对jar加密并生成为.dat文件
	 * @throws Exception
	 */
	private static void encrypt() throws Exception{
        System.out.println("加密密钥和解密密钥：" + key);  
        FileEncryptUtils.encryptFile(key, path_encrypt_source, path_encrypt);
//        FileEncryptUtils.decryptFile(key, path_decrypt_source, path_decrypt);
        System.out.println("************** 加密后的data文件生成成功 ***********");  
	}
	
}

