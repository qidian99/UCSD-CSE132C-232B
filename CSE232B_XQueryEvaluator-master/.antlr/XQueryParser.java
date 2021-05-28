// Generated from /Users/zouhongyu/Desktop/CSE232B_XQueryEvaluator/XQuery.g4 by ANTLR 4.8
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XQueryParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, DOC=35, IS=36, NAME=37, STR=38, WS=39;
	public static final int
		RULE_xq = 0, RULE_forclause = 1, RULE_letclause = 2, RULE_whereclause = 3, 
		RULE_returnclause = 4, RULE_joinclause = 5, RULE_cond = 6, RULE_var = 7, 
		RULE_varArr = 8, RULE_ap = 9, RULE_rp = 10, RULE_f = 11;
	private static String[] makeRuleNames() {
		return new String[] {
			"xq", "forclause", "letclause", "whereclause", "returnclause", "joinclause", 
			"cond", "var", "varArr", "ap", "rp", "f"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'('", "')'", "'<'", "'>'", "'{'", "'}'", "'/'", "'//'", "','", 
			"'for'", "'in'", "'let'", "':='", "'where'", "'return'", "'join'", "'='", 
			"'eq'", "'empty'", "'some'", "'satisfies'", "'and'", "'or'", "'not'", 
			"'$'", "'['", "']'", "'(\"'", "'\")'", "'*'", "'.'", "'..'", "'text()'", 
			"'@'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, "DOC", 
			"IS", "NAME", "STR", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XQuery.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XQueryParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class XqContext extends ParserRuleContext {
		public XqContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xq; }
	 
		public XqContext() { }
		public void copyFrom(XqContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class XqApContext extends XqContext {
		public ApContext ap() {
			return getRuleContext(ApContext.class,0);
		}
		public XqApContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqVarContext extends XqContext {
		public VarContext var() {
			return getRuleContext(VarContext.class,0);
		}
		public XqVarContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqJoinContext extends XqContext {
		public JoinclauseContext joinclause() {
			return getRuleContext(JoinclauseContext.class,0);
		}
		public XqJoinContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqParenContext extends XqContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public XqParenContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqIndirectChildContext extends XqContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public XqIndirectChildContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqStrContext extends XqContext {
		public TerminalNode STR() { return getToken(XQueryParser.STR, 0); }
		public XqStrContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqClauseContext extends XqContext {
		public ForclauseContext forclause() {
			return getRuleContext(ForclauseContext.class,0);
		}
		public ReturnclauseContext returnclause() {
			return getRuleContext(ReturnclauseContext.class,0);
		}
		public LetclauseContext letclause() {
			return getRuleContext(LetclauseContext.class,0);
		}
		public WhereclauseContext whereclause() {
			return getRuleContext(WhereclauseContext.class,0);
		}
		public XqClauseContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqDirectChildContext extends XqContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public XqDirectChildContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqConcatContext extends XqContext {
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public XqConcatContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqLetContext extends XqContext {
		public LetclauseContext letclause() {
			return getRuleContext(LetclauseContext.class,0);
		}
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public XqLetContext(XqContext ctx) { copyFrom(ctx); }
	}
	public static class XqTagContext extends XqContext {
		public List<TerminalNode> NAME() { return getTokens(XQueryParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(XQueryParser.NAME, i);
		}
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public XqTagContext(XqContext ctx) { copyFrom(ctx); }
	}

	public final XqContext xq() throws RecognitionException {
		return xq(0);
	}

	private XqContext xq(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		XqContext _localctx = new XqContext(_ctx, _parentState);
		XqContext _prevctx = _localctx;
		int _startState = 0;
		enterRecursionRule(_localctx, 0, RULE_xq, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				{
				_localctx = new XqVarContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(25);
				var();
				}
				break;
			case STR:
				{
				_localctx = new XqStrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				match(STR);
				}
				break;
			case DOC:
				{
				_localctx = new XqApContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(27);
				ap();
				}
				break;
			case T__0:
				{
				_localctx = new XqParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(28);
				match(T__0);
				setState(29);
				xq(0);
				setState(30);
				match(T__1);
				}
				break;
			case T__2:
				{
				_localctx = new XqTagContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(32);
				match(T__2);
				setState(33);
				match(NAME);
				setState(34);
				match(T__3);
				setState(35);
				match(T__4);
				setState(36);
				xq(0);
				setState(37);
				match(T__5);
				setState(38);
				match(T__2);
				setState(39);
				match(T__6);
				setState(40);
				match(NAME);
				setState(41);
				match(T__3);
				}
				break;
			case T__9:
				{
				_localctx = new XqClauseContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(43);
				forclause();
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__11) {
					{
					setState(44);
					letclause();
					}
				}

				setState(48);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__13) {
					{
					setState(47);
					whereclause();
					}
				}

				setState(50);
				returnclause();
				}
				break;
			case T__11:
				{
				_localctx = new XqLetContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(52);
				letclause();
				setState(53);
				xq(3);
				}
				break;
			case T__15:
				{
				_localctx = new XqJoinContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(55);
				joinclause();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(69);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(67);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new XqConcatContext(new XqContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(58);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(59);
						match(T__8);
						setState(60);
						xq(2);
						}
						break;
					case 2:
						{
						_localctx = new XqDirectChildContext(new XqContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(61);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(62);
						match(T__6);
						setState(63);
						rp(0);
						}
						break;
					case 3:
						{
						_localctx = new XqIndirectChildContext(new XqContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_xq);
						setState(64);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(65);
						match(T__7);
						setState(66);
						rp(0);
						}
						break;
					}
					} 
				}
				setState(71);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class ForclauseContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public ForclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forclause; }
	}

	public final ForclauseContext forclause() throws RecognitionException {
		ForclauseContext _localctx = new ForclauseContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_forclause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__9);
			setState(73);
			var();
			setState(74);
			match(T__10);
			setState(75);
			xq(0);
			setState(83);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(76);
				match(T__8);
				setState(77);
				var();
				setState(78);
				match(T__10);
				setState(79);
				xq(0);
				}
				}
				setState(85);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LetclauseContext extends ParserRuleContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public LetclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_letclause; }
	}

	public final LetclauseContext letclause() throws RecognitionException {
		LetclauseContext _localctx = new LetclauseContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_letclause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			match(T__11);
			setState(87);
			var();
			setState(88);
			match(T__12);
			setState(89);
			xq(0);
			setState(97);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(90);
				match(T__8);
				setState(91);
				var();
				setState(92);
				match(T__12);
				setState(93);
				xq(0);
				}
				}
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhereclauseContext extends ParserRuleContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public WhereclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whereclause; }
	}

	public final WhereclauseContext whereclause() throws RecognitionException {
		WhereclauseContext _localctx = new WhereclauseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_whereclause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(T__13);
			setState(101);
			cond(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnclauseContext extends ParserRuleContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public ReturnclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnclause; }
	}

	public final ReturnclauseContext returnclause() throws RecognitionException {
		ReturnclauseContext _localctx = new ReturnclauseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_returnclause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(T__14);
			setState(104);
			xq(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class JoinclauseContext extends ParserRuleContext {
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public List<VarArrContext> varArr() {
			return getRuleContexts(VarArrContext.class);
		}
		public VarArrContext varArr(int i) {
			return getRuleContext(VarArrContext.class,i);
		}
		public JoinclauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_joinclause; }
	}

	public final JoinclauseContext joinclause() throws RecognitionException {
		JoinclauseContext _localctx = new JoinclauseContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_joinclause);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(106);
			match(T__15);
			setState(107);
			match(T__0);
			setState(108);
			xq(0);
			setState(109);
			match(T__8);
			setState(110);
			xq(0);
			setState(111);
			match(T__8);
			setState(112);
			varArr();
			setState(113);
			match(T__8);
			setState(114);
			varArr();
			setState(115);
			match(T__1);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CondContext extends ParserRuleContext {
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
	 
		public CondContext() { }
		public void copyFrom(CondContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class CondEmptyContext extends CondContext {
		public XqContext xq() {
			return getRuleContext(XqContext.class,0);
		}
		public CondEmptyContext(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondIsContext extends CondContext {
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public TerminalNode IS() { return getToken(XQueryParser.IS, 0); }
		public CondIsContext(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondSomeContext extends CondContext {
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondSomeContext(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondParenContext extends CondContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondParenContext(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondAndContext extends CondContext {
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public CondAndContext(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondOrContext extends CondContext {
		public List<CondContext> cond() {
			return getRuleContexts(CondContext.class);
		}
		public CondContext cond(int i) {
			return getRuleContext(CondContext.class,i);
		}
		public CondOrContext(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondEq2Context extends CondContext {
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public CondEq2Context(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondEq1Context extends CondContext {
		public List<XqContext> xq() {
			return getRuleContexts(XqContext.class);
		}
		public XqContext xq(int i) {
			return getRuleContext(XqContext.class,i);
		}
		public CondEq1Context(CondContext ctx) { copyFrom(ctx); }
	}
	public static class CondNotContext extends CondContext {
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public CondNotContext(CondContext ctx) { copyFrom(ctx); }
	}

	public final CondContext cond() throws RecognitionException {
		return cond(0);
	}

	private CondContext cond(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CondContext _localctx = new CondContext(_ctx, _parentState);
		CondContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_cond, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				{
				_localctx = new CondEq1Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(118);
				xq(0);
				setState(119);
				match(T__16);
				setState(120);
				xq(0);
				}
				break;
			case 2:
				{
				_localctx = new CondEq2Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(122);
				xq(0);
				setState(123);
				match(T__17);
				setState(124);
				xq(0);
				}
				break;
			case 3:
				{
				_localctx = new CondIsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(126);
				xq(0);
				setState(127);
				match(IS);
				setState(128);
				xq(0);
				}
				break;
			case 4:
				{
				_localctx = new CondEmptyContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(130);
				match(T__18);
				setState(131);
				match(T__0);
				setState(132);
				xq(0);
				setState(133);
				match(T__1);
				}
				break;
			case 5:
				{
				_localctx = new CondSomeContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(135);
				match(T__19);
				setState(136);
				var();
				setState(137);
				match(T__10);
				setState(138);
				xq(0);
				setState(146);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__8) {
					{
					{
					setState(139);
					match(T__8);
					setState(140);
					var();
					setState(141);
					match(T__10);
					setState(142);
					xq(0);
					}
					}
					setState(148);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(149);
				match(T__20);
				setState(150);
				cond(5);
				}
				break;
			case 6:
				{
				_localctx = new CondParenContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(152);
				match(T__0);
				setState(153);
				cond(0);
				setState(154);
				match(T__1);
				}
				break;
			case 7:
				{
				_localctx = new CondNotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(156);
				match(T__23);
				setState(157);
				cond(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(168);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(166);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						_localctx = new CondAndContext(new CondContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(160);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(161);
						match(T__21);
						setState(162);
						cond(4);
						}
						break;
					case 2:
						{
						_localctx = new CondOrContext(new CondContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_cond);
						setState(163);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(164);
						match(T__22);
						setState(165);
						cond(3);
						}
						break;
					}
					} 
				}
				setState(170);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class VarContext extends ParserRuleContext {
		public TerminalNode NAME() { return getToken(XQueryParser.NAME, 0); }
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(T__24);
			setState(172);
			match(NAME);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VarArrContext extends ParserRuleContext {
		public List<TerminalNode> NAME() { return getTokens(XQueryParser.NAME); }
		public TerminalNode NAME(int i) {
			return getToken(XQueryParser.NAME, i);
		}
		public VarArrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varArr; }
	}

	public final VarArrContext varArr() throws RecognitionException {
		VarArrContext _localctx = new VarArrContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_varArr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__25);
			setState(175);
			match(NAME);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(176);
				match(T__8);
				setState(177);
				match(NAME);
				}
				}
				setState(182);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(183);
			match(T__26);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ApContext extends ParserRuleContext {
		public ApContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ap; }
	 
		public ApContext() { }
		public void copyFrom(ApContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DirectChildContext extends ApContext {
		public TerminalNode DOC() { return getToken(XQueryParser.DOC, 0); }
		public TerminalNode NAME() { return getToken(XQueryParser.NAME, 0); }
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public DirectChildContext(ApContext ctx) { copyFrom(ctx); }
	}
	public static class IndirectChildContext extends ApContext {
		public TerminalNode DOC() { return getToken(XQueryParser.DOC, 0); }
		public TerminalNode NAME() { return getToken(XQueryParser.NAME, 0); }
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public IndirectChildContext(ApContext ctx) { copyFrom(ctx); }
	}

	public final ApContext ap() throws RecognitionException {
		ApContext _localctx = new ApContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_ap);
		try {
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				_localctx = new DirectChildContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				match(DOC);
				setState(186);
				match(T__27);
				setState(187);
				match(NAME);
				setState(188);
				match(T__28);
				setState(189);
				match(T__6);
				setState(190);
				rp(0);
				}
				break;
			case 2:
				_localctx = new IndirectChildContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(191);
				match(DOC);
				setState(192);
				match(T__27);
				setState(193);
				match(NAME);
				setState(194);
				match(T__28);
				setState(195);
				match(T__7);
				setState(196);
				rp(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class RpContext extends ParserRuleContext {
		public RpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rp; }
	 
		public RpContext() { }
		public void copyFrom(RpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AllContext extends RpContext {
		public AllContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class FilterContext extends RpContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public FilterContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class ParentContext extends RpContext {
		public ParentContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class CurrentContext extends RpContext {
		public CurrentContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class IndirectChildRPContext extends RpContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public IndirectChildRPContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class TextContext extends RpContext {
		public TextContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class ConcatContext extends RpContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public ConcatContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class TagNameContext extends RpContext {
		public TerminalNode NAME() { return getToken(XQueryParser.NAME, 0); }
		public TagNameContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class AttrContext extends RpContext {
		public TerminalNode NAME() { return getToken(XQueryParser.NAME, 0); }
		public AttrContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class ParenRPContext extends RpContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public ParenRPContext(RpContext ctx) { copyFrom(ctx); }
	}
	public static class DirectChildRPContext extends RpContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public DirectChildRPContext(RpContext ctx) { copyFrom(ctx); }
	}

	public final RpContext rp() throws RecognitionException {
		return rp(0);
	}

	private RpContext rp(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		RpContext _localctx = new RpContext(_ctx, _parentState);
		RpContext _prevctx = _localctx;
		int _startState = 20;
		enterRecursionRule(_localctx, 20, RULE_rp, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NAME:
				{
				_localctx = new TagNameContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(200);
				match(NAME);
				}
				break;
			case T__29:
				{
				_localctx = new AllContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(201);
				match(T__29);
				}
				break;
			case T__30:
				{
				_localctx = new CurrentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(202);
				match(T__30);
				}
				break;
			case T__31:
				{
				_localctx = new ParentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(203);
				match(T__31);
				}
				break;
			case T__32:
				{
				_localctx = new TextContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(204);
				match(T__32);
				}
				break;
			case T__33:
				{
				_localctx = new AttrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(205);
				match(T__33);
				setState(206);
				match(NAME);
				}
				break;
			case T__0:
				{
				_localctx = new ParenRPContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(207);
				match(T__0);
				setState(208);
				rp(0);
				setState(209);
				match(T__1);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(229);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(227);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
					case 1:
						{
						_localctx = new ConcatContext(new RpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(213);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(214);
						match(T__8);
						setState(215);
						rp(5);
						}
						break;
					case 2:
						{
						_localctx = new DirectChildRPContext(new RpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(216);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(217);
						match(T__6);
						setState(218);
						rp(4);
						}
						break;
					case 3:
						{
						_localctx = new IndirectChildRPContext(new RpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(219);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(220);
						match(T__7);
						setState(221);
						rp(3);
						}
						break;
					case 4:
						{
						_localctx = new FilterContext(new RpContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_rp);
						setState(222);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(223);
						match(T__25);
						setState(224);
						f(0);
						setState(225);
						match(T__26);
						}
						break;
					}
					} 
				}
				setState(231);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FContext extends ParserRuleContext {
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }
	 
		public FContext() { }
		public void copyFrom(FContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StrContext extends FContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public TerminalNode STR() { return getToken(XQueryParser.STR, 0); }
		public StrContext(FContext ctx) { copyFrom(ctx); }
	}
	public static class NotContext extends FContext {
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public NotContext(FContext ctx) { copyFrom(ctx); }
	}
	public static class OrContext extends FContext {
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public OrContext(FContext ctx) { copyFrom(ctx); }
	}
	public static class Eq1Context extends FContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public Eq1Context(FContext ctx) { copyFrom(ctx); }
	}
	public static class AndContext extends FContext {
		public List<FContext> f() {
			return getRuleContexts(FContext.class);
		}
		public FContext f(int i) {
			return getRuleContext(FContext.class,i);
		}
		public AndContext(FContext ctx) { copyFrom(ctx); }
	}
	public static class Eq2Context extends FContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public Eq2Context(FContext ctx) { copyFrom(ctx); }
	}
	public static class RpFtContext extends FContext {
		public RpContext rp() {
			return getRuleContext(RpContext.class,0);
		}
		public RpFtContext(FContext ctx) { copyFrom(ctx); }
	}
	public static class IsContext extends FContext {
		public List<RpContext> rp() {
			return getRuleContexts(RpContext.class);
		}
		public RpContext rp(int i) {
			return getRuleContext(RpContext.class,i);
		}
		public TerminalNode IS() { return getToken(XQueryParser.IS, 0); }
		public IsContext(FContext ctx) { copyFrom(ctx); }
	}
	public static class ParenFtContext extends FContext {
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public ParenFtContext(FContext ctx) { copyFrom(ctx); }
	}

	public final FContext f() throws RecognitionException {
		return f(0);
	}

	private FContext f(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FContext _localctx = new FContext(_ctx, _parentState);
		FContext _prevctx = _localctx;
		int _startState = 22;
		enterRecursionRule(_localctx, 22, RULE_f, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(256);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				_localctx = new RpFtContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(233);
				rp(0);
				}
				break;
			case 2:
				{
				_localctx = new Eq1Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(234);
				rp(0);
				setState(235);
				match(T__16);
				setState(236);
				rp(0);
				}
				break;
			case 3:
				{
				_localctx = new StrContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(238);
				rp(0);
				setState(239);
				match(T__16);
				setState(240);
				match(STR);
				}
				break;
			case 4:
				{
				_localctx = new Eq2Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(242);
				rp(0);
				setState(243);
				match(T__17);
				setState(244);
				rp(0);
				}
				break;
			case 5:
				{
				_localctx = new IsContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(246);
				rp(0);
				setState(247);
				match(IS);
				setState(248);
				rp(0);
				}
				break;
			case 6:
				{
				_localctx = new ParenFtContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(250);
				match(T__0);
				setState(251);
				f(0);
				setState(252);
				match(T__1);
				}
				break;
			case 7:
				{
				_localctx = new NotContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(254);
				match(T__23);
				setState(255);
				f(1);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(266);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(264);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new AndContext(new FContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(258);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(259);
						match(T__21);
						setState(260);
						f(4);
						}
						break;
					case 2:
						{
						_localctx = new OrContext(new FContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_f);
						setState(261);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(262);
						match(T__22);
						setState(263);
						f(3);
						}
						break;
					}
					} 
				}
				setState(268);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 0:
			return xq_sempred((XqContext)_localctx, predIndex);
		case 6:
			return cond_sempred((CondContext)_localctx, predIndex);
		case 10:
			return rp_sempred((RpContext)_localctx, predIndex);
		case 11:
			return f_sempred((FContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean xq_sempred(XqContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 1);
		case 1:
			return precpred(_ctx, 6);
		case 2:
			return precpred(_ctx, 5);
		}
		return true;
	}
	private boolean cond_sempred(CondContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 3);
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean rp_sempred(RpContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 4);
		case 6:
			return precpred(_ctx, 3);
		case 7:
			return precpred(_ctx, 2);
		case 8:
			return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean f_sempred(FContext _localctx, int predIndex) {
		switch (predIndex) {
		case 9:
			return precpred(_ctx, 3);
		case 10:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u0110\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\60\n\2\3\2\5\2\63\n\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\5\2;\n\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\7\2F\n\2\f"+
		"\2\16\2I\13\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3T\n\3\f\3\16\3W\13"+
		"\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\7\4b\n\4\f\4\16\4e\13\4\3\5\3\5"+
		"\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0093\n\b\f\b\16\b\u0096\13\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u00a1\n\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b"+
		"\u00a9\n\b\f\b\16\b\u00ac\13\b\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00b5\n"+
		"\n\f\n\16\n\u00b8\13\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\5\13\u00c8\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\5\f\u00d6\n\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\7\f\u00e6\n\f\f\f\16\f\u00e9\13\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\5\r\u0103\n\r\3\r\3\r\3\r\3\r\3\r\3\r\7\r\u010b\n\r\f\r\16\r\u010e"+
		"\13\r\3\r\2\6\2\16\26\30\16\2\4\6\b\n\f\16\20\22\24\26\30\2\2\2\u012e"+
		"\2:\3\2\2\2\4J\3\2\2\2\6X\3\2\2\2\bf\3\2\2\2\ni\3\2\2\2\fl\3\2\2\2\16"+
		"\u00a0\3\2\2\2\20\u00ad\3\2\2\2\22\u00b0\3\2\2\2\24\u00c7\3\2\2\2\26\u00d5"+
		"\3\2\2\2\30\u0102\3\2\2\2\32\33\b\2\1\2\33;\5\20\t\2\34;\7(\2\2\35;\5"+
		"\24\13\2\36\37\7\3\2\2\37 \5\2\2\2 !\7\4\2\2!;\3\2\2\2\"#\7\5\2\2#$\7"+
		"\'\2\2$%\7\6\2\2%&\7\7\2\2&\'\5\2\2\2\'(\7\b\2\2()\7\5\2\2)*\7\t\2\2*"+
		"+\7\'\2\2+,\7\6\2\2,;\3\2\2\2-/\5\4\3\2.\60\5\6\4\2/.\3\2\2\2/\60\3\2"+
		"\2\2\60\62\3\2\2\2\61\63\5\b\5\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64\3\2"+
		"\2\2\64\65\5\n\6\2\65;\3\2\2\2\66\67\5\6\4\2\678\5\2\2\58;\3\2\2\29;\5"+
		"\f\7\2:\32\3\2\2\2:\34\3\2\2\2:\35\3\2\2\2:\36\3\2\2\2:\"\3\2\2\2:-\3"+
		"\2\2\2:\66\3\2\2\2:9\3\2\2\2;G\3\2\2\2<=\f\3\2\2=>\7\13\2\2>F\5\2\2\4"+
		"?@\f\b\2\2@A\7\t\2\2AF\5\26\f\2BC\f\7\2\2CD\7\n\2\2DF\5\26\f\2E<\3\2\2"+
		"\2E?\3\2\2\2EB\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2H\3\3\2\2\2IG\3\2"+
		"\2\2JK\7\f\2\2KL\5\20\t\2LM\7\r\2\2MU\5\2\2\2NO\7\13\2\2OP\5\20\t\2PQ"+
		"\7\r\2\2QR\5\2\2\2RT\3\2\2\2SN\3\2\2\2TW\3\2\2\2US\3\2\2\2UV\3\2\2\2V"+
		"\5\3\2\2\2WU\3\2\2\2XY\7\16\2\2YZ\5\20\t\2Z[\7\17\2\2[c\5\2\2\2\\]\7\13"+
		"\2\2]^\5\20\t\2^_\7\17\2\2_`\5\2\2\2`b\3\2\2\2a\\\3\2\2\2be\3\2\2\2ca"+
		"\3\2\2\2cd\3\2\2\2d\7\3\2\2\2ec\3\2\2\2fg\7\20\2\2gh\5\16\b\2h\t\3\2\2"+
		"\2ij\7\21\2\2jk\5\2\2\2k\13\3\2\2\2lm\7\22\2\2mn\7\3\2\2no\5\2\2\2op\7"+
		"\13\2\2pq\5\2\2\2qr\7\13\2\2rs\5\22\n\2st\7\13\2\2tu\5\22\n\2uv\7\4\2"+
		"\2v\r\3\2\2\2wx\b\b\1\2xy\5\2\2\2yz\7\23\2\2z{\5\2\2\2{\u00a1\3\2\2\2"+
		"|}\5\2\2\2}~\7\24\2\2~\177\5\2\2\2\177\u00a1\3\2\2\2\u0080\u0081\5\2\2"+
		"\2\u0081\u0082\7&\2\2\u0082\u0083\5\2\2\2\u0083\u00a1\3\2\2\2\u0084\u0085"+
		"\7\25\2\2\u0085\u0086\7\3\2\2\u0086\u0087\5\2\2\2\u0087\u0088\7\4\2\2"+
		"\u0088\u00a1\3\2\2\2\u0089\u008a\7\26\2\2\u008a\u008b\5\20\t\2\u008b\u008c"+
		"\7\r\2\2\u008c\u0094\5\2\2\2\u008d\u008e\7\13\2\2\u008e\u008f\5\20\t\2"+
		"\u008f\u0090\7\r\2\2\u0090\u0091\5\2\2\2\u0091\u0093\3\2\2\2\u0092\u008d"+
		"\3\2\2\2\u0093\u0096\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095"+
		"\u0097\3\2\2\2\u0096\u0094\3\2\2\2\u0097\u0098\7\27\2\2\u0098\u0099\5"+
		"\16\b\7\u0099\u00a1\3\2\2\2\u009a\u009b\7\3\2\2\u009b\u009c\5\16\b\2\u009c"+
		"\u009d\7\4\2\2\u009d\u00a1\3\2\2\2\u009e\u009f\7\32\2\2\u009f\u00a1\5"+
		"\16\b\3\u00a0w\3\2\2\2\u00a0|\3\2\2\2\u00a0\u0080\3\2\2\2\u00a0\u0084"+
		"\3\2\2\2\u00a0\u0089\3\2\2\2\u00a0\u009a\3\2\2\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00aa\3\2\2\2\u00a2\u00a3\f\5\2\2\u00a3\u00a4\7\30\2\2\u00a4\u00a9\5"+
		"\16\b\6\u00a5\u00a6\f\4\2\2\u00a6\u00a7\7\31\2\2\u00a7\u00a9\5\16\b\5"+
		"\u00a8\u00a2\3\2\2\2\u00a8\u00a5\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8"+
		"\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\17\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad"+
		"\u00ae\7\33\2\2\u00ae\u00af\7\'\2\2\u00af\21\3\2\2\2\u00b0\u00b1\7\34"+
		"\2\2\u00b1\u00b6\7\'\2\2\u00b2\u00b3\7\13\2\2\u00b3\u00b5\7\'\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b5\u00b8\3\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2"+
		"\2\2\u00b7\u00b9\3\2\2\2\u00b8\u00b6\3\2\2\2\u00b9\u00ba\7\35\2\2\u00ba"+
		"\23\3\2\2\2\u00bb\u00bc\7%\2\2\u00bc\u00bd\7\36\2\2\u00bd\u00be\7\'\2"+
		"\2\u00be\u00bf\7\37\2\2\u00bf\u00c0\7\t\2\2\u00c0\u00c8\5\26\f\2\u00c1"+
		"\u00c2\7%\2\2\u00c2\u00c3\7\36\2\2\u00c3\u00c4\7\'\2\2\u00c4\u00c5\7\37"+
		"\2\2\u00c5\u00c6\7\n\2\2\u00c6\u00c8\5\26\f\2\u00c7\u00bb\3\2\2\2\u00c7"+
		"\u00c1\3\2\2\2\u00c8\25\3\2\2\2\u00c9\u00ca\b\f\1\2\u00ca\u00d6\7\'\2"+
		"\2\u00cb\u00d6\7 \2\2\u00cc\u00d6\7!\2\2\u00cd\u00d6\7\"\2\2\u00ce\u00d6"+
		"\7#\2\2\u00cf\u00d0\7$\2\2\u00d0\u00d6\7\'\2\2\u00d1\u00d2\7\3\2\2\u00d2"+
		"\u00d3\5\26\f\2\u00d3\u00d4\7\4\2\2\u00d4\u00d6\3\2\2\2\u00d5\u00c9\3"+
		"\2\2\2\u00d5\u00cb\3\2\2\2\u00d5\u00cc\3\2\2\2\u00d5\u00cd\3\2\2\2\u00d5"+
		"\u00ce\3\2\2\2\u00d5\u00cf\3\2\2\2\u00d5\u00d1\3\2\2\2\u00d6\u00e7\3\2"+
		"\2\2\u00d7\u00d8\f\6\2\2\u00d8\u00d9\7\13\2\2\u00d9\u00e6\5\26\f\7\u00da"+
		"\u00db\f\5\2\2\u00db\u00dc\7\t\2\2\u00dc\u00e6\5\26\f\6\u00dd\u00de\f"+
		"\4\2\2\u00de\u00df\7\n\2\2\u00df\u00e6\5\26\f\5\u00e0\u00e1\f\3\2\2\u00e1"+
		"\u00e2\7\34\2\2\u00e2\u00e3\5\30\r\2\u00e3\u00e4\7\35\2\2\u00e4\u00e6"+
		"\3\2\2\2\u00e5\u00d7\3\2\2\2\u00e5\u00da\3\2\2\2\u00e5\u00dd\3\2\2\2\u00e5"+
		"\u00e0\3\2\2\2\u00e6\u00e9\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2"+
		"\2\2\u00e8\27\3\2\2\2\u00e9\u00e7\3\2\2\2\u00ea\u00eb\b\r\1\2\u00eb\u0103"+
		"\5\26\f\2\u00ec\u00ed\5\26\f\2\u00ed\u00ee\7\23\2\2\u00ee\u00ef\5\26\f"+
		"\2\u00ef\u0103\3\2\2\2\u00f0\u00f1\5\26\f\2\u00f1\u00f2\7\23\2\2\u00f2"+
		"\u00f3\7(\2\2\u00f3\u0103\3\2\2\2\u00f4\u00f5\5\26\f\2\u00f5\u00f6\7\24"+
		"\2\2\u00f6\u00f7\5\26\f\2\u00f7\u0103\3\2\2\2\u00f8\u00f9\5\26\f\2\u00f9"+
		"\u00fa\7&\2\2\u00fa\u00fb\5\26\f\2\u00fb\u0103\3\2\2\2\u00fc\u00fd\7\3"+
		"\2\2\u00fd\u00fe\5\30\r\2\u00fe\u00ff\7\4\2\2\u00ff\u0103\3\2\2\2\u0100"+
		"\u0101\7\32\2\2\u0101\u0103\5\30\r\3\u0102\u00ea\3\2\2\2\u0102\u00ec\3"+
		"\2\2\2\u0102\u00f0\3\2\2\2\u0102\u00f4\3\2\2\2\u0102\u00f8\3\2\2\2\u0102"+
		"\u00fc\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u010c\3\2\2\2\u0104\u0105\f\5"+
		"\2\2\u0105\u0106\7\30\2\2\u0106\u010b\5\30\r\6\u0107\u0108\f\4\2\2\u0108"+
		"\u0109\7\31\2\2\u0109\u010b\5\30\r\5\u010a\u0104\3\2\2\2\u010a\u0107\3"+
		"\2\2\2\u010b\u010e\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2\2\2\u010d"+
		"\31\3\2\2\2\u010e\u010c\3\2\2\2\25/\62:EGUc\u0094\u00a0\u00a8\u00aa\u00b6"+
		"\u00c7\u00d5\u00e5\u00e7\u0102\u010a\u010c";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}