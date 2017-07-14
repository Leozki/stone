package stone;

import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by master on 2017/7/13.
 */
public class Lexer {
    public static String regexPat = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";
            /*
            "\\s*((//.*|" + // \s*((//.*
            "([0-9]+)|" + // ([0-9]+)
            "(\"(\\\\\"|" + // ("(\\"
            "\\\\\\\\|" + // \\\\
            "\\\\n|" + // \\n
            "[^\"])*\")|" + // [^"])*")
            "[A-Z_a-z][A-Z_a-z0-9]*|" +
            "==|" +
            "<=|" +
            ">=|" +
            "&&|" +
            "\\|\\||" +
            "\\p{Punct})?";
            */
    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<>();
    private boolean hasMore;
    private LineNumberReader lineNumberReader;

    public Lexer(Reader reader) {
        hasMore = true;
        lineNumberReader = new LineNumberReader(reader);
    }

    public Token read() throws ParseException {
        if (fillQueue(0)) {
            return queue.remove(0);
        } else {
            return Token.EOF;
        }
    }

    public Token peek(int i) throws ParseException {
        if (fillQueue(i)) {
            return queue.get(i);
        } else {
            return Token.EOF;
        }
    }

    private boolean fillQueue(int i) throws ParseException {
        while (i >= queue.size()) {
            if (hasMore) {
                readLine();
            } else {
                return false;
            }
        }
        return true;
    }

    protected void readLine() throws ParseException {
        String line;
        try {
            line = lineNumberReader.readLine();
        } catch (IOException e) {
            throw new ParseException(e);
        }
        if (line == null) {
            hasMore = false;
            return;
        }
        int lineNo = lineNumberReader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useAnchoringBounds(false).useTransparentBounds(true);
        int pos = 0;
        int endPos = line.length();
        while (pos < endPos) {
            matcher.region(pos, endPos);
            if (matcher.lookingAt()) {
                addToken(lineNo, matcher);
                pos = matcher.end();
            } else {
                throw new ParseException("bad token at line " + lineNo);
            }
        }
        queue.add(new IdToken(lineNo, Token.EOL));
    }

    protected void addToken(int lineno, Matcher matcher) {
        String m = matcher.group(1);
//        System.out.println("-------------");
//        System.out.println(matcher.group(1));
//        System.out.println(matcher.group(2));
//        System.out.println(matcher.group(3));
//        System.out.println(matcher.group(4));
        if (m != null) {
            if (matcher.group(2) == null) { // if not comment
                Token token;
                if (matcher.group(3) != null) {
                    token = new NumToken(lineno, Integer.parseInt(m));
                } else if (matcher.group(4) != null) {
                    token = new StrToken(lineno, toStringLiteral(m));
                } else {
                    token = new IdToken(lineno, m);
                }
                queue.add(token);
            }
        }
    }

    protected String toStringLiteral(String s) {
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for (int i = 1; i < len; i++) {
            char c = s.charAt(i);
            if (c == '\\' && i + 1 < len) {
                int c2 = s.charAt(i);
                if (c2 == '"' || c2 == '\\') {
                    c = s.charAt(++i);
                } else if (c2 == 'n') {
                    ++i;
                    c = '\n';
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    protected static class NumToken extends Token {
        private int value;

        public NumToken(int lineNumber, int value) {
            super(lineNumber);
            this.value = value;
        }

        @Override
        public boolean isNumber() {
            return true;
        }

        @Override
        public int getNumber() throws StoneException {
            return value;
        }

        @Override
        public String toString() {
            return "NumToken{" +
                    "value=" + value +
                    '}';
        }

        @Override
        public String getText() {
            return Integer.toString(value);
        }
    }

    protected static class IdToken extends Token {
        private String text;

        public IdToken(int lineNumber, String text) {
            super(lineNumber);
            this.text = text;
        }

        @Override
        public boolean isIdentifier() {
            return true;
        }

        @Override
        public String getText() {
            return text;
        }

        @Override
        public String toString() {
            return "IdToken{" +
                    "text='" + text + '\'' +
                    '}';
        }
    }

    protected static class StrToken extends Token {
        private String literal;

        public StrToken(int lineNumber, String literal) {
            super(lineNumber);
            this.literal = literal;
        }

        @Override
        public boolean isString() {
            return true;
        }

        @Override
        public String getText() {
            return literal;
        }

        @Override
        public String toString() {
            return "StrToken{" +
                    "literal='" + literal + '\'' +
                    '}';
        }
    }
}
