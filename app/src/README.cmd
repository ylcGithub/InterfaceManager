工程build.gradle中添加：

allprojects {
    repositories {
       ...
        maven { url 'https://jitpack.io' }
    }
}

app module中的build.gradle中添加：

dependencies {
    ...
    implementation 'com.github.ylcGithub:InterfaceManager:1.0.0'
}