// app.ts
App<IAppOption>({
  globalData: {
    userInfo: undefined,
    url: "http://localhost:8080"
  },
  onLaunch() {
    wx.showLoading({
      title: '正在登录',
      mask: true
    })
    // 登录
    wx.login({
      success: res => {
        console.log(res.code)
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        wx.request({
          url: this.globalData.url + '/login',
          data: {
            jsCode: res.code
          },
          method: "GET",
          success: (res) => {
            type resp = {
              status: number;
              msg: string;
              data: {
                token: string;
              }
            }
            console.log(res);
            const data = res.data as resp;
            const token = data.data.token;
            wx.setStorageSync("token", token);
          },
          fail: (err) => {
            wx.removeStorageSync("token");
            console.log(err);
          },
          complete: () => {
            wx.hideLoading();
          }
        })
      },
    })
  },
})