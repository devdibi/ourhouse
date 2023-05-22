import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		accessToken: String,
	},
	getters: {
		getAccessToken(state) {
			if (state.token == null) {
				return -1;
			}

			return state.accessToken;
		},
		isLogin(state) {
			return state.token == null ? false : true;
		},
	},
	mutations: {
		setToken(state, token) {
			state.token = token;
		},
	},
	actions: {
		setToken: ({ commit }, token) => {
			commit("setToken", token);
		},
	},
});
