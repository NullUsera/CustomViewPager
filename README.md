 自定义ViewGroup实现类似ViewPager滑动效果
 Scroller使用： http://blog.csdn.net/guolin_blog/article/details/48719871
 
 主要可以分为以下几个步骤：
  1. 创建Scroller实例
  2. 调用startScroll()方法来初始化滚动数据并刷新界面
  3. 重写computeScroll()方法，并在其内部完成平滑滚动的逻辑
