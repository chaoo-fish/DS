# ✨数据结构与算法

> 源码存放地
> Github：[https://github.com/sanshisi/DS](https://github.com/sanshisi/DS)

# 一、概念介绍

## 1.数据结构概述

> 数据之间的关系

### 1.1逻辑结构

* 线性结构：线性结构中的数据元素之间是一对一的关系
* 树形结构：树形结构中的数据元素之间存在一种一对一的层次关系
*  图形结构：图形结构的数据元素是多对多的关系

![image-20220110113715973](https://img-blog.csdnimg.cn/img_convert/25ebf6fa9acba0b50b353a0b13840989.png)

### 1.2物理结构

* 顺序存储结构：开辟一组连续的空间存储数据
* 链式存储结构：开辟一组随机的空间存储数据

![image-20220110123504095](https://img-blog.csdnimg.cn/img_convert/c684212070b16dbf6c030af726b8377b.png)

## 2.算法概述

### 2.1什么是算法

是解决特定问题求解步骤的描述,分析问题，一步一步求解，并得到结果,这一系列的步骤就称之为**算法**

**例如：**1~100求和

```java
=====方法一
int sum=0;
int N=100;
for(int i=1;i<=N;i++){
	sum+=i;
}
=====方法二
int N=100;
int sum=(N+1)*N/2;    
```

> 方案1随着N的增大，循环的次数也会增大，也就意味着执行的次数和运行的时间也会增大
>
> 方案2随着N的增大，其执行次数只有一次，不会随着N的增大而增大

**同一个问题，可以有多种不同的解决方案，也就是说可以用不同的算法去解决同一个问题**

### 2.2评价算法的好坏

* 事后统计法
* 事前分析法

![image-20220110124119211](https://img-blog.csdnimg.cn/img_convert/ad293e77d75a92004d532dd44f160bef.png)

![image-20220110124130526](https://img-blog.csdnimg.cn/img_convert/e7272dfac181efeafb3bd1aa3b7081b8.png)





### 2.3时间复杂度

> **算法时间复杂度主要探究的是问题输入规模N的数量级**
>
> **不是算法的具体执行次数**

---

**常数阶O(1)**

> 就是那些无循环、无递归、与问题输入规模N无关的、逐行执行的代码

```java
int a = 3;
int b = 4;
int c = a + b;
```

---

**线性阶O(n)**

> 与问题输入规模有关的，主要是一层循环的代码，多个一层循环可以并列但不能包含

```java
int N = 10;
for (int i = 1; i <= N; i++) {
    System.out.println(i);
}
```

```java
int N = 10;
for (int i = 1; i <= N; i++) {
    System.out.println(i);
}
for (int i = 1; i <= N; i++) {
    System.out.println(i);
}
```

---

**线性阶O(n+m)**

> 和线性阶O(n)一样，只不过我们有两种数据的输入规模

```java
int N = 10;
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        System.out.println(i + j);
    }
}
```

---

**平方阶O(n^2)**

> 与问题输入规模有关的，主要是二层嵌套循环的代码

```java
int N = 10;
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= N; j++) {
        System.out.println(i + j);
    }
}
```

---

**平方阶O(nm)**

> 和平方阶O(n^2)一样，只不过我们有两种数据输入规模

```java
int N = 10;
int M = 20;
for (int i = 1; i <= N; i++) {
    for (int j = 1; j <= M; j++) {
        System.out.println(i + j);
    }
}
```

---

**对数阶O(logn)**

> 与问题输入规模有关的，主要是一层循环迭代或递归的代码

```java
int count = 1;
int N = 100000;
while (count < N) 
    count = count * 2;
```

---

✨**时间复杂度简单计算**：忽略常数、只保留幂高项、且忽略幂高项的系数。

![image-20220110125423456](https://img-blog.csdnimg.cn/img_convert/f497f099e1b5d76459273da8ec59a4a4.png)

**✨常见阶的比较：**

![image-20220110125557002](https://img-blog.csdnimg.cn/img_convert/45313462df62bec9b886ebc3bd8763bf.png)



# 二、动态数组

> 动态数组就是顺序存储结构具体实现的核心思想

## 1.数组概述

首先我们看一下Java内置数组

**特点：**

* 数组的长度一旦确定则不可更改
* 数组只能存储同一类型的数据
* 数组每个存储空间地址是连续且相等的
* 数组提供角标的方式访问元素

**缺点：**

* 长度不可变
* 地址连续且提供角标访问很快
* 数组只有length这个属性



因此我们想实现更多的功能通过动态数组

例如：

* 在任意位置添加删除元素
* 获取任意位置元素
* 更改某一位置元素数据
* 清空数组
* ……

## 2.线性表的实现

### 2.1List接口的定义

定义一系列例如添加、删除、大小、查找元素第一次出现的位置、元素是否在数组、数组是否为空、分割数组、数组排序、迭代……

![image-20220110131628955](https://img-blog.csdnimg.cn/img_convert/1b5c7ae307e57c146da17a38960165b1.png)

代码位置：[https://github.com/sanshisi/DS/blob/master/src/p1/%E6%8E%A5%E5%8F%A3/List.java](https://github.com/sanshisi/DS/blob/master/src/p1/%E6%8E%A5%E5%8F%A3/List.java)

### 2.2实现ArrayList

✨**具体实现**

**添加或删除会遇到的情况**

> 扩容或缩容

```java
...add() 
    // 当容量满了，扩两倍
    // 判读线性表是否满状态
    if (size == data.length) {
        resize(2 * data.length);
    }
...
    
...remove()
    // 什么时候缩容
    // 1.有效元素是容量的1/4
    // 2.当前容量不得小于的等于默认容量
    if (size == data.length / 4 && data.length > DEFAULT_CAPACITY) {
        resize(data.length / 2);
    }
...


// 扩容/缩容 操作 不向外界开放提供  是私有private
private void resize(int newLen) {
    E[] newData = (E[]) new Object[newLen];
    for (int i = 0; i < size; i++) {
        newData[i] = data[i];
    }
    data = newData;
}
```

---

**排序**

```java
@Override
public void sort(Comparator<E> c) {
    if (c == null) {
        throw new IllegalArgumentException("comparator can not be null");
    }
    for (int i = 1; i < size; i++) {
        E e = data[i];
        int j = 0;
        for (j = i; j > 0 && c.compare(data[j - 1], e) > 0; j--) { // compare > 0 代表第一个值比第二个值大
            data[j] = data[j - 1];
        }
        data[j] = e;
    }
}
```

---

**重写equals**

```java
@Override
public boolean equals(Object o) { // 比较的是两个 ArrayList 是否相等
    // 1.判空
    if (o == null) {
        return false;
    }
    // 2.判自己
    if (this == o) {
        return true;
    }
    // 3.判类型
    if (o instanceof ArrayList) {
        // 4.按照自己的逻辑比较
        ArrayList<E> other = (ArrayList<E>) o;
        // 5.先比较元素的个数
        if (this.size != other.size) {
            return false;
        }
        // 6.有效元素个数相等的情况下 逐个比较元素
        for (int i = 0; i < size; i++) {
            if (!data[i].equals(other.data[i])) {
                return false;
            }
        }
        return true;
    }
    return false;
}
```

---

**重写迭代器**

```java
//获取当前这个数据结构/容器 的 迭代器
//通过迭代器对象 更方便挨个取出每一个元素
//同时 实现了Iterable 可以让当前的数据结构/容器 被foreach循环遍历
@Override
public Iterator<E> iterator() {
    return new ArrayListIterator();
}

//创建一个属于ArrayList的迭代器
class ArrayListIterator implements Iterator<E> {
    private int cur = 0;

    @Override
    public boolean hasNext() {//判断是否有下一个元素
        return cur < size;
    }

    @Override
    public E next() {//如果有下一个元素 则把当前元素返回 并移至到下一个元素
        return data[cur++]; // 先用后加
    }
}
```

……





![image-20220110131732408](https://img-blog.csdnimg.cn/img_convert/6e5d600a5fb86d0abff854bc518701d9.png)

代码位置：[https://github.com/sanshisi/DS/blob/master/src/p2/%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84/ArrayList.java](https://github.com/sanshisi/DS/blob/master/src/p2/%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84/ArrayList.java)

## 3.栈的实现

> 先进后出

### 3.1Stack接口的定义

**📃栈的方法**

* 出栈
* 入栈
* 查看栈顶数据
* ……

因为是动态数组实现栈，所以我们实现出入栈都是对数组进行操作

入栈本质上就是在动态数组尾部添加一个数据

出栈本质上就是动态数组尾部删除一个数据

![image-20220110132149124](https://img-blog.csdnimg.cn/img_convert/ea0c1b5f1c149c5e28b90fb2192b2964.png)

代码位置：[https://github.com/sanshisi/DS/blob/master/src/p1/%E6%8E%A5%E5%8F%A3/Stack.java](https://github.com/sanshisi/DS/blob/master/src/p1/%E6%8E%A5%E5%8F%A3/Stack.java)

### 3.2实现ArrayStack

![image-20220110132205228](https://img-blog.csdnimg.cn/img_convert/0852edb68dfad0b47985d742956431ec.png)

代码位置：[https://github.com/sanshisi/DS/blob/master/src/p2/%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84/ArrayStack.java](https://github.com/sanshisi/DS/blob/master/src/p2/%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84/ArrayStack.java)



### 3.3中缀表达式

> 传入一个表达式   (10+20/2*3)/2+8   对其进行计算



给定表达式：`(10+20/2*3)/2+8`

首先我们需要将表达式中的字符和数字分离，用到自定义方法`insertBlanks()`和字符串的`split()`,之后这些存入数组`tokens`中

```java
=====insertBlanks()=====
//对原表达式进行格式化处理 给所有的非数字字符两边添加空格
private static String insertBlanks(String expression) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < expression.length(); i++) {
        char c = expression.charAt(i);
        if (c == '(' || c == ')' || c == '+' || c == '-' || c == '*' || c == '/') {
            sb.append(' ');
            sb.append(c);
            sb.append(' ');
        } else {
            sb.append(c);
        }
    }
    return sb.toString();
}
```



接下来思路就很简单了，依次遍历数组`tokens`，取出元素放入`numberStack`、`operatorStack`两个栈中，然后在根据符号进行弹栈操



举个例子：

1*2+3 

> 需要遍历5次

**第1次遍历：**

`1`直接放入数字栈

`numberStack : [1]`

`operatorStack : []`	

**第2次遍历：**

`+`，符号栈为空，直接放入符号栈

`numberStack : [1]`

`operatorStack : [*]`	

**第3次遍历：**

`2`直接放入数字栈

`numberStack : [1,2]`

`operatorStack : [*]`	

**第4次遍历：**

`+`,此时符号栈已经有乘号了，并且乘号优先级比加号高，所以应当进行弹栈计算，将得到的结果放入数字栈

`numberStack : [2]`

`operatorStack : [+]`	

**第5次遍历：**

`3`直接放入数字栈

`numberStack : [2,3]`

`operatorStack : [+]`	

**最后**

将数字栈和符号栈中的元素依次弹出计算



代码位置：[https://github.com/sanshisi/DS/blob/master/src/p2/%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84/InfixCalculator.java](https://github.com/sanshisi/DS/blob/master/src/p2/%E7%BA%BF%E6%80%A7%E7%BB%93%E6%9E%84/InfixCalculator.java)
