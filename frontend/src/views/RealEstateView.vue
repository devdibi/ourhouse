<template>
	<div>
		<content id="content">
			<!-- 상세 검색 Container -->
			<div id="search-bar">
				<div style="min-width: 100px" class="text-center">
					<h3 class="m-0 mr-3 ml-3">검색</h3>
				</div>

				<!-- 기본 검색 -->
				<!-- <button class="btn btn-primary m-2">시도 선택</button>
				<button class="btn btn-primary m-2">구군 선택</button>
				<button class="btn btn-primary m-2">동 선택</button> -->
				<div class="location-option-container">
					<!-- <b-form-select class="mr-2" v-model="selectSido" :options="sidoList"> </b-form-select> -->
					<b-form-select class="mr-2" v-model="selectSido">
						<b-form-select-option :value="undefined" disabled>시도 검색</b-form-select-option>
						<b-form-select-option
							v-for="sido in sidoList"
							:key="sido.sidoCode"
							:value="sido.sidoCode"
						>
							{{ sido.name }}
						</b-form-select-option>
					</b-form-select>
					<b-form-select class="mr-2" v-model="selectSigungu">
						<b-form-select-option :value="null" selected :disabled="true"
							>시군구 검색</b-form-select-option
						>
						<b-form-select-option
							v-for="(sigungu, index) in sigunguList"
							:key="index"
							:value="sigungu.sigunguCode"
						>
							{{ sigungu.name }}
						</b-form-select-option>
					</b-form-select>
					<b-form-select class="mr-2" v-model="selectDong">
						<b-form-select-option :value="null" selected :disabled="true"
							>동 검색</b-form-select-option
						>
						<b-form-select-option
							v-for="(dong, index) in dongList"
							:key="index"
							:value="dong.dongCode"
						>
							{{ dong.name }}
						</b-form-select-option>
					</b-form-select>
					<!-- <b-form-select v-model="selected" :options="options"></b-form-select> -->
				</div>
				<!-- 기본 검색 -->
				<h3>|</h3>
				<!-- 상세 검색 -->
				<ApartmentItemOptionDateVue
					optionName="날짜"
					:isOpen="isOpen[0]"
					@changeDetailOption="changeDetailOption"
					@toggleOptionWindow="changeWindow(0)"
				/>
				<ApartmentItemOption
					optionName="가격대"
					:isOpen="isOpen[1]"
					:min="1"
					:max="10"
					@changeDetailOption="changeDetailOption"
					@toggleOptionWindow="changeWindow(1)"
				/>
				<ApartmentItemOption
					optionName="면적"
					:isOpen="isOpen[2]"
					:min="10"
					:max="100"
					@changeDetailOption="changeDetailOption"
					@toggleOptionWindow="changeWindow(2)"
				/>
				<ApartmentItemOption
					optionName="층수"
					:isOpen="isOpen[3]"
					:min="1"
					:max="20"
					@changeDetailOption="changeDetailOption"
					@toggleOptionWindow="changeWindow(3)"
				/>
				<div>
					<button class="btn btn-primary ml-2" @click="getBusStopInfo">test</button>
				</div>
				<!-- 상세 검색 -->
			</div>
			<!-- 상세 검색 Container -->
			<!-- 카카오 지도 -->
			<div id="wrap-map">
				<KakaoMap :location="location" :locations="locations" :option="clickOption" />
				<!-- 검색 결과 -->
				<div id="result">
					<h3 v-if="resultIsEmpty" class="text-center mt-3">검색 결과가 없습니다.</h3>
					<ApartmentItem
						v-else
						v-for="(apartDealData, index) in dealData"
						:key="index"
						:apartDealData="apartDealData"
						:index="index"
						:lastClickIndex="showDetailWindowIdx"
						:show="showDetailWindow"
						@showDetailWindowEvent="eventShowDetailWindow"
					/>
				</div>
				<!-- 검색 결과 -->
				<!-- 검색 결과 상세 페이지 -->
				<ApartmentItemDetail
					v-show="showDetailWindow"
					:showDetailWindowIdx="showDetailWindowIdx"
					:showDetailWindowData="showDetailWindowData"
					@sortEvent="sortEvent"
				/>

				<!-- 이미지 마커 표시 -->
				<ul id="image-marker-box">
					<li :class="{ markerClick: busClick }" class="marker-box" @click="markerClick('bus')">
						<img class="marker-img" src="@/assets/icon/bus.png" alt="" />
						<div class="marker-name">버스</div>
					</li>
					<li :class="{ markerClick: comClick }" class="marker-box" @click="markerClick('com')">
						<img class="marker-img" src="@/assets/icon/commercial.png" alt="" />
						<div class="marker-name">상권</div>
					</li>
					<li :class="{ markerClick: medClick }" class="marker-box" @click="markerClick('medical')">
						<img class="marker-img" src="@/assets/icon/drugstore.png" alt="" />
						<div class="marker-name">약국</div>
					</li>
					<li
						:class="{ markerClick: hopClick }"
						class="marker-box"
						@click="markerClick('hospital')"
					>
						<img class="marker-img" src="@/assets/icon/hospital.png" alt="" />
						<div class="marker-name">병원</div>
					</li>
				</ul>

				<!-- 검색 결과 상세 페이지 -->
			</div>
			<!-- 카카오 지도 -->
		</content>
		<!-- <TheFooter /> -->
		<!-- <div id="footer"></div> -->
	</div>
