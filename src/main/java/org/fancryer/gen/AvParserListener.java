// Generated from A:/Av/src/main/resources/0.2/AvParser.g4 by ANTLR 4.13.1
package org.fancryer.gen;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link AvParser}.
 */
public interface AvParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link AvParser#chunk}.
	 * @param ctx the parse tree
	 */
	void enterChunk(AvParser.ChunkContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#chunk}.
	 * @param ctx the parse tree
	 */
	void exitChunk(AvParser.ChunkContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#borrow}.
	 * @param ctx the parse tree
	 */
	void enterBorrow(AvParser.BorrowContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#borrow}.
	 * @param ctx the parse tree
	 */
	void exitBorrow(AvParser.BorrowContext ctx);
	/**
	 * Enter a parse tree produced by the {@code atom_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterAtom_exp(AvParser.Atom_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code atom_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitAtom_exp(AvParser.Atom_expContext ctx);
	/**
	 * Enter a parse tree produced by the {@code list_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterList_exp(AvParser.List_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code list_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitList_exp(AvParser.List_expContext ctx);
	/**
	 * Enter a parse tree produced by the {@code bytes_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBytes_exp(AvParser.Bytes_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code bytes_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBytes_exp(AvParser.Bytes_expContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ref_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterRef_exp(AvParser.Ref_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ref_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitRef_exp(AvParser.Ref_expContext ctx);
	/**
	 * Enter a parse tree produced by the {@code borrow_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterBorrow_exp(AvParser.Borrow_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code borrow_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitBorrow_exp(AvParser.Borrow_expContext ctx);
	/**
	 * Enter a parse tree produced by the {@code var_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterVar_exp(AvParser.Var_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code var_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitVar_exp(AvParser.Var_expContext ctx);
	/**
	 * Enter a parse tree produced by the {@code map_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterMap_exp(AvParser.Map_expContext ctx);
	/**
	 * Exit a parse tree produced by the {@code map_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitMap_exp(AvParser.Map_expContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#atom}.
	 * @param ctx the parse tree
	 */
	void enterAtom(AvParser.AtomContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#atom}.
	 * @param ctx the parse tree
	 */
	void exitAtom(AvParser.AtomContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#list_entry}.
	 * @param ctx the parse tree
	 */
	void enterList_entry(AvParser.List_entryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#list_entry}.
	 * @param ctx the parse tree
	 */
	void exitList_entry(AvParser.List_entryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#list}.
	 * @param ctx the parse tree
	 */
	void enterList(AvParser.ListContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#list}.
	 * @param ctx the parse tree
	 */
	void exitList(AvParser.ListContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#list_entries}.
	 * @param ctx the parse tree
	 */
	void enterList_entries(AvParser.List_entriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#list_entries}.
	 * @param ctx the parse tree
	 */
	void exitList_entries(AvParser.List_entriesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#map_entries}.
	 * @param ctx the parse tree
	 */
	void enterMap_entries(AvParser.Map_entriesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#map_entries}.
	 * @param ctx the parse tree
	 */
	void exitMap_entries(AvParser.Map_entriesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#map}.
	 * @param ctx the parse tree
	 */
	void enterMap(AvParser.MapContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#map}.
	 * @param ctx the parse tree
	 */
	void exitMap(AvParser.MapContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#map_entry}.
	 * @param ctx the parse tree
	 */
	void enterMap_entry(AvParser.Map_entryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#map_entry}.
	 * @param ctx the parse tree
	 */
	void exitMap_entry(AvParser.Map_entryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#plain_map_entry}.
	 * @param ctx the parse tree
	 */
	void enterPlain_map_entry(AvParser.Plain_map_entryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#plain_map_entry}.
	 * @param ctx the parse tree
	 */
	void exitPlain_map_entry(AvParser.Plain_map_entryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#decl_entry}.
	 * @param ctx the parse tree
	 */
	void enterDecl_entry(AvParser.Decl_entryContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#decl_entry}.
	 * @param ctx the parse tree
	 */
	void exitDecl_entry(AvParser.Decl_entryContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#bytes}.
	 * @param ctx the parse tree
	 */
	void enterBytes(AvParser.BytesContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#bytes}.
	 * @param ctx the parse tree
	 */
	void exitBytes(AvParser.BytesContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#int}.
	 * @param ctx the parse tree
	 */
	void enterInt(AvParser.IntContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#int}.
	 * @param ctx the parse tree
	 */
	void exitInt(AvParser.IntContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#string}.
	 * @param ctx the parse tree
	 */
	void enterString(AvParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#string}.
	 * @param ctx the parse tree
	 */
	void exitString(AvParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by {@link AvParser#string_content}.
	 * @param ctx the parse tree
	 */
	void enterString_content(AvParser.String_contentContext ctx);
	/**
	 * Exit a parse tree produced by {@link AvParser#string_content}.
	 * @param ctx the parse tree
	 */
	void exitString_content(AvParser.String_contentContext ctx);
}