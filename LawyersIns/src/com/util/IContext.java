package com.util;

public interface IContext extends MultiMap, IResourceKeys, Cloneable {
	IContext subset(String arg0);

	Object getclone();
}