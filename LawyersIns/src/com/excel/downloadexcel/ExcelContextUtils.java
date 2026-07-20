package com.excel.downloadexcel;

import java.util.Collections;
import java.util.Map;

import com.util.Context;

final class ExcelContextUtils {
	private ExcelContextUtils() {
	}

	static Map getMapIfPresent(Context ctx, String key) {
		Object value = ctx.get(key);
		return value instanceof Map ? (Map) value : Collections.EMPTY_MAP;
	}
}
