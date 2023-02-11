// pages/index/index.ts
Page({
  data: {

  },
  onLoad() {

  },
  toTest() {
    wx.navigateTo({
      url: '../testpage/test'
    })
      .then(success => console.log(success))
      .catch(err => console.log(err));
  }
})