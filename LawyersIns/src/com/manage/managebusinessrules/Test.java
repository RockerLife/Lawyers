package com.manage.managebusinessrules;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.jdom.input.SAXBuilder;

import com.manage.managebusinessrules.rules.RuleImpl;
import com.manage.managebusinessrules.rulesengine.RuleTraceUtils;
import com.manage.managemetadata.functions.Function;
import com.manage.managemetadata.functions.FunctionParser;
import com.manage.managemetadata.metadata.FieldImpl;
import com.manage.managemetadata.metadata.MetaDataResources;
import com.manage.managemetadata.metadata.MetaobjectImpl;
import com.manage.managemetadata.metadata.PropertyImpl;
import com.manage.managemetadata.metadata.PropertyversionImpl;
import com.ormapping.ibatis.SqlResources;
import com.util.Context;
import com.util.IContext;
import com.util.IResourceKeys;

public class Test {
  private static Logger log = Logger.getLogger(Test.class);
  
  protected static void loadResources(String projectName) throws Exception {
    log.debug("Loading resources....");
  }
  
  public static void main(String[] args) {
    try {
      SAXBuilder builder = new SAXBuilder();
      builder.build(new File("C:\\Users\\vikask\\Desktop\\labelconf.xml"));
      log.debug("loaded");
    } catch (Exception e) {
      log.error("Unexpected error", e);
    } 
  }
  
  public static List parseStepRules(IContext ctx) throws Exception {
    List finalargsList = new ArrayList();
    List argsList = null;
    try {
      Object obj = SqlResources.getSqlMapProcessor((IResourceKeys)ctx).select("SqlStmts.UserStatementviewgetExposures", (Map)ctx);
      if (obj != null && obj instanceof List) {
        List<Map> mapList = (List)obj;
        for (int i = 0; i < mapList.size(); i++) {
          Map row = mapList.get(i);
          ctx.put("PropertyID", row.get("PropertyID").toString());
          argsList = parseExposureRule(ctx);
          finalargsList.addAll(argsList);
        } 
      } 
      ctx.put("argsList", finalargsList);
    } catch (Exception e) {
      log.error("Unexpected error", e);
    } 
    return finalargsList;
  }
  
  public static List parseExposureRule(IContext ctx) throws Exception {
    List finalargsList = new ArrayList();
    List argsList = null;
    try {
      Object obj = SqlResources.getSqlMapProcessor((IResourceKeys)ctx).select("SqlStmts.UserStatementviewgettblFormulasByExposures", (Map)ctx);
      if (obj != null && obj instanceof List) {
        List<Map> mapList = (List)obj;
        for (int i = 0; i < mapList.size(); i++) {
          Map row = mapList.get(i);
          ctx.put("FormulaID", row.get("FormulaID").toString());
          argsList = parseRule(ctx);
          finalargsList.addAll(argsList);
        } 
      } 
      ctx.put("argsList", finalargsList);
    } catch (Exception e) {
      log.error("Unexpected error", e);
    } 
    return finalargsList;
  }
  
  public static List parseRule(IContext ctx) throws Exception {
    List argsList = null;
    try {
      argsList = parseRule((Context)ctx, new ArrayList(), false, null);
      ctx.put("argsList", argsList);
    } catch (Exception e) {
      log.error("Unexpected error", e);
    } 
    return argsList;
  }
  
  private static List parseRule(Context ctx, List argsList, boolean flag, Map<String, String> paramMap) throws Exception {
    String expr = "";
    String id = "";
    Map<String, String> map = new HashMap<String, String>();
    if (!flag) {
      map = getRule(ctx);
      if (map == null) {
        log.info("No Expression Found for ruleId ....." + ctx.get("FormulaID"));
        return null;
      } 
      log.info("Expression: " + (String)map.get("FormulaExpression"));
      id = String.valueOf(map.get("RuleSetName")) + "." + (String)map.get("FormulaName");
      expr = map.get("FormulaExpression");
    } else {
      map = paramMap;
    } 
    String rule = expr.substring(0, expr.indexOf("("));
    String ruleContent = expr.substring(expr.indexOf("(") + 1, expr.lastIndexOf(")"));
    if ("if".equalsIgnoreCase(rule)) {
      String condition = "";
      String then = "";
      String elsePart = "";
      Map<String, String> ifmap = populateFields(ruleContent);
      condition = ifmap.get("condition");
      then = ifmap.get("then");
      elsePart = ifmap.get("elsePart");
      parseEvaluateCondition(ctx, condition, id, argsList);
      if (then != null && !"".equals(then)) {
        RuleTraceUtils.populateLoggerBuffer(ctx).append("Start the execution of Then statement of " + id + ". \n");
        parseInnerStatement(then, ctx, argsList, id);
        RuleTraceUtils.populateLoggerBuffer(ctx).append("Complete the execution of Then statement of " + id + ". \n");
      } 
      if (elsePart != null && !"".equals(elsePart)) {
        RuleTraceUtils.populateLoggerBuffer(ctx).append("Start the execution of Else statement of " + id + ". \n");
        parseInnerStatement(elsePart, ctx, argsList, id);
        RuleTraceUtils.populateLoggerBuffer(ctx).append("Complete the execution of Else statement of " + id + ". \n");
      } 
    } else {
      parseInnerStatement(expr, ctx, argsList, id);
    } 
    return argsList;
  }
  
