/** @format */

// import { API } from "./index.js";
import { API } from "@/api/axios-jwt";

function list(success, fail) {
	let api = API();
	api.get(`/board/`).then(success).catch(fail);
}

function write(board, success, fail) {
	let api = API();
	api.post(`/board/write`, JSON.stringify(board)).then(success).catch(fail);
}

function detail(boardNo, success, fail) {
	let api = API();
	api.get(`/board/${boardNo}`).then(success).catch(fail);
}

function update(boardNo, board, success, fail) {
	let api = API();
	api.put(`/board/${boardNo}`, JSON.stringify(board)).then(success).catch(fail);
}

function remove(boardNo, success, fail) {
	let api = API();
	api.delete(`/board/${boardNo}`).then(success).catch(fail);
}

// 댓글 영역

function loadComment(boardNo, success, fail) {
	let api = API();
	api.get(`/board/${boardNo}`).then(success).catch(fail);
}

function addComment(boardNo, comment, success, fail) {
	let api = API();
	api.post(`/board/${boardNo}/comment`, JSON.stringify(comment)).then(success).catch(fail);
}

function removeComment(commentNo, success, fail) {
	let api = API();
	api.delete(`/board/*/comment/${commentNo}`).then(success).catch(fail);
}

export { list, write, detail, update, remove, addComment, removeComment, loadComment };
