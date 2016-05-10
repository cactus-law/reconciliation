package com.froad.comon.dao.impl;

/**
 * hql参数设置类,为空，刚根据索引设置值
 * 
 */
public class ParamPattern {
	/** 匹配模式 '%值' **/
	public final static String LIKE_PATTERN_START = "1";
	/** 匹配模式 '值%' **/
	public final static String LIKE_PATTERN_END = "2";
	/** 匹配模式 '%值%' **/
	public final static String LIKE_PATTERN_ANYWHERE = "3";
	/** 匹配模式 '值' **/
	public final static String LIKE_PATTERN_NONE = "4";

	private Object value;
	private boolean like;
	private String likeMode;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public boolean isLike() {
		return like;
	}

	public void setLike(boolean like) {
		this.like = like;
	}

	/**
	 * @param value
	 *            属性值
	 * @param like
	 *            是否模糊查询
	 * @param likeMode
	 *            匹配模式
	 */
	public ParamPattern(Object value, boolean like, String likeMode) {
		this.value = value;
		this.like = like;
		this.likeMode = likeMode;
	}

	public ParamPattern() {
	}

	public String getLikeMode() {
		return likeMode;
	}

	public void setLikeMode(String likeMode) {
		this.likeMode = likeMode;
	}
}