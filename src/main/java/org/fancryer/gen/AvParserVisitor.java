// Generated from A:/Av/src/main/resources/0.2/AvParser.g4 by ANTLR 4.13.1
package org.fancryer.gen;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link AvParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface AvParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link AvParser#chunk}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitChunk(AvParser.ChunkContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#borrow}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorrow(AvParser.BorrowContext ctx);
	/**
	 * Visit a parse tree produced by the {@code atom_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom_exp(AvParser.Atom_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code list_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_exp(AvParser.List_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code bytes_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytes_exp(AvParser.Bytes_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ref_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRef_exp(AvParser.Ref_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code borrow_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBorrow_exp(AvParser.Borrow_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code var_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_exp(AvParser.Var_expContext ctx);
	/**
	 * Visit a parse tree produced by the {@code map_exp}
	 * labeled alternative in {@link AvParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap_exp(AvParser.Map_expContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#atom}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAtom(AvParser.AtomContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#list_entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_entry(AvParser.List_entryContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList(AvParser.ListContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#list_entries}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitList_entries(AvParser.List_entriesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#map_entries}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap_entries(AvParser.Map_entriesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#map}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap(AvParser.MapContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#map_entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMap_entry(AvParser.Map_entryContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#plain_map_entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlain_map_entry(AvParser.Plain_map_entryContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#decl_entry}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDecl_entry(AvParser.Decl_entryContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#bytes}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBytes(AvParser.BytesContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#int}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInt(AvParser.IntContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(AvParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by {@link AvParser#string_content}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString_content(AvParser.String_contentContext ctx);
}