  private static Map<String, String> populateFields(String ruleContent) throws Exception {
    Map<String, String> map = new HashMap<String, String>();
    if (ruleContent.contains("=>")) {
      String condition = ruleContent.substring(0, ruleContent.indexOf("=>"));
      map.put("condition", condition);
      if (ruleContent.contains("->")) {
        String then = ruleContent.substring(ruleContent.indexOf("=>") + 2, ruleContent.indexOf("->"));
        String elsePart = ruleContent.substring(ruleContent.indexOf("->") + 2, ruleContent.length());
        map.put("then", then);
        map.put("elsePart", elsePart);
      } else {
        String then = ruleContent.substring(ruleContent.indexOf("=>") + 2, ruleContent.length());
        map.put("then", then);
      } 
    } else {
      String condition = ruleContent;
      map.put("condition", condition);
    } 
    return map;
  }
  
  private static List parseEvaluateCondition(Context ctx, String condition, String id, List argsList) throws Exception {
    RuleTraceUtils.populateLoggerBuffer(ctx).append("Start the execution of Condition statement of " + id + ". \n");
    Function f = (new FunctionParser(condition, (IResourceKeys)ctx)).parseFunction();
    RuleTraceUtils.populateLoggerBuffer(ctx).append("Finish the execution of Condition statement of " + id + ". \n");
    parseInnerFunction(f, argsList, ctx, id);
    return argsList;
  }
  
  private static void parseInnerFunction(Function f, List<Map<Object, Object>> argsList, Context ctx, String id) throws Exception {
    if (f.getArgsList() != null)
      for (int i = 0; i < f.getArgsList().size(); i++) {
        Object obj = f.getArgsList().get(i);
        if (obj instanceof com.manage.managemetadata.expressions.exprUtils.BindVar) {
          String field = obj.toString();
          if (field.contains(".")) {
            String metaobj = field.substring(0, field.indexOf("."));
            field = field.substring(field.indexOf(".") + 1);
          } 
          Map<Object, Object> map = new HashMap<Object, Object>();
          map.put("fieldName", field);
          try {
            FieldImpl fieldImpl = MetaDataResources.getInstance((IResourceKeys)ctx).getField(field);
            map.put("dataType", fieldImpl.getType());
          } catch (Exception e) {
            map.put("dataType", "V");
          } 
          argsList.add(map);
        } else if (obj instanceof com.manage.managemetadata.expressions.exprUtils.BindVarArray) {
          log.debug(obj.toString());
          if (obj.toString().contains(":")) {
            StringTokenizer strTokens = new StringTokenizer(obj.toString(), "|");
            while (strTokens.hasMoreTokens()) {
              String token = strTokens.nextToken();
              if (token.contains(":")) {
                token = token.substring(1);
                Map<Object, Object> map = new HashMap<Object, Object>();
                map.put("fieldName", token);
                try {
                  FieldImpl fieldImpl = MetaDataResources.getInstance((IResourceKeys)ctx).getField(token);
                  map.put("dataType", fieldImpl.getType());
                } catch (Exception e) {
                  map.put("dataType", "V");
                } 
                argsList.add(map);
              } 
            } 
          } 
        } else if (obj instanceof Function) {
          parseInnerFunction((Function)obj, argsList, ctx, id);
        } 
      }  
  }
  
