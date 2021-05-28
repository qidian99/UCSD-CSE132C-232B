// Generated from XQuery.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XQueryParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XQueryVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code xqAp}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqAp(XQueryParser.XqApContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqVar}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqVar(XQueryParser.XqVarContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqJoin}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqJoin(XQueryParser.XqJoinContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqParen}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqParen(XQueryParser.XqParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqIndirectChild}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqIndirectChild(XQueryParser.XqIndirectChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqStr}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqStr(XQueryParser.XqStrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqClause}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqClause(XQueryParser.XqClauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqDirectChild}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqDirectChild(XQueryParser.XqDirectChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqConcat}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqConcat(XQueryParser.XqConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqLet}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqLet(XQueryParser.XqLetContext ctx);
	/**
	 * Visit a parse tree produced by the {@code xqTag}
	 * labeled alternative in {@link XQueryParser#xq}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitXqTag(XQueryParser.XqTagContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#forclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitForclause(XQueryParser.ForclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#letclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetclause(XQueryParser.LetclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#whereclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhereclause(XQueryParser.WhereclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#returnclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnclause(XQueryParser.ReturnclauseContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#joinclause}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitJoinclause(XQueryParser.JoinclauseContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condEmpty}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondEmpty(XQueryParser.CondEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condIs}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondIs(XQueryParser.CondIsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condSome}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondSome(XQueryParser.CondSomeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condParen}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondParen(XQueryParser.CondParenContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condAnd}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondAnd(XQueryParser.CondAndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condOr}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondOr(XQueryParser.CondOrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code condEq2}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondEq2(XQueryParser.CondEq2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code condEq1}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondEq1(XQueryParser.CondEq1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code condNot}
	 * labeled alternative in {@link XQueryParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCondNot(XQueryParser.CondNotContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#var}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar(XQueryParser.VarContext ctx);
	/**
	 * Visit a parse tree produced by {@link XQueryParser#varArr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarArr(XQueryParser.VarArrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code directChild}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectChild(XQueryParser.DirectChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indirectChild}
	 * labeled alternative in {@link XQueryParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirectChild(XQueryParser.IndirectChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code all}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll(XQueryParser.AllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(XQueryParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parent}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParent(XQueryParser.ParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code current}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrent(XQueryParser.CurrentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indirectChildRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirectChildRP(XQueryParser.IndirectChildRPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code text}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(XQueryParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concat}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcat(XQueryParser.ConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tagName}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(XQueryParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(XQueryParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenRP(XQueryParser.ParenRPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code directChildRP}
	 * labeled alternative in {@link XQueryParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectChildRP(XQueryParser.DirectChildRPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code str}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(XQueryParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(XQueryParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(XQueryParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq1}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq1(XQueryParser.Eq1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(XQueryParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq2}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq2(XQueryParser.Eq2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code rpFt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpFt(XQueryParser.RpFtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code is}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs(XQueryParser.IsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenFt}
	 * labeled alternative in {@link XQueryParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenFt(XQueryParser.ParenFtContext ctx);
}