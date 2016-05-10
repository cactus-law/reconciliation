/**
 * 
 */
package com.froad.recon.reconciliation.common;

import java.util.Locale;

import org.springframework.context.MessageSource;

import com.froad.comon.util.Logger;
import com.froad.recon.reconciliation.flow.TradeResultSeparateFlow;

/**
 *	取页面的提示信息.
 */
public class PageMessageUtil {
	private final static Logger logger = Logger.getLogger(PageMessageUtil.class);
	private  MessageSource messageSource;
	/**
	 * 根据 返回的错误代码，得到错误描述，如果没有 返回默认描述.
	 * @param resource MessageSource
	 * @param fdReturnCode fd 返回的错误代码
	 * @return String
	 */
	public String getFdErrorCodeMessage(String fdReturnCode) {
		try {
			return messageSource.getMessage(fdReturnCode,null,Locale.CHINA);
		} catch (Exception e) {
			logger.error(fdReturnCode);
			return getDefaultFdError();
		}
	}
	/**
	 * 得到交易的默认错误提示.
	 * @param resource MessageSource
	 * @return
	 */
	public  String getDefaultFdError() {
		return messageSource.getMessage("9999",null,Locale.CHINA);
	}
	public MessageSource getMessageSource() {
		return messageSource;
	}
	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
}
