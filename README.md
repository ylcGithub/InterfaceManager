# InterfaceManager
接口管理工具类，用于类和类之间的通信
## 使用说明：
工程build.gradle中添加：
~~~
allprojects {
    repositories {
       ...
        maven { url 'https://jitpack.io' }
    }
}
~~~
app module中的build.gradle中添加：
~~~
dependencies {
    ...
    implementation 'com.github.ylcGithub:InterfaceManager:1.1.0'
}
~~~
## 使用范例1，先注册后调用：
注册接口：
~~~
InterfaceManager.getInstance().addInterface("test_key", new CustomInterfaceHasParamAndResult<String,String>() {
            @Override
            public String function(String params) {
                return s+"---返回------------:::";
            }
        })
 ~~~~
 调用接口：
 String result = InterfaceManager.getInstance().invokeInterface("test_key","这是调用参数",String.class);
 ## 使用范例2，先调用后注册：
 调用接口：
 ~~~
  InterfaceManager.getInstance().invokeInterface("test_key", "这是调用参数", String.class, new CustomResultInterface<String>() {
               @Override
               public void function(String result) {
                   
               }
           });
~~~
 注册接口：
 ~~~
 InterfaceManager.getInstance().addInterface("test_key", new CustomInterfaceHasParamAndResult<String,String>() {
            @Override
            public String function(String params) {
                return s+"--返回------------:::";
            }
        })
~~~
 
