<template>
	<div>
		<content id="content">
			<!-- 상세 검색 Container -->
			<div id="search-bar">
				<h3 style="margin: 0px; margin-left: 50px">검색</h3>
				<!-- 기본 검색 -->
				<button class="btn btn-primary m-2">시도 선택</button>
				<button class="btn btn-primary m-2">구군 선택</button>
				<button class="btn btn-primary m-2">동 선택</button>
				<!-- 기본 검색 -->
				<h3>|</h3>
				<!-- <div style="display: inline-block; position: relative">
					<div></div>
					<button class="btn btn-primary" @click="areaOptionPopUpFlag = !areaOptionPopUpFlag">
						면적
					</button>
					<div class="pop-up" v-show="areaOptionPopUpFlag"></div>
				</div> -->
				<!-- 상세 검색 -->
				<ApartmentItemOption
					optionName="가격대"
					:isOpen="isOpen[1]"
					@toggleOptionWindow="changeWindow(1)"
				/>
				<ApartmentItemOption
					optionName="면적"
					:isOpen="isOpen[2]"
					@toggleOptionWindow="changeWindow(2)"
				/>
				<ApartmentItemOption
					optionName="층수"
					:isOpen="isOpen[3]"
					@toggleOptionWindow="changeWindow(3)"
				/>
				<!-- 상세 검색 -->
			</div>
			<!-- 상세 검색 Container -->
			<!-- 카카오 지도 -->
			<div id="wrap-map">
				<kakao-map :location="location" />
				<!-- 검색 결과 -->
				<div id="result">
					<ApartmentItem
						v-for="(apartDealData, index) in dealData"
						:key="index"
						:apartDealData="apartDealData"
						:index="index"
						@showDetailWindow="eventShowDetailWindow"
					/>
				</div>
				<!-- 검색 결과 -->
				<!-- 검색 결과 상세 페이지 -->
				<ApartmentItemDetail
					v-show="showDetailWindow"
					:showDetailWindowIdx="showDetailWindowIdx"
					:showDetailWindowData="showDetailWindowData"
				/>

				<!-- 검색 결과 상세 페이지 -->
			</div>
			<!-- 카카오 지도 -->
		</content>
		<!-- <TheFooter /> -->
		<!-- <div id="footer"></div> -->
	</div>
</template>

<script>
// import axios from "axios";
import KakaoMap from "@/components/KakaoMap.vue";
import ApartmentItem from "@/components/RealEstate/ApartmentItem.vue";
import ApartmentItemDetail from "@/components/RealEstate/ApartmentItemDetail.vue";
// import TheHeader from "@/components/TheHeader.vue";
// import TheFooter from "@/components/TheFooter.vue";

import ApartmentItemOption from "@/components/RealEstate/ApartmentItemOption.vue";

