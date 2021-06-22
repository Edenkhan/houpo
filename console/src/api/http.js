import axios from "axios";

const baseURL = '/api';

const instance = axios.create({
  baseURL,
  timeout: 60000
});

instance.interceptors.request.use(config => {
  const token = localStorage.getItem('token')
  // 判断是否存在token，如果存在的话，则每个http header都加上token
  if(token) {
    config.headers.token = token
  }
  return config
  }, error => {
  console.log(error)
  })

function handleError(error) {
  if (error.response) {
    if (error.response.status === 401) {
      window.location.href = '/login';
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

export function getUrl(url) {
  return baseURL + url;
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