  private static List parseInnerStatement(String innerRuleContent, Context ctx, List argsList, String id) throws Exception {
    innerRuleContent = innerRuleContent.trim();
    String[] statements = (String[])null;
    String statement = "";
    if (innerRuleContent.contains(";")) {
      statements = innerRuleContent.split(";");
      if (statements != null)
        for (int i = 0; i < statements.length; i++) {
          statement = statements[i];
          argsList = parseStatement(statement, ctx, argsList, id);
        }  
    } else {
      argsList = parseStatement(innerRuleContent, ctx, argsList, id);
    } 
    return argsList;
  }
  
  public static List parseStatement(String stmt, Context ctx, List<Map<Object, Object>> argsList, String id) throws Exception {
    Object result = null;
    try {
      log.debug("------------------------------------------");
      RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
      String rule = stmt.substring(0, stmt.indexOf("("));
      String ruleContent = stmt.substring(stmt.indexOf("(") + 1, stmt.lastIndexOf(")"));
      if (isExpression(rule, ctx)) {
        if (!isMainRule(ruleContent, ctx)) {
          int formulaid = getRuleByName(ruleContent);
          ctx.put("FormulaID", Integer.valueOf(formulaid));
          parseRule(ctx, argsList, false, null);
        } 
      } else if (isAssignment(rule, ctx)) {
        String strFunction = null;
        String assignField = stmt.substring(stmt.indexOf(":") + 1, stmt.indexOf(","));
        int index = assignField.indexOf(".");
        if (index != -1)
          assignField = assignField.substring(index + 1); 
        String field = assignField;
        if (index == -1 && 
          field.contains("."))
          field = field.substring(field.indexOf(".") + 1); 
        strFunction = stmt.substring(stmt.indexOf(",") + 1, stmt.lastIndexOf(")"));
        if (strFunction.contains("(")) {
          parseExecuteFunction(strFunction, ctx, argsList, id);
        } else if (strFunction.startsWith(":")) {
          String rightField = strFunction.substring(strFunction.indexOf(":") + 1);
          int rightindex = assignField.indexOf(".");
          if (rightindex != -1)
            rightField = rightField.substring(rightindex + 1); 
          String field1 = rightField;
          if (rightindex == -1 && 
            field1.contains(".")) {
            String metaobj = field.substring(0, field.indexOf("."));
            field = field.substring(field.indexOf(".") + 1);
          } 
          Map<Object, Object> map = new HashMap<Object, Object>();
          map.put("fieldName", field);
          try {
            FieldImpl fieldImpl = MetaDataResources.getInstance((IResourceKeys)ctx).getField(field1);
            map.put("dataType", fieldImpl.getType());
          } catch (Exception e) {
            map.put("dataType", "V");
          } 
          argsList.add(map);
          result = ctx.get(rightField);
        } else {
          result = strFunction;
        } 
        RuleTrace.getInstance().addRuleTrace(String.valueOf(assignField) + " = " + result + "\n");
        ctx.put(assignField, result);
        log.debug("Statement " + stmt + " has been executed");
        log.debug("------------------------------------------");
        RuleImpl.populateLoggerBuffer(ctx).append("Statement " + stmt + " has been executed \n");
        RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
      } else {
        parseExecuteFunction(stmt, ctx, argsList, id);
        log.debug("Statement " + stmt + " has been executed");
        log.debug("------------------------------------------");
        RuleImpl.populateLoggerBuffer(ctx).append("Statement " + stmt + " has been executed \n");
        RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
      } 
    } catch (Exception e) {
      log.debug(String.valueOf(stmt) + " has problem in execution :- " + e.getMessage());
      log.debug("------------------------------------------");
      RuleImpl.populateLoggerBuffer(ctx).append(String.valueOf(stmt) + " has problem in execution \n" + e.getMessage());
      RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n" + e.getMessage());
    } 
    return argsList;
  }
  
  private static String parseRuleForMetaObjectProperty(String stmt, Context ctx) {
    StringBuffer buffer = null;
    String assignField = null;
    stmt = stmt.trim();
    if (stmt == null)
      return null; 
    if (!stmt.contains("|") && stmt.contains(":") && stmt.contains(".") && !stmt.contains("#")) {
      if (stmt.contains(",")) {
        assignField = stmt.substring(stmt.indexOf(":") + 1, stmt.indexOf(","));
      } else {
        assignField = stmt.substring(stmt.indexOf(":") + 1, stmt.length() - 1);
      } 
      if (assignField.contains(".")) {
        buffer = new StringBuffer(stmt);
        String moName = assignField.substring(0, assignField.indexOf("."));
        String property = assignField.substring(assignField.indexOf(".") + 1, assignField.length());
        buffer.replace(stmt.indexOf(":") + 1, stmt.indexOf(","), property);
        parseRuleForMetaObjectProperty(moName, property, ctx);
        return buffer.toString();
      } 
    } 
    return null;
  }
  
