package com.manage.managecomponent.components;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rules.RulesResources;
import com.util.Context;
import com.util.IContext;
import com.util.InetLogger;

/**
 * Compatibility override for the copy in manage-8.0.0.2.jar.
 * The library casts every reflected failure to Exception, which masks Errors
 * such as IllegalAccessError with a ClassCastException.
 */
public class BoImpl extends Bo {
	private static final InetLogger logger = InetLogger.getInetLogger(BoImpl.class);
	private Method method;
	private Object businessObj;

	public void postParseVisit() {
		super.postParseVisit();
		try {
			Class cls = Class.forName(getObj());
			method = cls.getMethod(getMethod(), getArgsTypes());
			businessObj = cls.newInstance();
		} catch (Exception e) {
			logger.error("Unable to initialize business object", e);
		}
	}

	public Object execute(IContext data) throws Exception {
		if (method == null)
			return null;
		try {
			return method.invoke(businessObj, getArguments(data));
		} catch (InvocationTargetException e) {
			Throwable cause = e.getCause();
			if (cause instanceof Exception)
				throw (Exception) cause;
			if (cause instanceof Error)
				throw new Exception("Business object invocation failed", cause);
			throw new RuntimeException(cause);
		}
	}

	public boolean evaluate(IContext data) throws Exception {
		if (getEval() == null || "".equals(getEval()))
			return true;
		try {
			RuleImpl ruleImpl = RulesResources.getInstance(data).findRule(getEval());
			return ruleImpl.evaluate((Context) data, null);
		} catch (Exception e) {
			logger.error("Error in loading resource for eval " + getEval(), e);
			throw e;
		}
	}

	private Class[] getArgsTypes() throws Exception {
		List argsList = getArgumentoperationList();
		if (argsList == null || argsList.isEmpty())
			return new Class[] { IContext.class };

		Class[] args = new Class[argsList.size()];
		for (int i = 0; i < argsList.size(); i++) {
			ArgumentoperationImpl argument = (ArgumentoperationImpl) argsList.get(i);
			args[i] = Class.forName(argument.getArgtype());
		}
		return args;
	}

	private Object[] getArguments(IContext data) {
		List argsList = getArgumentoperationList();
		if (argsList == null || argsList.isEmpty())
			return new Object[] { data };

		Object[] args = new Object[argsList.size()];
		for (int i = 0; i < argsList.size(); i++) {
			ArgumentoperationImpl argument = (ArgumentoperationImpl) argsList.get(i);
			args[i] = data.get(argument.getArgname());
		}
		return args;
	}
}
