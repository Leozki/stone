package stone.ast;

import stone.Token;
import stone.parser.Parser;

public class ClassParser extends ClosureParser {
    /*
    解析语法例子:
    class Position {
        x = y = 0
        def move(nx, ny) {
            x = nx;y = ny
        }
    }
    class Pos3D extends Position {
        z = 0
        def set (nx, ny, nz) {
            x = nx; y = ny; z = nz
        }
    }
    p = Pos3D.new
    p.move(3,4)
    print p.x
    p.set(5,6,7)
    print p.z
     */
    /*
    BNF:
    member : def | simple
    class_body : "{" [ member ] { (";" | EOL) [ member ]} "}"
    defclass : "class" IDENTIFIER [ "extends" IDENTIFIER ] class_body
    postfix : "." IDENTIFIER | "(" [ args ] ")"
    program: [ defclass | def | statement ] ( ";" | EOL )
     */

    Parser member = Parser.rule().or(def,simple);
    Parser class_body = Parser.rule(ClassBody.class).sep("{").option(member)
            .repeat(Parser.rule().sep(";", Token.EOL).option(member))
            .sep("}");
    Parser defclass = Parser.rule(ClassStmnt.class).sep("class").identifier(reserved)
            .option(Parser.rule().sep("extends").identifier(reserved))
            .ast(class_body);
    public ClassParser() {
        postfix.insertChoice(Parser.rule(Dot.class).sep(".").identifier(reserved));
        program.insertChoice(defclass);
    }
}
