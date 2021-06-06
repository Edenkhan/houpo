import Vue from "vue";
import { get, post } from "./http";
import { stringify } from "../utils/qs";
import { Popup } from 'vant';

Vue.use(Popup);
export function createAssess() {
  return post('/assess/createAssess');
}

export function assessResult() {
  return get('/assess/assessResult');
}

export function assessResultJson() {
  return get('/assess/assessResultJson');
}

export function shareAssessResultJson(userId) {
  return post('/assess/shareAssessResultJson', stringify({userId}));
}

export function share() {
  return post('/wechat/mp/share');
}

export function shareSuccess() {
  return post('/wechat/mp/shareSuccess');
}
