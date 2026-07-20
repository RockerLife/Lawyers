package com.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Context extends HashMap implements IContext, Serializable {
	private static InetLogger logger = InetLogger.getInetLogger(Context.class);
	private static final String[] SYSTEM_NAMESPACE_KEYS = new String[]{"inet_session_namespace"};
	private HashMap nameSpaceHash = new HashMap();
	private String[] SYSTEM_KEYS = new String[]{"previous_page", "next_page"};

	public Object getclone() {
		try {
			return this.clone();
		} catch (Exception arg1) {
			throw new InternalError(arg1.toString());
		}
	}

	public String getProject() {
		return (String) this.get("inet_project_id");
	}

	public void setProject(String project_id) {
		this.put("inet_project_id", project_id);
	}

	public List getMultipleValuesStartsWith(String key) {
		Iterator iterator = this.keySet().iterator();
		ArrayList values = new ArrayList();

		while (iterator.hasNext()) {
			String str = (String) iterator.next();
			if (str.startsWith(key)) {
				values.add(str);
			}
		}

		return values;
	}

	private boolean isSystemNamespace(String key) {
		for (int i = 0; i < SYSTEM_NAMESPACE_KEYS.length; ++i) {
			if (SYSTEM_NAMESPACE_KEYS[i].equals(key)) {
				return true;
			}
		}

		return false;
	}

	public Object put(Object key, Object value) {
		if ("Role_CDE".equals(key)) {
			logger.debug("here");
		}

		if (key == null) {
			return null;
		} else if (!(key instanceof String)) {
			return super.put(key, value);
		} else {
			String strKey = (String) key;
			int index = strKey.indexOf(".");
			if (index == -1) {
				return super.put(key, value);
			} else {
				String blockId = strKey.substring(0, index);
				strKey = strKey.substring(index + 1);
				return this.put(blockId, strKey, value);
			}
		}
	}

	public void putAllWithoutOverwrite(Map data) {
		if (data != null) {
			HashMap newData = new HashMap();
			Iterator keyIt = data.keySet().iterator();

			while (keyIt.hasNext()) {
				Object key = keyIt.next();
				if (!this.isSystemKey(key.toString()) && this.get(key) == null) {
					newData.put(key, data.get(key));
				}
			}

			this.putAll(newData);
		}
	}

	private boolean isSystemKey(String key) {
		for (int i = 0; i < this.SYSTEM_KEYS.length; ++i) {
			if (this.SYSTEM_KEYS[i].equals(key)) {
				return true;
			}
		}

		return false;
	}

	public Object get(Object key) {
		if (key == null) {
			return null;
		} else if (!(key instanceof String)) {
			return super.get(key);
		} else {
			String strKey = (String) key;
			int index = strKey.indexOf(".");
			if (index == -1) {
				return super.get(key);
			} else {
				String blockId = strKey.substring(0, index);
				strKey = strKey.substring(index + 1);
				return this.get(blockId, strKey);
			}
		}
	}

	public Object put(String namespaceKey, String key, Object value) {
		return this.getNameSpaceContext(namespaceKey).put(key, value);
	}

	public Object get(String namespaceKey, String key) {
		return this.getNameSpaceContext(namespaceKey).get(key);
	}

	public void remove(String namespaceKey, String key) {
		this.getNameSpaceContext(namespaceKey).remove(key);
	}

	public IContext subset(String namespaceKey) {
		return (IContext) this.nameSpaceHash.get(namespaceKey);
	}

	private Context getNameSpaceContext(String namespaceKey) {
		Context ctx = (Context) this.nameSpaceHash.get(namespaceKey);
		if (ctx == null) {
			ctx = new Context();
			this.nameSpaceHash.put(namespaceKey, ctx);
		}

		return ctx;
	}

	private Iterator namespaceKeys() {
		return this.nameSpaceHash.keySet().iterator();
	}
}