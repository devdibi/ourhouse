import axios from "axios";
import store from "@/store/store";

function API() {
	const instance = axios.create({
		baseURL: "http://localhost:9999/",
		headers: {
			"Content-Type": "application/json;charset=utf-8",
			Authorization: store.getters.getAccessToken,
		},
	});
	return instance;
}

export { API };