  private static void parseRuleForMetaObjectProperty(String moName, String property, Context ctx) {
    MetaobjectImpl moImpl = null;
    try {
      moImpl = MetaDataResources.getInstance((IResourceKeys)ctx).getMetaobject(moName);
      if (moImpl == null)
        return; 
      List<PropertyversionImpl> propversionList = moImpl.getPropertyversionList();
      if (propversionList == null)
        return; 
      PropertyversionImpl propversion = propversionList.get(0);
      if (propversion == null)
        return; 
      PropertyImpl prop = propversion.getProperty(property);
      if (prop == null)
        return; 
      prop.evaluate(ctx, null);
    } catch (Exception e) {
      log.debug("Either " + moName + "or " + property + " does not exist");
      RuleImpl.populateLoggerBuffer(ctx).append("Either " + moName + "or " + property + " does not exist \n");
    } 
  }
  
  private static List parseExecuteFunction(String strFunction, Context ctx, List argsList, String id) throws Exception {
    try {
      Function f = (new FunctionParser(strFunction, (IResourceKeys)ctx)).parseFunction();
      parseInnerFunction(f, argsList, ctx, id);
      return argsList;
    } catch (Exception e) {
      log.debug(String.valueOf(strFunction) + " could not be executed");
      RuleImpl.populateLoggerBuffer(ctx).append(String.valueOf(strFunction) + " could not be executed \n");
      return argsList;
    } 
  }
  
  private static boolean isExpression(String innerRuleContent, Context ctx) throws Exception {
    innerRuleContent = innerRuleContent.trim();
    if ("call_rule".equals(innerRuleContent))
      return true; 
    return false;
  }
  
  private static boolean isMainRule(String ruleContent, Context ctx) throws Exception {
    ruleContent = ruleContent.trim();
    if (ruleContent.contains("."))
      return false; 
    return true;
  }
  
  private static boolean isAssignment(String innerRuleContent, Context ctx) throws Exception {
    innerRuleContent = innerRuleContent.trim();
    if ("assign".equals(innerRuleContent))
      return true; 
    return false;
  }
  
  private static void testRule(Context ctx) throws Exception {
    ctx.put("NumberOfBillabeHours", Integer.valueOf(1034));
    ctx.put("NumberOfPersonnel", Integer.valueOf(303));
    ctx.put("arg1", Integer.valueOf(1303));
    ctx.put("d", Integer.valueOf(343));
    ctx.put("e", Integer.valueOf(3));
    ctx.put("NumberOfAttorneys", Integer.valueOf(99));
    ctx.put("NumberOfOtherEmployees", Integer.valueOf(312));
    Object output = null;
    output = execute(ctx);
    ctx.put("ruleoutput", output);
    log.info("Result : " + output + "\n");
  }
  
  private static Object execute(Context ctx) throws Exception {
    Map<String, String> map = getRule(ctx);
    if (map == null) {
      log.info("No Expression Found for ruleId ....." + ctx.get("FormulaID"));
      return null;
    } 
    log.info("Expression: " + (String)map.get("FormulaExpression"));
    return executeRule(ctx, map);
  }
  
  private static Object executeRule(Context ctx, Map<String, String> map) throws Exception {
    Object result = null;
    String expr = map.get("FormulaExpression");
    String rule = expr.substring(0, expr.indexOf("("));
    String ruleContent = expr.substring(expr.indexOf("(") + 1, expr.lastIndexOf(")"));
    String id = "";
    id = String.valueOf(map.get("RuleSetName")) + "." + (String)map.get("FormulaName");
    log.debug("Expression " + expr + " is going to execute");
    if ("if".equalsIgnoreCase(rule)) {
      result = executeRule(ctx, ruleContent, id);
    } else {
      result = executeInnerStatement(expr, ctx);
    } 
    log.debug("Expression " + expr + " has been executed");
    return result;
  }
  