export default {
	components: {
		KakaoMap,
		ApartmentItem,
		ApartmentItemDetail,
		// TheHeader,
		// TheFooter,
		ApartmentItemOption,
	},
	data() {
		return {
			location: {
				lat: 33.450701,
				lng: 126.570667,
			},
			lastIndex: -1,
			isOpen: [true, false, false, false],
			showDetailWindow: false,
			showDetailWindowIdx: -1,
			showDetailWindowData: Object,
			areaOptionPopUpFlag: false,
			dealData: [
				{
					aptCode: "11110000000015",
					buildYear: "2008",
					name: "종로센트레빌",
					jibunAddress: "숭인동 2-1",
					roadAddress: "동망산길 47-47",
					lng: "127.015738287163",
					lat: "37.5815574293752",
					dongCode: "1111017500",
					houseLike: false,
					deals: [
						{
							dealCode: "111101501000036",
							price: "38900",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "11",
							dealLike: false,
						},
						{
							dealCode: "111101501000038",
							price: "47000",
							year: "2015",
							month: "1",
							area: "84.92",
							floor: "5",
							dealLike: false,
						},
						{
							dealCode: "111101501000041",
							price: "38200",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "5",
							dealLike: false,
						},
						{
							dealCode: "111101501000042",
							price: "47500",
							year: "2015",
							month: "1",
							area: "84.92",
							floor: "4",
							dealLike: false,
						},
						{
							dealCode: "111101501000045",
							price: "37900",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "4",
							dealLike: false,
						},
						{
							dealCode: "111101501000046",
							price: "37800",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "2",
							dealLike: false,
						},
						{
							dealCode: "111101501000047",
							price: "55000",
							year: "2015",
							month: "1",
							area: "114.67",
							floor: "5",
							dealLike: false,
						},
						{
							dealCode: "111101501000048",
							price: "38700",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "6",
							dealLike: false,
						},
						{
							dealCode: "111101501000052",
							price: "39000",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "11",
							dealLike: false,
						},
						{
							dealCode: "111101501000054",
							price: "46000",
							year: "2015",
							month: "1",
							area: "84.92",
							floor: "2",
							dealLike: false,
						},
						{
							dealCode: "111101501000056",
							price: "38200",
							year: "2015",
							month: "1",
							area: "59.92",
							floor: "5",
							dealLike: false,
						},
					],
				},
				{
					aptCode: "11110000000016",
					buildYear: "2013",
					name: "종로중흥S클래스",
					jibunAddress: "숭인동 202-3",
					roadAddress: "종로66길 28-28",
					lng: "127.021707822617",
					lat: "37.5735312366803",
					dongCode: "1111017500",
					houseLike: false,
					deals: [
						{
							dealCode: "111101501000037",
							price: "14000",
							year: "2015",
							month: "1",
							area: "19.466",
							floor: "14",
							dealLike: false,
						},
						{
							dealCode: "111101501000053",
							price: "13400",
							year: "2015",
							month: "1",
							area: "19.466",
							floor: "13",
							dealLike: false,
						},
					],
				},
				{
					aptCode: "11110000000017",
					buildYear: "2004",
					name: "롯데캐슬천지인",
					jibunAddress: "숭인동 76",
					roadAddress: "종로 347-347",
					lng: "127.016000015149",
					lat: "37.5740562569508",
					dongCode: "1111017500",
					houseLike: false,
					deals: [
						{
							dealCode: "111101501000039",
							price: "52600",
							year: "2015",
							month: "1",
							area: "84.95",
							floor: "30",
							dealLike: false,
						},
					],
				},
				{
					aptCode: "11110000000018",
					buildYear: "2009",
					name: "종로청계힐스테이트",
					jibunAddress: "숭인동 766",
					roadAddress: "숭인동길 21-21",
					lng: "127.020959405051",
					lat: "37.5757847596495",
					dongCode: "1111017500",
					houseLike: false,
					deals: [
						{
							dealCode: "111101501000040",
							price: "39500",
							year: "2015",
							month: "1",
							area: "59.9426",
							floor: "7",
							dealLike: false,
						},
						{
							dealCode: "111101501000043",
							price: "56000",
							year: "2015",
							month: "1",
							area: "114.7153",
							floor: "2",
							dealLike: false,
						},
						{
							dealCode: "111101501000044",
							price: "39000",
							year: "2015",
							month: "1",
							area: "59.9426",
							floor: "5",
							dealLike: false,
						},
						{
							dealCode: "111101501000049",
							price: "37150",
							year: "2015",
							month: "1",
							area: "59.9426",
							floor: "2",
							dealLike: false,
						},
						{
							dealCode: "111101501000055",
							price: "39600",
							year: "2015",
							month: "1",
							area: "59.9426",
							floor: "8",
							dealLike: false,
						},
					],
				},
				{
					aptCode: "11110000000019",
					buildYear: "2003",
					name: "탑스빌",
					jibunAddress: "숭인동 178-76",
					roadAddress: "종로65길 12-12",
					lng: "127.020120466443",
					lat: "37.5750071670982",
					dongCode: "1111017500",
					houseLike: false,
					deals: [
						{
							dealCode: "111101501000050",
							price: "34500",
							year: "2015",
							month: "1",
							area: "82.94",
							floor: "2",
							dealLike: false,
						},
					],
				},
				{
					aptCode: "11110000000020",
					buildYear: "2012",
					name: "삼전솔하임2차",
					jibunAddress: "숭인동 296-19",
					roadAddress: "종로58가길 27-27",
					lng: "127.016735679084",
					lat: "37.5723925579397",
					dongCode: "1111017500",
					houseLike: false,
					deals: [
						{
							dealCode: "111101501000051",
							price: "12300",
							year: "2015",
							month: "1",
							area: "16.67",
							floor: "5",
							dealLike: false,
						},
					],
				},
			],
		};
	},
	created() {
		// let config = {
		// 	method: "post", // 기본값
		// 	// `url`이 절대값이 아닌 경우 `baseURL`은 URL 앞에 붙습니다.
		// 	// 상대적인 URL을 인스턴스 메서드에 전달하려면 `baseURL`을 설정하는 것은 편리합니다.
		// 	baseURL: "http://192.168.31.53:8080/house/",
		// 	data: {
		// 		dongCode: "1111017500",
		// 		email: "",
		// 		month: 1,
		// 		year: 2015,
		// 	},
		// };
		// axios(config)
		// 	.then(({ data }) => {
		// 		this.dealData = data.data;
		// 		// console.log(this.dealData);
		// 	})
		// 	.catch((err) => {
		// 		console.log(err);
		// 	});
	},
	methods: {
		/**
		 * 상세 옵션 열고 닫기 함수
		 */
		changeWindow(index) {
			if (this.lastIndex === index) {
				this.$set(this.isOpen, index, !this.isOpen[index]);
			} else {
				this.$set(this.isOpen, this.lastIndex, false);
				this.$set(this.isOpen, index, !this.isOpen[index]);
				this.lastIndex = index;
			}
		},
		/**
		 * 아파트 거래 정보 창 열고 닫기 함수
		 */
		eventShowDetailWindow(data) {
			console.log(data);

			this.location.lat = data.lat;
			this.location.lng = data.lng;

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
</style>
