package com.userbo;

import com.util.InetLogger;

import org.apache.axis.AxisFault;

import com.osi.ws.client.objects.SecureOneWSSoap11BindingStub;

class TestStub {
	private static final InetLogger logger = InetLogger.getInetLogger(TestStub.class);
	public static void main (String njk[]) throws AxisFault{
		SecureOneWSSoap11BindingStub bindingStub = new SecureOneWSSoap11BindingStub();
		logger.debug("");
	} 
}
