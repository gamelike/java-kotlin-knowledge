### [基础用法](https://www.bilibili.com/video/BV1n94y1U7Eu?spm_id_from=333.851.b_7265636f6d6d656e64.3&vd_source=41a1a85282fb11319a0e5a33b529b4a7)



- -X 请求类型 GET、POST、PUT、DELET 其中，GET可以省略,支持  '-X GET' or '-XGET'<br>
  curl https://jsonplaceholder.typicode.com/posts <br>
  curl -X  GET https://jsonplaceholder.typicode.com/posts <br>
  curl -XGET https://jsonplaceholder.typicode.com/posts <br>
  curl -XPOST http://jsonplaceholder.typicode.com/posts <br>

- -d 可以传递参数 <br>
  curl -XPOST http://jsonplaceholder.typicode.com/posts -d '{"title":"test"}' <br>
- -H 添加请求头,支持添加多个  <br>
   curl -XPOST http://jsonplaceholder.typicode.com/posts -H 'Content-Type:applicaiton/json' -H 'Accept:applicaiton/json' -d '{"title":"test"}' <br>
- -I 获取请求头部 <br>
   curl -I http://jsonplaceholder.typicode.com/posts <br>
- -O 下载文件到当前文件夹  -o 下载后的文件命名为xxx <br>
  curl -O https://s3.bmp.ovh/imgs/2022/06/12/9c9b8889af9da837.jpg <br>
  curl -o cat.png https://s3.bmp.ovh/imgs/2022/06/12/9c9b8889af9da837.jpg <br>
- -L 跟随重定向，curl不会跟随网页而跳转，可以使用-L跳转 <br>
  curl -L https://www.bilibili.com <br>
- -v 显示网络连接信息  --trace相同效果 <br>
  curl -L https://www.bilibili.com -v <br>
- --proxy 使用代理 <br>
  curl --proxy 协议：//用户名：密码@代理地址：端口 URL <br>
  curl --proxy "http://zhangsan:admin@127.0.0.1:8080" "https://www.baidu.com" <br>

### [对ftp协议的支持的使用](https://www.bilibili.com/video/BV1n94y1U7Eu?spm_id_from=333.851.b_7265636f6d6d656e64.3&vd_source=41a1a85282fb11319a0e5a33b529b4a7)

- -u 认证用户名密码 <br>
  curl -u 用户名：密码 -O ftp://server/test.mp4 <br>



### [官网](https://catonmat.net/cookbooks/curl/make-post-request)