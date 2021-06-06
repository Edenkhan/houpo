import {get, post} from "./http";
import {stringify} from "../utils/qs";

export function sendLoginVerifyCode(params) {
  return post('/user/sendLoginVerifyCode', stringify(params));
}

export function login(params) {
  return post('/wechat/mp/login', stringify(params));
}

export function logout(params) {
  return post('/wechat/mp/logout', stringify(params));
}

export function profile(params) {
  return get('/user/profile', stringify(params));
}

export function getUser(params) {
  return get('/user/get', stringify(params));
}

export function editUser(params) {

  return post('/user/edit', stringify(params));
}

export function checkUser(params) {
  return post('/user/check?' + stringify(params))
}

export function serachOrder(params) {
  return post('/activity/list?' + stringify(params))
}
export function pay(params) {
  return post('/activity/pay?' + stringify(params))
}
export function baseNotify(params) {
  return post('/activity/baseNotify?' + stringify(params))
}

export function handIn(params) {
  return post('/activity/add?' + stringify(params))
}
export function queryStatus(params) {
  return post('/activity/query?' + stringify(params))
}
export function deleteOrder(params) {
  return post('/activity/delete?' + stringify(params))
}
export function send(phone) {
  return post('/user/sendLoginVerifyCode?' + stringify({phoneNumber:phone}))
}
export function bind(params) {
  return post('/user/bind?' + stringify(params))
}
