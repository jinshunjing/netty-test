
## 原理
- 设计模式：Intercepting Filter
- ChannelHandlerContext - AbstractChannelHandlerContext - DefaultChannelHandlerContext: 双向链表，通过fireXXX方法走到next
- ChannelPipeline - DefaultChannelPipeline：head/tail
- ChannelHandler
- EventExecutor：EventLoop