</template>

<script>
import axios from "axios";
import { API } from "@/api/axios-jwt";
import KakaoMap from "@/components/KakaoMap.vue";
import ApartmentItem from "@/components/RealEstate/ApartmentItem.vue";
import ApartmentItemDetail from "@/components/RealEstate/ApartmentItemDetail.vue";
// import TheHeader from "@/components/TheHeader.vue";
// import TheFooter from "@/components/TheFooter.vue";

import ApartmentItemOption from "@/components/RealEstate/ApartmentItemOption.vue";
import ApartmentItemOptionDateVue from "@/components/RealEstate/ApartmentItemOptionDate.vue";

export default {
	components: {
		KakaoMap,
		ApartmentItem,
		ApartmentItemDetail,
		// TheHeader,
		// TheFooter,
		ApartmentItemOption,
		ApartmentItemOptionDateVue,
	},
	data() {
		return {
			// 검색 결과가 있는지
			resultIsEmpty: true,
			// 기본 검색
			selectSido: String,
			sidoList: Array,
			selectSigungu: String,
			sigunguList: Array,
			selectDong: String,
			dongList: Array,
			// 아파트 위치
			location: {
				lat: 33.450701,
				lng: 126.570667,
			},
			locations: [],
			// 상세 검색 상태 관리 변수
			lastOptionIndex: -1,
			isOpen: [false, false, false, false],
			// 검색 결과 상태 관리 변수
			showDetailWindow: false,
			showDetailWindowIdx: -1,
			showDetailWindowData: Object,
			// 검색 결과 데이터
			dealData: [],

			busClick: false,
			medClick: false,
			comClick: false,
			hopClick: false,
			clickOption: "-1",
		};
	},
	watch: {
		dealData(val) {
			this.locations = val;
		},
		selectSido(sido) {
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
		selectSigungu(sigungu) {
			console.log(sigungu);
			let config = {
				method: "get", // 기본값
				baseURL: "http://localhost:9999/house/dong",
				params: {
					sigungu,
				},
			};
			axios(config)
				.then(({ data }) => {
					console.log(data.sidoList);
					this.dongList = data.dongList;
				})
				.catch((err) => {
					console.log(err);
				});
		},
		selectDong(dongCode) {
			let searchCondition = {
				dongCode: dongCode,
				//나머지 정보 추가 가능
			};
			//로그인 했을 시
			if (this.$store.getters.isLogin) {
				let http = API();
				http
					.post("/house/user", searchCondition)
					.then(({ data }) => {
						console.log(data);
						if (data.data.length == null) {
							this.resultIsEmpty = true;
						} else if (data.data.length === 0) {
							this.resultIsEmpty = true;
						} else {
							this.showDetailWindow = false;
							console.log(data.data);
							this.resultIsEmpty = false;
							this.dealData = data.data;
						}
					})
					.catch((err) => {
						console.log(err);
					});
			}
			//로그인 안 했을 시
			else {
				console.log("로그인 안 했을 시");
				let config = {
					method: "post",
					baseURL: "http://localhost:9999/house/",
					data: {
						...searchCondition,
					},
				};
				axios(config)
					.then(({ data }) => {
						if (data.data.length === 0) {
							this.resultIsEmpty = true;
						} else {
							this.showDetailWindow = false;
							console.log(data.data);
							this.resultIsEmpty = false;
							this.dealData = data.data;
						}
					})
					.catch((err) => {
						console.log(err);
					});
			}
		},
	},
	created() {
		//메인 화면에서 동 입력으로 넘어왔을 시, 바로 출력
		if (this.$route.params.dongCode != null) {
			console.log(this.$route.params.dongCode);
			let searchCondition = {
				dongCode: this.$route.params.dongCode,
			};
			//로그인 했을 시
			if (this.$store.getters.isLogin) {
				let http = API();
				http
					.post("/house/user", searchCondition)
					.then(({ data }) => {
						console.log(data);
						if (data.data.length == null) {
							this.resultIsEmpty = true;
						} else if (data.data.length === 0) {
							this.resultIsEmpty = true;
						} else {
							this.showDetailWindow = false;
							console.log(data.data);
							this.resultIsEmpty = false;
							this.dealData = data.data;
						}
					})
					.catch((err) => {
						console.log(err);
					});
			}
			//로그인 안 했을 시
			else {
				console.log("로그인 안 했을 시");
				let config = {
					method: "post",
					baseURL: "http://localhost:9999/house/",
					data: {
						...searchCondition,
					},
				};
				axios(config)
					.then(({ data }) => {
						if (data.data.length === 0) {
							this.resultIsEmpty = true;
						} else {
							this.showDetailWindow = false;
							console.log(data.data);
							this.resultIsEmpty = false;
							this.dealData = data.data;
						}
					})
					.catch((err) => {
						console.log(err);
					});
			}
		}

		let config = {
			method: "get", // 기본값
			baseURL: "http://localhost:9999/house/sido",
		};
		axios(config)
			.then(({ data }) => {
				console.log(data);
				this.sidoList = data.sidoList;
				// data.sidoList.forEach((element) => {
				// 	tmp.push({ text: element.name, value: element.sidoCode });
				// });
				// console.log(tmp);
				// tmp.unshift({ text: "시도 검색", value: null, disabled: true });
				// this.sidoList = tmp;
			})
			.catch((err) => {
				console.log(err);
			});
	},
	methods: {
		sortEvent(sortOption) {
			console.log(sortOption);
			let desc = -1;
			if (sortOption.includes("Asc")) {
				desc = 1;
			}
			console.log(desc);

			switch (sortOption) {
				case "latestDate":
					this.showDetailWindowData.deals.sort(function (o1, o2) {
						if (o1.year === o2.year) {
							return -(o1.month - o2.month);
						}

						return -(o1.year - o2.year);
					});
					break;
				case "oldestDate":
					this.showDetailWindowData.deals.sort(function (o1, o2) {
						if (o1.year === o2.year) {
							return o1.month - o2.month;
						}

						return o1.year - o2.year;
					});
					break;
				case "price":
				case "priceAsc":
					this.showDetailWindowData.deals.sort(function (o1, o2) {
						return (o1.price - o2.price) * desc;
					});
					break;
				case "area":
				case "areaAsc":
					this.showDetailWindowData.deals.sort(function (o1, o2) {
						return (o1.area - o2.area) * desc;
					});
					break;
				case "floor":
				case "floorAsc":
					this.showDetailWindowData.deals.sort(function (o1, o2) {
						return (o1.floor - o2.floor) * desc;
					});
					break;
			}
		},
		markerClick(option) {
			this.clickOption = option;
			console.log(this.clickOption);
			if (option === "bus") {
				this.busClick = true;
				this.comClick = false;
				this.medClick = false;
				this.hopClick = false;
			} else if (option === "com") {
				this.busClick = false;
				this.comClick = true;
				this.medClick = false;
				this.hopClick = false;
			} else if (option === "medical") {
				this.busClick = false;
				this.comClick = false;
				this.medClick = true;
				this.hopClick = false;
			} else if (option === "hospital") {
				this.busClick = false;
				this.comClick = false;
				this.medClick = false;
				this.hopClick = true;
			}
		},
		getBusStopInfo() {
			console.log("=== 버스 정류장 정보 불러오기 ===");
		},
		// 상세 옵션 제어 함수
		changeDetailOption({ optionName, value }) {
			console.log("=== 상세 옵션 ===");
			console.log(optionName, value);
			console.log("=================");

			if (this.dealData.length === 0) {
				return;
			}

			if (optionName === "층수") {
				for (let i = 0; i < this.dealData.length; i++) {
					Array.from(this.dealData[i].deals).forEach((element) => {
						if (value[0] <= element.floor && element.floor <= value[1]) {
							element.showWindow = true;
						} else {
							element.showWindow = false;
						}
					});
				}
			} else if (optionName === "면적") {
				for (let i = 0; i < this.dealData.length; i++) {
					Array.from(this.dealData[i].deals).forEach((element) => {
						if (value[0] <= element.area && element.area <= value[1]) {
							element.showWindow = true;
						} else {
							element.showWindow = false;
						}
					});
				}
			} else if (optionName === "가격대") {
				let minPirce = value[0] * 10000;
				let maxPrice = value[1] * 10000;
				for (let i = 0; i < this.dealData.length; i++) {
					Array.from(this.dealData[i].deals).forEach((element) => {
						if (minPirce <= element.price && element.price <= maxPrice) {
							element.showWindow = true;
						} else {
							element.showWindow = false;
						}
					});
				}
			}
		},
		/**
		 * 상세 옵션 열고 닫기 함수
		 */
		changeWindow(index) {
			if (this.lastOptionIndex === index) {
				this.$set(this.isOpen, index, !this.isOpen[index]);
			} else {
				this.$set(this.isOpen, this.lastOptionIndex, false);
				this.$set(this.isOpen, index, !this.isOpen[index]);
				this.lastOptionIndex = index;
			}
		},
		/**
		 * 아파트 거래 정보 창 열고 닫기 함수
		 */
		eventShowDetailWindow(data) {
			// console.log(data);

			this.location.lat = data.lat;
			this.location.lng = data.lng;

			this.busClick = false;
			this.comClick = false;
			this.medClick = false;

			let index = data.id;
			if (this.showDetailWindowIdx === index) {
				this.showDetailWindow = !this.showDetailWindow;
			} else {
				this.showDetailWindow = true;
				this.showDetailWindowIdx = index;
				this.showDetailWindowData = this.dealData[index];
				// console.log("Aa", this.showDetailWindowData);
			}
		},
	},
};
</script>

<style scoped>
.location-option-container {
	min-width: 500px;
	display: flex;
	justify-content: space-between;
}

#close-detail {
	position: absolute;
	/* left: 966px; */
	right: -42px;
	top: 20px;
	width: 42px;
	height: 42px;
	border: 1px solid rgba(0, 0, 0, 0.16);
	border-left: 0;
	border-top-right-radius: 1px;
	border-bottom-right-radius: 1px;
	background-color: red;
	z-index: 2500;
}

