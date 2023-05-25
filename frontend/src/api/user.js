import { API } from "@/api/axios-jwt";

function getSido(success, fail) {
	let api = API();
	api.get(`/house/sido`).then(success).catch(fail);
}

function getSigungu(sido, success, fail) {
	let api = API();
	api
		.get(`/house/sigungu`, { params: { sido: sido } })
		.then(success)
		.catch(fail);
}

function getDong(sigungu, success, fail) {
	let api = API();
	api
		.get(`/house/dong`, { params: { sigungu: sigungu } })
		.then(success)
		.catch(fail);
}

function register(user, success, fail) {
	let api = API();
	api.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail);
}

function randomPassword(userEmail, userName, success, fail) {
	let api = API();
	api
		.get(`/user/randomPassword`, { params: { userEmail: userEmail, userName: userName } })
		.then(success)
		.catch(fail);
}

function emailCheck(userEmail, success, fail) {
	let api = API();
	api
		.get(`/user/emailCheck`, { params: { userEmail: userEmail } })
		.then(success)
		.catch(fail);
}

function getUserInfo(success, fail) {
	let api = API();
	api.get(`/user/getUserInfo`).then(success).catch(fail);
}

async function updateUserInfo(user, success, fail) {
	let api = API();
	await api.put(`/user/updateUserInfo`, JSON.stringify(user)).then(success).catch(fail);
}

function getFavoriteDeals(success, fail) {
	let api = API();
	api.get(`/user/getFavoriteDeals`).then(success).catch(fail);
}

function getFavoriteHouses(success, fail) {
	let api = API();
	api.get(`/user/getFavoriteHouses`).then(success).catch(fail);
}

export {
	getSido,
	getSigungu,
	getDong,
	register,
	randomPassword,
	emailCheck,
	getUserInfo,
	updateUserInfo,
	getFavoriteDeals,
	getFavoriteHouses,
};
