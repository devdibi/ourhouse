import { API } from "./index.js";

const api = API();

function average(dong_code, success, fail) {
  api.get(`/dashboard/${dong_code}`).then(success).catch(fail);
}

export { average };
