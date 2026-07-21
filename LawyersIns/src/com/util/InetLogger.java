package com.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggerFactory;

public class InetLogger extends Logger {
	public static LoggerFactory logFactory = new InetLoggerFactory();

	private static InetLogger logger1 = getInetLogger("log1");

	public InetLogger(String name) {
		super(name);
	}

	public static InetLogger getInetLogger(Class cls) {
		return getInetLogger(cls.getName());
	}

	public static InetLogger getInetLogger(String name) {
		return (InetLogger) Logger.getLogger(name, logFactory);
	}

	public static void logDebug(Object logObject) {
		Logger.getRootLogger().debug(logObject);
	}

	public void debug(long io_lValue) {
		super.debug(io_lValue);
	}

	public void debug(int io_iValue) {
		super.debug(io_iValue);
	}

	public void debug(boolean io_bValue) {
		if (io_bValue) {
			super.debug("true");
		} else {
			super.debug("false");
		}
	}

	public boolean isWarnEnabled() {
		return super.isEnabledFor(Level.WARN);
	}

	public boolean isErrorEnabled() {
		return super.isEnabledFor(Level.ERROR);
	}

	public boolean isFatalEnabled() {
		return super.isEnabledFor(Level.FATAL);
	}

	public void trace(Throwable e) {
		error(e == null ? "Unexpected error" : e.getMessage(), e);
	}

	public void writeToLog(String msg) {
		logger1.info(msg);
	}

	public void logMessage(String msg) {
		writeToLog(msg);
	}

	public void sendEmail(String msg, String subject, String env) {
		writeToLog(msg);
		try {
			if (!StringUtils.isBlank(env)) {

				String myEnv = null;
				try {
					myEnv = SystemProperties.getInstance().getString(
							"SEND_ERROR_EMAIL");
				} catch (Exception localException) {
				}
				if (!"true".equalsIgnoreCase(myEnv)) {
				}
			}
		} catch (Exception localException1) {
		}
	}

	public static void main(String[] args) throws Exception {
	}

	public int ineterror(Object obj, String stackTrace, String projectName,
			String session) {
		int id = 0;
		DataSourceAppender jdbc = null;

		try {
			jdbc = new DataSourceAppender(projectName);
			jdbc.setThreshold(Priority.ERROR);
			String sql = "%d{MM-dd-yyyy HH:mm:ss.sss}";

			ArrayList<String> queryParams = new ArrayList();
			String preparedStatement = "insert into LogTable(LogMessage,LogStack,ProjectName,Session,LogTime) values(?,?,?,?,?)";
			queryParams.add(obj == null ? null : obj.toString());
			queryParams.add(stackTrace);

			queryParams.add(projectName);
			queryParams.add(session);

			jdbc.setPreparedStatement(preparedStatement);
			jdbc.setQueryParams(queryParams);
			jdbc.setSql(sql);
			addAppender(jdbc);
			super.error(obj);

			id = getLatestId(projectName);
		} catch (Exception e) {
			super.error("Unable to write application log", e);
		} finally {
			if (jdbc != null) {
				removeAppender(jdbc);
				jdbc.close();
			}
		}
		return id;
	}

	public static int getLatestId(String projectName) {
		int id = 0;
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup(SystemProperties.getInstance().getProperty(
							"log4j.jdbc.datasource") != null ? SystemProperties
							.getInstance().getProperty("log4j.jdbc.datasource")
							.toString() : "");

			try (Connection cn = ds.getConnection();
					Statement st = cn.createStatement();
					ResultSet rs = st.executeQuery("select max(logid) from LogTable")) {
				while (rs.next()) {
					id = rs.getInt(1);
				}
			}
		} catch (Exception e) {
			logger1.error("Unable to read latest log id for project " + projectName, e);
		}
		return id;
	}
}
