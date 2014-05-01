## A simplified velocity implementation for code generation.

## 目标：
以velocity 1.7为基础, 裁剪出适合用作代码生成的模板引擎

## 裁剪：
* 没有event机制
* 没有macro
* 没有stop
* 没有evaluate
* 没有define
* 没有break

## 改动：
* requires jdk1.5+
* 默认情况下，日志输出到标准输出/标准错误流而非文件
* 默认采用classpath模板加载器而非文件系统模板加载器
* default I/O encoding changed to UTF-8(from iso-8859-1)
* 对于#set指令，默认允许设置null值
* 默认打开resource cache
* 去掉了parser pool
* #parse和#include标签支持相对路径
* 新增$ParseUtil.recParsing("xxx.vm").addParam("key", val)模板调用形式；相当于带调用栈的#parse标签，能用在当你需要每层递归的context都相互隔离的递归#parse的时候；也能支持相对路径
* 可放置min-velocity.properties文件(可选)在classpath根路径下，用于覆盖velocity的各种默认属性
* min-velocity.properties可使用default.static.util.mappings属性配置默认的静态工具类，这些工具类将被默认放入模板context中，刻配置多个，如：default.static.util.mappings = ClassUtils:org.apache.commons.lang.ClassUtils

## For English speakers, see below:
* No event mechanism
* No macro
* No '#stop'
* No '#evaluate'
* No '#define'
* No '#break'
* requires jdk1.5+
* By default logs to stdin/stderr rather than velocity.log
* defaults to use classapth resource loader
* I/O encoding defaults to UTF-8
* #set directive defaults to allow null value
* resource cache on by default
* parser pool removed
* relative path support for #parse and #include directives
* $ParseUtil.recParsing("xxx.vm").addParam("key", val) template parsing util added. You can see it as a '#parse' directive with invocation stack frame,  
which could easily do recursive parsing with isolated context in each round of recursion. This also supports relative path.
* could place an optional 'min-velocity.properties' file in classpath root to configure velocity runtime.
* min-velocity could contain zero or more 'default.static.util.mappings' property configs to expose static utility classes in template contexts, for example: default.static.util.mappings = ClassUtils:org.apache.commons.lang.ClassUtils, 
with this config you can reference to org.apache.commons.lang.ClassUtils class with key 'ClassUtils' anywhere.