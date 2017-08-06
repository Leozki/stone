package stone.ast;

import stone.env.Environment;
import stone.env.NestedEnv;
import stone.exception.StoneException;

import java.util.List;

public class Dot extends Postfix {
    public Dot(List<ASTree> list) {
        super(list);
    }



    public String name() {

        return ((ASTLeaf)child(0)).token().getText();
    }

    @Override
    public String toString() {
        return "Dot{."+name()+"}";
    }


    @Override
    public Object eval(Environment env, Object value) {
        String member = name();
        if (value instanceof ClassInfo) {
            if ("new".equals(member)) {
                ClassInfo ci = (ClassInfo)value;
                NestedEnv e = new NestedEnv(ci.getEnvironment());
                StoneObject so = new StoneObject(e);
                e.putNew("this",so);
                initObject(ci,e);
                return so;
            }
        }else if (value instanceof StoneObject) {
            try {
                return ((StoneObject)value).read(member);
            }catch (StoneObject.AccessException e) {

            }
            throw new StoneException("bad member access: " + member,this);
        }
        return null;
    }

    protected void initObject(ClassInfo ci,Environment env) {
        if (ci.getSuperClass() != null)
            initObject(ci.getSuperClass(),env);
        ci.body().eval(env);
    }

}
