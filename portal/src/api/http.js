import axios from "axios";
import {navigateToPage} from "../utils/navigate";
import {auth} from "../config";

const instance = axios.create({
  baseURL: '/api/',
  timeout: 60000
});

function handleError(error) {
  if (error.response) {
    if (error.response.status === 401) {
      navigateToPage(auth.homeAuthPath);
      return Promise.reject(error.response.data);
    }
    return Promise.reject(error.response.data);
  }

  if (error.request) {
    return Promise.reject({
      message: '网络连接出错'
    });
  }

  return Promise.reject({
    message: error.message
  });
}

export function get(url, config) {
  return instance.get(url, config)
    .then(data => {
      return data.data;
    })
    .catch(handleError);
}

export function post(url, data, config) {
  return instance.post(url, data, config)
    .then(data => {
      return data.data;
    })
    .catch(handleError);
}
