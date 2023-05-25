/** @format */

// import { API } from "./index.js";
import { API } from "@/api/axios-jwt";

const api = API();

function list(success, fail) {
  api.get(`/board/`).then(success).catch(fail);
}

function write(board, success, fail) {
  api.post(`/board/`, JSON.stringify(board)).then(success).catch(fail);
}

function detail(boardNo, success, fail) {
  api.get(`/board/${boardNo}`).then(success).catch(fail);
}

function update(boardNo, board, success, fail) {
  api.put(`/board/${boardNo}`, JSON.stringify(board)).then(success).catch(fail);
}

function remove(boardNo, success, fail) {
  api.delete(`/board/${boardNo}`).then(success).catch(fail);
}

// 댓글 영역

function loadComment(boardNo, success, fail) {
  api.get(`/board/${boardNo}`).then(success).catch(fail);
}

function addComment(boardNo, comment, success, fail) {
  api.post(`/board/${boardNo}/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function removeComment(commentNo, success, fail) {
  api.delete(`/board/*/comment/${commentNo}`).then(success).catch(fail);
}

export { list, write, detail, update, remove, addComment, removeComment, loadComment };
