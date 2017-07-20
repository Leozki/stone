package stone.ast;

import stone.parser.Parser;

/**
 * Created by master on 2017/7/20.
 */
public class ClosureParser extends FuncParser {
    public ClosureParser() {
        primary.insertChoice(Parser.rule(Fun.class).sep("fun").ast(paramList).ast(block));
    }
}