  private static Object executeRule(Context ctx, String ruleContent, String id) throws Exception {
    String condition = "";
    String then = "";
    String elsePart = "";
    Map<String, String> ifmap = populateFields(ruleContent);
    condition = ifmap.get("condition");
    then = ifmap.get("then");
    elsePart = ifmap.get("elsePart");
    if (evaluateCondition(ctx, condition, id)) {
      if (then != null && !"".equals(then)) {
        RuleTraceUtils.populateLoggerBuffer(ctx).append("Start the execution of Then statement of " + id + ". \n");
        Object retval = executeInnerStatement(then, ctx);
        RuleTraceUtils.populateLoggerBuffer(ctx).append("Complete the execution of Then statement of " + id + ". \n");
        return retval;
      } 
      return Boolean.valueOf(true);
    } 
    if (elsePart != null && !"".equals(elsePart)) {
      RuleTraceUtils.populateLoggerBuffer(ctx).append("Start the execution of Else statement of " + id + ". \n");
      Object retval = executeInnerStatement(elsePart, ctx);
      RuleTraceUtils.populateLoggerBuffer(ctx).append("Complete the execution of Else statement of " + id + ". \n");
      return retval;
    } 
    return Boolean.valueOf(false);
  }
  
  private static Object executeInnerStatement(String innerRuleContent, Context ctx) throws Exception {
    innerRuleContent = innerRuleContent.trim();
    String[] statements = (String[])null;
    String statement = "";
    Object result = null;
    if (innerRuleContent.contains(";")) {
      statements = innerRuleContent.split(";");
      if (statements != null)
        for (int i = 0; i < statements.length; i++) {
          statement = statements[i];
          result = executeStatement(statement, ctx);
        }  
    } else {
      result = executeStatement(innerRuleContent, ctx);
    } 
    return result;
  }
  
  private static Object executeStatement(String stmt, Context ctx) throws Exception {
    Object result = null;
    try {
      log.debug("------------------------------------------");
      RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
      String rule = stmt.substring(0, stmt.indexOf("("));
      String ruleContent = stmt.substring(stmt.indexOf("(") + 1, stmt.lastIndexOf(")"));
      if (isExpression(rule, ctx)) {
        if (!isMainRule(ruleContent, ctx)) {
          int id = getRuleByName(ruleContent);
          ctx.put("FormulaID", Integer.valueOf(id));
          return execute(ctx);
        } 
      } else {
        if (isAssignment(rule, ctx)) {
          String strFunction = null;
          String assignField = stmt.substring(stmt.indexOf(":") + 1, stmt.indexOf(","));
          int index = assignField.indexOf(".");
          if (index != -1)
            assignField = assignField.substring(index + 1); 
          strFunction = stmt.substring(stmt.indexOf(",") + 1, stmt.lastIndexOf(")"));
          if (strFunction.contains("(")) {
            result = executeFunction(strFunction, ctx);
          } else if (strFunction.startsWith(":")) {
            String rightField = strFunction.substring(strFunction.indexOf(":") + 1);
            int rightindex = assignField.indexOf(".");
            if (rightindex != -1)
              rightField = rightField.substring(rightindex + 1); 
            result = ctx.get(rightField);
          } else {
            result = strFunction;
          } 
          RuleTrace.getInstance().addRuleTrace(String.valueOf(assignField) + " = " + result + "\n");
          ctx.put(assignField, result);
          log.debug("Statement " + stmt + " has been executed");
          log.debug("------------------------------------------");
          RuleImpl.populateLoggerBuffer(ctx).append("Statement " + stmt + " has been executed \n");
          RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
          return result;
        } 
        result = executeFunction(stmt, ctx);
        log.debug("Statement " + stmt + " has been executed");
        log.debug("------------------------------------------");
        RuleImpl.populateLoggerBuffer(ctx).append("Statement " + stmt + " has been executed \n");
        RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
        return result;
      } 
    } catch (Exception e) {
      log.debug(String.valueOf(stmt) + " has problem in execution");
      log.debug("------------------------------------------");
      RuleImpl.populateLoggerBuffer(ctx).append(String.valueOf(stmt) + " has problem in execution \n");
      RuleImpl.populateLoggerBuffer(ctx).append("------------------------------------------ \n");
    } 
    return result;
  }
  
