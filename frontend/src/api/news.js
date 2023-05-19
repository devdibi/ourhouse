import { API } from "./index.js";

const api = API()

function list(success, fail) {
  api.get(`/notice`).then(success).catch(fail);
}

export { list };
