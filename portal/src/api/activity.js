import {get, post} from "./http";
import {stringify} from "../utils/qs";

export function activityList(params) {
  return get('/activityManage/list?'+ stringify(params));
}

export function getActivity(id) {
  return post('/activityManage/get', stringify({
    id
  }));
}

export function addSignUp(params) {
  return post('/activitySignUp/add', stringify(params));
}
