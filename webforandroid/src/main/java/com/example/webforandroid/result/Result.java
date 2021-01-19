package com.example.webforandroid.result;

public enum Result {
    LOGIN_SUCCESS(1,"登录成功"),
    LOGIN_ERROR(2,"登录失败"),
    REGISTER_SUCCESS(3,"注册成功"),
    REGISTER_FAILED(4,"注册失败"),
    SERVER_INTERNAL_ERROR(5,"服务器内部发生错误");

    private Integer resultID;
    private String result;

    Result(Integer id, String result){
        this.resultID = id;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultID=" + resultID +
                ", result='" + result + '\'' +
                '}';
    }

    // getter&&setter
    public Integer getResultID() {
        return resultID;
    }

    public void setResultID(Integer resultID) {
        this.resultID = resultID;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
