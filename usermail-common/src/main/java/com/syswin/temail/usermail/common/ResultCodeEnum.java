package com.syswin.temail.usermail.common;

/**
 * 返回code
 */
public enum ResultCodeEnum {
  /* 请求成功 */
  SUCCESS(200, "success"),
  /* 请求参数错误 */
  ERROR_REQUEST_PARAM(400, "请求参数错误"),
  /* storeType 不正确！ */
  ERROR_ILLEGAL_STORE_TYPE(459, "storeType 不正确！"),
  /* 发送方处于接收方的黑名单中 */
  ERROR_IN_BLACKLIST(461, "发送方处于接收方的黑名单中"),
  /* 重复DATABASE_KEY_ID，msgId重复，前端有可能收不到回执。 */
  ERROR_DATABASE_KEY_ID(463, "重复DATABASE_KEY_ID,"),
  /* 源消息不存在，此code APP 端做业务逻辑判断，不能修改code值 */
  ERROR_ILLEGAL_PARENT_MSG_ID(464, "源消息不存在"),
  /* 消息decode错误，请检查消息格式 */
  ERROR_MSG_DECODE(467, "消息decode错误，请检查消息格式"),
  /* 消息encode错误，请检查消息格式 */
  ERROR_MSG_ENCODE(468, "消息encode错误，请检查消息格式"),
  /* 消息ZIP错误，请检查消息格式 */
  ERROR_MSG_ZIP(469, "消息ZIP错误，请检查消息格式"),
  /* 出现异常 */
  ERROR_SERVER(500, "出现异常"),
  /* seqID过滤参数格式错误，无法正确解析 */
  ERROR_FILTER_SEQIDS(567, "seqID过滤参数格式错误，无法正确解析");

  private int code;
  private String message;

  ResultCodeEnum(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "RESULT_CODE{" +
        "code=" + code +
        ", message='" + message + '\'' +
        '}';
  }
}
