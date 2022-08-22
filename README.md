**一、萌宠相关**

 **1.1、 接口功能：**  获取所有宠物

 **URL：** http://172.26.160.2:9876/pet/select

 **HTTP请求方式：** GET/POST

  **请求参数:** 无

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 3,
                "name": "小红",
                "desc": null,
                "gender": "0",
                "age": "3",
                "weight": "13",
                "birth_day": "2020-2-5",
                "create_time": "2020-2-5",
                "health_info": "5",
                "type": "0",
                "image": null,
                "isadopt": "1"
            }
        ]
    }
}
```


 **1.2、 接口功能：**  增加宠物


 **URL：** http://172.26.160.2:9876/pet/insert


 **HTTP请求方式：** GET/POST


 ** 请求参数:** 


| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| name                 | 是        | String |    |
| desc | 是        | String |    |
| gender| 是        | String |    |
| age              | 是       | String |    |
| weight| 是        | String |    |
| birth_day| 是        | String |    |
| create_time| 是        | String |    |
| health_info| 是        | String |    |
| type| 是        | String |    |
| image| 是        | String |    |
| isadopt| 是        | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "添加成功": null
    }
}
```


 
 **1.3、 接口功能：**   根据id删除宠物

 **URL：** http://172.26.160.2:9876/pet/delete

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 无
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "删除成功": null
    }
}
```



 **1.4、 接口功能：**  根据id修改宠物

 **URL：** http://172.26.160.2:9876/pet/update

 **支持格式：** 

 **HTTP请求方式：** GET/POST

| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| name                 | 是        | String |    |
| desc | 是        | String |    |
| gender| 是        | String |    |
| age              | 是       | String |    |
| weight| 是        | String |    |
| birth_day| 是        | String |    |
| create_time| 是        | String |    |
| health_info| 是        | String |    |
| type| 是        | String |    |
| image| 是        | String |    |
| isadopt| 是        | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "修改成功": null
    }
}
```

 **1.5、 接口功能：**  根据类型和性别获取宠物

 **URL：** http://172.26.160.2:9876/pet/selectByTypeAndGender

 **HTTP请求方式：** GET/POST

  **请求参数:** 
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| type                   | 是       | String |    |
| gender                 | 是        | String |    |

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 3,
                "name": "小红",
                "desc": null,
                "gender": "0",
                "age": "3",
                "weight": "13",
                "birth_day": "2020-2-5",
                "create_time": "2020-2-5",
                "health_info": "5",
                "type": "0",
                "image": null,
                "isadopt": "1"
            }
        ]
    }
}
```
**二、用户相关**

 **2.1、 接口功能：** 获取所有用户  

 **URL：** http://172.26.160.2:9876/clientUser/select

 **HTTP请求方式：** GET/POST

  **请求参数:** 无

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 3,
                "name": "小红",
                "phone": 123414125,
                "address": "北京市朝阳区",
                "pet": "3",
            }
        ]
    }
}
```


 **2.2、 接口功能：**  增加用户


 **URL：** http://172.26.160.2:9876/clientUser/insert


 **HTTP请求方式：** GET/POST


 ** 请求参数:** 


| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| name                 | 是        | String |    |
| phone| 是        | String |    |
| address| 是        | String |    |
| pet| 是       | String |    |


 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "添加成功": null
    }
}
```


 
 **2.3、 接口功能：**   根据id删除用户

 **URL：** http://172.26.160.2:9876/clientUser/delete

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 无
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "删除成功": null
    }
}
```



 **2.4、 接口功能：**  根据id修改用户

 **URL：** http://172.26.160.2:9876/clientUser/update

 **支持格式：** 

 **HTTP请求方式：** GET/POST

| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| name                 | 是        | String |    |
| phone| 是        | String |    |
| address| 是        | String |    |
| pet| 是       | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "修改成功": null
    }
}
```

**三、萌宠社区**

 **3.1、 接口功能：** 获取所有社区  

 **URL：** http://172.26.160.2:9876/petCommunity/getPetCommunityList

 **HTTP请求方式：** GET/POST

  **请求参数:** 无

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 1,
                "user": "张三",
                "name": "上海金毛兴趣圈",
                "tag": "金毛",
                "desc": "对金毛感兴趣的欢迎加入我们的大家庭",
                "num": "101"
            }
        ]
    }
}
```


 **3.2、 接口功能：**  增加社区


 **URL：** http://172.26.160.2:9876/petCommunity/insert


 **HTTP请求方式：** GET/POST


 ** 请求参数:** 


| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| user| 是       | String    |    |
| name                 | 是        | String |    |
| tag| 是        | String |    |
| desc| 是        | String |    |
| num| 是       | String |    |


 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "添加成功": null
    }
}
```


 
 **3.3、 接口功能：**   根据id删除社区

 **URL：** http://172.26.160.2:9876/petCommunity/delete

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 无
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "删除成功": null
    }
}
```



 **3.4、 接口功能：**  根据id修改社区

 **URL：** http://172.26.160.2:9876/petCommunity/update

 **支持格式：** 

 **HTTP请求方式：** GET/POST

| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| user| 是       | String    |    |
| name                 | 是        | String |    |
| tag| 是        | String |    |
| desc| 是        | String |    |
| num| 是       | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "修改成功": null
    }
}
```

**四、公益项目**

 **4.1、 接口功能：** 获取所有公益项目

 **URL：** http://172.26.160.2:9876/project/selectAllProject

 **HTTP请求方式：** GET/POST

  **请求参数:** 无

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 10,
                "user": "1",
                "title": "为流浪的它找个家",
                "location": "海南",
                "desc": "打造温馨的线下领养体验中心",
                "intro": "机场杂物间惊现一窝小奶猫，救助人在海口美兰机场工作，在杂物间发现了五只嗷嗷待哺的小奶猫，而猫妈妈却不见了踪影。",
                "tag": "流浪动物",
                "collection_agency": null,
                "executive_agency": null,
                "num": "6.92",
                "image": null
            }
        ]
    }
}
```


 **4.2、 接口功能：**  增加公益项目


 **URL：** http://172.26.160.2:9876/project/insertProject


 **HTTP请求方式：** GET/POST


 ** 请求参数:** 


| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| user| 是       | String    |    |
| title| 是        | String |    |
| location| 是        | String |    |
| desc| 是        | String |    |
| intro| 是       | String |    |
| tag| 是        | String |    |
| collection_agency| 是        | String |    |
| num| 是       | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "添加成功": null
    }
}
```


 
 **4.3、 接口功能：**   根据id删除社区

 **URL：** http://172.26.160.2:9876/project/deleteProjectById

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 无
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "删除成功": null
    }
}
```



 **4.4、 接口功能：**  根据id修改社区

 **URL：** http://172.26.160.2:9876/project/updateProject

 **支持格式：** 

 **HTTP请求方式：** GET/POST
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| user| 是       | String    |    |
| title| 是        | String |    |
| location| 是        | String |    |
| desc| 是        | String |    |
| intro| 是       | String |    |
| tag| 是        | String |    |
| collection_agency| 是        | String |    |
| num| 是       | String |    |


 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "修改成功": null
    }
}
```


**五、萌宠医院**

 **5.1、 接口功能：** 获取所有医院

 **URL：** http://172.26.160.2:9876/hospital/select

 **HTTP请求方式：** GET/POST

  **请求参数:** 无

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 10,
                "name": "美联众合动物医院（太阳宫分院）",
                "pet": null,
                "user": "1",
                "grade": "4.5",
                "tag": "连锁",
                "location": "朝阳区",
                "distance": "1.2km ",
                "image": null
            }
        ]
    }
}
```


 **5.2、 接口功能：**  增加医院


 **URL：** http://172.26.160.2:9876/hospital/insert


 **HTTP请求方式：** GET/POST


 ** 请求参数:** 


| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| user                 | 是        | String |    |
| pet                  | 是        | String |    |
| location             | 是        | String |    |
| name                 | 是       | String |    |
| grade                | 是        | String |    |
| tag                  | 是        | String |    |
| distance             | 是        | String |    |
| image                | 是        | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "添加成功": null
    }
}
```


 
 **5.3、 接口功能：**   根据id删除医院

 **URL：** http://172.26.160.2:9876/hospital/deleteProjectById

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 无
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "删除成功": null
    }
}
```



 **5.4、 接口功能：**  根据id修改社区

 **URL：** http://172.26.160.2:9876/hospital/updateProject

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| user                 | 是        | String |    |
| pet                  | 是        | String |    |
| location             | 是        | String |    |
| name                 | 是       | String |    |
| grade                | 是        | String |    |
| tag                  | 是        | String |    |
| distance             | 是        | String |    |
| image                | 是        | String |    |


 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "修改成功": null
    }
}
```



**六、萌宠医院相关产品**

 **6.1、 接口功能：**  查询所有医院产品

 **URL：** http://172.26.160.2:9876/hospitalProduct/select

 **HTTP请求方式：** GET/POST

  **请求参数:** 无

 **返回结果** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "total": 3,
        "list": [
            {
                "id": 10,
                "name": "狗狗夏日清凉浴",
                "hospitalName": null,
                "pet": null,
                "user": null,
                "originalPrice": "59.9",
                "discountPrice": "44",
                "image": null
            }
        ]
    }
}
```


 **6.2、 接口功能：**  增加医院产品


 **URL：** http://172.26.160.2:9876/projectProduct/insert


 **HTTP请求方式：** GET/POST


 ** 请求参数:** 


| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是       | int    |    |
| name                 | 是        | String |    |
| hospital_name                  | 是        | String |    |
| pet             | 是        | String |    |
| user              | 是       | String |    |
| original_price               | 是        | String |    |
| discount_price                  | 是        | String |    |
| image                | 是        | String |    |


 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "添加成功": null
    }
}
```


 
 **6.3、 接口功能：**   根据id删除医院产品

 **URL：** http://172.26.160.2:9876/projectProduct/delete

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数: ** 无
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "删除成功": null
    }
}
```



 **6.4、 接口功能：**  根据id修改医院产品名称

 **URL：** http://172.26.160.2:9876/projectProduct/update

 **支持格式：** 

 **HTTP请求方式：** GET/POST

 ** 请求参数:** 
| 字段                 | 是否必填  | 类型   | 备注 |
|--------------------- |------    |--------|----|
| id                   | 是        | int    |    |
| name                 | 是        | String |    |

 **返回结果:** 

```
{
    "status": "suc",
    "code": 0,
    "message": "",
    "data": {
        "修改成功": null
    }
}
```
