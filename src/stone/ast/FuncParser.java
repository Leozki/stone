package stone.ast;

import stone.ast.BasicParser;
import stone.parser.Parser;

import static stone.parser.Parser.rule;

/**
 * Created by master on 2017/7/18.
 */
public class FuncParser extends BasicParser {
    /*
   新增语法,函数调用
   param : IDENTIFIER
   params : param { "," param }
   param_list : "(" [ params ] ")"
   def : "def" IDENTIFIER param_list block
   args : expr { "," expr }
   postfix : "(" [ args ] ")"
   primary : ( "(" expr ")" | NUMBER | IDENTIFIER | STRING ) { postfix }
   simple : expr [ args ]
   program : [ def | statement ] ( ";" | EOL )
     */
    Parser param = rule().identifier(reserved);
    Parser params = rule(ParameterList.class).ast(param).repeat(
            rule().sep(",").ast(param));
    Parser paramList = rule().sep("(").maybe(params).sep(")");

    Parser def = rule(Defstmnt.class).sep("def").identifier(reserved)
            .ast(paramList).ast(block);
    Parser args = rule(Arguments.class).ast(expr).repeat(
            rule().sep(",").ast(expr));
    Parser postfix = rule().sep("(").maybe(args).sep(")");

    public FuncParser() {
        reserved.add(")");
        primary.repeat(postfix);
        simple.option(args);
        program.insertChoice(def);
    }

}
