package com.manage.process;

public class ZohoTimerLifecycleTest {
	public static void main(String[] args) {
		ZohoTimer timer = new ZohoTimer();
		timer.createZohoTimer(60000L);
		if(!timer.isRunning())
			throw new AssertionError("Zoho refresh timer did not start");
		timer.cancel();
		if(timer.isRunning())
			throw new AssertionError("Zoho refresh timer did not stop");

		try {
			timer.createZohoTimer(0);
			throw new AssertionError("Invalid refresh interval was accepted");
		} catch (IllegalArgumentException expected) {
			// expected
		}
	}
}
