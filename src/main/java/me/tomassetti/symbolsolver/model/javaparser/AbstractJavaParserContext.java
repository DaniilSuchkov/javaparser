package me.tomassetti.symbolsolver.model.javaparser;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.Statement;
import me.tomassetti.symbolsolver.model.*;

import java.util.List;

/**
 * Created by federico on 28/07/15.
 */
public abstract class AbstractJavaParserContext<N extends Node> implements Context {

    protected N wrappedNode;

    public AbstractJavaParserContext(N wrappedNode) {
        this.wrappedNode = wrappedNode;
    }

    protected final SymbolReference solveWith(SymbolDeclarator symbolDeclarator, String name){
        for (SymbolDeclaration decl : symbolDeclarator.getSymbolDeclarations()){
            if (decl.name().equals(name)){
                return SymbolReference.solved(decl);
            }
        }
        return SymbolReference.unsolved();
    }

    @Override
    public final Context getParent() {
        return JavaParserFactory.getContext(wrappedNode.getParentNode());
    }

    @Override
    public final boolean isRoot() {
        return (wrappedNode instanceof CompilationUnit);
    }
}
