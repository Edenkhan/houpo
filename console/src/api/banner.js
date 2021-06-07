import {get, post} from "./http"
import {stringify} from "../utils/qs"

export function listBanner(params) {
  return get('/banner/list?' + stringify(params))
}

export function addBanner(params) {
  return post('/banner/add',stringify(params))
}

export function editBanner(params) {
  return post('/banner/edit',stringify(params))
}

export function getBanner(id) {
  return get(`/banner/get?id=${id}`)
}

