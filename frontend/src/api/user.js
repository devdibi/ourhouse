// import { API } from "./index.js";
3;
import { API } from "@/api/axios-jwt";

const api = API();

function getSido(success, fail) {
  api.get(`/house/sido`).then(success).catch(fail);
}

function getSigungu(sido, success, fail) {
  api
    .get(`/house/sigungu`, { params: { sido: sido } })
    .then(success)
    .catch(fail);
}

function getDong(sigungu, success, fail) {
  api
    .get(`/house/dong`, { params: { sigungu: sigungu } })
    .then(success)
    .catch(fail);
}

function register(user, success, fail) {
  api.post(`/user/register`, JSON.stringify(user)).then(success).catch(fail);
}

function randomPassword(userEmail, userName, success, fail) {
  api
    .get(`/user/randomPassword`, { params: { userEmail: userEmail, userName: userName } })
    .then(success)
    .catch(fail);
}

function emailCheck(userEmail, success, fail) {
  api
    .get(`/user/emailCheck`, { params: { userEmail: userEmail } })
    .then(success)
    .catch(fail);
}

function getUserInfo(success, fail) {
  api.get(`/user/getUserInfo`).then(success).catch(fail);
}

function updateUserInfo(user, success, fail) {
  api.put(`/user/updateUserInfo`, JSON.stringify(user)).then(success).catch(fail);
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
};
