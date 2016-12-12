这是很酷一个可以展开，缩小的Viewpager。

![](https://jitpack.io/v/githubwing/ExpandableViewpager.svg)](https://jitpack.io/#githubwing/ExpandableViewpager)


![image](https://github.com/githubwing/ExpandableViewpager/raw/master/img.gif)




## [下载体验](https://github.com/githubwing/ExpandableViewpager/raw/master/app-debug.apk)


## 使用

在gradle添加依赖

```

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
	dependencies {
	        compile 'com.github.githubwing:ExpandableViewpager:1.0.0'
	}


```

其次将ExpandableViewpager添加到布局。

ExpandViewPager提供了Viewpager常用方法以外。还有几个额外的方法：


```

 //设置背景布局
 mViewpager.setBackgroundView(int resId);
 
 //设置最小高度
 mViewpager.setMinHeight(int height);
 
//设置展开关闭监听器
mViewpager.setOnStateChangedListener(OnStateChangedListener listener);

//展开以及缩小
mViewpager.expand();
mViewpager.collaps();


```


# License

    Copyright 2016 androidwing1992

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
        http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

