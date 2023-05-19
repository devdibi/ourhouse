import Vue from "vue";
import VueRouter from "vue-router";

import MainView from "@/views/MainView.vue";
import RealEstate from "@/views/RealEstateView";

Vue.use(VueRouter);

const routes = [
	{
		path: "/",
		name: "main",
		component: MainView,
	},
	{
		path: "/land",
		name: "land",
		component: RealEstate,
	},
];

const router = new VueRouter({
	mode: "history",
	base: process.env.BASE_URL,
	routes,
});

export default router;
