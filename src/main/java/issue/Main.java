package issue;

import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.interception.logging.JavetStandardConsoleInterceptor;
import com.caoccao.javet.interop.V8Host;
import com.caoccao.javet.interop.options.V8RuntimeOptions;

public class Main {
    public static void main(String[] args) throws Exception {
        var fns = new Fns();
        var v8Flags = V8RuntimeOptions.V8_FLAGS;
        v8Flags.setMaxHeapSize(768);
        try (var v8Runtime = V8Host.getV8Instance().createV8Runtime()) {
            var globalObject = v8Runtime.getGlobalObject();
            globalObject.bind(fns);
            var javetStandardConsoleInterceptor = new JavetStandardConsoleInterceptor(v8Runtime);
            javetStandardConsoleInterceptor.register(globalObject);

            try {
                v8Runtime.getExecutor("""
                        try {
                            throwError();
                        } catch (e) {
                            console.error(e.stack);
                        }
                        """).executeString();
            } catch (JavetException e) {
                e.printStackTrace();
            } finally {
                javetStandardConsoleInterceptor.unregister(globalObject);
                globalObject.unbind(fns);
                v8Runtime.lowMemoryNotification();
                globalObject.close();
            }
        }
    }
}
