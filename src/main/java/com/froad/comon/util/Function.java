package com.froad.comon.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * 常用的方法
 * 
 * @author jwzhang
 */
public class Function {
	/**
	 * 将特殊的字符串 通过map数据的填充，产生一个新字符串 如 "你是：${name},姓名为：${age}" 输出结果
	 * （你是：李四,姓名为：123）
	 * 
	 * @Title: replaceTargetString
	 * @Description: TODO
	 * @param target
	 * @param map
	 * @return
	 * @return String
	 * @throws
	 */
	public static String replaceTargetString(String target,
			Map<String, Object> map) {
		if (target == null) {
			return null;
		}
		StringBuffer bfkey = new StringBuffer();
		StringBuffer bf = new StringBuffer();
		char[] array = target.toCharArray();
		int indexMark = -1;
		int start = -1;
		int end = -1;
		for (int i = 0, length = array.length; i < length; i++) {
			if (array[i] == '$') {
				indexMark = i;
				continue;
			} else if (array[i] == '{') {
				start = i;
				continue;
			} else if (array[i] == '}') {
				end = i;
			}
			if (indexMark + 1 == start) {
				if (end > start) {
					String key = bfkey.toString();
					Object value = map.get(key);
					String strValue = value == null ? "" : value.toString();

					bf.append(strValue);

					bfkey = new StringBuffer();
					indexMark = -1;
					start = -1;
					end = -1;
					continue;
				}
				bfkey.append(array[i]);
				continue;
			}
			bf.append(array[i]);
		}
		return bf.toString();
	}

	/**
	 * @Title: 两个数相除
	 * @Description: TODO
	 * @param target
	 *            被除数
	 * @param divisor
	 *            除数
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal divide(BigDecimal target, BigDecimal divisor) {
		return divide(target, divisor, false);
	}

	/**
	 * @Title: 两个数相除
	 * @Description: TODO
	 * @param target
	 *            被除数
	 * @param divisor
	 *            除数
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal divide(BigDecimal target, BigDecimal divisor,
			Boolean isParamsNullReturnZero) {
		if (target == null || divisor == null
				|| divisor.compareTo(new BigDecimal(0)) == 0) {
			if (isParamsNullReturnZero) {
				return new BigDecimal(0);
			} else {
				return null;
			}
		}
		return target.divide(divisor, 10, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * @Title: 两个数相加
	 * @Description: TODO
	 * @param target
	 * @param augend
	 *            要加的值
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal add(BigDecimal target, Object augend) {
		if (target == null && augend == null) {
			return null;
		}
		if (target != null && augend == null) {
			return target;
		}
		if (target == null && augend != null) {
			if (augend instanceof Integer) {
				return new BigDecimal((Integer) augend);
			} else if (augend instanceof Long) {
				return new BigDecimal((Long) augend);
			} else if (augend instanceof Double) {
				return new BigDecimal((Double) augend);
			} else if (augend instanceof Float) {
				return new BigDecimal((Float) augend);
			} else if (augend instanceof String) {
				return new BigDecimal((String) augend);
			} else if (augend instanceof BigDecimal) {
				return (BigDecimal) augend;
			} else {
				return new BigDecimal(augend.toString());
			}
		}
		if (augend instanceof Integer) {
			return target.add(new BigDecimal((Integer) augend));
		} else if (augend instanceof Long) {
			return target.add(new BigDecimal((Long) augend));
		} else if (augend instanceof Double) {
			return target.add(new BigDecimal((Double) augend));
		} else if (augend instanceof Float) {
			return target.add(new BigDecimal((Float) augend));
		} else if (augend instanceof String) {
			return target.add(new BigDecimal((String) augend));
		} else if (augend instanceof BigDecimal) {
			return target.add((BigDecimal) augend);
		} else {
			return new BigDecimal(augend.toString());
		}
	}

	/**
	 * @Title: 两个数相减
	 * @Description: TODO
	 * @param target
	 * @param subtrahend
	 *            要减的数
	 * @return
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal subtract(BigDecimal target, BigDecimal subtrahend) {
		if (target == null || subtrahend == null) {
			return null;
		}
		return target.subtract(subtrahend);
	}

	/**
	 * @Title: 两个数相乘
	 * @Description: TODO
	 * @param target
	 * @param multiplicand
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal multiply(BigDecimal target, BigDecimal multiplicand) {
		if (target == null || multiplicand == null) {
			return null;
		}
		return target.multiply(multiplicand);
	}

	/**
	 * @Title: 两个数相乘
	 * @Description: TODO
	 * @param target
	 * @param multiplicand
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal multiply(Double target, Double multiplicand) {
		if (target == null || multiplicand == null) {
			return null;
		}
		BigDecimal tmpA = new BigDecimal(target.toString());
		BigDecimal tmpB = new BigDecimal(multiplicand.toString());
		return tmpA.multiply(tmpB);
	}

	/**
	 * @Title: round 将指定的字符串 保留几位小数位
	 * @Description: TODO
	 * @param target
	 * @param scale
	 * @return
	 * @return BigDecimal
	 * @throws
	 */
	public static BigDecimal round(BigDecimal target, int scale) {
		if (target == null) {
			return null;
		}
		BigDecimal big2 = new BigDecimal(1);
		return target.divide(big2, scale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal round(String target, int scale) {
		if (target == null) {
			return null;
		}
		BigDecimal big2 = new BigDecimal(target);
		return big2.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal round(Double target, int scale) {
		if (target == null) {
			return null;
		}
		/** Double转String类型处理,修复四舍五入bug @author qwu @create 2014-01-06 **/
		BigDecimal big2 = new BigDecimal(String.valueOf(target));
		return big2.setScale(scale, BigDecimal.ROUND_HALF_UP);
	}

	/***
	 * 当前系统减去 开始时间， 用时
	 * @param startTime
	 * @return
	 */
	public  static Long getProcessTime(Long startTime){
		return System.currentTimeMillis()-startTime;
	}  

	public static void main(String[] args) {
		String [] aaa=new String[]{"张三","历史"};
		System.out.println(Arrays.toString(aaa));
	}

	
	
}
