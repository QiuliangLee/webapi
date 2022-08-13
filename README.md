1	宠物相关
1.1	添加宠物
请求地址：get /pet/addPet
请求参数
字段	说明	类型	备注	是否必填
id
	宠物主键	String		否
name
	姓名	String		否
desc
	描述	String		否
gender
	性别	String		否
age
	年龄	String		否
weight
	体重	String		否
birth_day
	生日	String		否
create_time
	进入平台时间	String		否
health_info
	健康信息	String		否
type
	类别	String		否
Image
	图像	String		否

1.2	根据类型和性别获取所有宠物
请求地址：get      /pet/getPetListByTypeAndGender
请求参数
字段	说明	类型	备注	是否必填
type
	宠物类型	String		是
gender
	性别	String		是
返回参数
 
data：
字段	说明	类型	备注	是否必填
List
	Pet list	List		是
List：
字段	说明	类型	备注	是否必填

	Pet list	List		是
字段	说明	类型	备注	是否必填
id
	宠物主键	String		否
name
	姓名	String		否
desc
	描述	String		否
gender
	性别	String		否
age
	年龄	String		否
weight
	体重	String		否
birth_day
	生日	String		否
create_time
	进入平台时间	String		否
health_info
	健康信息	String		否
type
	类别	String		否
Image
	图像	String		否
1.3	获取所有宠物
请求地址：get      /pet/ /pet/getList
请求参数：无
返回参数
 
data：
字段	说明	类型	备注	是否必填
List
	Pet list	List		是
List：
字段	说明	类型	备注	是否必填

	Pet list	List		是
字段	说明	类型	备注	是否必填
id
	宠物主键	String		否
name
	姓名	String		否
desc
	描述	String		否
gender
	性别	String		否
age
	年龄	String		否
weight
	体重	String		否
birth_day
	生日	String		否
create_time
	进入平台时间	String		否
health_info
	健康信息	String		否
type
	类别	String		否
Image
	图像	String		否
1.1	领养宠物
请求地址：get      /pet/ /pet/ updatePetByIs_adopt
请求参数：id，宠物逐渐
返回参数：无

2	用户相关
2.1	添加用户
请求地址：get   /clientUser/insertClientUser
请求参数
字段	说明	类型	备注	是否必填
id
	宠物主键	String		否
name
	姓名	String		否
phone
	电话	String		否
address
	地址	String		否
pet
	宠物id	String		否