## [Javet issue #446](https://github.com/caoccao/Javet/issues/446)

What is output by this code:

```
Error: Uncaught JavaError in function callback
    at <anonymous>:2:5
```

What I would expect to be output:

```
Error: Uncaught JavaError in function callback:
java.lang.ExceptionInInitializerError
        at issue.Fns.throwError(Fns.java:16)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
        at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(Unknown Source)
        at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(Unknown Source)
        at java.base/java.lang.reflect.Method.invoke(Unknown Source)
        at com.caoccao.javet.interop.callback.V8FunctionCallback.receiveCallback(V8FunctionCallback.java:350)
        at com.caoccao.javet.interop.V8Native.scriptExecute(Native Method)
        at com.caoccao.javet.interop.V8Runtime.execute(V8Runtime.java:1089)
        at com.caoccao.javet.interop.executors.V8StringExecutor.execute(V8StringExecutor.java:107)
        at com.caoccao.javet.interop.IV8Executable.execute(IV8Executable.java:44)
        at com.caoccao.javet.interop.IV8Executable.executeString(IV8Executable.java:172)
        at issue.Main.main(Main.java:26)
Caused by: java.lang.RuntimeException: Reached throwError()
        at issue.Fns$ErrorCls.throwError(Fns.java:8)
        at issue.Fns$ErrorCls.<clinit>(Fns.java:11)
        ... 12 more
    at <anonymous>:2:5
```