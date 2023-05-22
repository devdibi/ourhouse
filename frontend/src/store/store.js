import Vue from "vue";
import Vuex from "vuex";

Vue.use(Vuex);

export default new Vuex.Store({
	state: {
		prefix: "Bearer ",
		accessToken: null,
	},
	getters: {
		getAccessToken(state) {
			if (state.accessToken === null) {
				return -1;
			}

			return state.prefix + state.accessToken;
		},
		isLogin(state) {
			return state.accessToken == null ? false : true;
		},
	},
	mutations: {
		setToken(state, token) {
			state.accessToken = token;
		},
	},
	actions: {
		setToken: ({ commit }, token) => {
			commit("setToken", token);
		},
	},
});
