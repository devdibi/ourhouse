<template>
	<div id="main">
		<div id="main-page-image-container">
			<div id="text-box">
				<p id="main-text">어떠한 집을 찾고 있나요?</p>
			</div>
			<img src="@/assets/images/main-page-pic.png" alt="" />
			<div id="select-area-input-box" data-app>
				<v-select v-model="selectSido" :items="sidoList" item-text="name" item-value="sidoCode" menu-props="auto"
					label="시도 검색" hide-details outlined return-object color="#6750A4" background-color="#E0D0F4"></v-select>
				<span class="mx-1"></span>
				<v-select v-model="selectSigungu" :items="sigunguList" item-text="name" item-value="value" menu-props="auto"
					label="시군구 검색" hide-details outlined return-object color="#6750A4" background-color="#E0D0F4"></v-select>
				<span class="mx-1"></span>
				<v-select v-model="selectDong" :items="dongList" item-text="name" item-value="value" menu-props="auto"
					label="동 검색" hide-details outlined return-object color="#6750A4" background-color="#E0D0F4"></v-select>
			</div>
		</div>
		<!-- 공지사항, 자유게시판, 부동산뉴스 시작-->
		<div id="icon-navbar">
			<div class="iconContainer">
				<router-link to="/notice/list">
					<img src="@/assets/icon/notice_icon.png" alt="공지 게시판" id="noticeIcon">
					<p>공지사항</p>
				</router-link>
			</div>
			<div class="iconContainer">
				<router-link to="/board/list">
					<img src="@/assets/icon/board_icon.png" alt="자유 게시판" id="boardIcon">
					<p>자유 게시판</p>
				</router-link>
			</div>
			<div class="iconContainer">
				<router-link to="/news">
					<img src="@/assets/icon/news_icon.png" alt="뉴스" id="newsIcon">
					<p>뉴스</p>
				</router-link>
			</div>
		</div>
		<!-- 좋아요 많은 아파트, 좋아요 많은 거래 상위 5개 출력 -->
		<div id="main-stats-box">
			<!-- 아파트 랭킹 출력 -->
			<div id="aptContainer">
				<h3 id="aptTitle">아파트 랭킹 Top 5</h3>
				<b-table :items="topApts"></b-table>
			</div>
			<!-- 거래 랭킹 출력 -->
			<div id="dealContainer">
				<h3 id="dealTitle">거래 랭킹 Top 5</h3>
				<b-table :items="topDeals"></b-table>
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
			topApts: [],
			topDeals: [],
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

		//아파트 랭킹 불러오기
		config = {
			method: "get",
			baseURL: "http://localhost:9999/house/topapt",
		}
		axios(config)
			.then(({ data }) => {
				if (data.message == "success") {
					for (var i = 0; i < 5; i++){
						var tmpApt = {
							"순위":i+1,
							"주소": data.houses[i].sidoName + data.houses[i].gugunName + data.houses[i].jibunAddress,
							"아파트 이름": data.houses[i].aptName,
							"평균 가격":data.houses[i].priceAvg + " 만원",
							"평균 면적": data.houses[i].areaAvg + "m2",
							"좋아요 수": data.houses[i].likeNums
						}
						this.topApts.push(tmpApt);
					}
				}
			})
			.catch((err) => {
				console.log(err)
			});

		config = {
			method: "get",
			baseURL: "http://localhost:9999/house/topdeal",
		}
		axios(config)
			.then(({ data }) => {
				if (data.message == "success") {
					for (var i = 0; i < 5; i++){
						var tmpDeal = {
							"순위":i+1,
							"주소": data.deals[i].sidoName + data.deals[i].gugunName + data.deals[i].jibunAddress,
							"아파트 이름": data.deals[i].aptName,
							"층":data.deals[i].floor,
							"가격":data.deals[i].price + " 만원",
							"면적": data.deals[i].area + "m2",
							"좋아요 수": data.deals[i].likeNums
						}
						this.topDeals.push(tmpDeal);
					}
				}
			})
			.catch((err) => {
				console.log(err)
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
* {
	font-family: 'Noto Sans';
	font-style: normal;
	color: #929292;;
}

a {
	text-decoration: none;
	color: black;
}

a:hover {
	text-decoration: none;
	color: #6960d5;
}

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

#icon-navbar {
	width: 100%;
	max-width: 1000px;
	height: 250px;
	margin: auto;
	padding-top: 50px;
}

.iconContainer {
	float: left;
	width: 33%;
	margin-top: 50px; 
	margin-bottom: 50px;
	font-size: 20px;
}

#newsIcon,
#noticeIcon,
#boardIcon {
	display: inline-flex;
	width: auto;
	max-width: 140px;
	margin: auto;
  justify-content: center;
  align-items: center;
	padding-bottom: 20px;
}

#main-stats-box {
	width: 100%;
}

#aptContainer, 
#dealContainer{
	display: inline-flex;
	margin: auto;
	margin-bottom: 50px;
}

#aptTable, #dealTable{
	text-align: center;
	padding: 20px;
}

#dealTitle, #aptTitle{
	color: #6a60d5ad;
	font-weight:bold;
}

</style>
