# andriod cv
https://github.com/chiclaim/AndroidAll?tab=readme-ov-file
## Retrofit
核心原理为使用 OkHttp 发送网络请求，通过动态代理将定义的 Java 接口转化为 HTTP 请求，然后将请求发送给服务器
## AccessibilityService
### 自动化UI测试
自动抢红包、自动点赞

## apk压缩
**资源压缩**  

### 代码混淆
ProGuard  minifyEnabled为true的作用：启用代码混淆、压缩APK  

通过混淆可以提高程序的安全性，增加逆向工程的难度，同时也有效缩减了apk的体积
### Bitmap
#### Glide
先 with()，再 load()，最后 into()

with() 方法中传入的实例会决定 Glide 加载图片的生命周期，如果传入的是 Activity 或者 Fragment 的实例，那么当这个 Activity 或 Fragment 被销毁的时候，图片加载也会停止。如果传入的是 ApplicationContext，那么只有当应用程序被杀掉的时候，图片加载才会停止。

Glide 从来都不会直接将图片的完整尺寸全部加载到内存中，而是用多少加载多少。Glide 会自动判断 ImageView 的大小，然后只将这么大的图片像素加载到内存当中，帮助我们节省内存开支

## Flutter
自绘引擎解决的是 UI 的跨平台问题  
Flutter 既不使用 WebView，也不使用操作系统的原生控件。 相反，Flutter 使用自己的高性能渲染引擎来绘制 Widget（组件）。这样不仅可以保证在 Android 和iOS 上 UI 的一致性，也可以避免对原生控件依赖而带来的限制及高昂的维护成本。  

