package com.userbo;

import java.util.ArrayList;
import java.util.List;

import com.manage.managemetadata.metadata.IDropDown;
import com.ormapping.ibatis.SqlResources;
import com.util.IContext;

public class StatusDropdown implements IDropDown
{

	public List getDropDownData(IContext ctx,String field)throws Exception
	{		
		List list = new ArrayList();
		
		if(ctx.get("StatusKey") == null)
			ctx.put("StatusKeys", "(1,2,3,4,5,6,7,8)");
		else if("2".equals(ctx.get("StatusKey").toString()))
			ctx.put("StatusKeys", "(2,3,4,5,7)");
		else if("3".equals(ctx.get("StatusKey").toString()))
			ctx.put("StatusKeys", "(2,4,5,6,7)");
		else if("4".equals(ctx.get("StatusKey").toString()) || "5".equals(ctx.get("StatusKey").toString()))
			ctx.put("StatusKeys", "(2)");
		else if("6".equals(ctx.get("StatusKey").toString()))
			ctx.put("StatusKeys", "(8)");
		else if("7".equals(ctx.get("StatusKey").toString()))
			ctx.put("StatusKeys", "(2,5,6)");
		
		list=SqlResources.getSqlMapProcessor(ctx).select("SqlStmts.UserStatementdropdowndatastatusDropdown", ctx);
		return list;
		
	}    
}