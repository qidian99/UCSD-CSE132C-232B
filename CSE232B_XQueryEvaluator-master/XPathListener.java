// Generated from XPath.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XPathParser}.
 */
public interface XPathListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code directChild}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterDirectChild(XPathParser.DirectChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directChild}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitDirectChild(XPathParser.DirectChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indirectChild}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterIndirectChild(XPathParser.IndirectChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indirectChild}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitIndirectChild(XPathParser.IndirectChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code all}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAll(XPathParser.AllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code all}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAll(XPathParser.AllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterFilter(XPathParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitFilter(XPathParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParent(XPathParser.ParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParent(XPathParser.ParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code current}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterCurrent(XPathParser.CurrentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code current}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitCurrent(XPathParser.CurrentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indirectChildRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterIndirectChildRP(XPathParser.IndirectChildRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indirectChildRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitIndirectChildRP(XPathParser.IndirectChildRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code text}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterText(XPathParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code text}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitText(XPathParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concat}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterConcat(XPathParser.ConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concat}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitConcat(XPathParser.ConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tagName}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XPathParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tagName}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XPathParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAttr(XPathParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAttr(XPathParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParenRP(XPathParser.ParenRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParenRP(XPathParser.ParenRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code directChildRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterDirectChildRP(XPathParser.DirectChildRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directChildRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitDirectChildRP(XPathParser.DirectChildRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code str}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterStr(XPathParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code str}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitStr(XPathParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterNot(XPathParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitNot(XPathParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterOr(XPathParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitOr(XPathParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq1}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEq1(XPathParser.Eq1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code eq1}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEq1(XPathParser.Eq1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code and}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterAnd(XPathParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitAnd(XPathParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq2}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEq2(XPathParser.Eq2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code eq2}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEq2(XPathParser.Eq2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterRpFt(XPathParser.RpFtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitRpFt(XPathParser.RpFtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code is}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterIs(XPathParser.IsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code is}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitIs(XPathParser.IsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenFt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void enterParenFt(XPathParser.ParenFtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenFt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 */
	void exitParenFt(XPathParser.ParenFtContext ctx);
}