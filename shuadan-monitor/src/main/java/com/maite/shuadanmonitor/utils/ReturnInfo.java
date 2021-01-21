package com.maite.shuadanmonitor.utils;

/**
 * 通用操作结果数据。
 * @author XGQ
 */
public class ReturnInfo<T> {

    private boolean   success;
    private int       code;
    private String    information;
    private String    summary;
    private T         object;
    private Throwable exception;

    /**
     * 初始化类 {@link ReturnInfo} 的新实例。
     */
    public ReturnInfo(){
        this(false, -9);
    }

    /**
     * 初始化类 {@link ReturnInfo} 的新实例。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     */
    public ReturnInfo(boolean success, int code){
        this(success, code, "");
    }

    /**
     * 初始化类 {@link ReturnInfo} 的新实例。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     */
    public ReturnInfo(boolean success, int code, String information){
        this(success, code, information, null);
    }

    /**
     * 初始化类 {@link ReturnInfo} 的新实例。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     * @param data 数据。
     */
    public ReturnInfo(boolean success, int code, String information, T data){
        this(success, code, information, data, null);
    }

    /**
     * 初始化类 {@link ReturnInfo} 的新实例。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     * @param data 数据。
     * @param exception 异常对象。
     */
    public ReturnInfo(boolean success, int code, String information, T data, Throwable exception){
        this(success, code, information, "", data, exception);
    }

    /**
     * 初始化类 {@link ReturnInfo} 的新实例。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     * @param summary 数据描述。
     * @param data 数据。
     * @param exception 异常对象。
     */
    public ReturnInfo(boolean success, int code, String information, String summary, T data, Throwable exception){
        this.success = success;
        this.code = code;
        this.information = information;
        this.summary = summary;
        this.object = data;
        this.exception = exception;
    }

    /**
     * 设置结果数据。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     */
    public void set(boolean success, int code) {
        set(success, code, "");
    }

    /**
     * 设置结果数据。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     */
    public void set(boolean success, int code, String information) {
        set(success, code, information, null);
    }

    /**
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     * @param data 数据。
     */
    public void set(boolean success, int code, String information, T data) {
        set(success, code, information, data, null);
    }

    /**
     * 设置结果数据。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     * @param data 数据。
     * @param exception 异常对象。
     */
    public void set(boolean success, int code, String information, T data, Throwable exception) {
        set(success, code, information, "", data, exception);
    }

    /**
     * 设置结果数据。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     * @param code 数据标识。
     * @param information 数据信息。
     * @param summary 数据描述。
     * @param data 数据。
     * @param exception 异常对象。
     */
    public void set(boolean success, int code, String information, String summary, T data, Throwable exception) {
        this.success = success;
        this.code = code;
        this.information = information;
        this.summary = summary;
        this.object = data;
        this.exception = exception;
    }

    /**
     * 获取数据结果是否是成功的结果。
     * 
     * @return 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 获取数据结果是否是成功的结果。
     * 
     * @param success 如果成功则为 <tt>true</tt>；否则，为<tt>false</tt>。
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取数据标识。
     * 
     * @return 数据标识。
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置数据标识。
     * 
     * @param code 数据标识。
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * 获取数据信息。
     * 
     * @return 数据信息。
     */
    public String getInformation() {
        return information;
    }

    /**
     * 设置数据信息。
     * 
     * @param information 数据信息。
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * 获取数据描述。
     * 
     * @return 数据描述。
     */
    public String getSummary() {
        return summary;
    }

    /**
     * 设置数据描述。
     * 
     * @param message 数据描述。
     */
    public void setSummary(String message) {
        this.summary = message;
    }

    /**
     * 获取数据。
     * 
     * @return 数据。
     */
    public T getData() {
        return object;
    }

    /**
     * 设置数据。
     * 
     * @param object 数据。
     */
    public void setData(T object) {
        this.object = object;
    }

    /**
     * 获取异常对象。
     * 
     * @return 异常对象。
     */
    public Throwable getException() {
        return exception;
    }

    /**
     * 设置异常对象。
     * 
     * @param exception 异常对象。
     */
    public void setException(Throwable exception) {
        this.exception = exception;
    }
}
