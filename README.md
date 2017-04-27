# BannerView
Extends from my another repository"AutoPlayViewpager"

BannerView从我的初始项目AutoPlayViewpager改进而来

> 关于我，欢迎关注  
  博客：ccapton(http://blog.csdn.net/ccapton) 微信：[Ccapton]()   
 
### 简介: 

这是一个自动轮播的控件，能加载网络图片，本地图片，程序资源包图片。


### 示例:  

![](https://raw.githubusercontent.com/Ccapton/AutoPlayViewpager/master/vp.gif) 



### 特性 
加入了可见的圆点指示器，可定制其尺寸（三种），颜色（不限），播放时间间隔，相对位置（左，中，右）。
改进：无限播放与滑动，初始化后加载图片数据即可实现轮播效果。
### 原理说明

这是一个自定义BannerView继承自RelativeLayout，子View为ViewPage和Indicator(继承自RelativeLayout)。通过自定义View：Indicator，
动态添加其子View：Dot（自定义View,继承自ImageView），作为圆点指示器。通过子View ：ViewPager的OnPageChangeListener监听其滑动状态，
进而动态绘制圆点指示器，达到指示效果；通过添加线程，隔一段时间跳转ViewPager到下一页面达到播放效果。
### 如何配置
build.gradle(Project)
``` code
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
build.gradle(Module:app)
``` code
 dependencies {
	         compile 'com.github.Ccapton:BannerView:1.0'
	}
```

### 公共方法

``` code
loadUrl(ArrayList<String> urlList)//加载网络图片地址，只需要把地址集合添加进去就行了。
setAutoPlay(boolean play)//设置是否自动播放，默认播放
setInterval(int interval)//设置播放间隔(ms)，例如3000ms
setDotSize(int dotSize)//设置指示器圆点尺寸（三种）：AutoPlayViewpager.DOT_SMALL,AutoPlayViewpager.DOT_NORMAL,AutoPlayViewpager.DOT_LARGE
setDotColor(int color)//设置指示器颜色，任意颜色
setIndicatorLocation(String location)//设置指示器相对于父布局的位置（三个位置）AutoPlayViewpager.INDICATOR_LOCATION_LEFT,AutoPlayViewpager.INDICATOR_LOCATION_CENTER,AutoPlayViewpager.INDICATOR_LOCATION_RIGHT
...其他ViewPager通用方法...
```
### 使用方法

例：在activity_main.xml中，
``` xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.capton.bannerviewdemo.MainActivity">

    <com.capton.bannerview.BannerView
        xmlns:bannerview="http://schemas.android.com/apk/res-auto"
        android:id="@+id/banner"
        bannerview:dot_size="@dimen/dot_size_normal"
        android:layout_width="match_parent"
        android:layout_height="150dp">
    </com.capton.bannerview.BannerView>
    
    <com.capton.bannerview.BannerView
        android:layout_marginTop="10dp"
        android:layout_below="@+id/banner"
        xmlns:bannerview="http://schemas.android.com/apk/res-auto"
        bannerview:incator_location="left"                  <!--圆点指示器位置：左（下）边-->
        bannerview:dot_size="@dimen/dot_size_small"        <!--圆点指示器尺寸：小-->
        bannerview:dot_color="@color/colorPrimary"          <!--圆点指示器颜色：可自定义-->
        android:id="@+id/banner2"            
        android:layout_width="match_parent"
        android:layout_height="150dp">
    </com.capton.bannerview.BannerView>
    
    <com.capton.bannerview.BannerView
        android:layout_marginTop="10dp"
        android:layout_below="@id/banner2"
        xmlns:bannerview="http://schemas.android.com/apk/res-auto"
        bannerview:incator_location="right"
        bannerview:dot_size="@dimen/dot_size_large"
        bannerview:dot_color="@color/colorAccent"
        android:id="@+id/banner3"
        android:layout_width="match_parent"
        android:layout_height="150dp">
    </com.capton.bannerview.BannerView>
</RelativeLayout>
```

例：在MainActivity中
``` code
public class MainActivity extends AppCompatActivity {

    BannerView bannerView;
    BannerView bannerView2;
    BannerView bannerView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> stringArrayList=new ArrayList<>();
         stringArrayList.add("http://img2.91.com/uploads/allimg/140331/32-1403311A009.jpg");
         stringArrayList.add("http://img1.imgtn.bdimg.com/it/u=3055632223,2826867768&fm=214&gp=0.jpg");
        stringArrayList.add("http://img.article.pchome.net/00/28/41/62/pic_lib/wm/hlfjkpbz_17.jpg");

        bannerView= (BannerView) findViewById(R.id.banner);
        bannerView2= (BannerView) findViewById(R.id.banner2);
        bannerView3= (BannerView) findViewById(R.id.banner3);

         bannerView.loadUrl( stringArrayList);  //加载图片地址集合
         bannerView.setAutoPlay(true); //是否播放，默认 播放
         bannerView.smoothPlay(false); //播放时是否有滑动效果 默认 有
         bannerView.setInterval(3000); //播放间隔,默认3000ms
         // bannerView.setIndicatorLocation(BannerView.INDICATOR_LOCATION_CENTER); //动态改变指示器位置
         // bannerView.setDotSize(BannerView.DOT_NORMAL);  //指示器圆点的尺寸 实际尺寸为：6dp，8dp，12dp
         // bannerView.setDotColor(Color.GREEN);           //指示器圆点的颜色，完全自定义
        bannerView2.loadUrl( stringArrayList);
        bannerView2.setAutoPlay(false);
        bannerView2.setInterval(4000);

        bannerView3.loadUrl(stringArrayList);
        bannerView3.setAutoPlay(false);
        bannerView3.setInterval(5000);

    }
}
```  
### 作者的话
 根据前作AutoPlayViewpager的不足和缺点，把这次的轮播插件做成可无限循环的效果，而且你们只需要把控件初始化后，加载数据源进去就行了。
 
