<%--
  Created by IntelliJ IDEA.
  User: guochunyan
  Date: 2015/12/14
  Time: 17:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- 设置密保手机 -->
<div class="modal fade" id="phoneModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="phoneTitle">设置密保手机</h4>
            </div>
            <div class="modal-body">
                <form id="phoneForm">
                    <div class="form-group">
                        <input type="text" class="form-control" name="phone" style=" max-width:340px;"
                               placeholder="请填写手机号码"
                               name="username"/>
                    </div>
                    <div class="form-group form-inline">
                        <input type="text" class="form-control" name="code_text" id="exampleInputAmount"
                               placeholder="6位验证码">
                        <button type="button" class="btn btn_large ">发送验证码</button>
                    </div>
                </form>
                <p>无法及时获取验证码？是否以下情况</p>

                <p>· 如果收不到验证码，请稍后重新获取，同时检查下手机是不是有安全软件在拦截短信；</p>

                <p> · 如果多次获取还是收不到验证码，请联系客服，谢谢！</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="commons.Binding('phone',this)">绑定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>
<!-- 设置密保邮箱 -->
<div class="modal fade" id="emailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">设置密保邮箱</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group over">
                        <div class="col-xs-2">邮箱地址：</div>
                        <div class="col-xs-10">
                            <select class="form-control">
                                <option value="">443895413@qq.com</option>
                                <option value="">443895413@qq.com</option>
                                <option value="">443895413@qq.com</option>

                            </select>
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-10">
                            <input type="text" class="form-control" placeholder="6位验证码">
                            <button type="submit" class="btn btn_large ">发送验证码</button>
                        </div>
                    </div>
                </form>
                <p>* 您可用使用此密保邮箱找回密码及登录</p>

                <p>* 请勿随意泄露邮箱地址，以防被不法分子利用，骗取帐号信息</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="commons.Binding('email',this)">绑定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>
<!-- 修改邮箱信息-->
<div class="modal fade" id="modifyemailModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="emailTitle">修改密保邮箱</h4>
            </div>
            <div class="modal-body">
                <form id="EmailChange">
                    <div class="form-group over">
                        <div class="col-xs-2">现邮箱地址：</div>
                        <div class="col-xs-10">
                            <label id="oldEmail">443895413@qq.com</label>
                            <button type="" class="btn btn_large ">发送验证码</button>
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-10">
                            <div class="input-group">
                                <input type="text" class="form-control" placeholder="请输入校验码">
                            <span class="input-group-btn">
                        <button class="btn btn-default" type="button">校验</button>
                            </span>
                            </div>
                        </div>
                    </div>
                </form>
                <form id="newEmail" class="hide">
                    <div class="form-group over">
                        <div class="col-xs-2">新邮箱地址：</div>
                        <div class="col-xs-10">
                            <input type="text" class="form-control" placeholder="请输入新邮箱地址">
                        </div>
                    </div>
                    <div class="form-group form-inline">
                        <div class="col-xs-2"></div>
                        <div class="col-xs-10">
                            <input type="text" class="form-control" placeholder="6位验证码">
                            <button type="submit" class="btn btn_large ">发送验证码</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="commons.oldEmail('oldEmails')">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>

            </div>
        </div>
    </div>
</div>