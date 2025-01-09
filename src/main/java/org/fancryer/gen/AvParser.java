// Generated from A:/Av/src/main/resources/0.2/AvParser.g4 by ANTLR 4.13.1
package org.fancryer.gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all","warnings","unchecked","unused","cast","CheckReturnValue"})
public class AvParser extends Parser
{
	static{RuntimeMetaData.checkVersion("4.13.1",RuntimeMetaData.VERSION);}

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache=
			new PredictionContextCache();
	public static final int
			Int=1, Float=2, Dot=3, True=4, False=5, Nil=6, HexInt=7, Overlay=8, Unite=9,
			Default=10, Intersect=11, Differ=12, Either=13, Push=14, Id=15, Brace_Paren_Left=16,
			Brace_Paren_Right=17, Brace_Square_Left=18, Brace_Square_Right=19, Brace_Curly_Left=20,
			Brace_Curly_Right=21, Comma=22, Colon=23, Semicolon=24, BindTemporary=25,
			BindPersistent=26, Quote=27, EscapeSequence=28, WS=29, Comment=30, DQUOTE=31,
			TEXT=32, BACKSLASH_PAREN=33;
	public static final int
			RULE_chunk=0, RULE_borrow=1, RULE_exp=2, RULE_atom=3, RULE_list_entry=4,
			RULE_list=5, RULE_list_entries=6, RULE_map_entries=7, RULE_map=8,
			RULE_map_entry=9, RULE_plain_map_entry=10, RULE_decl_entry=11, RULE_bytes=12,
			RULE_int=13, RULE_string=14, RULE_string_content=15;

	private static String[] makeRuleNames()
	{
		return new String[]{
				"chunk","borrow","exp","atom","list_entry","list","list_entries",
				"map_entries","map","map_entry","plain_map_entry","decl_entry","bytes",
				"int","string","string_content"
		};
	}

	public static final String[] ruleNames=makeRuleNames();

	private static String[] makeLiteralNames()
	{
		return new String[]{
				null,null,null,"'.'","'true'","'false'",null,null,null,null,
				null,null,null,null,null,null,"'('","')'","'['","']'","'{'",
				"'}'","','","':'","';'","':='","'::='","'''",null,null,null,
				null,null,"'\\('"
		};
	}

	private static final String[] _LITERAL_NAMES=makeLiteralNames();

	private static String[] makeSymbolicNames()
	{
		return new String[]{
				null,"Int","Float","Dot","True","False","Nil","HexInt","Overlay",
				"Unite","Default","Intersect","Differ","Either","Push","Id","Brace_Paren_Left",
				"Brace_Paren_Right","Brace_Square_Left","Brace_Square_Right","Brace_Curly_Left",
				"Brace_Curly_Right","Comma","Colon","Semicolon","BindTemporary",
				"BindPersistent","Quote","EscapeSequence","WS","Comment","DQUOTE",
				"TEXT","BACKSLASH_PAREN"
		};
	}

	private static final String[] _SYMBOLIC_NAMES=makeSymbolicNames();
	public static final Vocabulary VOCABULARY=new VocabularyImpl(_LITERAL_NAMES,_SYMBOLIC_NAMES);

