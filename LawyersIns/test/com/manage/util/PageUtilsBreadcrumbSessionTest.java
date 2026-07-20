package com.manage.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.util.Constants;
import com.util.Context;

public class PageUtilsBreadcrumbSessionTest {
	public static void main(String[] args) throws Exception {
		Context original = new Context();
		original.put("policyKey", "123");
		original.put(Constants.HTTPREQUEST, new Object());
		original.put(PageUtils.PAGE_LIST_STACK, new ArrayList());

		Context snapshot = PageUtils.createBreadCrumbSnapshot(original);
		if(snapshot.containsKey(Constants.HTTPREQUEST) || snapshot.containsKey(PageUtils.PAGE_LIST_STACK))
			throw new AssertionError("Breadcrumb snapshot retained request or page stack");
		if(!"123".equals(snapshot.get("policyKey")))
			throw new AssertionError("Breadcrumb snapshot lost application data");

		new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(snapshot);
	}
}
