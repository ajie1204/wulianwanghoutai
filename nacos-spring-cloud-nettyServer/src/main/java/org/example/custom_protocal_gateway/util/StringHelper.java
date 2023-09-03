package org.example.custom_protocal_gateway.util;

import java.util.*;

/**
 * 类名 StringHelper
 * 描述 字符串工具类
 *
 * @author hedonglin
 * @version 1.0
 * @date 2019/8/30 10:24
 */
public class StringHelper {

	/**
	 * 方法描述: 判断字符串是否相等
	 * @param str1 字符串
	 * @param str2 字符串
	 * @author hedonglin
	 * @date 2019/9/25 17:45
	 * @return {@link boolean} true/false
	 */
	public static boolean equals(String str1, String str2){
		if (str1 == null && str2 == null){
			return true;
		}
		boolean b1 = str1 != null && str1.equals(str2);
		boolean b2 = str2 != null && str2.equals(str1);
		if (b1 || b2){
			return true;
		}
		return false;
	}

	/**
	 * 方法描述: 判断字符串为空
	 * @param str 字符串
	 * @author hedonglin
	 * @date 2019/9/25 17:45
	 * @return {@link boolean} true/false
	 */
	public static boolean isBlank(String str){
		if (str == null || "".equals(str.trim())){
			return true;
		}
		return false;
	}

	/**
	 * 方法描述: 判断字符串不为空
	 * @param str 字符串
	 * @author hedonglin
	 * @date 2019/9/25 17:45
	 * @return {@link boolean} true/false
	*/
	public static boolean isNotBlank(String str){
		return !isBlank(str);
	}
	
	
	/**
	 * <br\>字符串拆分为List
	 * @param str 字符串 
	 * @param delimit 分隔符
	 * @param isTrim 是否需要去空格
	 * @return List<String>
	 */
	public static List<String> splitToList(String str ,String delimit,boolean isTrim){
		if (StringHelper.isBlank(str)) {
			return Collections.emptyList();
		}
		
		String[] split = (isTrim ? str.trim() : str ).split(delimit);
		if (split.length == 0) {
			return Collections.emptyList();
		}
		
		if (!isTrim) {
			return Arrays.asList(split);
		}
		
		List<String> list = new ArrayList<>();
		
		for (String s : split) {
			// split string has blank
			if (StringHelper.isBlank(s.trim())) {
				continue;
			}
			list.add(s.trim());
		}
		
		return list;
	}
	
	/**
	 * <br\>字符串拆分为List(默认不去空格)
	 * @param str 字符串 
	 * @return List<String>
	 */
	public static List<String> splitToList(String str ,boolean isTrim){
		return splitToList(str,",",isTrim);
	}
	
	/**
	 * <br\>字符串拆分为List(默认不去空格)
	 * @param str 字符串 
	 * @param delimit 分隔符
	 * @return List<String>
	 */
	public static List<String> splitToList(String str ,String delimit){
		return splitToList(str,delimit,false);
	}
	
	
	/**
	 * <br\>字符串拆分为List(默认不去空格，都好分割)
	 * @param str 字符串 
	 * @return List<String>
	 */
	public static List<String> splitToList(String str){
		return splitToList(str,",",false);
	}
	
	
	/**
	 * <br/>判断字符串数组是否全为空，
	 * @param strs 字符串
	 * @return boolean
	 */
	public static boolean isAllBlank(String... strs) {
		for (String str : strs) {
			if (!StringHelper.isBlank(str)) {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * <br/>判断字符串数组是否不全为空，
	 * @param strs 字符串
	 * @return boolean
	 */
	public static boolean hasNotBlank(String... strs) {
		return !isAllBlank(strs);
	}
	
	
	/**
	 * <br/>判断字符串数组是否有为空
	 * @param strs 字符串数组
	 * @return boolean
	 */
	public static boolean hasBlank(String... strs) {
		for (String str : strs) {
			if (StringHelper.isBlank(str)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 方法描述: 从转换的XML的Map中获取某个Map类型的Key
	 * @param map xmlMap
	 * @author hedonglin
	 * @date 2019/9/2 16:54
	 * @return {@link Map} Map
	*/
	@SuppressWarnings("all")
	public static Map<String, Object> getMapByMap(Map<String, Object> map, String key){
		if (StringHelper.isBlank(key)){
			return Collections.emptyMap();
		}

		Object headObj = map.get(key);

		if (headObj == null) {
			return Collections.emptyMap();
		}

		if (headObj instanceof Map) {
			return (Map<String, Object>) headObj;
		}

		if (headObj instanceof List){
			List list = (List)headObj;
			if (list.size() == 0){
				return Collections.emptyMap();
			}

			// 只取最后一个标签
			Object o = list.get(list.size() - 1);
			if (o == null){
				return Collections.emptyMap();
			}

			if (o instanceof Map){
				return (Map<String, Object>) headObj;
			}
		}

		return Collections.emptyMap();
	}


	/**
	 * 方法描述: 从转换的XML的Map中获取某个Map类型的Key
	 * @param map xmlMap
	 * @author hedonglin
	 * @date 2019/9/2 16:54
	 * @return {@link Map} Map
	 */
	public static String getValueByMap(Map<String, Object> map, String key){
		String result = "";
		Object headObj = map.get(key);

		if (headObj == null) {
			return result;
		}

		// 本身是String类型
		result = getStringByObject(headObj);
		if (StringHelper.isNotBlank(result)){
			return result;
		}

		// 传入放入标签有多个，取最后一个
		if (headObj instanceof List){
			List list = (List)headObj;
			if (list.size() == 0){
				return result;
			}

			// 只取最后一个标签
			Object o = list.get(list.size() - 1);
			return getStringByObject(o);
		}
		return result;
	}

	private static String getStringByObject(Object o){
		if (o instanceof String){
			return (String) o;
		}

		if (o instanceof Double){
			return String.valueOf(o);
		}

		if (o instanceof Integer){
			return String.valueOf(o);
		}

		if (o instanceof Long){
			return String.valueOf(o);
		}

		if (o instanceof Float){
			return String.valueOf(o);
		}

		return "";
	}


	/**
	 * 方法描述: 获取多级key的值，如：PayerAccount.No
	 *
	 * @param key      多级key,通过dian号分隔
	 * @param reqParam 请求参数
	 * @return {@link String} 对应的值
	 * @author hedonglin
	 * @date 2019/8/30 16:26
	 */
	public static String getMultiLevelReqParam(String key,
											   Map<String, Object> reqParam) {
		if (StringHelper.isBlank(key)) {
			return null;
		}

		String[] split = key.split("\\.");

		return split.length < 2 ? StringHelper.getValueByMap(reqParam, key) :
				layerByLayerInvo(split, null, reqParam);
	}


	/**
	 * 递归获取多级参Map参数值
	 *
	 * @param strs     多级key的数组(必须有序)
	 * @param child    从哪一个节点开始查(默认空值)
	 * @param reqParam 请求参数
	 * @return String
	 */
	@SuppressWarnings("unchecked")
	private static String layerByLayerInvo(String[] strs, String child,
										   Map<String, Object> reqParam) {
		for (int i = 0; i < strs.length; i++) {
			if (StringHelper.isNotBlank(child) && !StringHelper.equals(child, strs[i])) {
				continue;
			}
			Object object = reqParam.get(strs[i]);
			if (object instanceof Map) {
				child = strs[i + 1];
				return layerByLayerInvo(strs, child, (Map<String, Object>) object);
			}

			return (String) object;
		}
		return child;
	}

}
