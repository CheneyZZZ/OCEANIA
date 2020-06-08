## CodeNeuron说明文档



为了方便后续迭代，项目使用了SpringBoot和mybatis框架，由于本阶段采用终端输入输出，因此整个项目只有service和dao两层，我们在application的main函数中直接调用service提供的方法，完成对应检查点内容输出

### 目录

1. 文件目录结构
2. 效果演示
3. 检查点功能说明
4. Jenkins流程展示

### 1. 文件目录结构：

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.png)

Dao负责数据库CURD

PO对应数据库实体以及整个项目用到的实体

Service负责具体逻辑实现

VO是对Service层返回数据的统一封装实体

### 2. 效果演示

命令描述：

- connectedComponent 展示设置阈值后的连通域（阈值初始为0）
- searchAll 搜索全路径
- searchShortest 搜索最短路径
- closenessThreshold {amount} 设置阈值，{amount} 处直接输入具体数值

启动Spring Boot项目 自动输出点个数、边个数以及初始连通域个数

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/start.png)

输入 connectedComponent 展示所有初始连通域个数并按连通域内点个数从多到少排序输出

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/connectedComponent1.png)

输入closenessThreshold 0.7 设置连通域

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/threshold.png)

输入 connectedComponent 展示过滤后的连通域总个数并按连通域内点个数从多到少输出

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/connectedComponent2.png)

输入 searchAll 进入全路径搜索，根据输入提示输入起点和终点

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/searchAll.png)

输入 searchShortest 进入最短路径搜索，根据输入提示输入起点和终点

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/searchShortest.png)

### 3. 检查点功能说明

#### 检查点1

- 接口类是com.example.codeneuron.Service.GraphService.InitGraph，实现类是com.example.codeneuron.ServiceImpl.GraphService.InitGraphImpl
- 在内存中使用邻接表和逆邻接表来表示整张图 InitAdjacencyTable() 用来生成邻接表 InitInverseAdjacencyTable() 用来生成逆邻接表

#### 检查点2

- 点与边保存在全局变量nodes和edges中，都为list结构，直接调用size方法返回点数和边数
- 计算初始连通域的数量的方法是com.example.codeneuron.ServiceImpl.CalculateService.GraphImpl中的ConnectedDomainCount()

#### 检查点3

- com.example.codeneuron.ServiceImpl.CalculateService.PathCalImpl中的 ClosenessCalculate()方法用来计算紧密度，计算结果直接存储在邻接表与逆邻接表内


#### 检查点4

- 生成筛选紧密度后的连通域的方法是com.example.codeneuron.ServiceImpl.CalculateService.GraphImpl中的TopologyCalculate()
- 其他方法说明：
  - getThreshold()，获取紧密度
  - getConnectedDomain()，获取连通域
  - setThreshold(double threshold)，设置紧密度
  - setCalculate(Set tempSet, String key)，封装生成连通域方法

#### 检查点5 路径查找

- 接口类是com.example.codeneuron.Service.CalculateService.PathCal，实现类是com.example.codeneuron.ServiceImpl.CalculateService.PathCalImpl
- 查找所有路径的方法是PathCalImpl.searchAllPaths()，查找最短路径的方法是PathCalImpl.searchShortestPath()
- 其他方法说明：
  - inputToFindPath()，被这两个关键方法调用，处理输入输出，获得完整函数名后调用findPath方法
  - getCompleteFuncName(String func)：inputToFindPath调用，处理函数重名并返回完整函数名
  - findPath(String src, String des)：inputToFindPath调用，具体计算前的准备和计算后的处理
  - DFS(GraphNode start, String des)：findPath调用，进行DFS搜索
  - printPath(ArrayList<GraphNode> path)：打印一条路径
  - sortByLength(ArrayList<ArrayList<GraphNode>> paths)：为多条路径按长度由大到小排序

### Jenkins流程展示

实现自动化部署

包括四部分：拉取、构建、测试和部署 

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/jenkins01.png)

blue ocean展示自动化测试

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/jenkins02.jpg)

blue ocean展示自动化部署

![Image text](https://se3.oss-cn-shanghai.aliyuncs.com/se/jenkins03.png)