import { API } from "@/api/axios-jwt";

function list(success, fail) {
	let api = API();
	api.get(`/notice/list`).then(success).catch(fail);
}

function write(notice, success, fail) {
	let api = API();
	api.post(`/admin/`, JSON.stringify(notice)).then(success).catch(fail);
}

function detail(noticeNo, success, fail) {
	let api = API();
	api.get(`/notice/${noticeNo}`).then(success).catch(fail);
}

function update(noticeNo, notice, success, fail) {
	let api = API();
	api.put(`/admin/${noticeNo}`, JSON.stringify(notice)).then(success).catch(fail);
}

function remove(noticeNo, success, fail) {
	let api = API();
	api.delete(`/admin/${noticeNo}`).then(success).catch(fail);
}

export { list, write, detail, update, remove };
