import { API } from "./index.js";

const api = API();

function list(success, fail) {
  api.get(`/news/`).then(success).catch(fail);
}

function hit(newsNo, success, fail) {
  api.put(`/news/${newsNo}`).then(success).catch(fail);
}

function update(success, fail) {
  api.post(`/news/`).then(success).catch(fail);
}

export { list, hit, update };
