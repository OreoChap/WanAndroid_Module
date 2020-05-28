
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