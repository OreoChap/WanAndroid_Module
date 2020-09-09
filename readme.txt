
app：壳工程（依赖main模块）
main：主模块（依赖其他模块）
lib_baselibrary：资源、基类等模块
module_XXX：对应功能模块

更新日志
5/28
1、增加泛型处理（对应module_user类的网络请求）；
2、修改模块依赖：main模块依赖全部子模块，主页集成在main模块中；
3、提供全局Context；
4、实现cookie持久化（PersistentCookieJar）；
5、优化依赖库；

5/30
1、完成OkHttpClientManager 的Post 请求；

6/1
1、添加上了User收藏页面的数据

6/2
1、处理recyclerView复用问题

6/12
1、尝试使用config.gradle管理依赖

6/18
1、使用aspectjx（hujiang）实现快速按键监听
2、修改包名，依赖

9/9
1、在retrofit中，增加了Observer对错误的处理（try/catch）；
2、除了1，还可以在service中，使用Single，如UserService；
3、增加了数据结构"跳表"；
4、增加了Intent的扩展函数；
5、优化了说明等。