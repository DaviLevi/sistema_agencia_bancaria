package back_end.contexto;

import java.util.HashMap;
import java.util.Map;

public class ContextoAplicacao {

    private static Map<String,Object> contexto = new HashMap<>();


    public static void insereModulo(String nomeModulo, Object modulo){
        contexto.put(nomeModulo, modulo);
    }

    public static Object getModulo(String nomeModulo){
        return contexto.get(nomeModulo);
    }

}
