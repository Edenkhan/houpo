import {get} from "./http";

export function keepSession() {
  return get('/base/session/keep');
}

export function getBanner() {
  return get('/banner/session/keep');
}



export function getBannerOnindex() {
  return get('/banner/get');
}
