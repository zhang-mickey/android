混合前端（如 RN，Flutter），同一份代码开发iOS和Android

## NDK (Native Development Kit)
允许开发人员使用 C、C++ 和其他原生编程语言编写部分 Android 应用的代码
### JNI（Java Native Interface）模块

## AsyncTask原理
AsyncTask内部会创建一个线程池来执行后台任务，然后利用Handler将后台线程的执行结果传递给主线程，从而更新UI

生命周期依赖于Activity/Fragment：AsyncTask是一个匿名内部类，它的生命周期与创建它的Activity/Fragment绑定。如果Activity/Fragment在后台被销毁，而AsyncTask还在执行，就可能导致内存泄漏或程序崩溃

## IntentService原理

##  ANR（Application Not Responding）
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


## Fragment

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
Android系统内置了 SQLite 数据库引擎
SQLiteOpenHelper类，借助这个类可以非常简单地对数据库进行创建和升级
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
## 
强类型语言要求在编译时或运行时严格执行类型检查，确保变量和表达式具有明确的数据类型
##
线程池是JDK中提供的ThreadPoolExecutor类

降低资源消耗：通过池化技术重复利用已创建的线程，降低线程创建和销毁造成的损耗

提高响应速度：任务到达时，无需等待线程创建即可立即执行
![image](https://github.com/zhang-mickey/android/assets/145342600/d673f5dd-cd7b-4821-8fde-f574e93169fa)



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

