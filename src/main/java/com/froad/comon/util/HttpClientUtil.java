package com.froad.comon.util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	 private final static Logger logger = Logger.getLogger(HttpClientUtil.class);
	
	/**
	 * 根据url下载文件，保存到filepath中
	 * @param url
	 * @param filepath
	 * @return
	 * @throws Exception 
	 */
	public static int download(String url, String filepath) throws Exception {
		try {
			HttpClient client = new DefaultHttpClient();
			HttpGet httpget = new HttpGet(url);
			HttpResponse response = client.execute(httpget);

			HttpEntity entity = response.getEntity();
			InputStream is = entity.getContent();
			File file = new File(filepath);
			file.getParentFile().mkdirs();
			FileOutputStream fileout = new FileOutputStream(file);
			
			byte[] buffer=new byte[1024];
			int ch = 0;
			ch = is.read(buffer);
			
			if(ch>0){
				fileout.write(buffer, 0, ch);
			}else{
				is.close();
				fileout.close();
				return 0;
			}
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			is.close();
			fileout.flush();
			fileout.close();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			 throw e;
			//return -1;
		}
		return 1;
	}
	
	/**下载文件*/
	public static String getDownLoad(String url,String filePath){
		String msg="";
		CloseableHttpClient  httpclient=null;
		String connectTimeout=PropertyUtil.getProperties("downLoadConnectTimeout");
		String scocketTimeout=PropertyUtil.getProperties("downLoadSocketTimeout");
		Integer  connectTimeoutInt;
		Integer  scocketTimeoutInt;
		if(StringUtils.isNotEmpty(connectTimeout)){
			connectTimeoutInt=Integer.parseInt(connectTimeout);
		}else{
			msg = String.format("连接超时时间为空！" );
			return msg;
		}
		if(StringUtils.isNotEmpty(scocketTimeout)){
			scocketTimeoutInt=Integer.parseInt(scocketTimeout);
		}else{
			msg = String.format("读取超时时间为空！" );
			return msg;
		}
		msg=getDownLoad(url, filePath, connectTimeoutInt, scocketTimeoutInt);
		return msg;
	}
	
	
	public static String getDownLoad(String url,String filePath,Integer connectTimeoutInt,Integer scocketTimeoutInt){
		String msg="";
		CloseableHttpClient  httpclient=null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()
					.setConnectTimeout(connectTimeoutInt) //设置连接超时
					.setSocketTimeout(scocketTimeoutInt).build(); //数据传输时间60s
			
			 httpclient = HttpClients.custom().setDefaultRequestConfig(requestConfig).build();
			//httpclient = HttpClients.createDefault();
			// 创建httpget.  
			HttpGet httpget = new HttpGet(url);
		    logger.info("下载对账文件URL：" + url);
			// 执行get请求.  
			CloseableHttpResponse response = httpclient.execute(httpget);
			try {
				// 获取响应实体  
				HttpEntity entity = response.getEntity();
				
				Header  dispositionHeader=response.getFirstHeader("Content-disposition");
				// 打印响应状态  
				 // 相应码
                int responseCode = response.getStatusLine().getStatusCode();
                // 判断相应码，非200视为错误。
                if (responseCode != 200) {
                	msg = String.format("错误响应码：" + responseCode);
                    logger.error(msg);
                }else{
                	if (entity != null&& dispositionHeader!=null && StringUtils.isNotEmpty(dispositionHeader.getValue())) {
                		int resultCode=downLoad(entity.getContent(),filePath);
                		if(resultCode<1){
                			msg = String.format("下载文件失败");
                			logger.error(msg);
                		}
                	}else{
                		msg = String.format("无对账文件下载",url);
        		    	logger.error(msg);
                	}
                }
			} finally {
				response.close();
			}
		}catch (Exception e) {
			msg = String.format("服务器内部错误：" + url);
            logger.error(msg, e);
        }finally {
			// 关闭连接,释放资源  
			try {
				if(httpclient!=null){
					httpclient.close();
				}
			} catch (IOException e) {
				msg = "io异常";
				logger.error( e);
			}
		}
		return msg;
	}
	
	public static int downLoad(InputStream is ,String filepath){
		FileOutputStream fileout=null;
		try {
			File file = new File(filepath);
			if(!file.getParentFile().exists()){
				file.getParentFile().mkdirs();
			}
			fileout = new FileOutputStream(file);
			
			byte[] buffer=new byte[1024];
			int ch = 0;
			while ((ch = is.read(buffer)) != -1) {
				fileout.write(buffer, 0, ch);
			}
			fileout.flush();
		}catch (Exception e) {
			logger.error(e);
			return -1;
		}finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e);
					return -1;
				}
			}
			if(fileout!=null){
				try {
					fileout.close();
				} catch (IOException e) {
					logger.error(e);
					return -1;
				}
			}
		}
		return 1;
	}
	
	public static void main(String[] args) throws Exception {
//	  
		String url="http://172.16.10.20:8087/QueryTransDetails.do?date=20150801&bankGroup=221";
		String filePath="E:\\abc.txt";
		getDownLoad(url, filePath,180000,120000);
	}
	
	
	
	
	
}
