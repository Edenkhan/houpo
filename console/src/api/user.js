import {get, post} from "./http";
import {stringify} from "../utils/qs";

export function listUsers(params) {
  return get('/user/list?' + stringify(params));
}

export function addUser(params) {
  return post('/user/add', stringify(params));
}

export function editUser(params) {
  return post('/user/edit', stringify(params));
}

export function getUser(id) {
  return post('/user/get', stringify({
    id
  }));
}
