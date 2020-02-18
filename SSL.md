
## 基础
- 模块：handler
- 包：io.netty.handler.ssl
- 协议：TLS
- 密码：Ciper

##
- keyManagers:
- trustManagers:
- secureRandom:

## SslContext
- 抽象类：SslContext
- JDK实现类：JdkSslContext, JdkSslEngine
- OpenSSL实现类：OpenSslContext

## SslHandler
- closeOutbound(ChannelPromise):ChannelFuture
