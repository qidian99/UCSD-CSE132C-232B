// Generated from XQuery.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link XQueryParser}.
 */
public interface XQueryListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by the {@code xqAp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqAp(XQueryParser.XqApContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqAp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqAp(XQueryParser.XqApContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqVar}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqVar(XQueryParser.XqVarContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqVar}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqVar(XQueryParser.XqVarContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqJoin}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqJoin(XQueryParser.XqJoinContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqJoin}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqJoin(XQueryParser.XqJoinContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqParen}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqParen(XQueryParser.XqParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqParen}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqParen(XQueryParser.XqParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqIndirectChild}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqIndirectChild(XQueryParser.XqIndirectChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqIndirectChild}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqIndirectChild(XQueryParser.XqIndirectChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqStr}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqStr(XQueryParser.XqStrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqStr}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqStr(XQueryParser.XqStrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqClause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqClause(XQueryParser.XqClauseContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqClause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqClause(XQueryParser.XqClauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqDirectChild}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqDirectChild(XQueryParser.XqDirectChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqDirectChild}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqDirectChild(XQueryParser.XqDirectChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqConcat}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqConcat(XQueryParser.XqConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqConcat}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqConcat(XQueryParser.XqConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqLet}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqLet(XQueryParser.XqLetContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqLet}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqLet(XQueryParser.XqLetContext ctx);
	/**
	 * Enter a parse tree produced by the {@code xqTag}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void enterXqTag(XQueryParser.XqTagContext ctx);
	/**
	 * Exit a parse tree produced by the {@code xqTag}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 */
	void exitXqTag(XQueryParser.XqTagContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#forclause}.
	 * @param ctx the parse tree
	 */
	void enterForclause(XQueryParser.ForclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#forclause}.
	 * @param ctx the parse tree
	 */
	void exitForclause(XQueryParser.ForclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#letclause}.
	 * @param ctx the parse tree
	 */
	void enterLetclause(XQueryParser.LetclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#letclause}.
	 * @param ctx the parse tree
	 */
	void exitLetclause(XQueryParser.LetclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#whereclause}.
	 * @param ctx the parse tree
	 */
	void enterWhereclause(XQueryParser.WhereclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#whereclause}.
	 * @param ctx the parse tree
	 */
	void exitWhereclause(XQueryParser.WhereclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#returnclause}.
	 * @param ctx the parse tree
	 */
	void enterReturnclause(XQueryParser.ReturnclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#returnclause}.
	 * @param ctx the parse tree
	 */
	void exitReturnclause(XQueryParser.ReturnclauseContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#joinclause}.
	 * @param ctx the parse tree
	 */
	void enterJoinclause(XQueryParser.JoinclauseContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#joinclause}.
	 * @param ctx the parse tree
	 */
	void exitJoinclause(XQueryParser.JoinclauseContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEmpty(XQueryParser.CondEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEmpty(XQueryParser.CondEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condIs}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondIs(XQueryParser.CondIsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condIs}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondIs(XQueryParser.CondIsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condSome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondSome(XQueryParser.CondSomeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condSome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondSome(XQueryParser.CondSomeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condParen}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondParen(XQueryParser.CondParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condParen}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondParen(XQueryParser.CondParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondAnd(XQueryParser.CondAndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondAnd(XQueryParser.CondAndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondOr(XQueryParser.CondOrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondOr(XQueryParser.CondOrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code condEq2}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEq2(XQueryParser.CondEq2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code condEq2}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEq2(XQueryParser.CondEq2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code condEq1}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondEq1(XQueryParser.CondEq1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code condEq1}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondEq1(XQueryParser.CondEq1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code condNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCondNot(XQueryParser.CondNotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code condNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCondNot(XQueryParser.CondNotContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 */
	void enterVar(XQueryParser.VarContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 */
	void exitVar(XQueryParser.VarContext ctx);
	/**
	 * Enter a parse tree produced by {@link XQueryParser#varArr}.
	 * @param ctx the parse tree
	 */
	void enterVarArr(XQueryParser.VarArrContext ctx);
	/**
	 * Exit a parse tree produced by {@link XQueryParser#varArr}.
	 * @param ctx the parse tree
	 */
	void exitVarArr(XQueryParser.VarArrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code directChild}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterDirectChild(XQueryParser.DirectChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directChild}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitDirectChild(XQueryParser.DirectChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indirectChild}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void enterIndirectChild(XQueryParser.IndirectChildContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indirectChild}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 */
	void exitIndirectChild(XQueryParser.IndirectChildContext ctx);
	/**
	 * Enter a parse tree produced by the {@code all}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAll(XQueryParser.AllContext ctx);
	/**
	 * Exit a parse tree produced by the {@code all}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAll(XQueryParser.AllContext ctx);
	/**
	 * Enter a parse tree produced by the {@code filter}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterFilter(XQueryParser.FilterContext ctx);
	/**
	 * Exit a parse tree produced by the {@code filter}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitFilter(XQueryParser.FilterContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parent}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParent(XQueryParser.ParentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parent}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParent(XQueryParser.ParentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code current}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterCurrent(XQueryParser.CurrentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code current}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitCurrent(XQueryParser.CurrentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code indirectChildRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterIndirectChildRP(XQueryParser.IndirectChildRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code indirectChildRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitIndirectChildRP(XQueryParser.IndirectChildRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code text}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterText(XQueryParser.TextContext ctx);
	/**
	 * Exit a parse tree produced by the {@code text}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitText(XQueryParser.TextContext ctx);
	/**
	 * Enter a parse tree produced by the {@code concat}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterConcat(XQueryParser.ConcatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code concat}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitConcat(XQueryParser.ConcatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code tagName}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Exit a parse tree produced by the {@code tagName}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Enter a parse tree produced by the {@code attr}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterAttr(XQueryParser.AttrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitAttr(XQueryParser.AttrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterParenRP(XQueryParser.ParenRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitParenRP(XQueryParser.ParenRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code directChildRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void enterDirectChildRP(XQueryParser.DirectChildRPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code directChildRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 */
	void exitDirectChildRP(XQueryParser.DirectChildRPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code str}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterStr(XQueryParser.StrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code str}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitStr(XQueryParser.StrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterNot(XQueryParser.NotContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitNot(XQueryParser.NotContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterOr(XQueryParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitOr(XQueryParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq1}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEq1(XQueryParser.Eq1Context ctx);
	/**
	 * Exit a parse tree produced by the {@code eq1}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEq1(XQueryParser.Eq1Context ctx);
	/**
	 * Enter a parse tree produced by the {@code and}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterAnd(XQueryParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitAnd(XQueryParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code eq2}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterEq2(XQueryParser.Eq2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code eq2}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitEq2(XQueryParser.Eq2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code rpFt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterRpFt(XQueryParser.RpFtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code rpFt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitRpFt(XQueryParser.RpFtContext ctx);
	/**
	 * Enter a parse tree produced by the {@code is}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterIs(XQueryParser.IsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code is}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitIs(XQueryParser.IsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code parenFt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void enterParenFt(XQueryParser.ParenFtContext ctx);
	/**
	 * Exit a parse tree produced by the {@code parenFt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 */
	void exitParenFt(XQueryParser.ParenFtContext ctx);
}