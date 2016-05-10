package com.froad.recon.importfile.handler;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对账文件处理工厂类
 * @author Administrator
 */
public class FileHandlerFactory {
    final static Logger logger = LoggerFactory.getLogger(FileHandlerFactory.class);

    private Map<String,FileGetProcessor> fileGetProcessorMap;
    private Map<String,FileAnalysisProcessor> fileAnalysisProcessorMap;
    private Map<String,FileCheckProcessor> fileCheckProcessorMap;
    private Map<String,DataDealProcessor> dataDealProcessorMap;

	public Map<String, FileGetProcessor> getFileGetProcessorMap() {
		return fileGetProcessorMap;
	}

	public void setFileGetProcessorMap(
			Map<String, FileGetProcessor> fileGetProcessorMap) {
		this.fileGetProcessorMap = fileGetProcessorMap;
	}

	public Map<String, FileAnalysisProcessor> getFileAnalysisProcessorMap() {
		return fileAnalysisProcessorMap;
	}

	public void setFileAnalysisProcessorMap(
			Map<String, FileAnalysisProcessor> fileAnalysisProcessorMap) {
		this.fileAnalysisProcessorMap = fileAnalysisProcessorMap;
	}

	public Map<String, FileCheckProcessor> getFileCheckProcessorMap() {
		return fileCheckProcessorMap;
	}

	public void setFileCheckProcessorMap(
			Map<String, FileCheckProcessor> fileCheckProcessorMap) {
		this.fileCheckProcessorMap = fileCheckProcessorMap;
	}

	public Map<String, DataDealProcessor> getDataDealProcessorMap() {
		return dataDealProcessorMap;
	}

	public void setDataDealProcessorMap(
			Map<String, DataDealProcessor> dataDealProcessorMap) {
		this.dataDealProcessorMap = dataDealProcessorMap;
	}

	public  FileGetProcessor getFileGetProcessor(String key){
       if(fileGetProcessorMap!=null){
    	  return fileGetProcessorMap.get(key);
       }else{
    	   return null;
       }
	}
	
	public  FileAnalysisProcessor getFileAnalysisProcessor(String key){
		if(fileAnalysisProcessorMap!=null){
			return fileAnalysisProcessorMap.get(key);
		}else{
			return null;
		}
	}
	
	public  FileCheckProcessor getFileCheckProcessor(String key){
		if(fileCheckProcessorMap!=null){
			return fileCheckProcessorMap.get(key);
		}else{
			return null;
		}
	}
	
	public  DataDealProcessor getDataDealProcessor(String key){
		if(dataDealProcessorMap!=null){
			return dataDealProcessorMap.get(key);
		}else{
			return null;
		}
	}
	
}
