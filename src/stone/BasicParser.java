package stone;

import static stone.Parser.rule;

import java.util.HashSet;

import stone.Parser.Operators;
import stone.ast.*;

/**
 * Created by master on 2017/7/16.
 */
public class BasicParser {
    HashSet<String> reserved = new HashSet<String>();
    Operators operators = new Operators();

    /*
    元符号：
    { pat } => 模式 pat 至少重复 0 次
    [ pat ] => 与重复出现 0 次或者 1 次的模式 pat 匹配
    pat1 | pat2 => 与 pat1 或者 pat2 匹配
    () => 将括号内视为一个完整的模式

    语法定义：
    primary : "(" expr ")" | NUMBER | IDENTIFIER | STRING
    factor  : "-" primary | primary
    expr    : factor { OP factor }
    block   : "{" [ statement ] {(";" | EOL [ statement ] } "}"
    simple  : expr
    statement : "if" expr block [ "else" block ]
                | "while" expr block
                | simple
    program : [ statement ] ( ";" | EOL )
     */

    Parser expr0 = rule();
    Parser primary = rule(PrimaryExpr.class)
            .or(rule().sep("(").ast(expr0).sep(")"),
                    rule().number(NumberLiteral.class),
                    rule().identifier(Name.class, reserved),
                    rule().string(StringLiteral.class));

    Parser factor = rule().or(rule(NegativeExpr.class).sep("-").ast(primary),
            primary);

    Parser expr = expr0.expression(BinaryExpr.class, factor, operators);

    Parser statement0 = rule();
    Parser block = rule(BlockStmnt.class)
            .sep("{").option(statement0)
            .repeat(rule().sep(";", Token.EOL).option(statement0))
            .sep("}");
    Parser simple = rule(PrimaryExpr.class).ast(expr);
    Parser statement = statement0.or(
            rule(IfSmnt.class).sep("if").ast(expr).ast(block)
                    .option(rule().sep("else").ast(block)),
            rule(WhileStmnt.class).sep("while").ast(expr).ast(block),
            simple
    );
    Parser program = rule().or(statement, rule(NullStmnt.class)).sep(";", Token.EOL);

    public BasicParser() {
        reserved.add(";");
        reserved.add("}");
        reserved.add(Token.EOL);

        operators.add("=", 1, Operators.RIGHT);
        operators.add("==", 2, Operators.LEFT);
        operators.add(">", 2, Operators.LEFT);
        operators.add("<", 2, Operators.LEFT);
        operators.add("+", 3, Operators.LEFT);
        operators.add("-", 3, Operators.LEFT);
        operators.add("*", 4, Operators.LEFT);
        operators.add("/", 4, Operators.LEFT);
        operators.add("%", 4, Operators.LEFT);
    }

    public ASTree parse(Lexer lexer) throws ParseException {
        return program.parse(lexer);
    }
}
