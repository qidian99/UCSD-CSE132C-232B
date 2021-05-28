// Generated from /Users/zouhongyu/Desktop/CSE232B_XQueryEvaluator/XPath.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XPathLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, DOC=20, IS=21, NAME=22, STR=23, WS=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "DOC", "IS", "NAME", "STR", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'(\"'", "'\")'", "'/'", "'//'", "'*'", "'.'", "'..'", "'text()'", 
			"'@'", "'('", "')'", "','", "'['", "']'", "'='", "'eq'", "'and'", "'or'", 
			"'not'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, "DOC", "IS", "NAME", 
			"STR", "WS"
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


	public XPathLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XPath.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\32\u008f\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b"+
		"\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r"+
		"\3\16\3\16\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25t\n\25\3\26\3\26\3\26\3\26\5\26z\n\26\3\27\6\27}\n"+
		"\27\r\27\16\27~\3\30\3\30\6\30\u0083\n\30\r\30\16\30\u0084\3\30\3\30\3"+
		"\31\6\31\u008a\n\31\r\31\16\31\u008b\3\31\3\31\2\2\32\3\3\5\4\7\5\t\6"+
		"\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24"+
		"\'\25)\26+\27-\30/\31\61\32\3\2\5\7\2\60\60\62;C\\aac|\n\2\"#)).\60\62"+
		"<AAC\\aac|\5\2\13\f\17\17\"\"\2\u0093\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2"+
		"\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2"+
		"\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3"+
		"\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3"+
		"\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\3\63\3\2\2\2\5\66"+
		"\3\2\2\2\79\3\2\2\2\t;\3\2\2\2\13>\3\2\2\2\r@\3\2\2\2\17B\3\2\2\2\21E"+
		"\3\2\2\2\23L\3\2\2\2\25N\3\2\2\2\27P\3\2\2\2\31R\3\2\2\2\33T\3\2\2\2\35"+
		"V\3\2\2\2\37X\3\2\2\2!Z\3\2\2\2#]\3\2\2\2%a\3\2\2\2\'d\3\2\2\2)s\3\2\2"+
		"\2+y\3\2\2\2-|\3\2\2\2/\u0080\3\2\2\2\61\u0089\3\2\2\2\63\64\7*\2\2\64"+
		"\65\7$\2\2\65\4\3\2\2\2\66\67\7$\2\2\678\7+\2\28\6\3\2\2\29:\7\61\2\2"+
		":\b\3\2\2\2;<\7\61\2\2<=\7\61\2\2=\n\3\2\2\2>?\7,\2\2?\f\3\2\2\2@A\7\60"+
		"\2\2A\16\3\2\2\2BC\7\60\2\2CD\7\60\2\2D\20\3\2\2\2EF\7v\2\2FG\7g\2\2G"+
		"H\7z\2\2HI\7v\2\2IJ\7*\2\2JK\7+\2\2K\22\3\2\2\2LM\7B\2\2M\24\3\2\2\2N"+
		"O\7*\2\2O\26\3\2\2\2PQ\7+\2\2Q\30\3\2\2\2RS\7.\2\2S\32\3\2\2\2TU\7]\2"+
		"\2U\34\3\2\2\2VW\7_\2\2W\36\3\2\2\2XY\7?\2\2Y \3\2\2\2Z[\7g\2\2[\\\7s"+
		"\2\2\\\"\3\2\2\2]^\7c\2\2^_\7p\2\2_`\7f\2\2`$\3\2\2\2ab\7q\2\2bc\7t\2"+
		"\2c&\3\2\2\2de\7p\2\2ef\7q\2\2fg\7v\2\2g(\3\2\2\2hi\7f\2\2ij\7q\2\2jk"+
		"\7e\2\2kl\7w\2\2lm\7o\2\2mn\7g\2\2no\7p\2\2ot\7v\2\2pq\7f\2\2qr\7q\2\2"+
		"rt\7e\2\2sh\3\2\2\2sp\3\2\2\2t*\3\2\2\2uv\7?\2\2vz\7?\2\2wx\7k\2\2xz\7"+
		"u\2\2yu\3\2\2\2yw\3\2\2\2z,\3\2\2\2{}\t\2\2\2|{\3\2\2\2}~\3\2\2\2~|\3"+
		"\2\2\2~\177\3\2\2\2\177.\3\2\2\2\u0080\u0082\7$\2\2\u0081\u0083\t\3\2"+
		"\2\u0082\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7$\2\2\u0087\60\3\2\2\2\u0088"+
		"\u008a\t\4\2\2\u0089\u0088\3\2\2\2\u008a\u008b\3\2\2\2\u008b\u0089\3\2"+
		"\2\2\u008b\u008c\3\2\2\2\u008c\u008d\3\2\2\2\u008d\u008e\b\31\2\2\u008e"+
		"\62\3\2\2\2\b\2sy~\u0084\u008b\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}