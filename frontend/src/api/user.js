import { API } from "./index.js";

const api = API();

function getSido(success, fail) {
  api.get(`/house/sido`).then(success).catch(fail);
}

function getSigungu(sido, success, fail) {
  api.get(`/house/sigungu`, { params: sido }).then(success).catch(fail);
}

function getDong(sigungu, success, fail) {
  api.get(`http://localhost:9999/house/`, { params: sigungu }).then(success).catch(fail);
}

export { getSido, getSigungu, getDong };