#detail {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	z-index: 2;
	left: 406px;
	width: 560px;
	background: white;
}

#result {
	position: absolute;
	top: 0;
	bottom: 0;
	left: 0;
	width: 400px;
	background: #fef7ff;
	/* box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); */
	z-index: 200;
	border: 1px black solid;
	border-top: none;
	border-bottom: none;
	overflow-y: auto;
}

#wrap-map {
	position: relative;
	background: purple;
	height: calc(100% - 56px);
	/* margin: 5px; */
}

#header {
	height: 50px;
	width: 100%;
	background: black;
	position: relative;
	z-index: 1;
	top: 0;
	display: block;
}

#nav-bar {
	padding: 0px;
	height: 50px;
	width: 100%;
	background: blueviolet;
	position: relative;
	/* z-index: 1; */
	top: 0;
}

#content {
	height: calc(100% - 106px);
	width: 100%;
	background: #ccc;
	position: absolute;
	z-index: 0;
}

#search-bar {
	position: relative;
	/* background: #0cf; */
	background: #ffffff;
	/* box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25); */
	z-index: 5;
	height: 56px;
	border: 1px black solid;
	display: flex;
	align-items: center;
}

/* #search-bar button {
  height: 50px;
} */

/* #footer {
	height: 50px;
	width: 100%;
	background: red;
	position: fixed;
	bottom: 0;
} */

.pop-up {
	width: 200px;
	height: 500px;
	background: burlywood;
	position: absolute;
	top: 50px;
	left: 0;
}

#image-marker-box {
	width: 200px;
	height: 75px;
	position: absolute;
	top: 10px;
	right: 10px;
	background: white;
	z-index: 2;
	display: flex;
	border-radius: 15px;
}

.marker-box {
	width: 100%;
	list-style: none;
}

.marker-img {
	width: 100%;
	height: 50px;
}
.marker-name {
	height: 25px;
	text-align: center;
}

.markerClick {
	background: pink;
}
</style>
