package com.syswin.temail.usermail.infrastructure.domain.impl;

import com.syswin.temail.usermail.domains.Usermail;
import com.syswin.temail.usermail.infrastructure.domain.UsermailRepo;
import com.syswin.temail.usermail.dto.QueryTrashDTO;
import com.syswin.temail.usermail.dto.TrashMailDTO;
import com.syswin.temail.usermail.dto.UmQueryDTO;
import com.syswin.temail.usermail.infrastructure.domain.mapper.UsermailMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsermailRepoImpl implements UsermailRepo {

  private final UsermailMapper usermailMapper;

  @Autowired
  public UsermailRepoImpl(UsermailMapper usermailMapper) {
    this.usermailMapper = usermailMapper;
  }

  /**
   * 保存单聊消息
   */
  @Override
  public void saveUsermail(Usermail usermail) {
    usermailMapper.saveUsermail(usermail);
  }

  /**
   * 根据用户会话id查询消息列表
   */
  @Override
  public List<Usermail> getUsermail(UmQueryDTO umQueryDto) {
    return usermailMapper.getUsermail(umQueryDto);
  }

  /**
   * 根据msgId获取消息
   */
  @Override
  public Usermail getUsermailByMsgid(String msgid, String owner) {
    return usermailMapper.getUsermailByMsgid(msgid, owner);
  }

  /**
   * 获取用户最新一条消息
   */
  @Override
  public List<Usermail> getLastUsermail(UmQueryDTO umQueryDto) {
    return usermailMapper.getLastUsermail(umQueryDto);
  }

  /**
   * 撤回消息
   */
  @Override
  public int revertUsermail(UmQueryDTO umQueryDto) {
    return usermailMapper.revertUsermail(umQueryDto);
  }

  /**
   * 批量删除消息
   */
  @Override
  public int removeMsg(List<String> msgIds, String owner) {
    return usermailMapper.removeMsg(msgIds, owner);
  }

  /**
   * 阅后即焚
   */
  @Override
  public void destoryAfterRead(String owner, String msgid, int status) {
    usermailMapper.destoryAfterRead(owner, msgid, status);
  }

  /**
   * 根据会话id批量删除消息
   */
  @Override
  public int batchDeleteBySessionId(String sessionId, String owner) {
    return usermailMapper.batchDeleteBySessionId(sessionId, owner);
  }

  /**
   * 根据msgId获取用户消息列表
   */
  @Override
  public List<Usermail> getUsermailListByMsgid(String msgid) {
    return usermailMapper.getUsermailListByMsgid(msgid);
  }

  /**
   * 根据msgIds获取消息列表
   */
  @Override
  public List<Usermail> getUsermailByFromToMsgIds(String from, List<String> msgIds) {
    return usermailMapper.getUsermailByFromToMsgIds(from, msgIds);
  }

  /**
   * 更新消息的回复数
   */
  @Override
  public void updateReplyCountAndLastReplyMsgid(String msgid, String owner, int count, String lastReplyMsgid) {
    usermailMapper.updateReplyCountAndLastReplyMsgid(msgid, owner, count, lastReplyMsgid);
  }

  /**
   * 根据msgIds批量更新消息状态
   */
  @Override
  public int updateStatusByMsgIds(List<String> msgIds, String owner, int status) {
    return usermailMapper.updateStatusByMsgIds(msgIds, owner, status);
  }

  /**
   * 根据用户的消息状态批量删除消息
   */
  @Override
  public int removeMsgByStatus(List<TrashMailDTO> trashMails, String owner, int status) {
    return usermailMapper.removeMsgByStatus(trashMails, owner, status);
  }

  /**
   * 根据msgIds批量移入废纸篓
   */
  @Override
  public int updateStatusByTemail(List<TrashMailDTO> trashMails, String owner, int status) {
    return usermailMapper.updateStatusByTemail(trashMails, owner, status);
  }

  /**
   * 查询指定状态的消息列表
   */
  @Override
  public List<Usermail> getUsermailByStatus(QueryTrashDTO queryDto) {
    return usermailMapper.getUsermailByStatus(queryDto);
  }

}