# 在前端项目运行之前的准备
* 保证设备拥有Node.js 16以上,npm或yarn等最新版本
* 安装yarn,npm运行命令npm install -g yarn
* 安装Node.js,https://nodejs.org/zh-cn
* 部分设备会遇到“因为在此系统上禁止运行脚本”问题
打开Windows Power Shell(管理员),输入该命令:set-ExecutionPolicy RemoteSigned
然后输入A，关闭窗口即可

* 相关文件下载，国内源 https://wwqn.lanzoul.com/b03kap6pa 密码:1hg8

# 运行项目,cd到该项目目录,yarn构建,再yarn dev运行项目

## 前后端分离项目模板

包含基本的登录、注册、密码重置等等功能，可以二次开发编写具体场景下的应用程序。

* 登录功能（支持用户名、邮箱登录）
* 注册用户（通过邮箱注册）
* 重置密码（通过邮箱重置密码）



##注意##
* 遇到“OpenSSL SSL_read: Connection was reset, errno 10054”问题时在最外层的目录的终端中输入 git config --global http.sslVerify "false"
* 克隆后打开Navicat Premium在项目复制SQL中的SQL文件，然后新建一个数据库，名为blog-project,在这个数据库里新建查询，复制sql语句运行
* 克隆时遇到错误"stderr: fatal: unable to access 'https://my.gitlab.server/group/repo.git/': Empty reply from server" 可尝试在终端运行该行代码"git config --global --unset http.sslverify"
