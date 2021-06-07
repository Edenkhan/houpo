import {get, post} from "./http"
import {stringify} from "../utils/qs"

export function listActivity(params) {
  return get('/activity/list?' + stringify(params))
}

export function addActivity(params) {
  return post('/activity/add',stringify(params))
}

export function editActivity(params) {
  return post('/activity/edit',stringify(params))
}

export function getActivity(id) {
  return get(`/activity/get?id=${id}`)
}

