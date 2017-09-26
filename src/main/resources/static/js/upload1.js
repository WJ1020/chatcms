 $("#fileUploadContent").initUpload({
        "uploadUrl":"/uploadcourse/",//上传文件信息地址
        // "progressUrl":"#",//获取进度信息地址，可选，注意需要返回的data格式如下（{bytesRead: 102516060, contentLength: 102516060, items: 1, percent: 100, startTime: 1489223136317, useTime: 2767}）
        "showSummerProgress":false,//总进度条，默认限制
        //"size":350,//文件大小限制，单位kb,默认不限制
        //"maxFileNumber":3,//文件个数限制，为整数
        //   "filelSavePath":"/uploadstudent",//文件上传地址，后台设置的根目录
       "beforeUpload":beforeUploadFun,//在上传前执行的函数
          "onUpload":onUploadFun,//在上传后执行的函数
        autoCommit:true,//文件是否自动上传
          "fileType":['xls']//文件类型限制，默认不限制，注意写的是文件后缀
    });
    function beforeUploadFun(opt){

        // opt.otherData =[{"name":"你要上传的参数","value":"你要上传的值"}];
    }
    function onUploadFun(opt,data){
        var state=data.substring(0,7);
        var countsum=data.substring(7);
        if (state==="success"){
            var content="成功插入了"+countsum+"条数据";
            layer.alert(content, {
                icon: 1,
                skin: 'layer-ext-moon' //该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
            })
        }else {

        }

        // uploadTools.uploadError(opt);//显示上传错误
    }
