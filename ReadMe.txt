模块目录说明：
action：网页访问入口
dao：dao层
handler：对外接口
impl：业务逻辑
Service：Service层（调用dao层）

RCK：中文意思是清算

2015-03-26：拆分目录结构，为以后maven模块化做准备。
beans：数据库模块
comon：公共模块
flow：流程模块(日切)
reconcile：集群模块(对账)