package com.froad.comon.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.froad.comon.constant.ParamCommand;

public class FileUtil {
	
	final static Logger logger = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 解压到指定目录
	 * 
	 * @param zipPath
	 * @param descDir
	 * 
	 */
	public static void unZipFiles(String zipPath, String descDir)
			throws IOException {
		unZipFiles(new File(zipPath), descDir);
	}
	
	/**
	 * 解压到指定文件名
	 * 
	 * @param zipPath
	 * @param descDir
	 * 
	 */
	public static void unZipFile(String zipPath, String descDir, String fileName)
			throws IOException {
		unZipFile(new File(zipPath), descDir, fileName);
	}

	/**
	 * 解压文件到指定目录
	 * 
	 * @param zipFile
	 * @param descDir
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFiles(File zipFile, String descDir)
			throws IOException {
		
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		ZipFile zip = new ZipFile(zipFile);
		
		for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
			
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = entry.getName();
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");

			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[4096];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		logger.info("******************解压完毕********************");
	}
	
	/**
	 * 解压单个文件到指定文件名
	 * 
	 * @param zipFile
	 * @param fileName
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void unZipFile(File zipFile, String descDir, String fileName)
			throws IOException {
			
		File pathFile = new File(descDir);
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		ZipFile zip = new ZipFile(zipFile);
		
		for (Enumeration entries = zip.getEntries(); entries.hasMoreElements();) {
			
			ZipEntry entry = (ZipEntry) entries.nextElement();
			String zipEntryName = fileName;
			InputStream in = zip.getInputStream(entry);
			String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");

			// 判断路径是否存在,不存在则创建文件路径
			File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
			if (!file.exists()) {
				file.mkdirs();
			}
			
			// 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
			if (new File(outPath).isDirectory()) {
				continue;
			}

			OutputStream out = new FileOutputStream(outPath);
			byte[] buf1 = new byte[4096];
			int len;
			while ((len = in.read(buf1)) > 0) {
				out.write(buf1, 0, len);
			}
			in.close();
			out.close();
		}
		logger.info("******************解压完毕********************");
	}
	
    /**
     * 删除单个文件
     * @param   sPath    被删除文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String sPath) {
        boolean flag = false;
        File file = new File(sPath);
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists()) {
            file.delete();
            flag = true;
        }
        return flag;
    }
	
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param   sPath 被删除目录的文件路径
     * @return  目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String sPath) {
    	
        //如果sPath不以文件分隔符结尾，自动添加文件分隔符
        if (!sPath.endsWith(File.separator)) {
            sPath = sPath + File.separator;
        }
        
        File dirFile = new File(sPath);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if (!dirFile.exists() || !dirFile.isDirectory()) {
            return false;
        }
        
        boolean flag = true;
        
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            //删除子文件
            if (files[i].isFile()) {
                flag = deleteFile(files[i].getAbsolutePath());
                if (!flag) break;
            } //删除子目录
            else {
                flag = deleteDirectory(files[i].getAbsolutePath());
                if (!flag) break;
            }
        }
        
        if (!flag){ 
        	return false;
        }
        
        //删除当前目录
        if (dirFile.delete()) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * 检查文件内容
     * @param fileName
     * @return 1正常，-1异常
     */
    public static int checkContent(String fileName){
    	File file = new File(fileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));

			String str = br.readLine();
			if(str == null || str.startsWith("error")){
				return -1;
			}
			return Integer.parseInt(str.trim());
		}catch(Exception e){
			return -1;
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (Exception e) {
					br = null;
				}
			}
		}
    }
    
    /**
     * 检查文件内容
     * @param fileName
     * @return 1正常，-1异常
     */
    public static int checkContent(String fileName, int begin, int end){
    	File file = new File(fileName);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));

			String str = br.readLine();
			if(str == null){
				return -1;
			}
			str = str.substring(begin, end);
			return Integer.parseInt(str.trim());
		}catch(Exception e){
			return -1;
		}finally{
			if(br != null){
				try {
					br.close();
				} catch (Exception e) {
					br = null;
				}
			}
		}
    }
		
    
    public static void main(String[] args)  {
    	String filePath = "E:/1001/1001.zip";
		String fileDir="E:/1001";
		try {
			FileUtil.unZipFiles(filePath, fileDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
