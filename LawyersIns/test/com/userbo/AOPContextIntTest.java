package com.userbo;

import com.util.Context;

public class AOPContextIntTest {
	public static void main(String[] args) {
		Context ctx = new Context();
		if (AOP.getContextInt(ctx, "missing") != 0)
			throw new AssertionError("Missing percentage was not treated as zero");

		ctx.put("blank", "  ");
		if (AOP.getContextInt(ctx, "blank") != 0)
			throw new AssertionError("Blank percentage was not treated as zero");

		ctx.put("percentage", " 12 ");
		if (AOP.getContextInt(ctx, "percentage") != 12)
			throw new AssertionError("Numeric percentage was not parsed");
	}
}
