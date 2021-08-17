package bean;

import cn.bmob.v3.BmobObject;

public class App extends BmobObject {
    private String appimg;
    private Integer appType;
    private String appName;

    public String getAppimg() {
        return appimg;
    }

    public void setAppimg(String appimg) {
        this.appimg = appimg;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getAppType() {
        return appType;
    }

    public void setAppType(Integer appType) {
        this.appType = appType;
    }
}
