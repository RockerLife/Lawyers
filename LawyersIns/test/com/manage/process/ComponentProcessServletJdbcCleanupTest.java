package com.manage.process;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class ComponentProcessServletJdbcCleanupTest {
	public static void main(String[] args) throws Exception {
		Driver driver = new TestDriver();
		DriverManager.registerDriver(driver);
		int count = ComponentProcessServlet.deregisterJdbcDrivers(driver.getClass().getClassLoader());
		if(count < 1)
			throw new AssertionError("Application JDBC driver was not deregistered");
	}

	private static class TestDriver implements Driver {
		public Connection connect(String url, Properties info) throws SQLException { return null; }
		public boolean acceptsURL(String url) throws SQLException { return false; }
		public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException { return new DriverPropertyInfo[0]; }
		public int getMajorVersion() { return 1; }
		public int getMinorVersion() { return 0; }
		public boolean jdbcCompliant() { return false; }
		public Logger getParentLogger() { return Logger.getGlobal(); }
	}
}
