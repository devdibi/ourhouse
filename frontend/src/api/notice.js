// import { API } from "./index.js";
import { API } from "@/api/axios-jwt";

const api = API();

function list(success, fail) {
  api.get(`/notice/`).then(success).catch(fail);
}

function write(notice, success, fail) {
  api.post(`/admin/`, JSON.stringify(notice)).then(success).catch(fail);
}

function detail(noticeNo, success, fail) {
  api.get(`/notice/${noticeNo}`).then(success).catch(fail);
}

function update(noticeNo, notice, success, fail) {
  api.put(`/admin/${noticeNo}`, JSON.stringify(notice)).then(success).catch(fail);
}

function remove(noticeNo, success, fail) {
  api.delete(`/admin/${noticeNo}`).then(success).catch(fail);
}

export { list, write, detail, update, remove };
