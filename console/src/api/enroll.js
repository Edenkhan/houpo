import {get, post} from "./http"
import {stringify} from "../utils/qs"

export function listEnroll(params) {
  return get('/enroll/list?' + stringify(params))
}
