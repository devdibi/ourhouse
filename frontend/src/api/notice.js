import { API } from "./index.js";

const api = API();

function list(success, fail) {
  api.get(`/notice/`).then(success).catch(fail);
}

function write(notice, success, fail) {
  api.post(`/notice/`, JSON.stringify(notice)).then(success).catch(fail);
}

function detail(noticeNo, success, fail) {
  api.get(`/notice/${noticeNo}`).then(success).catch(fail);
}

function update(noticeNo, notice, success, fail) {
  api.put(`/notice/${noticeNo}`, JSON.stringify(notice)).then(success).catch(fail);
}

function remove(noticeNo, success, fail) {
  api.delete(`/notice/${noticeNo}`).then(success).catch(fail);
}

export { list, write, detail, update, remove };