app原生开发后期有相应的改动，如果需要用户体验新的功能，那么用户必须得升级app才行；而非原生的则只用更新服务器上的代码即可

 ![image](https://github.com/zhang-mickey/android/assets/145342600/5b27a57a-e2a0-4fcb-acd5-7b8117822005)

程序主要有两种运行方式：静态编译与动态解释。静态编译的程序在执行前程序会被提前编译为机器码（或中间字节码），通常将这种类型称为AOT （Ahead of time）即 “提前编译”。而解释执行则是在运行时将源码实时翻译为机器码来执行，通常将这种类型称为JIT（Just-in-time）即“即时编译”。

AOT 程序的典型代表是用 C/C++ 开发的应用，它们必须在执行前编译成机器码；而JIT的代表则非常多，如JavaScript、python等

### 网页加载控件WebView
![image](https://github.com/zhang-mickey/android/assets/145342600/1174d21c-29d8-4653-a53d-4a5f79c6767e)


混合开发中，H5代码是运行在 WebView 中，而 WebView 实质上就是一个浏览器内核，其 JavaScript 依然运行在一个权限受限的沙箱中，所以对于大多数系统能力都没有访问权限，如无法访问文件系统、不能使用蓝牙等。


## handler

## hooker
![image](https://github.com/zhang-mickey/android/assets/145342600/0870c999-e69d-405b-8cde-5ecb24a693fd)

## IPC

### binder

## Handler 线程间通信
Handler 常用于在后台线程中执行耗时操作后，将结果传递给主线程并更新 UI。这样可以避免在主线程中执行耗时操作导致界面卡顿  

## WeakReference
弱引用可以让您保持对对象的引用，同时允许GC在必要时释放对象，回收内存

主要用于解决一些临时引用对象的场景下可能出现的内存泄漏问题
## Zygote
android系统中各种进程的启动方式：
init进程 ––> Zygote进程 ––> SystemServer进程 ––>各种应用进程

启动SystemServer

孵化应用进程
## NDK (Native Development Kit)
允许开发人员使用 C、C++ 和其他原生编程语言编写部分 Android 应用的代码
### JNI（Java Native Interface）模块
从 JVM 角度，存在两种类型的代码：“Java”和“native”, native 一般指的是 c/c++，为了使 java 和 native 端能够进行交互，java 设计了 JNI（java native interface）。
JNI 允许java虚拟机（VM）内运行的java代码与C++、C++和汇编等其他编程语言编写的应用程序和库进行互操作

## AsyncTask原理
AsyncTask内部会创建一个线程池来执行后台任务，然后利用Handler将后台线程的执行结果传递给主线程，从而更新UI

生命周期依赖于Activity/Fragment：AsyncTask是一个匿名内部类，它的生命周期与创建它的Activity/Fragment绑定。如果Activity/Fragment在后台被销毁，而AsyncTask还在执行，就可能导致内存泄漏或程序崩溃

## IntentService原理

##  ANR（Application Not Responding）
当我发送一个绘制UI 的消息到主线程Handler之后，经过一定的时间没有被执行，则抛出ANR异常  
主线程阻塞
UI线程操作耗时

将所有耗时操作，比如访问网络，Socket 通信，查询大量SQL 语句，复杂逻辑计算等都放在子线程中去

## ART虚拟机（Android Runtime） VS Dalvik
Dalvik：Dalvik是一种基于寄存器的虚拟机，它在运行时将DEX（Dalvik Executable）字节码转换为机器码执行。
Art：Art是一种AOT（Ahead of Time）编译器，它在应用安装时将DEX字节码预先编译为本地机器码，然后在运行时直接执行本地机器码，无需再进行实时的字节码转换。

Dalvik每次都要编译再运行，Art只会安装时启动编译

Art占用空间比Dalvik大（原生代码占用的存储空间更大），“空间换时间”

Art减少编译，减少了CPU使用频率，使用明显改善电池续航
## 内存泄露
资源对象没关闭造成的内存泄漏，如查询数据库后没有关闭游标cursor

构造Adapter时，没有使用 convertView 重用

Bitmap对象不在使用时调用recycle()释放内存

对象被生命周期长的对象引用，如activity被静态集合引用导致activity不能释放
### 线程产生内存泄露
主要原因在于线程生命周期的不可控
## DrawerLayout
https://www.kancloud.cn/digest/sdksdk0/126441
## Fragment

## RecyclerView
RecyclerView 支持局部刷新（notifyItemChanged()），即只更新列表中的部分项而不是整个列表，以提高性能和效率

### viewholder 复用机制
用于缓存子项的视图引用，以减少 findViewById() 的调用次数

缓存到CachedView中的ViewHolder并不会清理相关信息(比如position、state等)，因此刚移出屏幕的ViewHolder，再次被移回屏幕时，只要从CachedView中查找并显示即可，不需要重新绑定(bindViewHolder)。

而缓存到RecycledViewPool中的ViewHolder会被清理状态和位置信息，因此从RecycledViewPool查找到ViewHolder，需要重新调用bindViewHolder绑定数据

![image](https://github.com/zhang-mickey/android/assets/145342600/e522adb9-3323-4335-b1f0-ca2139ff96b7)

## 自定义ViewGroup
ViewGroup的父类是View，所以它也具有View的特征，可以实现View的方法，但它主要用来充当View的容器。

## 
跨线程的数据和UI更新？
**Handler 机制**
Handler 是 Android 中用于在不同线程之间发送和处理消息的类
**回调**
Activity和Fragment的生命周期？Activity和Fragment之间如何数据传递？

DK 是 Android 提供的工具集，用于在 Android 应用中开发 C/C++ 库。使用 NDK，您可以编写纯 C/C++ 代码，并将其编译为共享库（例如 .so 文件），然后在 Android 应用中使用 JNI 调用这些共享库。
## Android客户端与服务器端通信
项目搭建之始，客户端和服务器一般用 Get 和Post的方式来交互

## WebView

## 服务器配置
短信业务要企业级用户才能使用
建议在本地开发完成之后再上线服务器，所以最好本地也建个表
#### 
Mac和Ubuntu系统都是默认 安装好Apache服务器的，只需要启动一下即可
### XML传输格式
每个需要访问网络的应用程序都会有一个自己的服务器 可以向服务器提交
数据，也可以从服务器上获取数据
## 数据库
### 本地存储
Android系统内置了 SQLite 数据库引擎
**SQLiteOpenHelper类**，借助这个类可以非常简单地对数据库进行创建和升级
每当升级一个数据库版本的时候，onUpgrade()方法里都一 定要写一个相应的if判断语句。
这是为了保证App在跨版本升级的时候， 每一次的数据库修改都能被全部执行。比如用户当前是从第2版升级到第3版，那么只有第二条 判断语句会执行，而如果用户是直接从第1版升级到第3版，那么两条判断语句都会执行。使用 这种方式来维护数据库的升级，不管版本怎样更新，都可以保证数据库的表结构是最新的，而 且表中的数据完全不会丢失。
## splashscreen 

## Content provider
用于在不同的应用程序之间实现数据共享的功能
## Service
用于实现后台功能的Service属于四大组件之 一
Service的运行不依赖于任何用户界面，即使程序被切换到后台，或 者用户打开了另外一个应用程序，Service仍然能够保持正常运行。

## 安卓多线程
我们需要执行一些耗时操作，比如发 起一条网络请求时，考虑到网速等其他原因，服务器未必能够立刻响应我们的请求，如果不将 这类操作放在子线程里运行，就会导致主线程被阻塞，从而影响用户对软件的正常使用
## 调用相册

### Developer options 
build code 连按五次 打开


# Java
SNI:多个域名共享同一个地址并使用不同的证书
## spring boost

### WebSocket
HTTP 协议有一个缺陷：通信只能由客户端发起   做不到服务器主动向客户端推送信息 
![image](https://github.com/zhang-mickey/android/assets/145342600/b0bc9109-c5ba-48f2-b3ec-6324a4c89253)

这种单向请求的特点，注定了如果服务器有连续的状态变化，客户端要获知就非常麻烦。我们只能使用"轮询"：每隔一段时候，就发出一个询问，了解服务器有没有新的信息。最典型的场景就是聊天室。

轮询的效率低，非常浪费资源（因为必须不停连接，或者 HTTP 连接始终打开）
###

依赖注入 IoC（Inversion of Control:控制反转

![image](https://github.com/zhang-mickey/android/assets/145342600/29ac258d-a61e-4041-bf8c-9f7a5827d290)

## 
C++需要开发者手动去管理内存分配，没有 JavaScript 及Java中垃圾回收（GC）的机制
## 
强类型语言要求在编译时或运行时严格执行类型检查，确保变量和表达式具有明确的数据类型
##
线程池是JDK中提供的ThreadPoolExecutor类

降低资源消耗：通过池化技术重复利用已创建的线程，降低线程创建和销毁造成的损耗

提高响应速度：任务到达时，无需等待线程创建即可立即执行
![image](https://github.com/zhang-mickey/android/assets/145342600/d673f5dd-cd7b-4821-8fde-f574e93169fa)

#### 
static 是 Java 中的一个关键字，当用它来修饰成员变量时，那么该变量就属于该类，而不是该类的实例。所以用 static 修饰的变量，它的生命周期是很长的
## hashmap
线程不安全，红黑树
### 扩容
数据结构是数组，所以会涉及到扩容
#### 
<< 运算符表示按位左移运算符，将其左操作数的位向左移动右操作数指定的位数。1 << 30 表示将十进制数 1 的二进制表示向左移动 30 位




# android
设备查询 个人中心 我的订单 我的钱包
用户能够通过手机查看充电桩的位置、充电进度、缴费以及实施远程监控
APP通过手机蓝牙，发送指令到充电桩，充电桩收到蓝牙指令后，给汽车进行充电。
为避免用户逃单，采用预付费方式
智能充电  内嵌的一个二维码模块，用户在使用智能充电时，只要拿出手机扫一下二维码即可充电使用
时序数据库
自主筛选  有空闲时间、是否有停车费、快慢冲、距离等筛选类别
![image](https://github.com/zhang-mickey/android/assets/145342600/6e4939be-9862-49d2-994f-d3d274f4be0e)

## 硬件通信
使用4G模块的充电桩设备，本身成本比较高，而且需要移动网络的支持，在一些特殊的场所，如地下室等信号较弱或无信号的场所，除非安装通讯基站，不然就会对充电桩的使用造成影响，这又增加了产品的成本
### 蓝牙
蓝牙充电桩 充电桩无需联网
创建一个蓝牙适配器并进行初始化
startDiscovery()方法来扫描附近的蓝牙设备



## 单机
单Activity+多Fragment模式
### jetpack Compose
#### Navigation组件
使用 NavHost 可组合函数定义应用中的路线和界面

使用 NavHostController 在界面之间导航

![image](https://github.com/zhang-mickey/android/assets/145342600/764d40a4-4fe3-47bb-8773-118774732d16)

#### ViewModel 
熟悉 Android 架构组件 ViewModel。能够使用 ViewModelProvider.Factory 实例化 ViewModel


#### livedata
![image](https://github.com/zhang-mickey/android/assets/145342600/3710f114-62a7-4778-89e0-ebee322df7e4)

#### 持久化room 
Room 是在 SQLite 数据库基础上构建的一个抽象层

Room 并不直接使用 SQLite，而是负责简化数据库设置和配置以及数据库与应用交互方面的琐碎工作
##### room 实体


##### Room DAO

##### Room Database

数据存储：使用本地SQLite数据库存储数据

数据导出：导出员工和任务数据
## 幸运转盘
### 
首先为商品抬价，抬成多少，因商品而宜

刚开始砍价，砍得都非常快

反比例函数y=1/x？随着x逐渐增加，y只会越来越小，无限趋近于0，但是始终比0大

·              条件1：Y取值最小为0.01，最大为0.99。我们设定，出现0.01的概率为90%，出现0.99的概率为0.01%。

·              条件2：Y的值越大，出现的概率设定越小。同样的Y值，新用户出现概率比老用户高。

·              条件3：当剩余价格X1小于0.01的时候，砍价成功。

·               条件4：砍价超过24H，强制结束。


在后台管理系统中：开发【砍价活动】功能，新增一个砍价活动，设置砍价人数、商品、商品价格、商品最低价等，新增活动的时候，在【新增砍价活动】接口中，根据砍价人数和商品价格、商品的最低价计算出对应的一个砍价随机数（有小数）的list集合




###  预防羊毛党
比如我们平台有些返现的红包，补贴，他的特征是有大量的新用户，来到我们的平台不干什么其他事儿，直接进到套现的流程，没有任何后续的行为路径。

在红包领取模块也可以增加验证码（上行短信验证码）

三元素验证API

如果一个用户领取了免单，他登陆过的所有ip、这些ip登陆过的用户一律进行敏感标注
在Spring Boot中，可以通过 HttpServletRequest.getRemoteAddr() 获取用户的IP地址。

设置利益获取上限：红包活动，需要考虑到一个正常的用户最多能够完成的量，并设置上限。

提高参与门槛：像上面的红包车活动，设置活动门槛，比如注册时间满7天才能参与此次活动，而不是所有用户都能参与。

设置防范风险提示：制定规则，比如在活动期间如果有人恶意刷单违规操作，平台有权取消其参与的资格或者相应的奖励。

避免直接利益：一般情况下，红包可直接提现，是羊毛党的最爱，可以用购物优惠券或者会员卡抵用券的形式，这种情况下，羊包党的即得利益就会少一些，兴趣也会小很多。

加强风控机制：活动时，首先要考虑到如果有羊毛党的大批量涌入服务器的承载力，为服务器扩容。其次，加强黑客防范技术，避免平台有漏洞被黑客攻击。

#### 提高关键成本
注册7日后方可




## 埋点日志

报活埋点：记录 App 的启动操作，包括客户端冷启动或压后台后 App 界面回到前台。用于统计启动次数、新增用户、活跃用户、活跃账号等核心指标。

页面自动埋点：自动记录页面的打开、来源、停留时长等信息。用于分析页面 PV、UV、来源去向等指标。
用户行为监控
负责统计PV(页面访问次数)、UV(页面访问人数)以及用户的点击操作等行为。

压后台埋点：记录 App 前后台切换相关信息。用于分析用户使用应用的时长、活跃时间等指标。



















# php
php header的用法

(1)声明content-type
```
header('Content-Type: application/json; charset=utf-8');
```
## php 表单

## PHP JSON
将 PHP 数组转换为 JSON 格式数据
```
<?php
   $arr = array('a' => 1, 'b' => 2, 'c' => 3, 'd' => 4, 'e' => 5);
   echo json_encode($arr);
?>

```
```
{"a":1,"b":2,"c":3,"d":4,"e":5}

```
[]: 这是 PHP 中的数组附加操作符。它表示将右侧的值添加到数组的末尾
