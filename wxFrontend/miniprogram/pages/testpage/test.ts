// pages/testpage/test.ts
Page({
  data: {
    hasData: false,
    userResp: '',
    adminResp: '',
    imgUrl: '',
    chooseImgUrl: '',
    downloadImgPath: ''
  },
  getData() {
    type dataType = {
      status: number;
      msg: string;
      data?: object;
    };
    wx.request({
      url: 'http://localhost:8080/test/userAuth',
      method: 'POST',
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('token')
      },
      success: res => {
        const data = res.data as dataType;
        this.setData({
          userResp: data.msg
        })
      },
      fail: err => {
        console.log(err);
        this.setData({
          userResp: err.errMsg
        })
      }
    });
    wx.request({
      url: 'http://localhost:8080/test/adminAuth',
      method: 'POST',
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('token')
      },
      success: res => {
        const data = res.data as dataType;
        this.setData({
          adminResp: data.msg
        })
      },
      fail: err => {
        console.log(err);
        this.setData({
          adminResp: err.errMsg
        })
      }
    });
    this.setData({
      imgUrl: 'http://localhost:8080/test/image',
      hasData: true
    });
  },
  chooseFile() {
    wx.chooseMedia({
      mediaType: ['image'],
      success: res => {
        this.setData({
          chooseImgUrl: res.tempFiles[0].tempFilePath
        })
      }
    })
  },
  uploadFile() {
    wx.uploadFile({
      url: 'http://localhost:8080/test/upload',
      filePath: this.data.chooseImgUrl,
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('token')
      },
      name: 'image',
      success: res => {
        console.log('upload success');
        console.log(res);
      }
    })
  }, 
  downloadFile() {
    wx.downloadFile({
      url: 'http://localhost:8080/test/download',
      // filePath: "../../static/download.png",
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('token')
      },
      success: res => {
        this.setData({
          downloadImgPath: res.tempFilePath
        })
      }
    })
  },
  downloadExcel() {
    wx.downloadFile({
      url: 'http://localhost:8080/test/excel',
      filePath: wx.env.USER_DATA_PATH + '/exampleExcel.xlsx',
      header: {
        Authorization: 'Bearer ' + wx.getStorageSync('token')
      },
      success: res => {
        console.log(res.filePath);
        wx.openDocument({
          filePath: res.filePath,
          showMenu: true,
          fileType: 'xlsx',
          success: () => {
            console.log('打开文件成功');
          }
        })
      }
    })
  }
})