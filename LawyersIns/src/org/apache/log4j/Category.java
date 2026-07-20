package org.apache.log4j;

import java.text.MessageFormat;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.log4j.helpers.AppenderAttachableImpl;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggingEvent;

import com.util.Context;
import com.util.HtmlConstants;

public class Category
  implements AppenderAttachable
{
  protected String name;
  protected volatile Level level;
  protected volatile Category parent;
  private static final String FQCN = Category.class.getName();
  protected ResourceBundle resourceBundle;
  protected LoggerRepository repository;
  AppenderAttachableImpl aai;
  protected boolean additive = true;
  
  protected Category(String name)
  {
    this.name = name;
  }
  
  public synchronized void addAppender(Appender newAppender)
  {
    if (this.aai == null) {
      this.aai = new AppenderAttachableImpl();
    }
    this.aai.addAppender(newAppender);
    this.repository.fireAddAppenderEvent(this, newAppender);
  }
  
  public void assertLog(boolean assertion, String msg)
  {
    if (!assertion) {
      error(msg);
    }
  }
  
  public void callAppenders(LoggingEvent event)
  {
    int writes = 0;
    for (Category c = this; c != null; c = c.parent) {
      synchronized (c)
      {
        if (c.aai != null) {
          writes += c.aai.appendLoopOnAppenders(event);
        }
        if (!c.additive) {
          break;
        }
      }
    }
    if (writes == 0) {
      this.repository.emitNoAppenderWarning(this);
    }
  }
  
  synchronized void closeNestedAppenders()
  {
   /* Enumeration<E> enum = getAllAppenders();
    if (enum != null) {
      while (enum.hasMoreElements())
      {
        Appender a = (Appender)enum.nextElement();
        if ((a instanceof AppenderAttachable)) {
          a.close();
        }
      }
    }*/
  }
  
  public void debug(Object message)
  {
    if (this.repository.isDisabled(10000)) {
      return;
    }
    if (Level.DEBUG.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.DEBUG, message, null);
    }
  }
  
  public void debug(Object message, Throwable t)
  {
    if (this.repository.isDisabled(10000)) {
      return;
    }
    if (Level.DEBUG.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.DEBUG, message, t);
    }
  }
  
  //Method created to put logs per user session 
  public void debug(Context ctx, Object message){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.DEBUG.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, message, new LoggingEvent(FQCN, this, Level.DEBUG, message, new Throwable()));
		 
		  forcedLog(FQCN, Level.DEBUG, message, null);
	  }
  }
  
  //Method created to put logs per user session 
  public void debug(Context ctx, String message){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.DEBUG.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, message, new LoggingEvent(FQCN, this, Level.DEBUG, message, new Throwable()));
		 
		  forcedLog(FQCN, Level.DEBUG, message, null);
	  }
  }
  
  public void error(Object message)
  {
    if (this.repository.isDisabled(40000)) {
      return;
    }
    if (Level.ERROR.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.ERROR, message, null);
    }
  }
  
  public void error(Object message, Throwable t)
  {
    if (this.repository.isDisabled(40000)) {
      return;
    }
    if (Level.ERROR.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.ERROR, message, t);
    }
  }
  
  //Method created to put logs per user session 
  public void error(Context ctx, Object message){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.ERROR.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, message, new LoggingEvent(FQCN, this, Level.ERROR, message, new Throwable()));
		 
		  forcedLog(FQCN, Level.ERROR, message, null);
	  }
  }
  
  //Method created to put logs per user session 
  public void error(Context ctx, Object message, Throwable t){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.ERROR.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, message, new LoggingEvent(FQCN, this, Level.ERROR, message, t));
		 
		  forcedLog(FQCN, Level.ERROR, message, null);
	  }
  }
  
  //Method created to put logs per user session 
  public void error(Context ctx, Throwable t){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.ERROR.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, t.getMessage(), new LoggingEvent(FQCN, this, Level.ERROR, t.getMessage(), t));
		 
		  forcedLog(FQCN, Level.ERROR, t.getMessage(), null);
	  }
  }
  
  /**
   * @deprecated
   */
  public static Logger exists(String name)
  {
    return LogManager.exists(name);
  }
  
  public void fatal(Object message)
  {
    if (this.repository.isDisabled(50000)) {
      return;
    }
    if (Level.FATAL.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.FATAL, message, null);
    }
  }
  
  public void fatal(Object message, Throwable t)
  {
    if (this.repository.isDisabled(50000)) {
      return;
    }
    if (Level.FATAL.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.FATAL, message, t);
    }
  }
  
  //Method created to put logs per user session 
  public void fatal(Context ctx, Object message){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.INFO.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, message, new LoggingEvent(FQCN, this, Level.FATAL, message, new Throwable()));
		 
		  forcedLog(FQCN, Level.FATAL, message, null);
	  }
  }
  
  protected void forcedLog(String fqcn, Priority level, Object message, Throwable t)
  {
	  /*try{
		if(CacheManager.get("jsessionid") != null){
			String logs = CacheManager.get("jsessionid").toString();
			logs = logs + new PatternLayout("%5p - %m%n").format(new LoggingEvent(fqcn, this, level, message, t));
			CacheManager.put("jsessionid", logs);
		}
	}catch(Exception e){
		
	}*/
	  
    callAppenders(new LoggingEvent(fqcn, this, level, message, t));
  }
  
  public boolean getAdditivity()
  {
    return this.additive;
  }
  
  public synchronized Enumeration getAllAppenders()
  {
    if (this.aai == null) {
      return NullEnumeration.getInstance();
    }
    return this.aai.getAllAppenders();
  }
  
  public synchronized Appender getAppender(String name)
  {
    if ((this.aai == null) || (name == null)) {
      return null;
    }
    return this.aai.getAppender(name);
  }
  
  public Level getEffectiveLevel()
  {
    for (Category c = this; c != null; c = c.parent) {
      if (c.level != null) {
        return c.level;
      }
    }
    return null;
  }
  
  /**
   * @deprecated
   */
  public Priority getChainedPriority()
  {
    for (Category c = this; c != null; c = c.parent) {
      if (c.level != null) {
        return c.level;
      }
    }
    return null;
  }
  
  /**
   * @deprecated
   */
  public static Enumeration getCurrentCategories()
  {
    return LogManager.getCurrentLoggers();
  }
  
  /**
   * @deprecated
   */
  public static LoggerRepository getDefaultHierarchy()
  {
    return LogManager.getLoggerRepository();
  }
  
  /**
   * @deprecated
   */
  public LoggerRepository getHierarchy()
  {
    return this.repository;
  }
  
  public LoggerRepository getLoggerRepository()
  {
    return this.repository;
  }
  
  public static Category getInstance(String name)
  {
    return LogManager.getLogger(name);
  }
  
  public static Category getInstance(Class clazz)
  {
    return LogManager.getLogger(clazz);
  }
  
  public final String getName()
  {
    return this.name;
  }
  
  public final Category getParent()
  {
    return this.parent;
  }
  
  public final Level getLevel()
  {
    return this.level;
  }
  
  /**
   * @deprecated
   */
  public final Level getPriority()
  {
    return this.level;
  }
  
  public static final Category getRoot()
  {
    return LogManager.getRootLogger();
  }
  
  public ResourceBundle getResourceBundle()
  {
    for (Category c = this; c != null; c = c.parent) {
      if (c.resourceBundle != null) {
        return c.resourceBundle;
      }
    }
    return null;
  }
  
  protected String getResourceBundleString(String key)
  {
    ResourceBundle rb = getResourceBundle();
    if (rb == null) {
      return null;
    }
    try
    {
      return rb.getString(key);
    }
    catch (MissingResourceException mre)
    {
      error("No resource is associated with key \"" + key + "\".");
    }
    return null;
  }
  
  public void info(Object message)
  {
    if (this.repository.isDisabled(20000)) {
      return;
    }
    if (Level.INFO.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.INFO, message, null);
    }
  }
  
  public void info(Object message, Throwable t)
  {
    if (this.repository.isDisabled(20000)) {
      return;
    }
    if (Level.INFO.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.INFO, message, t);
    }
  }
  
  //Method created to put logs per user session 
  public void info(Context ctx, Object message){
	  if(this.repository.isDisabled(10000)) {
		  return;
	  }
	  
	  if(Level.INFO.isGreaterOrEqual(getEffectiveLevel())){
		  insertUserLog(ctx, message, new LoggingEvent(FQCN, this, Level.INFO, message, new Throwable()));
		 
		  forcedLog(FQCN, Level.INFO, message, null);
	  }
  }
  
  public boolean isAttached(Appender appender)
  {
    if ((appender == null) || (this.aai == null)) {
      return false;
    }
    return this.aai.isAttached(appender);
  }
  
  public boolean isDebugEnabled()
  {
    if (this.repository.isDisabled(10000)) {
      return false;
    }
    return Level.DEBUG.isGreaterOrEqual(getEffectiveLevel());
  }
  
  public boolean isEnabledFor(Priority level)
  {
    if (this.repository.isDisabled(level.level)) {
      return false;
    }
    return level.isGreaterOrEqual(getEffectiveLevel());
  }
  
  public boolean isInfoEnabled()
  {
    if (this.repository.isDisabled(20000)) {
      return false;
    }
    return Level.INFO.isGreaterOrEqual(getEffectiveLevel());
  }
  
  public void l7dlog(Priority priority, String key, Throwable t)
  {
    if (this.repository.isDisabled(priority.level)) {
      return;
    }
    if (priority.isGreaterOrEqual(getEffectiveLevel()))
    {
      String msg = getResourceBundleString(key);
      if (msg == null) {
        msg = key;
      }
      forcedLog(FQCN, priority, msg, t);
    }
  }
  
  public void l7dlog(Priority priority, String key, Object[] params, Throwable t)
  {
    if (this.repository.isDisabled(priority.level)) {
      return;
    }
    if (priority.isGreaterOrEqual(getEffectiveLevel()))
    {
      String pattern = getResourceBundleString(key);
      String msg;
      if (pattern == null) {
        msg = key;
      } else {
        msg = MessageFormat.format(pattern, params);
      }
      forcedLog(FQCN, priority, msg, t);
    }
  }
  
  public void log(Priority priority, Object message, Throwable t)
  {
    if (this.repository.isDisabled(priority.level)) {
      return;
    }
    if (priority.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, priority, message, t);
    }
  }
  
  public void log(Priority priority, Object message)
  {
    if (this.repository.isDisabled(priority.level)) {
      return;
    }
    if (priority.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, priority, message, null);
    }
  }
  
  public void log(String callerFQCN, Priority level, Object message, Throwable t)
  {
    if (this.repository.isDisabled(level.level)) {
      return;
    }
    if (level.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(callerFQCN, level, message, t);
    }
  }
  
  public synchronized void removeAllAppenders()
  {
    if (this.aai != null)
    {
      this.aai.removeAllAppenders();
      this.aai = null;
    }
  }
  
  public synchronized void removeAppender(Appender appender)
  {
    if ((appender == null) || (this.aai == null)) {
      return;
    }
    this.aai.removeAppender(appender);
  }
  
  public synchronized void removeAppender(String name)
  {
    if ((name == null) || (this.aai == null)) {
      return;
    }
    this.aai.removeAppender(name);
  }
  
  public void setAdditivity(boolean additive)
  {
    this.additive = additive;
  }
  
  final void setHierarchy(LoggerRepository repository)
  {
    this.repository = repository;
  }
  
  public void setLevel(Level level)
  {
    this.level = level;
  }
  
  /**
   * @deprecated
   */
  public void setPriority(Priority priority)
  {
    this.level = ((Level)priority);
  }
  
  public void setResourceBundle(ResourceBundle bundle)
  {
    this.resourceBundle = bundle;
  }
  
  /**
   * @deprecated
   */
  public static void shutdown() {}
  
  public void warn(Object message)
  {
    if (this.repository.isDisabled(30000)) {
      return;
    }
    if (Level.WARN.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.WARN, message, null);
    }
  }
  
  public void warn(Object message, Throwable t)
  {
    if (this.repository.isDisabled(30000)) {
      return;
    }
    if (Level.WARN.isGreaterOrEqual(getEffectiveLevel())) {
      forcedLog(FQCN, Level.WARN, message, t);
    }
  }
  
  //Method created to insert user logs to database
  private void insertUserLog(Context ctx, Object message, LoggingEvent event){
	  try{
    	if(ctx.get(HtmlConstants.INET_METHOD) != null && (ctx.get(HtmlConstants.INET_METHOD).toString().equals("reloadStartDebugXML") ||
    			ctx.get(HtmlConstants.INET_METHOD).toString().equals("reloadStopDebugXML")))
    		return;
    	
		  if(ctx.get("user_id") != null && ctx.get(ctx.get("user_id")+"_isShowStartDebug") != null
				  && ctx.get(ctx.get("user_id")+"_isShowStartDebug").equals("Y")){
			  
			  /*if(ComponentProcessServlet.DEBUGMSG == null)
				  ComponentProcessServlet.DEBUGMSG = new StringBuffer(ComponentProcessServlet.layout.format(event));
			  else{
				  ComponentProcessServlet.DEBUGMSG = ComponentProcessServlet.DEBUGMSG.append(new StringBuffer(ComponentProcessServlet.layout.format(event)));
			  }*/
			  
			  /*if(ctx.get("DEBUGMSG") == null)
				  ctx.put("DEBUGMSG", new PatternLayout("%5p - %m%n").format(event));
			  else
				  ctx.put("DEBUGMSG", ctx.get("DEBUGMSG").toString() + new PatternLayout("%5p - %m%n").format(event));*/
		  }
	  }catch(Exception e){
		  LogLog.error("Unable to capture the request debug message", e);
	  }
  }
}
