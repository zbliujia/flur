package com.flur.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FaceUtil {
	List<String> list = new ArrayList<String>();
	List<String> list_Index = new ArrayList<String>();
	Map<String, String> mp_index = new HashMap<String, String>();

	public Map<String, String> getMp_index() {
		return mp_index;
	}

	public void setMp_index(Map<String, String> mp_index) {
		this.mp_index = mp_index;
	}

	Map<String, String> mp = new HashMap<String, String>();

	public FaceUtil() {

		// 英文
		list_Index.add("[酷]");
		list_Index.add("[怒]");
		list_Index.add("[吃]");
		list_Index.add("[死]");
		list_Index.add("[呆]");
		list_Index.add("[吐]");
		list_Index.add("[晕]");
		list_Index.add("[衰]");
		list_Index.add("[困]");
		list_Index.add("[汗]");
		list_Index.add("[色]");
		list_Index.add("[哭]");
		list_Index.add("[疑问]");
		list_Index.add("[奋斗]");
		list_Index.add("[抓狂]");
		list_Index.add("[微笑]");
		list_Index.add("[尴尬]");
		list_Index.add("[大笑]");
		list_Index.add("[闭嘴]");
		list_Index.add("[愤怒]");
		list_Index.add("[生病]");
		list_Index.add("[调皮]");
		list_Index.add("[飞吻]");
		list_Index.add("[流泪]");
		list_Index.add("[睡觉]");
		list_Index.add("[亲亲]");
		list_Index.add("[害怕]");
		list_Index.add("[再见]");
		list_Index.add("[听音乐]");
		list_Index.add("[糗大了]");

		// 英文
		mp_index.put("[酷]", "emoji_01.png");
		mp_index.put("[怒]", "emoji_04.png");
		mp_index.put("[吃]", "emoji_08.png");
		mp_index.put("[死]", "emoji_13.png");
		mp_index.put("[呆]", "emoji_22.png");
		mp_index.put("[吐]", "emoji_24.png");
		mp_index.put("[晕]", "emoji_25.png");
		mp_index.put("[衰]", "emoji_26.png");
		mp_index.put("[困]", "emoji_27.png");
		mp_index.put("[汗]", "emoji_29.png");
		mp_index.put("[色]", "emoji_30.png");
		mp_index.put("[哭]", "emoji_17.png");
		mp_index.put("[疑问]", "emoji_02.png");
		mp_index.put("[奋斗]", "emoji_03.png");
		mp_index.put("[抓狂]", "emoji_05.png");
		mp_index.put("[微笑]", "emoji_06.png");
		mp_index.put("[尴尬]", "emoji_07.png");
		mp_index.put("[大笑]", "emoji_09.png");
		mp_index.put("[闭嘴]", "emoji_10.png");
		mp_index.put("[愤怒]", "emoji_12.png");
		mp_index.put("[生病]", "emoji_14.png");
		mp_index.put("[调皮]", "emoji_15.png");
		mp_index.put("[飞吻]", "emoji_16.png");
		mp_index.put("[流泪]", "emoji_18.png");
		mp_index.put("[睡觉]", "emoji_19.png");
		mp_index.put("[亲亲]", "emoji_20.png");
		mp_index.put("[害怕]", "emoji_23.png");
		mp_index.put("[再见]", "emoji_28.png");
		mp_index.put("[听音乐]", "emoji_11.png");
		mp_index.put("[糗大了]", "emoji_21.png");

		// 中文
		list.add("【酷】");
		list.add("【怒】");
		list.add("【吃】");
		list.add("【死】");
		list.add("【呆】");
		list.add("【吐】");
		list.add("【晕】");
		list.add("【衰】");
		list.add("【困】");
		list.add("【汗】");
		list.add("【色】");
		list.add("【哭】");
		list.add("【疑问】");
		list.add("【奋斗】");
		list.add("【抓狂】");
		list.add("【微笑】");
		list.add("【尴尬】");
		list.add("【大笑】");
		list.add("【闭嘴】");
		list.add("【愤怒】");
		list.add("【生病】");
		list.add("【调皮】");
		list.add("【飞吻】");
		list.add("【流泪】");
		list.add("【睡觉】");
		list.add("【亲亲】");
		list.add("【害怕】");
		list.add("【再见】");
		list.add("【听音乐】");
		list.add("【糗大了】");

		// 英文
		list.add("[酷]");
		list.add("[怒]");
		list.add("[吃]");
		list.add("[死]");
		list.add("[呆]");
		list.add("[吐]");
		list.add("[晕]");
		list.add("[衰]");
		list.add("[困]");
		list.add("[汗]");
		list.add("[色]");
		list.add("[哭]");
		list.add("[疑问]");
		list.add("[奋斗]");
		list.add("[抓狂]");
		list.add("[微笑]");
		list.add("[尴尬]");
		list.add("[大笑]");
		list.add("[闭嘴]");
		list.add("[愤怒]");
		list.add("[生病]");
		list.add("[调皮]");
		list.add("[飞吻]");
		list.add("[流泪]");
		list.add("[睡觉]");
		list.add("[亲亲]");
		list.add("[害怕]");
		list.add("[再见]");
		list.add("[听音乐]");
		list.add("[糗大了]");

		// 中文
		mp.put("【酷】", "emoji_01.png");
		mp.put("【怒】", "emoji_04.png");
		mp.put("【吃】", "emoji_08.png");
		mp.put("【死】", "emoji_13.png");
		mp.put("【呆】", "emoji_22.png");
		mp.put("【吐】", "emoji_24.png");
		mp.put("【晕】", "emoji_25.png");
		mp.put("【衰】", "emoji_26.png");
		mp.put("【困】", "emoji_27.png");
		mp.put("【汗】", "emoji_29.png");
		mp.put("【色】", "emoji_30.png");
		mp.put("【哭】", "emoji_17.png");
		mp.put("【疑问】", "emoji_02.png");
		mp.put("【奋斗】", "emoji_03.png");
		mp.put("【抓狂】", "emoji_05.png");
		mp.put("【微笑】", "emoji_06.png");
		mp.put("【尴尬】", "emoji_07.png");
		mp.put("【大笑】", "emoji_09.png");
		mp.put("【闭嘴】", "emoji_10.png");
		mp.put("【愤怒】", "emoji_12.png");
		mp.put("【生病】", "emoji_14.png");
		mp.put("【调皮】", "emoji_15.png");
		mp.put("【飞吻】", "emoji_16.png");
		mp.put("【流泪】", "emoji_18.png");
		mp.put("【睡觉】", "emoji_19.png");
		mp.put("【亲亲】", "emoji_20.png");
		mp.put("【害怕】", "emoji_23.png");
		mp.put("【再见】", "emoji_28.png");
		mp.put("【听音乐】", "emoji_11.png");
		mp.put("【糗大了】", "emoji_21.png");

		// 英文
		mp.put("[酷]", "emoji_01.png");
		mp.put("[怒]", "emoji_04.png");
		mp.put("[吃]", "emoji_08.png");
		mp.put("[死]", "emoji_13.png");
		mp.put("[呆]", "emoji_22.png");
		mp.put("[吐]", "emoji_24.png");
		mp.put("[晕]", "emoji_25.png");
		mp.put("[衰]", "emoji_26.png");
		mp.put("[困]", "emoji_27.png");
		mp.put("[汗]", "emoji_29.png");
		mp.put("[色]", "emoji_30.png");
		mp.put("[哭]", "emoji_17.png");
		mp.put("[疑问]", "emoji_02.png");
		mp.put("[奋斗]", "emoji_03.png");
		mp.put("[抓狂]", "emoji_05.png");
		mp.put("[微笑]", "emoji_06.png");
		mp.put("[尴尬]", "emoji_07.png");
		mp.put("[大笑]", "emoji_09.png");
		mp.put("[闭嘴]", "emoji_10.png");
		mp.put("[愤怒]", "emoji_12.png");
		mp.put("[生病]", "emoji_14.png");
		mp.put("[调皮]", "emoji_15.png");
		mp.put("[飞吻]", "emoji_16.png");
		mp.put("[流泪]", "emoji_18.png");
		mp.put("[睡觉]", "emoji_19.png");
		mp.put("[亲亲]", "emoji_20.png");
		mp.put("[害怕]", "emoji_23.png");
		mp.put("[再见]", "emoji_28.png");
		mp.put("[听音乐]", "emoji_11.png");
		mp.put("[糗大了]", "emoji_21.png");
	}

	public List<String> getList_Index() {
		return list_Index;
	}

	public void setList_Index(List<String> list_Index) {
		this.list_Index = list_Index;
	}

	/**
	 * 识别表情
	 * 
	 * @param value
	 */
	public static String identify(String str) {
		FaceUtil face = new FaceUtil();
		for (int i = 0; i < face.list.size(); i++) {
			if (Validator.notEmpty(str) && str.indexOf(face.list.get(i).toString()) != -1)
				str = str.replace(face.list.get(i).toString(), "<img title='" + face.list.get(i).toString() + "' src='/static/images/face_icon/default/" + face.mp.get(face.list.get(i)).toString() + "' style='width:30px; height:30px;vertical-align: middle;' />");
		}
		return str;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Map<String, String> getMp() {
		return mp;
	}

	public void setMp(Map<String, String> mp) {
		this.mp = mp;
	}
}