  private static Object executeFunction(String strFunction, Context ctx) throws Exception {
    try {
      Function f = (new FunctionParser(strFunction, (IResourceKeys)ctx)).parseFunction();
      return f.execute((Map)ctx);
    } catch (Exception e) {
      log.debug(String.valueOf(strFunction) + " could not be executed");
      RuleImpl.populateLoggerBuffer(ctx).append(String.valueOf(strFunction) + " could not be executed \n");
      return null;
    } 
  }
  
  private static boolean evaluateCondition(Context ctx, String condition, String id) throws Exception {
    RuleTraceUtils.populateLoggerBuffer(ctx).append("Start the execution of Condition statement of " + id + ". \n");
    Function f = (new FunctionParser(condition, (IResourceKeys)ctx)).parseFunction();
    boolean result = ((Boolean)f.execute((Map)ctx)).booleanValue();
    RuleTraceUtils.populateLoggerBuffer(ctx).append("Finish the execution of Condition statement of " + id + ". \n");
    return result;
  }
  
  public static StringBuffer populateLoggerBuffer(Context data) {
    Object obj = data.get("loggerbuffer");
    StringBuffer loggerBuffer = null;
    if (obj != null && obj instanceof String) {
      loggerBuffer = new StringBuffer(obj.toString());
    } else if (obj != null && obj instanceof StringBuffer) {
      loggerBuffer = (StringBuffer)data.get("loggerbuffer");
    } 
    if (loggerBuffer == null) {
      loggerBuffer = new StringBuffer();
      data.put("loggerbuffer", loggerBuffer);
    } 
    return loggerBuffer;
  }
  
  public static int calculateDiffInYears(Date date1, Date date2) {
    if (date1 == null || date2 == null)
      return 0; 
    long lDifferenceInMS = 0L;
    long lDays = 0L;
    lDifferenceInMS = date1.getTime() - date2.getTime();
    lDays = lDifferenceInMS / 86400000L;
    return Integer.parseInt(String.valueOf(Math.round((float)(lDays / 365L))));
  }
  
  public static String getInitcapString(String str) {
    if (str == null || str.equals(""))
      return null; 
    str = String.valueOf(str.substring(0, 1).toUpperCase()) + str.substring(1).toLowerCase();
    return str;
  }
  
  private static Map<String, String> getRule(Context ctx) throws Exception {
    Map<String, String> map = null;
    try {
      Connection cn = null;
      ResultSet rs = null;
      Statement st = null;
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      cn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1435/rateonedb", "sa", "outline");
      st = cn.createStatement();
      rs = st.executeQuery("select * from tblFormulas where FormulaId = " + ctx.get("FormulaID"));
      while (rs.next()) {
        map = new HashMap<String, String>();
        map.put("FormulaName", rs.getString(2));
        map.put("FormulaExpression", rs.getString(3));
        map.put("RuleSetName", rs.getString(4));
      } 
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw e;
    } 
    return map;
  }
  
  private static int getRuleByName(String ruleContent) throws Exception {
    int formulaid = 0;
    String ruleSetName = ruleContent.substring(0, ruleContent.indexOf("."));
    String ruleName = ruleContent.substring(ruleContent.indexOf(".") + 1);
    try {
      Connection cn = null;
      ResultSet rs = null;
      PreparedStatement ps = null;
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      cn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1435/rateonedb", "sa", "outline");
      ps = cn.prepareStatement("select FormulaID from tblFormulas where FormulaName = ? and RuleSetName = ?");
      ps.setString(1, ruleName);
      ps.setString(2, ruleSetName);
      rs = ps.executeQuery();
      while (rs.next())
        formulaid = rs.getInt(1); 
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw e;
    } 
    return formulaid;
  }
  
  private static List getDropDownList(String query) throws Exception {
    List<ResultSet> dropDownList = new ArrayList();
    try {
      Connection cn = null;
      ResultSet rs = null;
      Statement st = null;
      Class.forName("net.sourceforge.jtds.jdbc.Driver");
      cn = DriverManager.getConnection("jdbc:jtds:sqlserver://localhost:1435/rateonedb", "sa", "outline");
      st = cn.createStatement();
      rs = st.executeQuery(query);
      while (rs.next())
        dropDownList.add(rs); 
      log.debug("Records fetched : " + dropDownList.size());
    } catch (Exception e) {
      log.error("Unexpected error", e);
      throw e;
    } 
    return dropDownList;
  }
}
