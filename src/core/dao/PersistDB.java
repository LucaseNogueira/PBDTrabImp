package core.dao;

import core.dao.antlr.SQLiteBaseListener;
import core.dao.antlr.SQLiteLexer;
import core.dao.antlr.SQLiteParser;
import core.shell.controller.ShellControllerInterface;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CodePointCharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

//import br.udesc.udescdb.SQLiteBaseListener;
//import br.udesc.udescdb.SQLiteLexer;
//import br.udesc.udescdb.SQLiteParser;

public class PersistDB {
    
    private ShellControllerInterface shellController;

    public PersistDB(ShellControllerInterface sc) {
        this.shellController = sc;
    }
    
    public void executeDB(String str){
        CodePointCharStream inputStream = CharStreams.fromString(str);
        SQLiteLexer lexer = new SQLiteLexer(inputStream);
        CommonTokenStream cts = new CommonTokenStream(lexer);
        SQLiteParser parser = new SQLiteParser(cts);
        parser.setBuildParseTree(true);
        ParseTree tree = parser.parse();

        ParseTreeWalker p = new ParseTreeWalker();
        p.walk(new SQLiteBaseListener(shellController), tree);
    }
}
