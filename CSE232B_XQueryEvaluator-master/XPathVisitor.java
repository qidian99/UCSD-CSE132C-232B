// Generated from XPath.g4 by ANTLR 4.9
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link XPathParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface XPathVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by the {@code directChild}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectChild(XPathParser.DirectChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indirectChild}
	 * labeled alternative in {@link XPathParser#ap}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirectChild(XPathParser.IndirectChildContext ctx);
	/**
	 * Visit a parse tree produced by the {@code all}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAll(XPathParser.AllContext ctx);
	/**
	 * Visit a parse tree produced by the {@code filter}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFilter(XPathParser.FilterContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parent}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParent(XPathParser.ParentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code current}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCurrent(XPathParser.CurrentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code indirectChildRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndirectChildRP(XPathParser.IndirectChildRPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code text}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitText(XPathParser.TextContext ctx);
	/**
	 * Visit a parse tree produced by the {@code concat}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConcat(XPathParser.ConcatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code tagName}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTagName(XPathParser.TagNameContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attr}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttr(XPathParser.AttrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenRP(XPathParser.ParenRPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code directChildRP}
	 * labeled alternative in {@link XPathParser#rp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDirectChildRP(XPathParser.DirectChildRPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code str}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStr(XPathParser.StrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot(XPathParser.NotContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(XPathParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq1}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq1(XPathParser.Eq1Context ctx);
	/**
	 * Visit a parse tree produced by the {@code and}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(XPathParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eq2}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEq2(XPathParser.Eq2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code rpFt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRpFt(XPathParser.RpFtContext ctx);
	/**
	 * Visit a parse tree produced by the {@code is}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIs(XPathParser.IsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenFt}
	 * labeled alternative in {@link XPathParser#f}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenFt(XPathParser.ParenFtContext ctx);
}