	/**
	 @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static
	{
		tokenNames=new String[_SYMBOLIC_NAMES.length];
		for(int i=0;i<tokenNames.length;i++)
		{
			tokenNames[i]=VOCABULARY.getLiteralName(i);
			if(tokenNames[i]==null)
			{
				tokenNames[i]=VOCABULARY.getSymbolicName(i);
			}

			if(tokenNames[i]==null)
			{
				tokenNames[i]="<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames()
	{
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary()
	{
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName(){return "AvParser.g4";}

	@Override
	public String[] getRuleNames(){return ruleNames;}

	@Override
	public String getSerializedATN(){return _serializedATN;}

	@Override
	public ATN getATN(){return _ATN;}

	public AvParser(TokenStream input)
	{
		super(input);
		_interp=new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ChunkContext extends ParserRuleContext
	{
		public TerminalNode EOF(){return getToken(AvParser.EOF,0);}

		public MapContext map()
		{
			return getRuleContext(MapContext.class,0);
		}

		public Map_entriesContext map_entries()
		{
			return getRuleContext(Map_entriesContext.class,0);
		}

		public ChunkContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_chunk;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterChunk(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitChunk(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitChunk(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ChunkContext chunk() throws RecognitionException
	{
		ChunkContext _localctx=new ChunkContext(_ctx,getState());
		enterRule(_localctx,0,RULE_chunk);
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(34);
				_errHandler.sync(this);
				switch(_input.LA(1))
				{
					case Brace_Curly_Left:
					{
						setState(32);
						map();
					}
					break;
					case Int:
					case Float:
					case True:
					case False:
					case Nil:
					case HexInt:
					case Id:
					case DQUOTE:
					{
						setState(33);
						map_entries();
					}
					break;
					case EOF:
						break;
					default:
						break;
				}
				setState(36);
				match(EOF);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BorrowContext extends ParserRuleContext
	{
		public TerminalNode Overlay(){return getToken(AvParser.Overlay,0);}

		public TerminalNode Unite(){return getToken(AvParser.Unite,0);}

		public TerminalNode Default(){return getToken(AvParser.Default,0);}

		public TerminalNode Intersect(){return getToken(AvParser.Intersect,0);}

		public TerminalNode Differ(){return getToken(AvParser.Differ,0);}

		public TerminalNode Either(){return getToken(AvParser.Either,0);}

		public TerminalNode Push(){return getToken(AvParser.Push,0);}

		public BorrowContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_borrow;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterBorrow(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitBorrow(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitBorrow(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BorrowContext borrow() throws RecognitionException
	{
		BorrowContext _localctx=new BorrowContext(_ctx,getState());
		enterRule(_localctx,2,RULE_borrow);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(38);
				_la=_input.LA(1);
				if(!((((_la)&~0x3f)==0&&((1L<<_la)&32512L)!=0)))
				{
					_errHandler.recoverInline(this);
				}
				else
				{
					if(_input.LA(1)==Token.EOF) matchedEOF=true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpContext extends ParserRuleContext
	{
		public ExpContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_exp;}

		public ExpContext(){}

		public void copyFrom(ExpContext ctx)
		{
			super.copyFrom(ctx);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Atom_expContext extends ExpContext
	{
		public AtomContext atom()
		{
			return getRuleContext(AtomContext.class,0);
		}

		public Atom_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterAtom_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitAtom_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitAtom_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class List_expContext extends ExpContext
	{
		public ListContext list()
		{
			return getRuleContext(ListContext.class,0);
		}

		public List_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterList_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitList_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitList_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Bytes_expContext extends ExpContext
	{
		public BytesContext bytes()
		{
			return getRuleContext(BytesContext.class,0);
		}

		public Bytes_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterBytes_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitBytes_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitBytes_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Ref_expContext extends ExpContext
	{
		public ExpContext exp()
		{
			return getRuleContext(ExpContext.class,0);
		}

		public TerminalNode Dot(){return getToken(AvParser.Dot,0);}

		public AtomContext atom()
		{
			return getRuleContext(AtomContext.class,0);
		}

		public Ref_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterRef_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitRef_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitRef_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Borrow_expContext extends ExpContext
	{
		public List<ExpContext> exp()
		{
			return getRuleContexts(ExpContext.class);
		}

		public ExpContext exp(int i)
		{
			return getRuleContext(ExpContext.class,i);
		}

		public BorrowContext borrow()
		{
			return getRuleContext(BorrowContext.class,0);
		}

		public Borrow_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterBorrow_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitBorrow_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitBorrow_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Var_expContext extends ExpContext
	{
		public TerminalNode Quote(){return getToken(AvParser.Quote,0);}

		public TerminalNode Id(){return getToken(AvParser.Id,0);}

		public StringContext string()
		{
			return getRuleContext(StringContext.class,0);
		}

		public Var_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterVar_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitVar_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitVar_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Map_expContext extends ExpContext
	{
		public MapContext map()
		{
			return getRuleContext(MapContext.class,0);
		}

		public Map_expContext(ExpContext ctx){copyFrom(ctx);}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterMap_exp(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitMap_exp(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitMap_exp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException
	{
		return exp(0);
	}

	private ExpContext exp(int _p) throws RecognitionException
	{
		ParserRuleContext _parentctx=_ctx;
		int _parentState=getState();
		ExpContext _localctx=new ExpContext(_ctx,_parentState);
		ExpContext _prevctx=_localctx;
		int _startState=4;
		enterRecursionRule(_localctx,4,RULE_exp,_p);
		try
		{
			int _alt;
			enterOuterAlt(_localctx,1);
			{
				setState(50);
				_errHandler.sync(this);
				switch(_input.LA(1))
				{
					case Int:
					case Float:
					case True:
					case False:
					case Nil:
					case HexInt:
					case Id:
					case DQUOTE:
					{
						_localctx=new Atom_expContext(_localctx);
						_ctx=_localctx;
						_prevctx=_localctx;

						setState(41);
						atom();
					}
					break;
					case Brace_Square_Left:
					{
						_localctx=new List_expContext(_localctx);
						_ctx=_localctx;
						_prevctx=_localctx;
						setState(42);
						list();
					}
					break;
					case Brace_Curly_Left:
					{
						_localctx=new Map_expContext(_localctx);
						_ctx=_localctx;
						_prevctx=_localctx;
						setState(43);
						map();
					}
					break;
					case Brace_Paren_Left:
					{
						_localctx=new Bytes_expContext(_localctx);
						_ctx=_localctx;
						_prevctx=_localctx;
						setState(44);
						bytes();
					}
					break;
					case Quote:
					{
						_localctx=new Var_expContext(_localctx);
						_ctx=_localctx;
						_prevctx=_localctx;
						setState(45);
						match(Quote);
						setState(48);
						_errHandler.sync(this);
						switch(_input.LA(1))
						{
							case Id:
							{
								setState(46);
								match(Id);
							}
							break;
							case DQUOTE:
							{
								setState(47);
								string();
							}
							break;
							default:
								throw new NoViableAltException(this);
						}
					}
					break;
					default:
						throw new NoViableAltException(this);
				}
				_ctx.stop=_input.LT(-1);
				setState(61);
				_errHandler.sync(this);
				_alt=getInterpreter().adaptivePredict(_input,4,_ctx);
				while(_alt!=2&&_alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER)
				{
					if(_alt==1)
					{
						if(_parseListeners!=null) triggerExitRuleEvent();
						_prevctx=_localctx;
						{
							setState(59);
							_errHandler.sync(this);
							switch(getInterpreter().adaptivePredict(_input,3,_ctx))
							{
								case 1:
								{
									_localctx=new Borrow_expContext(new ExpContext(_parentctx,_parentState));
									pushNewRecursionContext(_localctx,_startState,RULE_exp);
									setState(52);
									if(!(precpred(_ctx,1))) throw new FailedPredicateException(this,"precpred(_ctx, 1)");
									setState(53);
									borrow();
									setState(54);
									exp(2);
								}
								break;
								case 2:
								{
									_localctx=new Ref_expContext(new ExpContext(_parentctx,_parentState));
									pushNewRecursionContext(_localctx,_startState,RULE_exp);
									setState(56);
									if(!(precpred(_ctx,2))) throw new FailedPredicateException(this,"precpred(_ctx, 2)");
									setState(57);
									match(Dot);
									setState(58);
									atom();
								}
								break;
							}
						}
					}
					setState(63);
					_errHandler.sync(this);
					_alt=getInterpreter().adaptivePredict(_input,4,_ctx);
				}
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext
	{
		public IntContext int_()
		{
			return getRuleContext(IntContext.class,0);
		}

		public TerminalNode Float(){return getToken(AvParser.Float,0);}

		public StringContext string()
		{
			return getRuleContext(StringContext.class,0);
		}

		public TerminalNode True(){return getToken(AvParser.True,0);}

		public TerminalNode False(){return getToken(AvParser.False,0);}

		public TerminalNode Nil(){return getToken(AvParser.Nil,0);}

		public TerminalNode Id(){return getToken(AvParser.Id,0);}

		public AtomContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_atom;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterAtom(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitAtom(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitAtom(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AtomContext atom() throws RecognitionException
	{
		AtomContext _localctx=new AtomContext(_ctx,getState());
		enterRule(_localctx,6,RULE_atom);
		try
		{
			setState(71);
			_errHandler.sync(this);
			switch(_input.LA(1))
			{
				case Int:
				case HexInt:
					enterOuterAlt(_localctx,1);
				{
					setState(64);
					int_();
				}
				break;
				case Float:
					enterOuterAlt(_localctx,2);
				{
					setState(65);
					match(Float);
				}
				break;
				case DQUOTE:
					enterOuterAlt(_localctx,3);
				{
					setState(66);
					string();
				}
				break;
				case True:
					enterOuterAlt(_localctx,4);
				{
					setState(67);
					match(True);
				}
				break;
				case False:
					enterOuterAlt(_localctx,5);
				{
					setState(68);
					match(False);
				}
				break;
				case Nil:
					enterOuterAlt(_localctx,6);
				{
					setState(69);
					match(Nil);
				}
				break;
				case Id:
					enterOuterAlt(_localctx,7);
				{
					setState(70);
					match(Id);
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class List_entryContext extends ParserRuleContext
	{
		public Decl_entryContext decl_entry()
		{
			return getRuleContext(Decl_entryContext.class,0);
		}

		public ExpContext exp()
		{
			return getRuleContext(ExpContext.class,0);
		}

		public List_entryContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_list_entry;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterList_entry(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitList_entry(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitList_entry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_entryContext list_entry() throws RecognitionException
	{
		List_entryContext _localctx=new List_entryContext(_ctx,getState());
		enterRule(_localctx,8,RULE_list_entry);
		try
		{
			setState(75);
			_errHandler.sync(this);
			switch(getInterpreter().adaptivePredict(_input,6,_ctx))
			{
				case 1:
					enterOuterAlt(_localctx,1);
				{
					setState(73);
					decl_entry();
				}
				break;
				case 2:
					enterOuterAlt(_localctx,2);
				{
					setState(74);
					exp(0);
				}
				break;
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ListContext extends ParserRuleContext
	{
		public TerminalNode Brace_Square_Left(){return getToken(AvParser.Brace_Square_Left,0);}

		public TerminalNode Brace_Square_Right(){return getToken(AvParser.Brace_Square_Right,0);}

		public List_entriesContext list_entries()
		{
			return getRuleContext(List_entriesContext.class,0);
		}

		public ListContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_list;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterList(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitList(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ListContext list() throws RecognitionException
	{
		ListContext _localctx=new ListContext(_ctx,getState());
		enterRule(_localctx,10,RULE_list);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(77);
				match(Brace_Square_Left);
				setState(79);
				_errHandler.sync(this);
				_la=_input.LA(1);
				if((((_la)&~0x3f)==0&&((1L<<_la)&2283110646L)!=0))
				{
					{
						setState(78);
						list_entries();
					}
				}

				setState(81);
				match(Brace_Square_Right);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class List_entriesContext extends ParserRuleContext
	{
		public List<List_entryContext> list_entry()
		{
			return getRuleContexts(List_entryContext.class);
		}

		public List_entryContext list_entry(int i)
		{
			return getRuleContext(List_entryContext.class,i);
		}

		public List<TerminalNode> Comma(){return getTokens(AvParser.Comma);}

		public TerminalNode Comma(int i)
		{
			return getToken(AvParser.Comma,i);
		}

		public List_entriesContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_list_entries;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterList_entries(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitList_entries(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitList_entries(this);
			else return visitor.visitChildren(this);
		}
	}

	public final List_entriesContext list_entries() throws RecognitionException
	{
		List_entriesContext _localctx=new List_entriesContext(_ctx,getState());
		enterRule(_localctx,12,RULE_list_entries);
		int _la;
		try
		{
			int _alt;
			enterOuterAlt(_localctx,1);
			{
				setState(83);
				list_entry();
				setState(90);
				_errHandler.sync(this);
				_alt=getInterpreter().adaptivePredict(_input,9,_ctx);
				while(_alt!=2&&_alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER)
				{
					if(_alt==1)
					{
						{
							{
								setState(85);
								_errHandler.sync(this);
								_la=_input.LA(1);
								if(_la==Comma)
								{
									{
										setState(84);
										match(Comma);
									}
								}

								setState(87);
								list_entry();
							}
						}
					}
					setState(92);
					_errHandler.sync(this);
					_alt=getInterpreter().adaptivePredict(_input,9,_ctx);
				}
				setState(94);
				_errHandler.sync(this);
				_la=_input.LA(1);
				if(_la==Comma)
				{
					{
						setState(93);
						match(Comma);
					}
				}

			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Map_entriesContext extends ParserRuleContext
	{
		public List<Map_entryContext> map_entry()
		{
			return getRuleContexts(Map_entryContext.class);
		}

		public Map_entryContext map_entry(int i)
		{
			return getRuleContext(Map_entryContext.class,i);
		}

		public List<TerminalNode> Comma(){return getTokens(AvParser.Comma);}

		public TerminalNode Comma(int i)
		{
			return getToken(AvParser.Comma,i);
		}

		public Map_entriesContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_map_entries;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterMap_entries(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitMap_entries(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitMap_entries(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Map_entriesContext map_entries() throws RecognitionException
	{
		Map_entriesContext _localctx=new Map_entriesContext(_ctx,getState());
		enterRule(_localctx,14,RULE_map_entries);
		int _la;
		try
		{
			int _alt;
			enterOuterAlt(_localctx,1);
			{
				setState(96);
				map_entry();
				setState(103);
				_errHandler.sync(this);
				_alt=getInterpreter().adaptivePredict(_input,12,_ctx);
				while(_alt!=2&&_alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER)
				{
					if(_alt==1)
					{
						{
							{
								setState(98);
								_errHandler.sync(this);
								_la=_input.LA(1);
								if(_la==Comma)
								{
									{
										setState(97);
										match(Comma);
									}
								}

								setState(100);
								map_entry();
							}
						}
					}
					setState(105);
					_errHandler.sync(this);
					_alt=getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(107);
				_errHandler.sync(this);
				_la=_input.LA(1);
				if(_la==Comma)
				{
					setState(106);
					match(Comma);
				}

			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MapContext extends ParserRuleContext
	{
		public TerminalNode Brace_Curly_Left(){return getToken(AvParser.Brace_Curly_Left,0);}

		public TerminalNode Brace_Curly_Right(){return getToken(AvParser.Brace_Curly_Right,0);}

		public Map_entriesContext map_entries()
		{
			return getRuleContext(Map_entriesContext.class,0);
		}

		public MapContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_map;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterMap(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitMap(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitMap(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MapContext map() throws RecognitionException
	{
		MapContext _localctx=new MapContext(_ctx,getState());
		enterRule(_localctx,16,RULE_map);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(109);
				match(Brace_Curly_Left);
				setState(111);
				_errHandler.sync(this);
				_la=_input.LA(1);
				if((((_la)&~0x3f)==0&&((1L<<_la)&2147516662L)!=0))
				{
					setState(110);
					map_entries();
				}

				setState(113);
				match(Brace_Curly_Right);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Map_entryContext extends ParserRuleContext
	{
		public Plain_map_entryContext plain_map_entry()
		{
			return getRuleContext(Plain_map_entryContext.class,0);
		}

		public Decl_entryContext decl_entry()
		{
			return getRuleContext(Decl_entryContext.class,0);
		}

		public Map_entryContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_map_entry;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterMap_entry(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitMap_entry(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitMap_entry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Map_entryContext map_entry() throws RecognitionException
	{
		Map_entryContext _localctx=new Map_entryContext(_ctx,getState());
		enterRule(_localctx,18,RULE_map_entry);
		try
		{
			setState(117);
			_errHandler.sync(this);
			switch(getInterpreter().adaptivePredict(_input,15,_ctx))
			{
				case 1->
				{
					enterOuterAlt(_localctx,1);
					setState(115);
					plain_map_entry();
				}
				case 2->
				{
					enterOuterAlt(_localctx,2);
					setState(116);
					decl_entry();
				}
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Plain_map_entryContext extends ParserRuleContext
	{
		public AtomContext atom()
		{
			return getRuleContext(AtomContext.class,0);
		}

		public ExpContext exp()
		{
			return getRuleContext(ExpContext.class,0);
		}

		public TerminalNode Colon(){return getToken(AvParser.Colon,0);}

		public Plain_map_entryContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_plain_map_entry;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterPlain_map_entry(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitPlain_map_entry(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitPlain_map_entry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Plain_map_entryContext plain_map_entry() throws RecognitionException
	{
		Plain_map_entryContext _localctx=new Plain_map_entryContext(_ctx,getState());
		enterRule(_localctx,20,RULE_plain_map_entry);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(119);
				atom();
				setState(121);
				_errHandler.sync(this);
				_la=_input.LA(1);
				if(_la==Colon)
				{
					setState(120);
					match(Colon);
				}

				setState(123);
				exp(0);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class Decl_entryContext extends ParserRuleContext
	{
		public ExpContext exp()
		{
			return getRuleContext(ExpContext.class,0);
		}

		public TerminalNode BindTemporary(){return getToken(AvParser.BindTemporary,0);}

		public TerminalNode BindPersistent(){return getToken(AvParser.BindPersistent,0);}

		public TerminalNode Id(){return getToken(AvParser.Id,0);}

		public StringContext string()
		{
			return getRuleContext(StringContext.class,0);
		}

		public Decl_entryContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_decl_entry;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterDecl_entry(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitDecl_entry(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitDecl_entry(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Decl_entryContext decl_entry() throws RecognitionException
	{
		Decl_entryContext _localctx=new Decl_entryContext(_ctx,getState());
		enterRule(_localctx,22,RULE_decl_entry);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(127);
				_errHandler.sync(this);
				switch(_input.LA(1))
				{
					case Id ->
					{
						setState(125);
						match(Id);
					}
					case DQUOTE ->
					{
						setState(126);
						string();
					}
					default -> throw new NoViableAltException(this);
				}
				setState(129);
				_la=_input.LA(1);
				if(!(_la==BindTemporary||_la==BindPersistent))
				{
					_errHandler.recoverInline(this);
				}
				else
				{
					if(_input.LA(1)==Token.EOF) matchedEOF=true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(130);
				exp(0);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BytesContext extends ParserRuleContext
	{
		public TerminalNode Brace_Paren_Left(){return getToken(AvParser.Brace_Paren_Left,0);}

		public TerminalNode Brace_Paren_Right(){return getToken(AvParser.Brace_Paren_Right,0);}

		public List<TerminalNode> HexInt(){return getTokens(AvParser.HexInt);}

		public TerminalNode HexInt(int i)
		{
			return getToken(AvParser.HexInt,i);
		}

		public BytesContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_bytes;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterBytes(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitBytes(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitBytes(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BytesContext bytes() throws RecognitionException
	{
		BytesContext _localctx=new BytesContext(_ctx,getState());
		enterRule(_localctx,24,RULE_bytes);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(132);
				match(Brace_Paren_Left);
				setState(134);
				_errHandler.sync(this);
				_la=_input.LA(1);
				do
				{
					setState(133);
					match(HexInt);
					setState(136);
					_errHandler.sync(this);
					_la=_input.LA(1);
				}
				while(_la==HexInt);
				setState(138);
				match(Brace_Paren_Right);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IntContext extends ParserRuleContext
	{
		public TerminalNode Int(){return getToken(AvParser.Int,0);}

		public TerminalNode HexInt(){return getToken(AvParser.HexInt,0);}

		public IntContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_int;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterInt(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitInt(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitInt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IntContext int_() throws RecognitionException
	{
		IntContext _localctx=new IntContext(_ctx,getState());
		enterRule(_localctx,26,RULE_int);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(140);
				_la=_input.LA(1);
				if(!(_la==Int||_la==HexInt))
				{
					_errHandler.recoverInline(this);
				}
				else
				{
					if(_input.LA(1)==Token.EOF) matchedEOF=true;
					_errHandler.reportMatch(this);
					consume();
				}
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StringContext extends ParserRuleContext
	{
		public List<TerminalNode> DQUOTE(){return getTokens(AvParser.DQUOTE);}

		public TerminalNode DQUOTE(int i)
		{
			return getToken(AvParser.DQUOTE,i);
		}

		public List<String_contentContext> string_content()
		{
			return getRuleContexts(String_contentContext.class);
		}

		public String_contentContext string_content(int i)
		{
			return getRuleContext(String_contentContext.class,i);
		}

		public StringContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_string;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterString(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitString(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException
	{
		StringContext _localctx=new StringContext(_ctx,getState());
		enterRule(_localctx,28,RULE_string);
		int _la;
		try
		{
			enterOuterAlt(_localctx,1);
			{
				setState(142);
				match(DQUOTE);
				setState(146);
				_errHandler.sync(this);
				_la=_input.LA(1);
				while(_la==TEXT||_la==BACKSLASH_PAREN)
				{
					{
						{
							setState(143);
							string_content();
						}
					}
					setState(148);
					_errHandler.sync(this);
					_la=_input.LA(1);
				}
				setState(149);
				match(DQUOTE);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class String_contentContext extends ParserRuleContext
	{
		public TerminalNode BACKSLASH_PAREN(){return getToken(AvParser.BACKSLASH_PAREN,0);}

		public ExpContext exp()
		{
			return getRuleContext(ExpContext.class,0);
		}

		public TerminalNode Brace_Paren_Right(){return getToken(AvParser.Brace_Paren_Right,0);}

		public TerminalNode TEXT(){return getToken(AvParser.TEXT,0);}

		public String_contentContext(ParserRuleContext parent,int invokingState)
		{
			super(parent,invokingState);
		}

		@Override
		public int getRuleIndex(){return RULE_string_content;}

		@Override
		public void enterRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).enterString_content(this);
		}

		@Override
		public void exitRule(ParseTreeListener listener)
		{
			if(listener instanceof AvParserListener) ((AvParserListener)listener).exitString_content(this);
		}

		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor)
		{
			if(visitor instanceof AvParserVisitor) return ((AvParserVisitor<? extends T>)visitor).visitString_content(this);
			else return visitor.visitChildren(this);
		}
	}

	public final String_contentContext string_content() throws RecognitionException
	{
		String_contentContext _localctx=new String_contentContext(_ctx,getState());
		enterRule(_localctx,30,RULE_string_content);
		try
		{
			setState(156);
			_errHandler.sync(this);
			switch(_input.LA(1))
			{
				case BACKSLASH_PAREN:
					enterOuterAlt(_localctx,1);
				{
					setState(151);
					match(BACKSLASH_PAREN);
					setState(152);
					exp(0);
					setState(153);
					match(Brace_Paren_Right);
				}
				break;
				case TEXT:
					enterOuterAlt(_localctx,2);
				{
					setState(155);
					match(TEXT);
				}
				break;
				default:
					throw new NoViableAltException(this);
			}
		}
		catch(RecognitionException re)
		{
			_localctx.exception=re;
			_errHandler.reportError(this,re);
			_errHandler.recover(this,re);
		}
		finally
		{
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx,int ruleIndex,int predIndex)
	{
		switch(ruleIndex)
		{
			case 2:
				return exp_sempred((ExpContext)_localctx,predIndex);
		}
		return true;
	}

	private boolean exp_sempred(ExpContext _localctx,int predIndex)
	{
		switch(predIndex)
		{
			case 0:
				return precpred(_ctx,1);
			case 1:
				return precpred(_ctx,2);
		}
		return true;
	}

	public static final String _serializedATN=
			"\u0004\u0001!\u009f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
			"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
			"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
			"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
			"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
			"\u0001\u0000\u0001\u0000\u0003\u0000#\b\u0000\u0001\u0000\u0001\u0000"+
			"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
			"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u00021\b\u0002"+
			"\u0003\u00023\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
			"\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002<\b\u0002\n\u0002\f\u0002"+
			"?\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
			"\u0001\u0003\u0001\u0003\u0003\u0003H\b\u0003\u0001\u0004\u0001\u0004"+
			"\u0003\u0004L\b\u0004\u0001\u0005\u0001\u0005\u0003\u0005P\b\u0005\u0001"+
			"\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0003\u0006V\b\u0006\u0001"+
			"\u0006\u0005\u0006Y\b\u0006\n\u0006\f\u0006\\\t\u0006\u0001\u0006\u0003"+
			"\u0006_\b\u0006\u0001\u0007\u0001\u0007\u0003\u0007c\b\u0007\u0001\u0007"+
			"\u0005\u0007f\b\u0007\n\u0007\f\u0007i\t\u0007\u0001\u0007\u0003\u0007"+
			"l\b\u0007\u0001\b\u0001\b\u0003\bp\b\b\u0001\b\u0001\b\u0001\t\u0001\t"+
			"\u0003\tv\b\t\u0001\n\u0001\n\u0003\nz\b\n\u0001\n\u0001\n\u0001\u000b"+
			"\u0001\u000b\u0003\u000b\u0080\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b"+
			"\u0001\f\u0001\f\u0004\f\u0087\b\f\u000b\f\f\f\u0088\u0001\f\u0001\f\u0001"+
			"\r\u0001\r\u0001\u000e\u0001\u000e\u0005\u000e\u0091\b\u000e\n\u000e\f"+
			"\u000e\u0094\t\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
			"\u000f\u0001\u000f\u0001\u000f\u0003\u000f\u009d\b\u000f\u0001\u000f\u0000"+
			"\u0001\u0004\u0010\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
			"\u0016\u0018\u001a\u001c\u001e\u0000\u0003\u0001\u0000\b\u000e\u0001\u0000"+
			"\u0019\u001a\u0002\u0000\u0001\u0001\u0007\u0007\u00ac\u0000\"\u0001\u0000"+
			"\u0000\u0000\u0002&\u0001\u0000\u0000\u0000\u00042\u0001\u0000\u0000\u0000"+
			"\u0006G\u0001\u0000\u0000\u0000\bK\u0001\u0000\u0000\u0000\nM\u0001\u0000"+
			"\u0000\u0000\fS\u0001\u0000\u0000\u0000\u000e`\u0001\u0000\u0000\u0000"+
			"\u0010m\u0001\u0000\u0000\u0000\u0012u\u0001\u0000\u0000\u0000\u0014w"+
			"\u0001\u0000\u0000\u0000\u0016\u007f\u0001\u0000\u0000\u0000\u0018\u0084"+
			"\u0001\u0000\u0000\u0000\u001a\u008c\u0001\u0000\u0000\u0000\u001c\u008e"+
			"\u0001\u0000\u0000\u0000\u001e\u009c\u0001\u0000\u0000\u0000 #\u0003\u0010"+
			"\b\u0000!#\u0003\u000e\u0007\u0000\" \u0001\u0000\u0000\u0000\"!\u0001"+
			"\u0000\u0000\u0000\"#\u0001\u0000\u0000\u0000#$\u0001\u0000\u0000\u0000"+
			"$%\u0005\u0000\u0000\u0001%\u0001\u0001\u0000\u0000\u0000&\'\u0007\u0000"+
			"\u0000\u0000\'\u0003\u0001\u0000\u0000\u0000()\u0006\u0002\uffff\uffff"+
			"\u0000)3\u0003\u0006\u0003\u0000*3\u0003\n\u0005\u0000+3\u0003\u0010\b"+
			"\u0000,3\u0003\u0018\f\u0000-0\u0005\u001b\u0000\u0000.1\u0005\u000f\u0000"+
			"\u0000/1\u0003\u001c\u000e\u00000.\u0001\u0000\u0000\u00000/\u0001\u0000"+
			"\u0000\u000013\u0001\u0000\u0000\u00002(\u0001\u0000\u0000\u00002*\u0001"+
			"\u0000\u0000\u00002+\u0001\u0000\u0000\u00002,\u0001\u0000\u0000\u0000"+
			"2-\u0001\u0000\u0000\u00003=\u0001\u0000\u0000\u000045\n\u0001\u0000\u0000"+
			"56\u0003\u0002\u0001\u000067\u0003\u0004\u0002\u00027<\u0001\u0000\u0000"+
			"\u000089\n\u0002\u0000\u00009:\u0005\u0003\u0000\u0000:<\u0003\u0006\u0003"+
			"\u0000;4\u0001\u0000\u0000\u0000;8\u0001\u0000\u0000\u0000<?\u0001\u0000"+
			"\u0000\u0000=;\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>\u0005"+
			"\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000@H\u0003\u001a\r\u0000"+
			"AH\u0005\u0002\u0000\u0000BH\u0003\u001c\u000e\u0000CH\u0005\u0004\u0000"+
			"\u0000DH\u0005\u0005\u0000\u0000EH\u0005\u0006\u0000\u0000FH\u0005\u000f"+
			"\u0000\u0000G@\u0001\u0000\u0000\u0000GA\u0001\u0000\u0000\u0000GB\u0001"+
			"\u0000\u0000\u0000GC\u0001\u0000\u0000\u0000GD\u0001\u0000\u0000\u0000"+
			"GE\u0001\u0000\u0000\u0000GF\u0001\u0000\u0000\u0000H\u0007\u0001\u0000"+
			"\u0000\u0000IL\u0003\u0016\u000b\u0000JL\u0003\u0004\u0002\u0000KI\u0001"+
			"\u0000\u0000\u0000KJ\u0001\u0000\u0000\u0000L\t\u0001\u0000\u0000\u0000"+
			"MO\u0005\u0012\u0000\u0000NP\u0003\f\u0006\u0000ON\u0001\u0000\u0000\u0000"+
			"OP\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000QR\u0005\u0013\u0000"+
			"\u0000R\u000b\u0001\u0000\u0000\u0000SZ\u0003\b\u0004\u0000TV\u0005\u0016"+
			"\u0000\u0000UT\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0001"+
			"\u0000\u0000\u0000WY\u0003\b\u0004\u0000XU\u0001\u0000\u0000\u0000Y\\"+
			"\u0001\u0000\u0000\u0000ZX\u0001\u0000\u0000\u0000Z[\u0001\u0000\u0000"+
			"\u0000[^\u0001\u0000\u0000\u0000\\Z\u0001\u0000\u0000\u0000]_\u0005\u0016"+
			"\u0000\u0000^]\u0001\u0000\u0000\u0000^_\u0001\u0000\u0000\u0000_\r\u0001"+
			"\u0000\u0000\u0000`g\u0003\u0012\t\u0000ac\u0005\u0016\u0000\u0000ba\u0001"+
			"\u0000\u0000\u0000bc\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000\u0000"+
			"df\u0003\u0012\t\u0000eb\u0001\u0000\u0000\u0000fi\u0001\u0000\u0000\u0000"+
			"ge\u0001\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000"+
			"\u0000ig\u0001\u0000\u0000\u0000jl\u0005\u0016\u0000\u0000kj\u0001\u0000"+
			"\u0000\u0000kl\u0001\u0000\u0000\u0000l\u000f\u0001\u0000\u0000\u0000"+
			"mo\u0005\u0014\u0000\u0000np\u0003\u000e\u0007\u0000on\u0001\u0000\u0000"+
			"\u0000op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000qr\u0005\u0015"+
			"\u0000\u0000r\u0011\u0001\u0000\u0000\u0000sv\u0003\u0014\n\u0000tv\u0003"+
			"\u0016\u000b\u0000us\u0001\u0000\u0000\u0000ut\u0001\u0000\u0000\u0000"+
			"v\u0013\u0001\u0000\u0000\u0000wy\u0003\u0006\u0003\u0000xz\u0005\u0017"+
			"\u0000\u0000yx\u0001\u0000\u0000\u0000yz\u0001\u0000\u0000\u0000z{\u0001"+
			"\u0000\u0000\u0000{|\u0003\u0004\u0002\u0000|\u0015\u0001\u0000\u0000"+
			"\u0000}\u0080\u0005\u000f\u0000\u0000~\u0080\u0003\u001c\u000e\u0000\u007f"+
			"}\u0001\u0000\u0000\u0000\u007f~\u0001\u0000\u0000\u0000\u0080\u0081\u0001"+
			"\u0000\u0000\u0000\u0081\u0082\u0007\u0001\u0000\u0000\u0082\u0083\u0003"+
			"\u0004\u0002\u0000\u0083\u0017\u0001\u0000\u0000\u0000\u0084\u0086\u0005"+
			"\u0010\u0000\u0000\u0085\u0087\u0005\u0007\u0000\u0000\u0086\u0085\u0001"+
			"\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088\u0086\u0001"+
			"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u008a\u0001"+
			"\u0000\u0000\u0000\u008a\u008b\u0005\u0011\u0000\u0000\u008b\u0019\u0001"+
			"\u0000\u0000\u0000\u008c\u008d\u0007\u0002\u0000\u0000\u008d\u001b\u0001"+
			"\u0000\u0000\u0000\u008e\u0092\u0005\u001f\u0000\u0000\u008f\u0091\u0003"+
			"\u001e\u000f\u0000\u0090\u008f\u0001\u0000\u0000\u0000\u0091\u0094\u0001"+
			"\u0000\u0000\u0000\u0092\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001"+
			"\u0000\u0000\u0000\u0093\u0095\u0001\u0000\u0000\u0000\u0094\u0092\u0001"+
			"\u0000\u0000\u0000\u0095\u0096\u0005\u001f\u0000\u0000\u0096\u001d\u0001"+
			"\u0000\u0000\u0000\u0097\u0098\u0005!\u0000\u0000\u0098\u0099\u0003\u0004"+
			"\u0002\u0000\u0099\u009a\u0005\u0011\u0000\u0000\u009a\u009d\u0001\u0000"+
			"\u0000\u0000\u009b\u009d\u0005 \u0000\u0000\u009c\u0097\u0001\u0000\u0000"+
			"\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d\u001f\u0001\u0000\u0000"+
			"\u0000\u0015\"02;=GKOUZ^bgkouy\u007f\u0088\u0092\u009c";
	public static final ATN _ATN=
			new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static
	{
		_decisionToDFA=new DFA[_ATN.getNumberOfDecisions()];
		for(int i=0;i<_ATN.getNumberOfDecisions();i++)
		{
			_decisionToDFA[i]=new DFA(_ATN.getDecisionState(i),i);
		}
	}
}