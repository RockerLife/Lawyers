package com.manage.process;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ComponentProcessServletErrorResponseTest {
	public static void main(String[] args) throws Exception {
		final List<String> calls = new ArrayList<String>();
		RequestDispatcher dispatcher = proxy(RequestDispatcher.class, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) {
				if("forward".equals(method.getName()))
					calls.add("forward");
				return defaultValue(method.getReturnType());
			}
		});
		HttpServletRequest request = request(dispatcher, calls);
		HttpServletResponse response = response(false, calls);

		if(!ComponentProcessServlet.forwardErrorPage(request, response))
			throw new AssertionError("Uncommitted response was not forwarded");
		if(!calls.equals(Arrays.asList("reset", "status:500", "dispatcher:error.jsp", "forward")))
			throw new AssertionError("Unexpected error response sequence: " + calls);

		calls.clear();
		if(ComponentProcessServlet.forwardErrorPage(request, response(true, calls)))
			throw new AssertionError("Committed response was forwarded");
		if(!calls.isEmpty())
			throw new AssertionError("Committed response was modified: " + calls);
	}

	private static HttpServletRequest request(final RequestDispatcher dispatcher, final List<String> calls) {
		return proxy(HttpServletRequest.class, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) {
				if("getRequestDispatcher".equals(method.getName())) {
					calls.add("dispatcher:" + args[0]);
					return dispatcher;
				}
				return defaultValue(method.getReturnType());
			}
		});
	}

	private static HttpServletResponse response(final boolean committed, final List<String> calls) {
		return proxy(HttpServletResponse.class, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) {
				if("isCommitted".equals(method.getName()))
					return committed;
				if("reset".equals(method.getName()))
					calls.add("reset");
				if("setStatus".equals(method.getName()))
					calls.add("status:" + args[0]);
				return defaultValue(method.getReturnType());
			}
		});
	}

	@SuppressWarnings("unchecked")
	private static <T> T proxy(Class<T> type, InvocationHandler handler) {
		return (T)Proxy.newProxyInstance(type.getClassLoader(), new Class[] { type }, handler);
	}

	private static Object defaultValue(Class<?> type) {
		if(type == Boolean.TYPE)
			return false;
		if(type == Integer.TYPE)
			return 0;
		if(type == Long.TYPE)
			return 0L;
		return null;
	}
}
