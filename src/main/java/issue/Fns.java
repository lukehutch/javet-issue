package issue;

import com.caoccao.javet.annotations.V8Function;

public class Fns {
    private static class ErrorCls {
        private static int throwError() {
            throw new RuntimeException("Reached throwError()");
        }

        public static final int X = throwError();
    }
    
    @V8Function
    public static int throwError() {
        return ErrorCls.X;
    }
}
