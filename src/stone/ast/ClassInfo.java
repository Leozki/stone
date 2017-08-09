package stone.ast;

import stone.env.Environment;
import stone.exception.StoneException;

public class ClassInfo {
    protected ClassStmnt definition;
    protected Environment environment;
    protected ClassInfo superClass;
    public ClassInfo(ClassStmnt cs,Environment env) {
        definition = cs;
        environment = env;
        Object obj = env.get(cs.superClass());
        if (obj == null)
            superClass = null;
        else if(obj instanceof  ClassInfo)
            superClass = (ClassInfo) obj;
        else
            throw new StoneException("unknow super class:"+cs.superClass(),cs);
    }
    public String name() {
        return definition.name();
    }

    public ClassInfo getSuperClass() {
        return superClass;
    }
    public ClassBody body(){
        return definition.body();
    }

    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public String toString() {
        return "<Class" + name() +'>';
    }
}
