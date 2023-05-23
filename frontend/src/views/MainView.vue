<template>
	<div id="main">
		<div id="main-page-image-container">
			<div id="text-box">
				<p id="main-text">어떠한 집을 찾고 있나요?</p>
			</div>
			<img src="@/assets/images/main-page-pic.png" alt="" />
			<div id="select-area-input-box" data-app>
				<v-select
					v-model="selectSido"
					:items="sidoList"
					item-text="name"
					item-value="sidoCode"
					menu-props="auto"
					label="시도 검색"
					hide-details
					outlined
					return-object
					color="#6750A4"
					background-color="#E0D0F4"
				></v-select>
				<span class="mx-1"></span>
				<v-select
					v-model="selectSigungu"
					:items="sigunguList"
					item-text="name"
					item-value="value"
					menu-props="auto"
					label="시군구 검색"
					hide-details
					outlined
					return-object
					color="#6750A4"
					background-color="#E0D0F4"
				></v-select>
				<span class="mx-1"></span>
				<v-select
					v-model="selectDong"
					:items="dongList"
					item-text="name"
					item-value="value"
					menu-props="auto"
					label="동 검색"
					hide-details
					outlined
					return-object
					color="#6750A4"
					background-color="#E0D0F4"
				></v-select>
			</div>
		</div>
	</div>
</template>

<script>
import axios from "axios";

export default {
	name: "MainView",
	components: {},
	data() {
		return {
			selectSido: "시도 검색",
			sidoList: [""],
			selectSigungu: String,
			sigunguList: [""],
			selectDong: String,
			dongList: [""],
		};
	},
	created() {
		let config = {
			method: "get", // 기본값
			baseURL: "http://localhost:9999/house/sido",
		};
		axios(config)
			.then(({ data }) => {
				console.log(data);
				this.sidoList = data.sidoList;
			})
			.catch((err) => {
				console.log(err);
			});
	},
	methods: {},
	watch: {
		selectSido({ sidoCode }) {
			let sido = sidoCode;
			// console.log(sido);

			let config = {
				method: "get", // 기본값
				baseURL: "http://localhost:9999/house/sigungu",
				params: {
					sido,
				},
			};
			axios(config)
				.then(({ data }) => {
					// console.log(data.sidoList);
					this.sigunguList = data.sidoList;
				})
				.catch((err) => {
					console.log(err);
				});
		},
		selectSigungu({ sigunguCode }) {
			let sigungu = sigunguCode;
			let config = {
				method: "get", // 기본값
				baseURL: "http://localhost:9999/house/dong",
				params: {
					sigungu,
				},
			};
			axios(config)
				.then(({ data }) => {
					// console.log(data.dongList);
					this.dongList = data.dongList;
				})
				.catch((err) => {
					console.log(err);
				});
		},
		selectDong({ dongCode }) {
			console.log(dongCode);
		},
	},
};
</script>

<style scoped>
#main {
	height: 650px;
	width: 100%;
	text-align: center;
}

#main-page-image-container {
	/* border: 1px black solid; */
	position: relative;
	height: 100%;
}

#main-page-image-container img {
	width: 100%;
	height: 100%;
	filter: opacity(50%);
}

#text-box {
	width: 100%;
	position: absolute;
	top: 250px;
	z-index: 1;
	/* left: 50%;
	transform: translate(-50%, 0); */
	display: inline-block;
}

#main-text {
	color: #ffffff;
	font-weight: bold;
	font-size: 50px;
}

#select-area-input-box {
	/* border: 1px black solid; */
	position: absolute;
	width: 700px;
	height: 72px;
	top: 436px;
	left: 50%;
	transform: translate(-50%, 0);
	display: flex;
}
</style>
