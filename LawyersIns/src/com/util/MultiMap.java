package com.util;

import java.util.Map;

public interface MultiMap extends Map {
	Object put(String arg0, String arg1, Object arg2);

	Object get(String arg0, String arg1);

	void remove(String arg0, String arg1);
}	